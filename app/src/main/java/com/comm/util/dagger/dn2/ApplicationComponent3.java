/**
 * ClassName:      Application3
 * Description:    Description
 * Author:         zh
 * CreateDate:     2021/9/25 7:40 PM
 * UpdateUser:     zh
 * UpdateDate:     2021/9/25 7:40 PM
 * UpdateRemark:   Modify the description
 */

package com.comm.util.dagger.dn2;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface ApplicationComponent3 {
    void inject(MainActivity3 mainActivity);
}
