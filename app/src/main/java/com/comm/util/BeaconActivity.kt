package com.comm.util

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

//@Route(path = "/app/BeaconActivity")
class BeaconActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beacon)
        findViewById<View>(R.id.bt_learn_more).setOnClickListener {
            val i = Intent()
            i.action = "com.google.android.payments.standard"
            startActivity(i)
        }
    }
}