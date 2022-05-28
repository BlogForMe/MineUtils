package com.comm.util.ui

import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.comm.util.R
import kotlinx.android.synthetic.main.activity_toast_util.*

class ToastUtilActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toast_util)
        bt_show_toast.setOnClickListener {
            var toast = Toast.makeText(this,"showToast",Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.BOTTOM ,0,0)
            toast.show()
        }



    }
}
