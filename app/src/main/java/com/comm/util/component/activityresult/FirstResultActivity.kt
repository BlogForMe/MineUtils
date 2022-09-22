package com.comm.util.component.activityresult

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.comm.util.R
import com.comm.util.base.BaseActivity
import com.comm.util.component.activityresult.ResultOkFragment.Companion.newInstance
import timber.log.Timber

class FirstResultActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFragment()
    }


//    override fun setLayoutId(): Int {
//        return R.layout.activity_first_result
//    }

    val requestCode = 1000
    override fun initView() {
        super.initView()
        val intent = Intent(this, SecondResultActivity::class.java)
        findViewById<View>(R.id.bt_activity).setOnClickListener {
            startActivityForResult(intent, requestCode)
            Timber.i("startActivityForResult $requestCode")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Timber.d("onActivityResult resultCode $requestCode resultCode $resultCode  data  ${data?.getStringExtra(SecondResultActivity.sData)}")
    }

    private fun initFragment() {
        val ftn = supportFragmentManager.beginTransaction().replace(R.id.fl_content, newInstance("数据"))
        ftn.commit()
    }
}