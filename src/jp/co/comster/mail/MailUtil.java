package jp.co.comster.mail;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/**
 * JavaMailユーティリティ
 *
 * @author COMSTER）山口　英志
 * @version 1.0 ( 2008/04/14 )
 * @see http://java.sun.com/products/javamail/index.jsp<br>
 * http://java.sun.com/javase/technologies/desktop/javabeans/glasgow/jaf.html<br>
 * mail.jar<br>
 * activation.jar
 */
public class MailUtil {

	/**
	 * SMTP設定
	 */
	private static final String SMTP_KEY		= "mail.smtp.host";
	/**
	 * HOST設定
	 */
	private static final String HOST_KEY		= "mail.host";
	/**
	 * ヘッダー設定
	 */
	private static final String CONTENT_TYPE	= "Content-Type";

	/**
	 * コンストラクタ
	 */
	public MailUtil() {
	}

	/**
	 * メール送信
	 * @param mailConfig
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	public boolean sendmail(MailConfig mailConfig) {
		final Properties properties = System.getProperties();
		properties.put(HOST_KEY, mailConfig.getSmtp());
		properties.put(SMTP_KEY, mailConfig.getSmtp());
//		properties.put("mail.debug", "true");

        // SSL関連設定
		if (mailConfig instanceof SSLMailConfig) {
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.user", ((SSLMailConfig) mailConfig).getSslUser());
			properties.put("mail.smtp.port", ((SSLMailConfig) mailConfig).getSslPort());
			properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			properties.put("mail.smtp.socketFactory.fallback", "false");
			properties.put("mail.smtp.socketFactory.port", ((SSLMailConfig) mailConfig).getSslPort());
		}

		Session session ;
		if (!(mailConfig instanceof SSLMailConfig))
			session = Session.getDefaultInstance(properties, null);
		else
			session = Session.getInstance(properties, new Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(((SSLMailConfig) mailConfig).getSslUser(), ((SSLMailConfig) mailConfig).getSslPass());
	            }
	        });

		session.setDebug(mailConfig.getDebug().booleanValue());
		final MimeMessage mimeMessage = new MimeMessage(session);
		try {
			mimeMessage.setFrom(new InternetAddress(
					mailConfig.getFromAddress(),
					mailConfig.getFromName(),
					mailConfig.getEncord()));
			mimeMessage.setRecipients(Message.RecipientType.TO, getInternetAddress(mailConfig.getToAddress()));
			mimeMessage.setRecipients(Message.RecipientType.CC, getInternetAddress(mailConfig.getToCC()));
			mimeMessage.setRecipients(Message.RecipientType.BCC, getInternetAddress(mailConfig.getToBCC()));
			mimeMessage.setSubject(mailConfig.getTitle(), mailConfig.getEncord());
			mimeMessage.setHeader(CONTENT_TYPE, mailConfig.getMimeType());
			mimeMessage.setSentDate(new Date());
			if (mailConfig.getAttachmentFiles().size() > 0) {
				final Multipart multipart = new MimeMultipart();
				final MimeBodyPart mimeBodyText = new MimeBodyPart();
				mimeBodyText.setText(mailConfig.getBody(), mailConfig.getEncord());
				multipart.addBodyPart(mimeBodyText);
				for (int i = 0; i < mailConfig.getAttachmentFiles().size(); i++) {
					final MimeBodyPart mimeBodyFile = new MimeBodyPart();
					final FileDataSource fileDataSource = new FileDataSource(mailConfig.getAttachmentFile(i).getFileName());
					if (mailConfig.getAttachmentFile(i).getFileText() == null || mailConfig.getAttachmentFile(i).getFileText().equals(""))
						mimeBodyFile.setDataHandler(new DataHandler(fileDataSource));
					else
						mimeBodyFile.setDataHandler(new DataHandler(mailConfig.getAttachmentFile(i).getFileText(), mailConfig.getAttachmentFile(i).getMimeType()));
					mimeBodyFile.setFileName(MimeUtility.encodeWord(fileDataSource.getName()));
		            multipart.addBodyPart(mimeBodyFile);
				}
	            mimeMessage.setContent(multipart);
			} else if (mailConfig.getFileName() == null || mailConfig.getFileName().equals("")) {
				mimeMessage.setText(mailConfig.getBody(), mailConfig.getEncord());
			} else {
				final MimeBodyPart mimeBodyText = new MimeBodyPart();
				mimeBodyText.setText(mailConfig.getBody(), mailConfig.getEncord());
				final MimeBodyPart mimeBodyFile = new MimeBodyPart();
				final FileDataSource fileDataSource = new FileDataSource(mailConfig.getFileName());
				if (mailConfig.getFileText() == null || mailConfig.getFileText().equals(""))
					mimeBodyFile.setDataHandler(new DataHandler(fileDataSource));
				else
					mimeBodyFile.setDataHandler(new DataHandler(mailConfig.getFileText(), mailConfig.getMimeType()));
				mimeBodyFile.setFileName(MimeUtility.encodeWord(fileDataSource.getName()));
				final Multipart multipart = new MimeMultipart();
	            multipart.addBodyPart(mimeBodyText);
	            multipart.addBodyPart(mimeBodyFile);
	            mimeMessage.setContent(multipart);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return false;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}

		try {
			Transport.send(mimeMessage);
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * あて先文字列をカンマで区切り、InternetAddressの配列にして返す
	 * @param address
	 * @return
	 * @throws AddressException
	 */
	private InternetAddress[] getInternetAddress(String address) throws AddressException {
		if (address == null || address.equals(""))
			return null;
		final String[] addresses = address.split(",");
		final List<InternetAddress> list = new ArrayList<InternetAddress>();
		for (int i = 0; i < addresses.length; i++)
			list.add(new InternetAddress(addresses[i]));
		return (InternetAddress[])list.toArray(new InternetAddress[0]);
	}

}
