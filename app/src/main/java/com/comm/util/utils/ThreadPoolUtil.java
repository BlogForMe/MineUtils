package com.comm.util.utils;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolUtil {
    private static final String TAG = "ThreadPool";
    private static final int CORE_POOL_SIZE = 3;
    private static final int MAX_POOL_SIZE = 8;
    private static final int KEEP_ALIVE_TIME = 10; // 10 seconds
    private static ThreadPoolUtil instance;
    private ThreadPoolExecutor threadPool = null;

    private ThreadPoolUtil() {
        threadPool = new ThreadPoolExecutor(CORE_POOL_SIZE,MAX_POOL_SIZE,KEEP_ALIVE_TIME,
                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
    }

    public static synchronized ThreadPoolUtil getInstance(){
        if(instance == null){
            instance = new ThreadPoolUtil();
        }
        return instance;
    }

    public void removeJob(Runnable task){
        threadPool.remove(task);
    }

    /***
     * 线程池执行command
     * @param r
     */
    public void submmitJob(Runnable r){
        threadPool.execute(r);
    }
}
