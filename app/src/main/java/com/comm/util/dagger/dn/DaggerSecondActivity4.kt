package com.comm.util.dagger.dn

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.comm.util.R


class DaggerSecondActivity4 : AppCompatActivity() {
    var TAG = javaClass.simpleName

//    @Inject
//    lateinit var student: Student

//    @Inject
//    lateinit var aInterface: AInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second2)

//        DaggerApplicationComponent4.create().studentComponent().create().inject(this)
//        Log.i(TAG, "student: $student ")

//        Log.i(TAG, "aInterface: $aInterface ")

    }
}