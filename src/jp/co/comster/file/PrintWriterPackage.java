package jp.co.comster.file;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * PrintWriterの生成に使用したインスタンスを保持します。
 *
 * @author COMSTER）山口　英志
 * @version 1.0 ( 2008/03/04 )
 *
 */
public class PrintWriterPackage {

	private FileOutputStream fileOutputStream_;
	private OutputStreamWriter outputStreamWriter_;
	private PrintWriter printWriter_;

	/**
	 * コンストラクタ
	 * @param fileOutputStream
	 * @param outputStreamWriter
	 * @param printWriter
	 */
	public PrintWriterPackage(FileOutputStream fileOutputStream, OutputStreamWriter outputStreamWriter, PrintWriter printWriter) {
		fileOutputStream_ = fileOutputStream;
		outputStreamWriter_ = outputStreamWriter;
		printWriter_ = printWriter;
	}

	/**
	 * PrintWriterに書き込みます
	 * @param s
	 */
	public void write(String s) {
		printWriter_.write(s);
	}

	/**
	 * ストリームを閉じます
	 * @throws IOException
	 *
	 */
	public void close() throws IOException {
		if (printWriter_ != null)
			printWriter_.close();
		if (outputStreamWriter_ != null)
			outputStreamWriter_.close();
		if (fileOutputStream_ != null)
			fileOutputStream_.close();
	}

}
