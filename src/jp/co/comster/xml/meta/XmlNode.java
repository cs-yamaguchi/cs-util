package jp.co.comster.xml.meta;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * ＸＭＬファイル作成用　メタデータ定義
 *
 * @author COMSTER）山口　英志
 * @version 1.0 ( 2008/03/17 )
 *
 */
public class XmlNode {

	/**
	 * XmlConstructor
	 */
	private XmlConstructor xmlConstructor_ = null;

	/**
	 * 空白項目
	 */
	private boolean isNull_ = false;

	/**
	 * キー
	 */
	private String key_;

	/**
	 * 値
	 */
	private String value_;

	/**
	 * 子ノード
	 */
	private XmlNode[] childNode_ = null;

	/**
	 * 属性
	 */
	private Map<String, String> attribute_ = new LinkedHashMap<String, String>();

	/**
	 * コンストラクタ
	 * @param key XMLキー
	 * @param value XML値
	 */
	public XmlNode(String key, String value) {
		isNull_ = value == null;
		key_ = key;
		value_ = value;
	}

	/**
	 * コンストラクタ
	 * @param key XMLキー
	 * @param childNode XMLノード
	 */
	public XmlNode(String key, XmlNode[] childNode) {
		key_ = key;
		childNode_ = childNode;
	}

	/**
	 * 項目の値を取得します。
	 * @return
	 */
	public String getTag() {
		return getTag(0);
	}

	/**
	 * 項目の値を取得します。
	 * @param depth
	 * @return
	 */
	public String getTag(int depth) {
		if (isNull_)
			return "";
		if (childNode_ == null)
			return getDepth(depth) + "<" + key_ + getAttribute() + ">" + value_ + "</" + key_ + ">" + xmlConstructor_.getNextLine();
		final StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(getDepth(depth) + "<" + key_ + getAttribute() + ">" + xmlConstructor_.getNextLine());
		for (int i = 0; i < childNode_.length; i++)
			stringBuffer.append(childNode_[i].getTag(depth + 1));
		stringBuffer.append(getDepth(depth) + "</" + key_ + ">" + xmlConstructor_.getNextLine());
		return stringBuffer.toString();
	}

	/**
	 * ノードの深さ分インデントを返します。
	 * @param depth
	 * @return
	 */
	private String getDepth(int depth) {
		final StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < depth; i++)
			stringBuffer.append(xmlConstructor_.getIndent());
		return stringBuffer.toString();
	}

	/**
	 * 属性を出力します。
	 * @return
	 */
	private String getAttribute() {
		final StringBuffer stringBuffer = new StringBuffer();
		for(Iterator<Map.Entry<String, String>> iterator = attribute_.entrySet().iterator(); iterator.hasNext();) {
			final Map.Entry<String, String> mapEntry = (Map.Entry<String, String>)iterator.next();
			stringBuffer.append(" ");
			stringBuffer.append(mapEntry.getKey());
			stringBuffer.append("=");
			stringBuffer.append("\"" + mapEntry.getValue() + "\"");
		}
		return stringBuffer.toString();
	}

	/**
	 * 属性を追加します。
	 * @param key
	 * @param value
	 */
	public void addAttribute(String key, String value) {
		attribute_.put(key, value);
	}

	/**
	 * xmlConstructor を取得する
	 */
	public XmlConstructor getXmlConstructor() {
		return xmlConstructor_;
	}

	/**
	 * @param xmlConstructor 設定する xmlConstructor
	 */
	public void setXmlConstructor(XmlConstructor xmlConstructor) {
		xmlConstructor_ = xmlConstructor;
		if (childNode_ != null)
			for (int i = 0; i < childNode_.length; i++)
				childNode_[i].setXmlConstructor(xmlConstructor);
	}

	/**
	 * @return the isNull
	 */
	public boolean isNull() {
		return isNull_;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key_;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value_;
	}

	/**
	 * @return the childNode
	 */
	public XmlNode[] getChildNode() {
		return childNode_;
	}

}
