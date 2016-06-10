package jp.co.comster.ftp.command;

import java.io.IOException;

import jp.co.comster.ftp.FtpConfig;
import jp.co.comster.ftp.FtpUtilException;

/**
 * ＦＴＰユーティリティ
 * 指定したパスの指定したファイルを転送します。
 *
 * @author COMSTER）山口　英志
 * @version 1.0 ( 2008/04/21 )
 * @deprecated commons.netの応答レスポンスが遅い為
 *
 */
public class Touch extends AbstractFtpCommand {

	/**
	 * フルパス作成ファイル名
	 */
	private String touchFullAddressFileName_;

	/**
	 * 成功コード
	 */
	public static final int SUCCESS_CODE = 226;

	/**
	 * 成功
	 */
	private boolean isSuccess_;

	/**
	 * コンストラクタ
	 * @param ftpConfig
	 */
	public Touch(FtpConfig ftpConfig) {
		super(ftpConfig);
	}

	/**
	 * FTP実行メソッド
	 * @exception FtpUtilException
	 */
	protected void execute() throws IOException {
		final int statusCode = super.ftpClient_.stor(touchFullAddressFileName_);
		isSuccess_  = (statusCode == SUCCESS_CODE);
	}

	/**
	 * @param touchFullAddressFileName 設定する touchFullAddressFileName
	 */
	public void setTouchFullAddressFileName(String touchFullAddressFileName) {
		touchFullAddressFileName_ = touchFullAddressFileName;
	}

	/**
	 * @return isSuccess
	 */
	public boolean isSuccess() {
		return isSuccess_;
	}

}
