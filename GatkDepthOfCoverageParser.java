package nhs.genetics.cardiff.framework;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Parser for GATK depth of coverage output
 *
 * @author  Matt Lyon
 * @version 1.0
 * @since   2016-01-07
 */
public class GatkDepthOfCoverageParser {

    private static final Logger log = Logger.getLogger(GatkDepthOfCoverageParser.class.getName());

    private File gatkDepthOfCoverageFile;
    private ArrayList<HashMap<GenomicLocation, Integer>> depthOfCoverage = new ArrayList<>();
    private ArrayList<String> sampleIds = new ArrayList<>();

    public GatkDepthOfCoverageParser(File gatkDepthOfCoverageFile){
        this.gatkDepthOfCoverageFile = gatkDepthOfCoverageFile;
    }

    public void parseGatkDepthOfCoverageFile(){
        log.log(Level.INFO, "Parsing GATK depth of coverage file");

        boolean passHeaderLine = false;
        String line;

        //read gatk per-base depth file
        try (BufferedReader reader = new BufferedReader(new FileReader(gatkDepthOfCoverageFile))){

            while ((line = reader.readLine()) != null) {

                if (!line.equals("")) {
                    String[] fields = line.split("\t");

                    //store SampleIDs
                    if (!passHeaderLine){

                        for (int n = 3 ; n < fields.length; ++n){

                            //store sampleIDs
                            String[] subFields = fields[n].split("_");

                            sampleIds.add(subFields[2]);
                            depthOfCoverage.add(new HashMap<GenomicLocation, Integer>());
                        }

                        passHeaderLine = true;

                    } else {

                        String[] subFields = fields[0].split(":");
                        GenomicLocation genomicLocation = new GenomicLocation(subFields[0], Integer.parseInt(subFields[1]));

                        //loop over coverage values
                        for (int n = 3; n < fields.length; ++n) {
                            depthOfCoverage.get(n-3).put(genomicLocation, Integer.parseInt(fields[n]));
                        }

                    }

                }

            }

            reader.close();
        } catch (IOException e) {
            log.log(Level.SEVERE, "Problem reading gatk depth of coverage file: " + e.getMessage());
        }

    }

    public ArrayList<HashMap<GenomicLocation, Integer>> getDepthOfCoverage() {
        return depthOfCoverage;
    }
    public ArrayList<String> getSampleIds() {
        return sampleIds;
    }

}
