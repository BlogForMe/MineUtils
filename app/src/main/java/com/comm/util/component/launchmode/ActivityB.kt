package com.comm.util.component.launchmode

import android.app.ActivityManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.comm.util.base.BaseActivity
import com.comm.util.databinding.ActivitySecondBinding
import timber.log.Timber

/**
 *              原文链接：https://blog.csdn.net/javazejian/article/details/52071885

 */
class ActivityB : BaseActivity() {

    val binding by lazy { ActivitySecondBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        bt_go_third.setOnClickListener {
//            startActivity(Intent(this, ThirdActivity::class.java))
//        }
        setContentView(binding.root)
        binding.btGoActivityd.setOnClickListener {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_LAUNCHER)
            val cn = ComponentName("com.john.kot", "com.john.kot.activity.ActivityD")
            intent.component = cn
            startActivity(intent)
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

