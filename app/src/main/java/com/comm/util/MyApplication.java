package com.comm.util;

import com.alibaba.android.arouter.launcher.ARouter;

import android.app.Application;
import android.os.Environment;
import com.android.util.AppUtil;
import com.android.util.ToastUtil;
import com.comm.util.dagger.dn.di.ApplicationComponent2;
import com.comm.util.dagger.dn.di.DaggerApplicationComponent2;
import timber.log.Timber;

/**
 * Created by jon on 12/9/17.
 */

public class MyApplication extends Application {
    static ApplicationComponent2 applicationComponent = DaggerApplicationComponent2.create();
    private static MyApplication app;

    public static ApplicationComponent2 getApplicationComponent2() {
        return applicationComponent;
    }

    //Dagger
    public static MyApplication getInstance() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        AppUtil.init(this);
        ToastUtil.register(this);
        Timber.plant(new Timber.DebugTree());

        ARouter.openLog();     // Print log
        ARouter.openDebug();   // Turn on debugging mode (If you are running in
        //            InstantRun mode, you must turn on debug mode! Online version needs to be
        //            closed, otherwise there is a security risk)
        ARouter.init(this);
        //        initGreenDao();
    }

    //static ApplicationComponent2 applicationComponent2 = DaggerApplicationComponent2.create();
    //
    //public static ApplicationComponent2 getApplicationComponent2() {
    //    return applicationComponent2;
    //}

    //
    //    private void initGreenDao() {
    ////        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "dblearn.db");
    //        MigrationHelper.DEBUG = true;
    //        MySQLiteOpenHelper helper = new MySQLiteOpenHelper(this,"dblearn.db",null);
    //        SQLiteDatabase db = helper.getWritableDatabase();
    //        DaoMaster daoMaster = new DaoMaster(db);
    //        daoSession = daoMaster.newSession();
    //    }

    //    public DaoSession getDaoSession() {
    //        return daoSession;
    //    }

    private void initXlog() {
        System.loadLibrary("c++_shared");
        System.loadLibrary("marsxlog");

        final String SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath();
        final String logPath = SDCARD + "/marssample/log";

        // this is necessary, or may crash for SIGBUS
        final String cachePath = this.getFilesDir() + "/xlog";

        //init xlog
        //if (BuildConfig.DEBUG) {
        //    Xlog.appenderOpen(Xlog.LEVEL_DEBUG, Xlog.AppednerModeAsync, cachePath, logPath,
        //        "MarsSample", 10, "");
        //    Xlog.setConsoleLogOpen(true);
        //
        //} else {
        //    Xlog.appenderOpen(Xlog.LEVEL_INFO, Xlog.AppednerModeAsync, cachePath, logPath,
        //        "MarsSample", 10, "");
        //    Xlog.setConsoleLogOpen(false);
        //}
        //
        //Log.setLogImp(new Xlog());

    }

    private void inject() {
        //        AppComponent appComponent = DaggerAppComponent.builder()
        //                .appModule(new AppModule(this))
        //                .build();
        //        ComponentHolder.setAppComponent(appComponent);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        //Log.appenderClose();
    }
}
