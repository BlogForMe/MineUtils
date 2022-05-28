package com.comm.util.ui.recycleview.adapter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.comm.util.ui.recycleview.loadmore.LoadMoreView;
import com.comm.util.ui.recycleview.loadmore.SimpleLoadMoreView;
import timber.log.Timber;

/**
 * Created by Administrator on 2017/12/27 0027.
 */

public abstract class BaseQuickAdapter<T, K extends BaseViewHolder> extends RecyclerView.Adapter<K> {

    public static final int HEADER_VIEW = 0x00000111;
    public static final int LOADING_VIEW = 0x00000222;
    public static final int FOOTER_VIEW = 0x00000333;
    public static final int EMPTY_VIEW = 0x00000555;
    private final LoadMoreView mLoadMoreView = new SimpleLoadMoreView();
    private final int mPreLoadNumber = 1;
    protected List<T> mData;
    protected int mLayoutResId;
    protected Context mContent;
    protected LayoutInflater mLayoutInflater;
    // load more
    private boolean mLoadMoreEnable = false;
    private boolean mNextLoadEnable = false;
    private boolean mLoading = false;
    private RequestLoadMoreListener mRequestLoadMoreListener;
    //header footer
    private LinearLayout mHeaderLayout;
    private int mLastPosition = -1;
    //head footer;
    private LinearLayout mFooterLayout;
    private RecyclerView mRecyclerView;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId The layout resource id of each item.
     * @param data        A new list is created out of this one to avoid mutable list
     */
    public BaseQuickAdapter(int layoutResId, List<T> data) {
        this.mData = data == null ? new ArrayList<T>() : data;
        if (layoutResId != 0) {
            this.mLayoutResId = layoutResId;
        }
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    public void setRecyclerView(RecyclerView mRecyclerView) {
        this.mRecyclerView = mRecyclerView;
    }

    @Override
    public K onCreateViewHolder(ViewGroup parent, int viewType) {
        K baseViewHolder = null;
        this.mContent = parent.getContext();
        this.mLayoutInflater = LayoutInflater.from(mContent);
//        View view = mLayoutInflater.inflate(R.layout.layout_animation, null);
        switch (viewType) {
            case LOADING_VIEW:
                baseViewHolder = getLoadingView(parent);
                break;
            case  HEADER_VIEW:
                baseViewHolder = createBaseViewHolder(mHeaderLayout);
                break;
            default:
                baseViewHolder = onCreateNormViewHolder(parent, viewType);
                break;
        }
//        bindViewClickListener(baseViewHolder);
        baseViewHolder.setAdapter(this);
        return baseViewHolder;
    }

    private K getLoadingView(ViewGroup parent) {
        View view = getItemView(mLoadMoreView.getLayoutId(), parent);
        K holder = createBaseViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(K holder, int position) {
        //Do not move position, need to change before LoadMoreView binding
        autoLoadMore(position);
        int viewType = holder.getItemViewType();
        switch (viewType) {
            case 0:
                convert(holder, getItem(position - getHeaderLayoutCount()));
                break;
            case LOADING_VIEW:
                mLoadMoreView.convert(holder);
                break;
            case HEADER_VIEW:
                break;
            case EMPTY_VIEW:
                break;
            case FOOTER_VIEW:
                break;
            default:
                convert(holder, getItem(position - getHeaderLayoutCount()));
                break;
        }
    }

    private void autoLoadMore(int position) {
        if (getLoadMoreViewCount() == 0) {
            return;
        }
        if (position < getItemCount() - mPreLoadNumber) {
            return;
        }
        if (mLoadMoreView.getLoadMoreStatus() != LoadMoreView.STATUS_DEFAULT) {
            return;
        }
        mLoadMoreView.setLoadMoreStatus(LoadMoreView.STATUS_LOADING);
        if (!mLoading) {
            mLoading = true;
            if (getRecyclerView() != null) {
                getRecyclerView().post(new Runnable() {
                    @Override
                    public void run() {
                        mRequestLoadMoreListener.onLoadMoreRequested();
                    }
                });
            } else {
                mRequestLoadMoreListener.onLoadMoreRequested();
            }
        }

    }


    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    protected abstract void convert(K helper, T item);

    private T getItem(int position) {
        if (position < mData.size()) {
            return mData.get(position);
        } else
            return null;
    }

    /**
     * if addHeaderView will be return 1, if not will be return 0
     */
    private int getHeaderLayoutCount() {
        if (mHeaderLayout == null || mHeaderLayout.getChildCount() == 0) {
            return 0;
        }
        return 1;
    }

    @Override
    public int getItemCount() {
        int count = getHeaderLayoutCount() + mData.size() + getFooterLayoutCount() + getLoadMoreViewCount();
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        int numHeaders = getHeaderLayoutCount();
        if (position < numHeaders) {            //位置小于headerView的个数，显示headerView;
            return HEADER_VIEW;
        } else {
            int adjPosition = position - numHeaders;
            int adapterCount = mData.size();
            if (adjPosition < adapterCount) {
                return getDefItemViewType(adjPosition);
            } else {
//                adjPosition = adjPosition - adapterCount;   //实际位置
//                int numFooters = getFooterLayoutCount();
//                if (adjPosition < numFooters) {
//                    return FOOTER_VIEW;
//                } else {
                Timber.d("测试");
                return LOADING_VIEW;
//                }
            }
        }
    }


    private int getDefItemViewType(int adjPosition) {
//        if (mN)
        return super.getItemViewType(adjPosition);
    }

    private int getFooterLayoutCount() {
        if (mFooterLayout == null) {
            return 0;
        }
        return 1;
    }


    private K onCreateNormViewHolder(ViewGroup parent, int viewType) {
        int layoutId = mLayoutResId;
//        if (mMultiTypeDelegate)
        return createBaseViewHolder(parent, layoutId);
    }

    private K createBaseViewHolder(ViewGroup parent, int layoutResId) {
        return createBaseViewHolder(getItemView(layoutResId, parent));
    }

    /**
     * @param layoutResId ID for an XML layout resource to load
     * @param parent      Optional view to be the parent of the generated hierarchy or else simply an object that
     *                    provides a set of LayoutParams values for root of the returned
     *                    hierarchy
     * @return view will be return
     */
    private View getItemView(int layoutResId, ViewGroup parent) {
        return mLayoutInflater.inflate(layoutResId, parent, false); //一开始没添加false导致一直出错
    }

    /**
     * if you want to use subclass of BaseViewHolder in the adapter,
     * you must override the method to create new ViewHolder.
     *
     * @param itemView
     * @return new ViewHolder
     */
    private K createBaseViewHolder(View itemView) {
        Class temp = getClass();
        Class z = null;
        while (z == null && null != temp) {
            z = getInstanceGenericKClass(temp);
            temp = temp.getSuperclass();
        }
        K k;
        // 泛型擦除会导致z为null
        if (z == null) {
            k = (K) new BaseViewHolder(itemView);
        } else {
            k = createGenericKInstance(z, itemView);
        }
        return k != null ? k : (K) new BaseViewHolder(itemView);
    }

    /**
     * try to create Generic K instance
     *
     * @param z
     * @param itemView
     * @return
     */

    private K createGenericKInstance(Class z, View itemView) {
        try {
            Constructor constructor = null;
            // inner and unstatic class
            if (z.isMemberClass() && !Modifier.isStatic(z.getModifiers())) {
                constructor = z.getDeclaredConstructor(getClass(), View.class);
                constructor.setAccessible(true);
                return (K) constructor.newInstance(this, itemView);
            } else {
                constructor = z.getDeclaredConstructor(View.class);
                constructor.setAccessible(true);
                return (K) constructor.newInstance(itemView);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * get generic parameter K
     *
     * @param z
     * @return
     */
    private Class getInstanceGenericKClass(Class z) {
        Type type = z.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] types = ((ParameterizedType) type).getActualTypeArguments();
            for (Type temp : types) {
                if (temp instanceof Class) {
                    Class temClass = (Class) temp;
                    if (BaseViewHolder.class.isAssignableFrom(temClass)) {
                        return temClass;
                    }
                } else if (temp instanceof ParameterizedType) {
                    Type rawType = ((ParameterizedType) temp).getRawType();
                    if (rawType instanceof Class && BaseViewHolder.class.isAssignableFrom((Class<?>) rawType)) {
                        return (Class<?>) rawType;
                    }
                }
            }
        }
        return null;
    }


    private void bindViewClickListener(K baseViewHolder) {
        if (baseViewHolder == null) {
            return;
        }
        View view = baseViewHolder.itemView;
        if (view == null) {
            return;
        }
    }

    /**
     * Set the enabled state of load more.
     *
     * @param enable True if load more is enabled, false otherwise.
     */
    public void setEnableLoadMore(boolean enable) {
        int oldLoadMoreCount = getLoadMoreViewCount();
    }

    /**
     * Load more view count
     *
     * @return 0 or 1
     */
    private int getLoadMoreViewCount() {
        if (mRequestLoadMoreListener == null || !mLoadMoreEnable) {
            return 0;
        }
        if (!mNextLoadEnable && mLoadMoreView.isLoadEndMoreGone()) {
            return 0;
        }
        if (mData.size() == 0) {
            return 0;
        }
        return 1;
    }

    /**
     * setting up a new instance to data;
     *
     * @param data
     */
    public void setNewData(List<T> data) {
        this.mData = data == null ? new ArrayList<T>() : data;
        if (mRequestLoadMoreListener != null) {

        }
        mLastPosition = -1;
        notifyDataSetChanged();
    }

    /**
     * add new data to the end of mData
     *
     * @param newData
     */
    public void addData(Collection<? extends T> newData) {
        mData.addAll(newData);
        notifyItemRangeChanged(mData.size() - newData.size() + getHeaderLayoutCount(), newData.size());
        compatibilityDataSizeChanged(newData.size());
    }

    /**
     * compatible getLoadMoreViewCount and getEmptyViewCount may change
     *
     * @param size
     */
    private void compatibilityDataSizeChanged(int size) {
        final int dataSize = mData == null ? 0 : mData.size();
        if (dataSize == size) {
            notifyDataSetChanged();
        }
    }

    /**
     * Refresh end, no more data
     *
     * @param gone if true gone the load more view
     */
    public void loadMoreEnd(boolean gone) {
        if (getLoadMoreViewCount() == 0) {
            return;
        }
        mLoading = false;
        mNextLoadEnable = false;
        mLoadMoreView.setLoadMoreEndGone(gone);
        if (gone) {
            notifyItemRemoved(getLoadMoreViewPosition());
        } else {
            mLoadMoreView.setLoadMoreStatus(LoadMoreView.STATUS_END);
            notifyItemChanged(getLoadMoreViewPosition());
        }
    }

    /**
     * Gets to load more locations
     *
     * @return
     */
    private int getLoadMoreViewPosition() {
        return getHeaderLayoutCount() + mData.size() + getFooterLayoutCount();
    }

    /**
     * Refresh complete
     */
    public void loadMoreComplete() {
        if (getLoadMoreViewCount() == 0) {
            return;
        }
        mLoading = false;
        mNextLoadEnable = true;
        mLoadMoreView.setLoadMoreStatus(LoadMoreView.STATUS_DEFAULT);
        notifyItemChanged(getLoadMoreViewPosition());
    }

    /**
     * Append header to the rear of the mHeaderLayout.
     *
     * @param header
     */
    public int addHeaderView(View header) {
        return addHeaderView(header, -1);
    }

    /**
     * Add header view to mHeaderLayout and set header view posotion int mHeaderLayout
     * when index = -1 or index > child count in mHeaderLayout.
     * the effect of this method is the same as that of {@link #addHeaderView(View)}.
     *
     * @param header
     * @param index  the position in mHeaderLayout of this header.
     *               when index = -1 or index >= child count in mHeaderLayout.
     *               the effect of this method is the same as that of{@link #addHeaderView(View)}
     * @return
     */
    private int addHeaderView(View header, int index) {
        return addHeaderView(header, index, LinearLayout.VERTICAL);
    }

    /**
     * @param header
     * @param index
     * @param orientation
     * @return
     */
    private int addHeaderView(View header, int index, int orientation) {
        if (mHeaderLayout == null) {
            mHeaderLayout = new LinearLayout(header.getContext());
            if (orientation == LinearLayout.VERTICAL) {
                mHeaderLayout.setOrientation(LinearLayout.VERTICAL);
                mHeaderLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            } else {
                mHeaderLayout.setOrientation(LinearLayout.HORIZONTAL);
                mHeaderLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            }
        }
        final int childCount = mHeaderLayout.getChildCount();
        if (index < 0 || index > childCount) {
            index = childCount;
        }
        mHeaderLayout.addView(header, index);
        if (mHeaderLayout.getChildCount() == 1) {
            int position = getHeaderViewPosition();
            if (position != -1) {
                notifyItemInserted(position);
            }
        }
        return index;
    }

    private int getHeaderViewPosition() {
        //Return to header view notify position
//        if (getEmptyViewCount()==1){
//        }
        return  0;
    }

    private int getEmptyViewCount() {
        return 0;
    }

    public void setOnLoadMoreListener(RequestLoadMoreListener requestLoadMoreListener, RecyclerView recyclerView) {
        openLoadMore(requestLoadMoreListener);
        if (getRecyclerView() == null) {
            setRecyclerView(recyclerView);
        }
    }

    private void openLoadMore(RequestLoadMoreListener requestLoadMoreListener) {
        this.mRequestLoadMoreListener = requestLoadMoreListener;
        mNextLoadEnable = true;
        mLoadMoreEnable = true;
        mLoading = false;
    }

    public interface RequestLoadMoreListener {
        void onLoadMoreRequested();
    }

}
