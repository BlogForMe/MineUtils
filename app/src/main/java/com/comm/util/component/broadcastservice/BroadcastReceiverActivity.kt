package com.comm.util.component.broadcastservice

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.comm.util.R
import kotlinx.android.synthetic.main.activity_broadcast_receiver.*

class BroadcastReceiverActivity : AppCompatActivity() {
    private var updateBr: UpdateBroadcastReceiver? = null
    private val downUrL = "http://116.62.149.166:8301/v2/open/version/download"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast_receiver)

        //点击发送普通广播
        bt_broadcast.setOnClickListener {
            val intent = Intent()
            intent.action = UpdateBroadcastReceiver.ACTION_UPDATE
//            LocalBroadcastManager.getInstance(this).sendBroadcast(intentBroad)
//            intent.setPackage("com.comm.util")
            sendBroadcast(intent)
        }

        bt_go_second.setOnClickListener {
            startActivity(Intent(this, SecondBroadCastActivity::class.java))
        }


        val intent = Intent()
        intent.action = "com.broadcast.android"

        bt_order_broadcast.setOnClickListener {
            sendOrderedBroadcast(intent,null)
        }

        bt_local_broadcast.setOnClickListener {
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
        }


    }

//    override fun onStart() {
//        super.onStart()
//        updateBr = UpdateBroadcastReceiver()
//        val intentFilter = IntentFilter()
//        intentFilter.addAction(UpdateBroadcastReceiver.ACTION_UPDATE)
////        registerReceiver(updateBr, intentFilter)
//
//        /**
//         * LocalBroadcastManager
//         */
//        updateBr?.let {
//            LocalBroadcastManager.getInstance(this).registerReceiver(it,intentFilter)
//        }
//    }


    override fun onDestroy() {
        super.onDestroy()
        if (updateBr != null) {
            unregisterReceiver(updateBr)
        }
    }
}