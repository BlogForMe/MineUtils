/**
 * ClassName:      StudentComponent
 * Description:    Description
 * Author:         zh
 * CreateDate:     2021/9/25 6:57 PM
 * UpdateUser:     zh
 * UpdateDate:     2021/9/25 6:57 PM
 * UpdateRemark:   Modify the description
 */
package com.comm.util.dagger.dn.di;

import com.comm.util.dagger.dn.DaggerSecondActivity1;
import dagger.Subcomponent;

@Subcomponent(modules = StudentModule.class)
public interface StudentComponent {
    void inject(DaggerSecondActivity1 secondActivity);

    @Subcomponent.Factory
    interface Factory{
        StudentComponent create();
    }

}