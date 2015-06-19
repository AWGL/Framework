package nhs.genetics.cardiff;

/**
 * Created by ml on 23/03/15.
 */

public class MalformedBEDRecordException extends RuntimeException {

    public MalformedBEDRecordException(String message) {
        super(message);
    }
    public String getMessage()
    {
        return super.getMessage();
    }

}
