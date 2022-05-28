package com.comm.util.utils;

import java.io.InputStream;
import java.util.ArrayList;

import android.content.Context;

public class RawAssertUtil {

    public static  ArrayList<Float> loadDatas(Context context, int resData) {
        ArrayList<Float> datas = new ArrayList();
        try {
            String data0;
            InputStream in = context.getResources().openRawResource(resData);
            int length = in.available();
            byte[] buffer = new byte[length];
            in.read(buffer);
            data0 = new String(buffer);
            in.close();
            String[] data0s = data0.split(",");
            for (String str : data0s) {
                datas.add(Float.valueOf(str));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datas;
    }
}
