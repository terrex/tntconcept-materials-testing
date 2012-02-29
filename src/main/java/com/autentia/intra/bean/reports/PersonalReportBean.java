/**
 *
 */
package com.autentia.intra.bean.reports;

import com.autentia.intra.bean.BaseBean;
import com.autentia.intra.businessobject.Department;
import com.autentia.intra.businessobject.Organization;
import com.autentia.intra.businessobject.User;
import com.autentia.intra.dao.SortCriteria;
import com.autentia.intra.dao.hibernate.DepartmentDAO;
import com.autentia.intra.dao.hibernate.OrganizationDAO;
import com.autentia.intra.dao.hibernate.UserDAO;
import com.autentia.intra.dao.search.BillSearch;
import com.autentia.intra.manager.report.ReportManager;
import com.autentia.intra.util.ConfigurationUtil;
import com.autentia.intra.util.FacesUtils;
import com.autentia.intra.xml.ParameterReport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * @author cris
 */

public class PersonalReportBean extends BaseBean {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static final Log log = LogFactory.getLog(PersonalReportBean.class);

    private ArrayList<SelectItem> reports;

    private ArrayList<ReportParameterDefinition> reportParametersDefinitions;

    private static final OrganizationDAO organizationDAO = new OrganizationDAO();

    private static final UserDAO userDAO = new UserDAO();

    private static final DepartmentDAO departmentDAO = DepartmentDAO.getDefault();

    private BillSearch search = new BillSearch();

    private String selectedReport = null;

    private String format;

    private Organization org;

    private StringBuffer parameters;

    private StringBuffer selectMany;

    private boolean launch = false;

    public void run() {
        parameters = new StringBuffer();
        selectMany = new StringBuffer();
        ReportManager.createRunParameters(reportParametersDefinitions, parameters, selectMany);
        setLaunch(true);
        return;
    }

    public boolean isLaunch() {
        return launch;
    }

    public void setLaunch(boolean launch) {
        this.launch = launch;
    }

    public boolean getLaunch() {
        return launch;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Organization getOrg() {
        return org;
    }

    public void setOrg(Organization org) {
        this.org = org;
    }

    public void selectedReportChanged(ValueChangeEvent event) {
        this.setSelectedReport((String) event.getNewValue());
        FacesUtils.renderResponse();
    }

    public String getSelectedReport() {
        return selectedReport;
    }

    public void setSelectedReport(String selectedReport) {
        parameters = new StringBuffer();
        parameters.append("?");

        if (selectedReport.equals(this.selectedReport)) {
            return;
        }
        launch = false;
        this.selectedReport = selectedReport;
        reportParametersDefinitions = new ArrayList<ReportParameterDefinition>();

        int numReports = 0;
        numReports = ReportManager.getReportManager().getReportListPersonal().size();
        for (int i = 0; i < numReports; i++) {
            List reportData = new ArrayList<List>();
            String reportName = "";
            reportData = ReportManager.getReportManager().getReportListPersonal().get(i);
            reportName = reportData.get(0).toString();
            if (reportName.equals(selectedReport)) {
                List<ParameterReport> parameters = new ArrayList<ParameterReport>();
                parameters = (List<ParameterReport>) reportData.get(1);
                ReportManager.createReportParameterDefinition(parameters, reportParametersDefinitions, getUsers(), getOrgs(), null, getDepartments());
                reportParametersDefinitions.add(new ReportParameterDefinition("idOrg", "hidden", "idOrg", ConfigurationUtil.getDefault().getIdOurCompany()));
            }
        }
        this.selectedReport = selectedReport.replace(' ', '.');
        selectedReport = this.selectedReport;

    }

    public ArrayList<SelectItem> getReports() {
        setLaunch(false);
        if (reports == null) {
            ArrayList<SelectItem> m = new ArrayList<SelectItem>();
            m.add(new SelectItem("", "- Elegir informe -"));
            int numReports = 0;
            numReports = ReportManager.getReportManager().getReportListPersonal().size();
            for (int i = 0; i < numReports; i++) {
                List reportData = new ArrayList();
                String name = "";
                String nameFile = "";
                reportData = ReportManager.getReportManager().getReportListPersonal().get(i);
                nameFile = reportData.get(0).toString();
                name = nameFile.replace('.', ' ');
                m.add(new SelectItem(nameFile, name));
            }

            reports = m;
        }

        return reports;
    }

    public ArrayList<SelectItem> getFormats() {
        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        ret.add(new SelectItem("pdf", "PDF"));
        ret.add(new SelectItem("csv", "CSV"));
        ret.add(new SelectItem("html", "HTML"));
        ret.add(new SelectItem("rtf", "RTF"));
        ret.add(new SelectItem("xls", "XLS"));
        ret.add(new SelectItem("odt", "ODT"));
        return ret;
    }

    public ArrayList<SelectItem> getOrgs() {
        ArrayList<SelectItem> reto = new ArrayList<SelectItem>();
        List<Organization> refs = organizationDAO.search(search, new SortCriteria("name"));
        for (Organization ref : refs) {
            reto.add(new SelectItem(ref.getId().toString(), ref.getName()));
        }
        return reto;
    }

    public ArrayList<SelectItem> getUsers() {
        ArrayList<SelectItem> reto = new ArrayList<SelectItem>();
        List<User> refs = userDAO.search(search, new SortCriteria("name"));
        for (User ref : refs) {
            reto.add(new SelectItem(ref.getId().toString(), ref.getName()));
        }
        return reto;
    }

    public ArrayList<SelectItem> getDepartments() {
        ArrayList<SelectItem> reto = new ArrayList<SelectItem>();
        List<Department> refs = departmentDAO.search(search, new SortCriteria("name"));

        for (Department ref : refs) {
            reto.add(new SelectItem(ref.getId().toString(), ref.getRuta()));
        }

        Collections.sort(reto, new Comparator<SelectItem>() {
            public int compare(SelectItem o1, SelectItem o2) {
                Department este = departmentDAO.getById(Integer.valueOf((String) o1.getValue()));
                Department otro = departmentDAO.getById(Integer.valueOf((String) o2.getValue()));
                return este.getRuta().compareTo(otro.getRuta());
            }
        });
        reto.add(0, new SelectItem(""));

        return reto;
    }

    public ArrayList<ReportParameterDefinition> getReportParametersDefinitions() {
        return reportParametersDefinitions;

    }

    public StringBuffer getParameters() {
        return parameters;
    }

    public StringBuffer getSelectMany() {
        return selectMany;
    }

    public void setParameters(StringBuffer parameters) {
        if (parameters.equals("?")) {
            this.parameters = parameters;
        } else
            return;

    }

}
