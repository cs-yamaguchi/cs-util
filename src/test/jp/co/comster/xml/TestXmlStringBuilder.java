package test.jp.co.comster.xml;

import jp.co.comster.xml.meta.XmlConstructor;
import jp.co.comster.xml.meta.XmlNode;
import jp.co.comster.xml.writer.XmlStringBuilder;
import junit.framework.TestCase;

public class TestXmlStringBuilder extends TestCase {

	public TestXmlStringBuilder(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testFileRecordBuilder1() {

		final XmlNode[] parentNode = getTextNode();
		final XmlStringBuilder xmlStringBuilder = new XmlStringBuilder(parentNode);
		final String fileRecordBuilder = xmlStringBuilder.toString();
		System.out.println(fileRecordBuilder);

	}

	public void testFileRecordBuilder2() {

		final XmlNode[] parentNode = getTextNode();
		final XmlConstructor xmlConstructor = new XmlConstructor();
		xmlConstructor.setXmlVersion("1.0");
		xmlConstructor.setXmlEncording("EUCJP");
		xmlConstructor.setIndent("  ");
		xmlConstructor.setEncord("EUCJP");
		xmlConstructor.setNextLine("\r\n");
		final XmlStringBuilder xmlStringBuilder = new XmlStringBuilder(xmlConstructor, parentNode);
		xmlStringBuilder.setIndentIndex(-2);
		final String fileRecordBuilder = xmlStringBuilder.toString();
		System.out.println(fileRecordBuilder);
		final XmlConstructor xmlConstructor2 = parentNode[0].getXmlConstructor();
		assertEquals(xmlConstructor.getEncord(), xmlConstructor2.getEncord());
		assertEquals(xmlStringBuilder.getIndentIndex(), -2);
	}

	private XmlNode[] getTextNode() {

		final XmlNode[] principal1 = new XmlNode[] {
			new XmlNode("顧客メールアド", "0000000001"),
			new XmlNode("nulltest", (String)null),
		};

		final XmlNode[] itemNode1 = new XmlNode[] {
			new XmlNode("ITEM連番", "0000000001"),
			new XmlNode("本人", principal1),
		};

		itemNode1[0].addAttribute("test1", "attribute1-1");
		itemNode1[0].addAttribute("test2", "attribute1-2");

		final XmlNode[] principal2 = new XmlNode[] {
			new XmlNode("顧客メールアド", "0000000001"),
		};

		final XmlNode[] itemNode2 = new XmlNode[] {
			new XmlNode("ITEM連番", "0000000002"),
			new XmlNode("本人", principal2),
		};
		itemNode2[0].addAttribute("test1", "attribute2-1");
		itemNode2[0].addAttribute("test2", "attribute2-2");

		final XmlNode[] itemTitle = new XmlNode[] {
			new XmlNode("ITEM日付", "20070517"),
			new XmlNode("ITEM件数", "000000117"),
			new XmlNode("システム識別ＩＤ", "008"),
			new XmlNode("処理振替ＩＤ", "0000"),
			new XmlNode("入手ＴＳ", "ｘｘｘ"),
			new XmlNode("ITEM", itemNode1),
			new XmlNode("ITEM", itemNode2),
		};

		itemTitle[5].addAttribute("index", "1");
		itemTitle[6].addAttribute("index", "2");

		final XmlNode[] headNode = new XmlNode[] {
			new XmlNode("HEAD", itemTitle),
		};

		final XmlNode[] parentNode = new XmlNode[] {
			new XmlNode("家族ＤＢ共通ＩＦ", headNode),
		};
		return parentNode;
	}

}
