package com.comm.util.openlib.rxretrofit.xiangx.bean;

public class ResponseResult {
    private SuccessBean data;
    private int code;
    private String message;

    public ResponseResult() {
    }

    public ResponseResult(SuccessBean data, int code, String message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }

    public SuccessBean getData() {
        return data;
    }

    public void setData(SuccessBean data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
