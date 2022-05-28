package com.comm.util.ui.recycleview.xiangxue;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.TextView;
import com.comm.util.R;

import static android.content.ContentValues.TAG;
//自定义控件 之旅

/*
总结：适配器将空间UI与数据解耦
RecyclerView支持多个不同布局类型，他们是怎么缓存的呢
总结：回收池中不同的类型的 get set 通过type值查找
 */

public class RecyclerView extends ViewGroup {

    private boolean needRelayout;

    private Adapter adapter;

    //屏幕上显示的View集合
    private List<View> viewList;

    //item
    private int rowCount;
    //所有item的高度
    private int[] heights;
    //屏幕宽
    private int width;
    //屏幕高
    private int height;
    //
    private Recycler recycler;
    //最小滑动距离
    private int touchSlop;
    //记录当前滑动的Y值
    private int currentY;
    //屏幕上显示的第一个item的上边界到屏幕上边界的距离，有正负
    private int scrollY;
    //屏幕上显示的第一个item的index
    private int firstRow;

    public RecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        needRelayout = true;
        this.viewList = new ArrayList<>();
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        //最小滑动距离
        touchSlop = viewConfiguration.getScaledTouchSlop();
    }

    public void setAdapter(Adapter adapter) {
        needRelayout = true;
        this.adapter = adapter;
        recycler = new Recycler(adapter.getViewTypeCount());

    }

    //滑动拦截
    //点击不拦截
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept = false;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                currentY = (int) ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int y2 = Math.abs(currentY - (int) ev.getRawY());
                intercept = y2 > touchSlop;
                break;
        }
        return intercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                int y2 = (int) event.getRawY();
                int diff = currentY - y2;
                scrollBy(0, diff);
                break;
        }

        return super.onTouchEvent(event);
    }

    @Override
    public void scrollBy(int x, int diff) {
        //scrollY 第一个可见Item 距离屏幕左上角的距离
        scrollY += diff;
        //firstRow第一个可见元素在数据数组中的索引
        //修正
        scrollY = scrollBounds(scrollY,firstRow,height);
        if (scrollY > 0){
//            正向上滑
            while (heights[firstRow] < scrollY){
                if (!viewList.isEmpty()){
                    //移除最顶上的View
                    Log.e(TAG, "-----移除-----" + ((TextView)viewList.get(0).findViewById(R.id.text1)).getText());
                    View view = viewList.get(0);
                    viewList.remove(view);
                    removeView(view);
                    scrollY -= heights[firstRow];
                    firstRow++;
                }
            }
            while (getFilledHeight() < height) {
                //添加View
                int size = viewList.size();
                int dataIndex = firstRow + size;
                View view = obtain(dataIndex,width,height);
                Log.i(TAG, "添加dataIndex：" + dataIndex + "getChildCount()" + ((ViewGroup) view).getChildCount());
                viewList.add(view);
                addView(view);
            }
        }else{
//            正向下滑
//            firstRow + viewList.size() - 1屏幕上最后一个item的index
            while (!viewList.isEmpty() && getFilledHeight() - height > heights[firstRow + viewList.size() - 1]){
                View view = viewList.remove(viewList.size() - 1);
                Log.e(TAG, "-----移除-----" + ((TextView)view.findViewById(R.id.text1)).getText());
                removeView(view);
            }
            while (0 > scrollY){
                firstRow--;
                View view = obtain(firstRow,width,height);
                viewList.add(0,view);
                addView(view);
                scrollY += heights[firstRow+1];
            }
        }

        repositionViews();
    }

    //最后一个显示的控件底部到屏幕上边界的距离
    private int getFilledHeight() {
        return sumArray(firstRow,viewList.size()) - scrollY;
    }
    //获取在屏幕上的所有显示控件的高度和
    private int sumArray(int firstIndex,int count){
        int sum = 0;
        count += firstIndex;
        for (int i = firstIndex; i < count; i++) {
            sum += heights[i];
        }
        return sum;
    }

    //滑到最顶部和滑到最底部
    private int scrollBounds(int scrollY, int firstRow, int height) {
        if (scrollY == 0){

        }else if (scrollY < 0){
            //修整下滑的临界值
            scrollY = Math.max(scrollY,-sumArray(0,firstRow));
        }else{
            //修整上滑临界值
            /*  sumArray(firstRow,adapter.getCount()- firstRow)
            屏幕上第一个可以显示的item到所有数据的最后一个item的高度
                sumArray(firstRow,adapter.getCount()- firstRow) - height为负时，
            说明屏幕下边界以下已经没有View并且没有可以填充的View
            最后需要与scrollY取最小值，防止屏幕下边界一下还有很多View可以补充的情况
            */
            scrollY = Math.min(scrollY, Math.max(0,sumArray(firstRow,adapter.getCount()- firstRow) - height));

        }
        return scrollY;
    }

    private void repositionViews() {
        int left, top, right, bottom, i;
        top = -scrollY;
        i = 0;
        for (View view : viewList) {
            bottom = top + heights[i++];
            view.layout(0, top, width, bottom);
            top = bottom;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
//onLayout   ----> childView  onLayout 重新摆放子控件

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (needRelayout || changed) {
            //测量
            needRelayout = false;

            //摆放的时候   初始化
            viewList.clear();
            //比较耗时间
            removeAllViews();
            if (adapter != null) {
                rowCount = adapter.getCount();
                heights = new int[rowCount];
                for (int i = 0; i < rowCount; i++) {
                    heights[i] = adapter.getHeight(i);
                }
                width = r - l;
                height = b - t;
                int top = 0, bottom;
                for (int i = 0; i < rowCount && top < height; i++) {
                    bottom = top + heights[i];
                    //实例化 布局
                    //怎么摆放
                    //摆放多少个
                    View view = makeAndSetup(i, 0, top, width, bottom);
                    viewList.add(view);
                    addView(view, 0);
                    top = bottom;
                }
            }

        }
    }

    private View makeAndSetup(int indexData, int left, int top, int right, int bottom) {
        View view = obtain(indexData,right-left,bottom-top);
        view.layout(left, top, right, bottom);
        return view;
    }

    private View obtain(int row, int width, int height) {
        int type = adapter.getItemViewType(row);
        //回收池
        View reclyView = recycler.getRecyclerdView(type);
//        取不出来
        View view = null;
        if (reclyView == null){
            view = adapter.onCreateViewHolder(row, view, this);
            if (view == null) {
                throw new RuntimeException("onCreateViewHolder 必须初始化");
            }
        }else{
            view = adapter.onBinderViewHodler(row,reclyView,this);
        }
//        回收池
//        tag值   填充   移除

        if (view == null) {
            throw new RuntimeException("converview is not null");
        }
        view.setTag(R.id.tag_type_view, type);
        view.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
        return view;
    }


    @Override
    public void removeView(View view) {
        super.removeView(view);
        int type = (int) view.getTag(R.id.tag_type_view);
        recycler.addRecyclerdView(view, type);
    }

    interface Adapter {
        View onCreateViewHolder(int position, View converView, ViewGroup parent);

        View onBinderViewHodler(int position, View converView, ViewGroup parent);

        //Item的类型
        int getItemViewType(int row);

        int getViewTypeCount();

        int getCount();

        //固定高度
        int getHeight(int index);
    }
}
