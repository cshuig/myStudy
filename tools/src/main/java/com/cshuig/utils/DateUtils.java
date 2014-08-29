package com.cshuig.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.lang3.time.DurationFormatUtils;

/**
 * 日期工具类。
 * Created by Administrator on 2014/8/29 0029.
 */
public class DateUtils {

    /**
     * @return
     */
    public static Date now() {
        return (new Date());
    }
    /**
     * 将一个Date对象 按照pattern格式输出,如果pattern为空，则默认输出："yyyy-MM-dd 格式
     * @param date
     * @param pattern
     * @return
     */
    public static String formatDate(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        if (pattern == null) {
            pattern = "yyyy-MM-dd";
        }
        return (new SimpleDateFormat(pattern).format(date));
    }

    /**
     * 将一个Date对象 输出为：yyyy-MM-dd HH:mm:ss 格式的字符串
     * @param date
     * @return
     */
    public static String yyyy_MM_dd_HH_mm_ss_ByDate(Date date) {
        return (formatDate(date, "yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 输出当前日期
     * @return 输出格式：yyyyMMddHHmmssSSS
     */
    public static String yyyyMMddHHmmssSSS() {
        return (formatDate(now(), "yyyyMMddHHmmssSSS"));
    }

    /**
     * 输出当前日期
     * @return  输出格式：yyyyMMddHHmmss
     */
    public static String yyyyMMddHHmmss() {
        return (formatDate(now(), "yyyyMMddHHmmss"));
    }
    /**
     * 输出当前日期
     * @return 输出格式：yyyy-MM-dd
     */
    public static String formatDate() {
        return (formatDate(now(), "yyyy-MM-dd"));
    }

    /**
     * @return
     */
    public static String formatTime() {
        return (formatDate(now(), "HH:mm:ss"));
    }

    /**
     * @return
     */
    public static String formatTime2() {
        return (formatDate(now(), "HHmmss"));
    }

    /**
     * @return
     */
    public static String formatDate2() {
        return (formatDate(now(), "yyyyMMdd"));
    }

    /**
     * 将一个Date对象 输出为：yyyy-MM-dd 格式的字符串
     * @param date
     * @return
     */
    public static String yyyy_MM_dd_ByDate(Date date) {
        return (formatDate(date, "yyyy-MM-dd"));
    }
    /**
     * 将一个Date对象 输出为：yyyyMMdd 格式的字符串
     * @param date
     * @return
     */
    public static String yyyyMMddByDate(Date date) {
        return (formatDate(date, "yyyyMMdd"));
    }

    /**
     * @param date
     * @return
     */
    public static String formatTime(Date date) {
        return (formatDate(date, "HH:mm:ss"));
    }

    /**
     * @param datetime 格式：2014-08-29 16:00:00
     * @return
     */
    public static Date parseDateTime(String datetime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if ((datetime == null) || (datetime.equals(""))) {
            return null;
        } else {
            try {
                return formatter.parse(datetime);
            } catch (ParseException e) {
                return parseDate(datetime);
            }
        }
    }

    /**
     * @param date
     * @return
     */
    public static Date parseDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        if ((date == null) || (date.equals(""))) {
            return null;
        } else {
            try {
                return formatter.parse(date);
            } catch (ParseException e) {
                return null;
            }
        }
    }

    /**
     * @param date
     * @return
     */
    public static Date parseDate2(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        if ((date == null) || (date.equals(""))) {
            return null;
        } else {
            try {
                return formatter.parse(date);
            } catch (ParseException e) {
                return null;
            }
        }
    }

    /**
     * @param datetime
     * @return
     */
    public static Date parseDate(Date datetime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        if (datetime == null) {
            return null;
        } else {
            try {
                return formatter.parse(formatter.format(datetime));
            } catch (ParseException e) {
                return null;
            }
        }
    }

    /**
     * @param o
     * @return
     */
    public static String formatDate(Object o) {
        if (o == null) {
            return "";
        }
        if (o.getClass() == String.class) {
            return formatDate((String) o);
        } else if (o.getClass() == Date.class) {
            return formatDate((Date) o);
        } else if (o.getClass() == Timestamp.class) {
            return formatDate(new Date(((Timestamp) o).getTime()));
        } else {
            return o.toString();
        }
    }

    /**
     * @param o
     * @return
     */
    public static String formatDateTime(Object o) {
        if (o.getClass() == String.class) {
            return formatDateTime((String) o);
        } else if (o.getClass() == Date.class) {
            return formatDateTime((Date) o);
        } else if (o.getClass() == Timestamp.class) {
            return formatDateTime(new Date(((Timestamp) o).getTime()));
        } else {
            return o.toString();
        }
    }

    /**
     * 给时间加上或减去指定毫秒，秒，分，时，天、月或年等，返回变动后的时间
     *
     * @param date 要加减前的时间，如果不传，则为当前日期
     * @param field 时间域，有Calendar.MILLISECOND,Calendar.SECOND,Calendar.MINUTE,<br> *
     *            Calendar.HOUR,Calendar.DATE, Calendar.MONTH,Calendar.YEAR
     * @param amount
     *            按指定时间域加减的时间数量，正数为加，负数为减。
     * @return 变动后的时间
     */
    public static Date add(Date date, int field, int amount) {
        if (date == null) {
            date = new Date();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(field, amount);
        return cal.getTime();
    }

    /**
     * @param date
     * @param amount
     * @return
     */
    public static Date addMilliSecond(Date date, int amount) {
        return add(date, Calendar.MILLISECOND, amount);
    }

    /**
     * @param date
     * @param amount
     * @return
     */
    public static Date addSecond(Date date, int amount) {
        return add(date, Calendar.SECOND, amount);
    }

    /**
     * @param date
     * @param amount
     * @return
     */
    public static Date addMiunte(Date date, int amount) {
        return add(date, Calendar.MINUTE, amount);
    }

    /**
     * @param date
     * @param amount
     * @return
     */
    public static Date addHour(Date date, int amount) {
        return add(date, Calendar.HOUR, amount);
    }

    /**
     * @param date
     * @param amount
     * @return
     */
    public static Date addDay(Date date, int amount) {
        return add(date, Calendar.DATE, amount);
    }

    /**
     * @param date
     * @param amount
     * @return
     */
    public static Date addMonth(Date date, int amount) {
        return add(date, Calendar.MONTH, amount);
    }

    /**
     * @param date
     * @param amount
     * @return
     */
    public static Date addYear(Date date, int amount) {
        return add(date, Calendar.YEAR, amount);
    }

    /**
     * @return
     */
    public static Date getDate() {
        return parseDate(formatDate2());
    }

    /**
     * 日期格式转换,从YYYYMMdd格式转换成YYYY-MM-dd
     *
     * @param dateString
     * @return
     */
    public static String parseDateString(String dateString) {
        return formatDate(parseDate2(dateString));
    }

    /**
     *
     * @param dat
     * @param tim
     * @param len
     * @return
     */
    public static String formatTimeStamp(String dat, String tim,int len) {
        SimpleDateFormat df = new SimpleDateFormat(dat);
        if (tim.length()>len) {
            return df.format(new Long(tim));
        }
        return tim;
    }

    /**
     * 当前时间 到 目标时间 差
     * @param targetTime 格式：2014-08-29 16:00:00
     * @return
     */
    public static String nowToTargetTime(String targetTime){
        int available = 0;//目标时间延长几天
        Date targetDate = DateUtils.parseDateTime(targetTime);
        Date nowDate = DateUtils.parseDateTime(DateUtils.yyyy_MM_dd_HH_mm_ss_ByDate(now()));
        String remain = DurationFormatUtils.formatPeriod(nowDate.getTime(),org.apache.commons.lang3.time.DateUtils.addDays(targetDate,available).getTime(),"d'天'HH'小时'mm'分钟'ss'秒'");
        return remain;
    }

}
