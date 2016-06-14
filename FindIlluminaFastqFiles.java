package nhs.genetics.cardiff.framework;

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * Created by msl on 27/01/2015.
 */
public class FindIlluminaFastqFiles {

    private static final Logger log = Logger.getLogger(FindIlluminaFastqFiles.class.getName());

    IlluminaSampleSheetFile illuminaSampleSheetFile;
    IlluminaRunParametersFile illuminaRunParametersFile;
   /* HashMap<String, FastqPair> fastqFiles = new HashMap<String, FastqPair>();

    public FindIlluminaFastqFiles(IlluminaSampleSheetFile illuminaSampleSheetFile, IlluminaRunParametersFile illuminaRunParametersFile){
        this.illuminaSampleSheetFile = illuminaSampleSheetFile;
        this.illuminaRunParametersFile = illuminaRunParametersFile;
    };

    public void findFastqFileNames()
    {

        for (final IlluminaSampleSheetRecord record : illuminaSampleSheetFile.getSampleSheetRecords()){
            if (record.getSampleName().equals("")){
                File[] read1MatchingFiles = illuminaRunParametersFile.getFastqFolder().listFiles(new FilenameFilter() {
                    public boolean accept(File dir, String name) {
                        return name.startsWith(record.getSampleID()) && name.endsWith(".fastq.gz") && name.contains("R1");
                    }
                });

                File[] read2MatchingFiles = illuminaRunParametersFile.getFastqFolder().listFiles(new FilenameFilter() {
                    public boolean accept(File dir, String name) {
                        return name.startsWith(record.getSampleID()) && name.endsWith(".fastq.gz") && name.contains("R2");
                    }
                });

                fastqFiles.put(record.getSampleID(), new FastqPair(read1MatchingFiles[0], read2MatchingFiles[0]));

            } else {
                File[] read1MatchingFiles = illuminaRunParametersFile.getFastqFolder().listFiles(new FilenameFilter() {
                    public boolean accept(File dir, String name) {
                        return name.startsWith(record.getSampleName()) && name.endsWith(".fastq.gz") && name.contains("R1");
                    }
                });

                File[] read2MatchingFiles = illuminaRunParametersFile.getFastqFolder().listFiles(new FilenameFilter() {
                    public boolean accept(File dir, String name) {
                        return name.startsWith(record.getSampleName()) && name.endsWith(".fastq.gz") && name.contains("R2");
                    }
                });

                fastqFiles.put(record.getSampleID(), new FastqPair(read1MatchingFiles[0], read2MatchingFiles[0]));

            }

        }
    }

    public HashMap<String, FastqPair> getFastqFiles() {
        return fastqFiles;
    }*/
}
