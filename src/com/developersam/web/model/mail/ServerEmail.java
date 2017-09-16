package com.developersam.web.model.mail;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * A class that packs up all the email sending functions.
 */
public class ServerEmail {

    private static final String FROM = "no-reply@dev-sam.appspotmail.com";
    private String receiverAddress;
    private String subject;
    private String messageText;

    /**
     * Construct the object by all necessary email ingredients.
     * @param receiverAddress user's email address e.g. abc@gmail.com
     * @param subject subject of the email
     * @param messageText main content of the email
     */
    public ServerEmail(String receiverAddress, String subject, String messageText) {
        this.receiverAddress = receiverAddress;
        this.subject = subject;
        this.messageText = messageText;
    }

    /**
     * Send the email. The result is not clear.
     */
    public void send() {
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.host", "smtp.gmail.com");
        Session session = Session.getDefaultInstance(properties);
        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiverAddress));
            message.setSubject(subject);
            message.setText(messageText);
            Transport.send(message);
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }


}