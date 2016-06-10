package jp.co.comster.ftp.command;

import java.io.IOException;

import jp.co.comster.ftp.FtpConfig;
import jp.co.comster.ftp.FtpUtilException;


/**
 * ＦＴＰユーティリティ
 * 指定したパスに指定したファイルが存在するかを返します。
 *
 * @author COMSTER）山口　英志
 * @version 1.0 ( 2008/04/21 )
 *
 */
public class ExistsFile extends AbstractFtpCommand {

	/**
	 * 指定パス
	 */
	private String path_;
	/**
	 * 指定ファイル名
	 */
	private String fileName_;

	/**
	 * 結果値
	 */
	private boolean isExistsFile_;

	/**
	 * コンストラクタ
	 * @param ftpConfig
	 */
	public ExistsFile(FtpConfig ftpConfig) {
		super(ftpConfig);
	}

	/**
	 * FTP実行メソッド
	 * @exception FtpUtilException
	 */
	protected void execute() throws IOException {
		if (!super.ftpClient_.changeWorkingDirectory(path_))
			throw new FtpUtilException(FtpUtilException.DIRECTORY_NOT_FOUND);
		final String[] fileList = super.ftpClient_.listNames(path_);
		if (fileList == null) {
			isExistsFile_ = false;
			return;
		}
		for(int i = 0; i < fileList.length; i++)
			if (fileList[i].equals(path_ + fileName_)) {
				isExistsFile_ = true;
				return;
			}
	}

	/**
	 * @param fileName 設定する fileName
	 */
	public void setFileName(String fileName) {
		fileName_ = fileName;
	}

	/**
	 * @param path 設定する path
	 */
	public void setPath(String path) {
		path_ = path;
	}

	/**
	 * @return isExistsFile_
	 */
	public boolean isExistsFile() {
		return isExistsFile_;
	}

}
