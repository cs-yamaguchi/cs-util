package jp.co.comster.ftp;

import jp.co.comster.ftp.command.DeleteFile;
import jp.co.comster.ftp.command.ExistsFile;
import jp.co.comster.ftp.command.GetFile;
import jp.co.comster.ftp.command.Ls;
import jp.co.comster.ftp.command.Mkdir;
import jp.co.comster.ftp.command.PutFile;
import jp.co.comster.ftp.command.Rmdir;


/**
 * ＦＴＰユーティリティ
 * ファサードパターン
 * @author COMSTER）山口　英志
 * @version 1.0 ( 2008/03/03 )
 * @see http://commons.jakarta.jp/net/<br>
 * commons-net-1.4.1.jar
 */
public class FtpUtil {

	/**
	 * FTP接続設定
	 */
	private FtpConfig ftpConfig_;

	/**
	 * コンストラクタ
	 * @param ftpConfig
	 */
	public FtpUtil(FtpConfig ftpConfig) {
		ftpConfig_ = ftpConfig;
	}

	/**
	 * ディレクトリを作成します。
	 * @param makeDirectoryName
	 * @return
	 * @throws FtpUtilException
	 */
	public boolean mkdir(String makeDirectoryName) throws FtpUtilException {
		final Mkdir mkdir = new Mkdir(ftpConfig_);
		mkdir.setMakeDirectoryName(makeDirectoryName);
		mkdir.executeFileTransferProtcol();
		return mkdir.isSuccess();
	}

	/**
	 * ディレクトリを削除します。
	 * @param removeDirectoryName
	 * @return
	 * @throws FtpUtilException
	 */
	public boolean rmdir(String removeDirectoryName) throws FtpUtilException {
		final Rmdir rmdir = new Rmdir(ftpConfig_);
		rmdir.setRemoveDirectoryName(removeDirectoryName);
		rmdir.executeFileTransferProtcol();
		return rmdir.isSuccess();
	}

	/**
	 * 指定したパスに指定したファイルが存在するかを返します。
	 * @param path
	 * @param fileName
	 * @return
	 * @throws FtpUtilException
	 */
	public boolean existsFile(String path, String fileName) throws FtpUtilException {
		final ExistsFile existsFile = new ExistsFile(ftpConfig_);
		existsFile.setPath(path);
		existsFile.setFileName(fileName);
		existsFile.executeFileTransferProtcol();
		return existsFile.isExistsFile();

	}

	/**
	 * 指定したパスのファイルリストを返します。
	 * @param path
	 * @return
	 * @throws FtpUtilException
	 */
	public String[] ls(String path) throws FtpUtilException {
		final Ls ls = new Ls(ftpConfig_);
		ls.setPath(path);
		ls.executeFileTransferProtcol();
		return ls.getExistsFileNames();
	}

	/**
	 * 指定したパスの指定したファイルを転送します。
	 * @param localFullAddressFileName
	 * @param serverFullAddressFileName
	 * @return
	 * @throws FtpUtilException
	 */
	public boolean putFile(String localFullAddressFileName, String serverFullAddressFileName) throws FtpUtilException {
		final PutFile putFile = new PutFile(ftpConfig_);
		putFile.setLocalFullAddressFileName(localFullAddressFileName);
		putFile.setServerFullAddressFileName(serverFullAddressFileName);
		putFile.executeFileTransferProtcol();
		return putFile.isSuccess();
	}

	/**
	 * 指定したパスの指定したファイルを取得します。
	 * @param localFullAddressFileName
	 * @param serverFullAddressFileName
	 * @return
	 * @throws FtpUtilException
	 */
	public boolean getFile(String localFullAddressFileName, String serverFullAddressFileName) throws FtpUtilException {
		final GetFile getFile = new GetFile(ftpConfig_);
		getFile.setLocalFullAddressFileName(localFullAddressFileName);
		getFile.setServerFullAddressFileName(serverFullAddressFileName);
		getFile.executeFileTransferProtcol();
		return getFile.isSuccess();
	}

	/**
	 * 指定したファイルを削除します。
	 * @param originalFullAddressFileName
	 * @param putFullAddressFileName
	 * @return
	 * @throws FtpUtilException
	 */
	public boolean deleteFile(String deleteFullAddressFileName) throws FtpUtilException {
		final DeleteFile deleteFile = new DeleteFile(ftpConfig_);
		deleteFile.setDeleteFullAddressFileName(deleteFullAddressFileName);
		deleteFile.executeFileTransferProtcol();
		return deleteFile.isSuccess();
	}

}
