package jp.co.comster.geocode.method;

/**
 * 緯度経度距離算出法インターフェース
 *
 * @author COMSTER）山口　英志
 * @version 1.0 ( 2010/04/28 )
 *
 */
public interface IMethod {

	public abstract double getA();
	public abstract double getE2();
	public abstract double getMNUM();


}
