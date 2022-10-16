package com.comm.util.dagger.dn

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.comm.util.MyApplication
import com.comm.util.R
import com.comm.util.dagger.dn.di.ApiService
import com.comm.util.dagger.dn.di.DaggerUserComponent2
import com.comm.util.dagger.dn.di.User2
import javax.inject.Inject

class DaggerSecondActivity2 : AppCompatActivity() {
    var TAG = javaClass.simpleName

    @Inject
    lateinit var user3: User2

    @Inject
    lateinit var apiService3: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second2)

         DaggerUserComponent2.builder().applicationComponent2(
            MyApplication.getApplicationComponent2()
        ).build().inject(this);
        //DaggerApplicationComponent相当于DaggerApplication的实现类
        //DaggerApplicationComponent1.create().inject(this);//DaggerApplicationComponent相当于DaggerApplication的实现类
        Log.i(TAG, "user3: $user3")
        Log.i(TAG, "apiService3: $apiService3");
    }
}