package com.comm.util.ui.recycleview.loadmore;

import com.comm.util.R;

/**
 * Created by A on 2018/1/18.
 */

public class SimpleLoadMoreView extends LoadMoreView {

    @Override
    public int getLayoutId() {
        return R.layout.load_more_load_end_view;
    }

    @Override
    protected int getLoadEndViewId() {
        return R.id.load_more_load_end_view;
    }

    @Override
    protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    @Override
    protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }
}
