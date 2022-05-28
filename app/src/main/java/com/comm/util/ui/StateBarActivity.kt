package com.comm.util.ui

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.View.*
import android.view.WindowInsetsController
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsCompat
import com.comm.util.R

class StateBarActivity : AppCompatActivity() {

    private var controller: WindowInsetsController? = null

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_state_bar)
//        window.decorView.systemUiVisibility =SYSTEM_UI_FLAG_FULLSCREEN or SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//        window.decorView.systemUiVisibility =SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or SYSTEM_UI_FLAG_LAYOUT_STABLE

        /** Lean back **/
//        window.decorView.systemUiVisibility =
//            SYSTEM_UI_FLAG_FULLSCREEN or SYSTEM_UI_FLAG_HIDE_NAVIGATION


        /** Immersive **/
//        window.decorView.systemUiVisibility =
//            SYSTEM_UI_FLAG_FULLSCREEN or SYSTEM_UI_FLAG_HIDE_NAVIGATION or  SYSTEM_UI_FLAG_IMMERSIVE


        /** Sticky immersive **/
//        window.decorView.systemUiVisibility =
//            SYSTEM_UI_FLAG_FULLSCREEN or SYSTEM_UI_FLAG_HIDE_NAVIGATION or  SYSTEM_UI_FLAG_IMMERSIVE_STICKY

//        window.statusBarColor = Color.BLUE

//        window.statusBarColor = Color.WHITE
//        window.decorView.systemUiVisibility =SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
//        window.decorView.systemUiVisibility =SYSTEM_UI_FLAG_LOW_PROFILE

//        controller = window.insetsController

    }


    var isWhite = false
    fun change_statebar_element_color(view: View) {
        if (isWhite) {
            window.decorView.systemUiVisibility = SYSTEM_UI_FLAG_LAYOUT_STABLE //statebar 元素显示白色
            isWhite = false
        } else {
            window.decorView.systemUiVisibility =
                SYSTEM_UI_FLAG_LIGHT_STATUS_BAR  //显示黑色
            isWhite = true
        }
    }


    fun change_statebar_color(view: View) {
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        window.clearFlags(
//            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
//        );
        window.statusBarColor = ContextCompat.getColor(
            this,
            android.R.color.holo_blue_dark
        )
        window.decorView.systemUiVisibility =
            SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }

    var isHide = true

    @RequiresApi(Build.VERSION_CODES.R)
    fun hide_state_bar(view: android.view.View) {
        if (isHide) {
            controller?.hide(WindowInsetsCompat.Type.statusBars())
        } else {
            controller?.show(WindowInsetsCompat.Type.statusBars())
        }
        isHide = !isHide
    }

    @RequiresApi(Build.VERSION_CODES.R)
    fun AppearanceLightStatusBars(view: android.view.View) {
        if (isWhite) {
            controller?.setSystemBarsAppearance(
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
        } else {
            controller?.setSystemBarsAppearance(
                0,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
        }
        isWhite = !isWhite
    }
}