package com.comm.util.dagger.dn

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.comm.util.R
import com.comm.util.dagger.dn.di.AInterface
import com.comm.util.dagger.dn.di.DaggerApplicationComponent3
import com.comm.util.dagger.dn.di.Student
import javax.inject.Inject


class DaggerSecondActivity4 : AppCompatActivity() {
    var TAG = javaClass.simpleName

    @Inject
    lateinit var student: Student

    @Inject
    lateinit var aInterface: AInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second2)

        DaggerApplicationComponent3.create().studentComponent().create().inject(this)
        Log.i(TAG, "student: $student ")
        Log.i(TAG, "aInterface: $aInterface ")
    }
}