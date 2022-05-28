package com.comm.util.framework.dispatch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.comm.util.R
import com.comm.util.framework.dispatch.widget.BaseViewPagerAdapter
import com.comm.util.framework.dispatch.widget.ListViewFragment
import kotlinx.android.synthetic.main.activity_view_pager_list.*

class ViewPagerListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager_list)

        val mFragments = ArrayList<Fragment>()
        for (i in 0..4) {
//            mFragments.add(ItemRecyclerFragment.newInstance(1))
            mFragments.add(ListViewFragment.newInstance(i.toString()))
        }
        viewpager.adapter = BaseViewPagerAdapter(supportFragmentManager, mFragments)
    }
}