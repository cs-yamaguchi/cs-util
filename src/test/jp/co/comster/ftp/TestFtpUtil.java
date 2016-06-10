package test.jp.co.comster.ftp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import jp.co.comster.file.FileUtil;
import jp.co.comster.file.PrintWriterPackage;
import jp.co.comster.ftp.FtpConfig;
import jp.co.comster.ftp.FtpUtil;
import jp.co.comster.ftp.FtpUtilException;
import junit.framework.TestCase;

public class TestFtpUtil extends TestCase {

	public TestFtpUtil(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	private FtpConfig getFtpConfig() {
		final FtpConfig ftpConfig = new FtpConfig();
		ftpConfig.setHost("proxy05.benesse.co.jp");
		ftpConfig.setMode("binary");
		ftpConfig.setUser("zbp@zbsv502s");
		ftpConfig.setPassword("zbp");
		ftpConfig.setPort(new Integer(21));
		return ftpConfig;
	}

	public void testMkdir() {
		final FtpConfig ftpConfig = getFtpConfig();
		final FtpUtil ftpUtil = new FtpUtil(ftpConfig);
		boolean isSuccsess = false;
		try {
			isSuccsess = ftpUtil.mkdir("/home/zbp/yamaguchi/ftptest/");
		} catch (FtpUtilException e) {
			e.printStackTrace();
			fail();
		}
		assertTrue(isSuccsess);

	}

	public void testRmdir() {
		final FtpConfig ftpConfig = getFtpConfig();
		final FtpUtil ftpUtil = new FtpUtil(ftpConfig);
		boolean isSuccsess = false;
		try {
			isSuccsess = ftpUtil.rmdir("/home/zbp/yamaguchi/ftptest/");
		} catch (FtpUtilException e) {
			e.printStackTrace();
			fail();
		}
		assertTrue(isSuccsess);
	}

	public void testExistsFile() {
		final FtpConfig ftpConfig = getFtpConfig();
		final FtpUtil ftpUtil = new FtpUtil(ftpConfig);
		boolean existsFile = false;

		try {
			existsFile = ftpUtil.existsFile("/home/zbp/yamaguchi_nothing/", "test.txt");
		} catch (FtpUtilException e) {
			e.printStackTrace();
			assertTrue(e.isBusinessException());
		}
		assertFalse(existsFile);

		try {
			ftpUtil.mkdir("/home/zbp/yamaguchi/ftptest/");
			existsFile = ftpUtil.existsFile("/home/zbp/yamaguchi/ftptest/", "test.txt");
			ftpUtil.rmdir("/home/zbp/yamaguchi/ftptest/");
		} catch (FtpUtilException e) {
			e.printStackTrace();
		}
		assertFalse(existsFile);


		final FileUtil fileUtil = new FileUtil();
		fileUtil.mkdir("ftptest/");
		mockFileMaker(fileUtil, "ftptest/test.tmp", "testftp");

		boolean notExistsFile = false;

		try {
			ftpUtil.mkdir("/home/zbp/yamaguchi/ftptest/");
			ftpUtil.putFile("ftptest/test.tmp", "/home/zbp/yamaguchi/ftptest/test.tmp");
			existsFile = ftpUtil.existsFile("/home/zbp/yamaguchi/ftptest/", "test.tmp");
			notExistsFile = ftpUtil.existsFile("/home/zbp/yamaguchi/ftptest/", "testnotfound.tmp");
			ftpUtil.deleteFile("/home/zbp/yamaguchi/ftptest/test.tmp");
			ftpUtil.rmdir("/home/zbp/yamaguchi/ftptest/");
		} catch (FtpUtilException e) {
			e.printStackTrace();
		}
		assertTrue(existsFile);
		assertFalse(notExistsFile);


		fileUtil.rmForce("ftptest/");
	}

	public void testLs() {
		final FtpConfig ftpConfig = getFtpConfig();
		final FtpUtil ftpUtil = new FtpUtil(ftpConfig);
		String[] existsFileName = null;

		try {
			existsFileName = ftpUtil.ls("/home/zbp/yamaguchi_nothing/");
		} catch (FtpUtilException e) {
			e.printStackTrace();
			assertTrue(e.isBusinessException());
		}
		assertNull(existsFileName);

		try {
			ftpUtil.mkdir("/home/zbp/yamaguchi/ftptest/");
			existsFileName = ftpUtil.ls("/home/zbp/yamaguchi/ftptest/");
			ftpUtil.rmdir("/home/zbp/yamaguchi/ftptest/");
		} catch (FtpUtilException e) {
			e.printStackTrace();
		}
		assertNotNull(existsFileName);

		final FileUtil fileUtil = new FileUtil();
		fileUtil.mkdir("ftptest/");
		mockFileMaker(fileUtil, "ftptest/test.tmp", "testftp");


		try {
			ftpUtil.mkdir("/home/zbp/yamaguchi/ftptest/");
			ftpUtil.putFile("ftptest/test.tmp", "/home/zbp/yamaguchi/ftptest/test.tmp");
			existsFileName = ftpUtil.ls("/home/zbp/yamaguchi/ftptest/");
			ftpUtil.deleteFile("/home/zbp/yamaguchi/ftptest/test.tmp");
			ftpUtil.rmdir("/home/zbp/yamaguchi/ftptest/");
		} catch (FtpUtilException e) {
			e.printStackTrace();
		}
		assertNotNull(existsFileName);
		assertEquals(existsFileName.length, 1);
		assertEquals(existsFileName[0], "/home/zbp/yamaguchi/ftptest/test.tmp");




		fileUtil.rmForce("ftptest/");
	}

	public void testPutFile() {

		final FileUtil fileUtil = new FileUtil();
		fileUtil.mkdir("ftptest/");
		mockFileMaker(fileUtil, "ftptest/test.tmp", "testftp");

		final FtpConfig ftpConfig = getFtpConfig();
		final FtpUtil ftpUtil = new FtpUtil(ftpConfig);
		boolean isSuccsess = false;
		try {
			isSuccsess = ftpUtil.putFile("ftptest/test.tmp", "/home/zbp/yamaguchi/test.tmp");
		} catch (FtpUtilException e) {
			e.printStackTrace();
		}
		assertTrue(isSuccsess);

		fileUtil.rmForce("ftptest/");

	}

	public void testGetFile() {

		final FileUtil fileUtil = new FileUtil();
		fileUtil.mkdir("ftptest/");

		final FtpConfig ftpConfig = getFtpConfig();
		final FtpUtil ftpUtil = new FtpUtil(ftpConfig);
		boolean isSuccsess = false;
		try {
			isSuccsess = ftpUtil.getFile("ftptest/test.tmp", "/home/zbp/yamaguchi/test.tmp");
		} catch (FtpUtilException e) {
			e.printStackTrace();
		}
		assertTrue(isSuccsess);

		fileUtil.rmForce("ftptest/");

	}

	public void testDeleteFile() {
		final FtpConfig ftpConfig = getFtpConfig();
		final FtpUtil ftpUtil = new FtpUtil(ftpConfig);
		boolean isSuccsess = false;

		try {
			isSuccsess = ftpUtil.deleteFile("/home/zbp/yamaguchi/test.tmp");
		} catch (FtpUtilException e) {
			e.printStackTrace();
		}
		assertTrue(isSuccsess);

	}

	public void testLoginFail() {
		final FtpConfig ftpConfig = getFtpConfig();
		ftpConfig.setHost("zbsv502s");
		ftpConfig.setUser("zbp");
		ftpConfig.setPort(null);

		final FtpUtil ftpUtil = new FtpUtil(ftpConfig);

		try {
			ftpUtil.ls("/home/zbp/yamaguchi/");
		} catch (FtpUtilException e) {
			e.printStackTrace();
		}

	}

	public void testLoginFail2() {
		final FtpConfig ftpConfig = getFtpConfig();
		ftpConfig.setPassword("fail");

		final FtpUtil ftpUtil = new FtpUtil(ftpConfig);

		try {
			ftpUtil.ls("/home/zbp/yamaguchi/");
		} catch (FtpUtilException e) {
			e.printStackTrace();
		}

	}

	public void testAscii() {
		final FtpConfig ftpConfig = getFtpConfig();
		ftpConfig.setMode("ascii");

		final FtpUtil ftpUtil = new FtpUtil(ftpConfig);

		try {
			ftpUtil.ls("/home/zbp/yamaguchi/");
		} catch (FtpUtilException e) {
			e.printStackTrace();
		}

	}

	public void testImage() {
		final FtpConfig ftpConfig = getFtpConfig();
		ftpConfig.setMode("image");
		final FtpUtil ftpUtil = new FtpUtil(ftpConfig);

		try {
			ftpUtil.ls("/home/zbp/yamaguchi/");
		} catch (FtpUtilException e) {
			e.printStackTrace();
		}

	}

	public void testEbcdic() {
		final FtpConfig ftpConfig = getFtpConfig();
		ftpConfig.setMode("ebcdic");

		final FtpUtil ftpUtil = new FtpUtil(ftpConfig);

		try {
			ftpUtil.ls("/home/zbp/yamaguchi/");
		} catch (FtpUtilException e) {
			e.printStackTrace();
		}

	}

	public void testLocal() {
		final FtpConfig ftpConfig = getFtpConfig();
		ftpConfig.setMode("local");

		final FtpUtil ftpUtil = new FtpUtil(ftpConfig);

		try {
			ftpUtil.ls("/home/zbp/yamaguchi/");
		} catch (FtpUtilException e) {
			e.printStackTrace();
		}

	}

	private void mockFileMaker(FileUtil fileUtil, String filename, String body) {
		PrintWriterPackage createWriter = null;
		try {
			createWriter = fileUtil.createWriter(filename, "MS932");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		createWriter.write(body);
		try {
			createWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
