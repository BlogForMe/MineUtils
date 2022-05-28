package com.comm.util.ndk;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 9/29/2017.
 */
public class DeviceValueBean {
    private Long number;


    private int pressureHighValue;


    private int pressureLowValue;


    private int jumpValue;


    private double dataValue;


    private String date;


    private Date checkDate;


    private boolean checkGls;


    private int deviceType;


    private int beforeMeal = 0;
    private String ckeckTime;

    public String getCkeckTime() {
        return ckeckTime;
    }

    public void setCkeckTime(String ckeckTime) {
        this.ckeckTime = ckeckTime;
    }

    public Long getNumber() {
        return this.number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public int getPressureHighValue() {
        return this.pressureHighValue;
    }

    public void setPressureHighValue(int pressureHighValue) {
        this.pressureHighValue = pressureHighValue;
    }

    public int getPressureLowValue() {
        return this.pressureLowValue;
    }

    public void setPressureLowValue(int pressureLowValue) {
        this.pressureLowValue = pressureLowValue;
    }

    public int getJumpValue() {
        return this.jumpValue;
    }

    public void setJumpValue(int jumpValue) {
        this.jumpValue = jumpValue;
    }

    public double getDataValue() {
        return this.dataValue;
    }

//    public void setDataValue(int dataValue) {
//        this.dataValue = dataValue;
//    }

    public void setDataValue(double dataValue) {
        this.dataValue = dataValue;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
        checkDate = new Date();
    }

    public boolean isCheckGls() {
        return this.checkGls;
    }

    public String getCheckDateFormat() {
        if (checkDate == null) return "";
        return new SimpleDateFormat("yyyyMMddHHmmss").format(checkDate);
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public boolean getCheckGls() {
        return this.checkGls;
    }

    public void setCheckGls(boolean checkGls) {
        this.checkGls = checkGls;
    }

    public int getDeviceType() {
        return this.deviceType;
    }

    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
    }

    public int getBeforeMeal() {
        return this.beforeMeal;
    }

    public void setBeforeMeal(int beforeMeal) {
        this.beforeMeal = beforeMeal;
    }



}
