package com.comm.util.utils;

import java.io.File;

import android.os.Environment;
import timber.log.Timber;

/**
 * Created by Administrator on 2017/12/14 0014.
 */

public class StorageUtil {

    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }


    /**
     * 获取应用的缓存目录
     */
    public static String getCacheDirectory() {
        String downFile = Environment.getExternalStorageDirectory().getPath() + "/ApkFile";
        if (!new File(downFile).exists()) {
            new File(downFile).mkdirs();
        }
        if (downFile == null) {
            Timber.w("Can't define system cache directory! The app should be re-installed.");
        }
        return downFile;
    }

    public static String getRootDirectory() {
        String downFile = Environment.getExternalStorageDirectory().getPath();
        if (downFile == null) {
            Timber.w("Can't define system cache directory! The app should be re-installed.");
        }
        return downFile;
    }


}
