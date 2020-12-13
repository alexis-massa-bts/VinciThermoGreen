package dataAccess;

import java.sql.SQLException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.PasswordAuthentication;

public class MailUtil {

	public static void sendMail(String recipient, String stadeConcerne) throws SQLException, MessagingException {
		Properties props = new Properties();

		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		String myAccountEmail = DataMySQL.getMail("amassa");
		String password = "Alxs2Msa351";

		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myAccountEmail, password);
			}
		});

		Message message = prepareMessage(session, myAccountEmail, recipient, stadeConcerne);
		
		Transport.send(message);
		System.out.println("Mail sent");
	}

	private static Message prepareMessage(Session session, String myAccountEmail, String recipient, String stadeConcerne) {
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			message.setSubject("Alerte : température dans un stade !");
			message.setText("Vérifier la température du stade" + stadeConcerne + " ASAP !");
			return message;
		} catch (AddressException e) {
			System.out.println("AddressException: " + e);
		} catch (MessagingException e) {
			System.out.println("MessagingException: " + e);
		}
		return null;

	}
}
