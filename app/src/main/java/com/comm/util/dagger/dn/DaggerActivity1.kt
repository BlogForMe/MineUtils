package com.comm.util.dagger.dn

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.comm.util.R
import com.comm.util.dagger.dn.di.ApiService
import com.comm.util.dagger.dn.di.DaggerApplicationComponent
import com.comm.util.dagger.dn.di.User
import retrofit2.Retrofit
import javax.inject.Inject

class DaggerActivity1 : AppCompatActivity() {
    var TAG = "DaggerActivity"

    //3.设置Inject注解

    @Inject
    lateinit var user: User

    @Inject
    lateinit var user2: User

    @Inject
    lateinit var retrofit: Retrofit

    @Inject
    lateinit var apiService: ApiService

    @Inject
    lateinit var apiService2: ApiService

    //@Inject
    //ApiService apiService2;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dagger2)
        //4.执行注入动作
        DaggerApplicationComponent.create()
            .inject(this) //DaggerApplicationComponent相当于DaggerApplication的实现类

//        Log.i(TAG, "user: $user")
//        Log.i(TAG, "user2: $user2")
//        Log.i(TAG, "retrofit: $retrofit")
        Log.i(TAG, "apiService: $apiService")
        Log.i(TAG, "apiService2: " +apiService2);
        startActivity(Intent(this, DaggerSecondActivity1::class.java))
    }
}