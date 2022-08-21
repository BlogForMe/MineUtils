package com.comm.util.dagger.xiangxue

import dagger.Module
import dagger.Provides

/**
 *
 * ClassName:      HttpModule
 * Description:    Description
 * Author:         zh
 * CreateDate:     2022/8/7 9:58 PM
 * UpdateUser:     zh
 * UpdateDate:     2022/8/7 9:58 PM
 * UpdateRemark:   Modify the description
 */

@Module
class HttpModule {

    @Provides
    fun provideHttpObject(): HttpObject {
        return HttpObject()
    }
}