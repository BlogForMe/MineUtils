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

import com.comm.util.dagger.dn.DaggerUserActivity;
import retrofit2.Retrofit;

// 2. 当作IOC容器,把对象注入到目标类中
@MyScope // 设置作用域和ApplicationComponent组件的生命周期一致，NetModule设置@Singleton 那么ApplicationComponent也必须设置
//@Component(modules = {NetModule2.class}) //模块装载到组件上去
public interface ApplicationComponent2 {

    void inject(DaggerUserActivity daggerActivity);//指定目标类 //dependencies = {ApplicationComponent1.class}


    Retrofit retrofit();
    ApiService apiService();

    //StudentComponent.Factory studentComponent();
}
