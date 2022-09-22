package com.comm.util.component.launchmode

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.comm.util.base.BaseActivity
import com.comm.util.databinding.ActivitySecondBinding
import kotlinx.android.synthetic.main.activity_second.*
import timber.log.Timber

//@Route(path = ARouterManager.ACTIVITY_SECOND)
class ActivityB : BaseActivity() {

    val binding by lazy { ActivitySecondBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bt_go_third.setOnClickListener {
            startActivity(Intent(this, ThirdActivity::class.java))
        }
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

