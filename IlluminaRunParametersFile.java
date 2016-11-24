package nhs.genetics.cardiff.framework;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A program for reporting Illumina sequencing audits
 *
 * @author  Matt Lyon
 * @version 1.0
 * @since   2015-01-27
 */

public class IlluminaRunParametersFile {

    private static final Logger log = Logger.getLogger(IlluminaRunParametersFile.class.getName());

    private File runParametersFile, analysisFolder, localRootRunFolder, fastqFolder;
    private String flowcellSerialNo,flowcellPartNo, pr2SerialNo, pr2PartNo, reagentSerialNo, reagentPartNo, applicationVersion,
            applicationName, runIdentifier, FPGAVersion, RTAVersion, reagentKitBarcode, scannerID;
    private Date flowcellExpireDate, pr2ExpireDate, reagentExpireDate, runStartDate;

    private SimpleDateFormat basicFormat = new SimpleDateFormat("yyMMdd");
    private SimpleDateFormat expiryFormat = new SimpleDateFormat("yyyy-MM-dd");

    public IlluminaRunParametersFile(File runParametersFile){
        this.runParametersFile = runParametersFile;
    }

    private static String searchXmlFile(String nodeName, String nodeKeyName, File runParametersFile) {

        Element fstElmnt, fstNmElmnt;
        NodeList fstNmElmntLst, fstNm;

        try {

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(runParametersFile);
            doc.getDocumentElement().normalize();

            //get flowcell arguments
            NodeList nodeLst = doc.getElementsByTagName(nodeName);
            for (int s = 0; s < nodeLst.getLength(); s++) {
                Node fstNode = nodeLst.item(s);

                if (fstNode.getNodeType() == Node.ELEMENT_NODE) {

                    fstElmnt = (Element) fstNode;
                    fstNmElmntLst = fstElmnt.getElementsByTagName(nodeKeyName);
                    fstNmElmnt = (Element) fstNmElmntLst.item(0);
                    fstNm = fstNmElmnt.getChildNodes();

                    return ((Node) fstNm.item(0)).getNodeValue();
                }

            }

        } catch (Exception e) {
            log.log(Level.SEVERE, "Could not parse RunParameters.xml: " + e.getMessage());
            throw new RuntimeException();
        }

        return "";
    }

    public void parseRunParametersXml(){

        String[] split;

        split = searchXmlFile("FlowcellRFIDTag", "SerialNumber", runParametersFile).split("-");
        flowcellSerialNo = split[1];
        flowcellPartNo = searchXmlFile("FlowcellRFIDTag", "PartNumber", runParametersFile);
        split = searchXmlFile("FlowcellRFIDTag", "ExpirationDate", runParametersFile).split("T");

        try {
            flowcellExpireDate = expiryFormat.parse(split[0]);
        } catch (Exception e){
            log.log(Level.SEVERE, "Could not convert data string: " + e.getMessage());
        }

        pr2SerialNo = searchXmlFile("PR2BottleRFIDTag", "SerialNumber", runParametersFile);
        pr2PartNo = searchXmlFile("PR2BottleRFIDTag", "PartNumber", runParametersFile);
        split = searchXmlFile("PR2BottleRFIDTag", "ExpirationDate", runParametersFile).split("T");

        try {
            pr2ExpireDate = expiryFormat.parse(split[0]);
        } catch (Exception e){
            log.log(Level.SEVERE, "Could not convert data string: " + e.getMessage());
        }

        reagentSerialNo = searchXmlFile("ReagentKitRFIDTag", "SerialNumber", runParametersFile);
        reagentPartNo = searchXmlFile("ReagentKitRFIDTag", "PartNumber", runParametersFile);
        split = searchXmlFile("ReagentKitRFIDTag", "ExpirationDate", runParametersFile).split("T");

        try {
            reagentExpireDate = expiryFormat.parse(split[0]);
        } catch (Exception e){
            log.log(Level.SEVERE, "Could not convert data string: " + e.getMessage());
        }

        applicationVersion = searchXmlFile("Setup", "ApplicationVersion", runParametersFile);
        applicationName = searchXmlFile("Setup", "ApplicationName", runParametersFile);

        runIdentifier = searchXmlFile("RunParameters", "RunID", runParametersFile);

        FPGAVersion = searchXmlFile("RunParameters", "FPGAVersion", runParametersFile);
        RTAVersion = searchXmlFile("RunParameters", "RTAVersion", runParametersFile);

        reagentKitBarcode = searchXmlFile("RunParameters", "ReagentKitBarcode", runParametersFile);
        analysisFolder = new File (searchXmlFile("RunParameters", "AnalysisFolder", runParametersFile));

        try {
            runStartDate = basicFormat.parse(searchXmlFile("RunParameters", "RunStartDate", runParametersFile));
        } catch (Exception e){
            log.log(Level.WARNING, "Could not convert data string: " + e.getMessage());
        }

        localRootRunFolder = new File (searchXmlFile("RunParameters", "OutputFolder", runParametersFile));

        scannerID = searchXmlFile("RunParameters", "ScannerID", runParametersFile);

        fastqFolder = new File (analysisFolder + "\\Data\\Intensities\\BaseCalls");
    }

    public String getFlowcellSerialNo() {
        return flowcellSerialNo;
    }
    public String getFlowcellPartNo() {
        return flowcellPartNo;
    }
    public String getPr2SerialNo() {
        return pr2SerialNo;
    }
    public String getPr2PartNo() {
        return pr2PartNo;
    }
    public String getReagentSerialNo() {
        return reagentSerialNo;
    }
    public String getReagentPartNo() {
        return reagentPartNo;
    }
    public String getApplicationVersion() {
        return applicationVersion;
    }
    public String getApplicationName() {
        return applicationName;
    }
    public String getRunIdentifier() {
        return runIdentifier;
    }
    public String getFPGAVersion() {
        return FPGAVersion;
    }
    public String getRTAVersion() {
        return RTAVersion;
    }
    public String getReagentKitBarcode() {
        return reagentKitBarcode;
    }
    public String getScannerID() {
        return scannerID;
    }
    public File getAnalysisFolder() {
        return analysisFolder;
    }
    public File getLocalRootRunFolder() {
        return localRootRunFolder;
    }
    public File getFastqFolder() {
        return fastqFolder;
    }
    public Date getFlowcellExpireDate() {
        return flowcellExpireDate;
    }
    public Date getPr2ExpireDate() {
        return pr2ExpireDate;
    }
    public Date getReagentExpireDate() {
        return reagentExpireDate;
    }
    public Date getRunStartDate() {
        return runStartDate;
    }

}
