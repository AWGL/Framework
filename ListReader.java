package nhs.genetics.cardiff.framework;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Helper class for reading lists
 *
 * @author  Matt Lyon
 * @version 1.0
 * @since   2015-02-26
 */

public class ListReader {

    private static final Logger log = Logger.getLogger(ListReader.class.getName());
    private File filePath;
    private ArrayList<String> elements = new ArrayList<String>();
    private HashSet<String> uniqueElements = new HashSet<String>();

    public ListReader(File filePath){
        this.filePath = filePath;
    }

    public void parseListReader() throws IOException {
        log.log(Level.INFO, "Parsing list");

        String line;

        try (BufferedReader bReader = new BufferedReader (new FileReader(filePath))){

            while ((line = bReader.readLine()) != null) {
                elements.add(line);
                uniqueElements.add(line);
            }

            bReader.close();
        }

    }

    public ArrayList<String> getElements() {
        return elements;
    }
    public File getFilePath() {
        return filePath;
    }
    public String getFileName(){
        return filePath.getName();
    }
    public HashSet<String> getUniqueElements() {
        return uniqueElements;
    }

}
