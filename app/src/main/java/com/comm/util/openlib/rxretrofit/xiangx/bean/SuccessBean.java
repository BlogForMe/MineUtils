package com.comm.util.openlib.rxretrofit.xiangx.bean;

public class SuccessBean {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SuccessBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

