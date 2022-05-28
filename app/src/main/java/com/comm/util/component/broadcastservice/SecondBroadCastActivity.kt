package com.comm.util.component.broadcastservice

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.comm.util.R
import kotlinx.android.synthetic.main.activity_broadcast_receiver.*

class SecondBroadCastActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_broad_cast)

        //点击发送普通广播
        bt_broadcast.setOnClickListener {
            val intentBroad = Intent()
            intentBroad.action = UpdateBroadcastReceiver.ACTION_UPDATE
            sendBroadcast(intentBroad)
        }
    }

    override fun onStart() {
        super.onStart()
        var updateBr = UpdateBroadcastReceiver()
        val intentFilter = IntentFilter()
        intentFilter.addAction(UpdateBroadcastReceiver.ACTION_UPDATE)
        registerReceiver(updateBr, intentFilter)
    }
}