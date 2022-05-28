package com.comm.util.ui.customview.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.MotionEvent.*
import android.view.ViewGroup
import android.widget.Scroller

class IScrollerView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {

    private val mScroller: Any = Scroller(context)


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val count = childCount
        for (i in 0 until count) {
            val childView = getChildAt(i)
            measureChild(childView, widthMeasureSpec, heightMeasureSpec)
        }
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            ACTION_DOWN -> {
                Log.d("MyScrollView ", "ACTION_DOWN getScrollY $scrollY getScrollX $scrollX")
            }
            ACTION_MOVE -> {
                Log.d("MyScrollView ", "ACTION_MOVE getScrollY $scrollY getScrollX $scrollX")
            }
            ACTION_UP -> {
                Log.d("MyScrollView ", "ACTION_UP getScrollY $scrollY   getScrollX $scrollX")
                scrollBy(-200, 0)
            }
        }
        return true
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {

//        val childAt = getChildAt(0)
//        Timber.d("childAt.width ${childAt.width} childAt.height ${childAt.height}")
//        childAt.layout(0, 0, childAt.measuredWidth,childAt.measuredHeight)
    }

}