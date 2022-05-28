package com.comm.util.socket

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.comm.util.R
import kotlinx.android.synthetic.main.activity_mina_test.*

class MinaTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mina_test)

        bt_start_send.setOnClickListener {
            val intent = Intent(this,BleDataService::class.java)
            startService(intent)
        }
    }
}
