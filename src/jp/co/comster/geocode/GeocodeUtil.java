package jp.co.comster.geocode;

import jp.co.comster.geocode.method.Grs80Method;
import jp.co.comster.geocode.method.IMethod;
import jp.co.comster.geocode.model.GeocodeModel;


/**
 * 緯度経度ユーティリティ
 *
 * @author COMSTER）山口　英志
 * @version 1.0 ( 2010/04/28 )
 *
 */
public class GeocodeUtil {

	/**
	 * ２つの緯度経度から距離を求めます。
	 * 簡易版
	 * @param pointA
	 * @param pointB
	 * @return km
	 */
	public static double calcDistance(GeocodeModel pointA, GeocodeModel pointB) {
//		if (pointA == null || pointB == null)
//			return 0;
//		final double latitudeDistance = Math.abs(pointB.getLatitude().doubleValue() - pointA.getLatitude().doubleValue());
//		final double longitudeDistance = Math.abs(pointB.getLongitude().doubleValue() - pointA.getLongitude().doubleValue());
//		final double distance = Math.sqrt(latitudeDistance * latitudeDistance + longitudeDistance * longitudeDistance);
//		final double kmdistance = distance * 111;
//		return kmdistance;
		return calcDistance(pointA, pointB, null);
	}

	/**
	 * 度単位の数値をラジアン単位に変換する
	 * @param deg
	 * @return
	 */
	private static double deg2rad(double deg) {
		return deg * Math.PI / 180.0;
	}

	/**
	 * ２つの緯度経度から距離を求めます。
	 *
	 * @param pointA
	 * @param pointB
	 * @param method
	 * @return km
	 */
	public static double calcDistance(GeocodeModel pointA, GeocodeModel pointB, IMethod method) {
		if (pointA == null || pointB == null)
			return 0;
		if (method == null)
			method = new Grs80Method();
		double my = deg2rad((pointA.getLatitude().doubleValue() + pointB.getLatitude().doubleValue()) / 2.0);
		double dy = deg2rad(pointA.getLatitude().doubleValue() - pointB.getLatitude().doubleValue());
		double dx = deg2rad(pointA.getLongitude().doubleValue() - pointB.getLongitude().doubleValue());

		double sin = Math.sin(my);
		double w = Math.sqrt(1.0 - method.getE2() * sin * sin);
		double m = method.getMNUM() / (w * w * w);
		double n = method.getA() / w;

		double dym = dy * m;
		double dxncos = dx * n * Math.cos(my);

		return Math.sqrt(dym * dym + dxncos * dxncos);
	}

}
