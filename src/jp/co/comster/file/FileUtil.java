package jp.co.comster.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;


/**
 * ファイル操作ユーティリティクラス
 *
 * @author COMSTER）山口　英志
 * @version 1.0 ( 2008/02/04 )
 *
 */
public class FileUtil {

	/**
	 * 対象のディレクトリのファイルリストを返す。
	 * @param directoryPath 対象ディレクトリ
	 * @return
	 * @throws FileNotFoundException
	 */
	public String[] ls(String directoryPath) throws FileNotFoundException {
		final File targetDirectory = new File(directoryPath);
		isExistsDirectory(directoryPath, targetDirectory);
		final String[] files = targetDirectory.list();
		final List<String> fileList = new ArrayList<String>();
		if(!directoryPath.endsWith("/"))
			directoryPath += "/";
		for (int i = 0; i < files.length; i++) {
			final File file = new File(directoryPath + files[i]);
			if (file.isFile())
				fileList.add(file.getName());
		}
		return (String[])fileList.toArray(new String[0]);
	}

	/**
	 * ディレクトリが存在するかを返します。
	 * @param directoryPath
	 * @param targetDirectory
	 * @throws FileNotFoundException
	 */
	private void isExistsDirectory(String directoryPath, final File targetDirectory) throws FileNotFoundException {
		if (!targetDirectory.exists())
			throw new FileNotFoundException(directoryPath);
		if (!targetDirectory.isDirectory())
			throw new FileNotFoundException(directoryPath + " is not directory");
	}

	/**
	 * 対象のディレクトリから対象ファイルを検索して返す。
	 * @param directoryPath 対象ディレクトリ
	 * @param filter 対象ファイル検索条件
	 * @return
	 * @throws FileUtilException
	 */
	public String[] directoryExistFiles(String directoryPath, FilenameFilter filter) throws FileNotFoundException {
		final File targetDirectory = new File(directoryPath);
		isExistsDirectory(directoryPath, targetDirectory);
		return targetDirectory.list(filter);
	}

	/**
	 * 引数のパスが存在しない場合、ディレクトリを作成します。
	 * @param path
	 */
	public boolean mkdir(String path) {
		final File directory = new File(path);
		if (!directory.exists())
			return directory.mkdir();
		return false;
	}

	/**
	 * 引数のパスが存在する場合、ディレクトリを削除します。
	 * @param path
	 */
	public boolean rmdir(String path) {
		final File directory = new File(path);
		if (directory.exists())
			if (directory.isDirectory())
				return directory.delete();
		return false;
	}

	/**
	 * 引数のパス・ファイルが存在する場合、中身のファイルを再帰的に削除します。
	 * @param path
	 */
	public boolean rmForce(String path) {
		final File file = new File(path);
		if (!file.exists())
			return false;
		if (file.isDirectory())
			rmAll(file);
		return file.delete();
	}

	/**
	 * 再帰ファイル削除
	 * @param directory
	 */
	private void rmAll(final File directory) {
		final String[] ls = directory.list();
		for (int i = 0; i < ls.length; i++) {
			final File file = new File(directory.getPath() + "/" + ls[i]);
			if (file.isDirectory())
				rmAll(file);
			file.delete();
		}
	}

	/**
	 * ファイルをコピーします。
	 * @param orijinalFileName
	 * @param targetPathName
	 * @param targetFileName
	 * @return
	 */
	public void copy(String orijinalFileName, String targetPathName) throws IOException {
		copy(orijinalFileName, targetPathName, null);
	}

	/**
	 * ファイルをコピーします。
	 * @param orijinalFileName
	 * @param targetPathName
	 * @param targetFileName
	 * @return
	 */
	public void copy(String orijinalFileName, String targetPathName, String targetFileName) throws IOException {
		final File orijinalFile = new File(orijinalFileName);
		if (!orijinalFile.exists())
			throw new FileNotFoundException(orijinalFileName);
		final File targetPath = new File(targetPathName);
		if (!targetPath.exists())
			throw new FileNotFoundException(targetPathName);
		if(!targetPathName.endsWith("/"))
			targetPathName += "/";
		if (targetFileName == null || targetFileName.equals(""))
			targetFileName = orijinalFile.getName();
		FileInputStream originalInputStream = null;
		FileOutputStream createOutputStream = null;
		FileChannel originalFile = null;
		FileChannel createFile = null;
		try{
			originalInputStream = new FileInputStream(orijinalFileName);
			createOutputStream = new FileOutputStream(targetPathName + targetFileName);
			originalFile = originalInputStream.getChannel();
			createFile = createOutputStream.getChannel();
			createFile.transferFrom(originalFile, 0, originalFile.size());
		} finally {
			if (originalFile != null)
				originalFile.close();
			if (createFile != null)
				createFile.close();
			if (originalInputStream != null)
				originalInputStream.close();
			if (createOutputStream != null)
				createOutputStream.close();
		}
	}

	/**
	 * ファイルを移動します。
	 * @param orijinalFileName
	 * @param targetPathName
	 * @throws FileNotFoundException
	 */
	public void move(String orijinalFileName, String targetPathName) throws FileNotFoundException {
		move(orijinalFileName, targetPathName, null);
	}

	/**
	 * ファイルを移動します。
	 * @param orijinalFileName
	 * @param targetPathName
	 * @param targetFileName
	 * @throws FileNotFoundException
	 */
	public void move(String orijinalFileName, String targetPathName, String targetFileName) throws FileNotFoundException {
		final File orijinalFile = new File(orijinalFileName);
		if (!orijinalFile.exists())
			throw new FileNotFoundException(orijinalFileName);
		final File targetPath = new File(targetPathName);
		if (!targetPath.exists())
			throw new FileNotFoundException(targetPathName);
		if(!targetPathName.endsWith("/"))
			targetPathName += "/";
		if (targetFileName == null || targetFileName.equals(""))
			targetFileName = orijinalFile.getName();
		final File targetFile = new File(targetPathName + targetFileName);
		orijinalFile.renameTo(targetFile);
	}

	/**
	 * PrintWriterを取得します。
	 * @param file
	 * @param encode
	 * @return
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public PrintWriterPackage createWriter(String file, String encode) throws FileNotFoundException, UnsupportedEncodingException {
		final FileOutputStream fileOutputStream = new FileOutputStream(file, false);
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, encode);
		return new PrintWriterPackage(fileOutputStream, outputStreamWriter, new PrintWriter(outputStreamWriter));
	}

	/**
	 * ファイルの行数を返す
	 * @param fileName
	 * @param encode
	 * @return
	 */
	public int rowCount(String fileName, String encode) throws IOException {
		return rowCount(new File(fileName), encode);
	}

	/**
	 * ファイルの行数を返す
	 * @param targetFile
	 * @param encode
	 * @return
	 */
	public int rowCount(File targetFile, String encode) throws IOException {
		FileInputStream fileInputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		int count = 0;
		try {
			fileInputStream = new FileInputStream(targetFile);
			inputStreamReader = new InputStreamReader(fileInputStream, encode);
			bufferedReader = new BufferedReader(inputStreamReader);
			while (bufferedReader.readLine() != null)
				count++;
		} finally {
			if (bufferedReader != null)
				bufferedReader.close();
			if (inputStreamReader != null)
				inputStreamReader.close();
			if (fileInputStream != null)
				fileInputStream.close();
		}
		return count;
	}

	/**
	 * ファイルの1行目のバイト数を返す
	 * @param fileName
	 * @param encord
	 * @return
	 */
	public int fileLineLength(String fileName, String encode) throws IOException  {
		return fileLineLength(new File(fileName), encode);

	}
	/**
	 * ファイルの1行目のバイト数を返す
	 * @param targetFile
	 * @param encord
	 * @return
	 */
	public int fileLineLength(File targetFile, String encode) throws IOException  {
		FileInputStream fileInputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileInputStream = new FileInputStream(targetFile);
			inputStreamReader = new InputStreamReader(fileInputStream, encode);
			bufferedReader = new BufferedReader(inputStreamReader);
			String readLine = bufferedReader.readLine();
			if (readLine == null)
				return 0;
			final int length = readLine.length();
			readLine = bufferedReader.readLine();
			if (readLine == null)
				return length;
			return length + "\n".length();
		} finally {
			if (bufferedReader != null)
				bufferedReader.close();
			if (inputStreamReader != null)
				inputStreamReader.close();
			if (fileInputStream != null)
				fileInputStream.close();
		}
	}

	/**
	 * ファイルのサイズを返す
	 * @param fileName
	 * @return
	 */
	public long fileSize(String fileName) {
		return fileSize(new File(fileName));
	}

	/**
	 * ファイルのサイズを返す
	 * @param targetFile
	 * @return
	 */
	public long fileSize(File targetFile) {
		return targetFile.length();
	}

}
