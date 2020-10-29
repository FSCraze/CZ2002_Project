import java.io.File;
import java.io.FileNotFoundException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Scanner;


public class SendMailTLS {

	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner emailScan = new Scanner(new File("EmailCred.txt"));

		String username = "";
		String password = "";
		emailScan.useDelimiter("[,\n]");
		while (emailScan.hasNext()){
			username = emailScan.next().trim();
			password = emailScan.next().trim();
		}
		
		final String username1 = username;
		final String password1 = password;
		 

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username1, password1);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("from-email@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse("xiaocraze@gmail.com")); // to be added an email addr
			message.setSubject("Testing Subject");
			message.setText("Dear Mail Crawler,"
				+ "\n\n No spam to my email, please!");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}