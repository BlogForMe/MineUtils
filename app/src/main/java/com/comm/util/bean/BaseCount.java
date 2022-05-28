package com.comm.util.bean;

/**
 * @author : John
 * @date : 2018/7/19
 */
public class BaseCount<T> extends BaseMeta {

    /**
     * count : 0
     * dataList : []
     * dataType : list
     * meta : {"describe":"操作成功","statusCode":"0"}
     */

    private int count;
    private String dataType;
    private T dataList;
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public T getDataList() {
        return dataList;
    }

    public void setDataList(T dataList) {
        this.dataList = dataList;
    }

}
