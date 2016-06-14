package nhs.genetics.cardiff.framework;

import java.util.HashSet;

/**
 * Created by msl on 30/12/2014.
 */

public class VEPTranscriptAnnotation {

    private String line;

    //annotation fields
    private String symbol, feature, hgvsCoding, hgvsProtein, siftResult, polyPhenResult, exonNo, intronNo, exonTotal, intronTotal;
    private double siftScore, polyphenScore;
    private HashSet<String> consequences = new HashSet<String>();

    private MalformedVEPFileException malformedVEPFileException = new MalformedVEPFileException("Malformed VEP annotation file, check number of columns.");

    public VEPTranscriptAnnotation(String line){
        this.line = line;
    }

    public void parseVEPRecord() { //split VEP record into fields

        String[] fields = line.split("\t");

        if (fields.length != 18) {
            throw malformedVEPFileException;
        }

        //make unique list of consequences
        for (String consequence : fields[9].split(",")){
            this.consequences.add(consequence);
        }

        if (fields[10].equals("-")) {
            this.symbol = "";
        } else {
            this.symbol = fields[10];
        }

        if (fields[11].equals("-")) {
            this.feature = "";
        } else {
            String[] subFields = fields[11].split(":");
            this.feature = subFields[0];
        }

        if (fields[12].equals("-")){
            this.hgvsCoding = "";
        } else {
            String[] subFields = fields[12].split(":");
            this.hgvsCoding = subFields[1];
        }

        if (fields[13].equals("-")){
            this.hgvsProtein = "";
        } else {
            String[] subFields = fields[13].split(":");

            if (subFields[1].contains("(") && subFields[1].contains(")")){
                subFields = subFields[1].split("\\(");
                subFields = subFields[1].split("\\)");
                this.hgvsProtein = subFields[0];
            } else {
                this.hgvsProtein = subFields[1];
            }
        }

        //substitute for HGVS recommendation
        if (this.hgvsProtein.equals("p.%3D")){
            this.hgvsProtein = "p.=";
        }

        if (fields[14].equals("-")) {
            this.polyPhenResult = "";
            this.polyphenScore = 0;
        } else {
            String[] subFields = fields[14].split("\\(|\\)");
            this.polyPhenResult = subFields[0];
            this.polyphenScore = Double.parseDouble(subFields[1]);
        }

        if (fields[15].equals("-")) {
            this.siftResult = "";
            this.siftScore  = 0;
        } else {
            String[] subFields = fields[15].split("\\(|\\)");
            this.siftResult = subFields[0];
            this.siftScore = Double.parseDouble(subFields[1]);
        }

        if (fields[16].equals("-")) {
            this.exonNo = "";
            this.exonTotal = "";
        } else {
            String[] subFields = fields[16].split("/");

            this.exonNo = subFields[0];
            this.exonTotal = subFields[1];
        }

        if (fields[17].equals("-")) {
            this.intronNo = "";
            this.intronTotal = "";
        } else {
            String[] subFields = fields[17].split("/");

            this.intronNo = subFields[0];
            this.intronTotal = subFields[1];
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VEPTranscriptAnnotation that = (VEPTranscriptAnnotation) o;

        if (Double.compare(that.siftScore, siftScore) != 0) return false;
        if (Double.compare(that.polyphenScore, polyphenScore) != 0) return false;
        if (symbol != null ? !symbol.equals(that.symbol) : that.symbol != null) return false;
        if (feature != null ? !feature.equals(that.feature) : that.feature != null) return false;
        if (hgvsCoding != null ? !hgvsCoding.equals(that.hgvsCoding) : that.hgvsCoding != null) return false;
        if (hgvsProtein != null ? !hgvsProtein.equals(that.hgvsProtein) : that.hgvsProtein != null) return false;
        if (siftResult != null ? !siftResult.equals(that.siftResult) : that.siftResult != null) return false;
        if (polyPhenResult != null ? !polyPhenResult.equals(that.polyPhenResult) : that.polyPhenResult != null)
            return false;
        if (exonNo != null ? !exonNo.equals(that.exonNo) : that.exonNo != null) return false;
        if (intronNo != null ? !intronNo.equals(that.intronNo) : that.intronNo != null) return false;
        if (exonTotal != null ? !exonTotal.equals(that.exonTotal) : that.exonTotal != null) return false;
        if (intronTotal != null ? !intronTotal.equals(that.intronTotal) : that.intronTotal != null) return false;
        return !(consequences != null ? !consequences.equals(that.consequences) : that.consequences != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = symbol != null ? symbol.hashCode() : 0;
        result = 31 * result + (feature != null ? feature.hashCode() : 0);
        result = 31 * result + (hgvsCoding != null ? hgvsCoding.hashCode() : 0);
        result = 31 * result + (hgvsProtein != null ? hgvsProtein.hashCode() : 0);
        result = 31 * result + (siftResult != null ? siftResult.hashCode() : 0);
        result = 31 * result + (polyPhenResult != null ? polyPhenResult.hashCode() : 0);
        result = 31 * result + (exonNo != null ? exonNo.hashCode() : 0);
        result = 31 * result + (intronNo != null ? intronNo.hashCode() : 0);
        result = 31 * result + (exonTotal != null ? exonTotal.hashCode() : 0);
        result = 31 * result + (intronTotal != null ? intronTotal.hashCode() : 0);
        temp = Double.doubleToLongBits(siftScore);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(polyphenScore);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (consequences != null ? consequences.hashCode() : 0);
        return result;
    }

    public String getSymbol() {
        return symbol;
    }
    public String getFeature() {
        return feature;
    }
    public String getHgvsCoding() {
        return hgvsCoding;
    }
    public String getHgvsProtein() {
        return hgvsProtein;
    }
    public String getSiftResult() {
        return siftResult;
    }
    public String getPolyPhenResult() {
        return polyPhenResult;
    }
    public String getExonNo() {
        return exonNo;
    }
    public String getIntronNo() {
        return intronNo;
    }
    public String getExonTotal() {
        return exonTotal;
    }
    public String getIntronTotal() {
        return intronTotal;
    }
    public double getSiftScore() {
        return siftScore;
    }
    public double getPolyphenScore() {
        return polyphenScore;
    }
    public HashSet<String> getConsequences() {
        return consequences;
    }

}
