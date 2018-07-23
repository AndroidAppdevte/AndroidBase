package com.scqkzqtz.base.library.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {

    /**
     * @param patten 填入所需时间格式yyyy-MM-dd HH:mm:ss
     * @return Date转String
     */
    public static String DateToString(Date dateDate, String patten) {
        String strsub = "";
        if (dateDate != null) {
            SimpleDateFormat format = new SimpleDateFormat(patten);
            format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
            strsub = format.format(dateDate);
        } else {
            strsub = "";
        }
        return strsub;
    }

    /**
     * 默认转换yyyy-MM-dd HH:mm:ss
     */
    public static String DateToString(Date dateDate) {
        String strsub = "";
        if (dateDate != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
            strsub = format.format(dateDate);
        } else {
            strsub = "";
        }
        return strsub;
    }

    /**
     * String转换Date
     */
    public static Date StringToDate(String dateStr) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        Date date = null;
        try {
            date = format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            new Date();
        }
        return date;
    }

    /**
     * 时间戳转换Date
     *
     * @param lt      时间戳
     * @param pattern yyyy-MM-dd HH:mm:ss
     */
    public static String timeStampToDate(long lt, String pattern) {
        String dateStr;
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        Date date = new Date(lt);
        dateStr = format.format(date);
        return dateStr;
    }

    /**
     * 获取日期是星期几
     */
    public static String getWeekDayStr(Date date) {
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        String mWay = "星期天";
        if ("1".equals(String.valueOf(c.get(Calendar.DAY_OF_WEEK)))) {
            mWay = "星期天";
        } else if ("2".equals(mWay)) {
            mWay = "星期一";
        } else if ("3".equals(mWay)) {
            mWay = "星期二";
        } else if ("4".equals(mWay)) {
            mWay = "星期三";
        } else if ("5".equals(mWay)) {
            mWay = "星期四";
        } else if ("6".equals(mWay)) {
            mWay = "星期五";
        } else if ("7".equals(mWay)) {
            mWay = "星期六";
        }
        return mWay;
    }

    /**
     * 获取日期是星期几
     */
    public static int getWeekDayNumber(Date date) {
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        String mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        try {
            return Integer.parseInt(mWay);
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    /**
     * 日期加减
     *
     * @param date 日期
     * @param day  加减天数
     */
    public static Date dateAfterDay(Date date, int day) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_YEAR, day);
        return c.getTime();
    }

    /**
     * 月份加减
     *
     * @param date  时间
     * @param month 加减月份
     */
    public static Date dateAfterMonth(Date date, int month) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, month);
        return c.getTime();
    }

    /**
     * 两个时间相差的天数
     */
    public static int dateBetween(Date date1, Date date2) {
        long days = (date1.getTime() - date2.getTime()) / (1000 * 3600 * 24);
        return (int) days;
    }
}
