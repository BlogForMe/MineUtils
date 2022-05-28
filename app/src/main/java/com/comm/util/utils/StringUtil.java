package com.comm.util.utils;

import java.math.BigDecimal;
import java.util.List;

import static android.text.TextUtils.isEmpty;

/**
 * 格式化显示字符串
 */
public class StringUtil {

    public static final int PATIENTCODE = 449;
    public static final double TEMPHIGHHEAD = 37.4; // 正常温度最高值
    public static final double TEMPLOWHEAD = 34.5;
    public static final double TEMPHIGHBODY = 37.3;
    public static final double TEMPLOWBODY = 36.1;
    public static final double TEMPHIGHFOOT = 30;
    public static final double TEMPLOWFOOT = 28;
    public static final double SUGARBEFORHIGT = 6.1;//餐前血糖
    public static final double SUGARBEFORLOW = 3.9;
    public static final double SUGARAFTERHIGT = 9.4;  //餐后血糖
    public static final double SUGARAFTERRLOW = 6.7;
    public static final int OXYGENSP02H = 94;   // 大于94 正常 大于90 供氧不足 小于90 低血氧症
    public static final int OXYGENSP02L = 90;
    public static final int OXYGENPRH = 100; // 大于100 心动过速  小于60 心动缓慢
    public static final int OXYGENPRL = 60;
    //    public static final double  OXYGENPIH = 6.7;
//    public static final double  OXYGENPIL= 6.7;
    public static final int PRESSHYh = 160;
    public static final int PRESSHYL = 140;
    public static final int PRESSDYh = 100;
    public static final int PRESSDYL = 90;
    public static final String SUGAR = "btn_blood_sugar_test";
    public static final String TEMP = "btn_temp_test";
    public static final String PHOTO = "btn_photo_test";
    public static final String FEET = "btn_feet_test";
    public static final String OXYGEN = "btn_ox_test";
    public static final String PRESS = "btn_press_test";
    public static final String footFeelingNormal = "Normal";
    public static final String footFeelingSlip = "Slip";
    public static final String footFeelingVanish = "Vanish";
    /* 注册广播*/
    public final static String ACTION_GATT_CONNECTED = "com.example.bluetooth.le.ACTION_GATT_CONNECTED";
    public final static String ACTION_GATT_DISCONNECTED = "com.example.bluetooth.le.ACTION_GATT_DISCONNECTED";
    public final static String ACTION_GATT_SERVICES_DISCOVERED = "com.example.bluetooth.le.ACTION_GATT_SERVICES_DISCOVERED";
    public final static String ACTION_DATA_AVAILABLE = "com.example.bluetooth.le.ACTION_DATA_AVAILABLE";
    public final static String EXTRA_NOTIFY_DATA = "com.example.bluetooth.le.EXTRA_NOTIFY_DATA";
    public final static String EXTRA_READ_DATA = "com.example.bluetooth.le.EXTRA_READ_DATA";
    public final static String BREAK_CONNECT = "com.casanube.dm.casanube_dm_android";
    // business action
    public final static String ACTION_CHARACTER_NOTIFICATION = "com.example.bluetooth.le.notification.success";
    public final static String ACTION_SPO2_DATA_AVAILABLE = "com.example.bluetooth.le.ACTION_SPO2_DATA_AVAILABLE";
    public final static String EXTRA_DATA = "com.example.bluetooth.le.EXTRA_DATA";
    // 力康设备
    public final static int TRANSFER_PACKAGE_SIZE = 10;
    public static String PatternEdit = "[\\u4e00-\\u9fa5]+"; //匹配汉字
    public static String PattrenInteger = "^[1-9]\\d*|0$";//匹配整数
    public static boolean isCheckHistory = false;

    /**
     * 判断字符是否是汉字
     * <p>
     * https://blog.csdn.net/qq_28585471/article/details/76056625
     *
     * @param c 字符
     * @return 是否是汉字
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);

        // if(c>255){
        // return true;
        // }

        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
            || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
            || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
            || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
            || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
            || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;
    }

    public static float floatOne(float fl) {
        BigDecimal bd = new BigDecimal(fl);
        BigDecimal nf = bd.setScale(1, BigDecimal.ROUND_HALF_UP);
        return nf.floatValue();
    }

    public static String getRemoteTxt(int rmStates) {
        String txt = "";
        switch (rmStates) {
            case 0:
                txt = "未开始";
                break;
            case 1:
                txt = "问卷准备";
                break;
            case 2:
                txt = "检查进行中";
                break;
            case 3:
                txt = "报告编辑";
                break;
            case 4:
                txt = "检查完毕";
                break;
            case 5:
                txt = "已取消";
                break;
        }
        return txt;
    }

    /**
     * 保留两位小数
     *
     * @param value
     * @return
     */
    public static double decimalTwoPlace(double value) {
        BigDecimal bigDecimal = new BigDecimal(value);
        double f2 = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return f2;
    }

    /**
     * 保留1位小数
     *
     * @param value
     * @return
     */
    public static double decimalOnePlace(double value) {
        BigDecimal bigDecimal = new BigDecimal(value);
        double f2 = bigDecimal.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
        return f2;
    }

    /**
     * 取整
     *
     * @param value
     * @return
     */
    public static int doubleToInt(double value) {
        int dti = new BigDecimal(2.7875).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
        return dti;
    }

    public static String getCheckDateConclusion(String mealStatus, Double dataValue, int timeInterval) {
        String conclusion = "";
        if ("BLOOD_GLUCOSE_AM".equals(mealStatus) || "SLEEP_AM".equals(mealStatus)) {
            if (dataValue <= 3.9) {
                conclusion = "rel_low";
            } else if (dataValue <= 7.0 && dataValue > 3.9) {
                conclusion = "normal";
            } else if (dataValue > 7.0 && dataValue <= 8.4) {
                conclusion = "rel_high";
            } else if (dataValue > 8.4) {
                conclusion = "very_high";
            }
        } else {
            if (timeInterval == 1) {
                if (dataValue <= 3.9) {
                    conclusion = "rel_low";
                } else if (dataValue <= 9.4 && dataValue > 3.9) {
                    conclusion = "normal";
                } else if (dataValue > 9.4 && dataValue <= 11.1) {
                    conclusion = "rel_high";
                } else if (dataValue > 11.1) {
                    conclusion = "very_high";
                }
            } else if (timeInterval == 2) {
                if (dataValue <= 3.9) {
                    conclusion = "rel_low";
                } else if (dataValue <= 7.8 && dataValue > 3.9) {
                    conclusion = "normal";
                } else if (dataValue > 7.8 && dataValue <= 11.1) {
                    conclusion = "rel_high";
                } else if (dataValue > 11.1) {
                    conclusion = "very_high";
                }
            }
        }
        return conclusion;
    }

    public static boolean isListEmpty(List<?> list){
        return list != null && !list.isEmpty();
    }

    /**
     * 字体设置
     * @param color
     * @param unit
     * @return
     */
    public static String getUnitText(String color,String unit) {
        return "<font color="+color+"><small><small><small>" + unit + "</small></small></small></font>";
    }

    public static String getUnitText(String unit) {
        return "<font color='#333333'>" + unit + "</font>";
    }

    public static   String getIsTxt(String txt){
        return   isEmpty(txt)? "":txt;
    }

    public String getDateRange(String mealStatus, Double dataValue, int timeInterval) {
        String conclusion = "";
        if ("BLOOD_GLUCOSE_AM".equals(mealStatus) || "SLEEP_AM".equals(mealStatus)) {
            if (dataValue <= 3.9) {
                conclusion = "rel_low";
            } else if (dataValue <= 7.0 && dataValue > 3.9) {
                conclusion = "normal";
            } else if (dataValue > 7.0 && dataValue <= 8.4) {
                conclusion = "rel_high";
            } else if (dataValue > 8.4) {
                conclusion = "very_high";
            }
        }
        return conclusion;
    }
}
