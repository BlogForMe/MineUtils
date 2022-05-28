package com.comm.util.anim.interpol

import android.animation.TimeInterpolator

class DecelerateAccelerateInterpolator : TimeInterpolator {
    override fun getInterpolation(input: Float): Float {
        var result: Float = 0.0f
        if (input <= 0.5) {
            result = Math.sin(Math.PI * input).toFloat() / 2
            // 使用正弦函数来实现先减速后加速的功能，逻辑如下：
            // 因为正弦函数初始弧度变化值非常大，刚好和余弦函数是相反的
            // 随着弧度的增加，正弦函数的变化值也会逐渐变小，这样也就实现了减速的效果。
            // 当弧度大于π/2之后，整个过程相反了过来，现在正弦函数的弧度变化值非常小，渐渐随着弧度继续增加，变化值越来越大，弧度到π时结束，这样从0过度到π，也就实现了先减速后加速的效果
        } else {
             result= (2 - Math.sin(Math.PI * input)).toFloat() / 2
        }
        return result
        // 返回的result值 = 随着动画进度呈先减速后加速的变化趋势
    }
}