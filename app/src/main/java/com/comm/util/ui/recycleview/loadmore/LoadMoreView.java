package com.comm.util.ui.recycleview.loadmore;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import com.comm.util.ui.recycleview.adapter.BaseViewHolder;

/**
 * Created by A on 2018/1/18.
 */

public abstract class LoadMoreView {
    public static final int STATUS_DEFAULT = 1;
    public static final int STATUS_LOADING = 2;
    public static final int STATUS_FAIL = 3;
    public static final int STATUS_END = 4;
    private boolean mLoadMoreEndGone = false;
    private int mLoadMoreStatus = STATUS_DEFAULT;

    public boolean isLoadEndMoreGone() {
        if (getLoadEndViewId() == 0) {
            return true;
        }
        return mLoadMoreEndGone;
    }

    protected abstract int getLoadEndViewId();

    public void convert(BaseViewHolder holder) {
        switch (mLoadMoreStatus) {
            case STATUS_LOADING:
                visibleLoading(holder, true);
                visibleLoadFail(holder, false);
                visibleLoadEnd(holder, false);
                break;
            case STATUS_FAIL:
                visibleLoading(holder, false);
                visibleLoadFail(holder, true);
                visibleLoadEnd(holder, false);
                break;
            case STATUS_END:
                visibleLoading(holder, false);
                visibleLoadFail(holder, false);
                visibleLoadEnd(holder, true);
                break;
            case STATUS_DEFAULT:
                visibleLoading(holder, false);
                visibleLoadFail(holder, false);
                visibleLoadEnd(holder, false);
                break;

        }
    }

    private void visibleLoadEnd(BaseViewHolder holder, boolean visible) {
        final int loadEndViewId = getLoadEndViewId();
        if (loadEndViewId != 0) {
            holder.setVisible(loadEndViewId, visible);
        }
    }

    private void visibleLoadFail(BaseViewHolder holder, boolean visible) {
        holder.setVisible(getLoadFailViewId(), visible);
    }

    private void visibleLoading(BaseViewHolder holder, boolean visible) {
        holder.setVisible(getLoadingViewId(), visible);
    }

    protected abstract
    @IdRes
    int getLoadingViewId();


    /**
     * load more layout
     *
     * @return
     */
    public abstract
    @LayoutRes
    int getLayoutId();

    /**
     * load fail view
     *
     * @return
     */
    protected abstract
    @IdRes
    int getLoadFailViewId();

    public int getLoadMoreStatus() {
        return mLoadMoreStatus;
    }

    public void setLoadMoreStatus(int statusLoading) {
        this.mLoadMoreStatus = statusLoading;
    }

    public void setLoadMoreEndGone(boolean loadMoreEndGone) {
        this.mLoadMoreEndGone = loadMoreEndGone;
    }
}
