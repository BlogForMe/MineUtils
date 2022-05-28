package com.comm.util.dagger.login;

import com.comm.util.dagger.DaggerPresenterActivity;
import dagger.Component;

/**
 * Created by A on 2018/1/24.
 */

@Component
public interface LoginModelComponet {
    void inject(DaggerPresenterActivity daggerActivity);
}
