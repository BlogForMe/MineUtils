package com.comm.util

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class AppManagerActivity : AppCompatActivity() {
    var TAG = "MainActivity3"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        findViewById<View>(R.id.go_seeting_dev).setOnClickListener { view: View? ->
            val intent = Intent()
            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            intent.data = Uri.parse("package:" + "my.com.tngdigital.ewallet.dev")
            //intent.setData(Uri.parse("package:" + "my.com.tngdigital.ewallet.uat"));
            startActivity(intent)
        }
        findViewById<View>(R.id.go_seeting_sbx).setOnClickListener { view: View? ->
            val intent = Intent()
            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            intent.data = Uri.parse("package:" + "my.com.tngdigital.ewallet.uat")
            //intent.setData(Uri.parse("package:" + "my.com.tngdigital.ewallet.uat"));
            startActivity(intent)
        }
        findViewById<View>(R.id.go_seeting_sit).setOnClickListener { view: View? ->
            val intent = Intent()
            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            intent.data = Uri.parse("package:" + "my.com.tngdigital.ewallet.sit")
            //intent.setData(Uri.parse("package:" + "my.com.tngdigital.ewallet.uat"));
            startActivity(intent)
        }
    }
}