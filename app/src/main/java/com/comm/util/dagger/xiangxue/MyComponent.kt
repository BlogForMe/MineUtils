package com.comm.util.dagger.xiangxue

import dagger.Component

/**
 *
 * ClassName:      MyComponent
 * Description:    Description
 * Author:         zh
 * CreateDate:     2022/8/7 10:01 PM
 * UpdateUser:     zh
 * UpdateDate:     2022/8/7 10:01 PM
 * UpdateRemark:   Modify the description
 */

@Component(modules = [HttpModule::class, DataBaseModule::class])
interface MyComponent {
    //注入点
    fun inject(activity: DaggerxxActivity)
}