package nhs.genetics.cardiff;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by msl on 26/01/2015.
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

                if (headers[n].equals("Sample_ID")) {
                    sampleID = propertyFields[n];
                }

                if (headers[n].equals("Sample_Name")){
                    sampleName = propertyFields[n];
                }

                if (headers[n].equals("Sample_Plate")){
                    samplePlate = propertyFields[n];
                }

                if (headers[n].equals("Sample_Well")){
                    sampleWell = propertyFields[n];
                }

                if (headers[n].equals("I7_Index_ID")){
                    i7IndexID = propertyFields[n];
                }

                if (headers[n].equals("index")){
                    index = propertyFields[n];
                }

                if (headers[n].equals("I5_Index_ID")){
                    i5IndexID = propertyFields[n];
                }

                if (headers[n].equals("index2")){
                    index2 = propertyFields[n];
                }

                if (headers[n].equals("Sample_Project")){
                    sampleProject = propertyFields[n];
                }

                if (headers[n].equals("Description")){
                    description = propertyFields[n];
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
