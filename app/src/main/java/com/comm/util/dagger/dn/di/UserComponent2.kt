/**
 * ClassName:      UserComponent
 * Description:    Description
 * Author:         zh
 * CreateDate:     2021/9/25 4:41 PM
 * UpdateUser:     zh
 * UpdateDate:     2021/9/25 4:41 PM
 * UpdateRemark:   Modify the description
 */
package com.comm.util.dagger.dn.di

import com.comm.util.dagger.dn.DaggerSecondActivity2
import com.comm.util.dagger.dn.DaggerUserActivity2
import dagger.Component

@UserScope
@Component(modules = [UserModule2::class], dependencies = [ApplicationComponent2::class])
interface UserComponent2 {
    fun inject(activity: DaggerUserActivity2)
    fun inject(activity: DaggerSecondActivity2)
}