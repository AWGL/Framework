package nhs.genetics.cardiff.framework;

import java.util.logging.Logger;

/**
 * Created by msl on 07/01/2015.
 */
public class StringLongPair {

    private static final Logger log = Logger.getLogger(StringLongPair.class.getName());

    String s;
    long l;

    public StringLongPair(String s, long l){
        this.s = s;
        this.l = l;
    }

    public long getLong(){
        return l;
    }
    public String getString(){
        return s;
    }
}
