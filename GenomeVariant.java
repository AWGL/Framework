package nhs.genetics.cardiff;

/**
 * Created by msl on 30/12/2014.
 */
public class GenomeVariant {

    private String chrom, ref, alt;
    private int pos;

    public GenomeVariant(String chrom, int pos, String ref, String alt){
        this.chrom = chrom;
        this.pos = pos;
        this.ref = ref;
        this.alt = alt;
    }

    public GenomeVariant(String s){ //use 1:111000A>T

        String[] split1 = s.split(":"); //get chrom
        String[] split2 = split1[1].split("\\D"); //get pos
        String[] split3 = split1[1].split("\\d"); //get ref>alt
        String[] split4 = split3[split3.length - 1].split(">");

        this.chrom = split1[0];
        this.pos = Integer.parseInt(split2[0]);
        this.ref = split4[0];
        this.alt = split4[1];
    }

    public String getChrom(){
        return chrom;
    }
    public String getRef(){
        return ref;
    }
    public String getAlt(){
        return alt;
    }
    public int getPos(){
        return pos;
    }
    public String getConcatenatedVariant(){
        return chrom + ":" + Integer.toString(pos) + ref + ">" + alt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GenomeVariant that = (GenomeVariant) o;

        if (pos != that.pos) return false;
        if (!alt.equals(that.alt)) return false;
        if (!chrom.equals(that.chrom)) return false;
        if (!ref.equals(that.ref)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = chrom.hashCode();
        result = 31 * result + ref.hashCode();
        result = 31 * result + alt.hashCode();
        result = 31 * result + pos;
        return result;
    }
}
