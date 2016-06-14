package nhs.genetics.cardiff.framework;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by msl on 05/02/2015.
 */
public class BEDFile {

    private static final Logger log = Logger.getLogger(BEDFile.class.getName());
    private MalformedBEDRecordException malformedBEDRecordException = new MalformedBEDRecordException("Malformed BED file, check number of columns.");

    private File filePath;
    private ArrayList<GenomicLocation> bedRecords = new ArrayList<GenomicLocation>();
    private HashMap<GenomicLocation, HashSet<String>> totalTargetBases = new HashMap<GenomicLocation, HashSet<String>>();
    private int start, end;
    private GenomicLocation loc;
    private String chrom;

    public BEDFile(File filePath) {
        this.filePath = filePath;
    }
    public void parseBED(){

        log.log(Level.INFO, "Parsing BED File: " + filePath);

        String line;

        try (BufferedReader bedReader = new BufferedReader(new FileReader(filePath))){

            while((line = bedReader.readLine())!= null) {

                if (!line.equals("")){

                    String[] fields = line.split("\t");

                    if (fields.length < 3 || fields.length > 12){
                        throw malformedBEDRecordException;
                    }

                    chrom = fields[0];
                    start = Integer.parseInt(fields[1]);
                    end = Integer.parseInt(fields[2]);

                    if (start < end){
                        if (fields.length == 4){
                            loc = new GenomicLocation(chrom, start, end, fields[3]);
                        } else {
                            loc = new GenomicLocation(chrom, start, end);
                        }
                    } else {
                        if (fields.length == 4){
                            loc = new GenomicLocation(chrom, end, start, fields[3]);
                        } else {
                            loc = new GenomicLocation(chrom, end, start);
                        }
                    }

                    bedRecords.add(loc);
                }

            }

            bedReader.close();

        } catch (IOException e) {
            log.log(Level.SEVERE, "Problem reading BED file: " + e.getMessage());
        }

        //print metrics
        log.log(Level.INFO, "Total targets discovered in " + filePath + " " + getTargetSize());
    }

    public ArrayList<GenomicLocation> getBedRecords() {
        return bedRecords;
    }
    public int getTargetSize() {
        return bedRecords.size();
    }
    public void setTargetBases(){

        //Store target bases
        for (GenomicLocation locRecord : bedRecords) {
            for (int j = locRecord.getStartPosition(); j < locRecord.getEndPosition(); ++j){

                GenomicLocation temp = new GenomicLocation(locRecord.getChromosome(), j);

                //store unique bases from all regions
                if (!totalTargetBases.containsKey(temp)) {
                    totalTargetBases.put(temp, new HashSet<String>());
                }

                totalTargetBases.get(temp).add(locRecord.getName());
            }
        }

        log.log(Level.INFO, "Total number of unique target bases: " + totalTargetBases.size() + " bp");

    }
    public HashMap<GenomicLocation, HashSet<String>> getTargetBases(){
        return totalTargetBases;
    }
    
}
