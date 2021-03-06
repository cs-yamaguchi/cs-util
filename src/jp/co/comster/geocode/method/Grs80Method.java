package jp.co.comster.geocode.method;

/**
 * 緯度経度距離算出法
 * Grs80
 * @author COMSTER）山口　英志
 * @version 1.0 ( 2010/04/28 )
 *
 */
public final class Grs80Method implements IMethod {

	public double getA() {
		return 6378137.000;
	}

	public double getE2() {
		return 0.00669438002301188;
	}

	public double getMNUM() {
		return 6335439.32708317;
	}

}
