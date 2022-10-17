package com.comm.util.dagger.dn2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.comm.util.R
import com.comm.util.dagger.dn2.di.DaggerApplicationComponent5
import com.comm.util.dagger.dn2.di.User5
import javax.inject.Inject

class MainActivity5 : AppCompatActivity() {
    var TAG = javaClass.simpleName

    @Inject
    lateinit var user: User5
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        DaggerApplicationComponent5.create().inject(this)
        Log.i(TAG, "onCreate: user= $user")
    }
}