package jp.co.comster.ftp.command;

import java.io.IOException;

import jp.co.comster.ftp.FtpConfig;
import jp.co.comster.ftp.FtpUtilException;

/**
 * ＦＴＰユーティリティ
 * 指定したファイルを削除します。
 *
 * @author COMSTER）山口　英志
 * @version 1.0 ( 2008/04/21 )
 *
 */
public class DeleteFile extends AbstractFtpCommand {

	/**
	 * 削除対象フルパスファイル名
	 */
	private String deleteFullAddressFileName_;

	/**
	 * 成功
	 */
	private boolean isSuccess_;

	/**
	 * コンストラクタ
	 * @param ftpConfig
	 */
	public DeleteFile(FtpConfig ftpConfig) {
		super(ftpConfig);
	}

	/**
	 * FTP実行メソッド
	 * @exception FtpUtilException
	 */
	protected void execute() throws IOException {
		isSuccess_ = ftpClient_.deleteFile(deleteFullAddressFileName_);
	}

	/**
	 * @param deleteFullAddressFileName 設定する deleteFullAddressFileName
	 */
	public void setDeleteFullAddressFileName(String deleteFullAddressFileName) {
		deleteFullAddressFileName_ = deleteFullAddressFileName;
	}

	/**
	 * @return isSuccess
	 */
	public boolean isSuccess() {
		return isSuccess_;
	}

}
