package jp.co.comster.ftp.command;

import java.io.IOException;

import jp.co.comster.ftp.FtpConfig;
import jp.co.comster.ftp.FtpUtilException;

/**
 * ＦＴＰユーティリティ
 * ディレクトリを作成します。
 *
 * @author COMSTER）山口　英志
 * @version 1.0 ( 2008/04/21 )
 *
 */
public class Mkdir extends AbstractFtpCommand {

	/**
	 * フルパスディレクトリ名
	 */
	private String makeDirectoryName_;

	/**
	 * 成功コード
	 */
	public static final int SUCCESS_CODE = 257;

	/**
	 * 成功
	 */
	private boolean isSuccess_;

	/**
	 * コンストラクタ
	 * @param ftpConfig
	 */
	public Mkdir(FtpConfig ftpConfig) {
		super(ftpConfig);
	}

	/**
	 * FTP実行メソッド
	 * @exception FtpUtilException
	 */
	protected void execute() throws IOException {
		final int statusCode = ftpClient_.mkd(makeDirectoryName_);
		isSuccess_  = (statusCode == SUCCESS_CODE);
	}

	/**
	 * @param makeDirectoryName 設定する makeDirectoryName
	 */
	public void setMakeDirectoryName(String makeDirectoryName) {
		makeDirectoryName_ = makeDirectoryName;
	}

	/**
	 * @return isSuccess
	 */
	public boolean isSuccess() {
		return isSuccess_;
	}

}
