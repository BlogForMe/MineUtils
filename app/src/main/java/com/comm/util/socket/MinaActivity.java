package com.comm.util.socket;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.HandlerThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.comm.util.R;
import com.comm.util.socket.mina.ConnectionManager;
import com.comm.util.socket.mina.SessionManager;
import timber.log.Timber;

public class MinaActivity extends AppCompatActivity {


    private final MessageBroadcastReceiver receiver = new MessageBroadcastReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mina);
        registerBroadCast();

        findViewById(R.id.bt_connect).setOnClickListener(v -> {
            Timber.i("启动 QMinaService");
            Intent intent = new Intent(this, BleDataService.class);
            startService(intent);
        });
        findViewById(R.id.bt_send).setOnClickListener(v -> {
            SessionManager.getInstance().writeToServer("JYT201910100000129");
        });

//        findViewById(R.id.bt_onlooperprepared).setOnClickListener(v -> {
//            IoThread ioThread = new IoThread("JFAJZ");
//            ioThread.start();
//        });
    }

    private void registerBroadCast() {
        IntentFilter filter = new IntentFilter(ConnectionManager.BROADCAST_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, filter);
    }

    private void unRegisterBroadCast() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, BleDataService.class));
        unRegisterBroadCast();
    }

    private class MessageBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra("message");
            Timber.i("message   "+message);
//            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }

    private class IoThread extends HandlerThread {

        public IoThread(String name) {
            super(name);
        }

        @Override
        protected void onLooperPrepared() {
            super.onLooperPrepared();
            Timber.i("onLooperPrepared  ");
        }
    }
}
