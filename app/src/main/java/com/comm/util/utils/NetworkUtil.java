package com.comm.util.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.android.util.AppUtil;

public class NetworkUtil {
    public static boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager)
                AppUtil.getApp().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }


}
