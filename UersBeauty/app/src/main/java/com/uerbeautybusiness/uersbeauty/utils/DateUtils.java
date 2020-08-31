package com.uerbeautybusiness.uersbeauty.utils;

import android.annotation.SuppressLint;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiong on 2016/9/26.
 * time:2016/9/26 12:02
 */
//时间转换工具类
public class DateUtils {

    private static Calendar calendar;

    public static String getDateString(String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date(System.currentTimeMillis());
        String time = dateFormat.format(date);
        return time;
    }

    public static String getTime1(String user_time) {
        String re_time = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d;
        try {
            d = sdf.parse(user_time);
            long l = d.getTime();
            String str = String.valueOf(l);
            re_time = str.substring(0, 10);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return re_time;
    }

    public static String getYearMonthDay() {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy年MM月dd日");
        Date date1 = new Date();
        String formatDate1 = sdf1.format(date1);
        return formatDate1;

    }

    public static String getTimeFromLong(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Long l = Long.valueOf(time);
        long now = Long.valueOf(String.valueOf(date.getTime()).substring(0, 10));
        long t = now - l;
        long min = t / 60;
        if (min < 60 && min > 5) {
            return min + "分钟前";
        } else if (min <= 5) {
            return "刚刚";
        } else {
            long hour = t / 60 / 60;
            if (hour < 24) {
                return hour + "小时前";
            } else {
                long day = t / 60 / 60 / 24;
                if (day > 5) {
                    return sdf.format(new Date(Long.valueOf(time) * 1000));
                } else {
                    return day + "天前";
                }
            }
        }
    }

    public static String getDaoJiShi(long time) {
        long l = time / 86400;
        long l1 = time % 86400;
        long l2 = l1 / 3600;
        long l3 = l1 % 3600;
        long l4 = l3 / 60;
        return l + "天" + " " + l2 + ":" + l4;
    }


    public static String getDaoJiShi3(long time) {
        long l = time / 86400;
        long l1 = time % 86400;
        long l2 = l1 / 3600;
        long l3 = l1 % 3600;
        long l4 = l3 / 60;
        if (l == 0) {
            if (l2 == 0) {
                return l4 + "分";
            } else {
                return l2 + "时";
            }

        } else {
            return l + "天";
        }

    }

    public static String getHourAndMinute(long time) {
        long sumMinute = time / 60;
        long minute = sumMinute % 60;
        long hour = sumMinute / 60;
        if (minute < 10) {
            return hour + "小时0" + minute + "分钟";
        } else {
            return hour + "小时" + minute + "分钟";
        }
    }

    public static String getHourAndMinuteAndSecond(long time) {
        long second = time % 60;
        long sumMinute = time / 60;
        long minute = sumMinute % 60;
        long hour = sumMinute / 60;
        String times = "";
        if (hour < 10) {
            times += "0" + hour + ":";
        } else {
            times += hour + ":";
        }
        if (minute < 10) {
            times += "0" + minute + ":";
        } else {
            times += minute + ":";
        }
        if (second < 10) {
            times += "0" + second;
        } else {
            times += second;
        }
        return times;
    }

    public static String getDaoJiShi4(long time) {
        long l = time / 86400;
        long l1 = time % 86400;
        long l2 = l1 / 3600;
        long l3 = l1 % 3600;
        long l4 = l3 / 60;
        if (l == 0) {
            if (l2 == 0) {
                return l4 + "分";
            } else {
                return l2 + "时" + l4 + "分";
            }

        } else {
            return l + "天" + l2 + "时";
        }

    }

    ;

    public static String getDaoJiShi2(long time) {
        long l = time / 86400;
        long l1 = time % 86400;
        long l2 = l1 / 3600;
        StringBuilder sb = new StringBuilder();
        if (String.valueOf(l).length() == 1) {
            sb.append("0").append(l).append("天");
        } else {
            sb.append(l).append("天");
        }
        if (String.valueOf(l2).length() == 1) {
            sb.append("0").append(l2).append("时");
        } else {
            sb.append(l2).append("时");
        }
        return sb.toString();
    }

    /**
     * 解析10位的时间戳
     * 获取年月日 时分秒
     *
     * @param time
     * @return
     */
    public static String getDate(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        return sdf.format(new Date(Long.valueOf(time) * 1000));
    }

    public static String getToday(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(new Date(Long.valueOf(time) * 1000));
    }

    public static String getDate1(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日 HH:mm");
        return sdf.format(new Date(Long.valueOf(time) * 1000));
    }

    public static String stampToDate(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        return sdf.format(new Date(Long.valueOf(time) * 1000));
    }

    public static String stampToDate2(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date(Long.valueOf(time) * 1000));
    }

    public static String getDate3(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
        return sdf.format(new Date(Long.valueOf(time) * 1000));
    }

    public static String getDate4(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
        return sdf.format(new Date(Long.valueOf(time) * 1000));
    }

    public static String getDate5(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        return sdf.format(new Date(Long.valueOf(time) * 1000));
    }

    public static String getDate7(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        return sdf.format(new Date(Long.valueOf(time) * 1000));
    }

    public static String getDate6(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
        return sdf.format(new Date(Long.valueOf(time) * 1000));
    }

    public static String getDate8(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM.dd");
        return sdf.format(new Date(Long.valueOf(time) * 1000));
    }

    public static String getDate9(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        return sdf.format(new Date(Long.valueOf(time) * 1000));
    }

    public static String getDate10(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd HH:mm");
        return sdf.format(new Date(Long.valueOf(time) * 1000));
    }

    public static String getDate11(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
        return sdf.format(new Date(Long.valueOf(time) * 1000));
    }

    /**
     * 不是本年显示年月，本年显示月份
     *
     * @param time
     * @return
     */
    public static String getYearOrMonth(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String year = sdf.format(new Date(Long.valueOf(time) * 1000));

        Calendar calendar = Calendar.getInstance();
        int thisYear = calendar.get(Calendar.YEAR);//今年
        if (Integer.parseInt(year) != thisYear) {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy年MM月");
            return sdf1.format(new Date(Long.valueOf(time) * 1000));
        } else {
            SimpleDateFormat sdf2 = new SimpleDateFormat("MM月");
            return sdf2.format(new Date(Long.valueOf(time) * 1000));
        }
    }

    /**
     * 获取小时分钟
     */

    public static String getTime(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(new Date(Long.valueOf(time) * 1000));
    }

    /*获取小时分秒*/
    public static String getTime2(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(new Date(Long.valueOf(time) * 1000));
    }

    public static String getNoyearTime(long date) {
        long currentTime = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        boolean isSameYear = sdf.format(currentTime).equals(sdf.format(date * 1000));
        if (isSameYear) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd HH:mm");
            Log.e("tiantian", "返回的日期:" + simpleDateFormat.format(date));
            return simpleDateFormat.format(date * 1000);
        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            return simpleDateFormat.format(date * 1000);
        }
    }

    public static String getYearTime(long date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return simpleDateFormat.format(date * 1000);
    }

    public static String getNoYearNoMinTime(long date) {
        long currentTime = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        boolean isSameYear = sdf.format(currentTime).equals(sdf.format(date * 1000));
        if (isSameYear) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd");
            Log.e("tiantian", "返回的日期:" + simpleDateFormat.format(date));
            return simpleDateFormat.format(date * 1000);
        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
            return simpleDateFormat.format(date * 1000);
        }
    }

    public static String getNoyearTime1(long date) {
        long currentTime = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        boolean isSameYear = sdf.format(currentTime).equals(sdf.format(date * 1000));
        if (isSameYear) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd HH:mm");
            Log.e("tiantian", "返回的日期:" + simpleDateFormat.format(date));
            return simpleDateFormat.format(date * 1000);
        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            return simpleDateFormat.format(date * 1000);
        }
    }

    public static String getNoyearTime3(long date) {
        long currentTime = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        boolean isSameYear = sdf.format(currentTime).equals(sdf.format(date * 1000));
        if (isSameYear) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd日");
            Log.e("tiantian", "返回的日期:" + simpleDateFormat.format(date));
            return simpleDateFormat.format(date * 1000);
        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
            return simpleDateFormat.format(date * 1000);
        }
    }

    public static String getNoyearTime4(long date) {
        long currentTime = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        boolean isSameYear = sdf.format(currentTime).equals(sdf.format(date * 1000));
        if (isSameYear) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd日 HH:mm");
            Log.e("tiantian", "返回的日期:" + simpleDateFormat.format(date));
            return simpleDateFormat.format(date * 1000);
        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日  HH:mm");
            return simpleDateFormat.format(date * 1000);
        }
    }

    public static String getNoyearTime2(long date) {
        long currentTime = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        boolean isSameYear = sdf.format(currentTime).equals(sdf.format(date * 1000));
        if (isSameYear) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm");
            Log.e("tiantian", "返回的日期:" + simpleDateFormat.format(date));
            return simpleDateFormat.format(date * 1000);
        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return simpleDateFormat.format(date * 1000);
        }
    }

    public static String getYearTime2(long date) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return simpleDateFormat.format(date * 1000);

    }

    public static String getSimilarTime(long date) {
        long currentTime = System.currentTimeMillis();
        long distanceTime = currentTime / 1000 - date;
        if (distanceTime <= 60) {
            return "刚刚";
        } else if (distanceTime < 3600) {
            return distanceTime / 60 + "分钟前";
        } else if (distanceTime < 60 * 60 * 24) {
            boolean sameDate = DateUtils.isSameDate(currentTime / 1000, date);
            if (sameDate) {
                return "今天";
            } else {
                return "昨天";
            }
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            boolean isSameYear = sdf.format(currentTime).equals(sdf.format(date * 1000));
            if (isSameYear) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd日");
                return simpleDateFormat.format(date * 1000);
            } else {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
                return simpleDateFormat.format(date * 1000);
            }

        }
    }

    /**
     * 根据long类型的时间戳，转换为一个String类型的描述性时间
     * 通话记录如果发生在今天：“15：30”
     * 发生在昨天：“昨天8:23”
     * 发生在前天：“前天4:56”
     * 更早：     “2016/04/15”
     *
     * @return
     */
    //timeStample是聊天记录发生的时间
    public static String getTime(long timeStample) {
        //得到现在的时间戳
        long now = System.currentTimeMillis();
        //在java中,int类型的数进行除法运算,只能的整数,正是利用这一点,
        //在下列日期中,只要没过昨天24点,无论相差了1s还是23小时,除法得到的结果都是前一天,
        int day = (int) (now / 1000 / 60 / 60 / 60 - timeStample / 1000 / 60 / 60 / 60);
        switch (day) {
            //如果是0这则说明是今天,显示时间
            case 0:
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                return sdf.format(timeStample);
            //如果是1说明是昨天,显示昨天+时间
            case 1:
                SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
                return "昨天" + sdf1.format(timeStample);
            //如果是1说明是前天,显示前天+时间
            case 2:
                SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
                return "前天" + sdf2.format(timeStample);
            //结果大于2就只显示年月日
            default:
                SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy:MM:dd");
                return sdf3.format(timeStample);
        }
    }

    /*
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String s) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long ts = Long.parseLong((date.getTime() + "").substring(0, 10));
        return String.valueOf(ts);
    }

    public static String dateToTimestamp(String s) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;
        try {
            date = simpleDateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long ts = Long.parseLong((date.getTime() + "").substring(0, 10));
        return String.valueOf(ts);
    }


    /**
     * 判断两个时间戳是不是同一天
     */
    public static boolean isSameDate(long stamp1, long stamp2) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String s1 = sdf.format(new Date(stamp1 * 1000));
        String s2 = sdf.format(new Date(stamp2 * 1000));
        if (s1.equals(s2)) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 计算两个时间戳相差的天数,小时，分钟
     */
    public static Map discrepantDate(long stamp1, long stamp2) {

        Map<String, String> map = new HashMap<>();
        long stamp = Math.abs(stamp1 - stamp2);
        int date = (int) (stamp / (3600 * 24));//计算相差的天数
        map.put("date", date + "");
        long delivery = stamp % (3600 * 24);//小时的时间戳
        int hour = (int) (delivery / 3600);//小时
        map.put("hour", hour + "");
        int minute = ((int) (delivery % 3600)) / 60;//分钟的时间戳
        map.put("minute", minute + "");
        int second = ((int) (delivery % 3600)) % 60;
        map.put("second", second + "");
        return map;
    }

    /**
     * 功能描述：返回年份
     *
     * @return 返回年份
     * @param日期
     */
    public static String getYear() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        String formatDate = sdf.format(date);
        return formatDate;

    }

    /**
     * 功能描述：返回月份
     *
     * @param date Date 日期
     * @return 返回月份
     */
    public static int getMonth(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 功能描述：返回日份
     *
     * @param date Date 日期
     * @return 返回日份
     */
    public static int getDay(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 功能描述：返回小时
     *
     * @param date 日期
     * @return 返回小时
     */
    public static int getHour(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 功能描述：返回分钟
     *
     * @param date 日期
     * @return 返回分钟
     */
    public static int getMinute(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 返回秒钟
     *
     * @param date Date 日期
     * @return 返回秒钟
     */
    public static int getSecond(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }

    /**
     * 功能描述：返回毫秒
     *
     * @param date 日期
     * @return 返回毫秒
     */
    public static long getMillis(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getTimeInMillis();
    }

    /*时间选择器*/
    public static String currentMonth() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");// 格式化对象
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return sdf.format(calendar.getTime());
    }

    /**
     * 获取今天是本周的第几天
     *
     * @return
     */
    public static int getDayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        //获取当前时间为本周的第几天
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        if (day == 1) {
            return 7;
        } else {
            return day - 1;
        }
    }

    /**
     * 获取今天是本月的第几天
     *
     * @return
     */
    public static int getDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        //获取当前时间为本周的第几天
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return day;
    }

    /**
     * 获取本月是本年的第几个月
     *
     * @return
     */
    public static int getMonthOfYear() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int month = c.get(Calendar.MONTH);
        return month + 1;
    }

    /**
     * 获取今天凌晨到现在的时间差
     *
     * @return
     */
    public static long getNowBetweenToday() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date start = calendar.getTime();
        return (System.currentTimeMillis() - start.getTime()) / 1000;
    }

    /**
     * 获取多少天前的起始时间戳
     *
     * @param count
     * @return
     */
    public static long getDayStartTimestamp(int count) {
        long now = System.currentTimeMillis() / 1000;
        return now - getNowBetweenToday() - 24 * 3600 * count;
    }

    /**
     * 获取多少天前的终止时间戳
     *
     * @param count
     * @return
     */
    public static long getDayEndTimestamp(int count) {
        return getDayStartTimestamp(count - 1);
    }

    /**
     * 获取多少周前的起始时间戳
     *
     * @param count
     * @return
     */
    public static long getWeekStartTimestamp(int count) {
        long now = System.currentTimeMillis() / 1000;
        long thisWeekTimestamp = getNowBetweenToday() + 24 * 3600 * (getDayOfWeek() - 1);
        return now - thisWeekTimestamp - 24 * 3600 * 7 * count;
    }

    /**
     * 获取多少周前的终止时间戳
     *
     * @param count
     * @return
     */
    public static long getWeekEndTimestamp(int count) {
        long now = System.currentTimeMillis() / 1000;
        long thisWeekTimestamp = getNowBetweenToday() + 24 * 3600 * (getDayOfWeek() - 1);
        return now - thisWeekTimestamp - 24 * 3600 * 7 * (count - 1);
    }

    /**
     * 获取本月天数
     *
     * @return
     */
    public static int getThisMonthDayCount() {
        int month = getMonthOfYear() - 1;
        int day = 0;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                day = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                day = 30;
                break;
            case 2:
                day = isLeapYear() ? 28 : 29;
        }
        return day;
    }

    /**
     * 判断年份是否为闰年
     * 判断闰年的条件， 能被4整除同时不能被100整除，或者能被400整除
     */
    public static boolean isLeapYear() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        boolean isLeapYear = false;
        if (year % 4 == 0 && year % 100 != 0) {
            isLeapYear = true;
        } else if (year % 400 == 0) {
            isLeapYear = true;
        }
        return isLeapYear;
    }

    /**
     * 获取多少月前的起始时间戳
     *
     * @param count
     * @return
     */
    public static long getMonthStartTimestamp(int count) {
        int day = getThisMonthDayCount();
        return getDayStartTimestamp(getDayOfMonth() - 1 + day * count);
    }

    /**
     * 获取多少月前的终止时间戳
     *
     * @param count
     * @return
     */
    public static long getMonthEndTimestamp(int count) {
        int day = getThisMonthDayCount();
        return getDayStartTimestamp(getDayOfMonth() - 1 + day * (count - 1));
    }

    public static String timestampToDate(long time, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Long t = new Long(time * 1000);
        return format.format(t);
    }

    public static long getYearStartTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.YEAR, Integer.parseInt(DateUtils.getYear()));
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date start = calendar.getTime();
        return start.getTime() / 1000;
    }

    public static final String yyyyMMddHHmm = "yyyy-MM-dd HH:mm";

    public static String currentTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 格式化对象
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return sdf.format(calendar.getTime());
    }

    public static Date parseDate(String dateStr, String type) {
        SimpleDateFormat df = new SimpleDateFormat(type);
        Date date = null;
        try {
            date = df.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;

    }

    public static String dateToString(Date date, String pattern)
            throws Exception {
        return new SimpleDateFormat(pattern).format(date);
    }


    public static int getMonth() {
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    public static int getCurrentMonthDay() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    public static int getWeekDay() {
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    }

    public static int getHour() {
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }

    public static int getMinute() {
        return Calendar.getInstance().get(Calendar.MINUTE);
    }

    @SuppressLint("SimpleDateFormat")
    public static Date getDateFromString(int year, int month) {
        String dateString = year + "-" + (month > 9 ? month : ("0" + month))
                + "-demo01";
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return date;
    }

    // 根据年月日计算年龄,birthTimeString:"1994-11-14"
    public static int getAgeFromBirthTime(String birthTimeString) {
        // 先截取到字符串中的年、月、日
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            Date birthdate = formatter.parse(birthTimeString);
            return getAge(birthdate);
        } catch (Exception e) {
            return 0;
        }
    }

    public static int getAge(Date birthDay) throws Exception {
        // 获取当前系统时间
        Calendar cal = Calendar.getInstance();
        // 如果出生日期大于当前时间，则抛出异常
        if (cal.before(birthDay)) {
            throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
        }
        // 取出系统当前时间的年、月、日部分
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

        // 将日期设置为出生日期
        cal.setTime(birthDay);
        // 取出出生日期的年、月、日部分
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        // 当前年份与出生年份相减，初步计算年龄
        int age = yearNow - yearBirth;
        // 当前月份与出生日期的月份相比，如果月份小于出生月份，则年龄上减1，表示不满多少周岁
        if (monthNow <= monthBirth) {
            // 如果月份相等，在比较日期，如果当前日，小于出生日，也减1，表示不满多少周岁
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth)
                    age--;
            } else {
                age--;
            }
        }
        return age;
    }

}

