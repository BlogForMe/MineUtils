package com.comm.util.memory;

import android.content.Context;

/**
 * Created by jon on 1/8/18.
 */

public class UserManger {
    private  static  UserManger instance;
    private final Context  context;

    private UserManger(Context context) {
        this.context = context;
    }

    public static UserManger getInstance() {
        return instance;
    }

    public static  UserManger getInstance(Context context){
        if (instance==null){
            instance = new UserManger(context);
        }
        return  instance;
    }
}
