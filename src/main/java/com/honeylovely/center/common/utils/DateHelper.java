package com.honeylovely.center.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ryan on 1/13/16.
 */
public class DateHelper {
    private static final ThreadLocal<DateFormat> dateFormat = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    private static final ThreadLocal<DateFormat> dateTimeFormat = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };
    
    private static final ThreadLocal<DateFormat> displayFormat = new ThreadLocal<DateFormat>() {
    	@Override
    	protected DateFormat initialValue() {
    		return new SimpleDateFormat("yyyy-MM-dd HH:mm");
    	}
    };

    private static final ThreadLocal<DateFormat> timeFormat = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("HH:mm:ss");
        }
    };
    
    private static final ThreadLocal<DateFormat> yearMonthFormat = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyyMM");
        }
    };

    public static String format(Date date) {
        return dateFormat.get().format(date);
    }

    public static Date parse(String str) throws ParseException {
        return dateFormat.get().parse(str);
    }
    
    public static String formatYearMonth(Date date) {
        return yearMonthFormat.get().format(date);
    }

    public static Date parseYearMonth(String str) throws ParseException {
        return yearMonthFormat.get().parse(str);
    }

    public static Date toDate(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

    public static Date parseDateTime(String str) throws ParseException {
        return dateTimeFormat.get().parse(str);
    }

    public static String formatDatetime(Date date) {
        return dateTimeFormat.get().format(date);
    }
    
    public static Date parseDisplay(String str) throws ParseException {
        return displayFormat.get().parse(str);
    }

    public static String formatDisplay(Date date) {
        return displayFormat.get().format(date);
    }

    public static String formatTime(Date date) {
        return timeFormat.get().format(date);
    }

    public static int getDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * diff days between 2 dates
     * @param endDate
     * @param startDate
     * @return
     */
    public static int dateDiff(Date endDate, Date startDate) {
        return (int) ((endDate.getTime() - startDate.getTime()) / 86400000);
    }

    /**
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean sameDay(Date date1, Date date2) {
        return org.apache.commons.lang3.time.DateUtils.isSameDay(date1, date2);
    }

    public static double hourDiff(Date date1, Date date2) {
        return (date1.getTime() - date2.getTime()) / 3600000.0;
    }

    public static double minuteDiff(Date date1, Date date2) {
        return (date1.getTime() - date2.getTime()) / 60000.0;
    }

    /**
     * date is apart of
     * @param time
     * @param date
     * @return
     */
    public static Date changeDateTime(Date time, Date date) {
        Calendar calendar = Calendar.getInstance();
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(time);
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, calendar1.get(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar1.get(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar1.get(Calendar.SECOND));

        return calendar.getTime();
    }

    /**
     * add days to the date
     * @param date
     * @param days
     * @return
     */
    public static Date dateAddDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }

    /**
     * make date time as 00:00:00
     * @param date
     * @return
     */
    public static Date zeroDatetime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        return calendar.getTime();
    }

    /**
     *
     * @return
     */
    public static Date monthFirstDay() {
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        return calendar.getTime();
    }

	/**
	 * 转换为时分秒
	 */
	public static String TimeToHMS(Long time){
		//long maxtime = Math.max((System.currentTimeMillis() - time) / 1000, 0);
		String result = "";
		long min = 0;
		long hour = 0;
		long day = 0;
		if(time % 60 < 1){
			result = time+"秒";
		}else if(time/60 % 60 < 1){
			min = time/60;
			result = min + "分" + (time - min*60) +"秒";
		}else if(time/3600 < 24){
			hour = time/3600;
			min = (time - hour*3600) / 60;
			result = hour + "小时" + min+"分" + (time - hour*3600 - min*60) + "秒";
		}else{
			day = time/86400;
			hour = (time - day*86400) / 3600;
			min = (time - day*86400 - hour*3600) / 60;
			result = day + "天" + hour+"小时" + min+"分" + (time -day*86400 - hour*3600 - min*60)+"秒";
		}
		return result;
	}
}
