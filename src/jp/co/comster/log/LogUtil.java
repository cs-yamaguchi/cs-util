package jp.co.comster.log;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

/**
 * ログ出力ユーティリティ
 *
 * @author COMSTER）山口　英志
 * @version 1.0 ( 2008/02/04 )
 * @see http://logging.apache.org/<br>
 * log4j-1.2.13.jar
 */
public class LogUtil {

	/**
	 * ログ出力
	 */
	private boolean isLogWrite_ = true;
	/**
	 * SQLログ出力
	 */
	private boolean isLogQuery_ = true;
	/**
	 * Logger
	 */
	private Logger logger_;

	/**
	 * Title
	 */
	private String title_;

	/**
	 * ログ出力日付形式
	 */
	public static final SimpleDateFormat DATE_TIME_MILLISECOND = new SimpleDateFormat ("yyyy/MM/dd HH:mm:ss.SSS");

	/**
	 * ログ出力日付形式
	 */
	public static final SimpleDateFormat DATE_TIME = new SimpleDateFormat ("yyyy/MM/dd HH:mm:ss");

	/**
	 * ログ出力日付形式
	 */
	public static final SimpleDateFormat TIME = new SimpleDateFormat ("HH:mm:ss.SSS");

	/**
	 * ログ出力日付形式
	 */
	public static final SimpleDateFormat DATE = new SimpleDateFormat ("yyyy/MM/dd");

	/**
	 * コンストラクタ
	 * @param logger
	 */
	public LogUtil(Logger logger) {
		logger_ = logger;
	}

	/**
	 * @return isLogWrite
	 */
	public boolean isLogWrite() {
		return isLogWrite_;
	}

	/**
	 * @param isLogWrite 設定する isLogWrite
	 */
	public void setLogWrite(boolean isLogWrite) {
		isLogWrite_ = isLogWrite;
	}

	/**
	 * @return isLogQuery
	 */
	public boolean isLogQuery() {
		return isLogQuery_;
	}

	/**
	 * @param isLogQuery 設定する isLogQuery
	 */
	public void setLogQuery(boolean isLogQuery) {
		isLogQuery_ = isLogQuery;
	}

	/**
	 * ログ出力設定に従ってログを出力する
	 * @param message ログメッセージ
	 */
	public void executeLogger(String message) {
		if (isLogWrite_)
			logger_.debug(message);
	}

	/**
	 * ログ出力設定に従ってクエリーログを出力する
	 * @param message ログメッセージ
	 */
	public void executeQueryLogger(String message) {
		if (isLogWrite_)
			if (isLogQuery_)
				logger_.debug("SQL : " + message);
	}

	/**
	 * ログ出力設定に従ってログを出力する
	 * @param message ログメッセージ
	 */
	public void abnormalEnd(String message) {
		if (isLogWrite_) {
			logger_.debug(message);
			logger_.debug(title_ + "　異常終了");
		}
	}

	/**
	 * ログ出力設定に従ってログを出力する
	 * @param message ログメッセージ
	 */
	public void normalEnd(String message) {
		if (isLogWrite_) {
			logger_.debug(message);
			logger_.debug(title_ + "　正常終了");
		}
	}
	/**
	 * ログ出力設定に従ってログを出力する
	 */
	public void start() {
		if (isLogWrite_)
			logger_.debug(title_ + "　開始");
	}

	/**
	 * ログ出力設定に従ってログを出力する
	 * @param message ログタイトル
	 */
	public void setTitle(String title) {
		title_ = title;
	}

	/**
	 * ログ出力するクエリーを返します。
	 * @param selectQuery クエリーテンプレート
	 * @param args クエリパラメータ
	 * @return ログ出力クエリー
	 */
	public String getLogQuery(String selectQuery, Object[] args) {
		final StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(selectQuery);
		if (args != null) {
			stringBuffer.append(" : key =  [");
			for (int i = 0; i < args.length; i++) {
				if (args[i] instanceof BigDecimal)
					stringBuffer.append(args[i]);
				else if (args[i] instanceof java.sql.Date)
					stringBuffer.append(LogUtil.DATE.format(args[i]));
				else if (args[i] instanceof java.sql.Time)
					stringBuffer.append(LogUtil.TIME.format(args[i]));
				else if (args[i] instanceof java.sql.Timestamp)
					stringBuffer.append(LogUtil.DATE_TIME_MILLISECOND.format(args[i]));
				else if (args[i] instanceof java.util.Date)
					stringBuffer.append(LogUtil.DATE_TIME_MILLISECOND.format(args[i]));
				else if (args[i] instanceof String)
					stringBuffer.append(args[i]);
				stringBuffer.append(", ");
			}
			if (stringBuffer.toString().endsWith(", ")) {
				stringBuffer.deleteCharAt(stringBuffer.toString().length() - 1);
				stringBuffer.deleteCharAt(stringBuffer.toString().length() - 1);
			}
			stringBuffer.append("]");
		}
		return stringBuffer.toString();
	}

	/**
	 * エラー発生時のロギング
	 * @param e
	 * @return
	 */
	public void exceptionLogging(Exception e) {
		if (isLogWrite_) {
			final StringBuffer body = new StringBuffer();
			body.append("Stack Trace");
			body.append("\n");
			body.append(e.toString());
			body.append("\n");
			for (int i = 0; i < e.getStackTrace().length; i++) {
				body.append("\tat " + e.getStackTrace()[i].toString());
				body.append("\n");
			}
			body.append("\n");
			logger_.debug(body.toString());
		}
	}


}
