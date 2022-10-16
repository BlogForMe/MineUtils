package com.comm.util.dagger.dn

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.comm.util.MyApplication
import com.comm.util.R
import com.comm.util.dagger.dn.di.ApiService
import com.comm.util.dagger.dn.di.DaggerUserComponent2
import com.comm.util.dagger.dn.di.User2
import com.comm.util.dagger.dn.di.UserComponent2
import javax.inject.Inject

class DaggerUserActivity2 : AppCompatActivity() {
    var TAG = javaClass.simpleName

    @Inject
    lateinit var user1: User2

    @Inject
    lateinit var user2: User2

    @Inject
    lateinit var apiService1: ApiService

    @Inject
    lateinit var apiService2: ApiService

    var userComponent: UserComponent2? = null

    @Inject
    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dagger_user)
        userComponent = DaggerUserComponent2.builder().applicationComponent2(
            MyApplication.applicationComponent2
        ).build()
        userComponent?.inject(this)
        Log.i(TAG, "user1: $user1")
        Log.i(TAG, "user2: $user2")

        Log.i(TAG, "apiService1: $apiService1")
        Log.i(TAG, "apiService2: $apiService2")

        Log.i(TAG, "context: $context")
        startActivity(Intent(this, DaggerSecondActivity2::class.java))


    }
}