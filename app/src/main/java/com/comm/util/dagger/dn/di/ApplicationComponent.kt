/**
 * ClassName:      ApplicationComponent
 * Description:    Description
 * Author:         zh
 * CreateDate:     2021/8/29 5:17 PM
 * UpdateUser:     zh
 * UpdateDate:     2021/8/29 5:17 PM
 * UpdateRemark:   Modify the description
 */
package com.comm.util.dagger.dn.di

import com.comm.util.dagger.dn.DaggerActivity1
import dagger.Component
import javax.inject.Singleton

// 2. Component可以当作IOC容器,然后把对象注入到目标类中
// 设置作用域和ApplicationComponent组件的生命周期一致
@Singleton
@Component(modules = [NetModule::class]) //模块装载到组件上去
interface ApplicationComponent {
    fun inject(daggerActivity: DaggerActivity1) //指定DaggerActivity作为要注入的目标类
}