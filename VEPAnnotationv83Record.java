package nhs.genetics.cardiff.framework;

import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by ml on 17/06/2015.
 */

public class VEPAnnotationv83Record {

    private static final Logger log = Logger.getLogger(VEPAnnotationv83Record.class.getName());

    //Allele|Consequence|IMPACT|SYMBOL|Gene|Feature_type|Feature|BIOTYPE|EXON|INTRON|HGVSc|HGVSp|cDNA_position|CDS_position|Protein_position|Amino_acids|Codons|Existing_variation|DISTANCE|STRAND|VARIANT_CLASS|SYMBOL_SOURCE|HGNC_ID|CANONICAL|TSL|APPRIS|CCDS|ENSP|SWISSPROT|TREMBL|UNIPARC|REFSEQ_MATCH|GENE_PHENO|SIFT|PolyPhen|DOMAINS|HGVS_OFFSET|GMAF|AFR_MAF|AMR_MAF|EAS_MAF|EUR_MAF|SAS_MAF|AA_MAF|EA_MAF|ExAC_MAF|ExAC_Adj_MAF|ExAC_AFR_MAF|ExAC_AMR_MAF|ExAC_EAS_MAF|ExAC_FIN_MAF|ExAC_NFE_MAF|ExAC_OTH_MAF|ExAC_SAS_MAF|CLIN_SIG|SOMATIC|PHENO|PUBMED|MOTIF_NAME|MOTIF_POS|HIGH_INF_POS|MOTIF_SCORE_CHANGE

    private boolean canonical;
    private int strand;
    private String record, allele, impact,	symbol,	gene, featureType, feature, biotype, exon, intron, hgvsc, hgvsp,
            cdnaPosition, cdsPosition, proteinPosition, aminoAcids, codons, existingVariation, distance, symbolSource, hgncId,
            tsl, appris, ccds, ensp, swissprot, trembl, uniparc, refseqMatch, sift, polyPhen, gMaf, afrMaf, amrMaf, easMaf, eurMaf,
            sasMaf, aaMaf, eaMaf, exacMaf, exacAdjMaf, exacAfrMaf, exacAmrMaf, exacEasMaf, exacFinMaf, exacNfeMaf, exacOthMaf,
            exacSasMaf, somatic, motifName, motifPos, highInfPos, motifScoreChange, variantClass, genePheno, hgvsOffset, pheno;
    private HashMap<String, HashSet<String>> domains = new HashMap<>();
    private HashSet<String> pubmedIds = new HashSet<>();
    private HashSet<String> consequences = new HashSet<>();
    private HashSet<String> clinSigs = new HashSet<>();

    public VEPAnnotationv83Record(String record) {
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
                this.hgvsc = subFields[1];
            }
            if (!fields[11].equals("")) {
                String[] subFields = fields[11].split(":");

                if (subFields[1].contains("(") && subFields[1].contains(")")){
                    subFields = subFields[1].split("\\(");
                    subFields = subFields[1].split("\\)");
                    if (subFields[0].equals("p.%3D")) this.hgvsp = "p.="; else this.hgvsp = subFields[0];
                } else {
                    if (subFields[1].equals("p.%3D")) this.hgvsp = "p.="; else this.hgvsp = subFields[1];
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
                this.appris = fields[25];
            }
            if (!fields[26].equals("")) {
                this.ccds = fields[26];
            }
            if (!fields[27].equals("")) {
                this.ensp = fields[27];
            }
            if (!fields[28].equals("")) {
                this.swissprot = fields[28];
            }
            if (!fields[29].equals("")) {
                this.trembl = fields[29];
            }
            if (!fields[30].equals("")) {
                this.uniparc = fields[30];
            }
            if (!fields[31].equals("")) {
                this.refseqMatch = fields[31];
            }
            if (!fields[32].equals("")) {
                this.genePheno = fields[32];
            }
            if (!fields[33].equals("")) {
                String[] subFields = fields[33].split("\\(");
                this.sift = subFields[0];
            }
            if (!fields[34].equals("")) {
                String[] subFields = fields[34].split("\\(");
                this.polyPhen = subFields[0];
            }
            if (!fields[35].equals("")) {
                try {
                    //loop over domain acc identifier
                    for (String domain : fields[35].split("&")){

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
            if (!fields[36].equals("")) {
                this.hgvsOffset = fields[36];
            }
            if (!fields[37].equals("")) {
                this.gMaf = fields[37];
            }
            if (!fields[38].equals("")) {
                this.afrMaf = fields[38];
            }
            if (!fields[39].equals("")) {
                this.amrMaf = fields[39];
            }
            if (!fields[40].equals("")) {
                this.easMaf = fields[40];
            }
            if (!fields[41].equals("")) {
                this.eurMaf = fields[41];
            }
            if (!fields[42].equals("")) {
                this.sasMaf = fields[42];
            }
            if (!fields[43].equals("")) {
                this.aaMaf = fields[43];
            }
            if (!fields[44].equals("")) {
                this.eaMaf = fields[44];
            }
            if (!fields[45].equals("")) {
                this.exacMaf = fields[45];
            }
            if (!fields[46].equals("")) {
                this.exacAdjMaf = fields[46];
            }
            if (!fields[47].equals("")) {
                this.exacAfrMaf = fields[47];
            }
            if (!fields[48].equals("")) {
                this.exacAmrMaf = fields[48];
            }
            if (!fields[49].equals("")) {
                this.exacEasMaf = fields[49];
            }
            if (!fields[50].equals("")) {
                this.exacFinMaf = fields[50];
            }
            if (!fields[51].equals("")) {
                this.exacNfeMaf = fields[51];
            }
            if (!fields[52].equals("")) {
                this.exacOthMaf = fields[52];
            }
            if (!fields[53].equals("")) {
                this.exacSasMaf = fields[53];
            }
            if (!fields[54].equals("")) {
                for (String clinSig : fields[54].split("&")){
                    this.clinSigs.add(clinSig);
                }
            }
            if (!fields[55].equals("")) {
                this.somatic = fields[55];
            }
            if (!fields[56].equals("")) {
                this.pheno = fields[56];
            }
            if (!fields[57].equals("")) {
                for (String pubmedId : fields[57].split("&")){
                    this.pubmedIds.add(pubmedId);
                }
            }
            if (!fields[58].equals("")) {
                this.motifName = fields[58];
            }
            if (!fields[59].equals("")) {
                this.motifPos = fields[59];
            }
            if (!fields[60].equals("")) {
                this.highInfPos = fields[60];
            }
            if (!fields[61].equals("")) {
                this.motifScoreChange = fields[61];
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            log.log(Level.FINE, e.getMessage());
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VEPAnnotationv83Record that = (VEPAnnotationv83Record) o;

        if (canonical != that.canonical) return false;
        if (strand != that.strand) return false;
        if (allele != null ? !allele.equals(that.allele) : that.allele != null) return false;
        if (impact != null ? !impact.equals(that.impact) : that.impact != null) return false;
        if (symbol != null ? !symbol.equals(that.symbol) : that.symbol != null) return false;
        if (gene != null ? !gene.equals(that.gene) : that.gene != null) return false;
        if (featureType != null ? !featureType.equals(that.featureType) : that.featureType != null) return false;
        if (feature != null ? !feature.equals(that.feature) : that.feature != null) return false;
        if (biotype != null ? !biotype.equals(that.biotype) : that.biotype != null) return false;
        if (exon != null ? !exon.equals(that.exon) : that.exon != null) return false;
        if (intron != null ? !intron.equals(that.intron) : that.intron != null) return false;
        if (hgvsc != null ? !hgvsc.equals(that.hgvsc) : that.hgvsc != null) return false;
        if (hgvsp != null ? !hgvsp.equals(that.hgvsp) : that.hgvsp != null) return false;
        if (cdnaPosition != null ? !cdnaPosition.equals(that.cdnaPosition) : that.cdnaPosition != null) return false;
        if (cdsPosition != null ? !cdsPosition.equals(that.cdsPosition) : that.cdsPosition != null) return false;
        if (proteinPosition != null ? !proteinPosition.equals(that.proteinPosition) : that.proteinPosition != null)
            return false;
        if (aminoAcids != null ? !aminoAcids.equals(that.aminoAcids) : that.aminoAcids != null) return false;
        if (codons != null ? !codons.equals(that.codons) : that.codons != null) return false;
        if (existingVariation != null ? !existingVariation.equals(that.existingVariation) : that.existingVariation != null)
            return false;
        if (distance != null ? !distance.equals(that.distance) : that.distance != null) return false;
        if (symbolSource != null ? !symbolSource.equals(that.symbolSource) : that.symbolSource != null) return false;
        if (hgncId != null ? !hgncId.equals(that.hgncId) : that.hgncId != null) return false;
        if (tsl != null ? !tsl.equals(that.tsl) : that.tsl != null) return false;
        if (appris != null ? !appris.equals(that.appris) : that.appris != null) return false;
        if (ccds != null ? !ccds.equals(that.ccds) : that.ccds != null) return false;
        if (ensp != null ? !ensp.equals(that.ensp) : that.ensp != null) return false;
        if (swissprot != null ? !swissprot.equals(that.swissprot) : that.swissprot != null) return false;
        if (trembl != null ? !trembl.equals(that.trembl) : that.trembl != null) return false;
        if (uniparc != null ? !uniparc.equals(that.uniparc) : that.uniparc != null) return false;
        if (refseqMatch != null ? !refseqMatch.equals(that.refseqMatch) : that.refseqMatch != null) return false;
        if (sift != null ? !sift.equals(that.sift) : that.sift != null) return false;
        if (polyPhen != null ? !polyPhen.equals(that.polyPhen) : that.polyPhen != null) return false;
        if (gMaf != null ? !gMaf.equals(that.gMaf) : that.gMaf != null) return false;
        if (afrMaf != null ? !afrMaf.equals(that.afrMaf) : that.afrMaf != null) return false;
        if (amrMaf != null ? !amrMaf.equals(that.amrMaf) : that.amrMaf != null) return false;
        if (easMaf != null ? !easMaf.equals(that.easMaf) : that.easMaf != null) return false;
        if (eurMaf != null ? !eurMaf.equals(that.eurMaf) : that.eurMaf != null) return false;
        if (sasMaf != null ? !sasMaf.equals(that.sasMaf) : that.sasMaf != null) return false;
        if (aaMaf != null ? !aaMaf.equals(that.aaMaf) : that.aaMaf != null) return false;
        if (eaMaf != null ? !eaMaf.equals(that.eaMaf) : that.eaMaf != null) return false;
        if (exacMaf != null ? !exacMaf.equals(that.exacMaf) : that.exacMaf != null) return false;
        if (exacAdjMaf != null ? !exacAdjMaf.equals(that.exacAdjMaf) : that.exacAdjMaf != null) return false;
        if (exacAfrMaf != null ? !exacAfrMaf.equals(that.exacAfrMaf) : that.exacAfrMaf != null) return false;
        if (exacAmrMaf != null ? !exacAmrMaf.equals(that.exacAmrMaf) : that.exacAmrMaf != null) return false;
        if (exacEasMaf != null ? !exacEasMaf.equals(that.exacEasMaf) : that.exacEasMaf != null) return false;
        if (exacFinMaf != null ? !exacFinMaf.equals(that.exacFinMaf) : that.exacFinMaf != null) return false;
        if (exacNfeMaf != null ? !exacNfeMaf.equals(that.exacNfeMaf) : that.exacNfeMaf != null) return false;
        if (exacOthMaf != null ? !exacOthMaf.equals(that.exacOthMaf) : that.exacOthMaf != null) return false;
        if (exacSasMaf != null ? !exacSasMaf.equals(that.exacSasMaf) : that.exacSasMaf != null) return false;
        if (somatic != null ? !somatic.equals(that.somatic) : that.somatic != null) return false;
        if (motifName != null ? !motifName.equals(that.motifName) : that.motifName != null) return false;
        if (motifPos != null ? !motifPos.equals(that.motifPos) : that.motifPos != null) return false;
        if (highInfPos != null ? !highInfPos.equals(that.highInfPos) : that.highInfPos != null) return false;
        if (motifScoreChange != null ? !motifScoreChange.equals(that.motifScoreChange) : that.motifScoreChange != null)
            return false;
        if (variantClass != null ? !variantClass.equals(that.variantClass) : that.variantClass != null) return false;
        if (genePheno != null ? !genePheno.equals(that.genePheno) : that.genePheno != null) return false;
        if (hgvsOffset != null ? !hgvsOffset.equals(that.hgvsOffset) : that.hgvsOffset != null) return false;
        if (pheno != null ? !pheno.equals(that.pheno) : that.pheno != null) return false;
        if (domains != null ? !domains.equals(that.domains) : that.domains != null) return false;
        if (pubmedIds != null ? !pubmedIds.equals(that.pubmedIds) : that.pubmedIds != null) return false;
        if (consequences != null ? !consequences.equals(that.consequences) : that.consequences != null) return false;
        return clinSigs != null ? clinSigs.equals(that.clinSigs) : that.clinSigs == null;

    }

    @Override
    public int hashCode() {
        int result = (canonical ? 1 : 0);
        result = 31 * result + strand;
        result = 31 * result + (allele != null ? allele.hashCode() : 0);
        result = 31 * result + (impact != null ? impact.hashCode() : 0);
        result = 31 * result + (symbol != null ? symbol.hashCode() : 0);
        result = 31 * result + (gene != null ? gene.hashCode() : 0);
        result = 31 * result + (featureType != null ? featureType.hashCode() : 0);
        result = 31 * result + (feature != null ? feature.hashCode() : 0);
        result = 31 * result + (biotype != null ? biotype.hashCode() : 0);
        result = 31 * result + (exon != null ? exon.hashCode() : 0);
        result = 31 * result + (intron != null ? intron.hashCode() : 0);
        result = 31 * result + (hgvsc != null ? hgvsc.hashCode() : 0);
        result = 31 * result + (hgvsp != null ? hgvsp.hashCode() : 0);
        result = 31 * result + (cdnaPosition != null ? cdnaPosition.hashCode() : 0);
        result = 31 * result + (cdsPosition != null ? cdsPosition.hashCode() : 0);
        result = 31 * result + (proteinPosition != null ? proteinPosition.hashCode() : 0);
        result = 31 * result + (aminoAcids != null ? aminoAcids.hashCode() : 0);
        result = 31 * result + (codons != null ? codons.hashCode() : 0);
        result = 31 * result + (existingVariation != null ? existingVariation.hashCode() : 0);
        result = 31 * result + (distance != null ? distance.hashCode() : 0);
        result = 31 * result + (symbolSource != null ? symbolSource.hashCode() : 0);
        result = 31 * result + (hgncId != null ? hgncId.hashCode() : 0);
        result = 31 * result + (tsl != null ? tsl.hashCode() : 0);
        result = 31 * result + (appris != null ? appris.hashCode() : 0);
        result = 31 * result + (ccds != null ? ccds.hashCode() : 0);
        result = 31 * result + (ensp != null ? ensp.hashCode() : 0);
        result = 31 * result + (swissprot != null ? swissprot.hashCode() : 0);
        result = 31 * result + (trembl != null ? trembl.hashCode() : 0);
        result = 31 * result + (uniparc != null ? uniparc.hashCode() : 0);
        result = 31 * result + (refseqMatch != null ? refseqMatch.hashCode() : 0);
        result = 31 * result + (sift != null ? sift.hashCode() : 0);
        result = 31 * result + (polyPhen != null ? polyPhen.hashCode() : 0);
        result = 31 * result + (gMaf != null ? gMaf.hashCode() : 0);
        result = 31 * result + (afrMaf != null ? afrMaf.hashCode() : 0);
        result = 31 * result + (amrMaf != null ? amrMaf.hashCode() : 0);
        result = 31 * result + (easMaf != null ? easMaf.hashCode() : 0);
        result = 31 * result + (eurMaf != null ? eurMaf.hashCode() : 0);
        result = 31 * result + (sasMaf != null ? sasMaf.hashCode() : 0);
        result = 31 * result + (aaMaf != null ? aaMaf.hashCode() : 0);
        result = 31 * result + (eaMaf != null ? eaMaf.hashCode() : 0);
        result = 31 * result + (exacMaf != null ? exacMaf.hashCode() : 0);
        result = 31 * result + (exacAdjMaf != null ? exacAdjMaf.hashCode() : 0);
        result = 31 * result + (exacAfrMaf != null ? exacAfrMaf.hashCode() : 0);
        result = 31 * result + (exacAmrMaf != null ? exacAmrMaf.hashCode() : 0);
        result = 31 * result + (exacEasMaf != null ? exacEasMaf.hashCode() : 0);
        result = 31 * result + (exacFinMaf != null ? exacFinMaf.hashCode() : 0);
        result = 31 * result + (exacNfeMaf != null ? exacNfeMaf.hashCode() : 0);
        result = 31 * result + (exacOthMaf != null ? exacOthMaf.hashCode() : 0);
        result = 31 * result + (exacSasMaf != null ? exacSasMaf.hashCode() : 0);
        result = 31 * result + (somatic != null ? somatic.hashCode() : 0);
        result = 31 * result + (motifName != null ? motifName.hashCode() : 0);
        result = 31 * result + (motifPos != null ? motifPos.hashCode() : 0);
        result = 31 * result + (highInfPos != null ? highInfPos.hashCode() : 0);
        result = 31 * result + (motifScoreChange != null ? motifScoreChange.hashCode() : 0);
        result = 31 * result + (variantClass != null ? variantClass.hashCode() : 0);
        result = 31 * result + (genePheno != null ? genePheno.hashCode() : 0);
        result = 31 * result + (hgvsOffset != null ? hgvsOffset.hashCode() : 0);
        result = 31 * result + (pheno != null ? pheno.hashCode() : 0);
        result = 31 * result + (domains != null ? domains.hashCode() : 0);
        result = 31 * result + (pubmedIds != null ? pubmedIds.hashCode() : 0);
        result = 31 * result + (consequences != null ? consequences.hashCode() : 0);
        result = 31 * result + (clinSigs != null ? clinSigs.hashCode() : 0);
        return result;
    }

    public boolean isCanonical() {
        return canonical;
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
    public String getHgvsc() {
        return hgvsc;
    }
    public String getHgvsp() {
        return hgvsp;
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
    public String getSymbolSource() {
        return symbolSource;
    }
    public String getHgncId() {
        return hgncId;
    }
    public String getTsl() {
        return tsl;
    }
    public String getAppris() {
        return appris;
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
    public String getRefseqMatch() {
        return refseqMatch;
    }
    public String getSift() {
        return sift;
    }
    public String getPolyPhen() {
        return polyPhen;
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
    public String getExacMaf() {
        return exacMaf;
    }
    public String getExacAdjMaf() {
        return exacAdjMaf;
    }
    public String getExacAfrMaf() {
        return exacAfrMaf;
    }
    public String getExacAmrMaf() {
        return exacAmrMaf;
    }
    public String getExacEasMaf() {
        return exacEasMaf;
    }
    public String getExacFinMaf() {
        return exacFinMaf;
    }
    public String getExacNfeMaf() {
        return exacNfeMaf;
    }
    public String getExacOthMaf() {
        return exacOthMaf;
    }
    public String getExacSasMaf() {
        return exacSasMaf;
    }
    public String getSomatic() {
        return somatic;
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
    public HashMap<String, HashSet<String>> getDomains() {
        return domains;
    }
    public HashSet<String> getPubmedIds() {
        return pubmedIds;
    }
    public HashSet<String> getConsequences() {
        return consequences;
    }
    public HashSet<String> getClinSigs() {
        return clinSigs;
    }

}
