package nhs.genetics.cardiff.framework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class to represent a single record of a multi-sample VCF
 *
 * @author  Matt Lyon
 * @version 1.0
 * @since   2014-12-03
 */

public class VCFRecord {

    private boolean hasGenotypes;
    private String line, id, filter, info, format;
    private double qual;
    private GenomeVariant genomeVariant;
    private HashMap<String, String> infoSubFields = new HashMap<String, String>(); //infoKey = infoValue
    private ArrayList<HashMap<String,String>> formatSubFields = new ArrayList<HashMap<String,String>>(); //FormatKey=FormatValue
    private ArrayList<String> columnHeaders = new ArrayList<String>();
    private String[] bodyFields;

    private static final Logger log = Logger.getLogger(VCFRecord.class.getName());

    public VCFRecord(String line, boolean hasGenotypes, ArrayList<String> columnHeaders) {
        this.line = line;
        this.hasGenotypes = hasGenotypes;
        this.columnHeaders = columnHeaders;
    }

    public void parseVCFRecord(){

        //split VCF record into fields
        this.bodyFields = line.split("\t");

        this.genomeVariant = new GenomeVariant(bodyFields[0], Integer.parseInt(bodyFields[1]), bodyFields[3], bodyFields[4]);
        this.id = bodyFields[2]; //not required, string or '.'

        if (!bodyFields[5].equals(".")) //missing value
        {
            this.qual = Double.parseDouble(bodyFields[5]);
        } else {
            this.qual = 0; //surrogate value; no probability that this variant is not an error
        }

        this.filter = bodyFields[6];
        this.info = bodyFields[7];

        if (!info.equals(".")) {
            extractInfoBody();
        }

        if (hasGenotypes){
            this.format = bodyFields[8];
            extractFormatBody();
        }
    }

    private void extractInfoBody() //operate line-by-line
    {
        log.log(Level.FINEST, "Extract info body");
        String last = "", beforeLast = "";
        String[] tokens = info.split("((?<=;|=)|(?=;|=))");

        for (String token : tokens)
        {
            if (last.equals("=")) {
                this.infoSubFields.put(beforeLast, token);
            }

            beforeLast = last;
            last = token;
        }

    }

    private void extractFormatBody()
    {
        log.log(Level.FINEST, "Extract format body");

        String[] fields = format.split(":");

        //loop over sample genotypes
        for (int n = 9; n < bodyFields.length; ++n){

            String[] values = bodyFields[n].split(":"); //split out genotype
            HashMap<String,String> val = new HashMap<String, String>();

            if (!values[0].equals("./.") && !values[0].equals(".|.")){ //skip no calls

                if (fields.length == values.length){
                    for (int j = 0; j < fields.length; ++j)
                    {
                        val.put(fields[j], values[j]);
                    }
                } else {
                    log.log(Level.WARNING, "Format and genotype sub-fields are not the same length, skipping record.");
                }

            }

            //add key=value to sampleID set
            formatSubFields.add(val);
        }
    }

    public GenomeVariant getGenomeVariant(){
        return genomeVariant;
    }
    public String getGenomeVariantAsString(){
        return genomeVariant.toString();
    }
    public String getId (){
        return id;
    }
    public String getFilter (){
        return filter;
    }
    public double getQual(){
        return qual;
    }
    public HashMap<String, String> getInfoSubFields(){
        return infoSubFields;
    }
    public ArrayList<HashMap<String,String>> getFormatSubFields(){
        return formatSubFields;
    }
}
