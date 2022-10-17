/**
 * ClassName:      AppModule
 * Description:    Description
 * Author:         zh
 * CreateDate:     2021/9/25 8:43 PM
 * UpdateUser:     zh
 * UpdateDate:     2021/9/25 8:43 PM
 * UpdateRemark:   Modify the description
 */
package com.comm.util.dagger.dn2.di

import dagger.Module
import dagger.Provides

@Module
class AppModule6 {
    @Provides
    fun provideUser(): User6 {
        return User6()
    }
}