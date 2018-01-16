package com.kazmani.messaging;

import java.util.Properties;  

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailDao {

    public Mail sendMail(Mail email) {

	final String username = "serihbrah@gmail.com";
	final String password = "serihbra101";

	Mail myMail = new Mail();

	try {

	    System.out.println("about to begin initialization");
	    Properties props = new Properties();
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.port", "587");

	    System.out.println("finished initialization");

	    Authenticator auth = new Authenticator() {
		// override the getPasswordAuthentication method
		protected PasswordAuthentication getPasswordAuthentication() {
		    return new PasswordAuthentication(username, password);
		}
	    };

	    Session session = Session.getInstance(props, auth);
	    System.out.println("finished session and authentication");

	    MimeMessage msg = new MimeMessage(session);
	    msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
	    msg.addHeader("format", "flowed");
	    msg.addHeader("Content-Transfer-Encoding", "8bit");

	    msg.setFrom(new InternetAddress(email.getSenderEmail()));
	    msg.setRecipients(RecipientType.TO,
		    InternetAddress.parse(email.getRecipientEmail()));
	    msg.setSubject(email.getSubject());
	    // msg.setText(body);
	    StringBuilder emailBody = new StringBuilder();
	    emailBody.append("<html><body>");
	    emailBody.append("<div style=\"background-color:black;color:white;height:100px;\">");
	    emailBody.append("<b>" + email.getBody() + "</b>");
	    emailBody.append("</div>");
	    emailBody.append("</body></html>");

	    msg.setContent(emailBody.toString(), "text/HTML");

	    System.out.println("finished preparing message,about to send!");
	    Transport.send(msg);
	    System.out.println("finished sending");

	    myMail.setMessage("Mail message successfully sent");

	    return myMail;

	} catch (MessagingException ex) {
	    ex.printStackTrace();
	    myMail.setErrorMessage("Error due to:" + ex.getMessage());
	    return myMail;
	}

    }

}
