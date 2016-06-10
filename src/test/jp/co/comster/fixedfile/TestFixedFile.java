package test.jp.co.comster.fixedfile;

import jp.co.comster.fixedfile.FixedFileLayoutMeta;
import jp.co.comster.fixedfile.FixedFileMaker;
import junit.framework.TestCase;

public class TestFixedFile extends TestCase {

	public TestFixedFile(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testFixedFile() {
		new FixedFileMaker();
		final FixedFileLayoutMeta[] fixedFileLayoutMeta = new FixedFileLayoutMeta[] {
			new FixedFileLayoutMeta("hoge", 10, false),
			new FixedFileLayoutMeta(10),
		};

		final String fixedString = FixedFileMaker.toString(fixedFileLayoutMeta);

		int length = 0;

		for (int i = 0; i < fixedFileLayoutMeta.length; i++)
			length += fixedFileLayoutMeta[i].getLength();

		assertEquals(fixedString.length(), length);

	}

}
