package test.jp.co.comster.geocode;

import jp.co.comster.geocode.GeocodeUtil;
import jp.co.comster.geocode.method.BesselMethod;
import jp.co.comster.geocode.method.Grs80Method;
import jp.co.comster.geocode.method.Wgs84Method;
import jp.co.comster.geocode.model.GeocodeModel;
import junit.framework.TestCase;

public class TestGeocodeUtil extends TestCase {

	public TestGeocodeUtil(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testCalcDistance() {
//		final GeocodeModel pointA = new GeocodeModel(36.0602, 140.0528);
//		final GeocodeModel pointB = new GeocodeModel(35.3918, 139.4441);

		final GeocodeModel pointA = new GeocodeModel(34.676059, 133.967977);
		final GeocodeModel pointB = new GeocodeModel(34.6957378454424, 133.977928161621);
		final GeocodeModel pointC = new GeocodeModel();
		pointC.setLatitude(new Double(34.6957378454424));
		pointC.setLongitude(new Double(133.977928161621));
		System.out.println(GeocodeUtil.calcDistance(pointA, pointB));
		System.out.println(GeocodeUtil.calcDistance(pointA, pointB, new BesselMethod()));
		System.out.println(GeocodeUtil.calcDistance(pointA, pointB, new Grs80Method()));
		System.out.println(GeocodeUtil.calcDistance(pointA, pointB, new Wgs84Method()));
		System.out.println(GeocodeUtil.calcDistance(pointA, pointC));
		new GeocodeUtil();
		GeocodeUtil.calcDistance(null, null);
	}

}
