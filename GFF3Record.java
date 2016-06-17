package nhs.genetics.cardiff.framework;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Parser for GFF3 files
 *
 * @author  Matt Lyon
 * @version 1.0
 * @since   2016-01-13
 */
public class GFF3Record {

    //NC_000001.10	RefSeq	region	1	249250621	.	+	.	ID=id0;Name=1;Dbxref=taxon:9606;chromosome=1;gbkey=Src;genome=chromosome;mol_type=genomic DNA

    private static final Logger log = Logger.getLogger(GFF3Record.class.getName());

    private String gffRecord, seqId, source, type;
    private GenomicLocation genomicLocation;
    private Double score;
    private Boolean strand;
    private Integer phase;
    private HashMap<String, String> attributes = new HashMap<>();

    public GFF3Record(String gtfRecord){
        this.gffRecord = gtfRecord;
    }

    public void parseGFFRecord() {

        try {

            String[] fields = gffRecord.split("\t");

            //fill in fields
            genomicLocation = new GenomicLocation(convertSeqIdToStandardContig(fields[0]), Integer.parseInt(fields[3]), Integer.parseInt(fields[4]));

            seqId = fields[0];
            source = fields[1];
            type = fields[2];

            try {
                score = Double.parseDouble(fields[5]);
            } catch (NumberFormatException e) {
                score = null;
            }

            if (fields[6].equals("+")) {
                strand = true;
            } else if (fields[6].equals("-")) {
                strand = false;
            } else {
                strand = null;
            }

            try {
                phase = Integer.parseInt(fields[7]);
            } catch (NumberFormatException e) {
                phase = null;
            }

            for (String iter : fields[8].split(";")) {
                String[] fields2 = iter.split("=");
                attributes.put(fields2[0], fields2[1]);
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            log.log(Level.INFO, "Incorrect field number: " + gffRecord);
        }
    }

    private static String convertSeqIdToStandardContig(String seqId){
        String[] fields = seqId.split("_|\\.");
        String contig = fields[1].replaceFirst("^0+(?!$)", "");

        if (contig.equals("23")) {
            return "X";
        } else if (contig.equals("24")){
            return "Y";
        } else {
            return contig;
        }

    }

    public String getSeqId() {
        return seqId;
    }
    public String getSource() {
        return source;
    }
    public String getType() {
        return type;
    }
    public GenomicLocation getGenomicLocation() {
        return genomicLocation;
    }
    public Double getScore() {
        return score;
    }
    public Boolean getStrand() {
        return strand;
    }
    public Integer getPhase() {
        return phase;
    }
    public HashMap<String, String> getAttributes() {
        return attributes;
    }
}

