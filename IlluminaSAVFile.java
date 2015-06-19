package nhs.genetics.cardiff;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by msl on 04/02/2015.
 */
public class IlluminaSAVFile {
    private static final Logger log = Logger.getLogger(IlluminaSAVFile.class.getName());

    private HashMap<Integer, Double> percentGreaterThanQ30ByCycleMedian = new HashMap<Integer, Double>();
    private File illuminaSAVFile;
    private Double medianClusterDensity;

    public IlluminaSAVFile(File illuminaSAVFile){
        this.illuminaSAVFile = illuminaSAVFile;
    }

    private static double median(ArrayList<Double> values)
    {
        Collections.sort(values);

        if (values.size() % 2 == 1)
            return values.get((values.size()+1)/2-1);
        else
        {
            double lower = values.get(values.size()/2-1);
            double upper = values.get(values.size()/2);

            return (lower + upper) / 2.0;
        }
    }

    private void parseSAVFile(){
        log.log(Level.INFO, "Reading sequence analysis viewer file: " + illuminaSAVFile);

        boolean passedHeader = false;
        String line;
        Integer cycleNo;
        HashMap<String, Integer> headers = new HashMap<String, Integer>();
        HashMap<Integer, ArrayList<Double>> percentGreaterThanQ30ByCycle = new HashMap<Integer, ArrayList<Double>>();
        ArrayList<Double> clusterDensity = new ArrayList<Double>();

        try (BufferedReader savReader = new BufferedReader(new FileReader(illuminaSAVFile))){

            while ((line = savReader.readLine()) != null) {

                if (!passedHeader) {

                    passedHeader = true;
                    String[] fields = line.split(",");

                    //store headers with column numbers
                    for (Integer n = 0; n < fields.length; ++n) {
                        headers.put(fields[n], n);
                    }

                } else {

                    String[] fields = line.split(",");
                    cycleNo = Integer.parseInt(fields[headers.get("Cycle")]);

                    if (!percentGreaterThanQ30ByCycle.containsKey(cycleNo)) {
                        percentGreaterThanQ30ByCycle.put(cycleNo, new ArrayList<Double>());
                    }

                    percentGreaterThanQ30ByCycle.get(cycleNo).add(Double.parseDouble(fields[headers.get("%> Q30")]));
                    clusterDensity.add(Double.parseDouble(fields[headers.get("Density Pf(k/mm2)")]));

                }
            }

            savReader.close();

        } catch (Exception e){
            log.log(Level.SEVERE, "Reading sequence analysis viewer file: " + e.getMessage());
        }

        for (Map.Entry<Integer, ArrayList<Double>> iter : percentGreaterThanQ30ByCycle.entrySet()){
            percentGreaterThanQ30ByCycleMedian.put(iter.getKey(), median(iter.getValue()));
        }

        medianClusterDensity = median(clusterDensity);

    }

    private void extractSAVMetrics(){
        //TODO: call SAV and generate table
    }

    public HashMap<Integer, Double> getPercentGreaterThanQ30ByCycleMedian() {
        return percentGreaterThanQ30ByCycleMedian;
    }
    public Double getMedianClusterDensity() {
        return medianClusterDensity;
    }
}
