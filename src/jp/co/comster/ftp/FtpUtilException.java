package jp.co.comster.ftp;

import java.io.IOException;


/**
 * FTPUtil
 * Exception
 *
 * @author COMSTER）山口　英志
 * @version 1.0 ( 2008/03/03 )
 *
 */
public class FtpUtilException extends IOException {

	/**
	 * 接続失敗
	 */
	public static final int CONNECT_FAIL = 1;

	/**
	 * ログイン失敗
	 */
	public static final int LOGIN_FAIL = 2;

	/**
	 * ディレクトリが存在しない
	 */
	public static final int DIRECTORY_NOT_FOUND = 3;

	/**
	 * ポートが開いていない
	 */
	public static final int PORT_NOT_OPRN = 4;

	/**
	 * エクセプション番号
	 */
	private int exceptionNumber_ = 0;

	/**
	 * コンストラクタ
	 * @param exceptionNumber
	 */
	public FtpUtilException(int exceptionNumber) {
		exceptionNumber_ = exceptionNumber;
	}

	/**
	 * コンストラクタ
	 * @param cause
	 */
	public FtpUtilException(Throwable cause) {
		initCause(cause);
	}

	/**
	 * エラーメッセージを取得します
	 * @return
	 */
	public String getMessage() {
		if (exceptionNumber_ == CONNECT_FAIL)
			return "FTPサーバーに接続が失敗しました。";
		if (exceptionNumber_ == LOGIN_FAIL)
			return "FTPサーバーにログインが失敗しました。";
		if (exceptionNumber_ == DIRECTORY_NOT_FOUND)
			return "転送先ディレクトリが存在しません。";
		if (exceptionNumber_ == PORT_NOT_OPRN)
			return "接続先のポートが開いていません。";
		return super.getMessage();
	}

	/**
	 * ユーザー判別のエラーを返します。
	 */
	public boolean isBusinessException() {
		return exceptionNumber_ > 0;
	}

}
