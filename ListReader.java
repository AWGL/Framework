package nhs.genetics.cardiff;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by msl on 26/02/2015.
 */
public class ListReader {

    private static final Logger log = Logger.getLogger(ListReader.class.getName());
    private File filePath;
    private ArrayList<String> elements = new ArrayList<String>();

    public ListReader(File filePath){
        this.filePath = filePath;
    }

    public void parseListReader(){

        String line;

        try (BufferedReader bReader = new BufferedReader (new FileReader(filePath))){

            while ((line = bReader.readLine()) != null) {
                elements.add(line);
            }

            bReader.close();
        } catch (IOException e){
            log.log(Level.SEVERE, "Problem reading list: " + e.getMessage());
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

}
