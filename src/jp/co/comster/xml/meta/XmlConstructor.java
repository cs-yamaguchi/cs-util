package jp.co.comster.xml.meta;

/**
 * ＸＭＬファイルを生成する為の定義をセットします。
 *
 * @author COMSTER）山口　英志
 * @version 1.0 ( 2008/03/17 )
 *
 */
public class XmlConstructor {

	/**
	 * XML改行
	 */
	private static final String NEXT_LINE = "\n";

	/**
	 * XMLインデント
	 */
	private static final String INDENT = "\t";

	/**
	 * XMLエンコード
	 */
	private static final String ENCORD = "MS932";

	/**
	 * XMLバージョン
	 */
	private String xmlVersion_ = "1.0";

	/**
	 * XMLエンコーディング
	 */
	private String xmlEncording_ = "Windows-31J";


	/**
	 * XML改行
	 */
	private String nextLine_ = NEXT_LINE;

	/**
	 * XMLインデント
	 */
	private String indent_ = INDENT;

	/**
	 * XML出力ファイルエンコード
	 */
	private String encord_ = ENCORD;

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
	 * @return head
	 */
	public String getHead() {
		return "<?xml version=\"__version__\" encoding=\"__encoding__\" ?>".replaceAll("__version__", xmlVersion_).replaceAll("__encoding__", xmlEncording_);
	}

	/**
	 * @return indent
	 */
	public String getIndent() {
		return indent_;
	}

	/**
	 * @param indent 設定する indent
	 */
	public void setIndent(String indent) {
		indent_ = indent;
	}

	/**
	 * @return nextLine
	 */
	public String getNextLine() {
		return nextLine_;
	}

	/**
	 * @param nextLine 設定する nextLine
	 */
	public void setNextLine(String nextLine) {
		nextLine_ = nextLine;
	}

	/**
	 * @param xmlVersion_ the xmlVersion to set
	 */
	public void setXmlVersion(String xmlVersion) {
		xmlVersion_ = xmlVersion;
	}

	/**
	 * @param xmlEncording_ the xmlEncording to set
	 */
	public void setXmlEncording(String xmlEncording) {
		xmlEncording_ = xmlEncording;
	}


}
