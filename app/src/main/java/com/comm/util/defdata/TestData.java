package com.comm.util.defdata;

import com.comm.util.bean.BaseCount;
import com.google.gson.Gson;

public class TestData {
    public static void main(String[] args) {
        String da = "{\"meta\":{\"describe\":\"失败\",\"statusCode\":\"0\"}}";
        BaseCount bc = new Gson().fromJson(da, BaseCount.class);

//        DpObserver<BaseCount> setData = new SetData();
//        setData.onNext(bc);
    }
}
