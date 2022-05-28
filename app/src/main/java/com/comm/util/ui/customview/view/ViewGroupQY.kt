package com.comm.util.ui.customview.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.android.util.DisplayUtils.getScreenH
import timber.log.Timber

class ViewGroupQY(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {


    private var mLastY: Float? = 0.0f

    private var mScreenHeight: Int = getScreenH(context).toInt()

    init {
        isClickable = true
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val count = childCount
        for (i in 0 until count) {
            val childView = getChildAt(i)
            measureChild(childView, widthMeasureSpec, heightMeasureSpec)
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        //设置ViewGroup高度
        val mlp = layoutParams
        mlp.height = mScreenHeight * childCount
        layoutParams = mlp

        // 依次修改每个子View的Top和bottom属性 , 按顺序排列下来
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            if (child.visibility != View.GONE) {
                child.layout(l, i * mScreenHeight, r, (i + 1) * mScreenHeight)
            }
        }
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val y = event?.y
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                mLastY = y
                Timber.d("ACTION_DOWN mLastY $mLastY")
            }
            MotionEvent.ACTION_MOVE -> {
//                if (!mScrooler.is)
                var dy = y?.let { mLastY?.minus(it) }
                Timber.d("ACTION_MOVE dy1 $dy")

                if (scrollY < 0) {
                    Timber.d("ACTION_MOVE  dy2 $dy")
                    dy = 0f
                }
                if (scrollY > height - mScreenHeight) {
                    Timber.d("ACTION_MOVE  dy3 $dy")
                    dy = 0f
                }
                Timber.d("ACTION_MOVE dy4 $dy")
                if (dy != null) {
                    scrollBy(0, dy.toInt())
                }
                mLastY = y
            }
        }
        return super.onTouchEvent(event)
    }
}