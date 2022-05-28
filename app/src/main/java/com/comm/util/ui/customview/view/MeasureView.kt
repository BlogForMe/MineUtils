package com.comm.util.ui.customview.view

import android.content.Context
import android.util.AttributeSet
import android.view.View

class MeasureView : View{
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(measureHeight(widthMeasureSpec),measureHeight(heightMeasureSpec))
    }

    private fun measureHeight(measureSpec: Int): Int {
        var result = 200
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)

        if(specMode==MeasureSpec.EXACTLY){
            result = specSize
        }else{
            if (specMode== MeasureSpec.AT_MOST){
                result = result.coerceAtMost(specSize)
            }
        }
        return  result
    }
}