package jp.co.comster.fixedfile;

import jp.co.comster.string.StringUtil;

/**
 * 固定長ファイル作成用　カラムデータ定義
 *
 * @author COMSTER）山口　英志
 * @version 1.0 ( 2008/03/07 )
 *
 */
public class FixedFileLayoutMeta {

	/**
	 * 空白項目
	 */
	private boolean isBlank_ = false;

	/**
	 * 出力値
	 */
	private String value_;

	/**
	 * 固定長
	 */
	private int length_ = 0;

	/**
	 * 数値判定（右寄せ）
	 */
	private boolean isNumeric_ = false;

	/**
	 * コンストラクタ
	 * @param value 出力する値
	 * @param length 固定長
	 * @param isNumeric 数値判定（右寄せ）
	 */
	public FixedFileLayoutMeta(String value, int length, boolean isNumeric) {
		value_ = value;
		length_ = length;
		isNumeric_ = isNumeric;
	}

	/**
	 * コンストラクタ
	 * 項目長の空白を定義します。
	 * @param length
	 */
	public FixedFileLayoutMeta(int length) {
		isBlank_ = true;
		length_ = length;
	}

	/**
	 * 項目の値を取得します。
	 * @return
	 */
	public String getValue() {
		if (isBlank_)
			return StringUtil.getBlankString(length_);
		return StringUtil.getFixedString(value_, length_, isNumeric_);
	}

	/**
	 * @return length
	 */
	public int getLength() {
		return length_;
	}

}
