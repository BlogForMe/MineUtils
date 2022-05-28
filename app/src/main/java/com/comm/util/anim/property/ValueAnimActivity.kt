package com.comm.util.anim.property

import android.animation.ValueAnimator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.comm.util.R
import kotlinx.android.synthetic.main.activity_value_anim.*
import timber.log.Timber

class ValueAnimActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_value_anim)

        bt_value_anim.setOnClickListener {
            val valueAnimator = ValueAnimator.ofInt(bt_value_anim.layoutParams.width, 600)
            valueAnimator.duration = 2000

            valueAnimator.addUpdateListener {
                val currentValue = it.animatedValue
                // 获得每次变化后的属性值
                Timber.d("currentValue $currentValue")

                // 每次值变化时，将值手动赋值给对象的属性
                bt_value_anim.layoutParams.width = currentValue as Int
                // 即将每次变化后的值 赋 给按钮的宽度，这样就实现了按钮宽度属性的动态变化

                bt_value_anim.requestLayout()
            }

            valueAnimator.start()
        }
    }
}