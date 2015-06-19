package nhs.genetics.cardiff;

/**
 * Created by matt on 12/03/15.
 */

public class MalformedVEPFileException extends RuntimeException {

    public MalformedVEPFileException(String message) {
        super(message);
    }
    public String getMessage()
    {
        return super.getMessage();
    }

}
