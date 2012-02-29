/* 
 *  TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L.  
 *	Copyright (C) 2007 Autentia Real Bussiness Solution S.L.					   
 *	
 * 	This program is free software; you can redistribute it and/or
 * 	modify it under the terms of the GNU General Public License
 * 	as published by the Free Software Foundation; either version 2
 * 	of the License, or (at your option) any later version.
 *
 * 	This program is distributed in the hope that it will be useful,
 * 	but WITHOUT ANY WARRANTY; without even the implied warranty of
 * 	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * 	GNU General Public License for more details.
 *
 * 	You should have received a copy of the GNU General Public License
 * 	along with this program; if not, write to the Free Software
 * 	Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 * 	Autentia Real Bussiness Solution S.L.
 * 	Tlf: +34 91 675 33 06, +34 655 99 11 72
 * 	Fax: +34 91 656 65 04
 * 	info@autentia.com																
 */

/**
 *
 */
package com.autentia.intra.servlet;

import com.autentia.intra.util.ConfigurationUtil;
import com.autentia.intra.util.HibernateUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.*;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ivan
 */
public class ReportServlet extends HttpServlet {

    /** */
    private static final long serialVersionUID = -5605017166013496429L;

    private static final Log log = LogFactory.getLog(ReportServlet.class);

    public static final String URL_PREFIX = "/report/";

    public static ArrayList prueba = new ArrayList();

    public static final String REPORT_PREFIX = "com/autentia/intra/report/";

    public static final String SUBREPORT_PREFIX = "subreports/";

    public static final String REPORT_SUFFIX = ".jasper";

    public static final String REPORT_DEFINITION_SUFFIX = ".jrxml";

    private enum Format {
        CSV, PDF, HTML, RTF, XLS, ODT
    }

    private URI getResourceAsURI(String qualifiedName) {
        try {
            return getResourceAsURL(qualifiedName).toURI();
        } catch (URISyntaxException e) {
            log.error("qualifiedName es " + qualifiedName);
            log.error(e);
            return null;
        } catch (NullPointerException npe) {
            log.error("qualifiedName es " + qualifiedName);
            log.error(npe);
            return null;
        }
    }

    private URL getResourceAsURL(String qualifiedName) {
        return getClass().getClassLoader().getResource(qualifiedName);
    }

    private String getResourceAsString(String qualifiedName) {
        return getResourceAsURI(qualifiedName).getPath();
    }

    private void debug(String s) {
        if (log.isDebugEnabled()) {
            log.debug(s);
        }

    }

    private void caseCSV(HttpServletResponse response, JasperReport report,
                         Map args, Connection con) throws Exception {
        debug("doGet - tipo csv");
        JasperPrint print = JasperFillManager.fillReport(report, args, con);
        JRCsvExporter exporter = new JRCsvExporter();
        exporter.setParameter(JRCsvExporterParameter.JASPER_PRINT, print);
        exporter.setParameter(JRCsvExporterParameter.OUTPUT_STREAM, response
                .getOutputStream());
        exporter.setParameter(JRCsvExporterParameter.FIELD_DELIMITER,
                ConfigurationUtil.getDefault().getReportCSVDelimiter());
        exporter.exportReport();

        response.setContentType("application/vnd.ms-excel");
    }

    private void caseHTML(HttpServletResponse response, JasperReport report,
                          Map args, Connection con) throws Exception {
        debug("doGet - tipo html");
        JasperPrint print = JasperFillManager.fillReport(report, args, con);
        JRHtmlExporter exporter = new JRHtmlExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, response
                .getOutputStream());
        exporter.exportReport();

    }

    private void caseRTF(HttpServletResponse response, JasperReport report,
                         Map args, Connection con) throws Exception {
        debug("doGet - tipo csv");
        JasperPrint print = JasperFillManager.fillReport(report, args, con);
        JRRtfExporter exporter = new JRRtfExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, response
                .getOutputStream());

        exporter.exportReport();

        response.setContentType("application/rtf");

    }

    private void caseXLS(HttpServletResponse response, JasperReport report,
                         Map args, Connection con) throws Exception {
        debug("doGet - tipo xls");
        JasperPrint print = JasperFillManager.fillReport(report, args, con);
        JRXlsExporter exporter = new JRXlsExporter();

        exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, print);
        exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
                Boolean.TRUE);
        exporter.setParameter(
                JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
                Boolean.TRUE);
        exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, response
                .getOutputStream());
        exporter.exportReport();
        response.setContentType("application/vnd.ms-excel");
    }

    private void casePDF(HttpServletResponse response, JasperReport report,
                         Map args, Connection con) throws Exception {
        debug("doGet - formato pdf");
        JasperPrint print = JasperFillManager.fillReport(report, args, con);
        JRPdfExporter exporter = new JRPdfExporter();
        //mejor que en jasperreports.properties porque así, sí sale más de una pagina pdf.
        //http://jasperforge.org/plugins/espforum/view.php?group_id=102&forumid=103&topicid=34919&page=2#35433
        Map fontMap = new HashMap();
        fontMap.put(new FontKey("Arial", false, false), new PdfFont("arial.ttf", "Identity-H", true));
        fontMap.put(new FontKey("Arial", false, true), new PdfFont("ariali.ttf", "Identity-H", true));
        fontMap.put(new FontKey("Arial", true, false), new PdfFont("arialbd.ttf", "Identity-H", true));
        fontMap.put(new FontKey("Arial", true, true), new PdfFont("arialbi.ttf", "Identity-H", true));
        exporter.setParameter(JRExporterParameter.FONT_MAP, fontMap);
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
        exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, response
                .getOutputStream());
        exporter.exportReport();
        response.setContentType("application/pdf");

    }

    private void caseODT(HttpServletResponse response, JasperReport report,
                         Map args, Connection con) throws Exception {
        debug("doGet - tipo odt");
        JasperPrint print = JasperFillManager.fillReport(report, args, con);
        JROdtExporter exporter = new JROdtExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, response
                .getOutputStream());
        exporter.exportReport();
        response.setContentType("application/odt");
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private Map getParametersAsMapAndSubReport(HttpServletRequest request,
                                               String reportCategory, JasperReport subReport) throws Exception {
        Map args = new HashMap();
        Enumeration e = request.getParameterNames();
        while (e.hasMoreElements()) {
            String arg = (String) e.nextElement();
            final String value = request.getParameter(arg);
            Object obj = null;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date value_date = sdf.parse(value);
                java.sql.Timestamp ts = new Timestamp(value_date.getTime());

                obj = ts;
            } catch (Exception ex) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date value_date = sdf.parse(value);
                    obj = value_date;
                } catch (Exception ex2) {
                    try {
                        Integer value_int = Integer.parseInt(value);
                        obj = value_int;
                    } catch (Exception ex3) {
                        obj = value;
                        if (value != null && "".equals(value)) {
                            obj = null;
                        }
                    }
                }
            }

            if (arg.startsWith("SUBREPORT")) {
                if (reportCategory.equals("personal/")) {
                    String path = ConfigurationUtil.getDefault()
                            .getReportPath();
                    String target = path + SUBREPORT_PREFIX + obj;
                    if (null == getClass().getClassLoader().getResource(
                            target + REPORT_DEFINITION_SUFFIX)) {
                        JasperCompileManager.compileReportToFile(target
                                + REPORT_DEFINITION_SUFFIX);
                    }
                    subReport = (JasperReport) JRLoader.loadObject(target
                            + REPORT_SUFFIX);
                    obj = target + REPORT_SUFFIX;
                } else {
                    String destino = REPORT_PREFIX + reportCategory
                            + SUBREPORT_PREFIX + obj + REPORT_SUFFIX;
                    if (null == getResourceAsURL(destino)) {
                        JasperCompileManager.compileReportToFile(
                                getResourceAsString(REPORT_PREFIX + reportCategory
                                        + SUBREPORT_PREFIX + obj
                                        + REPORT_DEFINITION_SUFFIX));
                    }

                    subReport = (JasperReport) JRLoader.loadObject(getResourceAsURL(destino));
                    obj = destino;
                }
            }
            args.put(arg, obj);
        }

        return args;
    }

    public static String subreport(String reportCategory, String obj) {
        String destino = REPORT_PREFIX + reportCategory
                + SUBREPORT_PREFIX + obj + REPORT_SUFFIX;
        ReportServlet x = new ReportServlet();
        if (null == x.getResourceAsURL(destino)) {
            try {
                JasperCompileManager.compileReportToFile(
                        x.getResourceAsString(REPORT_PREFIX + reportCategory
                                + SUBREPORT_PREFIX + obj
                                + REPORT_DEFINITION_SUFFIX));
            } catch (JRException e) {
                e.printStackTrace();
            }
        }

        return destino;
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        debug("doGet - inicio");
        String uri = request.getRequestURI();
        int i = uri.indexOf(URL_PREFIX);

        if (i == -1) {
            debug("doGet - SC_INTERNAL_SERVER_ERROR");
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Bad URL prefix for servlet: check your web.xml file");
            return;
        }

        String reportName = uri.substring(i + URL_PREFIX.length());
        int j = reportName.lastIndexOf(".");
        int k = reportName.lastIndexOf("/");

        if (!((j != -1) || (k != -1))) {
            debug("doGet - SC_INTERNAL_SERVER_ERROR");
            response
                    .sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                            "Report format not specified: append extension to report name");
            return;
        }

        JasperReport report = null;
        JasperReport subReport = null;

        try {

            String ext = reportName.substring(j + 1).toUpperCase();
            String reportCategory = reportName.substring(0, k + 1);
            reportName = reportName.substring(0, j);

            debug("doGet - report name solicitado: " + reportName);
            Format format = Format.valueOf(ext);

            report = initReport(reportName, reportCategory, i, j, k);

            debug("doGet - report: " + report);
            debug("doGet - iniciando lista de parametros");

            if (report == null) {
                debug("doGet - SC_NOT_FOUND");
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }


            Map args = getParametersAsMapAndSubReport(request, reportCategory, subReport);

            debug("doGet - argc " + args.size());
            debug("doGet - iniciando conexion and BD");

            Session session = HibernateUtil.currentSession();
            Connection con = session.connection();

            debug("doGet - seleccion de formato");

            switch (format) {
                case CSV: {
                    caseCSV(response, report, args, con);
                    break;
                }
                case HTML: {
                    caseHTML(response, report, args, con);
                    break;
                }
                case RTF: {
                    caseRTF(response, report, args, con);
                    break;
                }
                case XLS: {
                    caseXLS(response, report, args, con);
                    break;
                }
                case ODT: {
                    caseODT(response, report, args, con);
                    break;
                }
                case PDF: {
                    casePDF(response, report, args, con);
                    break;
                }
            }

            debug("doGet - finalización del informe");

        } catch (Exception e) {
            log.error("doGet - exception", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e
                    .getMessage());
        }

    }

    private JasperReport initReport(String reportName, String reportCategory, int i, int j, int k) throws Exception {

        JasperReport report = null;

        debug("doGet - report file: " + REPORT_PREFIX + reportName
                + REPORT_SUFFIX);
        debug("doGet - report jrxml url: "
                + getClass().getClassLoader().getResource(
                REPORT_PREFIX + reportName
                        + REPORT_DEFINITION_SUFFIX));
        debug("doGet - comprobación de fichero .jasper");

        if (reportCategory.equals("personal/")) {
            reportName = reportName.substring(k + 1, j);
            String path = ConfigurationUtil.getDefault().getReportPath()
                    + reportName;
            JasperCompileManager.compileReportToFile(path
                    + REPORT_DEFINITION_SUFFIX);
            report = (JasperReport) JRLoader.loadObject(path
                    + REPORT_SUFFIX);
        } else {
            if (null == getResourceAsURL(REPORT_PREFIX + reportName
                    + REPORT_SUFFIX)) {
                JasperCompileManager
                        .compileReportToFile(getResourceAsString(REPORT_PREFIX
                                + reportName + REPORT_DEFINITION_SUFFIX));
                debug("doGet - intentando compilar el informe "
                        + getClass().getClassLoader().getResource(
                        REPORT_PREFIX + reportName
                                + REPORT_DEFINITION_SUFFIX)
                        .getFile());
            }
            report = (JasperReport) JRLoader
                    .loadObject(getResourceAsURL(REPORT_PREFIX + reportName
                            + REPORT_SUFFIX));
        }
        return report;

    }

}
