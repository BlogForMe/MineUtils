package com.comm.util.component.fragment.life

import android.os.Bundle
import android.view.View
import com.comm.util.R
import com.comm.util.base.BaseActivity
import timber.log.Timber

/**
 * 横竖屏切换 查看Fragment生命周期
 */

class LifeActivity : BaseActivity() {
    val tag = "life_fragment"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFragment()
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_fragment
    }

    private fun initFragment() {
        var lifeFragment = supportFragmentManager.findFragmentById(R.id.fl_content)
        if (lifeFragment == null) {
            lifeFragment = LifeFragment()
            supportFragmentManager.beginTransaction().add(R.id.fl_content, lifeFragment).commit()
        }
        Timber.d("LifeFragment $lifeFragment")
    }

    fun btTimber(v: View?) {
        Timber.d("测试")
    }
}