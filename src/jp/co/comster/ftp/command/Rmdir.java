package jp.co.comster.ftp.command;

import java.io.IOException;

import jp.co.comster.ftp.FtpConfig;
import jp.co.comster.ftp.FtpUtilException;

/**
 * ＦＴＰユーティリティ
 * ディレクトリを削除します。
 *
 * @author COMSTER）山口　英志
 * @version 1.0 ( 2008/04/21 )
 *
 */
public class Rmdir extends AbstractFtpCommand {

	/**
	 * フルパスディレクトリ名
	 */
	private String removeDirectoryName_;

	/**
	 * 成功コード
	 */
	public static final int SUCCESS_CODE = 250;

	/**
	 * 成功
	 */
	private boolean isSuccess_;

	/**
	 * コンストラクタ
	 * @param ftpConfig
	 */
	public Rmdir(FtpConfig ftpConfig) {
		super(ftpConfig);
	}

	/**
	 * FTP実行メソッド
	 * @exception FtpUtilException
	 */
	protected void execute() throws IOException {
		final int statusCode = ftpClient_.rmd(removeDirectoryName_);
		isSuccess_  = (statusCode == SUCCESS_CODE);
	}

	/**
	 * @param removeDirectoryName 設定する removeDirectoryName
	 */
	public void setRemoveDirectoryName(String removeDirectoryName) {
		removeDirectoryName_ = removeDirectoryName;
	}

	/**
	 * @return isSuccess
	 */
	public boolean isSuccess() {
		return isSuccess_;
	}

}
