package test.jp.co.comster.string;

import java.math.BigDecimal;

import jp.co.comster.string.StringUtil;
import junit.framework.TestCase;

public class TestStringUtil extends TestCase {

	public TestStringUtil(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testIsSame() {
		new StringUtil();
		assertTrue(StringUtil.isSame(null, null));
		assertFalse(StringUtil.isSame(null, ""));
		assertFalse(StringUtil.isSame("", null));
		assertTrue(StringUtil.isSame("", ""));
	}

	public void testGetBlankString() {
		String blankString;
		blankString = StringUtil.getBlankString(10);
		assertEquals(blankString, "          ");

		blankString = StringUtil.getBlankString(10, '0');
		assertEquals(blankString, "0000000000");
	}

	public void testGetFixedStringString() {
		String fixedString;
		fixedString = StringUtil.getFixedString("aaa", 10, false);
		assertEquals(fixedString, "aaa       ");
		fixedString = StringUtil.getFixedString("aaa", 10, true);
		assertEquals(fixedString, "0000000aaa");
		fixedString = StringUtil.getFixedString("aaa", 0, false);
		assertEquals(fixedString, "");
		fixedString = StringUtil.getFixedString(null, 10, false);
		assertEquals(fixedString, "          ");
		fixedString = StringUtil.getFixedString("", 10, false);
		assertEquals(fixedString, "          ");
		fixedString = StringUtil.getFixedString("1234567890", 10, false);
		assertEquals(fixedString, "1234567890");
		fixedString = StringUtil.getFixedString("1234567890a", 10, false);
		assertEquals(fixedString, "1234567890");
		fixedString = StringUtil.getFixedString("1234567890a", 10, true);
		assertEquals(fixedString, "1234567890");


	}

	public void testToString() {
		String toString;
		toString = StringUtil.toString(null);
		assertNull(toString);
		toString = StringUtil.toString("aaa");
		assertEquals(toString, "aaa");
		toString = StringUtil.toString(new BigDecimal(10));
		assertEquals(toString, "10");

		try {
			toString = StringUtil.toString(new Object());
		} catch (RuntimeException e) {
			e.printStackTrace();
		}

	}

	public void testMergeString() {

		String mergeString;
		mergeString = StringUtil.mergeString(null, ",");
		assertNull(mergeString);

		mergeString = StringUtil.mergeString(new String[] {}, ",");
		assertEquals(mergeString, "");

		mergeString = StringUtil.mergeString(new String[] {"a", "b", "c", "d", "e", "f"}, ",");
		assertEquals(mergeString, "a,b,c,d,e,f");

	}

}
