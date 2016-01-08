package nhs.genetics.cardiff;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by ml on 13/10/2015.
 */
public class GTFRecord {

    private static final Logger log = Logger.getLogger(GTFRecord.class.getName());

    private String gtfRecord, source, feature;
    private GenomicLocation genomicLocation;
    private HashMap<String, String> attributes = new HashMap<>();
    private Integer frame;
    private Double score;
    private Boolean strand;

    public GTFRecord(String gtfRecord){
        this.gtfRecord = gtfRecord;
    }

    public void parseGTFRecord(){

        try {

            String[] fields = gtfRecord.split("\t");

            //fill in fields
            genomicLocation = new GenomicLocation(fields[0], Integer.parseInt(fields[3]), Integer.parseInt(fields[4]));

            source = fields[1];
            feature = fields[2];

            try {
                score = Double.parseDouble(fields[5]);
            } catch (NumberFormatException e) {
                score = null;
            }

            if (fields[6].equals("+")){
                strand = true;
            } else if (fields[6].equals("-")){
                strand = false;
            } else {
                strand = null;
            }

            try {
                frame = Integer.parseInt(fields[7]);
            } catch (NumberFormatException e) {
                frame = null;
            }

            for (String iter : fields[8].split(";")){

                String keyValuePair = iter.trim();

                String[] fields2 = keyValuePair.split(" ");
                attributes.put(fields2[0].replace("\"", ""), fields2[1].replace("\"", ""));
            }

        } catch (ArrayIndexOutOfBoundsException e){
            log.log(Level.INFO, "Incorrect field number: " + gtfRecord);
        }

    }

    public String getSource() {
        return source;
    }
    public String getFeature() {
        return feature;
    }
    public GenomicLocation getGenomicLocation() {
        return genomicLocation;
    }
    public Integer getFrame() {
        return frame;
    }
    public Double getScore() {
        return score;
    }
    public Boolean getStrand() {
        return strand;
    }
    public HashMap<String, String> getAttributes() {
        return attributes;
    }
}
