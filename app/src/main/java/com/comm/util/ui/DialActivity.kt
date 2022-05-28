package com.comm.util.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.comm.util.R
import kotlinx.android.synthetic.main.activity_dial.*

/**
 * 绘制体脂秤 体重的表盘
 */
class DialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dial)
//
//        val ff = 0.0f;
//        bt_value.setOnClickListener {
//            body_˝data_view.setBdFatValue(ff.toString())
//        }

        bt_setkg.setOnClickListener {
            dkgview.setEbelKg(78.4f)
        }
    }
}
