package jp.co.comster.fixedfile;


/**
 * 固定長カラム定義配列から、固定長のファイル１行を生成します。
 *
 * @author COMSTER）山口　英志
 * @version 1.0 ( 2008/03/07 )
 *
 */
public class FixedFileMaker {

	/**
	 * ファイルレイアウト構成を元にレコード文字を生成する
	 * @param fixedFileLayoutMeta
	 * @return
	 */
	public static String toString(FixedFileLayoutMeta[] fixedFileLayoutMeta) {
		final StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < fixedFileLayoutMeta.length; i++)
			stringBuffer.append(fixedFileLayoutMeta[i].getValue());
		return stringBuffer.toString();
	}

}
