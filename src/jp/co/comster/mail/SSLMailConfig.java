package jp.co.comster.mail;

/**
 * JavaMailのSSL送信設定を保持します。
 *
 * @author COMSTER）山口　英志
 * @version 1.0 ( 2015/12/29 )
 *
 */
public class SSLMailConfig extends MailConfig {

	/**
	 * SSLPORT
	 */
	private String sslPort_ = "465";

	/**
	 * SSLユーザー
	 */
	private String sslUser_;

	/**
	 * SSLパス
	 */
	private String sslPass_;

	/**
	 * @return sslPort
	 */
	public String getSslPort() {
		return sslPort_;
	}

	/**
	 * @param sslPort 設定する sslPort
	 */
	public void setSslPort(String sslPort) {
		this.sslPort_ = sslPort;
	}

	/**
	 * @return sslUser
	 */
	public String getSslUser() {
		return sslUser_;
	}

	/**
	 * @param sslUser 設定する sslUser
	 */
	public void setSslUser(String sslUser) {
		this.sslUser_ = sslUser;
	}

	/**
	 * @return sslPass
	 */
	public String getSslPass() {
		return sslPass_;
	}

	/**
	 * @param pass 設定する pass
	 */
	public void setSslPass(String sslPass) {
		this.sslPass_ = sslPass;
	}


}
