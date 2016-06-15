package nhs.genetics.cardiff.framework;

/**
 * Exception for empty VEP records
 *
 * @author  Matt Lyon
 * @version 1.0
 * @since   2015-03-12
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
