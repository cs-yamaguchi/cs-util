package jp.co.comster.geocode.model;

import java.io.Serializable;

/**
 * 緯度経度
 *
 * @author COMSTER）山口　英志
 * @version 1.0 ( 2010/04/28 )
 *
 */
public class GeocodeModel implements Serializable {

	/**
	 * 緯度
	 */
	private Double latitude_;

	/**
	 * 経度
	 */
	private Double longitude_;

	/**
	 * コンストラクタ
	 */
	public GeocodeModel() {
	}

	/**
	 * コンストラクタ
	 */
	public GeocodeModel(Double latitude, Double longitude) {
		latitude_ = latitude;
		longitude_ = longitude;
	}

	/**
	 * コンストラクタ
	 */
	public GeocodeModel(double latitude, double longitude) {
		this(new Double(latitude), new Double(longitude));
	}

	/**
	 * @return the latitude
	 */
	public Double getLatitude() {
		return latitude_;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(Double latitude) {
		latitude_ = latitude;
	}

	/**
	 * @return the longitude
	 */
	public Double getLongitude() {
		return longitude_;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(Double longitude) {
		longitude_ = longitude;
	}

}
