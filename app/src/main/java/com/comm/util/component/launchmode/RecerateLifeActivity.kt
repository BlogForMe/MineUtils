package com.comm.util.component.launchmode

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.comm.util.R
import timber.log.Timber

class RecerateLifeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recerate_life)
        Timber.i("onCreate()")
    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Timber.i("onRestoreInstanceState()")
    }

    fun reCreate(view: View) {
        recreate()
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Timber.i("onSaveInstanceState()")
    }

    override fun onRestart() {
        super.onRestart()
        Timber.i("onRestart()")
    }

    override fun onStart() {
        super.onStart()
        Timber.i("onStart()")
    }

    override fun onResume() {
        super.onResume()
        Timber.i("onResume()")
    }

    override fun onPause() {
        super.onPause()
        Timber.i("onPause()")
    }

    override fun onStop() {
        super.onStop()
        Timber.i("onStop()")
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Timber.i(" onNewIntent(Intent intent)")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("onDestroy()")
    }


}
