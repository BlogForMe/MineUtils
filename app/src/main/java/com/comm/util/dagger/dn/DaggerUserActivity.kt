package com.comm.util.dagger.dn

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.comm.util.R

class DaggerUserActivity : AppCompatActivity() {
    var TAG = "DaggerUserActivity"

//    @Inject
//    lateinit var user: User1
//
//    @Inject
//    lateinit var user2: User1

//    @Inject
//    lateinit var apiService3: ApiService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dagger_user)

//        Log.i(TAG, "apiService3: $apiService3")

        startActivity(Intent(this, DaggerSecondActivity1::class.java))
    }
}