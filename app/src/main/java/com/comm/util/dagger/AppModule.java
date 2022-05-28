package com.comm.util.dagger;

import android.content.Context;
import android.content.SharedPreferences;
import com.comm.util.MyApplication;
import dagger.Module;
import dagger.Provides;

/**
 * Created by jon on 1/28/18.
 */

@Module
public class AppModule {
    private final MyApplication application;

    public AppModule(MyApplication application) {
        this.application = application;
    }

    //提供全局的sp对象
    @Provides
    SharedPreferences provideSharedPreferences() {
        return application.getSharedPreferences("spfile", Context.MODE_PRIVATE);
    }

    //提供全局的Application对象
    @Provides
    MyApplication provideApplication() {
        return application;
    }


}
