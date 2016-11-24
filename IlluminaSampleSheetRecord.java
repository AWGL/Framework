package nhs.genetics.cardiff.framework;

import java.util.logging.Logger;

/**
 * A class for storing Illumina sample sheet rows
 *
 * @author  Matt Lyon
 * @version 1.0
 * @since   2015-01-26
 */
public class IlluminaSampleSheetRecord {

    private static final Logger log = Logger.getLogger(IlluminaSampleSheetRecord.class.getName());

    private int sampleNo;
    private String line, sampleID = "", sampleName = "", samplePlate = "", sampleWell = "", i7IndexID = "", index = "", i5IndexID = "",
            index2 = "", sampleProject = "", description = "";
    private String[] headers;

    public IlluminaSampleSheetRecord(String[] headers, String line, int sampleNo){
        this.line = line;
        this.headers = headers;
        this.sampleNo = sampleNo;
    }

    public void parseIlluminaSampleSheetRecord(){

        //on sample info. split CSV fields
        String[] propertyFields = line.split(",");

        try {
            //loop over column headers
            for (int n = 0; n < headers.length; ++n){

                if (headers[n].trim().equals("Sample_ID")) {
                    sampleID = propertyFields[n].trim();
                }

                if (headers[n].trim().equals("Sample_Name")){
                    sampleName = propertyFields[n].trim();
                }

                if (headers[n].trim().equals("Sample_Plate")){
                    samplePlate = propertyFields[n].trim();
                }

                if (headers[n].trim().equals("Sample_Well")){
                    sampleWell = propertyFields[n].trim();
                }

                if (headers[n].trim().equals("I7_Index_ID")){
                    i7IndexID = propertyFields[n].trim();
                }

                if (headers[n].trim().equals("index")){
                    index = propertyFields[n].trim();
                }

                if (headers[n].trim().equals("I5_Index_ID")){
                    i5IndexID = propertyFields[n].trim();
                }

                if (headers[n].trim().equals("index2")){
                    index2 = propertyFields[n].trim();
                }

                if (headers[n].trim().equals("Sample_Project")){
                    sampleProject = propertyFields[n].trim();
                }

                if (headers[n].trim().equals("Description")){
                    description = propertyFields[n].trim();
                }

            }
        } catch (ArrayIndexOutOfBoundsException e){
            return;
        }

    }

    public int getSampleNo() {
        return sampleNo;
    }
    public String getSampleID() {
        return sampleID;
    }
    public String getSampleName() {
        return sampleName;
    }
    public String getSamplePlate() {
        return samplePlate;
    }
    public String getSampleWell() {
        return sampleWell;
    }
    public String getI7IndexID() {
        return i7IndexID;
    }
    public String getIndex() {
        return index;
    }
    public String getI5IndexID() {
        return i5IndexID;
    }
    public String getIndex2() {
        return index2;
    }
    public String getSampleProject() {
        return sampleProject;
    }
    public String getDescription() {
        return description;
    }

}
