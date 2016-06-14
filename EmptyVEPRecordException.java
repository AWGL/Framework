package nhs.genetics.cardiff.framework;

/**
 * Created by matt on 12/03/15.
 */

public class EmptyVEPRecordException extends RuntimeException {

    public EmptyVEPRecordException(String message) {
        super(message);
    }
    public String getMessage()
    {
        return super.getMessage();
    }

}
