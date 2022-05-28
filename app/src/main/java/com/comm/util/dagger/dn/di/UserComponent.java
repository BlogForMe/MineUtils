/**
 * ClassName:      UserComponent
 * Description:    Description
 * Author:         zh
 * CreateDate:     2021/9/25 4:41 PM
 * UpdateUser:     zh
 * UpdateDate:     2021/9/25 4:41 PM
 * UpdateRemark:   Modify the description
 */

package com.comm.util.dagger.dn.di;

import com.comm.util.dagger.dn.DaggerUserActivity;
import dagger.Component;

@UserScope
@Component(modules = {UserModule.class})
public interface UserComponent {
    void inject(DaggerUserActivity daggerUserActivity);
}
