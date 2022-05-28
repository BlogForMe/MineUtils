package com.comm.util.dagger.util;

import com.comm.util.dagger.component.AppComponent;

/**
 * Created by jon on 1/28/18.
 */

public class ComponentHolder {
    private  static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    public static void setAppComponent(AppComponent appComponent) {
        ComponentHolder.appComponent = appComponent;
    }
}
