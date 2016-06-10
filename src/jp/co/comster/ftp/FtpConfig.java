package jp.co.comster.ftp;

/**
 * FTPの接続設定を保持します。
 *
 * @author COMSTER）山口　英志
 * @version 1.0 ( 2008/03/28 )
 *
 */
public class FtpConfig {

	/**
	 * FTP転送モード
	 */
	private String mode_;

	/**
	 * FTPユーザー
	 */
	private String user_;

	/**
	 * FTPパスワード
	 */
	private String password_;

	/**
	 * FTPポート
	 */
	private Integer port_;

	/**
	 * FTPホスト
	 */
	private String host_;

	/**
	 * @return host
	 */
	public String getHost() {
		return host_;
	}

	/**
	 * @param host 設定する host
	 */
	public void setHost(String host) {
		host_ = host;
	}

	/**
	 * @return mode
	 */
	public String getMode() {
		return mode_;
	}

	/**
	 * @param mode 設定する mode
	 */
	public void setMode(String mode) {
		mode_ = mode;
	}

	/**
	 * @return password
	 */
	public String getPassword() {
		return password_;
	}

	/**
	 * @param password 設定する password
	 */
	public void setPassword(String password) {
		password_ = password;
	}

	/**
	 * @return port
	 */
	public Integer getPort() {
		return port_;
	}

	/**
	 * @param port 設定する port
	 */
	public void setPort(Integer port) {
		port_ = port;
	}

	/**
	 * @return user
	 */
	public String getUser() {
		return user_;
	}

	/**
	 * @param user 設定する user
	 */
	public void setUser(String user) {
		user_ = user;
	}

}
