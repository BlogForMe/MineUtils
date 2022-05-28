package com.comm.util.component.broadcastservice

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class SecondOrderBroadCast : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.e("receive", "Mid")
        //获取上一个广播接收器结果
        val code = resultCode
        val data = resultData
        var bundleResult = getResultExtras(true).get("first")

        Log.e("receive", "获取到上一个广播接收器结果：code=  $code   data= $data  bundleResult $bundleResult")
    }
}