package com.comm.util.anim.interpol

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.comm.util.R
import kotlinx.android.synthetic.main.activity_interpalator.*

class InterpolatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interpalator)

        bt_anim_interpolator.setOnClickListener {
            val curTranslationX = bt_anim_interpolator.translationX

            val animator = ObjectAnimator.ofFloat(bt_anim_interpolator, "translationX", curTranslationX, 300f, curTranslationX)
            animator.duration = 5000
            animator.interpolator = DecelerateAccelerateInterpolator()
            animator.start()
        }
    }
}