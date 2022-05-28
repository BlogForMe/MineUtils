/**
 * ClassName:      MyScope
 * Description:    Description
 * Author:         zh
 * CreateDate:     2021/8/29 11:06 PM
 * UpdateUser:     zh
 * UpdateDate:     2021/8/29 11:06 PM
 * UpdateRemark:   Modify the description
 */
package com.comm.util.dagger.dn.di;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

// 自定义作用域

@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface UserScope {
}
