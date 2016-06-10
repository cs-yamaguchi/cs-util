package jp.co.comster.xml.reader;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.xerces.dom.DeferredDocumentImpl;
import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import jp.co.comster.xml.meta.XmlConstructor;
import jp.co.comster.xml.meta.XmlNode;

/**
 * ＸＭＬファイルを読み込みます。
 *
 * @author COMSTER）山口　英志
 * @version 1.0 ( 2008/03/19 )
 * @see http://xerces.apache.org/xerces-j/<br>
 * resolver.jar
 * serializer.jar
 * xercesImpl.jar
 * xercesSamples.jar
 * xml-apis.jar
 */
public class XmlFileParser {

	/**
	 * ＸＭＬファイル名からＸＭＬメタデータ定義配列を返す
	 * @param fileName
	 * @return
	 * @throws IOException
	 * @throws SAXException
	 */
	public static XmlNode[] toMeta(String fileName) throws SAXException, IOException {
		final Document document = getDocument(fileName);
		final String xmlEncording = ((DeferredDocumentImpl)document).getXmlEncoding();
		final String xmlVersion = ((DeferredDocumentImpl)document).getXmlVersion();
		final XmlNode[] xmlNodeBuilder = xmlNodeBuilder(document.getChildNodes());
		setXmlConstructor(xmlNodeBuilder, xmlEncording, xmlVersion);
		return xmlNodeBuilder;
	}

	/**
	 * ＸＭＬファイル名からＸＭＬメタデータ定義配列を返す
	 * @param fileName
	 * @return
	 * @throws IOException
	 * @throws SAXException
	 */
	public static XmlNode[] toMeta(InputStream inputStream) throws SAXException, IOException {
		final Document document = getDocument(inputStream);
		final String xmlEncording = ((DeferredDocumentImpl)document).getXmlEncoding();
		final String xmlVersion = ((DeferredDocumentImpl)document).getXmlVersion();
		final XmlNode[] xmlNodeBuilder = xmlNodeBuilder(document.getChildNodes());
		setXmlConstructor(xmlNodeBuilder, xmlEncording, xmlVersion);
		return xmlNodeBuilder;
	}

	/**
	 * xmlNodeBuilderにXmlConstructorを付加します
	 * @param xmlNodeBuilder
	 * @param xmlEncording
	 * @param xmlVersion
	 */
	private static void setXmlConstructor(XmlNode[] xmlNodeBuilder, String xmlEncording, String xmlVersion) {
		final XmlConstructor xmlConstructor = new XmlConstructor();
		xmlConstructor.setXmlEncording(xmlEncording);
		xmlConstructor.setXmlVersion(xmlVersion);
		for (int i = 0; i < xmlNodeBuilder.length; i++)
			xmlNodeBuilder[i].setXmlConstructor(xmlConstructor);
	}


	/**
	 * ＸＭＬファイル名からノード名の集計値を返す
	 * @param fileName
	 * @param nodeName
	 * @return
	 * @throws IOException
	 * @throws SAXException
	 */
	public static int nodeCount(String fileName, String nodeName) throws SAXException, IOException {
		final Document document = getDocument(fileName);
		int counter = 0;
		final NodeList nodeList = document.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++)
			counter += xmlNodeCounter(nodeList, nodeName);
		return counter;
	}

	/**
	 * XMLDocumentを取得します。
	 * @param fileName
	 * @return
	 * @throws SAXException
	 * @throws IOException
	 */
	private static Document getDocument(String fileName) throws SAXException, IOException {
		final DOMParser parser = new DOMParser();
		parser.parse(fileName);
		final Document document = parser.getDocument();
		return document;
	}

	/**
	 * XMLDocumentを取得します。
	 * @param fileName
	 * @return
	 * @throws IOException
	 * @throws SAXException
	 * @throws SAXException
	 * @throws IOException
	 */
	private static Document getDocument(InputStream inputStream) throws SAXException, IOException{
		final DOMParser parser = new DOMParser();
		InputSource inputSource = new InputSource(inputStream);
		parser.parse(inputSource);
		final Document document = parser.getDocument();
		return document;
	}

	/**
	 * DOMからXmlNode配列を返します。
	 * @param nodeList
	 * @return
	 */
	private static XmlNode[] xmlNodeBuilder(NodeList nodeList) {
		final List<XmlNode> xmlNodeList = new ArrayList<XmlNode>();
		for (int i = 0; i < nodeList.getLength(); i++) {
			final Node node = nodeList.item(i);
			if (node.hasChildNodes()) {
				if (isValueNode(node))
					xmlNodeList.add(new XmlNode(node.getNodeName(), node.getChildNodes().item(0).getNodeValue()));
				else
					xmlNodeList.add(new XmlNode(node.getNodeName(), xmlNodeBuilder(node.getChildNodes())));
			} else {
				if (!node.getNodeName().startsWith("#"))
					xmlNodeList.add(new XmlNode(node.getNodeName(), ""));
			}
			final NamedNodeMap attrs = node.getAttributes();
			if (attrs != null) {
				for (int j = 0; j < attrs.getLength(); j++) {
					final Node attr = attrs.item(j);
					((XmlNode)xmlNodeList.get(xmlNodeList.size() - 1)).addAttribute(attr.getNodeName(), attr.getNodeValue());
				}
			}
		}
		return (XmlNode[])xmlNodeList.toArray(new XmlNode[0]);
	}

	/**
	 * 値を保持したノードか、子ノードを持つノードかを判別します。
	 * @param node
	 * @return
	 */
	private static boolean isValueNode(final Node node) {
		if (node.getChildNodes().getLength() != 1)
		 return false;
		if (!node.getChildNodes().item(0).getNodeName().startsWith("#"))
			 return false;
		return true;
	}

	/**
	 * DOMからノード名の集計値を返します。
	 * @param nodeList
	 * @param nodeName
	 * @return
	 */
	private static int xmlNodeCounter(NodeList nodeList, String nodeName) {
		int counter = 0;
		for (int i = 0; i < nodeList.getLength(); i++) {
			final Node node = nodeList.item(i);
			if (node.getNodeName().equals(nodeName))
				counter++;
			if (node.hasChildNodes()) {
				counter += xmlNodeCounter(node.getChildNodes(), nodeName);
			}
		}
		return counter;
	}

}
