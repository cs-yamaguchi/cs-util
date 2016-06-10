package jp.co.comster.xml.writer;

import jp.co.comster.xml.meta.XmlConstructor;
import jp.co.comster.xml.meta.XmlNode;


/**
 * ＸＭＬメタデータ定義配列から、ＸＭＬファイルを生成します。
 *
 * @author COMSTER）山口　英志
 * @version 1.0 ( 2008/03/17 )
 *
 */
public class XmlStringBuilder {

	/**
	 * XMLConstructor
	 */
	private XmlConstructor xmlConstructor_;

	/**
	 * XMLConstructor
	 */
	private XmlNode[] xmlNode_;

	/**
	 * indentIndex
	 */
	private int indentIndex_ = 0;

	/**
	 * コンストラクタ
	 * @param xmlNode
	 */
	public XmlStringBuilder(XmlNode[] xmlNode) {
		xmlConstructor_ = new XmlConstructor();
		if (xmlNode.length > 0)
			if (xmlNode[0].getXmlConstructor() != null)
				xmlConstructor_ = xmlNode[0].getXmlConstructor();
		xmlNode_ = initilizeXmlNode(xmlConstructor_, xmlNode);
	}

	/**
	 * コンストラクタ
	 * @param xmlConstructor
	 * @param xmlNode
	 */
	public XmlStringBuilder(XmlConstructor xmlConstructor, XmlNode[] xmlNode) {
		xmlConstructor_ = xmlConstructor;
		xmlNode_ = initilizeXmlNode(xmlConstructor_, xmlNode);
	}

	/**
	 * XmlNodeにXmlConstructorをセットする
	 * @param xmlConstructor
	 * @param xmlNode
	 * @return
	 */
	private XmlNode[] initilizeXmlNode(XmlConstructor xmlConstructor, XmlNode[] xmlNode) {
		for (int i = 0; i < xmlNode.length; i++)
			xmlNode[i].setXmlConstructor(xmlConstructor);
		return xmlNode;
	}

	/**
	 * ＸＭＬメタデータ定義配列を元にＸＭＬファイルを生成する
	 * @param xmlFileLayoutMeta
	 * @return
	 */
	public String toString() {
		final StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(xmlConstructor_.getHead() + xmlConstructor_.getNextLine());
		for (int i = 0; i < xmlNode_.length; i++)
			if (indentIndex_ == 0)
				stringBuffer.append(xmlNode_[i].getTag());
			else
				stringBuffer.append(xmlNode_[i].getTag(indentIndex_));
		return stringBuffer.toString();
	}

	/**
	 * @return indentIndex
	 */
	public int getIndentIndex() {
		return indentIndex_;
	}

	/**
	 * @param indentIndex 設定する indentIndex
	 */
	public void setIndentIndex(int indentIndex) {
		indentIndex_ = indentIndex;
	}

}
