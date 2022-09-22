package com.comm.util.ui.tab

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.comm.util.R
import com.comm.util.base.BaseActivity
import kotlinx.android.synthetic.main.activity_tab_bottom.*
import timber.log.Timber

/**
 * Fragment重叠问题
 *
 * 代码 参考
 * https://blog.csdn.net/yuzhiqiang_1993/article/details/75014591
 */
class TabBottomActivity : BaseActivity() {


    private val HOME_FRAGMENT_KEY = "homeFragment"
    private val DASHBOARD_FRAGMENT_KEY = "DashboardFragment"
    private val NOTICE_FRAGMENT_KEY = "NoticeFragment"

    private var personFragment: PersonFragment? = null
    private var healthFragment: HealthFragment? = null
    private var homeFragment: HomeFragment? = null
    val fragmentList = ArrayList<Fragment>()

    private fun initFragment() {
        if (homeFragment == null) {
            homeFragment = HomeFragment()
            addFragment(homeFragment!!)
            showFragment(homeFragment)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
        方案2
        if (savedInstanceState != null) {
              *//*获取保存的fragment  没有的话返回null*//*
            homeFragment = supportFragmentManager.getFragment(savedInstanceState, HOME_FRAGMENT_KEY) as HomeFragment?
            healthFragment = supportFragmentManager.getFragment(savedInstanceState, DASHBOARD_FRAGMENT_KEY) as HealthFragment?
            personFragment = supportFragmentManager.getFragment(savedInstanceState, NOTICE_FRAGMENT_KEY) as PersonFragment?
            addToList(homeFragment)
            addToList(healthFragment)
            addToList(personFragment)

            Timber.tag(TAG).d("savedInstanceState() homeFragment $homeFragment")

        } else {
//            Timber.tag(TAG).d("onCreate() homeFragment $homeFragment")
            initFragment()
        }*/

        initFragment()
        Timber.tag(TAG).d("initFragment() homeFragment $homeFragment")

        bnv.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    Timber.d("home")
                    if (homeFragment == null) {
                        homeFragment = HomeFragment()
                        addFragment(homeFragment!!)
                    }
                    showFragment(homeFragment)
                }
                R.id.navigation_health -> {
                    Timber.d("navigation_health")
                    if (healthFragment == null) {
                        healthFragment = HealthFragment()
                        addFragment(healthFragment!!)
                    }
                    showFragment(healthFragment)
                }
                R.id.navigation_person -> {
                    if (personFragment == null) {
                        personFragment = PersonFragment()
                        addFragment(personFragment!!)
                    }
                    showFragment(personFragment)
                    Timber.d(",person")
                }

            }
            return@setOnNavigationItemSelectedListener true
        }
    }

//    override fun setLayoutId(): Int {
//        return R.layout.activity_tab_bottom
//    }


    private fun showFragment(fragment: Fragment?) {
        /*先隐藏其他fragment*/
        for (frag in fragmentList) {
            if (frag != fragment) {
                supportFragmentManager.beginTransaction().hide(frag).commit()
            }
        }
        fragment?.let {
            supportFragmentManager.beginTransaction().show(it).commit()
        }

    }

    private fun addFragment(fragment: Fragment) {
        /*判断该fragment是否已经被添加过  如果没有被添加  则添加*/
        if (!fragment.isAdded) {
            supportFragmentManager.beginTransaction().add(R.id.fl_content, fragment).commit()
            /*添加到 fragmentList*/
            fragmentList.add(fragment)
        }
    }

    /*
    方法 1
    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
    }*/

    private fun addToList(fragment: Fragment?) {
        if (fragment != null) {
            fragmentList.add(fragment)
        }
        Timber.d("fragmentList数量" + fragmentList.size)
    }


    /*


     override fun onSaveInstanceState(outState: Bundle) {
          super.onSaveInstanceState(outState)
          *//*fragment不为空时 保存*//*
        if (homeFragment != null) {
            supportFragmentManager.putFragment(outState!!, HOME_FRAGMENT_KEY, homeFragment!!)
        }
        Timber.tag(TAG).d("onSaveInstanceState() homeFragment $homeFragment")
        if (healthFragment != null) {
            supportFragmentManager.putFragment(outState!!, DASHBOARD_FRAGMENT_KEY, healthFragment!!)
        }
        if (personFragment != null) {
            supportFragmentManager.putFragment(outState!!, NOTICE_FRAGMENT_KEY, personFragment!!)
        }
    }*/

}