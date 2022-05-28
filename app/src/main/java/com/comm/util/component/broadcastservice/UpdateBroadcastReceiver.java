package com.comm.util.component.broadcastservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import timber.log.Timber;

/**
 * Created by jon on 12/17/17.
 */

public class UpdateBroadcastReceiver extends BroadcastReceiver {
    public final static String ACTION_UPDATE = "android.intent.action.UpdateBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Timber.d("UpdateBroadcastReceiver 输出");
//        ToastUtil.showToast(context, "UpdateBroadcastReceiver");
    }
}
