package nhs.genetics.cardiff;

/**
 * Created by ml on 13/10/2015.
 */
public class GTFRecord {

    private String gtfRecord, annotationSource, featureType, additionalInfo;
    private GenomicLocation genomicLocation;
    private int score, phase;
    private boolean strand;

    public GTFRecord(String gtfRecord){
        this.gtfRecord = gtfRecord;
    }

    public void parseGTFRecord(){
        String[] fields = gtfRecord.split("\t");

        //fill in fields
        genomicLocation = new GenomicLocation(fields[0], Integer.parseInt(fields[3]), Integer.parseInt(fields[4]));

    }

}
