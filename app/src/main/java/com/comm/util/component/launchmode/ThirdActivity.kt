package com.comm.util.component.launchmode

import android.content.Intent
import android.os.Bundle
import com.comm.util.R
import com.comm.util.base.BaseActivity
import kotlinx.android.synthetic.main.activity_third.*

class ThirdActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bt_gofirst.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }

    override fun setLayoutId(): Int {
      return  R.layout.activity_third
    }
}