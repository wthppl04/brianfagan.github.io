package cst135.groupprojectpwrc;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class EmailService {
	public static void sendEmail(String recepient, String itemName) throws Exception {
        System.out.println("Preparing to send email....");
        System.out.println(recepient);
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", true);
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");

        final String adminEmail = "GrandCanyonUnivVending@gmail.com";
        final String adminPassword = "Vending123";

        Session session = Session.getInstance(prop, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(adminEmail, adminPassword);
            }
        });

        Message message = prepareMessage(session, adminEmail, recepient, itemName);
        
        Transport.send(message);
        System.out.println("Message sent successfully");
        
    }

    private static Message prepareMessage(Session session, String adminEmail, String recepient , String itemName) {
        try {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(adminEmail));
        message.setRecipient(Message.RecipientType.TO,new InternetAddress(recepient));
        message.setSubject("!!Out of Stock Item!!");
        message.setText("Low Stock of " + itemName + ". Please replace immediately.");
        return message;
        } catch (Exception e) {
            Logger.getLogger(EmailService.class.getName()).log(Level.SEVERE, null, e);
    
    }
        return null;
}

}
