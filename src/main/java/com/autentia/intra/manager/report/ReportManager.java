package com.autentia.intra.manager.report;

import com.autentia.intra.bean.reports.ReportParameterDefinition;
import com.autentia.intra.util.ConfigurationUtil;
import com.autentia.intra.util.DateUtils;
import com.autentia.intra.xml.ParameterReport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xerces.parsers.SAXParser;
import org.xml.sax.*;

import javax.faces.model.SelectItem;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author cris
 */
public class ReportManager implements ContentHandler {

    private static final Log log = LogFactory.getLog(ReportManager.class);
    /* type of reports */
    private List reportListGeneral = null;
    private List reportListActivity = null;
    private List reportListBill = null;
    private List reportListProject = null;
    private List reportListOrganization = null;
    private List reportListPersonal = null;
    private List reportListOffer = null;
    private List reportListInteraction = null;
    private static List filesList = null;
    private static ReportManager instancia = null;

    private List<ParameterReport> param = null;
    private List reportList = null;
    private String tempVal;
    private ParameterReport tempParam;
    private long parsingStart;


    private ReportManager() {
        log.info("ReportManager - parsing reports");
        reportListGeneral = parserFolderReport(true, "com/autentia/intra/report/general");
        reportListActivity = parserFolderReport(true, "com/autentia/intra/report/activity");
        reportListBill = parserFolderReport(true, "com/autentia/intra/report/bill");
        reportListProject = parserFolderReport(true, "com/autentia/intra/report/project");
        reportListInteraction = parserFolderReport(true, "com/autentia/intra/report/interaction");
        reportListOrganization = parserFolderReport(true, "com/autentia/intra/report/organization");
        reportListOffer = parserFolderReport(true, "com/autentia/intra/report/offer");
        reportListPersonal = parserFolderReport(false, ConfigurationUtil.getDefault().getReportPath());
    }

    private static synchronized void init() {
        if (instancia == null) {
            instancia = new ReportManager();
        }
    }


    public static ReportManager getReportManager() {
        init();
        return instancia;
    }


    public List parserFolderReport(Boolean typeFile, String folder) {
        reportList = null;
        reportList = new ArrayList<List>();
        filesList = null;
        filesList = filesFromFolder(typeFile, folder);
        Collections.sort(filesList);

        for (int i = 0; i < filesList.size(); i++) {
            param = new ArrayList<ParameterReport>();
            List tmp = new ArrayList<List>();
            parseDocument(typeFile, filesList.get(i).toString());
            tmp.add(normalize(filesList.get(i).toString()));
            tmp.add(param);
            reportList.add(tmp);
        }
        return reportList;
    }

    private void parseDocument(Boolean typeFile, String reportName) {
        final InputStream jasperreportDtd = ReportManager.class.getClassLoader().getResourceAsStream(
                "net/sf/jasperreports/engine/dtds/jasperreport.dtd");
        InputStream xmlSource = null;

        parsingStart = System.currentTimeMillis();
        log.debug("parseDocument - [start] " + reportName);
        try {
            SAXParser sp = new SAXParser();
            log.debug("parseDocument -   newSAXParser=" + (System.currentTimeMillis() - parsingStart) + " ms.");

            ClassLoader loader = (ReportManager.class).getClassLoader();
            File f = null;
            try {
                if (typeFile == true)
                    f = new File(loader.getResource(reportName).toURI());
                else f = new File(reportName);
            }
            catch (URISyntaxException e) {
                e.printStackTrace();
            }
            log.debug("parseDocument -   getResource=" + (System.currentTimeMillis() - parsingStart) + " ms.");

            xmlSource = new FileInputStream(f);


            sp.setContentHandler(this);
            sp.setEntityResolver(new EntityResolver() {
                public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
                    if (publicId.equals("//JasperReports//DTD Report Design//EN") ||
                            systemId.equals("http://jasperreports.sourceforge.net/dtds/jasperreport.dtd")) {
                        return new InputSource(jasperreportDtd);
                    } else {
                        String msg = "DTD (" + publicId + " " + systemId + ") cannot be resolved by ReportManager: " +
                                "please change TNTConcept to add the new DTD or change your JasperReport's JRXML file " +
                                "to use the standard DTD";
                        log.error("parseDocument - " + msg);
                        throw new IllegalArgumentException(msg);
                    }
                }
            });

            sp.parse(new InputSource(xmlSource));
        }
        catch (FinalizeParsingException fpe) {
            //ignore this exception as it is thrown as an optimization
        }
        catch (SAXException se) {
            log.error("parseDocument - exception", se);
        }
        catch (IOException ie) {
            log.error("parseDocument - exception", ie);
        }

        finally {
            if (xmlSource != null) {
                try {
                    xmlSource.close();
                }
                catch (IOException e) {
                    // ignored
                }
            }
            try {
                jasperreportDtd.close();
            }
            catch (IOException e) {
                // ignored
            }
            log.info("parseDocument - " + reportName + " (" + (System.currentTimeMillis() - parsingStart) + " ms.)");
        }
    }

    public static List<String> filesFromFolder(Boolean typeFile, String path) {
        File[] filesList = null;
        List<String> list = new ArrayList<String>();
        ClassLoader loader = (ReportManager.class).getClassLoader();
        File f = null;
        try {
            if (typeFile == true)
                f = new File(loader.getResource(path).toURI());
            else f = new File(path);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (f.isDirectory()) {
            filesList = f.listFiles();
            for (File file : filesList) {
                int i = file.getAbsolutePath().lastIndexOf(".");
                String format = file.getAbsolutePath().substring(i + 1);
                if (file.isFile() && (format.equals("jrxml"))) {
                    list.add(path + "/" + file.getName());
                }
            }
        }
        return list;
    }

    public static String normalize(String path) {
        String pathCleaned = "";

        if (path.lastIndexOf("/") >= 0) {
            pathCleaned = path.substring(path.lastIndexOf("/") + 1);
        } else {
            pathCleaned = path;
        }
        pathCleaned = pathCleaned.replaceFirst(".jrxml", "");
        return pathCleaned;
    }

    public List<List> getReportListActivity() {
        return reportListActivity;
    }

    public void setReportListActivity(List<List> reportListActivity) {
        this.reportListActivity = reportListActivity;
    }

    public List<List> getReportListBill() {
        return reportListBill;
    }

    public void setReportListBill(List<List> reportListBill) {
        this.reportListBill = reportListBill;
    }

    public List<List> getReportListGeneral() {
        return reportListGeneral;
    }

    public void setReportListGeneral(List<List> reportListGeneral) {
        this.reportListGeneral = reportListGeneral;
    }

    public List<List> getReportListInteraction() {
        return reportListInteraction;
    }

    public void setReportListInteraction(List<List> reportListInteraction) {
        this.reportListInteraction = reportListInteraction;
    }

    public List<List> getReportListOrganization() {
        return reportListOrganization;
    }

    public List<List> getReportListPersonal() {
        return reportListPersonal;
    }

    public List<List> getReportListOffer() {
        return reportListOffer;
    }

    public void setReportListOrganization(List<List> reportListOrganization) {
        this.reportListOrganization = reportListOrganization;
    }

    public void setReportListPersonal(List<List> reportListPersonal) {
        this.reportListPersonal = reportListPersonal;
    }

    public void setReportListOffer(List<List> reportListOffer) {
        this.reportListOffer = reportListOffer;
    }

    public List<List> getReportListProject() {
        return reportListProject;
    }

    public void setReportListProject(List<List> reportListProject) {
        this.reportListProject = reportListProject;
    }

    // ContentHandler
    public void startElement(String uri, String localName, String tagName, Attributes attributes) throws SAXException {
        log.debug("parseDocument -   startElement(" + tagName + ")=" + (System.currentTimeMillis() - parsingStart) + " ms.");
        tempVal = "";
        if (tagName.equals("parameter")) {
            tempParam = new ParameterReport();
            tempParam.setName(attributes.getValue("name"));
        } else if ((tagName.equals("queryString")) || (tagName.equals("background"))) {
            throw new FinalizeParsingException("Finalize parsing jrxml");
        }
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        tempVal = new String(ch, start, length);
    }

    public void endElement(String uri, String localName, String tagName) throws SAXException {
        log.debug("parseDocument -   endElement(" + tagName + ")=" + (System.currentTimeMillis() - parsingStart) + " ms.");
        if (tagName.equals("parameter")) {
            param.add(tempParam);
        } else if (tagName.equals("parameterDescription")) {
            tempParam.setDescription(tempVal);
        } else if (tagName.equals("defaultValueExpression")) {
            tempParam.setDefaultValue(tempVal);
        }
    }

    public void setDocumentLocator(Locator locator) {
    }

    public void startDocument() throws SAXException {
    }

    public void endDocument() throws SAXException {
    }

    public void startPrefixMapping(String prefix, String uri) throws SAXException {
    }

    public void endPrefixMapping(String prefix) throws SAXException {
    }

    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
    }

    public void processingInstruction(String target, String data) throws SAXException {
    }

    public void skippedEntity(String name) throws SAXException {
    }


    public static void createRunParameters(ArrayList<ReportParameterDefinition> reportParametersDefinitions, StringBuffer parameters, StringBuffer selectMany) {
        parameters.append("?");
        for (ReportParameterDefinition definition : reportParametersDefinitions) {
            if (definition.getType().equals("text"))
                parameters.append(definition.getId() + "=" + definition.getValue() + "&");
            else if (definition.getType().equals("date")) {
                parameters.append(definition.getId() + "=" + definition.formatDate() + "&");
            } else if (definition.getType().equals("timestamp")) {
                if ("Fecha fin".equalsIgnoreCase(definition.getId())) {
                    definition.setDateValue(DateUtils.maxHourInDate(definition.getDateValue()));
                } else {
                    definition.setDateValue(DateUtils.minHourInDate(definition.getDateValue()));
                }
                parameters.append(definition.getId() + "=" + definition.formatTimestamp() + "&");
            } else if (definition.getType().equals("selectOne"))
                parameters.append(definition.getId() + "=" + definition.getValue() + "&");
            else if (definition.getType().equals("hidden"))
                parameters.append(definition.getId() + "=" + definition.getValue() + "&");
            else if (definition.getType().equals("selectMany") || definition.getType().equals("selectOne-selectMany"))
                selectMany.append(definition.getId() + "=" + definition.getValueMany() + "&");
        }

    }

    public static void createReportParameterDefinition(List<ParameterReport> parameters, ArrayList<ReportParameterDefinition> reportParametersDefinitions,
                                                       ArrayList<SelectItem> users, ArrayList<SelectItem> orgs, ArrayList<SelectItem> projs, ArrayList<SelectItem> deps) {

        for (int j = 0; j < parameters.size(); j++) {
            String paraName = "";
            String paraDescription = "";
            String paraDefault = "";
            paraName = parameters.get(j).getName();
            paraDescription = parameters.get(j).getDescription();
            paraDefault = parameters.get(j).getDefaultValue();
            if (paraDescription.toUpperCase().equals("DATE")) {
                reportParametersDefinitions.add(new ReportParameterDefinition(paraName, "date", paraName, new Date()));
            } else if (paraDescription.toUpperCase().equals("USER")) {
                reportParametersDefinitions.add(new ReportParameterDefinition(paraName, "selectOne", paraName, users));
            } else if (paraDescription.toUpperCase().equals("ORGANIZATION")) {
                reportParametersDefinitions.add(new ReportParameterDefinition(paraName, "selectMany", paraName, orgs));
            } else if (paraDescription.toUpperCase().equals("DEPARTMENT")) {
                reportParametersDefinitions.add(new ReportParameterDefinition(paraName, "selectOne", paraName, deps, false));
            } else if (paraDescription.toUpperCase().equals("PROJECT")) {
                reportParametersDefinitions.add(new ReportParameterDefinition(paraName, "selectOne-selectMany", paraName, orgs, projs));
            } else if (paraDescription.toUpperCase().equals("SUBREPORT")) {
                reportParametersDefinitions.add(new ReportParameterDefinition(paraName, "hidden", paraName, paraDefault.replace('"', ' ').trim()));
            } else if (paraDescription.toUpperCase().equals("DESCRIPTION")) {
                reportParametersDefinitions.add(new ReportParameterDefinition(paraName, "info", paraName, paraDefault.replace('"', ' ')));
            } else if (paraDescription.toUpperCase().equals("TIMESTAMP")) {
                reportParametersDefinitions.add(new ReportParameterDefinition(paraName, "timestamp", paraName, new Date()));
            }
        }


    }

}

