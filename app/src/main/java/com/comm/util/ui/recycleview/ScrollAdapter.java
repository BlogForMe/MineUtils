package com.comm.util.ui.recycleview;

import java.util.List;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.comm.util.R;
import com.comm.util.bean.RecipeBean;

import static com.comm.util.bean.RecipeBean.TYPE_BOTTOM;
import static com.comm.util.bean.RecipeBean.TYPE_ITEM;

public class ScrollAdapter extends BaseMultiItemQuickAdapter<RecipeBean, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param list is created out of this one to avoid mutable list
     */
    public ScrollAdapter(List<RecipeBean> reList) {
        super(reList);
        addItemType(TYPE_ITEM, R.layout.item_pharmacy);
        addItemType(TYPE_BOTTOM, R.layout.item_pharmacy_bottom);
    }

    @Override
    protected void convert(BaseViewHolder helper, RecipeBean item) {


    }
}
