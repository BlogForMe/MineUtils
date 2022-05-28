package com.comm.util.component.fragment.stack

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.comm.util.R
import com.comm.util.base.BaseActivity
import com.comm.util.component.activityresult.SecondResultActivity
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_stack.*
import timber.log.Timber

/**
 * Fragment回退栈
 * Activity fragment消息传递和调用
 */
class StackActivity : BaseActivity() {
    private lateinit var stack1Fragment: Stack1Fragment
    private val tbLayout: TabLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val ft = supportFragmentManager.beginTransaction()
         stack1Fragment = Stack1Fragment()
        stack1Fragment.arguments = Bundle().apply {
            putString("param1","stack")
        }
        ft.replace(R.id.fl_content,stack1Fragment, "One")
        ft.commit()



        bt_t1.setOnClickListener {
//            val ft1 = supportFragmentManager.beginTransaction();
//            ft1.replace(R.id.fl_content,  Stack1Fragment(), "One");
//            ft1.addToBackStack(null);
//            ft1.commit();
            stack1Fragment.arguments = Bundle().apply {
                putString("param1","stack2")
            }
        }

        findViewById<View>(R.id.bt_t2).setOnClickListener { v: View? ->
            val ft2 = supportFragmentManager.beginTransaction()
            ft2.replace(R.id.fl_content, Stack2Fragment(), "Two")
            ft2.addToBackStack(null)
            ft2.commit()
        }
        findViewById<View>(R.id.bt_t3).setOnClickListener { v: View? ->
            val ft3 = supportFragmentManager.beginTransaction()
            ft3.replace(R.id.fl_content, Stack3Fragment(), "Three")
            ft3.addToBackStack(null)
            ft3.commit()
        }
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_stack
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            21 -> {
                val datas = data!!.getStringExtra(SecondResultActivity.sData)
                Timber.d(datas)
            }
        }
    }
}