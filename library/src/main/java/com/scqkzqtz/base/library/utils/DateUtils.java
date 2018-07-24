package com.scqkzqtz.base.library.utils;

import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 时间转换工具
 */
public class DateUtils {
    /**
     * 转换时间规则:刚刚-几分钟前-几小时前-昨天（pattern1）-yyyy-MM-dd（pattern2）      //（pattern）表示可根据传入日期格式得到指定样式
     */
    public static final String FORMATTYPENORMAL = "formatTypeNormal";
    /**
     * 转换时间规则:刚刚-几分钟前-上下午（pattern1）-昨天上下午（pattern1）-星期几(注：往前五天)上下午（pattern1）-yyyy-MM-dd（pattern2）上下午（pattern1)
     */
    public static final String FORMATTYPEAMANDPM = "formatTypeAmAndPm";
    /**
     * 转换时间规则:刚刚-几分钟前-几小时前-昨天（pattern1）-几天前（pattern1 注：往前五天）-yyyy-MM-dd（pattern2）
     */
    public static final String FORMATTYPEDAY = "formatTypeDay";
    /**
     * 转换时间规则:刚刚-几分钟前-几小时前-昨天（pattern1）-星期几（ pattern1 注：往前五天）-yyyy-MM-dd（pattern2）
     */
    public static final String FORMATTYPEWEEK = "formatTypeWeek";

    /**
     * Date转String
     *
     * @param pattern 填入所需时间格式yyyy-MM-dd HH:mm:ss
     */
    public static String dateToString(Date dateDate, String pattern) {
        String strsub = "";
        if (dateDate != null) {
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
            strsub = format.format(dateDate);
        } else {
            strsub = "";
        }
        return strsub;
    }

    /**
     * Date转String-默认方法 时间格式：为yyyy-MM-dd HH:mm:ss
     *
     * @param dateDate
     */
    public static String dateToString(Date dateDate) {
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
     *
     * @param dateStr 时间
     * @param pattern 时间样式 默认为 yyyy-MM-dd HH:mm:ss
     */
    public static Date stringToDate(String dateStr, String pattern) {
        if (TextUtils.isEmpty(pattern)) pattern = "yyyy-MM-dd HH:mm:ss";
        DateFormat format = new SimpleDateFormat(pattern);
        format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        Date date;
        try {
            date = format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            date = new Date();
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
     * 获取日期是星期几（星期*）
     *
     * @param date 日期
     */
    public static String getWeekDayStr(Date date) {
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        c.setTime(date);
        String mWay = "星期天";
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            mWay = "星期天";
        } else if (c.get(Calendar.DAY_OF_WEEK) == 2) {
            mWay = "星期一";
        } else if (c.get(Calendar.DAY_OF_WEEK) == 3) {
            mWay = "星期二";
        } else if (c.get(Calendar.DAY_OF_WEEK) == 4) {
            mWay = "星期三";
        } else if (c.get(Calendar.DAY_OF_WEEK) == 5) {
            mWay = "星期四";
        } else if (c.get(Calendar.DAY_OF_WEEK) == 6) {
            mWay = "星期五";
        } else if (c.get(Calendar.DAY_OF_WEEK) == 7) {
            mWay = "星期六";
        }
        return mWay;
    }

    /**
     * 获取日期是星期几 （1）
     *
     * @param date 日期
     */
    public static int getWeekDayNumber(Date date) {
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        c.setTime(date);
        return c.get(Calendar.DAY_OF_WEEK);
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
     *
     * @param date1 时间一
     * @param date2 时间二
     */
    public static int dateBetween(Date date1, Date date2) {
        long days = (date1.getTime() - date2.getTime()) / (1000 * 3600 * 24);
        return (int) days;
    }

    /**
     * 转换时间规则-默认格式
     */
    public static String timeStrWith(Date dateRelease, String formatType) {
        return timeStrWith(new Date(), dateRelease, formatType);
    }

    /**
     * 转换时间规则-默认格式-传入当前时间
     *
     * @param dateCurrent 当前时间
     */
    public static String timeStrWith(Date dateCurrent, Date dateRelease, String formatType) {
        if (formatType.equals(FORMATTYPEAMANDPM)) {
            return timeStrWith(dateCurrent, dateRelease, "HH:mm", "yyyy-MM-dd", formatType);
        } else {
            return timeStrWith(dateCurrent, dateRelease, "HH:mm", "yyyy-MM-dd HH:mm", formatType);
        }
    }

    /**
     * 转换时间规则-自定义格式
     */
    public static String timeStrWith(Date dateRelease, String pattern1, String pattern2, String formatType) {
        return timeStrWith(new Date(), dateRelease, pattern1, pattern2, formatType);
    }

    /**
     * 转换时间规则-自定义格式-传入当前时间
     *
     * @param dateCurrent 当前时间
     */
    public static String timeStrWith(Date dateCurrent, Date dateRelease, String pattern1, String pattern2, String formatType) {
        String timeStr = "";
        switch (formatType) {
            case FORMATTYPEAMANDPM:
                timeStr = formatTypeAmAndPm(dateCurrent, dateRelease, pattern1, pattern2);
                break;
            case FORMATTYPEDAY:
                timeStr = formatTypeDay(dateCurrent, dateRelease, pattern1, pattern2);
                break;
            case FORMATTYPEWEEK:
                timeStr = formatTypeWeek(dateCurrent, dateRelease, pattern1, pattern2);
                break;
            default:
                timeStr = formatTypeNormal(dateCurrent, dateRelease, pattern1, pattern2);
                break;
        }
        return timeStr;
    }

    /**
     * 刚刚-几分钟前-几小时前-昨天（pattern1）-yyyy-MM-dd（pattern2）
     */
    private static String formatTypeNormal(Date dateCurrent, Date dateRelease, String pattern1, String pattern2) {
        long second = (dateCurrent.getTime() - dateRelease.getTime()) / 1000;//时间差-多少秒
        long minute = second / 60;
        long hour = minute / 60;
        if (second < 60) {
            return "刚刚";
        } else if (minute < 60) {
            return minute + "分钟前";
        } else {
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(dateRelease);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(dateCurrent);
            calendar2.add(Calendar.DAY_OF_YEAR, -1);
            if (calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR)) {//同一天
                return "昨天 " + dateToString(dateRelease, pattern1);
            } else if (1 <= hour && hour < 24) {
                return hour + "小时前";
            } else {
                return dateToString(dateRelease, pattern2);
            }
        }
    }

    /**
     * 刚刚-几分钟前-上下午（pattern1）-昨天上下午（pattern1）-星期几(注：往前五天)上下午（pattern1）-yyyy-MM-dd（pattern2）上下午（pattern1)
     */
    private static String formatTypeAmAndPm(Date dateCurrent, Date dateRelease, String pattern1, String pattern2) {
        long second = (dateCurrent.getTime() - dateRelease.getTime()) / 1000;//时间差-多少秒
        long minute = second / 60;
        long hour = minute / 60;
        if (second < 60) {
            return "刚刚";
        } else if (minute < 60) {
            return minute + "分钟前";
        } else {
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(dateRelease);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(dateCurrent);
            calendar2.add(Calendar.DAY_OF_YEAR, -1);

            String timeStr = ((calendar1.get(Calendar.AM_PM) == Calendar.getInstance().AM) ? "上午" : "下午") + dateToString(dateRelease, pattern1);
            if (calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR)) {//同一天
                return "昨天 " + timeStr;
            } else if (1 <= hour && hour < 24) {
                return timeStr;
            } else {
                for (int i = 2; i <= 5; i++) {
                    calendar2.add(Calendar.DAY_OF_YEAR, -1);
                    if (calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR)) {//同一天
                        return getWeekDayStr(calendar1.getTime()) + " " + timeStr;
                    }
                }
                return dateToString(dateRelease, pattern2) + " " + timeStr;
            }
        }
    }

    /**
     * 刚刚-几分钟前-几小时前-昨天（pattern1）-星期几（ pattern1 注：往前五天）-yyyy-MM-dd（pattern2）
     */
    private static String formatTypeWeek(Date dateCurrent, Date dateRelease, String pattern1, String pattern2) {
        long second = (dateCurrent.getTime() - dateRelease.getTime()) / 1000;//时间差-多少秒
        long minute = second / 60;
        long hour = minute / 60;
        if (second < 60) {
            return "刚刚";
        } else if (minute < 60) {
            return minute + "分钟前";
        } else {
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(dateRelease);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(dateCurrent);
            calendar2.add(Calendar.DAY_OF_YEAR, -1);
            if (calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR)) {//同一天
                return "昨天 " + dateToString(dateRelease, pattern1);
            } else if (1 <= hour && hour < 24) {
                return hour + "小时前";
            } else {
                for (int i = 2; i <= 5; i++) {
                    calendar2.add(Calendar.DAY_OF_YEAR, -1);
                    if (calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR)) {//同一天
                        return getWeekDayStr(calendar1.getTime()) + " " + dateToString(dateRelease, pattern1);
                    }
                }
                return dateToString(dateRelease, pattern2);
            }
        }
    }

    /**
     * 刚刚-几分钟前-几小时前-昨天（pattern1）-星期几（ pattern1 注：往前五天）-yyyy-MM-dd（pattern2）
     */
    private static String formatTypeDay(Date dateCurrent, Date dateRelease, String pattern1, String pattern2) {
        long second = (dateCurrent.getTime() - dateRelease.getTime()) / 1000;//时间差-多少秒
        long minute = second / 60;
        long hour = minute / 60;
        if (second < 60) {
            return "刚刚";
        } else if (minute < 60) {
            return minute + "分钟前";
        } else {
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(dateRelease);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(dateCurrent);
            calendar2.add(Calendar.DAY_OF_YEAR, -1);
            if (calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR)) {//同一天
                return "昨天 " + dateToString(dateRelease, pattern1);
            } else if (1 <= hour && hour < 24) {
                return hour + "小时前";
            } else {
                for (int i = 2; i <= 5; i++) {
                    calendar2.add(Calendar.DAY_OF_YEAR, -1);
                    if (calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR)) {//同一天
                        return i + "天前 ";
                    }
                }
                return dateToString(dateRelease, pattern2);
            }
        }
    }
}
