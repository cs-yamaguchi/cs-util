package jp.co.comster.ftp.command;

import java.io.IOException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.commons.net.ftp.FTPReply;

import jp.co.comster.ftp.FtpConfig;
import jp.co.comster.ftp.FtpUtilException;

/**
 * ＦＴＰユーティリティ
 * 基底クラス
 * @author COMSTER）山口　英志
 * @version 1.0 ( 2008/04/21 )
 *
 */
public abstract class AbstractFtpCommand {

	/**
	 * FTP接続設定
	 */
	protected FtpConfig ftpConfig_;

	/**
	 * FTPクライアント
	 */
	protected FTPClient ftpClient_;

	/**
	 * コンストラクタ
	 * @param ftpConfig
	 */
	public AbstractFtpCommand(FtpConfig ftpConfig) {
		ftpConfig_ = ftpConfig;
	}

	/**
	 * FTP実行メソッド
	 * @throws FtpUtilException
	 */
	protected abstract void execute() throws IOException;

	/**
	 * FTP処理を実行します。
	 * @throws FtpUtilException
	 */
	public void executeFileTransferProtcol() throws FtpUtilException {
		try {
			try {
				createConnectedClient();
				login();
				setFileType();
				execute();
			} finally {
				if (ftpClient_ != null && ftpClient_.isConnected())
					ftpClient_.disconnect();
			}
		} catch (IOException e) {
			if (e instanceof FtpUtilException)
				throw (FtpUtilException)e;
			throw new FtpUtilException(e);
		}
	}

	/**
	 * FTP転送のファイルタイプを指定します。
	 * @throws IOException
	 */
	private void setFileType() throws IOException {
		int fileType = FTP.BINARY_FILE_TYPE;
		if (ftpConfig_.getMode().equals("binary"))
			fileType = FTP.BINARY_FILE_TYPE;
		if (ftpConfig_.getMode().equals("ascii"))
			fileType = FTP.ASCII_FILE_TYPE;
		if (ftpConfig_.getMode().equals("image"))
			fileType = FTP.IMAGE_FILE_TYPE;
		if (ftpConfig_.getMode().equals("ebcdic"))
			fileType = FTP.EBCDIC_FILE_TYPE;
		if (ftpConfig_.getMode().equals("local"))
			fileType = FTP.LOCAL_FILE_TYPE;
		ftpClient_.setFileType(fileType);
	}

	/**
	 * 接続しているFTPサーバーへログインします。
	 * @throws IOException
	 */
	private void login() throws IOException {
		if (!ftpClient_.login(ftpConfig_.getUser(), ftpConfig_.getPassword()))
			throw new FtpUtilException(FtpUtilException.LOGIN_FAIL);
	}

	/**
	 * FTPコネクションをオープンします。
	 * @return
	 * @throws FtpUtilException
	 */
	private void createConnectedClient() throws IOException {
		ftpClient_ = new FTPClient();
		try {
			if (ftpConfig_.getPort() == null)
				ftpClient_.connect(ftpConfig_.getHost());
			else
				ftpClient_.connect(ftpConfig_.getHost(), ftpConfig_.getPort().intValue());
		} catch (FTPConnectionClosedException e) {
			throw new FtpUtilException(FtpUtilException.PORT_NOT_OPRN);
		}
		int reply = ftpClient_.getReplyCode();
		if (!FTPReply.isPositiveCompletion(reply))
			throw new FtpUtilException(FtpUtilException.CONNECT_FAIL);

	}

}
