package com.comm.util.ui

import android.annotation.TargetApi
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat.setOnApplyWindowInsetsListener
import androidx.core.view.WindowInsetsCompat
import com.comm.util.databinding.ActivityDisplayCountBinding

//            原文链接：https://blog.csdn.net/yingaizhu/article/details/112894902

// 刘海屏适配
class DisplayCountActivity : AppCompatActivity() {
    private lateinit var biding: ActivityDisplayCountBinding
    val TAG = "DisplayCountActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        biding = ActivityDisplayCountBinding.inflate(layoutInflater)
        setContentView(biding.root)
        biding.getsaftewidth.setOnClickListener {
            getNotchParams()
//            updateTopMarginDisplayCutout(biding.titleView)
        }
//        updateTopMarginDisplayCutout(biding.titleView)
    }


    /**
     * 获得刘海区域信息
     */
    @TargetApi(28)
    fun getNotchParams() {
        val decorView: View = window.decorView
        if (decorView != null) {
            decorView.post(Runnable {
                val windowInsets: WindowInsets = decorView.rootWindowInsets
                if (windowInsets != null) {
                    // 当全屏顶部显示黑边时，getDisplayCutout()返回为null
                    val displayCutout = windowInsets.displayCutout
                    Log.e("TAG", "安全区域距离屏幕左边的距离 SafeInsetLeft:" + displayCutout!!.safeInsetLeft)
                    Log.e("TAG", "安全区域距离屏幕右部的距离 SafeInsetRight:" + displayCutout.safeInsetRight)
                    Log.e("TAG", "安全区域距离屏幕顶部的距离 SafeInsetTop:" + displayCutout.safeInsetTop)
                    Log.e("TAG", "安全区域距离屏幕底部的距离 SafeInsetBottom:" + displayCutout.safeInsetBottom)
                    // 获得刘海区域
                    val rects: List<Rect> = displayCutout.boundingRects
                    if (rects == null || rects.isEmpty()) {
                        Log.e("TAG", "不是刘海屏")
                    } else {
                        Log.e("TAG", "刘海屏数量:" + rects.size)
                        for (rect in rects) {
                            Log.e("TAG", "刘海屏区域：$rect")
                        }
                    }
                }
            })
        }
    }

    fun updateTopMarginDisplayCutout(
        titleMenuView: View,
        addOriginalMargin: Boolean = false
    ) {
        val attrib = window.attributes
        attrib.layoutInDisplayCutoutMode =
            WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS
        setOnApplyWindowInsetsListener(titleMenuView) { v: View, insets: WindowInsetsCompat ->
            val params = v.layoutParams as ViewGroup.MarginLayoutParams
            val insetTop = insets.systemWindowInsetTop
            val topMargin = if (addOriginalMargin) params.topMargin + insetTop else insetTop
            updateTopMargin(v, topMargin)
            /**
             *  Pass [topMargin] into [onDisplayCutoutChanged] for layout
             *  with more than 1 title_menu_view
             */
//            onDisplayCutoutChanged(topMargin)
            insets.consumeSystemWindowInsets()
        }
    }

    protected fun updateTopMargin(titleMenuView: View, topMargin: Int) {
        val params = titleMenuView.layoutParams as ViewGroup.MarginLayoutParams
        params.topMargin = topMargin
        titleMenuView.layoutParams = params
    }


}