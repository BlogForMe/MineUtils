package com.comm.util.component.activityresult

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.comm.util.R
import com.comm.util.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_ok.*
import timber.log.Timber

/**
 * Created by A on 2018/3/20.
 */

class ResultOkFragment : BaseFragment() {


    companion object {
        fun newInstance(code: String) = ResultOkFragment().apply {
            arguments = Bundle().apply {
                putString(PARAMS_01, code)
            }
        }
    }

    override fun initView(rootView: View) {

    }

    private val requestCode: Int = 2000
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val intent = Intent(context, SecondResultActivity::class.java)

        bt_fragment.setOnClickListener {
            startActivityForResult(intent, requestCode)
            Timber.d("startActivityForResult $requestCode")
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Timber.d("onActivityResult resultCode $requestCode resultCode $resultCode  data ${data?.getStringExtra(SecondResultActivity.sData)}")
    }


    override fun setLayoutId(): Int {
        return R.layout.fragment_ok
    }


}
