package test.jp.co.comster.log;

import java.math.BigDecimal;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

import jp.co.comster.log.LogUtil;

public class TestLogUtil extends TestCase {

	public TestLogUtil(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testLogUtil() {
		final LogUtil logUtil = new LogUtil(null);
		assertNotNull(logUtil);
	}

	public void testIsLogWrite() {
		final LogUtil logUtil = new LogUtil(null);
		assertTrue(logUtil.isLogWrite());
	}

	public void testSetLogWrite() {
		final LogUtil logUtil = new LogUtil(null);
		logUtil.setLogWrite(false);
	}

	public void testIsLogQuery() {
		final LogUtil logUtil = new LogUtil(null);
		assertTrue(logUtil.isLogQuery());
	}

	public void testSetLogQuery() {
		final LogUtil logUtil = new LogUtil(null);
		logUtil.setLogQuery(false);
	}

	public void testExecuteLogger() {
		final LogUtil logUtil = new LogUtil(Logger.getLogger(this.getClass()));
		logUtil.executeLogger("hogehoge");
	}

	public void testExecuteQueryLogger() {
		final LogUtil logUtil = new LogUtil(Logger.getLogger(this.getClass()));
		logUtil.executeQueryLogger("hogehoge");
	}

	public void testAbnormalEnd() {
		final LogUtil logUtil = new LogUtil(Logger.getLogger(this.getClass()));
		logUtil.setTitle("hogehoge");
		logUtil.abnormalEnd("hogehoge");
	}

	public void testNormalEnd() {
		final LogUtil logUtil = new LogUtil(Logger.getLogger(this.getClass()));
		logUtil.setTitle("hogehoge");
		logUtil.normalEnd("hogehoge");
	}

	public void testStart() {
		final LogUtil logUtil = new LogUtil(Logger.getLogger(this.getClass()));
		logUtil.setTitle("hogehoge");
		logUtil.start();
	}

	public void testSetTitle() {
		final LogUtil logUtil = new LogUtil(Logger.getLogger(this.getClass()));
		logUtil.setTitle("hogehoge");
	}

	public void testGetLogQuery() {
		final LogUtil logUtil = new LogUtil(Logger.getLogger(this.getClass()));

		final Object[] param = new Object[] {
			new BigDecimal(10),
			new java.sql.Date(System.currentTimeMillis()),
			new java.sql.Time(System.currentTimeMillis()),
			new java.sql.Timestamp(System.currentTimeMillis()),
			new java.util.Date(),
			"hogehoge"
		};
		logUtil.getLogQuery("SELECT * FROM HOGEHOGE", param);
	}

	public void testExceptionLogging() {
		final LogUtil logUtil = new LogUtil(Logger.getLogger(this.getClass()));
		logUtil.exceptionLogging(new RuntimeException("hogehoge exception"));
	}

}
