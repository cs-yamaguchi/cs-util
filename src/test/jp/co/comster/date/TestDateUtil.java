package test.jp.co.comster.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import jp.co.comster.date.DateUtil;
import junit.framework.TestCase;

public class TestDateUtil extends TestCase {

	public TestDateUtil(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testObjectToDate() {

		final Date nullDate = DateUtil.objectToDate(null);
		assertNull(nullDate);

		final Date stringZeroDateTime = DateUtil.objectToDate("0000-00-00 00:00:00");
		assertNull(stringZeroDateTime);

		final Date stringCenturyDateTime = DateUtil.objectToDate("1000-01-01 00:00:00");
		assertNull(stringCenturyDateTime);

		final Date stringCenturyDate = DateUtil.objectToDate("1000-01-01");
		assertNull(stringCenturyDate);

		final Date stringTwoCenturyDate = DateUtil.objectToDate("2008/04/16 00:00:00");
		assertNotNull(stringTwoCenturyDate);

		final Date javaSqlDate = DateUtil.objectToDate(new java.sql.Date(System.currentTimeMillis()));
		assertNotNull(javaSqlDate);

		final Date javaSqlTimestamp = DateUtil.objectToDate(new java.sql.Timestamp(System.currentTimeMillis()));
		assertNotNull(javaSqlTimestamp);

		final Date javaUtilDate = DateUtil.objectToDate(new Date());
		assertNotNull(javaUtilDate);

		final Date object = DateUtil.objectToDate(new Object());
		assertNull(object);

	}

	public void testObjectToString() {
		final String emptyString = DateUtil.objectToString(null);
		assertEquals(emptyString, "");

		final String emptyStringByInteger = DateUtil.objectToString(new Integer(1));
		assertEquals(emptyStringByInteger, "");


		final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat ("yyyy/MM/dd HH:mm:ss");
		final Date date = new Date();
		final String stringDate = DateUtil.objectToString(date);
		assertEquals(stringDate, SIMPLE_DATE_FORMAT.format(date));

	}

	public void testDateToString() {
		final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat ("yyyy/MM/dd HH:mm:ss");
		final Date date = new Date();
		final String stringDate = DateUtil.dateToString(date);
		assertEquals(stringDate, SIMPLE_DATE_FORMAT.format(date));
	}

	public void testDateToYYYYMMDD() {
		final String emptyString = DateUtil.dateToYYYYMMDD(null);
		assertEquals(emptyString, "");

		final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat ("yyyyMMdd");
		final Date date = new Date();
		final String stringDate = DateUtil.dateToYYYYMMDD(date);
		assertEquals(stringDate, SIMPLE_DATE_FORMAT.format(date));
	}

	public void testDateToYYYYMMDDHHMMSS() {
		final String emptyString = DateUtil.dateToYYYYMMDDHHMMSS(null);
		assertEquals(emptyString, "");

		final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat ("yyyyMMddHHmmss");
		final Date date = new Date();
		final String stringDate = DateUtil.dateToYYYYMMDDHHMMSS(date);
		assertEquals(stringDate, SIMPLE_DATE_FORMAT.format(date));
	}

	public void testDateToNenGappiString() {
		final String emptyString = DateUtil.dateToNenGappiString(null);
		assertEquals(emptyString, "");

		final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat ("yyyy年M月d日");
		final Date date = new Date();
		final String stringDate = DateUtil.dateToNenGappiString(date);
		assertEquals(stringDate, SIMPLE_DATE_FORMAT.format(date));
	}

	public void testDateToNenGappiYoubiString() {
		final String emptyString = DateUtil.dateToNenGappiYoubiString(null);
		assertEquals(emptyString, "");

		final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat ("yyyy年M月d日(E)");
		final Date date = new Date();
		final String stringDate = DateUtil.dateToNenGappiYoubiString(date);
		assertEquals(stringDate, SIMPLE_DATE_FORMAT.format(date));
	}

	public void testToSqlDate() {
		final Date monthFirstDate = DateUtil.toSqlDate("2008", "4");
		assertEquals(monthFirstDate, DateUtil.objectToDate("2008/04/01 00:00:00"));
	}

	public void testToSqlDate2() {
		final Date monthFirstDate = DateUtil.toSqlDate("2008", null, "-04-01");
		assertEquals(monthFirstDate, DateUtil.objectToDate("2008/04/01 00:00:00"));
		final Date monthFirstDate5 = DateUtil.toSqlDate("2008", "5", "-04-01");
		assertEquals(monthFirstDate5, DateUtil.objectToDate("2008/05/01 00:00:00"));
	}

	public void testObjectToSqlDate() {
		final Date nullDate = DateUtil.objectToSqlDate(null);
		assertNull(nullDate);
		final Date date = DateUtil.objectToSqlDate(new Date());
		assertNotNull(date);

	}

	public void testTimestampToSqlDate() {
		final Date nullDate = DateUtil.timestampToSqlDate(null);
		assertNull(nullDate);
		final Date date = DateUtil.timestampToSqlDate(new java.sql.Timestamp(System.currentTimeMillis()));
		assertNotNull(date);
	}

	public void testGetYear() {
		final String nullDate = DateUtil.getYear(null);
		assertNull(nullDate);

		final Calendar calendar = Calendar.getInstance();
		final String date = DateUtil.getYear(new java.sql.Date(System.currentTimeMillis()));
		assertEquals((new Integer(calendar.get(Calendar.YEAR))).toString(), date);
	}

	public void testGetMonth() {
		final String nullDate = DateUtil.getMonth(null);
		assertNull(nullDate);

		final Calendar calendar = Calendar.getInstance();
		final String date = DateUtil.getMonth(new java.sql.Date(System.currentTimeMillis()));
		assertEquals((new Integer(calendar.get(Calendar.MONTH) + 1)).toString(), date);
	}

	public void testGetDay() {
		final String nullDate = DateUtil.getDay(null);
		assertNull(nullDate);

		final Calendar calendar = Calendar.getInstance();
		final String date = DateUtil.getDay(new java.sql.Date(System.currentTimeMillis()));
		assertEquals((new Integer(calendar.get(Calendar.DATE))).toString(), date);
	}

	public void testGetStartDate() {
		final java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
		final java.sql.Date startDate = DateUtil.getStartDate(date);
		assertFalse(date.getTime() == startDate.getTime());
	}

	public void testGetEndDate() {
		final java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
		final java.sql.Date endDate = DateUtil.getEndDate(date);
		assertFalse(date.getTime() == endDate.getTime());
	}

	public void testGetStartUtilDate() {
		final java.util.Date date = new java.util.Date(System.currentTimeMillis());
		final java.util.Date startDate = DateUtil.getStartDate(date);
		assertFalse(date.getTime() == startDate.getTime());
	}

	public void testGetEndUtilDate() {
		final java.util.Date date = new java.util.Date(System.currentTimeMillis());
		final java.util.Date endDate = DateUtil.getEndDate(date);
		assertFalse(date.getTime() == endDate.getTime());
	}

	public void testGetMonthFirstDate() {
		final Date firstDate = DateUtil.getMonthFirstDate(new Date());
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(firstDate);
		assertTrue(calendar.get(Calendar.DATE) == 1);
	}

	public void testGetMonthLastDate() {
		final Date lastDate = DateUtil.getMonthLastDate(new Date());
		final Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(lastDate);
		final Calendar calendar2 = Calendar.getInstance();
		calendar2.set(Calendar.DATE, 1);
		calendar2.add(Calendar.MONTH, 1);
		calendar2.add(Calendar.DATE, -1);
		assertTrue(calendar1.get(Calendar.DATE) == calendar2.get(Calendar.DATE));
	}

	public void testStringToSQLDate() {
		final Date exceptionDate = DateUtil.stringToSQLDate("exception time");
		assertNull(exceptionDate);

		final Date date = DateUtil.stringToSQLDate("2008/04/16");
		assertNotNull(date);
	}

	public void testStringToDate() {
		final Date exceptionDate = DateUtil.stringToDate("exception time");
		assertNull(exceptionDate);

		final Date date = DateUtil.stringToDate("2008/04/16 00:00:00.000");
		assertNotNull(date);
	}

	public void testIsDate() {
		final boolean exceptionDate = DateUtil.isDate("exception time");
		assertFalse(exceptionDate);

		final boolean date = DateUtil.isDate("2008/04/16 00:00:00.000");
		assertTrue(date);

	}

	public void testGetAge() {
		final Integer nullBirthday = DateUtil.getAge(null);
		assertNull(nullBirthday);

		final Integer birthday = DateUtil.getAge(DateUtil.stringToSQLDate("1977/01/07"));
		assertNotNull(birthday);
	}

	public void testGetWithSlash() {
		final String nulldate = DateUtil.getWithSlash(null);
		assertNull(nulldate);
		final String date6 = DateUtil.getWithSlash("080416");
		assertEquals(date6, "08/04/16");
		final String date8 = DateUtil.getWithSlash("20080416");
		assertEquals(date8, "2008/04/16");
		final String dateElse = DateUtil.getWithSlash("2008/0416");
		assertEquals(dateElse, "2008/0416");
	}

	public void testGetDateFromYMD() {
		final Date nulldate = DateUtil.getDateFromYMD(null);
		assertNull(nulldate);

		final Date date = DateUtil.getDateFromYMD("060416");
		assertNotNull(date);

		final Date notDate = DateUtil.getDateFromYMD("not date");
		assertNull(notDate);

	}

	public void testGetNextDay() {
		try {
			DateUtil.getNextDay(10, 1);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}

		final Date nulldate = DateUtil.getNextDay(Calendar.FRIDAY, 1);
		assertNotNull(nulldate);

		final Calendar calendar = Calendar.getInstance();
		final Date sameDayOfWeekdate = DateUtil.getNextDay(calendar.get(Calendar.DAY_OF_WEEK), 1);
		assertNotNull(sameDayOfWeekdate);

	}

	public void testIsSameDate() {

		final boolean doublenulldate = DateUtil.isSameDate(null, null);
		assertTrue(doublenulldate);

		final boolean rightnulldate = DateUtil.isSameDate(null, new Date());
		assertFalse(rightnulldate);

		final boolean leftnulldate = DateUtil.isSameDate(new Date(), null);
		assertFalse(leftnulldate);

		final boolean today = DateUtil.isSameDate(new Date(), new Date());
		assertTrue(today);

		final Calendar calendarYear = Calendar.getInstance();
		calendarYear.add(Calendar.YEAR, 1);
		final boolean addYear = DateUtil.isSameDate(calendarYear.getTime(), new Date());
		assertFalse(addYear);

		final Calendar calendarMonth = Calendar.getInstance();
		calendarMonth.add(Calendar.MONTH, 1);
		final boolean addMonth = DateUtil.isSameDate(calendarMonth.getTime(), new Date());
		assertFalse(addMonth);

		final Calendar calendarDate = Calendar.getInstance();
		calendarDate.add(Calendar.DATE, 1);
		final boolean addDate = DateUtil.isSameDate(calendarDate.getTime(), new Date());
		assertFalse(addDate);

	}

}
