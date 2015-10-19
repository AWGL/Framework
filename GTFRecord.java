package nhs.genetics.cardiff;

/**
 * Created by ml on 13/10/2015.
 */
public class GTFRecord {

    private String gtfRecord, source, feature, attribute;
    private GenomicLocation genomicLocation;
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

            attribute = fields[8];

        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println(gtfRecord);
        }

    }

    public String getSource() {
        return source;
    }
    public String getFeature() {
        return feature;
    }
    public String getAttribute() {
        return attribute;
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

}
