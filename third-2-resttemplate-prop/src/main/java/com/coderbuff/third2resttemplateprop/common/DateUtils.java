package com.coderbuff.third2resttemplateprop.common;

/**
 * @Author yuke
 * @Date 2022/7/21 10:40
 * @Description:
 */


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Wood
 */
public class DateUtils {
    private static final int[] DAY_OF_MONTH = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    /**
     * 获取当前月份
     * @return
     */
    public static int getThisMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前月的偏移月 可以为负数
     * @param year
     * @param month
     * @param offset
     * @return
     */
    public static String getSpecificMonthByOffset(int year, int month, int offset) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, 0, 0, 0, 0);
        cal.add(Calendar.MONTH, offset+1);
        return date2String(cal.getTime(), "yyyy-MM");
    }

    /**
     * 获取当前月的上个月
     * @return yyyy-MM格式
     */
    public static String getLastMonth() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, -1);
        return date2String(cal.getTime(), "yyyy-MM");
    }

    /**
     * 取得指定天数后的时间
     *
     * @param date      基准时间
     * @param dayAmount 指定天数，允许为负数
     */
    public static Date addDay(Date date, int dayAmount) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, dayAmount);
        return calendar.getTime();
    }

    /**
     * 取得指定小时数后的时间
     *
     * @param date       基准时间
     * @param hourAmount 指定小时数，允许为负数
     */
    public static Date addHour(Date date, int hourAmount) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, hourAmount);
        return calendar.getTime();
    }

    /**
     * 取得指定分钟数后的时间
     *
     * @param date         基准时间
     * @param minuteAmount 指定分钟数，允许为负数
     */
    public static Date addMinute(Date date, int minuteAmount) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minuteAmount);
        return calendar.getTime();
    }

    /**
     * 比较两日期对象中的小时和分钟部分的大小.
     *
     * @param date        日期对象1, 如果为 null 会以当前时间的日期对象代替
     * @param anotherDate 日期对象2, 如果为 null 会以当前时间的日期对象代替
     *                    如果日期对象1大于日期对象2, 则返回大于0的数; 反之返回小于0的数; 如果两日期对象相等, 则返回0.
     */
    public static int compareHourAndMinute(Date date, Date anotherDate) {
        if (date == null) {
            date = new Date();
        }
        if (anotherDate == null) {
            anotherDate = new Date();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hourOfDay1 = cal.get(Calendar.HOUR_OF_DAY);
        int minute1 = cal.get(Calendar.MINUTE);
        cal.setTime(anotherDate);
        int hourOfDay2 = cal.get(Calendar.HOUR_OF_DAY);
        int minute2 = cal.get(Calendar.MINUTE);
        if (hourOfDay1 > hourOfDay2) {
            return 1;
        } else if (hourOfDay1 == hourOfDay2) {            // 小时相等就比较分钟
            return minute1 > minute2 ? 1 : (minute1 == minute2 ? 0 : -1);
        } else {
            return -1;
        }
    }

    /**
     * 比较两日期对象的大小, 忽略秒, 只精确到分钟.
     *
     * @param date        日期对象1, 如果为 null 会以当前时间的日期对象代替
     * @param anotherDate 日期对象2, 如果为 null 会以当前时间的日期对象代替
     *                    如果日期对象1大于日期对象2, 则返回大于0的数; 反之返回小于0的数; 如果两日期对象相等, 则返回0.
     */
    public static int compareIgnoreSecond(Date date, Date anotherDate) {
        if (date == null) {
            date = new Date();
        }
        if (anotherDate == null) {
            anotherDate = new Date();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        date = cal.getTime();
        cal.setTime(anotherDate);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        anotherDate = cal.getTime();
        return date.compareTo(anotherDate);
    }

    /**
     * 取得当前时间的字符串表示，格式为2006-01-10 20:56:30.756
     */
    public static String currentMonth2String() {
        return date2String(new Date(), "yyyy-MM");
    }

    /**
     * 取得当前时间的字符串表示，格式为2006-01-10 20:56:30.756
     */
    public static String currentYear2String() {
        return date2String(new Date(), "yyyy");
    }

    /**
     * 取得当前时间的字符串表示，格式为2006-01-10 20:56:30.756
     */
    public static String getNextYear2String() {
        int year = Integer.parseInt(currentYear2String());
        return String.valueOf(++year);
    }

    /**
     * 取得当前时间的字符串表示，格式为2006-01-10 20:56:30.756
     */
    public static String currentDate2String() {
        return date2String(new Date());
    }

    /**
     * 把时间转换成字符串，格式为2006-01-10 20:56:30
     */
    public static String currentDateBySec2String() {
        return date2String(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 取得当前时间的字符串表示，格式为2006-01-10
     */
    public static String currentDate2StringByDay() {
        return date2StringByDay(new Date());
    }

    /**
     * 取得今天的最后一个时刻
     * 今天的最后一个时刻
     */
    public static Date currentEndDate() {
        return getEndDate(new Date());
    }

    /**
     * 取得今天的第一个时刻
     * 今天的第一个时刻
     */
    public static Date currentStartDate() {
        return getStartDate(new Date());
    }

    /**
     * 把时间转换成字符串，格式为2006-01-10 20:56:30.756
     */
    public static String date2String(Date date) {
        return date2String(date, "yyyy-MM-dd HH:mm:ss.SSS");
    }

    /**
     * 按照指定格式把时间转换成字符串，格式的写法类似yyyy-MM-dd HH:mm:ss.SSS
     */
    public static String date2String(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        return (new SimpleDateFormat(pattern)).format(date);
    }

    /**
     * 把时间转换成字符串，格式为2006-01
     */
    public static String date2StringByMonth(Date date) {
        return date2String(date, "yyyy-MM");
    }

    /**
     * 把时间转换成字符串，格式为2006-01-10
     */
    public static String date2StringByDay(Date date) {
        return date2String(date, "yyyy-MM-dd");
    }

    /**
     * 把时间转换成字符串，格式为2006-01-10 20:56
     */
    public static String date2StringByMinute(Date date) {
        return date2String(date, "yyyy-MM-dd HH:mm");
    }

    /**
     * 把时间转换成字符串，格式为2006-01-10 20:56:30
     */
    public static String date2StringBySecond(Date date) {
        return date2String(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 根据某星期几的英文名称来获取该星期几的中文数
     */
    public static String getChineseWeekNumber(String englishWeekName) {
        if ("monday".equalsIgnoreCase(englishWeekName)) {
            return "一";
        }
        if ("tuesday".equalsIgnoreCase(englishWeekName)) {
            return "二";
        }
        if ("wednesday".equalsIgnoreCase(englishWeekName)) {
            return "三";
        }
        if ("thursday".equalsIgnoreCase(englishWeekName)) {
            return "四";
        }
        if ("friday".equalsIgnoreCase(englishWeekName)) {
            return "五";
        }
        if ("saturday".equalsIgnoreCase(englishWeekName)) {
            return "六";
        }
        if ("sunday".equalsIgnoreCase(englishWeekName)) {
            return "日";
        }
        return null;
    }

    /**
     * 根据指定的年, 月, 日等参数获取日期对象
     */
    public static Date getDate(int year, int month, int date) {
        return getDate(year, month, date, 0, 0);
    }

    /**
     * 根据指定的年, 月, 日, 时, 分等参数获取日期对象.
     */
    public static Date getDate(int year, int month, int date, int hourOfDay, int minute) {
        return getDate(year, month, date, hourOfDay, minute, 0);
    }

    /**
     * 根据指定的年, 月, 日, 时, 分, 秒等参数获取日期对象.
     */
    public static Date getDate(int year, int month, int date, int hourOfDay, int minute, int second) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, date, hourOfDay, minute, second);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 取得某个日期是星期几，星期日是1，依此类推
     */
    public static int getDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取某天的结束时间, e.g. 2005-10-01 23:59:59.999
     */
    public static Date getEndDate(Date date) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    /**
     * 取得一个月最多的天数 0表示1月，依此类推
     */
    public static int getMaxDayOfMonth(int year, int month) {
        if (month == 1 && isLeapYear(year)) {
            return 29;
        }
        return DAY_OF_MONTH[month];
    }

    /**
     * 得到指定日期的下一天
     */
    public static Date getNextDay(Date date) {
        return addDay(date, 1);
    }

    /**
     * 获取某天的起始时间, e.g. 2005-10-01 00:00:00.000
     */
    public static Date getStartDate(Date date) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 根据日期对象来获取日期中的时间(HH:mm:ss).
     */
    public static String getTime(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        return format.format(date);
    }

    /**
     * 根据日期对象来获取日期中的时间(HH:mm).
     */
    public static String getTimeIgnoreSecond(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(date);
    }

    /**
     * 判断是否是闰年
     */
    public static boolean isLeapYear(int year) {
        Calendar calendar = Calendar.getInstance();
        return ((GregorianCalendar) calendar).isLeapYear(year);
    }

    public static boolean isSameMonth(Date date1, Date date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        int year1 = calendar1.get(Calendar.YEAR);
        int year2 = calendar2.get(Calendar.YEAR);
        int month1 = calendar1.get(Calendar.MONTH);
        int month2 = calendar2.get(Calendar.MONTH);
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) && calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH);
    }

    /**
     * 把字符串转换成日期，格式为2006-01-10
     */
    public static Date string2Date(String str) {
        return string2Date(str, "yyyy-MM-dd");
    }

    /**
     * 按照指定的格式把字符串转换成时间，格式的写法类似yyyy-MM-dd HH:mm:ss.SSS
     */
    public static Date string2Date(String str, String pattern) {
        if (ValidateUtils.isEmpty(str)) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = dateFormat.parse(str);
        } catch (
                ParseException e) {            // ignore
        }
        return date;
    }

    /**
     * 按照指定的格式把字符串转换成时间，格式的写法类似yyyy-MM-dd HH:mm:ss.SSS
     */
    public static Date string2DateWithException(String str, String pattern) throws ParseException {
        if (ValidateUtils.isEmpty(str)) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = null;
        date = dateFormat.parse(str);
        return date;
    }

    /**
     * 把字符串转换成日期，格式为2006-01-10 20:56:30
     */
    public static Date string2DateTime(String str) {
        return string2Date(str, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 取得一年中的第几周
     */
    public static int getWeekOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    private DateUtils() {
    }

    /**
     * 获取任意时间(必须小于28号)的下一个月的同一天日期
     */
    public static Date getNextMonthDate(Date date) {
        String dateStr = date2StringByDay(date);
        Integer thisDayYear = Integer.parseInt(dateStr.substring(0, 4));
        Integer thisDayMonth = Integer.parseInt(dateStr.substring(5, 7));
        Integer thisDayDay = Integer.parseInt(dateStr.substring(8, 10));
        if (thisDayDay > 28) {
            return null;
        } else {
            if (thisDayMonth == 12) {
                ++thisDayYear;
                thisDayMonth = 1;
            } else {
                ++thisDayMonth;
            }
            return getDate(thisDayYear, thisDayMonth, thisDayDay);
        }
    }

    /**
     * 获取昨天日期
     */
    public static String getYesterdyDateStr() {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(currentStartDate());
        calendar.add(calendar.DATE, -1);
        return date2StringByDay(calendar.getTime());
    }

    /**
     * 获取昨天第一个时刻
     */
    public static Date getYesterdyStartTime() {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(currentStartDate());
        calendar.add(calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * 获取当前日期所在月的最后一天
     */
    public static Date getLastDayOfMonthByDate(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        return calendar.getTime();
    }

    /**
     * 获取上月的最后一天
     */
    public static Date getLastDayOfMonthByDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        return calendar.getTime();
    }

    /**
     * 获取指定日期所在月的最后一天中午12点
     */
    public static Date getLastSpecificHourOfMonthByDate(Date date, int day, int hour) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取本月周期内的指定日期
     */
    public static Date getThisBillDate(Date date, short billDay) {
        if ((!ValidateUtils.isEmpty(date))) {
            String completeDateStr = DateUtils.date2StringByDay(date);
            Integer year = Integer.parseInt(completeDateStr.substring(0, 4));
            Integer month = Integer.parseInt(completeDateStr.substring(5, 7));
            Integer day = Integer.parseInt(completeDateStr.substring(8, 10));
            if (day < billDay) {
                if (month == 1) {
                    --year;
                    month = 12;
                } else {
                    --month;
                }
            }
            return DateUtils.getDate(year, month, billDay);
        } else {
            return date;
        }
    }

    /**
     * 获取下月周期内的指定日期
     */
    public static Date getNextBillDate(Date date, short billDate) {
        if ((!ValidateUtils.isEmpty(date))) {
            String dateStr = DateUtils.date2StringByDay(date);
            int year = Integer.parseInt(dateStr.substring(0, 4));
            short month = Short.parseShort(dateStr.substring(5, 7));
            short day = Short.parseShort(dateStr.substring(8, 10));
            int preBillDateYear = year;
            int preBillDateMonth = month;
            if (day >= billDate) {
                if (month == 12) {
                    preBillDateYear = year + 1;
                    preBillDateMonth = 1;
                } else {
                    preBillDateMonth = month + 1;
                }
            }            //后一个账单日
            return DateUtils.getDate(preBillDateYear, preBillDateMonth, billDate);
        } else {
            return date;
        }
    }

    /**
     * 获取date2到date1的天数
     */
    public static int differentDaysByMillisecond(Date date1, Date date2) {
        return (int) ((date1.getTime() - date2.getTime()) / (1000 * 3600 * 24));
    }

    public static void main(String[] args) {
        int thisMonth = getThisMonth();
        String lastMonth = getLastMonth();
        String specificMonthByOffset = getSpecificMonthByOffset(2022, 7, -7);
        System.out.println(thisMonth);
        System.out.println(lastMonth);
        System.out.println(specificMonthByOffset);
    }

    /**
     * 获取当前业务日期（实际日期的前一天），主要用于离线任务使用
     */
    public static Date currentBizDate() {
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        cal.clear();
        cal.set(year, month, day);
        return addDay(cal.getTime(), -1);
    }

    /**
     * 获取当前业务日期字符串形式
     */
    public static String currentBizDateStr() {
        return date2StringByDay(currentBizDate());
    }

    /**
     * 获取最小的日期
     */
    public static Date min(Date a, Date b) {
        if (ValidateUtils.isEmpty(a) || ValidateUtils.isEmpty(b)) {
            return null;
        }
        return a.before(b) ? a : b;
    }

}