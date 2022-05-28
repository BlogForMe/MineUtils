package com.comm.util.anim.property

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import timber.log.Timber

class MyView : View {

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    // 设置需要用到的变量
    val RADIUS = 70f // 圆的半径 = 70

    private var currentPoint // 当前点坐标
            : Point? = null
    val mPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLUE
    }


    init {

    }

    override fun onDraw(canvas: Canvas?) {

        // 如果当前点坐标为空(即第一次)
        if (currentPoint == null) {
            currentPoint = Point(RADIUS, RADIUS)
            val x = currentPoint?.x
            val y = currentPoint?.y
            if (x != null && y != null) {
                canvas?.drawCircle(x, y, RADIUS, mPaint)
            }
            // (重点关注)将属性动画作用到View中
            // 步骤1:创建初始动画时的对象点  & 结束动画时的对象点
            // (重点关注)将属性动画作用到View中
            // 步骤1:创建初始动画时的对象点  & 结束动画时的对象点
            val startPoint = Point(RADIUS, RADIUS) // 初始点为圆心(70,70)

            val endPoint = Point(700f, 1000f) // 结束点为(700,1000)

            // 步骤2:创建动画对象 & 设置初始值 和 结束值
            val anim = ValueAnimator.ofObject(PointEvaluator(), startPoint, endPoint)
            // 参数说明
            // 参数1：TypeEvaluator 类型参数 - 使用自定义的PointEvaluator(实现了TypeEvaluator接口)
            // 参数2：初始动画的对象点
            // 参数3：结束动画的对象点


            // 步骤3：设置动画参数
            anim.duration = 5000

            // 设置 值的更新监听器
            // 即每当坐标值（Point对象）更新一次,该方法就会被调用一次
            anim.addUpdateListener {
                currentPoint = it.animatedValue as Point?
                // 将每次变化后的坐标值（估值器PointEvaluator中evaluate（）返回的Piont对象值）到当前坐标值对象（currentPoint）
                // 从而更新当前坐标值（currentPoint）
                Timber.d("currentPoint==null  ${currentPoint?.x}  ${currentPoint?.y}")
                // 步骤4：每次赋值后就重新绘制，从而实现动画效果
                invalidate()
            }
            anim.start()
        } else {
            // 如果坐标值不为0,则画圆
            // 所以坐标值每改变一次,就会调用onDraw()一次,就会画一次圆,从而实现动画效果

            // 在该点画一个圆:圆心 = (30,30),半径 = 30
            val x = currentPoint!!.x
            val y = currentPoint!!.y
            canvas?.drawCircle(x, y, RADIUS, mPaint)

            Timber.d("currentPoint currentPoint!=null  ${currentPoint?.x}  ${currentPoint?.y}")

        }
    }
}