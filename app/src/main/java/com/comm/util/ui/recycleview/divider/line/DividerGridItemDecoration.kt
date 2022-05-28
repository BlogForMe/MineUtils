package com.comm.util.ui.recycleview.divider.line

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import timber.log.Timber

class DividerGridItemDecoration(context: Context) : RecyclerView.ItemDecoration() {
    private var mDivider: Drawable
    private val ATTRS = intArrayOf(android.R.attr.listDivider)

    init {
        val a = context.obtainStyledAttributes(ATTRS)
        mDivider = a.getDrawable(0)!!
        a.recycle()
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
//        super.onDraw(c, parent, state)
        drawHorizontal(c, parent)
        drawVertical(c, parent)
    }

    private fun drawVertical(c: Canvas, parent: RecyclerView) {
        for (i in 0..parent.childCount - 1) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = child.top - params.topMargin
            val bottom = child.bottom + params.bottomMargin
            val left = child.right + params.rightMargin
            val right = left + mDivider.intrinsicWidth
            Timber.i("drawVertical left $left top $top right $right bottom $bottom")
            mDivider.setBounds(left, top, right, bottom)
            mDivider.draw(c)
        }
    }

    private fun drawHorizontal(c: Canvas, parent: RecyclerView) {
//        val childCount= parent.childCount
        for (i in 0..parent.childCount - 1) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val left = child.left - params.leftMargin
            val right = child.right - params.rightMargin + mDivider.intrinsicWidth
            val top = child.bottom + params.bottomMargin
            val bottom = top + mDivider.intrinsicHeight

            Timber.i("drawHorizontal left $left top $top right $right bottom $bottom")
            mDivider.setBounds(left, top, right, bottom)
            mDivider.draw(c)
        }
    }

    override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
        super.getItemOffsets(outRect, itemPosition, parent)
        val spanCount = getSpanCount(parent)
        val childCount = parent.adapter!!.itemCount
        if (isLastRaw(parent, itemPosition, spanCount, childCount)) {
            outRect.set(0, 0, mDivider.intrinsicWidth, 0)
        } else if (isLastColum(parent, itemPosition, spanCount, childCount)) {
            outRect.set(0, 0, 0, mDivider.intrinsicHeight)
        } else {
            outRect.set(0, 0, mDivider.intrinsicWidth, mDivider.intrinsicHeight)
        }
    }

    private fun isLastColum(parent: RecyclerView, itemPosition: Int, spanCount: Int, childCount: Int): Boolean {
        val layoutManager = parent.layoutManager
        if (layoutManager is GridLayoutManager) {
            if ((itemPosition + 1) % spanCount == 0) { // 如果是最后一列，则不需要绘制右边
                return true
            }
        } else {
            val childCount = childCount - childCount % spanCount
            if (itemPosition >= childCount) {
                return true
            }
        }
        return false
    }


    private fun getSpanCount(parent: RecyclerView): Int {
        var spanCount = -1
        val layoutManager = parent.layoutManager
        if (layoutManager is GridLayoutManager) {
            spanCount = layoutManager.spanCount
        }
        return spanCount
    }

    private fun isLastRaw(parent: RecyclerView, pos: Int, spanCount: Int,
                          childCount: Int): Boolean {
        val childCount = childCount - childCount % spanCount
        if (pos > childCount) {  // 如果是最后一行，则不需要绘制底部
            return true
        } else {
            if ((pos + 1) % spanCount == 0) {
                return true
            }
        }
        return false
    }
}