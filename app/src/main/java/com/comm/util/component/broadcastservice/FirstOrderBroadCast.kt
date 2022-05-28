package com.comm.util.component.broadcastservice

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log

class FirstOrderBroadCast : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.e("receive", "Low")
        val code = 1
        val data = "hello I am FirstOrderBroadCast"
        val bundle: Bundle? = Bundle()
        bundle?.putString("first","from FirstOrderBroadCast")
        setResult(code, data, bundle)
    }
}