package com.comm.util.dagger.component;

import android.content.SharedPreferences;
import com.comm.util.MyApplication;
import com.comm.util.dagger.AppModule;
import dagger.Component;

/**
 * Created by jon on 1/28/18.
 */

@Component(modules = AppModule.class)
public interface AppComponent {
    SharedPreferences sharedPreferences();

    MyApplication myApplication();
}
