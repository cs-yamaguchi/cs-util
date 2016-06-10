package jp.co.comster.mail;

/**
 * 添付ファイル
 *
 * @author COMSTER）山口　英志
 * @version 1.0 ( 2010/05/11 )
 *
 */
public class AttachmentFile {

	/**
	 * filename
	 */
	private String fileName_;

	/**
	 * filetext
	 */
	private String fileText_;

	/**
	 * mime
	 */
	private String mimeType_ = "text/plain";

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName_;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		fileName_ = fileName;
	}

	/**
	 * @return the fileText
	 */
	public String getFileText() {
		return fileText_;
	}

	/**
	 * @param fileText the fileText to set
	 */
	public void setFileText(String fileText) {
		fileText_ = fileText;
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

}
