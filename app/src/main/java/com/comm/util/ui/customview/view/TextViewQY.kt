package com.comm.util.ui.customview.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.TextView

/**
 * Android群英传 自定义View
 */
class TextViewQY @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : TextView(context, attrs, defStyleAttr) {

    private var mPaint1: Paint = Paint().apply {
        color = resources.getColor(android.R.color.holo_blue_light)
        style = Paint.Style.FILL
    }

    private var mPaint2: Paint = Paint().apply {
        color = Color.YELLOW
        style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas?) {
        //绘制外层矩形
        canvas?.drawRect(0f,0f,measuredWidth.toFloat(),measuredHeight.toFloat(),mPaint1)

        //绘制内层矩形
        canvas?.drawRect(10f,10f,measuredWidth.toFloat()-10f,measuredHeight.toFloat()-10f,mPaint2)
        super.onDraw(canvas)

    }


}