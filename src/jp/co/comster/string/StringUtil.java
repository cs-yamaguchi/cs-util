package jp.co.comster.string;

import java.math.BigDecimal;

/**
 * Stringユーティリティ
 *
 * @author COMSTER）山口　英志
 * @version 1.0 ( 2008/02/06 )
 *
 */
public class StringUtil {

	/**
	 * 文字列 a と b が同じかどうかを返します。
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean isSame(String a, String b) {
		if (a == null && b == null)
			return true;
		if (a == null && b != null)
			return false;
		return (a.equals(b));
	}

	/**
	 * ブランク文字を返します。
	 * @param length
	 * @param blank
	 * @return
	 */
	public static String getBlankString(int length, char blank) {
		final StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < length; i++)
			stringBuffer.append(blank);
		return stringBuffer.toString();

	}

	/**
	 * ブランク文字を返します。
	 * @param length
	 * @return
	 */
	public static String getBlankString(int length) {
		return getBlankString(length, ' ');
	}

	/**
	 * 固定長文字列を取得します。
	 * @param value
	 * @param length
	 * @param right
	 * @return
	 */
	public static String getFixedString(String value, int length, boolean right, char blank) {
		if (length <= 0)
			return "";
		if (value == null)
			return getBlankString(length, blank);
		if (value.equals(""))
			return getBlankString(length, blank);
		if (value.length() == length)
			return value;
		if (value.length() > length)
			return value.substring(0, length);
		if (right)
			return getBlankString(length - value.length(), blank) + value;
		return value + getBlankString(length - value.length(), blank);
	}

	/**
	 * 固定長文字列を取得します。
	 * @param value
	 * @param length
	 * @param right
	 * @return
	 */
	public static String getFixedString(String value, int length, boolean right) {
		if (right)
			return getFixedString(value, length, right, '0');
		else
			return getFixedString(value, length, right, ' ');
	}

	/**
	 * 文字列を返却します。
	 * @param value
	 * @return
	 */
	public static String toString(Object value) {
		if (value == null)
			return null;
		if (value instanceof String)
			return (String)value;
		if (value instanceof BigDecimal)
			return value.toString();
		throw new RuntimeException("instance not found \n type = " + value.getClass().toString());
	}

	/**
	 * 文字列配列をセパレータで連結した文字列にして返却します。
	 * @param value
	 * @return
	 */
	public static String mergeString(String[] value, String sepalater) {
		if (value == null)
			return null;
		final StringBuffer stringBuffer = new StringBuffer ();
		for (int i = 0; i < value.length; i++) {
			stringBuffer.append(value[i]);
			if (i < value.length - 1)
				stringBuffer.append(sepalater);
		}
		return stringBuffer.toString();
	}

}
