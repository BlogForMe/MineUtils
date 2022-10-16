/**
 * ClassName:      TestModule
 * Description:    Description
 * Author:         zh
 * CreateDate:     2021/9/25 7:19 PM
 * UpdateUser:     zh
 * UpdateDate:     2021/9/25 7:19 PM
 * UpdateRemark:   Modify the description
 */
package com.comm.util.dagger.dn.di

import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class TestModule {
    @Binds
    abstract fun bindAInterface(impl: AInterfaceImpl02): AInterface // AInterfaceImpl01改成AInterfaceImpl02就能改注入


    companion object {
        @JvmStatic
        @Provides
        fun provideAInterfaceImpl01(): AInterfaceImpl01 {
            return AInterfaceImpl01()
        }

        @JvmStatic
        @Provides
        fun provideAInterfaceImpl02(): AInterfaceImpl02 {
            return AInterfaceImpl02()
        }
    }
}