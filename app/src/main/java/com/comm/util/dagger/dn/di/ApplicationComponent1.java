/**
 * ClassName:      ApplicationComponent
 * Description:    Description
 * Author:         zh
 * CreateDate:     2021/8/29 5:17 PM
 * UpdateUser:     zh
 * UpdateDate:     2021/8/29 5:17 PM
 * UpdateRemark:   Modify the description
 */

package com.comm.util.dagger.dn.di;

import javax.inject.Singleton;

import com.comm.util.dagger.dn.DaggerActivity1;
import com.comm.util.dagger.dn.DaggerSecondActivity;
import dagger.Component;

// 2. 当作IOC容器,把对象注入到目标类中
@Singleton // 设置作用域和ApplicationComponent组件的生命周期一致，NetModule设置@Singleton 那么ApplicationComponent也必须设置
@Component(modules = {NetModule1.class}) //模块装载到组件上去
public interface ApplicationComponent1 {

    void inject(DaggerActivity1 daggerActivity);//指定目标类 //dependencies = {ApplicationComponent1.class}

    void inject(
        DaggerSecondActivity secondActivity);   //dependencies = {ApplicationComponent1.class} 所以这里注释掉
}
