package com.comm.util.ui.recycleview.divider.line;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author : John
 * @date : 2018/8/20
 */
public class MyItemDecoration extends RecyclerView.ItemDecoration {
    public static final int[] ATRRS  = new int[]{
            android.R.attr.listDivider
    };        public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;
private final Drawable mDivider;
    private final Context context;


    public MyItemDecoration(Context context) {
        this.context = context;
        TypedArray ta = context.obtainStyledAttributes(ATRRS);
        this.mDivider = ta.getDrawable(0);
        ta.recycle();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
//        drawVerticalLine(c, parent, state);
        drawHorizontalLine(c, parent, state);
    }

    public void drawHorizontalLine(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
//        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(0);

            //获得child的布局信息
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left,top,right,bottom);
            mDivider.draw(c);
//        }
    }


    private void drawVerticalLine(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int top = parent.getPaddingTop();
        int bottom = parent.getHeight() - parent.getPaddingBottom();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            //获得child的布局信息
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mDivider.getIntrinsicWidth();

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(0,0,0,mDivider.getIntrinsicHeight());
    }
}
