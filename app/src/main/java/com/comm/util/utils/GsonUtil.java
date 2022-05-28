package com.comm.util.utils;

import java.util.Map;

import com.comm.util.bean.ZigBean;
import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by A on 2018/2/1.
 * http://blog.csdn.net/wanghao200906/article/details/45889955
 */

public class GsonUtil {
    public static Gson gson = null;

    private static String mSessionId = null;

    static {
        if (gson == null) {
            gson = new Gson();
        }
    }

    public static void setSessionId(String sessionId) {
        mSessionId = sessionId;
    }

    public static String mapToGson(Map<String, Object> map) {
            map.put("sessionId","c582de5223554474bf3b5dd87966eb80h");
            map.put("patientCode","449");
//            map.put("userId","449");
//        if (map != null && mSessionId != null){
//            map.put("sessionId",CertificationEntrance.generateDynamicTokenToApp(mSessionId));
//        }
        String sJson = gson.toJson(map);
        return sJson;
    }

    public static RequestBody strToRequestBody(String data) {
        return RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), data);
    }


    /**
     * 转换成Body
     *
     * @return
     */
    public static RequestBody getRequestBody(String data) {
        return RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), data);
    }

    public static ZigBean parseData(String cc){
        return gson.fromJson(cc, ZigBean.class);
    }



//    public static RequestBody noDoctorIdRequestBody(Map<String, Object> map) {
//        map.put("operate_way", "4");
//        String mSessionId = String.valueOf(SharedPreferencesUtils.getParam(AppCtxUtil.getApp(), SharedPreferencesUtils.DOC_SESSION_ID, ""));
//        if (map != null && !isEmpty(mSessionId)) {
//            map.put("sessionId", mSessionId);
//        }
//        String strEntity = GsonUtil.mapToGson(map);
//        return RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), strEntity);
//    }
    public static RequestBody loginnoDoctorIdBody(Map<String, Object> map) {
        map.put("operate_way", "4");
        String strEntity = GsonUtil.mapToGson(map);
        return RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), strEntity);
    }

}
