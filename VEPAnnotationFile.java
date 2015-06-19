package nhs.genetics.cardiff;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * Created by msl on 28/12/2014.
 * VEP annotation file created using this commands:
 * perl /home/msl/ensembl-tools-release-75/scripts/variant_effect_predictor/variant_effect_predictor.pl \
 * -i "$1" \
 * --fasta /home/msl/.vep/homo_sapien/Homo_sapiens.GRCh37.75.dna.primary_assembly.fa \
 * --refseq \
 * --offline \
 * --force_overwrite \
 * --no_stats \
 * --sift b \
 * --polyphen b \
 * --numbers \
 * --hgvs \
 * --symbol \
 * --gmaf \
 * --maf_1kg \
 * --maf_esp \
 *--fields Uploaded_variation,Location,Allele,AFR_MAF,AMR_MAF,ASN_MAF,EUR_MAF,AA_MAF,EA_MAF,Consequence,SYMBOL,Feature,HGVSc,HGVSp,PolyPhen,SIFT,EXON,INTRON
 */

public class VEPAnnotationFile {

    private File filePath;
    private HashMap<String, HashSet<VEPTranscriptAnnotation>> transcriptLevelRecords = new HashMap<String, HashSet<VEPTranscriptAnnotation>>(); //GenomeVariant:Transcript level annotation
    private HashMap<String, VEPGenomeAnnotation> genomeLevelRecords = new HashMap<String, VEPGenomeAnnotation>(); //GenomeVariant:GenomeVariant level annotation
    private ArrayList<String> fileHeaders = new ArrayList<>();

    private static final Logger log = Logger.getLogger(VEPAnnotationFile.class.getName());

    public VEPAnnotationFile(File filePath){
        this.filePath = filePath;
    }

    public void parseVEP()
    {
        log.log(Level.INFO, "Parsing " + filePath.toString());

        String line;

        try (BufferedReader vepReader = new BufferedReader(new FileReader(filePath))){

            while ((line = vepReader.readLine()) != null) {

                if (!line.equals("")) {

                    if (Pattern.matches("^#.*", line)){
                        fileHeaders.add(line);
                        continue;
                    }

                    String[] fields = line.split("\t");
                    String[] idFields = fields[0].split(";");

                    if (!genomeLevelRecords.containsKey(idFields[0])) {

                        try {
                            VEPGenomeAnnotation temp = new VEPGenomeAnnotation(line);
                            temp.parseVEPRecord();

                            genomeLevelRecords.put(idFields[0], temp);
                        } catch (EmptyVEPRecordException e){
                            log.log(Level.FINE, e.getMessage());
                        } catch (MalformedVEPFileException e){
                            log.log(Level.SEVERE, "Problem reading VEP file: " + e.getMessage());
                        }

                    }

                    if (isRefSeqAnnotation(fields[11])) {

                        if (!transcriptLevelRecords.containsKey(idFields[0])){
                            transcriptLevelRecords.put(idFields[0], new HashSet<VEPTranscriptAnnotation>());
                        }

                        try {

                            VEPTranscriptAnnotation temp = new VEPTranscriptAnnotation(line);
                            temp.parseVEPRecord();

                            transcriptLevelRecords.get(idFields[0]).add(temp);
                        } catch (MalformedVEPFileException e){
                            log.log(Level.SEVERE, "Problem reading VEP file: " + e.getMessage());
                        }

                    }

                }

            }

            vepReader.close();

        } catch (IOException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
    }

    private static boolean isRefSeqAnnotation(String field){

        if (Pattern.matches(".._.*", field)){ //third position underscore for refseq
            return true;
        } else {
            return false;
        }

    }

    public HashMap<String, HashSet<VEPTranscriptAnnotation>> getTranscriptLevelRecords() {
        return transcriptLevelRecords;
    }
    public HashMap<String, VEPGenomeAnnotation> getGenomeLevelRecords() {
        return genomeLevelRecords;
    }
    public ArrayList<String> getFileHeaders() {
        return fileHeaders;
    }
}
