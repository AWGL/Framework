package nhs.genetics.cardiff;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by msl on 27/01/2015.
 */
public class PipelineConfigFileMaker {

    private static final Logger log = Logger.getLogger(PipelineConfigFileMaker.class.getName());

    public static void makeIlluminaConfigFile(IlluminaSampleSheetFile illuminaSampleSheetFile, IlluminaRunParametersFile illuminaRunParametersFile)
    {
        log.log(Level.INFO, "Writing variable files...");

        StringBuilder analysisDirs = new StringBuilder("AnalysisDirs=( ");

        for (IlluminaSampleSheetRecord record : illuminaSampleSheetFile.getSampleSheetRecords())
        {
            analysisDirs.append('"');
            analysisDirs.append(Parameters.getCvxgenResultsRootDir());
            analysisDirs.append("/");
            analysisDirs.append(illuminaRunParametersFile.getRunIdentifier());
            analysisDirs.append('/');
            analysisDirs.append(record.getSampleID());
            analysisDirs.append('"');
            analysisDirs.append(' ');
        }

        analysisDirs.append(')');

        for (int n = 0; n < illuminaSampleSheetFile.getSampleSheetRecords().size(); ++n) {

            try (PrintWriter writer = new PrintWriter(illuminaSampleSheetFile.getSampleSheetRecords().get(n).getSampleID() + ".variables")){

                //write variable files
                writer.print("#Illumina Variables File\n");

                writer.print("\n#Run Details\n");
                writer.print("RunID=" + illuminaRunParametersFile.getRunIdentifier() + "\n");
                writer.print("ExperimentName=\"" + illuminaSampleSheetFile.getExperimentName() + "\"\n");
                writer.print("Platform=ILLUMINA\n");

                writer.print("\n#Demographics\n");
                writer.print("SampleID=" + illuminaSampleSheetFile.getSampleSheetRecords().get(n).getSampleID() + "\n");

                writer.print("\n#FASTQ Filenames\n");
                writer.print("Read1Fastq=" + illuminaSampleSheetFile.getSampleSheetRecords().get(n).getSampleID() + "_S" + (n + 1) + "_L001_R1_001.fastq.gz\n");
                writer.print("Read2Fastq=" + illuminaSampleSheetFile.getSampleSheetRecords().get(n).getSampleID() + "_S" + (n + 1) + "_L001_R2_001.fastq.gz\n");

                if (!illuminaSampleSheetFile.getSampleSheetRecords().get(n).getSampleProject().equals("")){
                    writer.print("\n#Sample Project\n");

                    StringBuilder sampleProjects = new StringBuilder("SampleProjects=( ");

                    for (String field : illuminaSampleSheetFile.getSampleSheetRecords().get(n).getSampleProject().split(";")){
                        sampleProjects.append('"');
                        sampleProjects.append(field);
                        sampleProjects.append('"');
                        sampleProjects.append(' ');
                    }

                    sampleProjects.append(')');

                    writer.print(sampleProjects.toString() + "\n");
                }

                writer.print("\n#Sample Descriptors\n");
                for (String field : illuminaSampleSheetFile.getSampleSheetRecords().get(n).getDescription().split(";")){
                    writer.print(field + "\n");
                }

                writer.print("\n#Analysis Folders\n");
                writer.print(analysisDirs.toString() + "\n");

                writer.close();

            } catch (IOException e){
                log.log(Level.SEVERE, "Could not write variables file: " + e.getMessage());
            }
        }

    }
}
