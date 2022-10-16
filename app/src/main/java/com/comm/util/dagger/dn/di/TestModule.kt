/**
 * ClassName:      TestModule
 * Description:    Description
 * Author:         zh
 * CreateDate:     2021/9/25 7:19 PM
 * UpdateUser:     zh
 * UpdateDate:     2021/9/25 7:19 PM
 * UpdateRemark:   Modify the description
 */
package com.comm.util.dagger.dn.di;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class TestModule {

    @Provides
    static AInterfaceImpl01 provideAInterfaceImpl01(){
        return new AInterfaceImpl01();
    }

    @Binds
    abstract AInterface bindAInterface(AInterfaceImpl01 impl);
}
