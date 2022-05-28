package com.comm.util.bean;

/**
 * Created by Administrator on 2018/8/24.
 */

public class SigleField extends BaseCount {

    private int videoRecordId;
    private String config;
    private String topic;

    public String getTopic() {
        return topic;
    }

    public int getVideoRecordId() {
        return videoRecordId;
    }

    public String getConfig() {
        return config;
    }
}
