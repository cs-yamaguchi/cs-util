package test.jp.co.comster;

import junit.framework.Test;
import junit.framework.TestSuite;
import test.jp.co.comster.date.TestDateUtil;
import test.jp.co.comster.file.TestFileUtil;
import test.jp.co.comster.fixedfile.TestFixedFile;
import test.jp.co.comster.geocode.TestGeocodeUtil;
import test.jp.co.comster.log.TestLogUtil;
import test.jp.co.comster.string.TestStringUtil;
import test.jp.co.comster.xml.TestXmlFileParser;
import test.jp.co.comster.xml.TestXmlStringBuilder;


public class AllTests {

	public static Test suite() {
		final TestSuite suite = new TestSuite("All Test");
		suite.addTestSuite(TestDateUtil.class);
		suite.addTestSuite(TestFileUtil.class);
		suite.addTestSuite(TestFixedFile.class);
//		suite.addTestSuite(TestFtpUtil.class);
		suite.addTestSuite(TestGeocodeUtil.class);
		suite.addTestSuite(TestLogUtil.class);
//		suite.addTestSuite(TestMailUtil.class);
		suite.addTestSuite(TestStringUtil.class);
		suite.addTestSuite(TestXmlFileParser.class);
		suite.addTestSuite(TestXmlStringBuilder.class);
		return suite;
	}
}