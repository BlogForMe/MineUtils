/**
 * ClassName:      StudentModule
 * Description:    Description
 * Author:         zh
 * CreateDate:     2021/9/25 7:08 PM
 * UpdateUser:     zh
 * UpdateDate:     2021/9/25 7:08 PM
 * UpdateRemark:   Modify the description
 */
package com.comm.util.dagger.dn.di;

import dagger.Module;

@Module
public class StudentModule {
    Student provideStudent(){
        return new Student();
    }
}
