/**
 * ClassName:      Application3
 * Description:    Description
 * Author:         zh
 * CreateDate:     2021/9/25 7:40 PM
 * UpdateUser:     zh
 * UpdateDate:     2021/9/25 7:40 PM
 * UpdateRemark:   Modify the description
 */
package com.comm.util.dagger.dn2.di

import com.comm.util.dagger.dn2.MainActivity6
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule6::class])
interface ApplicationComponent6 {
    fun inject(mainActivity: MainActivity6)
}