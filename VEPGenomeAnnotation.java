package nhs.genetics.cardiff.framework;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by msl on 30/12/2014.
 */
public class VEPGenomeAnnotation {

    private String line;
    private static final Logger log = Logger.getLogger(VEPGenomeAnnotation.class.getName());

    //annotation fields
    private double afrMAF, amrMAF, asnMAF, eurMAF, aaMAF, eaMAF;
    private boolean hasAfrMAF = false, hasAmrMAF = false, hasAsnMAF = false, hasEurMAF = false, hasAaMAF = false, hasEaMAF = false;

    private EmptyVEPRecordException emptyVEPRecordException = new EmptyVEPRecordException("All fields empty. Skipping record.");
    private MalformedVEPFileException malformedVEPFileException = new MalformedVEPFileException("Malformed VEP annotation file, check number of columns.");

    public VEPGenomeAnnotation(String line){
        this.line = line;
    }

    public void parseVEPRecord() { //split VEP record into fields

        String[] fields = line.split("\t");

        //malformed record
        if (fields.length != 18) {
            throw malformedVEPFileException;
        }

        //check for empty record
        for (int n = 3 ; n < 9; ++n){
            if (!fields[n].equals("") && !fields[n].equals("-")){
                break;
            }
            throw emptyVEPRecordException;
        }

        if (!fields[3].equals("-") && !fields[3].equals("")) {
            this.afrMAF = Double.parseDouble(fields[3]);
            hasAfrMAF = true;
        }

        if (!fields[4].equals("-") && !fields[4].equals("")) {
            this.amrMAF = Double.parseDouble(fields[4]);
            hasAmrMAF = true;
        }

        if (!fields[5].equals("-") && !fields[5].equals("")) {
            this.asnMAF = Double.parseDouble(fields[5]);
            hasAsnMAF = true;
        }

        if (!fields[6].equals("-") && !fields[6].equals("")) {
            this.eurMAF = Double.parseDouble(fields[6]);
            hasEurMAF = true;
        }

        if (!fields[7].equals("-") && !fields[7].equals("")) {
            this.aaMAF = Double.parseDouble(fields[7]);
            hasAaMAF = true;
        }

        if (!fields[8].equals("-") && !fields[8].equals("")) {
            this.eaMAF = Double.parseDouble(fields[8]);
            hasEaMAF = true;
        }

    }

    public double getAfrMAF() {
        return afrMAF;
    }
    public double getAmrMAF() {
        return amrMAF;
    }
    public double getAsnMAF() {
        return asnMAF;
    }
    public double getEurMAF() {
        return eurMAF;
    }
    public double getAaMAF() {
        return aaMAF;
    }
    public double getEaMAF() {
        return eaMAF;
    }
    public boolean isHasAfrMAF() {
        return hasAfrMAF;
    }
    public boolean isHasAmrMAF() {
        return hasAmrMAF;
    }
    public boolean isHasAsnMAF() {
        return hasAsnMAF;
    }
    public boolean isHasEurMAF() {
        return hasEurMAF;
    }
    public boolean isHasAaMAF() {
        return hasAaMAF;
    }
    public boolean isHasEaMAF() {
        return hasEaMAF;
    }

}
