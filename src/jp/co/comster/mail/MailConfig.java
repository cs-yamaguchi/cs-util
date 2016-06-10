package jp.co.comster.mail;

import java.util.ArrayList;
import java.util.List;

/**
 * JavaMailの送信設定を保持します。
 *
 * @author COMSTER）山口　英志
 * @version 1.0 ( 2008/03/28 )
 *
 */
public class MailConfig {

	/**
	 * SMTP
	 */
	private String smtp_;

	/**
	 * 送信元アドレス
	 */
	private String fromAddress_;

	/**
	 * 送信者名
	 */
	private String fromName_;

	/**
	 * メールエンコード
	 */
	private String encord_ = "iso-2022-jp";

	/**
	 * 送付先アドレス
	 */
	private String toAddress_;

	/**
	 * ＣＣ
	 */
	private String toCC_;

	/**
	 * ＢＣＣ
	 */
	private String toBCC_;


	/**
	 * mime
	 */
	private String mimeType_ = "text/plain";

	/**
	 * title
	 */
	private String title_;

	/**
	 * body
	 */
	private String body_;

	/**
	 * filename
	 */
	private String fileName_;

	/**
	 * filetext
	 */
	private String fileText_;

	/**
	 * Files
	 */
	private List<AttachmentFile> attachmentFiles_ = new ArrayList<AttachmentFile>();


	/**
	 * Debug
	 */
	private Boolean debug_ = new Boolean(false);

	/**
	 * @return encord
	 */
	public String getEncord() {
		return encord_;
	}

	/**
	 * @param encord 設定する encord
	 */
	public void setEncord(String encord) {
		encord_ = encord;
	}

	/**
	 * @return fromAddress
	 */
	public String getFromAddress() {
		return fromAddress_;
	}

	/**
	 * @param fromAddress 設定する fromAddress
	 */
	public void setFromAddress(String fromAddress) {
		fromAddress_ = fromAddress;
	}

	/**
	 * @return fromName
	 */
	public String getFromName() {
		return fromName_;
	}

	/**
	 * @param fromName 設定する fromName
	 */
	public void setFromName(String fromName) {
		fromName_ = fromName;
	}

	/**
	 * @return mimeType
	 */
	public String getMimeType() {
		return mimeType_;
	}

	/**
	 * @param mimeType 設定する mimeType
	 * text/html未対応
	 */
	public void setMimeType(String mimeType) {
		mimeType_ = mimeType;
	}

	/**
	 * @return smtp
	 */
	public String getSmtp() {
		return smtp_;
	}

	/**
	 * @param smtp 設定する smtp
	 */
	public void setSmtp(String smtp) {
		smtp_ = smtp;
	}

	/**
	 * @return toAddress
	 */
	public String getToAddress() {
		return toAddress_;
	}

	/**
	 * @param toAddress 設定する toAddress
	 */
	public void setToAddress(String toAddress) {
		toAddress_ = toAddress;
	}

	/**
	 * @return body
	 */
	public String getBody() {
		return body_;
	}

	/**
	 * @param body 設定する body
	 */
	public void setBody(String body) {
		body_ = body;
	}

	/**
	 * @return fileName
	 */
	public String getFileName() {
		return fileName_;
	}

	/**
	 * @param fileName 設定する fileName
	 * 存在するファイルを送信する場合はフルパス
	 * テキストファイルを添付する場合は、
	 * ファイル名とfileTextをセット
	 *
	 */
	public void setFileName(String fileName) {
		fileName_ = fileName;
	}

	/**
	 * @return title
	 */
	public String getTitle() {
		return title_;
	}

	/**
	 * @param title 設定する title
	 */
	public void setTitle(String title) {
		title_ = title;
	}

	/**
	 * @return debug
	 */
	public Boolean getDebug() {
		return debug_;
	}

	/**
	 * @param debug 設定する debug
	 */
	public void setDebug(Boolean debug) {
		debug_ = debug;
	}

	/**
	 * @return toBCC
	 */
	public String getToBCC() {
		return toBCC_;
	}

	/**
	 * @param toBCC 設定する toBCC
	 */
	public void setToBCC(String toBCC) {
		toBCC_ = toBCC;
	}

	/**
	 * @return toCC
	 */
	public String getToCC() {
		return toCC_;
	}

	/**
	 * @param toCC 設定する toCC
	 */
	public void setToCC(String toCC) {
		toCC_ = toCC;
	}

	/**
	 * @return fileText
	 */
	public String getFileText() {
		return fileText_;
	}

	/**
	 * @param fileText 設定する fileText
	 */
	public void setFileText(String fileText) {
		fileText_ = fileText;
	}

	/**
	 * @return 添付ファイルリスト
	 */
	public List<AttachmentFile> getAttachmentFiles() {
		return attachmentFiles_;
	}

	/**
	 * @param 添付ファイルリスト
	 */
	public void setAttachmentFiles(List<AttachmentFile> attachmentFiles) {
		attachmentFiles_ = attachmentFiles;
	}

	/**
	 * @param 添付ファイルへ追加
	 */
	public void addAttachmentFiles(AttachmentFile attachmentFile) {
		attachmentFiles_.add(attachmentFile);
	}

	/**
	 * @param 添付ファイルを取得
	 */
	public AttachmentFile getAttachmentFile(int index) {
		if (attachmentFiles_.size() < index)
			return null;
		return (AttachmentFile)attachmentFiles_.get(index);
	}

}
