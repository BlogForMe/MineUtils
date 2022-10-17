package com.comm.util.dagger.dn

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.comm.util.R
import com.comm.util.dagger.dn.di.*
import javax.inject.Inject
import javax.inject.Named


class DaggerSecondActivity4 : AppCompatActivity() {
    var TAG = javaClass.simpleName

    @StudentQualifier1
    @Inject
    lateinit var StudentQualifier1: Student

    @StudentQualifier2
    @Inject
    lateinit var StudentQualifier2: Student

    @Inject
    lateinit var aInterface: AInterface

    @Named("student1")
    @Inject
    lateinit var student1: Student

    @Named("student2")
    @Inject
    lateinit var student2: Student



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second2)

        DaggerApplicationComponent3.create().studentComponent().create().inject(this)
        Log.i(TAG, "student1: $student1 ")
        Log.i(TAG, "student2: $student2 ")

        Log.i(TAG, "StudentQualifier1: $StudentQualifier1 ")
        Log.i(TAG, "StudentQualifier2: $StudentQualifier2 ")

        Log.i(TAG, "aInterface: $aInterface ")
    }
}