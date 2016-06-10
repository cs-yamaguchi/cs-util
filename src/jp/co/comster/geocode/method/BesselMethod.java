package jp.co.comster.geocode.method;

/**
 * 緯度経度距離算出法
 * Bessel
 * @author COMSTER）山口　英志
 * @version 1.0 ( 2010/04/28 )
 *
 */
public final class BesselMethod implements IMethod {

	public double getA() {
		return 6377397.155;
	}

	public double getE2() {
		return 0.00667436061028297;
	}

	public double getMNUM() {
		return 6334832.10663254;
	}

}
