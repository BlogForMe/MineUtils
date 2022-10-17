package com.comm.util.dagger.dn.di;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * ClassName:      StudentQualifier1
 * Description:    Description
 * Author:         zh
 * CreateDate:     2022/10/16 10:26 PM
 * UpdateUser:     zh
 * UpdateRemark:   Modify the description
 */


@Qualifier
@Documented
@Retention(RUNTIME)
public @interface StudentQualifier1 {
}
