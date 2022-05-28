package com.comm.util.socket;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.HandlerThread;
import android.os.IBinder;
import com.comm.util.socket.mina.ConnectionConfig;
import com.comm.util.socket.mina.ConnectionManager;
import com.comm.util.socket.mina.SessionManager;
import timber.log.Timber;

public class BleDataService extends Service  {

    private final IBinder mBinder = new LocalBinder();
    private final int MQ_PPORT = 6667;
    private ScheduledExecutorService executorService;
    private ConnectionThread connThread;

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        executorService = Executors.newSingleThreadScheduledExecutor();
        initSocket();
    }

    private void initSocket() {
        connThread = new ConnectionThread("mina", getApplicationContext());
        connThread.start();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Timber.i("onStartCommand");
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Timber.i("onDestroy()");
    }

    public class LocalBinder extends Binder {
        public BleDataService getService() {
            return BleDataService.this;
        }
    }

    /**
     * 用来调用ConnectionManager 完成与服务器的连接
     */
    class ConnectionThread extends HandlerThread {
        private final Context mContext;
        boolean isConnection;
        ConnectionManager mManager;

        public ConnectionThread(String name, Context context) {
            super(name);
            this.mContext = context;
        }

        /**
         * 开始连接我们的服务器
         */
        @Override
        protected void onLooperPrepared() {
            super.onLooperPrepared();
//            String mqHOST ="cs.casanubeserver.com";
            String mqHOST ="10.0.0.9";
            ConnectionConfig config = new ConnectionConfig.Builder(mContext)
                    .setIp(mqHOST)
                    .setPort(MQ_PPORT)
                    .setReadBufferSize(512)
                    .setConnectionTimeout(10000)
                    .builder();
            mManager = new ConnectionManager(config);
            Timber.i("onLooperPrepared  ");
            while (true) {
                isConnection = mManager.connect();
                Timber.i("onLooperPrepared  启动连接    " + isConnection);
                if (isConnection) {
                    Timber.i("连接成功");

                    executorService.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            String serialNum = "JYT201910100000129";
                            SessionManager.getInstance().writeToServer(serialNum);
                            Timber.i("writeToServer unionUserId " + serialNum);
                        }
                    }, 3, 10, TimeUnit.SECONDS);
                    break;
                }
                try {
                    Timber.i("尝试重新连接");
                    Thread.sleep(15*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


        public void disConnection() {
            if (mManager != null) {
                mManager.disConnect();
                executorService.shutdown();
            }
        }
    }



}
