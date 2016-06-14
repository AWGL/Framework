package nhs.genetics.cardiff.framework;

/**
 * A class for running bedtools functions http://bedtools.readthedocs.io/en/latest
 *
 * @author  Matt Lyon
 * @version 1.0
 * @since   2015-03-23
 */
class MalformedBEDRecordException extends Exception
{
    public MalformedBEDRecordException(String message)
    {
        super(message);
    }
}
