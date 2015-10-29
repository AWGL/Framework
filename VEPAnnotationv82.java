package nhs.genetics.cardiff;

import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by ml on 17/06/2015.
 */

//Allele|Consequence|IMPACT|SYMBOL|Gene|Feature_type|Feature|BIOTYPE|EXON|INTRON|HGVSc|HGVSp|cDNA_position|CDS_position|Protein_position|Amino_acids|Codons|Existing_variation|ALLELE_NUM|DISTANCE|STRAND|VARIANT_CLASS|SYMBOL_SOURCE|HGNC_ID|CANONICAL|TSL|CCDS|ENSP|SWISSPROT|TREMBL|UNIPARC|GENE_PHENO|SIFT|PolyPhen|DOMAINS|HGVS_OFFSET|GMAF|AFR_MAF|AMR_MAF|EAS_MAF|EUR_MAF|SAS_MAF|AA_MAF|EA_MAF|CLIN_SIG|SOMATIC|PHENO|PUBMED|MOTIF_NAME|MOTIF_POS|HIGH_INF_POS|MOTIF_SCORE_CHANGE">

public class VEPAnnotationv82 {

    private static final Logger log = Logger.getLogger(VEPAnnotationv82.class.getName());

    private boolean canonical = false;

    private int distance, strand;

    private String record, allele, impact, symbol, gene, featureType,feature, biotype, exon, intron, hgvsCoding, hgvsProtein,
            cdnaPosition, cdsPosition, proteinPosition, aminoAcids, codons, variantClass, symbolSource, hgncId, tsl, ccds,
            ensp, swissprot, uniparc, genePheno, sift, polyPhen, hgvsOffset, gMaf, afrMaf, amrMaf, easMaf, eurMaf, sasMaf,
            aaMaf, eaMaf, somatic, pheno, motifName, motifPos, highInfPos, motifScoreChange;

    private HashSet<String> pubmeds = new HashSet<>();
    private HashSet<String> domains = new HashSet<>();
    private HashSet<String> trembls = new HashSet<>();
    private HashSet<String> existingVariations = new HashSet<>();
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
                for (String existingVariation : fields[17].split("&")){
                    this.existingVariations.add(existingVariation);
                }
            }
            if (!fields[18].equals("")) {
                this.distance = Integer.parseInt(fields[18]);
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
            if (!fields[23].equals("") && fields[23].equals("YES")) {
                this.canonical = true;
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
                for (String trembl : fields[28].split("&")){
                    this.trembls.add(trembl);
                }
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
                for (String domain : fields[33].split("&")){
                    this.domains.add(domain);
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
                for (String pubmed : fields[46].split("&")){
                    this.pubmeds.add(pubmed);
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
            log.log(Level.FINE, "Annotation record incomplete: " + e.getMessage());
        }

    }

    public boolean isCanonical() {
        return canonical;
    }

    public int getDistance() {
        return distance;
    }

    public int getStrand() {
        return strand;
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

    public String getVariantClass() {
        return variantClass;
    }

    public String getSymbolSource() {
        return symbolSource;
    }

    public String getHgncId() {
        return hgncId;
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

    public String getUniparc() {
        return uniparc;
    }

    public String getGenePheno() {
        return genePheno;
    }

    public String getSift() {
        return sift;
    }

    public String getPolyPhen() {
        return polyPhen;
    }

    public String getHgvsOffset() {
        return hgvsOffset;
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

    public String getPheno() {
        return pheno;
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

    public String[] getPubmeds() {
        return pubmeds.toArray(new String[pubmeds.size()]);
    }

    public HashSet<String> getDomains() {
        return domains;
    }

    public HashSet<String> getTrembls() {
        return trembls;
    }

    public HashSet<String> getExistingVariations() {
        return existingVariations;
    }

    public HashSet<String> getConsequences() {
        return consequences;
    }

    public HashSet<String> getClinSigs() {
        return clinSigs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VEPAnnotationv82 that = (VEPAnnotationv82) o;

        if (canonical != that.canonical) return false;
        if (distance != that.distance) return false;
        if (strand != that.strand) return false;
        if (record != null ? !record.equals(that.record) : that.record != null) return false;
        if (allele != null ? !allele.equals(that.allele) : that.allele != null) return false;
        if (impact != null ? !impact.equals(that.impact) : that.impact != null) return false;
        if (symbol != null ? !symbol.equals(that.symbol) : that.symbol != null) return false;
        if (gene != null ? !gene.equals(that.gene) : that.gene != null) return false;
        if (featureType != null ? !featureType.equals(that.featureType) : that.featureType != null) return false;
        if (feature != null ? !feature.equals(that.feature) : that.feature != null) return false;
        if (biotype != null ? !biotype.equals(that.biotype) : that.biotype != null) return false;
        if (exon != null ? !exon.equals(that.exon) : that.exon != null) return false;
        if (intron != null ? !intron.equals(that.intron) : that.intron != null) return false;
        if (hgvsCoding != null ? !hgvsCoding.equals(that.hgvsCoding) : that.hgvsCoding != null) return false;
        if (hgvsProtein != null ? !hgvsProtein.equals(that.hgvsProtein) : that.hgvsProtein != null) return false;
        if (cdnaPosition != null ? !cdnaPosition.equals(that.cdnaPosition) : that.cdnaPosition != null) return false;
        if (cdsPosition != null ? !cdsPosition.equals(that.cdsPosition) : that.cdsPosition != null) return false;
        if (proteinPosition != null ? !proteinPosition.equals(that.proteinPosition) : that.proteinPosition != null)
            return false;
        if (aminoAcids != null ? !aminoAcids.equals(that.aminoAcids) : that.aminoAcids != null) return false;
        if (codons != null ? !codons.equals(that.codons) : that.codons != null) return false;
        if (variantClass != null ? !variantClass.equals(that.variantClass) : that.variantClass != null) return false;
        if (symbolSource != null ? !symbolSource.equals(that.symbolSource) : that.symbolSource != null) return false;
        if (hgncId != null ? !hgncId.equals(that.hgncId) : that.hgncId != null) return false;
        if (tsl != null ? !tsl.equals(that.tsl) : that.tsl != null) return false;
        if (ccds != null ? !ccds.equals(that.ccds) : that.ccds != null) return false;
        if (ensp != null ? !ensp.equals(that.ensp) : that.ensp != null) return false;
        if (swissprot != null ? !swissprot.equals(that.swissprot) : that.swissprot != null) return false;
        if (uniparc != null ? !uniparc.equals(that.uniparc) : that.uniparc != null) return false;
        if (genePheno != null ? !genePheno.equals(that.genePheno) : that.genePheno != null) return false;
        if (sift != null ? !sift.equals(that.sift) : that.sift != null) return false;
        if (polyPhen != null ? !polyPhen.equals(that.polyPhen) : that.polyPhen != null) return false;
        if (hgvsOffset != null ? !hgvsOffset.equals(that.hgvsOffset) : that.hgvsOffset != null) return false;
        if (gMaf != null ? !gMaf.equals(that.gMaf) : that.gMaf != null) return false;
        if (afrMaf != null ? !afrMaf.equals(that.afrMaf) : that.afrMaf != null) return false;
        if (amrMaf != null ? !amrMaf.equals(that.amrMaf) : that.amrMaf != null) return false;
        if (easMaf != null ? !easMaf.equals(that.easMaf) : that.easMaf != null) return false;
        if (eurMaf != null ? !eurMaf.equals(that.eurMaf) : that.eurMaf != null) return false;
        if (sasMaf != null ? !sasMaf.equals(that.sasMaf) : that.sasMaf != null) return false;
        if (aaMaf != null ? !aaMaf.equals(that.aaMaf) : that.aaMaf != null) return false;
        if (eaMaf != null ? !eaMaf.equals(that.eaMaf) : that.eaMaf != null) return false;
        if (somatic != null ? !somatic.equals(that.somatic) : that.somatic != null) return false;
        if (pheno != null ? !pheno.equals(that.pheno) : that.pheno != null) return false;
        if (motifName != null ? !motifName.equals(that.motifName) : that.motifName != null) return false;
        if (motifPos != null ? !motifPos.equals(that.motifPos) : that.motifPos != null) return false;
        if (highInfPos != null ? !highInfPos.equals(that.highInfPos) : that.highInfPos != null) return false;
        if (motifScoreChange != null ? !motifScoreChange.equals(that.motifScoreChange) : that.motifScoreChange != null)
            return false;
        if (pubmeds != null ? !pubmeds.equals(that.pubmeds) : that.pubmeds != null) return false;
        if (domains != null ? !domains.equals(that.domains) : that.domains != null) return false;
        if (trembls != null ? !trembls.equals(that.trembls) : that.trembls != null) return false;
        if (existingVariations != null ? !existingVariations.equals(that.existingVariations) : that.existingVariations != null)
            return false;
        if (consequences != null ? !consequences.equals(that.consequences) : that.consequences != null) return false;
        return !(clinSigs != null ? !clinSigs.equals(that.clinSigs) : that.clinSigs != null);

    }

    @Override
    public int hashCode() {
        int result = (canonical ? 1 : 0);
        result = 31 * result + distance;
        result = 31 * result + strand;
        result = 31 * result + (record != null ? record.hashCode() : 0);
        result = 31 * result + (allele != null ? allele.hashCode() : 0);
        result = 31 * result + (impact != null ? impact.hashCode() : 0);
        result = 31 * result + (symbol != null ? symbol.hashCode() : 0);
        result = 31 * result + (gene != null ? gene.hashCode() : 0);
        result = 31 * result + (featureType != null ? featureType.hashCode() : 0);
        result = 31 * result + (feature != null ? feature.hashCode() : 0);
        result = 31 * result + (biotype != null ? biotype.hashCode() : 0);
        result = 31 * result + (exon != null ? exon.hashCode() : 0);
        result = 31 * result + (intron != null ? intron.hashCode() : 0);
        result = 31 * result + (hgvsCoding != null ? hgvsCoding.hashCode() : 0);
        result = 31 * result + (hgvsProtein != null ? hgvsProtein.hashCode() : 0);
        result = 31 * result + (cdnaPosition != null ? cdnaPosition.hashCode() : 0);
        result = 31 * result + (cdsPosition != null ? cdsPosition.hashCode() : 0);
        result = 31 * result + (proteinPosition != null ? proteinPosition.hashCode() : 0);
        result = 31 * result + (aminoAcids != null ? aminoAcids.hashCode() : 0);
        result = 31 * result + (codons != null ? codons.hashCode() : 0);
        result = 31 * result + (variantClass != null ? variantClass.hashCode() : 0);
        result = 31 * result + (symbolSource != null ? symbolSource.hashCode() : 0);
        result = 31 * result + (hgncId != null ? hgncId.hashCode() : 0);
        result = 31 * result + (tsl != null ? tsl.hashCode() : 0);
        result = 31 * result + (ccds != null ? ccds.hashCode() : 0);
        result = 31 * result + (ensp != null ? ensp.hashCode() : 0);
        result = 31 * result + (swissprot != null ? swissprot.hashCode() : 0);
        result = 31 * result + (uniparc != null ? uniparc.hashCode() : 0);
        result = 31 * result + (genePheno != null ? genePheno.hashCode() : 0);
        result = 31 * result + (sift != null ? sift.hashCode() : 0);
        result = 31 * result + (polyPhen != null ? polyPhen.hashCode() : 0);
        result = 31 * result + (hgvsOffset != null ? hgvsOffset.hashCode() : 0);
        result = 31 * result + (gMaf != null ? gMaf.hashCode() : 0);
        result = 31 * result + (afrMaf != null ? afrMaf.hashCode() : 0);
        result = 31 * result + (amrMaf != null ? amrMaf.hashCode() : 0);
        result = 31 * result + (easMaf != null ? easMaf.hashCode() : 0);
        result = 31 * result + (eurMaf != null ? eurMaf.hashCode() : 0);
        result = 31 * result + (sasMaf != null ? sasMaf.hashCode() : 0);
        result = 31 * result + (aaMaf != null ? aaMaf.hashCode() : 0);
        result = 31 * result + (eaMaf != null ? eaMaf.hashCode() : 0);
        result = 31 * result + (somatic != null ? somatic.hashCode() : 0);
        result = 31 * result + (pheno != null ? pheno.hashCode() : 0);
        result = 31 * result + (motifName != null ? motifName.hashCode() : 0);
        result = 31 * result + (motifPos != null ? motifPos.hashCode() : 0);
        result = 31 * result + (highInfPos != null ? highInfPos.hashCode() : 0);
        result = 31 * result + (motifScoreChange != null ? motifScoreChange.hashCode() : 0);
        result = 31 * result + (pubmeds != null ? pubmeds.hashCode() : 0);
        result = 31 * result + (domains != null ? domains.hashCode() : 0);
        result = 31 * result + (trembls != null ? trembls.hashCode() : 0);
        result = 31 * result + (existingVariations != null ? existingVariations.hashCode() : 0);
        result = 31 * result + (consequences != null ? consequences.hashCode() : 0);
        result = 31 * result + (clinSigs != null ? clinSigs.hashCode() : 0);
        return result;
    }
}