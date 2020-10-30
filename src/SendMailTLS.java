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
				InternetAddress.parse("xiaocraze@gmail.com")); // destination 
			message.setSubject("Testing Subject");
			message.setText("DeaTest,"
				+ "\n\n No spam to my email, please!");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void SendEmail (String [][] student_data, String m_num) throws FileNotFoundException
	{
		String email_to_send = null;
		int x,y,z;
		for(x=0;x<100;x++)
		{
			if(student_data[x][0]==null)
				break;
			if(student_data[x][0].equals(m_num))
			{
				email_to_send = student_data[x][5];
			}
		}
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
			InternetAddress.parse(email_to_send)); // destination 
			message.setSubject("Testing Subject");
			message.setText("Dear Student,"
				+ "\n\n You have been moved from the waitlist to the class index!");

			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		return;
	}
}