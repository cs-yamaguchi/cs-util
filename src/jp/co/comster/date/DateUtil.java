package jp.co.comster.date;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日付操作用ユーティリティ
 *
 * @author COMSTER）山口　英志
 * @version 1.0 ( 2007/11/08 )
 *
 */
public class DateUtil {

	private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat ("yyyy/MM/dd HH:mm:ss");
//	private static final SimpleDateFormat STANDARD_DATE_FORMAT = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
	private static final SimpleDateFormat JAPAN_STANDARD_DATE_FORMAT = new SimpleDateFormat ("yyyy年M月d日");
	private static final SimpleDateFormat JAPAN_STANDARD_DATE_FORMAT_YOUBI = new SimpleDateFormat ("yyyy年M月d日(E)");
	private static final SimpleDateFormat YYYYMMDD = new SimpleDateFormat ("yyyyMMdd");
	private static final SimpleDateFormat YYYYMMDDHHMMSS = new SimpleDateFormat ("yyyyMMddHHmmss");
	private static final SimpleDateFormat DATE_YEAR_FORMAT = new SimpleDateFormat ("yyyy");
	private static final SimpleDateFormat DATE_MONTH_FORMAT = new SimpleDateFormat ("M");
	private static final SimpleDateFormat DATE_DAY_FORMAT = new SimpleDateFormat ("d");
	private static final String INITIAL_DATE_TIME_ZERO = "0000-00-00 00:00:00";
	private static final String INITIAL_DATE_TIME_ONE = "1000-01-01 00:00:00";
	private static final String INITIAL_DATE_ONE = "1000-01-01";

	/**
	 * 日付インスタンスがセットされているオブジェクト型から日付型を抽出します。
	 * @param object
	 * @return
	 */
	public static java.util.Date objectToDate(Object object) {
		if (object == null)
			return null;
		if (object instanceof String) {
			if (((String)object).equals(INITIAL_DATE_TIME_ZERO))
				return null;
			if (((String)object).equals(INITIAL_DATE_TIME_ONE))
				return null;
			if (((String)object).equals(INITIAL_DATE_ONE))
				return null;
			return stringToDate(((String)object).replaceAll("-", "/"));
		}
		if (object instanceof java.sql.Date)
			return (java.sql.Date)object;
		if (object instanceof java.sql.Timestamp)
			return (java.sql.Timestamp)object;
		if (object instanceof java.util.Date)
			return (java.util.Date)object;
		return null;
	}

	/**
	 * 日付インスタンスがセットされているオブジェクト型から日付型を抽出し、
	 * yyyy/MM/dd HH:mm:ssに整形して返します。
	 * @param object
	 * @return
	 */
	public static String objectToString(Object object) {
		return dateToString(objectToDate(object));
	}

	/**
	 * 日付型からyyyy/MM/dd HH:mm:ssに整形して返します。
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date) {
		if (date == null)
			return "";
		return SIMPLE_DATE_FORMAT.format(date);
	}

	/**
	 * 日付型からyyyyMMddに整形して返します。
	 * @param date
	 * @return
	 */
	public static String dateToYYYYMMDD(Date date) {
		if (date == null)
			return "";
		return YYYYMMDD.format(date);
	}

	/**
	 * 日付型からyyyyMMddHHmmssに整形して返します。
	 * @param date
	 * @return
	 */
	public static String dateToYYYYMMDDHHMMSS(Date date) {
		if (date == null)
			return "";
		return YYYYMMDDHHMMSS.format(date);
	}

	/**
	 * 日付型からyyyy年M月d日に整形して返します。
	 * @param object
	 * @return
	 */
	public static String dateToNenGappiString(Date date) {
		if (date == null)
			return "";
		return JAPAN_STANDARD_DATE_FORMAT.format(date);
	}

	/**
	 * 日付型からyyyy年M月d日(E)に整形して返します。
	 * @param object
	 * @return
	 */
	public static String dateToNenGappiYoubiString(Date date) {
		if (date == null)
			return "";
		return JAPAN_STANDARD_DATE_FORMAT_YOUBI.format(date);
	}


	/**
	 * 年・月から月初日を返します。
	 * @param year 年
	 * @param month 月
	 * @return
	 */
	public static java.sql.Date toSqlDate(String year, String month) {
		return java.sql.Date.valueOf(year + "-" + month + "-01");
	}

	/**
	 * 年・月から月初日を返します。
	 * 規定日がセットされている場合は規定日で返します。
	 * @param year 年
	 * @param month 月
	 * @param monthlessDefault 規定日
	 * @return
	 */
    public static java.sql.Date toSqlDate(String year, String month, String monthlessDefault) {
		if(month == null || month.equals(""))
			return java.sql.Date.valueOf(year + monthlessDefault);
		return toSqlDate(year, month);
	}

    /**
     * Object → java.sql.Date へ変換します。
     * @param object
     * @return
     */
	public static java.sql.Date objectToSqlDate(Object object) {
		final Date objectToDate = objectToDate(object);
		if (objectToDate == null)
			return null;
		return new java.sql.Date(objectToDate.getTime());
	}

	/**
	 * Timestamp → java.sql.Date へ変換します。
	 * @param timestamp
	 * @return
	 */
	public static java.sql.Date timestampToSqlDate(Timestamp timestamp) {
		if (timestamp == null)
			return null;
		return new java.sql.Date(timestamp.getTime());
	}

	/**
	 * 年のみを取得します。
	 * @param date
	 * @return
	 */
	public static String getYear(java.sql.Date date) {
		if (date == null)
			return null;
		return DATE_YEAR_FORMAT.format(date);
	}

	/**
	 * 月のみを取得します。
	 * @param date
	 * @return
	 */
	public static String getMonth(java.sql.Date date) {
		if (date == null)
			return null;
		return DATE_MONTH_FORMAT.format(date);
	}

	/**
	 * 日付のみを取得します。
	 * @param date
	 * @return
	 */
	public static String getDay(java.sql.Date date) {
		if (date == null)
			return null;
		return DATE_DAY_FORMAT.format(date);
	}

	/**
	 * 時分秒ミリを最小にセットします。
	 * @param date
	 * @return
	 */
	public static java.sql.Date getStartDate(java.sql.Date date) {
		final Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(date.getTime());
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return new java.sql.Date(cal.getTimeInMillis());
	}

	/**
	 * 時分秒ミリを最大にセットします。
	 * @param date
	 * @return
	 */
	public static java.sql.Date getEndDate(java.sql.Date date) {
		final Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(date.getTime());
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return new java.sql.Date(cal.getTimeInMillis());
	}

	/**
	 * 時分秒ミリを最小にセットします。
	 * @param date
	 * @return
	 */
	public static java.util.Date getStartDate(java.util.Date date) {
		final Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(date.getTime());
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return new java.util.Date(cal.getTimeInMillis());
	}

	/**
	 * 時分秒ミリを最大にセットします。
	 * @param date
	 * @return
	 */
	public static java.util.Date getEndDate(java.util.Date date) {
		final Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(date.getTime());
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return new java.util.Date(cal.getTimeInMillis());
	}

	/**
	 * 月初日を取得します。
	 * @param date
	 * @return
	 */
	public static java.util.Date getMonthFirstDate(java.util.Date date) {
		final Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(date.getTime());
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 月末日を取得します。
	 * @param date
	 * @return
	 */
	public static java.util.Date getMonthLastDate(java.util.Date date) {
		final Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(date.getTime());
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DATE, - 1);
		return cal.getTime();
	}

	public static java.sql.Date stringToSQLDate(String string) {
		try {
			return new java.sql.Date(DateFormat.getDateInstance().parse(string).getTime());
		} catch (ParseException e) {
			return null;
		}
	}

	public static java.util.Date stringToDate(String string) {
		try {
			return new java.util.Date(DateFormat.getDateTimeInstance().parse(string).getTime());
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 文字列が日付として評価できるかを判別します。
	 * @param string
	 * @return
	 */
	public static boolean isDate(String string) {
		try {
			DateFormat.getDateInstance().parse(string);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	/**
	 * 誕生日から年齢を算出します。
	 *
	 * 計算式 : (システム日付(yyyyMMdd) - 誕生日(yyyyMMdd)) / 10000
	 *
	 * @param birthDay 誕生日
	 * @return 年齢
	 */
	 public static Integer getAge(Date birthDay) {
		 if (birthDay == null)
			 return null;
		 final Calendar systemDateCalender = Calendar.getInstance();
		 final Calendar birthDayCalender = Calendar.getInstance();
		 birthDayCalender.setTime(birthDay);
		 final DecimalFormat monthDateFormat = new DecimalFormat("00");
		 int systemDateYear = Integer.parseInt(
				 Integer.toString(systemDateCalender.get(Calendar.YEAR)) +
				 monthDateFormat.format(systemDateCalender.get(Calendar.MONTH) + 1) +
				 monthDateFormat.format(systemDateCalender.get(Calendar.DATE)));

		 int birthDayYear = Integer.parseInt(
				 Integer.toString(birthDayCalender.get(Calendar.YEAR)) +
				 monthDateFormat.format(birthDayCalender.get(Calendar.MONTH) + 1) +
				 monthDateFormat.format(birthDayCalender.get(Calendar.DATE)));
		 return new Integer((systemDateYear - birthDayYear) / 10000);
	 }

	 /**
	  * スラッシュが付いていない文字の日付にスラッシュを付加します。
	  * @param date
	  * @return
	  */
	 public static String getWithSlash(String date) {
		 final String SLASH = "/";
		 if (date == null)
			 return null;
		 if (date.length() == 6)
			 return date.substring(0,2) + SLASH + date.substring(2,4) + SLASH + date.substring(4,6);
		 if (date.length() == 8)
			 return date.substring(0,4) + SLASH + date.substring(4,6) + SLASH + date.substring(6,8);
		 return date;
	 }

	/**
	 * 日付として評価できる場合、日付型を返します。
	 * @param date
	 * @return
	 */
	public static Date getDateFromYMD(String date) {
		if (date == null)
			return null;
		final String tmporaryDate = (date.length() == 6)? "20" + date : date;
		if (isDate(getWithSlash(tmporaryDate)))
			return stringToSQLDate(getWithSlash(tmporaryDate));
		return null;
	}

	/**
	 * 指定した曜日のシステム時刻より次の日付を取得する。
	 * @param day
	 * @param addWeek
	 * @return
	 */
	public static Date getNextDay(int day, int addWeek) {
		if (day < 1 || 7 < day)
			throw new RuntimeException("1 < day > 7");
		final Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		if (calendar.get(Calendar.DAY_OF_WEEK) == day)
			calendar.add(Calendar.DATE, 7);
		else
			while (calendar.get(Calendar.DAY_OF_WEEK) != day)
				calendar.add(Calendar.DATE, 1);
		if (addWeek > 0)
			calendar.add(Calendar.DATE, addWeek * 7);
		return calendar.getTime();
	}

	/**
	 * 日付Aと日付Bが同一日であるか判別します。
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean isSameDate(Date a, Date b) {
		if (a == null && b == null)
			return true;
		if (a == null && b != null)
			return false;
		if (a != null && b == null)
			return false;
		final Calendar calendarA = Calendar.getInstance();
		final Calendar calendarB = Calendar.getInstance();
		calendarA.setTime(a);
		calendarB.setTime(b);
		if (calendarA.get(Calendar.YEAR) != calendarB.get(Calendar.YEAR))
			return false;
		if (calendarA.get(Calendar.MONTH) != calendarB.get(Calendar.MONTH))
			return false;
		if (calendarA.get(Calendar.DATE) != calendarB.get(Calendar.DATE))
			return false;
		return true;
	}

}
