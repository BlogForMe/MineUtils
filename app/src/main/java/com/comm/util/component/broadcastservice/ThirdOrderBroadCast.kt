package com.comm.util.component.broadcastservice

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log


class ThirdOrderBroadCast : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.e("receive", "High")
        //传递结果到下一个广播接收器
        val code = 3
        val data = "hello"
        val bundle: Bundle? = null
        setResult(code, data, bundle)
    }
}