package com.comm.util.component.launchmode

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.comm.util.ARouterManager
import com.comm.util.R
import com.comm.util.base.BaseActivity
import kotlinx.android.synthetic.main.activity_second.*
import timber.log.Timber

@Route(path = ARouterManager.ACTIVITY_SECOND)
class SecondActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bt_go_third.setOnClickListener {
            startActivity(Intent(this,ThirdActivity::class.java))
        }
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_second
    }

    /**
     * 检测某Activity是否在当前Task的栈顶
     */
    private fun isTopActivity(activityName: String): Boolean {
        val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val runningTaskInfos = manager.getRunningTasks(1)
        var cmpNameTemp: String? = null
        if (runningTaskInfos != null) {
            cmpNameTemp = runningTaskInfos[0].topActivity!!.className
            Timber.i("cmpNameTemp   $cmpNameTemp")
        }
        return if (cmpNameTemp == null) {
            false
        } else cmpNameTemp == activityName
    }
}