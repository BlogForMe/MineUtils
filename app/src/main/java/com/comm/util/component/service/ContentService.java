package com.comm.util.component.service;

import java.util.ArrayList;
import java.util.List;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import com.comm.util.openlib.rxretrofit.renyugang.Person;

public class ContentService extends Service {

    //回调接口的集合
    private List<Callback> list;
    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String name = (String) msg.obj;
            Log.i("ContentService", "---name-->" + name);
            Person person = new Person();
            person.setName(name);
            Log.i("ContentService", "---list.size()-->" + list.size());
            Log.i("ContentService", "---person-->" + person);
            //遍历集合，通知所有的实现类，即activity
            for (int i = 0; i < list.size(); i++) {
                list.get(i).getPerson(person);
            }
        }
    };

    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        return new LocalBinder();
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        list = new ArrayList<Callback>();
    }

    /**
     * 往回调接口集合中添加一个实现类
     * @param callback
     */
    public void addCallback(Callback callback) {
        list.add(callback);
    }

    public void asyncSendPerson(final String name) {
        // 休息5秒，模拟异步任务
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.sendMessage(handler.obtainMessage(0, name));
            }
        }).start();
    }

    /**
     * 回调接口
     * @author Ivan Xu
     *
     */
    public interface Callback {
        void getPerson(Person person);
    }

    public final class LocalBinder extends Binder {
        public ContentService getService() {
            return ContentService.this;
        }
    }
}
