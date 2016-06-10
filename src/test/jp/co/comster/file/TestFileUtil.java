package test.jp.co.comster.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import jp.co.comster.file.FileUtil;
import jp.co.comster.file.PrintWriterPackage;
import junit.framework.TestCase;

public class TestFileUtil extends TestCase {

	public TestFileUtil(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testLs() {

		final FileUtil fileUtil = new FileUtil();
		String[] ls = null;
		try {
			ls = fileUtil.ls("src/test/jp/co/comster/file");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		assertEquals(ls[0], "TestFileUtil.java");

		try {
			fileUtil.ls("src/test/jp/co/comster/file/notfound");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			fileUtil.ls("src/test/jp/co/comster/file/TestFileUtil.java");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void testDirectoryExistFiles() {
		final FileUtil fileUtil = new FileUtil();
		String[] directoryExistFiles = null;
		try {
			directoryExistFiles = fileUtil.directoryExistFiles("src/test/jp/co/comster/file", new JavaSourceFilenameFilter());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		assertEquals(directoryExistFiles[0], "TestFileUtil.java");

	}

	private class JavaSourceFilenameFilter implements FilenameFilter {
		public boolean accept(File dir, String name) {
			return (name.endsWith(".java"));
		}
	}


	public void testMkdir() {
		final FileUtil fileUtil = new FileUtil();
		final boolean mkdir = fileUtil.mkdir("testdirectory");
		assertTrue(mkdir);

		final boolean existsMkdir = fileUtil.mkdir("testdirectory");
		assertFalse(existsMkdir);
	}

	public void testRmdir() {
		final FileUtil fileUtil = new FileUtil();
		final boolean rmdir = fileUtil.rmdir("testdirectory");
		assertTrue(rmdir);
		final boolean notExistsRmdir = fileUtil.rmdir("testdirectory");
		assertFalse(notExistsRmdir);
	}

	public void testRmForce() {
		final FileUtil fileUtil = new FileUtil();

		final String DIRECTORY = "testdirectory";
		final String FILE_NAME_A = "testfile.a";
		final String FILE_NAME_B = "testfile.b";
		final String INNER_DIRECTORY = "innerdirectory";
		final String FILE_NAME_C = "testfile.c";
		fileUtil.mkdir(DIRECTORY);
		fileUtil.mkdir(DIRECTORY + "/" + INNER_DIRECTORY);

		mockFileMaker(fileUtil, DIRECTORY + "/" + FILE_NAME_A, "hogehoge");
		mockFileMaker(fileUtil, DIRECTORY + "/" + FILE_NAME_B, "fugafuga");
		mockFileMaker(fileUtil, DIRECTORY + "/" + INNER_DIRECTORY + "/" + FILE_NAME_C, "mugamuga");

		final boolean rmForce = fileUtil.rmForce(DIRECTORY);
		assertTrue(rmForce);

		final boolean notExistsRmForce = fileUtil.rmForce("notExists");
		assertFalse(notExistsRmForce);
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

	public void testCopy() {
		final FileUtil fileUtil = new FileUtil();
		final String ORIGINAL_DIRECTORY = "originaldirectory";
		final String ORIGINAL_FILE_NAME = "copyoriginal.tmp";
		final String TARGET_DIRECTORY = "targetdirectory";
		final String TARGET_FILE_NAME = "copytarget.tmp";
		fileUtil.mkdir(ORIGINAL_DIRECTORY);
		fileUtil.mkdir(TARGET_DIRECTORY);

		mockFileMaker(fileUtil, ORIGINAL_DIRECTORY + "/" + ORIGINAL_FILE_NAME, "hogehoge");

		try {
			fileUtil.copy(ORIGINAL_DIRECTORY + "/" + ORIGINAL_FILE_NAME, TARGET_DIRECTORY);
		} catch (IOException e) {
			e.printStackTrace();
		}
		final File fileOriginalName = new File(TARGET_DIRECTORY + "/" + ORIGINAL_FILE_NAME);
		assertTrue(fileOriginalName.exists());

		try {
			fileUtil.copy(ORIGINAL_DIRECTORY + "/" + ORIGINAL_FILE_NAME, TARGET_DIRECTORY, TARGET_FILE_NAME);
		} catch (IOException e) {
			e.printStackTrace();
		}
		final File file = new File(TARGET_DIRECTORY + "/" + TARGET_FILE_NAME);
		assertTrue(file.exists());

		try {
			fileUtil.copy("notexists" + "/" + ORIGINAL_FILE_NAME, TARGET_DIRECTORY, TARGET_FILE_NAME);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			fileUtil.copy(ORIGINAL_DIRECTORY + "/" + ORIGINAL_FILE_NAME, "notexists", TARGET_FILE_NAME);
		} catch (IOException e) {
			e.printStackTrace();
		}

		fileUtil.rmForce(ORIGINAL_DIRECTORY);
		fileUtil.rmForce(TARGET_DIRECTORY);

	}

	public void testMove() {

		final FileUtil fileUtil = new FileUtil();
		final String ORIGINAL_DIRECTORY = "originaldirectory";
		final String ORIGINAL_FILE_NAME = "moveoriginal.tmp";
		final String TARGET_DIRECTORY = "targetdirectory";
		final String TARGET_FILE_NAME = "movetarget.tmp";
		fileUtil.mkdir(ORIGINAL_DIRECTORY);
		fileUtil.mkdir(TARGET_DIRECTORY);

		mockFileMaker(fileUtil, ORIGINAL_DIRECTORY + "/" + ORIGINAL_FILE_NAME, "hogehoge");

		try {
			fileUtil.move(ORIGINAL_DIRECTORY + "/" + ORIGINAL_FILE_NAME, TARGET_DIRECTORY);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		File file;
		file = new File(TARGET_DIRECTORY + "/" + ORIGINAL_FILE_NAME);
		assertTrue(file.exists());

		file = new File(ORIGINAL_DIRECTORY + "/" + ORIGINAL_FILE_NAME);
		assertFalse(file.exists());

		fileUtil.rmForce(ORIGINAL_DIRECTORY);
		fileUtil.rmForce(TARGET_DIRECTORY);

		fileUtil.mkdir(ORIGINAL_DIRECTORY);
		fileUtil.mkdir(TARGET_DIRECTORY);

		mockFileMaker(fileUtil, ORIGINAL_DIRECTORY + "/" + ORIGINAL_FILE_NAME, "hogehoge");

		try {
			fileUtil.move(ORIGINAL_DIRECTORY + "/" + ORIGINAL_FILE_NAME, TARGET_DIRECTORY, TARGET_FILE_NAME);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		file = new File(TARGET_DIRECTORY + "/" + TARGET_FILE_NAME);
		assertTrue(file.exists());

		file = new File(ORIGINAL_DIRECTORY + "/" + ORIGINAL_FILE_NAME);
		assertFalse(file.exists());

		fileUtil.rmForce(ORIGINAL_DIRECTORY);
		fileUtil.rmForce(TARGET_DIRECTORY);

		fileUtil.mkdir(ORIGINAL_DIRECTORY);
		fileUtil.mkdir(TARGET_DIRECTORY);

		mockFileMaker(fileUtil, ORIGINAL_DIRECTORY + "/" + ORIGINAL_FILE_NAME, "hogehoge");

		try {
			fileUtil.move("notexists" + "/" + ORIGINAL_FILE_NAME, TARGET_DIRECTORY, TARGET_FILE_NAME);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			fileUtil.move(ORIGINAL_DIRECTORY + "/" + ORIGINAL_FILE_NAME, "notexists", TARGET_FILE_NAME);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		fileUtil.rmForce(ORIGINAL_DIRECTORY);
		fileUtil.rmForce(TARGET_DIRECTORY);

	}

	public void testRowCount() {


		final FileUtil fileUtil = new FileUtil();
		final String DIRECTORY = "directory";
		final String FILE_NAME = DIRECTORY + "/" + "rowcount.tmp";
		fileUtil.mkdir(DIRECTORY);

		final int NEXT_LINE_COUNT = 10;
		final StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < NEXT_LINE_COUNT; i++)
			stringBuffer.append("hogehoge" + "\n");

		mockFileMaker(fileUtil, FILE_NAME, stringBuffer.toString());
		int rowCount = 0;
		try {
			rowCount = fileUtil.rowCount(FILE_NAME, "MS932");
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(NEXT_LINE_COUNT, rowCount);


		try {
			rowCount = fileUtil.rowCount(new File(FILE_NAME), "MS932");
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(NEXT_LINE_COUNT, rowCount);

		fileUtil.rmForce(DIRECTORY);

	}

	public void testFileLineLength() {

		final FileUtil fileUtil = new FileUtil();
		final String DIRECTORY = "directory";
		final String FILE_NAME = DIRECTORY + "/" + "column.tmp";
		fileUtil.mkdir(DIRECTORY);

		final int COLUMN_COUNT = 10;
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < COLUMN_COUNT; i++)
			stringBuffer.append('a');
		stringBuffer.append("\n");
		stringBuffer.append('a');

		mockFileMaker(fileUtil, FILE_NAME, stringBuffer.toString());

		int columnCount = 0;
		try {
			columnCount = fileUtil.fileLineLength(FILE_NAME, "MS932");
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(COLUMN_COUNT + "\n".length(), columnCount);

		fileUtil.rmForce(DIRECTORY);

		fileUtil.mkdir(DIRECTORY);
		stringBuffer = new StringBuffer();
		for (int i = 0; i < COLUMN_COUNT; i++)
			stringBuffer.append('a');
		stringBuffer.append("\n");
		stringBuffer.append("\n");

		mockFileMaker(fileUtil, FILE_NAME, stringBuffer.toString());

		try {
			columnCount = fileUtil.fileLineLength(new File(FILE_NAME), "MS932");
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(COLUMN_COUNT + "\n".length(), columnCount);

		fileUtil.rmForce(DIRECTORY);

		fileUtil.mkdir(DIRECTORY);
		stringBuffer = new StringBuffer();
		for (int i = 0; i < COLUMN_COUNT; i++)
			stringBuffer.append('a');

		mockFileMaker(fileUtil, FILE_NAME, stringBuffer.toString());

		try {
			columnCount = fileUtil.fileLineLength(new File(FILE_NAME), "MS932");
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(COLUMN_COUNT, columnCount);

		fileUtil.rmForce(DIRECTORY);

		fileUtil.mkdir(DIRECTORY);
		for (int i = 0; i < COLUMN_COUNT; i++)
		mockFileMaker(fileUtil, FILE_NAME, "");

		try {
			columnCount = fileUtil.fileLineLength(new File(FILE_NAME), "MS932");
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(0, columnCount);


		fileUtil.rmForce(DIRECTORY);

	}

	public void testFileSize() {
		final FileUtil fileUtil = new FileUtil();
		final String DIRECTORY = "directory";
		final String FILE_NAME = DIRECTORY + "/" + "filesize.tmp";
		fileUtil.mkdir(DIRECTORY);

		mockFileMaker(fileUtil, FILE_NAME, "hogehoge");

		long fileSize = 0;
		fileSize = fileUtil.fileSize(FILE_NAME);

		assertEquals("hogehoge".length(), fileSize);

		fileUtil.rmForce(DIRECTORY);
	}

}
