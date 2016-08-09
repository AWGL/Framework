package nhs.genetics.cardiff.framework;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A class for writing config files for pipelines
 *
 * @author  Matt Lyon
 * @version 1.1
 * @since   2015-01-27
 */
public class PipelineConfigFileMaker {

    private static final Logger log = Logger.getLogger(PipelineConfigFileMaker.class.getName());

    public static void makeIlluminaConfigFile(IlluminaSampleSheetFile illuminaSampleSheetFile, IlluminaRunParametersFile illuminaRunParametersFile)
    {
        log.log(Level.INFO, "Writing variable files...");

        for (int n = 0; n < illuminaSampleSheetFile.getSampleSheetRecords().size(); ++n) {

            try (PrintWriter writer = new PrintWriter(illuminaSampleSheetFile.getSampleSheetRecords().get(n).getSampleID() + ".variables")){

                //write variable files
                writer.print("#Illumina Variables File\n");

                writer.print("\n#Demographics\n");
                writer.print("sampleId=" + illuminaSampleSheetFile.getSampleSheetRecords().get(n).getSampleID() + "\n");

                writer.print("\n#Run Details\n");
                writer.print("seqId=" + illuminaRunParametersFile.getRunIdentifier() + "\n");
                writer.print("platform=ILLUMINA\n");
                writer.print("worklistId=\"" + illuminaSampleSheetFile.getSampleSheetRecords().get(n).getSamplePlate() + "\"\n");

                writer.print("\n#Sample Descriptors\n");
                for (String field : illuminaSampleSheetFile.getSampleSheetRecords().get(n).getDescription().split(";")){
                    writer.print(field + "\n");
                }

                writer.close();

            } catch (IOException e){
                log.log(Level.SEVERE, "Could not write variables file: " + e.getMessage());
            }
        }

    }

}
