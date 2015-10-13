package nhs.genetics.cardiff;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * Created by ml on 13/10/2015.
 */
public class GTFParser {

    private static final Logger log = Logger.getLogger(GTFParser.class.getName());

    private File gtfFilePath;
    private ArrayList<String> headers = new ArrayList<>();
    private ArrayList<GTFRecord> gtfRecords = new ArrayList<>();

    public GTFParser(File gtfFilePath) {
        this.gtfFilePath = gtfFilePath;
    }

    public void parseGTFFile(){

        String line;

        try (BufferedReader reader = new BufferedReader(new FileReader(gtfFilePath))){

            while ((line = reader.readLine()) != null) {

                //skip headers
                if (!Pattern.matches("^#", line))
                {
                    headers.add(line);
                    continue;
                }

                //split record and add to array
                GTFRecord gtfRecord = new GTFRecord(line);
                gtfRecord.parseGTFRecord();

                gtfRecords.add(gtfRecord);
            }

            reader.close();

        } catch (IOException e) {
            log.log(Level.SEVERE, "Could not read GTF file: " + e.getMessage());
        }

    }

    public ArrayList<String> getHeaders() {
        return headers;
    }
    public ArrayList<GTFRecord> getGtfRecords() {
        return gtfRecords;
    }

}
