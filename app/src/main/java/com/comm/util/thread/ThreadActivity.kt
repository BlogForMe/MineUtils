package com.comm.util.thread

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import com.comm.util.R
import kotlinx.android.synthetic.main.activity_thread.*
import timber.log.Timber

class ThreadActivity : AppCompatActivity() {
    var handler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread)


        btA.setOnClickListener {
            val threadA = ThreadA()
            val threadB = ThreadB()
            Thread(threadA).start()
            if (threadA.getHandler() == null) {
                Thread.sleep(1000)
                handler = threadA.getHandler()
            }
            Thread(threadB).start()
        }
    }


    class ThreadA : Runnable {
        var mHandler: Handler? = null


        fun getHandler(): Handler? {
            return mHandler
        }

        override fun run() {
            Looper.prepare()
            mHandler = object : Handler(Looper.myLooper()!!) {
                override fun handleMessage(msg: Message) {
                    super.handleMessage(msg)
                    Timber.d("线程A: 线程B发过来消息了-- ${msg.obj} ")
                }
            }
            Looper.loop()
        }
    }


    inner class ThreadB : Runnable {
        override fun run() {
            val message = Message.obtain()
            message.what = 1
            message.obj = "线程B 发送消息" + System.currentTimeMillis()
            handler?.sendMessage(message)
        }
    }
}