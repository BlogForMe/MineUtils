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

import com.comm.util.dagger.dn.DaggerActivity;
import dagger.Component;

// 2. 当作IOC容器,把对象注入到目标类中
// 设置作用域和ApplicationComponent组件的生命周期一致
@Component(modules = {NetModule.class}) //模块装载到组件上去
public interface ApplicationComponent {
    void inject(DaggerActivity daggerActivity);//指定目标类
}
