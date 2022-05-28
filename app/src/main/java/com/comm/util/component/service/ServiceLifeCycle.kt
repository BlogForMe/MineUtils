package com.comm.util.component.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import timber.log.Timber

/**
 * @author : John
 * @date : 2018/7/12
 */
class ServiceLifeCycle : Service() {
    override fun onCreate() {
        super.onCreate()
        Timber.d(" onCreate()")
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Timber.d(" onStartCommand() flags $flags  startId $startId")
        val param = intent.getStringExtra(CheckServiceActivity.PARRAM)
//        stopSelf(2)
        Timber.d("param    $param")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Timber.d("onLowMemory()")
    }

    override fun onUnbind(intent: Intent): Boolean {
        Timber.d("onUnbind(intent)()")
        return true
    }

    override fun onRebind(intent: Intent) {
        super.onRebind(intent)
        Timber.d("onRebind(intent)()")
    }

    inner class LocalBinder : Binder() {
        val service: ServiceLifeCycle
            get() = this@ServiceLifeCycle
    }

    private val mBinder: IBinder = LocalBinder()
    fun stopMySelf() {
        Timber.d("stopMySelf()")
//        stopSelf(2)
    }

    override fun onBind(intent: Intent): IBinder {
        Timber.d("onBind(intent)()")
        return mBinder
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d(" onDestroy()")
    }
}