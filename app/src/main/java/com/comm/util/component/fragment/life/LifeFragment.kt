package com.comm.util.component.fragment.life

import android.os.Bundle
import com.comm.util.R
import com.comm.util.base.BaseFragment

/**
 * Created by A on 2018/3/20.
 */

class LifeFragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }


    override fun setLayoutId(): Int {
        return R.layout.fragment_life
    }

}
