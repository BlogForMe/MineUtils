package com.comm.util

import android.app.Application
import android.os.Environment
import com.alibaba.android.arouter.launcher.ARouter
import com.android.util.AppUtil
import com.android.util.ToastUtil
import com.comm.util.dagger.dn.di.ApplicationComponent2
import com.comm.util.dagger.dn.di.DaggerApplicationComponent2
import com.comm.util.dagger.dn.di.NetModule2
import timber.log.Timber
import timber.log.Timber.DebugTree

/**
 * Created by jon on 12/9/17.
 */
class MyApplication : Application() {
    companion object {
        lateinit var applicationComponent2: ApplicationComponent2
    }

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent2.builder().netModule2(NetModule2(this)).build()
        applicationComponent2 = DaggerApplicationComponent2.create()

        AppUtil.init(this)
        ToastUtil.register(this)
        Timber.plant(DebugTree())
        ARouter.openLog() // Print log
        ARouter.openDebug() // Turn on debugging mode (If you are running in
        ARouter.init(this)
    }


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
    private fun initXlog() {
        System.loadLibrary("c++_shared")
        System.loadLibrary("marsxlog")
        val SDCARD = Environment.getExternalStorageDirectory().absolutePath
        val logPath = "$SDCARD/marssample/log"

        // this is necessary, or may crash for SIGBUS
        val cachePath = this.filesDir.toString() + "/xlog"

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

}