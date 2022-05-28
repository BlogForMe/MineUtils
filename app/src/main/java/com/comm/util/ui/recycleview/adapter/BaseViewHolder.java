package com.comm.util.ui.recycleview.adapter;

import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.IdRes;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Administrator on 2017/12/27 0027.
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {

    /**
     * Views indexed with their IDs
     */
    private final SparseArray<View> views;
    private BaseQuickAdapter adapter;


    public BaseViewHolder(View itemView) {
        super(itemView);
        this.views = new SparseArray<>();
    }

    /**
     * Sets the adapter of a adapter view.
     *
     * @param adapter The adapter;
     * @return The BaseViewHolder for chaining.
     */
    protected BaseViewHolder setAdapter(BaseQuickAdapter adapter) {
        this.adapter = adapter;
        return this;
    }

    /**
     * Will set the text of a TextView.
     *
     * @param viewId The view id.
     * @param value  The text to put in the text view.
     * @return The BaseViewHolder for chaining.
     */
    public BaseViewHolder setText(int viewId, CharSequence value) {
        TextView view = getView(viewId);
        view.setText(value);
        return this;
    }

    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * Set a view visibility to VISIBLE (true) or INVISIBLE (false).
     *
     * @param viewId
     * @param visible
     * @return
     */
    public BaseViewHolder setVisible(@IdRes int viewId, boolean visible) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
        return this;
    }

}
