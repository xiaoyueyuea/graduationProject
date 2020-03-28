package com.yuelei.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    public final static String FORMAT_YEAR = "yyyy";

    public final static String FORMAT_MONTH = "yyyy-MM";

    public final static String FORMAT_DATE = "yyyy-MM-dd";

    public final static String FORMAT_HOUR = "yyyy-MM-dd HH";

    public final static String FORMAT_MINUTE = "yyyy-MM-dd HH:mm";

    public final static String FORMAT_SECOND = "yyyy-MM-dd HH:mm:ss";

    public final static String FORMAT_MILLISECOND = "yyyy-MM-dd HH:mm:ss SSS";

    public final static int FIELD_YEAR = Calendar.YEAR;

    public final static int FIELD_MONTH = Calendar.MONTH;

    public final static int FIELD_DATE = Calendar.DATE;

    public final static int FIELD_HOUR = Calendar.HOUR;

    public final static int FIELD_MINUTE = Calendar.MINUTE;

    public final static int FIELD_SECOND = Calendar.SECOND;

    public final static int FIELD_MILLISECOND = Calendar.MILLISECOND;

    /**
     * date1.compareTo(date2);
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int compare(Date date1, Date date2) {
        return date1.compareTo(date2);
    }

    /**
     *
     * @param field
     * @param date1
     * @param date2
     * @return
     */
    public static int compare(int field, Date date1, Date date2) {
        final Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        final Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        final Calendar c1 = Calendar.getInstance();
        c1.clear();
        final Calendar c2 = Calendar.getInstance();
        c2.clear();
        switch (field) {
            case FIELD_YEAR: {
                c1.set(calendar1.get(FIELD_YEAR), 0, 0);
                c2.set(calendar2.get(FIELD_YEAR), 0, 0);
                return c1.compareTo(c2);
            }
            case FIELD_MONTH: {
                c1.set(calendar1.get(FIELD_YEAR), calendar1.get(FIELD_MONTH), 0);
                c2.set(calendar2.get(FIELD_YEAR), calendar2.get(FIELD_MONTH), 0);
                return c1.compareTo(c2);
            }
            case FIELD_DATE: {
                c1.set(calendar1.get(FIELD_YEAR), calendar1.get(FIELD_MONTH), calendar1.get(FIELD_DATE));
                c2.set(calendar2.get(FIELD_YEAR), calendar2.get(FIELD_MONTH), calendar2.get(FIELD_DATE));
                return c1.compareTo(c2);
            }
            case FIELD_HOUR: {
                c1.set(calendar1.get(FIELD_YEAR), calendar1.get(FIELD_MONTH), calendar1.get(FIELD_DATE), calendar1.get(FIELD_HOUR), 0, 0);
                c2.set(calendar2.get(FIELD_YEAR), calendar2.get(FIELD_MONTH), calendar2.get(FIELD_DATE), calendar2.get(FIELD_HOUR), 0, 0);
                return c1.compareTo(c2);
            }
            case FIELD_MINUTE: {
                c1.set(calendar1.get(FIELD_YEAR), calendar1.get(FIELD_MONTH), calendar1.get(FIELD_DATE), calendar1.get(FIELD_HOUR), calendar1.get(FIELD_MINUTE), 0);
                c2.set(calendar2.get(FIELD_YEAR), calendar2.get(FIELD_MONTH), calendar2.get(FIELD_DATE), calendar2.get(FIELD_HOUR), calendar2.get(FIELD_MINUTE), 0);
                return c1.compareTo(c2);
            }
            case FIELD_SECOND: {
                c1.set(calendar1.get(FIELD_YEAR), calendar1.get(FIELD_MONTH), calendar1.get(FIELD_DATE), calendar1.get(FIELD_HOUR), calendar1.get(FIELD_MINUTE), calendar1.get(FIELD_SECOND));
                c2.set(calendar2.get(FIELD_YEAR), calendar2.get(FIELD_MONTH), calendar2.get(FIELD_DATE), calendar2.get(FIELD_HOUR), calendar2.get(FIELD_MINUTE), calendar2.get(FIELD_SECOND));
                return c1.compareTo(c2);
            }
            case FIELD_MILLISECOND: {
                return calendar1.compareTo(calendar2);
            }
            default: {
                return calendar1.compareTo(calendar2);
            }
        }
    }

    /**
     * 取得该日期当月的最后一天。
     *
     * @param date
     * @return
     */
    public static Date getLastDateOfMonth(Date date) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     *
     * @param pattern
     * @param date
     * @return
     */
    public static String format(String pattern, Date date) {
        return format(pattern, date, Locale.getDefault(Locale.Category.FORMAT));
    }

    public static String format(String pattern, Date date, Locale locale) {
        return new SimpleDateFormat(pattern, locale).format(date);
    }

    /**
     *
     * 例子：DateUtil.offset(FIELD_DATE,new Date(),10); //当前时间加10天。
     * <p>
     * 例子：DateUtil.offset(FIELD_HOUR,new Date(),10); //当前时间加10小时。
     *
     * @param base
     * @param field
     * @param offset
     * @return
     */
    public static Date offset(int field, Date base, int offset) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(base);
        calendar.add(field, offset);
        return calendar.getTime();
    }
}

