package test.jp.co.comster.xml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpURL;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.xml.sax.SAXException;

import jp.co.comster.xml.meta.XmlNode;
import jp.co.comster.xml.reader.XmlFileParser;
import jp.co.comster.xml.writer.XmlStringBuilder;
import junit.framework.TestCase;

public class TestXmlFileParser extends TestCase {

	public TestXmlFileParser(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testToMeta() throws SAXException, IOException {
		new XmlFileParser();

		final String file = "sample/WP_sample_xml.dat";

		final XmlNode[] toMeta = XmlFileParser.toMeta(file);

		final XmlStringBuilder xmlStringBuilder = new XmlStringBuilder(toMeta);
		final String fileRecordBuilder = xmlStringBuilder.toString();
		System.out.println(fileRecordBuilder);

	}

	public void testToMeta2() throws SAXException, IOException {
		new XmlFileParser();

		final String file = "sample/WP_sample_xml2.dat";

		final XmlNode[] toMeta = XmlFileParser.toMeta(file);

		final XmlStringBuilder xmlStringBuilder = new XmlStringBuilder(toMeta);
		final String fileRecordBuilder = xmlStringBuilder.toString();
		System.out.println(fileRecordBuilder);

	}

	public void testToMeta3() throws SAXException, IOException {
		new XmlFileParser();

		final String file = "sample/WP_sample_xml3.dat";

		final XmlNode[] toMeta = XmlFileParser.toMeta(file);

		final XmlStringBuilder xmlStringBuilder = new XmlStringBuilder(toMeta);
		final String fileRecordBuilder = xmlStringBuilder.toString();
		System.out.println(fileRecordBuilder);

	}

	public void testToMeta4() throws SAXException, IOException {
		new XmlFileParser();

		final String file = "sample/WP_sample_xml4.dat";

		final XmlNode[] toMeta = XmlFileParser.toMeta(file);
		assertEquals("NODE1", toMeta[0].getKey());
		assertFalse(toMeta[0].isNull());
		assertEquals("", toMeta[0].getValue());
		assertNull(toMeta[0].getChildNode());

		final XmlStringBuilder xmlStringBuilder = new XmlStringBuilder(toMeta);
		final String fileRecordBuilder = xmlStringBuilder.toString();
		System.out.println(fileRecordBuilder);

	}

//	private final static String HOST			= "webservices.amazon.co.jp";
	private final static String HOST			= "rss.rssad.jp";
	private final static int PORT				= 80;
//	private final static String PATH			= "/onca/xml";
	private final static String PATH			= "/rss/itmatmarkit/rss2dc.xml";
//	private final static String SERVICE			= "AWSECommerceService";
//	private final static String ACCESS_KEY_ID	= "0Q4E466VFFFWGEFK1H02";
//	private final static String PROXY_NAME		= "sf-proxy.bc-extra.net";
//	private final static int PROXY_PORT			= 8080;
//	private final static String API_VERSION		= "2008-06-26";

	public void testToMetaFromStream() throws SAXException, IOException {

		final List<NameValuePair> list = new ArrayList<NameValuePair>();
//		list.add(new NameValuePair("Service"		,SERVICE				));
//		list.add(new NameValuePair("AWSAccessKeyId"	,ACCESS_KEY_ID			));
//		list.add(new NameValuePair("Version"		,API_VERSION			));
		list.add(new NameValuePair("ContentType"	,"text/xml"				));
//		list.add(new NameValuePair("ResponseGroup"	,"ItemAttributes,SalesRank,BrowseNodes,Images"));
//		list.add(new NameValuePair("Operation"		,"ItemSearch"			));
//		list.add(new NameValuePair("SearchIndex"	,"Books"				));
//		list.add(new NameValuePair("Keywords"		,"java"					));
//		list.add(new NameValuePair("ItemPage"		,Integer.toString(1)	));

		final HttpMethod method = new GetMethod();
		method.setURI(new HttpURL(HOST, PORT, PATH));
		method.setQueryString((NameValuePair[])list.toArray(new NameValuePair[0]));

		final HttpClient client = new HttpClient();
//		client.getHostConfiguration().setProxy(PROXY_NAME, PROXY_PORT);
//		client.getState().setProxyCredentials(new AuthScope(PROXY_NAME, PROXY_PORT), null);
		client.executeMethod(method);

		final XmlNode[] toMeta = XmlFileParser.toMeta(method.getResponseBodyAsStream());

		final XmlStringBuilder xmlStringBuilder = new XmlStringBuilder(toMeta);
		final String fileRecordBuilder = xmlStringBuilder.toString();
		System.out.println(fileRecordBuilder);

	}

	public void testNodeCount1() throws SAXException, IOException {
		final String file = "sample/WP_sample_xml.dat";
		final int toMeta = XmlFileParser.nodeCount(file, "ITEM");
		System.out.println("ITEM = " + toMeta);
		assertEquals(3, toMeta);
	}

	public void testNodeCount2() throws SAXException, IOException {
		final String file = "sample/WP_sample_xml.dat";
		final int toMeta = XmlFileParser.nodeCount(file, "削除F");
		System.out.println("ITEM = " + toMeta);
		assertEquals(6, toMeta);
	}

	public void testNodeCount3() throws SAXException, IOException {
		final String file = "sample/WP_sample_xml.dat";
		final int toMeta = XmlFileParser.nodeCount(file, "NOTHING");
		System.out.println("ITEM = " + toMeta);
		assertEquals(0, toMeta);
	}

}
