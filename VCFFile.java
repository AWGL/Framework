package nhs.genetics.cardiff;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/*
 * Created by msl on 03/12/2014.
 * Class to hold and manage VCF file parsing
*/

public class VCFFile {

    private File filePath;
    private ArrayList<String> metaLines = new ArrayList<String>();
    private ArrayList<String> bodyLines = new ArrayList<String>();
    private ArrayList<String> columnHeaders = new ArrayList<String>();
    private HashSet<String> infoHeaders = new HashSet<String>();
    private HashSet<String> formatHeaders = new HashSet<String>();
    private ArrayList<VCFRecord> VCFRecords = new ArrayList<VCFRecord>();
    private HashMap<String, Integer> sampleIDs = new HashMap<String, Integer>(); //sampleID:SampleNo
    private String referenceGenome;
    private boolean hasGenotypes = false;

    private Exception truncatedVCF = new Exception("Truncated VCF. Too few column headers.");
    private Exception malformedVCF = new Exception("Malformed VCF. Incorrect column header format.");
    private Exception missingMetaLines = new Exception("Malformed VCF. Missing meta-lines.");

    private static final Logger log = Logger.getLogger(VCFFile.class.getName());

    //Initalise class
    public VCFFile(File filePath){
        this.filePath = filePath;
    }

    public void parseVCF()
    {
        log.log(Level.INFO, "Parsing " + filePath.toString());

        splitVCFHeaderAndBody(); //read VCF into memory
        extractColumnHeaders(); //extract column headers
        checkColumnHeaders(); //verify column headers
        extractInfoAndFormatSubHeaders(); //get info and format sub-headers

        //iterate down the VCF body
        for (String line : bodyLines)
        {
            VCFRecord record = new VCFRecord(line, hasGenotypes, columnHeaders);
            record.parseVCFRecord();
            VCFRecords.add(record);
        }

    }

    private void splitVCFHeaderAndBody() //extract VCF headers and body
    {
        log.log(Level.FINEST, "Splitting VCF header and body");

        boolean firstLine = true;
        String vcfLine;

        try (BufferedReader vcfReader = new BufferedReader(new FileReader(filePath))){

            while ((vcfLine = vcfReader.readLine()) != null) {

                if (vcfLine.equals(""))
                {
                    continue;
                }
                else if (firstLine)
                {
                    if (!Pattern.matches("^##fileformat=VCFv4.*", vcfLine))
                    {
                        log.log(Level.WARNING, "File format not VCF v4, Parser may not function correctly.");
                    }

                    firstLine = false;
                }
                else if (Pattern.matches("^#.*", vcfLine))
                {
                    metaLines.add(vcfLine);
                }
                else
                {
                    bodyLines.add(vcfLine);
                }

            }

            vcfReader.close();

        } catch (IOException e) {
            log.log(Level.SEVERE, "Could not read VCF file: " + e.getMessage());
        }
    }

    private void extractColumnHeaders() //true = has genotypes; false = no genotypes
    {
        log.log(Level.FINEST, "Getting column headers");

        try {

            if (metaLines != null && !metaLines.isEmpty()) {

                String[] headers = metaLines.get(metaLines.size() - 1 ).split("\t");

                for (String header : headers)
                {
                    columnHeaders.add(header);
                }

                if (columnHeaders.size() < 8)
                {
                    throw truncatedVCF;
                }
                else if (columnHeaders.size() > 7 && columnHeaders.size() < 10)
                {
                    log.log(Level.WARNING, "VCF has no genotypes.");
                }
                else
                {
                    this.hasGenotypes = true;

                    //store SampleIDs
                    for (int n = 9; n < columnHeaders.size(); ++n){
                        sampleIDs.put(columnHeaders.get(n), n - 9);
                    }
                }

            } else {
                throw missingMetaLines;
            }

        } catch (IndexOutOfBoundsException e){
            log.log(Level.SEVERE, e.getMessage());
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
        }
    }

    private void checkColumnHeaders() {

        log.log(Level.FINEST, "Checking column headers");

        try {

            if (!columnHeaders.get(0).equals("#CHROM")) {
                throw malformedVCF;
            }

            if (!columnHeaders.get(1).equals("POS")) {
                throw malformedVCF;
            }

            if (!columnHeaders.get(2).equals("ID")) {
                throw malformedVCF;
            }

            if (!columnHeaders.get(3).equals("REF")) {
                throw malformedVCF;
            }

            if (!columnHeaders.get(4).equals("ALT")) {
                throw malformedVCF;
            }

            if (!columnHeaders.get(5).equals("QUAL")) {
                throw malformedVCF;
            }

            if (!columnHeaders.get(6).equals("FILTER")) {
                throw malformedVCF;
            }

            if (!columnHeaders.get(7).equals("INFO")) {
                throw malformedVCF;
            }

            if (hasGenotypes && !columnHeaders.get(8).equals("FORMAT")) {
                throw malformedVCF;
            }

        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
        }
    }

    private void extractInfoAndFormatSubHeaders(){

        log.log(Level.FINEST, "Extracting info and format headers");

        for (String line : metaLines){

            if (Pattern.matches("^##INFO.*", line)) {

                String[] fields = line.split("=|,");
                infoHeaders.add(fields[2]);

            }

            if (Pattern.matches("^##FORMAT.*", line)){

                String[] fields = line.split("=|,");
                formatHeaders.add(fields[2]);

            }

            if (Pattern.matches("^##reference.*", line)){

                String[] fields = line.split("=");
                referenceGenome = fields[1];

            }
        }
    }

    public ArrayList<VCFRecord> getVCFRecords(){
        return VCFRecords;
    }
    public boolean getHasGenotypes(){
        return hasGenotypes;
    }
    public HashSet<String> getInfoHeaders(){
        return infoHeaders;
    }
    public HashSet<String> getFormatHeaders(){
        return formatHeaders;
    }
    public HashMap<String, Integer> getSampleIDs() { return sampleIDs; }
    public String getReferenceGenome() {
        return referenceGenome;
    }
    public boolean isHasGenotypes() {
        return hasGenotypes;
    }
}