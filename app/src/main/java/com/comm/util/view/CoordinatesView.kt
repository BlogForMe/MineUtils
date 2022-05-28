package com.comm.util.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View


/**
 * 屏幕坐标系
 */
class CoordinatesView :View{
    val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var arcWidth: Float = 2.0f

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView(context, attrs)
    }

    private fun initView(context: Context?, attrs: AttributeSet?) {

        mPaint.apply {
            color = Color.parseColor("#333333")
            strokeWidth = arcWidth
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
            textSize = 30f
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)


        val centerX = width / 2.0f
        val centerY = height / 2.0f

        canvas?.translate(centerX,centerY)
        canvas?.save()
        canvas?.drawText("测试文字1",0f,0f,mPaint)

        canvas?.rotate(180f)
        canvas?.drawText("测试文字2",95f,95f,mPaint)
        canvas?.restore()
        canvas?.drawText("测试文字3",150f,150f,mPaint)

//        canvas?.drawLine(0f,0f,500f,0f,mPaint)
//        canvas?.drawLine(0f,0f,0f,1000f,mPaint)
//
//
//        val rect = RectF(0f, -400f, 400f, 0f)   // 矩形区域





//
//        mPaint.setColor(Color.BLUE)           // 绘制黑色矩形
//        canvas?.drawRect(rect, mPaint)




    }


}