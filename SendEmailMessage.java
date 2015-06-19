package nhs.genetics.cardiff;

import java.util.logging.Logger;

/**
 * Created by matt on 06/03/15.
 */
public class SendEmailMessage {

    private static final Logger log = Logger.getLogger(SendEmailMessage.class.getName());

    String message;

    public SendEmailMessage(String message){
        this.message = message;
    }

    public void sendEmail(){
        //TODO: send email
    }

}
