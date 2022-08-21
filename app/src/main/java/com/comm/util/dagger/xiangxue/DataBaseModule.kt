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
class DataBaseModule {

    @Provides
    fun provideDataBaseObject(): DatabaseObject {
        return DatabaseObject()
    }

//    @Provides
//    fun abc(): Any {
//        return Any()
//    }

}