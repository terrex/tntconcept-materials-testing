package com.autentia.intra.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    private static final Calendar calendar = Calendar.getInstance();


    /**
     * returns a java.util.Date with hours and minutes passed
     * as argument. rest of date fields are despreciable.
     *
     * @param hour    hour to set
     * @param minutes minute to set
     * @return date with hour and minutes setted.
     */
    public static Date timeToDate(int hour, int minutes) {

        calendar.clear();

        calendar.set(Calendar.HOUR, hour);
        calendar.set(Calendar.MINUTE, minutes);

        return calendar.getTime();
    }


    /**
     * Return a java.util.Date with hours and minutes minimum in a Day
     *
     * @param d
     * @return
     */

    public static Date minHourInDate(Date d) {
        calendar.clear();
        calendar.setTime(d);

        calendar.set(Calendar.HOUR_OF_DAY, calendar.getMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getMinimum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getMinimum(Calendar.MILLISECOND));


        return calendar.getTime();

    }


    public static Date addDays2Date(Date d, int days) {
        calendar.clear();
        calendar.setTime(d);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }


    /**
     * Return a java.util.Date with hours and minutes minimum in a Day
     *
     * @param d
     * @return
     */

    public static Date maxHourInDate(Date d) {
        calendar.clear();
        calendar.setTime(d);

        calendar.set(Calendar.HOUR_OF_DAY, calendar.getMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getMaximum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getMaximum(Calendar.MILLISECOND));


        return calendar.getTime();

    }
}
