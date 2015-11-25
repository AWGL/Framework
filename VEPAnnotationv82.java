package nhs.genetics.cardiff;

import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by ml on 17/06/2015.
 */

public class VEPAnnotationv82 {

    private static final Logger log = Logger.getLogger(VEPAnnotationv82.class.getName());

    //Allele|Consequence|IMPACT|SYMBOL|Gene|Feature_type|Feature|BIOTYPE|EXON|INTRON|HGVSc|HGVSp|cDNA_position|CDS_position|Protein_position|Amino_acids|Codons|Existing_variation|DISTANCE|STRAND|VARIANT_CLASS|SYMBOL_SOURCE|HGNC_ID|CANONICAL|TSL|CCDS|ENSP|SWISSPROT|TREMBL|UNIPARC|GENE_PHENO|SIFT|PolyPhen|DOMAINS|HGVS_OFFSET|GMAF|AFR_MAF|AMR_MAF|EAS_MAF|EUR_MAF|SAS_MAF|AA_MAF|EA_MAF|CLIN_SIG|SOMATIC|PHENO|PUBMED|MOTIF_NAME|MOTIF_POS|HIGH_INF_POS|MOTIF_SCORE_CHANGE

    private boolean canonical;
    private int strand;
    private String record, allele, impact,	symbol,	gene, featureType, feature, biotype, exon, intron, hgvsCoding, hgvsProtein,
    cdnaPosition, cdsPosition, proteinPosition, aminoAcids, codons, existingVariation, distance, symbolSource, hgncId,
    tsl, ccds, ensp, swissprot, trembl, uniparc, sift, polyPhen, gMaf, afrMaf, amrMaf, easMaf,
    eurMaf, sasMaf, aaMaf, eaMaf, somatic, motifName, motifPos, highInfPos, motifScoreChange, variantClass, genePheno, hgvsOffset, pheno;
    private HashMap<String, HashSet<String>> domains = new HashMap<>();

    private HashSet<String> pubmedIds = new HashSet<>();
    private HashSet<String> consequences = new HashSet<>();
    private HashSet<String> clinSigs = new HashSet<>();

    public VEPAnnotationv82(String record) {
        this.record = record;
    }

    public void parseAnnotation() {
        String[] fields = record.split("\\|");

        try {

            if (!fields[0].equals("")) {
                this.allele = fields[0];
            }
            if (!fields[1].equals("")) {
                for (String consequence : fields[1].split("&")){
                    this.consequences.add(consequence);
                }
            }
            if (!fields[2].equals("")) {
                this.impact = fields[2];
            }
            if (!fields[3].equals("")) {
                this.symbol = fields[3];
            }
            if (!fields[4].equals("")) {
                this.gene = fields[4];
            }
            if (!fields[5].equals("")) {
                this.featureType = fields[5];
            }
            if (!fields[6].equals("")) {
                this.feature = fields[6];
            }
            if (!fields[7].equals("")) {
                this.biotype = fields[7];
            }
            if (!fields[8].equals("")) {
                this.exon = fields[8];
            }
            if (!fields[9].equals("")) {
                this.intron = fields[9];
            }
            if (!fields[10].equals("")) {
                String[] subFields = fields[10].split(":");
                this.hgvsCoding = subFields[1];
            }
            if (!fields[11].equals("")) {
                String[] subFields = fields[11].split(":");

                if (subFields[1].contains("(") && subFields[1].contains(")")){
                    subFields = subFields[1].split("\\(");
                    subFields = subFields[1].split("\\)");
                    if (subFields[0].equals("p.%3D")) this.hgvsProtein = "p.="; else this.hgvsProtein = subFields[0];
                } else {
                    if (subFields[1].equals("p.%3D")) this.hgvsProtein = "p.="; else this.hgvsProtein = subFields[1];
                }
            }
            if (!fields[12].equals("")) {
                this.cdnaPosition = fields[12];
            }
            if (!fields[13].equals("")) {
                this.cdsPosition = fields[13];
            }
            if (!fields[14].equals("")) {
                this.proteinPosition = fields[14];
            }
            if (!fields[15].equals("")) {
                this.aminoAcids = fields[15];
            }
            if (!fields[16].equals("")) {
                this.codons = fields[16];
            }
            if (!fields[17].equals("")) {
                this.existingVariation = fields[17];
            }
            if (!fields[18].equals("")) {
                this.distance = fields[18];
            }
            if (!fields[19].equals("")) {
                this.strand = Integer.parseInt(fields[19]);
            }
            if (!fields[20].equals("")) {
                this.variantClass = fields[20];
            }
            if (!fields[21].equals("")) {
                this.symbolSource = fields[21];
            }
            if (!fields[22].equals("")) {
                this.hgncId = fields[22];
            }
            if (fields[23].equals("YES")) {
                this.canonical = true;
            } else {
                this.canonical = false;
            }
            if (!fields[24].equals("")) {
                this.tsl = fields[24];
            }
            if (!fields[25].equals("")) {
                this.ccds = fields[25];
            }
            if (!fields[26].equals("")) {
                this.ensp = fields[26];
            }
            if (!fields[27].equals("")) {
                this.swissprot = fields[27];
            }
            if (!fields[28].equals("")) {
                this.trembl = fields[28];
            }
            if (!fields[29].equals("")) {
                this.uniparc = fields[29];
            }
            if (!fields[30].equals("")) {
                this.genePheno = fields[30];
            }
            if (!fields[31].equals("")) {
                String[] subFields = fields[31].split("\\(");
                this.sift = subFields[0];
            }
            if (!fields[32].equals("")) {
                String[] subFields = fields[32].split("\\(");
                this.polyPhen = subFields[0];
            }
            if (!fields[33].equals("")) {
                try {
                    //loop over domain acc identifier
                    for (String domain : fields[33].split("&")){

                        //split provider from identifier
                        String[] split = domain.split(":");

                        if (!this.domains.containsKey(split[0])){
                            this.domains.put(split[0], new HashSet<String>());
                        }

                        this.domains.get(split[0]).add(split[1]);
                    }
                } catch (Exception e) {
                    log.log(Level.INFO, e.getMessage());
                }

            }
            if (!fields[34].equals("")) {
                this.hgvsOffset = fields[34];
            }
            if (!fields[35].equals("")) {
                this.gMaf = fields[35];
            }
            if (!fields[36].equals("")) {
                this.afrMaf = fields[36];
            }
            if (!fields[37].equals("")) {
                this.amrMaf = fields[37];
            }
            if (!fields[38].equals("")) {
                this.easMaf = fields[38];
            }
            if (!fields[39].equals("")) {
                this.eurMaf = fields[39];
            }
            if (!fields[40].equals("")) {
                this.sasMaf = fields[40];
            }
            if (!fields[41].equals("")) {
                this.aaMaf = fields[41];
            }
            if (!fields[42].equals("")) {
                this.eaMaf = fields[42];
            }
            if (!fields[43].equals("")) {
                for (String clinSig : fields[43].split("&")){
                    this.clinSigs.add(clinSig);
                }
            }
            if (!fields[44].equals("")) {
                this.somatic = fields[44];
            }
            if (!fields[45].equals("")) {
                this.pheno = fields[45];
            }
            if (!fields[46].equals("")) {
                for (String pubmedId : fields[46].split("&")){
                    this.pubmedIds.add(pubmedId);
                }
            }
            if (!fields[47].equals("")) {
                this.motifName = fields[47];
            }
            if (!fields[48].equals("")) {
                this.motifPos = fields[48];
            }
            if (!fields[49].equals("")) {
                this.highInfPos = fields[49];
            }
            if (!fields[50].equals("")) {
                this.motifScoreChange = fields[50];
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            log.log(Level.FINE, e.getMessage());
        }

    }

    public String getAllele() {
        return allele;
    }
    public String getImpact() {
        return impact;
    }
    public String getSymbol() {
        return symbol;
    }
    public String getGene() {
        return gene;
    }
    public String getFeatureType() {
        return featureType;
    }
    public String getFeature() {
        return feature;
    }
    public String getBiotype() {
        return biotype;
    }
    public String getExon() {
        return exon;
    }
    public String getIntron() {
        return intron;
    }
    public String getHgvsCoding() {
        return hgvsCoding;
    }
    public String getHgvsProtein() {
        return hgvsProtein;
    }
    public String getCdnaPosition() {
        return cdnaPosition;
    }
    public String getCdsPosition() {
        return cdsPosition;
    }
    public String getProteinPosition() {
        return proteinPosition;
    }
    public String getAminoAcids() {
        return aminoAcids;
    }
    public String getCodons() {
        return codons;
    }
    public String getExistingVariation() {
        return existingVariation;
    }
    public String getDistance() {
        return distance;
    }
    public int getStrand() {
        return strand;
    }
    public String getSymbolSource() {
        return symbolSource;
    }
    public String getHgncId() {
        return hgncId;
    }
    public boolean isCanonical() {
        return canonical;
    }
    public String getTsl() {
        return tsl;
    }
    public String getCcds() {
        return ccds;
    }
    public String getEnsp() {
        return ensp;
    }
    public String getSwissprot() {
        return swissprot;
    }
    public String getTrembl() {
        return trembl;
    }
    public String getUniparc() {
        return uniparc;
    }
    public String getSift() {
        return sift;
    }
    public String getPolyPhen() {
        return polyPhen;
    }
    public HashMap<String, HashSet<String>> getDomains() {
        return domains;
    }
    public String getgMaf() {
        return gMaf;
    }
    public String getAfrMaf() {
        return afrMaf;
    }
    public String getAmrMaf() {
        return amrMaf;
    }
    public String getEasMaf() {
        return easMaf;
    }
    public String getEurMaf() {
        return eurMaf;
    }
    public String getSasMaf() {
        return sasMaf;
    }
    public String getAaMaf() {
        return aaMaf;
    }
    public String getEaMaf() {
        return eaMaf;
    }
    public String getSomatic() {
        return somatic;
    }
    public HashSet<String> getPubmedIds() {
        return pubmedIds;
    }
    public String getMotifName() {
        return motifName;
    }
    public String getMotifPos() {
        return motifPos;
    }
    public String getHighInfPos() {
        return highInfPos;
    }
    public String getMotifScoreChange() {
        return motifScoreChange;
    }
    public HashSet<String> getConsequences() {
        return consequences;
    }
    public HashSet<String> getClinSigs() {
        return clinSigs;
    }
    public String getVariantClass() {
        return variantClass;
    }
    public String getGenePheno() {
        return genePheno;
    }
    public String getHgvsOffset() {
        return hgvsOffset;
    }
    public String getPheno() {
        return pheno;
    }
}