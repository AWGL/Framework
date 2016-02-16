package nhs.genetics.cardiff;

import java.util.ArrayList;

/**
 * Created by ml on 12/02/2016.
 */
public class OmimMorbidMapRecord {

    private String line, disorder;
    private ArrayList<String> symbols = new ArrayList<>();

    public OmimMorbidMapRecord(String line){
        this.line = line;
    }

    public void parseRecord(){

        String[] fields = line.split("\\|");

        //get disorder
        disorder = fields[0];

        //split symbols
        for (String symbol : fields[1].split(",")){
            symbols.add(symbol.trim());
        }

    }

    public String getDisorder() {
        return disorder;
    }
    public ArrayList<String> getSymbols() {
        return symbols;
    }

}
