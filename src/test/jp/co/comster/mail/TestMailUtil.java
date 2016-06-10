package test.jp.co.comster.mail;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import jp.co.comster.mail.AttachmentFile;
import jp.co.comster.mail.MailUtil;
import jp.co.comster.mail.SSLMailConfig;
import junit.framework.TestCase;

public class TestMailUtil extends TestCase {

	public TestMailUtil(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}


	public void testSendmail1() {
		final SSLMailConfig mailConfig = new SSLMailConfig();
		mailConfig.setToAddress("cs-yamaguchi@benesse-infoshell.co.jp");
		mailConfig.setFromAddress("cs-yamaguchi@benesse-infoshell.co.jp");
		mailConfig.setFromName("JUnit test send");
		mailConfig.setSmtp("smtp.gmail.com");
		mailConfig.setTitle("テストメール　タイトル");
		mailConfig.setBody("テストメール　本文");
		mailConfig.setSslUser("nc.yamaguchi@gmail.com");
		mailConfig.setSslPass("netcitys");
		final MailUtil mailUtil = new MailUtil();
		final boolean sendmail = mailUtil.sendmail(mailConfig);
		assertTrue(sendmail);
	}

	public void testSendmail2() {
		final SSLMailConfig mailConfig = new SSLMailConfig();
		mailConfig.setToAddress("cs-yamaguchi@benesse-infoshell.co.jp");
		mailConfig.setFromAddress("cs-yamaguchi@benesse-infoshell.co.jp");
		mailConfig.setFromName("JUnit test send");
		mailConfig.setSmtp("smtp.gmail.com");
		mailConfig.setTitle("テストメール　タイトル");
		mailConfig.setBody("テストメール　本文");
		mailConfig.setFileName("");
		mailConfig.setSslUser("nc.yamaguchi@gmail.com");
		mailConfig.setSslPass("netcitys");
		final MailUtil mailUtil = new MailUtil();
		final boolean sendmail = mailUtil.sendmail(mailConfig);
		assertTrue(sendmail);
	}

	public void testSendmail3() {
		final SSLMailConfig mailConfig = new SSLMailConfig();
		mailConfig.setToAddress("cs-yamaguchi@benesse-infoshell.co.jp");
		mailConfig.setFromAddress("cs-yamaguchi@benesse-infoshell.co.jp");
		mailConfig.setFromName("JUnit test send");
		mailConfig.setSmtp("smtp.gmail.com");
		mailConfig.setTitle("テストメール　タイトル");
		mailConfig.setBody("テストメール　本文");
		mailConfig.setFileName(System.getProperty("user.dir") + "/mailtest/sample.txt");
		mailConfig.setSslUser("nc.yamaguchi@gmail.com");
		mailConfig.setSslPass("netcitys");
		final MailUtil mailUtil = new MailUtil();
		final boolean sendmail = mailUtil.sendmail(mailConfig);
		assertTrue(sendmail);
	}

	public void testSendmail4() {
		final SSLMailConfig mailConfig = new SSLMailConfig();
		mailConfig.setToAddress("cs-yamaguchi@benesse-infoshell.co.jp");
		mailConfig.setToCC("cs-yamaguchi@benesse-infoshell.co.jp");
		mailConfig.setFromAddress("cs-yamaguchi@benesse-infoshell.co.jp");
		mailConfig.setFromName("JUnit test send");
		mailConfig.setSmtp("smtp.gmail.com");
		mailConfig.setTitle("テストメール　タイトル");
		mailConfig.setBody("テストメール　本文");
		mailConfig.setSslUser("nc.yamaguchi@gmail.com");
		mailConfig.setSslPass("netcitys");
		final MailUtil mailUtil = new MailUtil();
		final boolean sendmail = mailUtil.sendmail(mailConfig);
		assertTrue(sendmail);
	}

	public void testSendmail5() {
		final SSLMailConfig mailConfig = new SSLMailConfig();
//		mailConfig.setToAddress("cs-yamaguchi@benesse-infoshell.co.jp");
//		mailConfig.setToCC("cs-yamaguchi@benesse-infoshell.co.jp");
		mailConfig.setToBCC("cs-yamaguchi@benesse-infoshell.co.jp");
		mailConfig.setFromAddress("cs-yamaguchi@benesse-infoshell.co.jp");
		mailConfig.setFromName("JUnit test send");
		mailConfig.setSmtp("smtp.gmail.com");
		mailConfig.setTitle("テストメール　タイトル");
		mailConfig.setBody("テストメール　本文");
		mailConfig.setSslUser("nc.yamaguchi@gmail.com");
		mailConfig.setSslPass("netcitys");
		final MailUtil mailUtil = new MailUtil();
		final boolean sendmail = mailUtil.sendmail(mailConfig);
		assertTrue(sendmail);
	}

	public void testSendmail6() {
		final SSLMailConfig mailConfig = new SSLMailConfig();
		mailConfig.setToAddress("cs-yamaguchi@benesse-infoshell.co.jp");
		mailConfig.setFromAddress("cs-yamaguchi@benesse-infoshell.co.jp");
		mailConfig.setFromName("JUnit test send");
		mailConfig.setSmtp("");
		mailConfig.setTitle("テストメール　タイトル");
		mailConfig.setBody("テストメール　本文");
		mailConfig.setSslUser("nc.yamaguchi@gmail.com");
		mailConfig.setSslPass("netcitys");
		final MailUtil mailUtil = new MailUtil();
		final boolean sendmail = mailUtil.sendmail(mailConfig);
		assertFalse(sendmail);
	}

	public void testSendmail7() {
		final SSLMailConfig mailConfig = new SSLMailConfig();
		mailConfig.setToAddress("cs-yamaguchi@benesse-infoshell.co.jp");
		mailConfig.setFromAddress("cs-yamaguchi@benesse-infoshell.co.jp");
		mailConfig.setFromName("JUnit test send");
		mailConfig.setSmtp("smtp.gmail.com");
		mailConfig.setTitle("テストメール　タイトル添付");
		mailConfig.setBody("テストメール　本文");
		mailConfig.setDebug(new Boolean(true));
		mailConfig.setMimeType("text/plain");
		mailConfig.setEncord("UTF-8");
		mailConfig.setSslUser("nc.yamaguchi@gmail.com");
		mailConfig.setSslPass("netcitys");
		final MailUtil mailUtil = new MailUtil();
		final boolean sendmail = mailUtil.sendmail(mailConfig);
		assertTrue(sendmail);
	}

	public void testSendmail8() {
		final SSLMailConfig mailConfig = new SSLMailConfig();
		mailConfig.setToAddress("cs-yamaguchi@benesse-infoshell.co.jp");
		mailConfig.setFromAddress("cs-yamaguchi@benesse-infoshell.co.jp");
		mailConfig.setFromName("JUnit test send");
		mailConfig.setSmtp("smtp.gmail.com");
		mailConfig.setTitle("テストメール　タイトル");
		mailConfig.setBody("テストメール　本文");
		mailConfig.setEncord("NOT-FOUND-ENCORDING");
		mailConfig.setSslUser("nc.yamaguchi@gmail.com");
		mailConfig.setSslPass("netcitys");
		final MailUtil mailUtil = new MailUtil();
		final boolean sendmail = mailUtil.sendmail(mailConfig);
		assertFalse(sendmail);
	}

	public void testSendmail9() {
		final SSLMailConfig mailConfig = new SSLMailConfig();
		mailConfig.setToAddress("cs-yamaguchi@benesse-infoshell.co.jp");
		mailConfig.setFromAddress("cs-yamaguchi@benesse-infoshell.co.jp");
		mailConfig.setFromName("JUnit test send");
		mailConfig.setSmtp("smtp.gmail.com");
		mailConfig.setTitle("テストメール　タイトル");
		mailConfig.setBody("テストメール　本文");
		mailConfig.setFileName("添付ファイルテスト.txt");
		mailConfig.setFileText("a\ti,u\ne,\"o\"");
		mailConfig.setSslUser("nc.yamaguchi@gmail.com");
		mailConfig.setSslPass("netcitys");
		final MailUtil mailUtil = new MailUtil();
		final boolean sendmail = mailUtil.sendmail(mailConfig);
		assertTrue(sendmail);
	}

	public void testSendmail10() throws UnsupportedEncodingException {
		final SSLMailConfig mailConfig = new SSLMailConfig();
		mailConfig.setToAddress("cs-yamaguchi@benesse-infoshell.co.jp");
		mailConfig.setFromAddress("cs-yamaguchi@benesse-infoshell.co.jp");
		mailConfig.setFromName("JUnit test send");
		mailConfig.setSmtp("smtp.gmail.com");
		mailConfig.setTitle("テストメール　タイトル");
		mailConfig.setBody("テストメール　本文");
		mailConfig.setFileName("添付ファイルテスト.txt");
		mailConfig.setFileText(new String("文字化け確認～".getBytes("MS932"), "ISO8859-1"));
		mailConfig.setSslUser("nc.yamaguchi@gmail.com");
		mailConfig.setSslPass("netcitys");

		final MailUtil mailUtil = new MailUtil();
		final boolean sendmail = mailUtil.sendmail(mailConfig);
		assertTrue(sendmail);
	}


	public void testSendmail11() {
		final SSLMailConfig mailConfig = new SSLMailConfig();
		mailConfig.setToAddress("cs-yamaguchi@benesse-infoshell.co.jp");
		mailConfig.setFromAddress("cs-yamaguchi@benesse-infoshell.co.jp");
		mailConfig.setFromName("JUnit test send");
		mailConfig.setSmtp("smtp.gmail.com");
		mailConfig.setTitle("テストメール　タイトル");
		mailConfig.setBody("テストメール　本文");
		mailConfig.setSslUser("nc.yamaguchi@gmail.com");
		mailConfig.setSslPass("netcitys");

		mailConfig.setAttachmentFiles(new ArrayList<AttachmentFile>());
		assertNull(mailConfig.getAttachmentFile(100));
		AttachmentFile attachmentFile;
		attachmentFile = new AttachmentFile();
		attachmentFile.setFileName("添付ファイルテスト1.txt");
		attachmentFile.setFileText("a\ti,u\ne,\"o\"");
		attachmentFile.setMimeType("text/plain");
		mailConfig.addAttachmentFiles(attachmentFile);

		attachmentFile = new AttachmentFile();
		attachmentFile.setFileName(System.getProperty("user.dir") + "/mailtest/sample.txt");
		attachmentFile.setMimeType("text/plain");
		mailConfig.addAttachmentFiles(attachmentFile);

		final MailUtil mailUtil = new MailUtil();
		final boolean sendmail = mailUtil.sendmail(mailConfig);
		assertTrue(sendmail);
	}


}
