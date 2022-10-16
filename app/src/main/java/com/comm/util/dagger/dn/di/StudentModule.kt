/**
 * ClassName:      StudentModule
 * Description:    Description
 * Author:         zh
 * CreateDate:     2021/9/25 7:08 PM
 * UpdateUser:     zh
 * UpdateDate:     2021/9/25 7:08 PM
 * UpdateRemark:   Modify the description
 */
package com.comm.util.dagger.dn.di

import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class StudentModule {

    @Provides
    fun provideStudent1(): Student {
        return Student()
    }

    @Named("student1") //  @Named 是Qualifier的实现
    @Provides
    fun provideStudent(): Student {
        return Student()
    }

    @Named("student2")
    @Provides
    fun provideStudent2(): Student {
        return Student("John")
    }
}