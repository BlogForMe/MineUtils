package com.comm.util.anim.property

import android.animation.TypeEvaluator


class PointEvaluator : TypeEvaluator<Point> {
    override fun evaluate(fraction: Float, startValue: Point?, endValue: Point?): Point {

        // 将动画初始值startValue 和 动画结束值endValue 强制类型转换成Point对象
        val startPoint = startValue
        val endPoint =  endValue

        // 根据fraction来计算当前动画的x和y的值
        val x = startPoint!!.x + fraction * (endPoint!!.x - startPoint.x)
        val y = startPoint.y + fraction * (endPoint.y - startPoint.y)

        // 将计算后的坐标封装到一个新的Point对象中并返回
        val point = Point(x,y)
        return  point
    }
}