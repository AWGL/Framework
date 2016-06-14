package nhs.genetics.cardiff.framework;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmailMessage {

    private static final Logger log = Logger.getLogger(SendEmailMessage.class.getName());

    public static void sendEmailMessage(String msgBody, String msgSubject, String senderEmailAddress, String senderName, String recipientEmailAddress, String recipientName){

        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        try {

            Message msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress(senderEmailAddress, senderName));

            msg.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(recipientEmailAddress, recipientName));

            msg.setSubject(msgSubject);
            msg.setText(msgBody);

            Transport.send(msg);

        } catch (Exception e) {
            log.log(Level.SEVERE, "Problem sending email: " + e.getMessage());
        }

    }

}
