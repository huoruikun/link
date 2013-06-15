package com.link.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class DateUtils {

	private static Log logger = LogFactory.getLog(DateUtils.class);
	public static final String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

	public static String getDateChStr() {
		Calendar calendar = new GregorianCalendar();
		return calendar.get(Calendar.YEAR) + "年"
				+ (calendar.get(Calendar.MONTH) + 1) + "月"
				+ calendar.get(Calendar.DAY_OF_MONTH) + "日";
	}

	public static String getDateStr() {
		Calendar calendar = new GregorianCalendar();
		int mon = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		return (calendar.get(Calendar.YEAR) + "-"
				+ (mon <= 9 ? ("0" + mon) : mon) + "-" + (day <= 9 ? ("0" + day)
				: day));
	}

	public static long paseDateLong(String date, String pattern) {
		long result = -1;
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			result = format.parse(date).getTime();
		} catch (ParseException e) {
			logger.error("", e);
		}
		return result;
	}

	public static String formatToStr(long date, String pattern) {
		Date d = new Date(date);
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(d);
	}

	public static String getPreDay() {
		Date d = new Date(System.currentTimeMillis() - 24 * 2600 * 1000);
		SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATETIME_PATTERN);
		return format.format(d);
	}

	
	public static StringBuffer getDayInterval(String startDate, String endDate) {
		StringBuffer dayInterval = new StringBuffer();
		dayInterval.append(startDate);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date sDate = null;
		Date eDate = null;
		try {
			sDate = sdf.parse(startDate);
			eDate = sdf.parse(endDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		Calendar calendar = new GregorianCalendar();
		calendar.setTime(sDate);
		for (int i = 1; calendar.getTime().before(eDate);) {
			dayInterval.append("," + sdf.format(calendar.getTime()));
			calendar.set(calendar.get(Calendar.YEAR), calendar
					.get(Calendar.MONTH), calendar.get(Calendar.DATE) + 1);
		}
		return dayInterval;
	}

	/**
	 * 得到当前日期
	 * 
	 * @param format
	 *            默认 yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static final String getDate(String format) {
		if (format.equals("")) {
			format = DEFAULT_DATETIME_PATTERN;
		}
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String cdate = sdf.format(cal.getTime());
		return cdate;
	}

	public static final String getDate(long time, String format) {
		if (format.equals("")) {
			format = DEFAULT_DATETIME_PATTERN;
		}
		Date d = new Date(time);
		SimpleDateFormat fm = new SimpleDateFormat(format);
		return fm.format(d);
	}

	public static final String getDate(Date d, String format) {
		if (format.equals("")) {
			format = DEFAULT_DATETIME_PATTERN;
		}
		if (d == null) {
			d = new Date(System.currentTimeMillis());
		}
		SimpleDateFormat fm = new SimpleDateFormat(format);
		return fm.format(d);
	}

	public static int getBetweenMinNumber(String dateA, String dateB) {
		long minNumber = 0;
		long mins = 60L * 1000L;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			java.util.Date d1 = df.parse(dateA);
			java.util.Date d2 = df.parse(dateB);
			minNumber = (d2.getTime() - d1.getTime()) / mins;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (int) minNumber;
	}

	/**
	 * 判断一天是否为周末
	 * 
	 * @param sDate
	 * @return
	 */
	public static boolean isWeekEnd(String sDate) {
		if ("".equals(sDate)) {
			return false;
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		Date date = null;
		try {
			date = df.parse(sDate);
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}

		Calendar cd = Calendar.getInstance();
		cd.setTime(date);
		int mydate = cd.get(Calendar.DAY_OF_WEEK);

		if (mydate == 1 || mydate == 7)
			return true;
		else
			return false;
	}

	/**
	 * 取两个时间间隔的分钟数（只计算同一天）
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static int getMinutesNumber(Date date1, Date date2) {
		int min1 = date1.getHours() * 60 + date1.getMinutes();
		int min2 = date2.getHours() * 60 + date2.getMinutes();
		return min2 - min1;
	}

	public static int getMinutesNumberNew(Date date1, Date date2) {
		return (int) (date2.getTime() - date1.getTime()) / 60000;
	}

	/**
	 * 解析时间
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static Date getDateByStr(String date, String format) {
		if (StringUtils.isEmpty(date))
			return new Date();

		if (StringUtils.isEmpty(format))
			format = DEFAULT_DATETIME_PATTERN;

		DateFormat format1 = new SimpleDateFormat(format);
		try {
			return format1.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date();
	}

	public static String getDateString(Date date) {
		if (date == null)
			date = new Date();

		SimpleDateFormat fm = new SimpleDateFormat(DEFAULT_DATETIME_PATTERN);
		return fm.format(date);
	}
	
	public static Date getTomorrow(){
		return getDate(1);
	}
	
	// 获取next天后的日期，如果next为负数，则表示几天前的date
	public static Date getDate(int next) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, next);
		return calendar.getTime();
	}
	
	public static Date getToday() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	public static long todayLeftTimes() {
		Date now = new Date();
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		System.out.println(calendar.getTime());
		
		return calendar.getTimeInMillis() - now.getTime();
	}
	
	public static void main(String[] args) {
		long seconds = todayLeftTimes();
		
		System.out.println(seconds / 1000 / 60);
	}

}
