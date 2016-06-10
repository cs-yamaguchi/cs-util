package jp.co.comster.ftp.command;

import java.io.IOException;

import jp.co.comster.ftp.FtpConfig;
import jp.co.comster.ftp.FtpUtilException;


/**
 * ＦＴＰユーティリティ
 * 指定したパスに存在するファイルリストを返す。
 *
 * @author COMSTER）山口　英志
 * @version 1.0 ( 2008/04/22 )
 *
 */
public class Ls extends AbstractFtpCommand {

	/**
	 * 指定パス
	 */
	private String path_;

	/**
	 * 結果値
	 */
	private String[] existsFileNames_;

	/**
	 * コンストラクタ
	 * @param ftpConfig
	 */
	public Ls(FtpConfig ftpConfig) {
		super(ftpConfig);
	}

	/**
	 * FTP実行メソッド
	 * @exception FtpUtilException
	 */
	protected void execute() throws IOException {
		if (!super.ftpClient_.changeWorkingDirectory(path_))
			throw new FtpUtilException(FtpUtilException.DIRECTORY_NOT_FOUND);
		existsFileNames_ = super.ftpClient_.listNames(path_);
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
	public String[] getExistsFileNames() {
		return existsFileNames_;
	}

}
