package nhs.genetics.cardiff;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Logger;

/**
 * Created by msl on 05/02/2015.
 */
public class GenomicLocation {

    private static final Logger log = Logger.getLogger(GenomicLocation.class.getName());

    private String chromosome, name;
    private int startPosition, endPosition, strand;

    public GenomicLocation(String chromosome, int startPosition, int endPosition){
        this.chromosome = chromosome;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }
    public GenomicLocation(String chromosome, int startPosition, int endPosition, String name){
        this.chromosome = chromosome;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.name = name;
    }
    public GenomicLocation(String chromosome, int startPosition, int endPosition, String name, int strand){
        this.chromosome = chromosome;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.name = name;
        this.strand = strand;
    }
    public GenomicLocation(String chromosome, int startPosition){
        this.chromosome = chromosome;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.name = name;
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static ArrayList<GenomicLocation> mergeBases(ArrayList<GenomicLocation> bases) {

        ArrayList<GenomicLocation> regions = new ArrayList<>();
        int lastStartPos = 0, lastEndPos = 0;
        String lastStartChrom = "";

        //sort bases by chrom pos
        Collections.sort(bases, GenomicLocation.chromosomeComparator);

        //make windows of overlapping regions
        for (int j = 0; j < bases.size(); ++j){ //loop over bases

            if (j == 0) { //first base

                lastStartChrom = bases.get(j).getChromosome();
                lastStartPos = bases.get(j).getStartPosition();
                lastEndPos = bases.get(j).getStartPosition();

            } else if (j == (bases.size() - 1)) { //last bases

                lastEndPos++;
                regions.add(new GenomicLocation(lastStartChrom, lastStartPos, lastEndPos));

            } else { //not first or last base

                if (!bases.get(j).getChromosome().equals(lastStartChrom)) { //different chromosome -- start new window

                    regions.add(new GenomicLocation(lastStartChrom, lastStartPos, lastEndPos));

                    lastStartChrom = bases.get(j).getChromosome();
                    lastStartPos = bases.get(j).getStartPosition();
                    lastEndPos = bases.get(j).getStartPosition();

                } else if (bases.get(j).getStartPosition() != lastEndPos + 1) { //different position -- start new window

                    regions.add(new GenomicLocation(lastStartChrom, lastStartPos, lastEndPos));

                    lastStartPos = bases.get(j).getStartPosition();
                    lastEndPos = bases.get(j).getStartPosition();

                } else {
                    lastEndPos++;
                }

            }

        }

        return regions;
    }

    public void setLeftPadding(int noBases){
        if (startPosition > noBases) {
            startPosition = (startPosition - noBases);
        } else {
            startPosition = 1;
        }
    }

    public void convertTo1Based(){
        startPosition += 1;
    }

    public void convertTo0Based(){
        startPosition -= 1;
    }

    public String getChromosome() {
        return chromosome;
    }
    public String getName() {
        return name;
    }
    public int getStartPosition() {
        return startPosition;
    }
    public int getEndPosition() {
        return endPosition;
    }
    public int getStrand() {
        return strand;
    }

    public void setRightPadding(int noBases){
        endPosition = (endPosition + noBases);
    }
    public void setChromosome(String chromosome) {
        this.chromosome = chromosome;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
    }
    public void setEndPosition(int endPosition) {
        this.endPosition = endPosition;
    }
    public void setStrand(int strand) {
        this.strand = strand;
    }

    /*Comparator for sorting the list by chromosome and position*/
    public static Comparator<GenomicLocation> chromosomeComparator = new Comparator<GenomicLocation>() {
        public int compare(GenomicLocation g1, GenomicLocation g2) {
            int returnVal = 0;

            if (isInteger(g1.getChromosome()) && isInteger(g2.getChromosome())){ //both chromosomes are numeric

                if (Integer.parseInt(g1.getChromosome()) > Integer.parseInt(g2.getChromosome())) {
                    returnVal = 1;
                } else if (Integer.parseInt(g1.getChromosome()) < Integer.parseInt(g2.getChromosome())) {
                    returnVal =  -1;
                } else if (g1.getStartPosition() > g2.getStartPosition()){
                    returnVal =  1;
                } else if (g1.getStartPosition() < g2.getStartPosition()){
                    returnVal =  -1;
                }

            } else {

                if (g1.getChromosome().compareTo(g2.getChromosome()) != 0){ //chromosomes are string
                    returnVal = g1.getChromosome().compareTo(g2.getChromosome());
                } else if (g1.getStartPosition() > g2.getStartPosition()){
                    returnVal =  1;
                } else if (g1.getStartPosition() < g2.getStartPosition()){
                    returnVal =  -1;
                }

            }

            return returnVal;
        }};

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GenomicLocation that = (GenomicLocation) o;

        if (startPosition != that.startPosition) return false;
        if (endPosition != that.endPosition) return false;
        if (strand != that.strand) return false;
        if (chromosome != null ? !chromosome.equals(that.chromosome) : that.chromosome != null) return false;
        return !(name != null ? !name.equals(that.name) : that.name != null);

    }

    @Override
    public int hashCode() {
        int result = chromosome != null ? chromosome.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + startPosition;
        result = 31 * result + endPosition;
        result = 31 * result + strand;
        return result;
    }
}
