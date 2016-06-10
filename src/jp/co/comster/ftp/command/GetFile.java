package jp.co.comster.ftp.command;

import java.io.FileOutputStream;
import java.io.IOException;

import jp.co.comster.ftp.FtpConfig;
import jp.co.comster.ftp.FtpUtilException;

/**
 * ＦＴＰユーティリティ
 * 指定したパスの指定したファイルを取得します。
 *
 * @author COMSTER）山口　英志
 * @version 1.0 ( 2008/04/22 )
 *
 */
public class GetFile extends AbstractFtpCommand {

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
	public GetFile(FtpConfig ftpConfig) {
		super(ftpConfig);
	}

	/**
	 * FTP実行メソッド
	 * @exception FtpUtilException
	 */
	protected void execute() throws IOException {
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(localFullAddressFileName_);
			isSuccess_ = super.ftpClient_.retrieveFile(serverFullAddressFileName_, fileOutputStream);
		} finally {
			if (fileOutputStream != null)
				fileOutputStream.close();
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
