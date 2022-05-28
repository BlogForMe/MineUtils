package com.comm.util.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import timber.log.Timber;

/**
 * Created by A on 2018/2/6.
 * https://github.com/jingle1267/android-utils/blob/master/util/src/main/java/com/ihongqiqu/util/DateUtils.java
 * <p>
 * http://blog.csdn.net/student9128/article/details/68932549
 */

public class DateUtils {
    /**
     * 日期类型 *
     * C代表冒号
     */
    public static final String yyyy_MM_dd = "yyyy-MM-dd";
    public static final String MM_dd = "MM-dd";
    public static final String MMdd = "MM:dd";
    public static final String HHCmm = "HH:mm";

    public static final String yyyyMMdd = "yyyyMMdd";
    public static final String HHmmss = "HHmmss";
    public static final String yyyyMMddCHHmmss = "yyyyMMddHH:mm:ss";
    public static final String LOCALE_DATE_FORMAT = "yyyy年M月d日 HH:mm:ss";
    public static final String DB_DATA_FORMAT = "yyyy-MM-DD HH:mm:ss";
    public static final String NEWS_ITEM_DATE_FORMAT = "hh:mm M月d日 yyyy";
    public static final String yyyyMMDDHHmmss = "yyyyMMddHHmmss";


    /**
     * yyyyMMDD ->   yyyy_MM_DD
     *
     * @param dateString
     */
    public static String ymd2yymmdd(String dateString) {
        SimpleDateFormat sdFormat = new SimpleDateFormat(yyyyMMdd);
        try {
            Date date = sdFormat.parse(dateString);
            String dataString = DateUtils.dateToString(date, yyyy_MM_dd);
            return dataString;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String y_m_d2mmdd(String dateString) {
        SimpleDateFormat sdFormat = new SimpleDateFormat(yyyyMMdd);
        try {
            Date date = sdFormat.parse(dateString);
            String dataString = DateUtils.dateToString(date, MM_dd);
            return dataString;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * @param dateString
     * @return
     */
    public static String ymdhms2mmdd(String dateString) {
        SimpleDateFormat sdFormat = new SimpleDateFormat(yyyyMMdd);
        try {
            Date date = sdFormat.parse(dateString);
            String dataString = DateUtils.dateToString(date, MMdd);
            return dataString;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }


    public static String dateToString(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    public static Date stringToDate(String dateStr, String pattern) throws ParseException {
        return new SimpleDateFormat(pattern).parse(dateStr);
    }


    /**
     * 天数加减
     *
     * @param dateTime
     * @param dayNum
     * @return
     */
    public static Object formatGetDate(String dateTime, int dayNum) {
        SimpleDateFormat sdf = new SimpleDateFormat(yyyyMMdd);
        Date date = null;
        try {
            date = stringToDate(dateTime, yyyyMMdd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, dayNum);
//        if (status == 1) {
//            return calendar.getTime();
//        }
        return sdf.format(calendar.getTime());
    }

    /**
     * 日期时间间隔
     * https://blog.csdn.net/laigezao/article/details/69236187
     *
     * @param dBegin
     * @param dEnd
     * @return
     */
    public static ArrayList<String> limitDates(Date dBegin, Date dEnd, String pattern) {
        ArrayList<String> lDate = new ArrayList<>();
        Calendar calBegin = Calendar.getInstance();
        calBegin.setTime(dBegin);

        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(dEnd);

        lDate.add(dateToString(calBegin.getTime(), pattern));
        while (dEnd.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add(dateToString(calBegin.getTime(), pattern));
        }
        return lDate;
    }


    /**
     * 6      * 时间戳转换成日期格式字符串
     * 7      * @param seconds 精确到秒的字符串
     * 8      * @param formatStr
     * 9      * @return
     * https://www.cnblogs.com/DreamDrive/p/5735758.html
     * 10
     */
    public static String timeStamp2Date(long time, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(time));
    }

    /**
     * https://codeday.me/bug/20171020/85365.html
     *
     * @param selectDate
     * @return
     */
    public static boolean isDateBigger(String selectDate) {
        SimpleDateFormat sdf = new SimpleDateFormat(yyyyMMdd);
        try {
            Date strDate = sdf.parse(selectDate);
            return new Date().after(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 时间列表临时使用
     *
     * @return
     * @throws ParseException
     */
    public static ArrayList<String> temporaryList() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date dBegin = sdf.parse("20180901");

        String currentDay = DateUtils.dateToString(new Date(), yyyyMMdd);
        Date dEnd = stringToDate(currentDay, yyyyMMdd);
        ArrayList<String> datas = limitDates(dBegin, dEnd, yyyyMMdd);
        return datas;
    }


    public static String isBetween(Date now, String formatTimes) {
        String ssTime = dateToString(new Date(), yyyyMMdd);

        for (String times : formatTimes.split(",")) {
            String[] arr = times.split("-");
            if (isBetween(ssTime + ":" + arr[0], ssTime + ":" + arr[1], times) != null) {
                return times;
            }
        }
        return null;
    }

    private static String isBetween(String beginStr, String endStr, String times) {
//        Calendar cal = Calendar.getInstance();


        SimpleDateFormat dt = new SimpleDateFormat(yyyyMMdd + ":" + HHCmm);
        try {
            Date now = dt.parse(dt.format(new Date()));
            Date begin = dt.parse(beginStr);
//            cal.set(Calendar.HOUR_OF_DAY, bp.getHours());
//            cal.set(Calendar.MINUTE, bp.getMinutes());
//            cal.set(Calendar.SECOND, 0);

            Date end = dt.parse(endStr);
//            cal.set(Calendar.HOUR_OF_DAY, ep.getHours());
//            cal.set(Calendar.MINUTE, ep.getMinutes());
//            cal.set(Calendar.SECOND, 59);
//            Date end = cal.getTime();


            boolean fir = begin.before(now) /*begin.getTime() < now.getTime()*/;
            Timber.i("fir   " + fir);
            boolean sed = end.after(now)    /*end.getTime() > now.getTime()*/;
            Timber.i("sed   " + sed);


            if (fir && sed) {
                Timber.i("times   " + times);
                return times;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


}
