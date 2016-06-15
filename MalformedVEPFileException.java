package nhs.genetics.cardiff.framework;

/**
 * Exception for unexpected VEP output
 *
 * @author  Matt Lyon
 * @version 1.0
 * @since   2015-03-12
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
