package com.comm.util.dagger.dn

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.comm.util.MyApplication
import com.comm.util.R
import com.comm.util.dagger.dn.di.DaggerUserComponent1
import com.comm.util.dagger.dn.di.User1
import com.comm.util.dagger.dn.di.UserComponent1
import javax.inject.Inject

class DaggerUserActivity : AppCompatActivity() {
    var TAG = "DaggerUserActivity"

    @Inject
    lateinit var user: User1

    @Inject
    lateinit var user2: User1

//    @Inject
//    lateinit var apiService3: ApiService

    var userComponent: UserComponent1? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dagger_user)
        userComponent = DaggerUserComponent1.builder().applicationComponent1(
            MyApplication.getApplicationComponent1()
        ).build()
        userComponent?.inject(this)
        Log.i(TAG, "user: $user")
        Log.i(TAG, "user2: $user2")

//        Log.i(TAG, "apiService3: $apiService3")

        startActivity(Intent(this, DaggerSecondActivity::class.java))
    }
}