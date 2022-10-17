package com.comm.util.dagger.dn2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.comm.util.R
import com.comm.util.dagger.dn2.di.DaggerApplicationComponent6
import com.comm.util.dagger.dn2.di.User6
import javax.inject.Inject

class MainActivity6 : AppCompatActivity() {
    var TAG = javaClass.simpleName

    @Inject
    lateinit var user: User6
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        DaggerApplicationComponent6.create().inject(this)
        Log.i(TAG, "onCreate: user= $user")
    }
}