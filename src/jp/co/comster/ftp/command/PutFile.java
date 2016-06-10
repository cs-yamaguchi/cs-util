package jp.co.comster.ftp.command;

import java.io.FileInputStream;
import java.io.IOException;

import jp.co.comster.ftp.FtpConfig;
import jp.co.comster.ftp.FtpUtilException;

/**
 * ＦＴＰユーティリティ
 * 指定したパスの指定したファイルを転送します。
 *
 * @author COMSTER）山口　英志
 * @version 1.0 ( 2008/04/21 )
 *
 */
public class PutFile extends AbstractFtpCommand {

	/**
	 * ローカルフルパスファイル名
	 */
	private String localFullAddressFileName_;

	/**
	 * サーバーフルパスファイル名
	 */
	private String serverFullAddressFileName_;

	/**
	 * 成功
	 */
	private boolean isSuccess_;

	/**
	 * コンストラクタ
	 * @param ftpConfig
	 */
	public PutFile(FtpConfig ftpConfig) {
		super(ftpConfig);
	}

	/**
	 * FTP実行メソッド
	 * @exception FtpUtilException
	 */
	protected void execute() throws IOException {
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(localFullAddressFileName_);
			isSuccess_ = super.ftpClient_.storeFile(serverFullAddressFileName_, fileInputStream);
		} finally {
			if (fileInputStream != null)
				fileInputStream.close();
		}
	}

	/**
	 * @param localFullAddressFileName 設定する localFullAddressFileName
	 */
	public void setLocalFullAddressFileName(String localFullAddressFileName) {
		localFullAddressFileName_ = localFullAddressFileName;
	}

	/**
	 * @param serverFullAddressFileName 設定する serverFullAddressFileName
	 */
	public void setServerFullAddressFileName(String serverFullAddressFileName) {
		serverFullAddressFileName_ = serverFullAddressFileName;
	}

	/**
	 * @return isSuccess
	 */
	public boolean isSuccess() {
		return isSuccess_;
	}
}
