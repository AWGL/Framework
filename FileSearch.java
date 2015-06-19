package nhs.genetics.cardiff;

/**
 * Created by msl on 14/01/2015.
 */
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileSearch {

    private static final Logger log = Logger.getLogger(FileSearch.class.getName());

    private String fileNameToSearch;
    private List<String> result = new ArrayList<String>();

    public String getFileNameToSearch() {
        return fileNameToSearch;
    }
    public List<String> getResult() {
        return result;
    }
    public void setFileNameToSearch(String fileNameToSearch) {
        this.fileNameToSearch = fileNameToSearch;
    }

    public void searchDirectory(File directory, String fileNameToSearch) {

        setFileNameToSearch(fileNameToSearch);

        if (directory.isDirectory()) {
            search(directory);
        } else {
            log.log(Level.SEVERE, directory.getAbsoluteFile() + " is not a directory!");
        }
    }

    private void search(File file) {

        if (file.isDirectory()) {
            //System.out.println("Searching directory ... " + file.getAbsoluteFile());

            //do you have permission to read this directory?
            if (file.canRead()) {
                for (File temp : file.listFiles()) {
                    if (temp.isDirectory()) {
                        search(temp);
                    } else {

                        if (temp.getName() != ""){
                            String lcName = temp.getName().toLowerCase();
                            String[] tempExt = lcName.split("\\.");

                            if (tempExt.length == 2){

                                if (getFileNameToSearch().equals(tempExt[1])){
                                    result.add(temp.getAbsoluteFile().toString());
                                }
                                /*if (getFileNameToSearch().equals(temp.getName().toLowerCase())) {
                                    result.add(temp.getAbsoluteFile().toString());
                                }*/
                            }

                        }
                    }
                }

            } else {
                log.log(Level.WARNING, file.getAbsoluteFile() + "Permission Denied");
            }
        }

    }

}