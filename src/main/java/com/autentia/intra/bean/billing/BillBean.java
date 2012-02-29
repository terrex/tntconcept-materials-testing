/* 
 * TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L.  
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
package com.autentia.intra.bean.billing;

import com.autentia.intra.bean.BaseBean;
import com.autentia.intra.bean.NavigationResults;
import com.autentia.intra.bean.account.AccountEntryBean;
import com.autentia.intra.businessobject.*;
import com.autentia.intra.dao.SortCriteria;
import com.autentia.intra.dao.hibernate.AlbaranDAO;
import com.autentia.intra.dao.hibernate.BillDAO;
import com.autentia.intra.dao.hibernate.OfferDAO;
import com.autentia.intra.dao.search.AlbaranSearch;
import com.autentia.intra.dao.search.BillSearch;
import com.autentia.intra.dao.search.ContactSearch;
import com.autentia.intra.dao.search.OfferSearch;
import com.autentia.intra.manager.account.AccountEntryManager;
import com.autentia.intra.manager.account.AccountManager;
import com.autentia.intra.manager.admin.DepartmentManager;
import com.autentia.intra.manager.admin.ProjectManager;
import com.autentia.intra.manager.billing.BillBreakDownManager;
import com.autentia.intra.manager.billing.BillManager;
import com.autentia.intra.manager.contacts.ContactManager;
import com.autentia.intra.manager.contacts.OrganizationManager;
import com.autentia.intra.manager.security.Permission;
import com.autentia.intra.upload.Uploader;
import com.autentia.intra.upload.UploaderFactory;
import com.autentia.intra.util.ConfigurationUtil;
import com.autentia.intra.util.FacesUtils;
import com.autentia.intra.util.FileUtil;
import com.autentia.intra.util.SpringUtils;
import org.acegisecurity.acls.domain.BasePermission;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.custom.fileupload.UploadedFile;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIData;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;

/**
 * UI bean for Bill objects.
 *
 * @author stajanov code generator
 */
public class BillBean extends BaseBean {
    private BillDAO billDAO = BillDAO.getDefault();

    public void numberValidate(FacesContext context,
                               UIComponent toValidate,
                               Object value) throws ValidatorException {
        if (value == null)
            return;

        String number = (String) value;

        List<Bill> bills = billDAO.search(null);

        if (bills == null || bills.isEmpty())
            return;

        for (Bill b : bills) {
            if (b.getNumber() != null && b.getNumber().equals(number) && !b.getId().equals(bill.getId())) {
                //((UIInput) toValidate).setValid(false);
                //context.addMessage(toValidate.getClientId(context), new FacesMessage("Ya existe otra oferta con este número"));
                throw new ValidatorException(new FacesMessage("Ya existe otra factura con este número"));
            }
        }
    }

    Account selectedAccount = null;


    private int year = Calendar.getInstance().get(Calendar.YEAR);


    private static int ALL_YEARS = 0;

    private static int ALL_ACCOUNTS = -1;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public static int getMaximumYears() {
        return ConfigurationUtil.getDefault().getAccountEntryMaximumYears();
    }

    public List<SelectItem> getYears() {
        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        FacesContext context = FacesContext.getCurrentInstance();
        Locale locale = context.getViewRoot().getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle(
                "com.autentia.intra.resources.messages", locale);

        Date fecha = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        int actual = cal.get(Calendar.YEAR);
        ret.add(new SelectItem(new Integer(actual), bundle
                .getString("bill.fiscalYear.now")));
        ret.add(new SelectItem(new Integer(ALL_YEARS), bundle
                .getString("bill.fiscalYear.all")));

        for (int i = 1; i < getMaximumYears(); i++) {
            ret.add(new SelectItem(new Integer(actual - i), "" + (actual - i)));
        }

        return ret;
    }


    public Organization getProvider() {
        return bill.getProvider();
    }

    public void setProvider(Organization provider) {
        bill.setProvider(provider);
    }

    public Organization getSearchProvider() {
        return search.getProvider();
    }

    public void setSearchProvider(Organization val) {
        if (search.isProviderSet()) {
            search.setProvider(val);
        }
    }

    public boolean isSearchProviderValid() {
        return search.isProviderSet();
    }

    public void setSearchProviderValid(boolean val) {
        if (val) {
            search.setProvider(search.getProvider());
        } else {
            search.unsetProvider();
        }
    }

    /**
     * Get the list of all providers
     *
     * @return the list of all providers
     */
    public List<SelectItem> getProviders() {
        List<Organization> refs = OrganizationManager.getDefault().getProvidersAndClients();
        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        for (Organization ref : refs) {
            if (ref.isEsProveedor()) {
                ret.add(new SelectItem(ref, ref.getName()));
            }
        }
        return ret;
    }


    /**
     * Get the list of all clients
     *
     * @return the list of all clients
     */
    public List<SelectItem> getClients() {
        List<Organization> refs = OrganizationManager.getDefault().getAllEntities(null, new SortCriteria("name"));
        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        for (Organization ref : refs) {
            if (ref.isEsCliente())
                ret.add(new SelectItem(ref, ref.getNameAcro()));
        }
        return ret;
    }


    public boolean getPuedoBuscarBitacore() {
        return (bill.getBillType() == BillType.ISSUED)
                && (bill.getId() != null);
    }

    public String searchInBitacore() {
        if (bill.getId() == null)
            return save();

        return NavigationResults.BILL_SEARCH_IN_BITACORE;
    }

    public List<SelectItem> getAccounts() {
        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();

        List<Account> refs = AccountManager.getDefault().getAllEntities(null, new SortCriteria("name"));
        for (Account ref : refs) {
            ret.add(new SelectItem(ref, ref.getName()));
        }
        return ret;
    }

    BillType selectedType = BillType.ISSUED;

    /**
     * Get the list of all BillType values
     *
     * @return the list of all BillType values
     */
    public List<SelectItem> getBillTypes() {
        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        BillType[] vals = BillType.values();
        for (BillType val : vals) {
            ret.add(new SelectItem(val, FacesUtils.formatMessage("BillType."
                    + val.name())));
        }
        return ret;
    }

    public Contact getContact() {
        return bill.getContact();
    }

    public void setContact(Contact contact) {
        bill.setContact(contact);
    }

    public BillType getBillType() {
        return bill.getBillType();
    }

    public void setBillType(BillType billType) {
        bill.setBillType(billType);
    }

    public String getOrderNumber() {
        return bill.getOrderNumber();
    }

    public void setOrderNumber(String orderNumber) {
        bill.setOrderNumber(orderNumber);
    }

    public Contact getSearchContact() {
        return search.getContact();
    }

    public void setSearchContact(Contact val) {
        if (search.isContactSet()) {
            search.setContact(val);
        }
    }

    public boolean isSearchContactValid() {
        return search.isContactSet();
    }

    public void setSearchContactValid(boolean val) {
        if (val) {
            search.setContact(search.getContact());
        } else {
            search.unsetContact();
        }
    }

    /**
     * Get the list of all contacts
     *
     * @return the list of all contacts
     */
    public List<SelectItem> getContacts() {
        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        List<Contact> refs = ContactManager.getDefault().getAllEntities(null, new SortCriteria("name"));
        for (Contact ref : refs) {
            ret.add(new SelectItem(ref, ref.getName()));
        }
        return ret;
    }

    public String getSearchOrderNumber() {
        return search.getOrderNumber();
    }

    public void setSearchOrderNumber(String val) {
        if (search.isOrderNumberSet()) {
            search.setOrderNumber(val);
        }
    }

    public boolean isSearchOrderNumberValid() {
        return search.isOrderNumberSet();
    }

    public void setSearchOrderNumberValid(boolean val) {
        if (val) {
            search.setOrderNumber(search.getOrderNumber());
        } else {
            search.unsetOrderNumber();
        }
    }

    /**
     * Serial version field
     */
    private static final long serialVersionUID = -1L;


    private BigDecimal totals;

    private BigDecimal totalsNoTaxes;

    private BigDecimal totalsTaxes;

    public BigDecimal getTotals() {
        return totals;
    }

    public void setTotals(BigDecimal total) {
        this.totals = total;
    }

    class comparaAlbaranes implements Comparator<Albaran> {
        public int compare(Albaran o1, Albaran o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }

    class comparaOffers implements Comparator<Offer> {
        public int compare(Offer o1, Offer o2) {
            return o1.getNumber().compareTo(o2.getNumber());
        }
    }

    public String quitaAlbaran() {
        UIData table = (UIData) FacesUtils.getComponent("bill:albarans");
        Albaran a = (Albaran) table.getRowData();
        bill.getAlbarans().remove(a);
        a.setBill(null);
        return null;
    }

    public String anadeAlbaran() {
        save();

        UIData table = (UIData) FacesUtils.getComponent("bill:albarans");
        Albaran a = (Albaran) table.getRowData();
        if (bill.getAlbarans() == null)
            bill.setAlbarans(new HashSet<Albaran>());

        bill.getAlbarans().add(a);
        a.setBill(bill);
        bill = (Bill) billDAO.merge(bill);
        if (bill.getLineaTrabajo() == null || bill.getLineaTrabajo().getId() == 1)
            bill.setLineaTrabajo(bill.getLineaTrabajoDefault());
        return null;
    }

    public String quitaOffer() {
        UIData table = (UIData) FacesUtils.getComponent("bill:offers");
        Offer a = (Offer) table.getRowData();
        bill.getOffers().remove(a);
        a.setBill(null);
        return null;
    }

    public String anadeOffer() {
        save();

        UIData table = (UIData) FacesUtils.getComponent("bill:offers");
        Offer a = (Offer) table.getRowData();
        if (bill.getOffers() == null)
            bill.setOffers(new HashSet<Offer>());

        bill.getOffers().add(a);
        a.setBill(bill);
        bill = (Bill) billDAO.merge(bill);
        if (bill.getLineaTrabajo() == null || bill.getLineaTrabajo().getId() == 1)
            bill.setLineaTrabajo(bill.getLineaTrabajoDefault());
        return null;
    }

    public List<Albaran> getAllMyAlbarans() {
        AlbaranDAO pdao = AlbaranDAO.getDefault();
        List<Albaran> ret = new ArrayList<Albaran>();
        if (bill.getClient() != null) {
            AlbaranSearch as = new AlbaranSearch();
            as.setClient(bill.getClient());
            as.setBill(null);
            for (Albaran a : pdao.search(as, null)) {
                ret.add(a);
            }
            as.unsetClient();
            as.setBill(bill);
            if (bill.getId() != null) {
                for (Albaran a : pdao.search(as, null)) {
                    ret.add(a);
                }
            }
        }
        Collections.sort(ret, new comparaAlbaranes());
        return ret;
    }

    public List<Offer> getAllMyOffers() {
        OfferDAO pdao = OfferDAO.getDefault();
        List<Offer> ret = new ArrayList<Offer>();
        if (bill.getClient() != null) {
            OfferSearch as = new OfferSearch();
            as.setOrganization(bill.getClient());
            as.setBill(null);
            for (Offer a : pdao.search(as, null)) {
                if ((a.getCosts() != null && !a.getCosts().isEmpty())
                        || (a.getRoles() != null && !a.getRoles().isEmpty())) {
                    ret.add(a);
                }
            }
            as.unsetOrganization();
            as.setBill(bill);
            if (bill.getId() != null) {
                for (Offer a : pdao.search(as, null)) {
                    ret.add(a);
                }
            }
        }
        Collections.sort(ret, new comparaOffers());
        return ret;
    }

    public BigDecimal getTotal() {
        return bill.getTotal();
    }


    public BigDecimal getTotalsNoTaxes() {
        return totalsNoTaxes;
    }

    public void setTotalsNoTaxes(BigDecimal totalsNoTaxes) {
        this.totalsNoTaxes = totalsNoTaxes;
    }


    public BigDecimal getTotalsTaxes() {
        return totalsTaxes;
    }

    public void setTotalsTaxes(BigDecimal totalsTaxes) {
        this.totalsTaxes = totalsTaxes;
    }

    private void calcTotals(List<Bill> res) {

        BigDecimal valor = new BigDecimal(0);
        BigDecimal valorNoTaxes = new BigDecimal(0);
        for (Bill elem : res) {
            valor = valor.add(elem.getTotal());
            valorNoTaxes = valorNoTaxes.add(elem.getTotalNoTaxes());
        }

        setTotals(valor);
        setTotalsNoTaxes(valorNoTaxes);
        setTotalsTaxes(valor.subtract(valorNoTaxes));
    }

    /**
     * Selected organization *
     */
    private Organization selectedOrganization = null;
    private Integer idSelectedOrganization = 0;

    public Organization getSelectedOrganization() {

        if (bill != null && bill.getProject() != null) {
            selectedOrganization = bill.getProject().getClient();
            return selectedOrganization;
        }

        /*
        if (selectedOrganization == null) {
            selectedOrganization = OrganizationManager.getDefault().getMyOrganization();
        }
        */

        return selectedOrganization;
    }

    public void setSelectedOrganization(Organization organization) {

        selectedOrganization = organization;
        if (bill != null) {
            Project project = bill.getProject();

            if (project != null) {
                project.setClient(organization);
            }
        }
    }

    public Integer getIdSelectedOrganization() {

        idSelectedOrganization = ConfigurationUtil.getDefault().getIdOurCompany();
        return idSelectedOrganization;
    }

    public void setIdSelectedOrganization(Integer idOrganization) {
        idSelectedOrganization = idOrganization;
    }

    public List<SelectItem> getProjectsBySelectedOrganization() {

        List<Project> refs = ProjectManager.getDefault().getProjectsByOrganization(selectedOrganization);

        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        for (Object ref : refs) {
            Project proy = (Project) ref;
            String asterisco = proy.isFinished() ? "(*)" : "";
            ret.add(new SelectItem(proy, proy.getNameDescriptivo() + asterisco));
        }

        return ret;
    }

    public List<SelectItem> getContactsBySelectedOrganization() {
        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();

        ContactSearch cs = new ContactSearch();
        cs.setOrganization(getSelectedOrganization());
        List<Contact> refs = ContactManager.getDefault().getAllEntities(cs, new SortCriteria("name"));
        for (Contact ref : refs) {
            ret.add(new SelectItem(ref, ref.getName()));
        }

        //	Añadiendo el campo vacio
        ret.add(0, new SelectItem(""));

        return ret;
    }

    public List<SelectItem> getProjectsOpenBySelectedOrganization() {

        List<Project> refs = ProjectManager.getDefault().getProjectsByOrganization(selectedOrganization);

        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        for (Object ref : refs) {
            Project proy = (Project) ref;
            ret.add(new SelectItem(proy, proy.getNameDescriptivo()));
        }

        return ret;
    }

    public List<SelectItem> getProjectsOpenBySelectedOrganizationOrBlank() {
        if (bill != null && bill.getClient() != null)
            selectedOrganization = bill.getClient();

        List<Project> refs = ProjectManager.getDefault().getProjectsByOrganization(selectedOrganization);

        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        for (Object ref : refs) {
            Project proy = (Project) ref;
            ret.add(new SelectItem(proy, proy.getNameDescriptivo()));
        }
        ret.add(0, new SelectItem(""));

        return ret;
    }

    public void onSelectedOrganizationChanged(ValueChangeEvent event) {
        setSelectedOrganization((Organization) event.getNewValue());
        bill.setClient((Organization) event.getNewValue());

        FacesUtils.renderResponse();
    }

    public void onSelectedOrganizationSearchChanged(ValueChangeEvent event) {
        Organization now = (Organization) event.getNewValue();
        if (now == null)
            return;
        setSearchProjectValid(true);
        setSelectedOrganization(now);
        FacesUtils.renderResponse();
    }

    public void onEditSelected(ActionEvent event) {
        setSelectedOrganization(null);
    }

    /**
     * Get the list of all projects
     *
     * @return the list of all projects
     */
    public List<SelectItem> getOpenprojects() {
        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();

        List<Project> refs = ProjectManager.getDefault().getAllEntities(null, new SortCriteria("name"));
        for (Project proy : refs) {
            ret.add(new SelectItem(proy, proy.getName()));
        }
        return ret;
    }

    public String unselect() {
        int idEntry = Integer.parseInt(FacesUtils
                .getRequestParameter("idEntry"));


        int id = bill.getId();
        bill.getEntries().remove(AccountEntryManager.getDefault().getEntityById(idEntry));
        save();

        bill = manager.getEntityById(id);
        return NavigationResults.EDIT;
    }

    /**
     * Get the list of all categoriass
     *
     * @return the list of all categoriass
     */
    public List<SelectItem> getEntriess() {
        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        List<AccountEntry> refs = AccountEntryManager.getDefault().getAllEntities(null, new SortCriteria("concept"));

        for (AccountEntry ref : refs) {
            ret.add(new SelectItem(ref, ref.getConcept()));
        }
        return ret;
    }

    /**
     * Get the list of all projects
     *
     * @return the list of all projects
     */
    public List<SelectItem> getProjects() {
        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        List<Project> refs = ProjectManager.getDefault().getAllEntities(null, new SortCriteria("referenciaLaboratorio"));

        for (Project ref : refs) {
            ret.add(new SelectItem(ref, ref.getNameDescriptivo()));
        }
        return ret;
    }

    public Set<AccountEntry> getEntries() {
        return bill.getEntries();
    }

    public void setEntries(Set<AccountEntry> entries) {
        bill.setEntries(entries);
    }

    public String searchEntries() {
        AccountEntryBean bean = (AccountEntryBean) FacesUtils
                .getBean("accountEntryBean");
        bean.setBill(bill);
        setBill(bill);
        return NavigationResults.BILL_SEARCH_ENTRIES;
    }

    /**
     * Get the list of all organizations
     *
     * @return the list of all organizations
     */
    public List<SelectItem> getOrganizations() {
        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        List<Organization> refs = OrganizationManager.getDefault().getAllEntities(null, new SortCriteria("name"));

        for (Organization ref : refs) {
            ret.add(new SelectItem(ref, ref.getName() + (ref.getAcronimo() == null || ref.getAcronimo().equals("") ? "" : " (" + ref.getAcronimo() + ")")));
        }

        ret.add(0, new SelectItem(""));

        return ret;
    }

    /* bill - generated by stajanov (do not edit/delete) */

    /**
     * Logger
     */
    private static final Log log = LogFactory.getLog(BillBean.class);


    /**
     * Upload service
     */
    private static final Uploader uploader = UploaderFactory
            .getInstance("bill");

    /**
     * Temporary file field
     */
    private String oldFile;

    /**
     * Uploaded file object
     */
    private UploadedFile uploadFile;

    /**
     * Active Bill object
     */
    private Bill bill;

    /**
     * Active search object
     */
    private BillSearch search = new BillSearch();

    /**
     * Manager
     */
    private static BillManager manager = BillManager.getDefault();

    /**
     * Quick search letter for ABC pager control
     */
    private Character letter;

    /**
     * Default sort column
     */
    private String sortColumn = "number";

    /**
     * Default sort order
     */
    private boolean sortAscending = false;


    /**
     * Handle an ABC pager letter click: filter objects by specified starting letter
     */
    public void letterClicked() {
        if (letter != null) {
            UIComponent comp = FacesUtils.getComponent("bills:list");
            HtmlDataTable tabla = (HtmlDataTable) comp;
            tabla.setFirst(0);

            search.setName(letter + "%");
        } else {
            search.unsetName();
        }
    }

    public Character getLetter() {
        return letter;
    }

    public void setLetter(Character letter) {
        this.letter = letter;
    }


    /**
     * List bills. Order depends on Faces parameter sort.
     *
     * @return the list of all bills sorted by requested criterion
     */
    public List<Bill> getAll() {

        if (year == ALL_YEARS) {
            search.unsetYear();
        } else {
            search.setYear(year);
        }
        search.setBillType(getSelectedType());

        List<Bill> res = null;

        res = manager.getAllEntities(search, new SortCriteria(sortColumn, sortAscending));


        calcTotals(res);

        return res;
    }

    // Getters to list possible values of related entities

    // Getters to list possible values of enum fields

    // Getters to list possible values of enum fields

    public String getPaymentModeFormatted() {
        return FacesUtils.formatMessage("BillPaymentMode."
                + this.getPaymentMode().name());
    }

    /**
     * Get the list of all PaymentMode values
     *
     * @return the list of all PaymentMode values
     */
    public List<SelectItem> getPaymentModes() {
        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        BillPaymentMode[] vals = BillPaymentMode.values();
        for (BillPaymentMode val : vals) {
            ret.add(new SelectItem(val, FacesUtils
                    .formatMessage("BillPaymentMode." + val.name())));
        }
        return ret;
    }

    public String getStateFormatted() {
        return FacesUtils.formatMessage("BillState." + this.getState().name());
    }

    /**
     * Get the list of all State values
     *
     * @return the list of all State values
     */
    public List<SelectItem> getStates() {
        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        BillState[] vals = BillState.values();
        for (BillState val : vals) {
            ret.add(new SelectItem(val, FacesUtils.formatMessage("BillState."
                    + val.name())));
        }
        return ret;
    }

    // Methods to create/remove instances of one-to-many entities (slave
    // entities)

    /**
     * Go to create page
     *
     * @return forward to CREATE page
     */
    public String create() {
        bill = new Bill();
        bill.setCreationDate(new Date());
        bill.setStartBillDate(new Date());
        bill.setEndBillDate(new Date());
        bill.setExpiration(90);
        bill.setBillType(getSelectedType());
        selectedOrganization = null;
        return NavigationResults.CREATE;
    }

    /**
     * Go to edit page
     *
     * @return forward to EDIT page
     */
    public String edit() {
        Integer id = Integer.parseInt(FacesUtils.getRequestParameter("id"));
        bill = manager.getEntityById(id);

        return NavigationResults.EDIT;
    }


    /**
     * Save bean and stay on it
     *
     * @return forward to list page
     */
    public String save() {
        if (bill.getId() == null) {
            manager.insertEntity(bill);
        } else {
            bill = (Bill) billDAO.merge(bill);
            manager.updateEntity(bill);
        }

        // Handle uploads for file field
        if (uploadFile != null) {
            try {
                uploader.replace(Integer.toString(bill.getId()), oldFile, uploadFile);
            } catch (IOException e) {
                log.error("save - exception uploading field file", e);
                FacesUtils.addErrorMessage("file", "error.fileTransfer", e.getMessage());
            }
        }

        //FacesUtils.addInfoMessage("billType", "bill.saveBillOK");
        return null;
    }

    /**
     * Delete bean and go back to beans list
     *
     * @return forward to LIST page
     */
    public String delete() {
        manager.deleteEntity(bill);
        bill = null;
        return NavigationResults.LIST;
    }

    public boolean isPuedoBorrar() {
        return bill != null
                && (bill.getAlbarans() == null
                || bill.getAlbarans().isEmpty())
                && (bill.getOffers() == null
                || bill.getOffers().isEmpty());
    }

    /**
     * Go back to beans list
     *
     * @return forward to LIST page
     */
    public String list() {
        return NavigationResults.LIST;
    }

    /**
     * Go to search page
     *
     * @return forward to SEARCH page
     */
    public String search() {
        return NavigationResults.SEARCH;
    }

    /**
     * Check if we have an active object.
     *
     * @return true is an object is selected
     */
    public boolean isBillSelected() {
        return bill != null;
    }

    // Getters and setters to manipulate sorting
    public boolean isSortAscending() {
        return sortAscending;
    }

    public void setSortAscending(boolean sortAscending) {
        this.sortAscending = sortAscending;
    }

    public String getSortColumn() {
        return sortColumn;
    }

    public void setSortColumn(String sortColumn) {
        this.sortColumn = sortColumn;
    }

    // Getters and setters to handle search

    public Date getSearchStartCreationDate() {
        return search.getStartCreationDate();
    }

    public void setSearchStartCreationDate(Date val) {
        if (val != null) {
            search.setStartCreationDate(val);
        } else {
            search.unsetStartCreationDate();
        }
    }

    public boolean isSearchStartCreationDateValid() {
        return search.isStartCreationDateSet();
    }

    public void setSearchStartCreationDateValid(boolean val) {
        if (val) {
            search.setStartCreationDate(search.getStartCreationDate());
        } else {
            search.unsetStartCreationDate();
        }
    }

    public Date getSearchEndCreationDate() {
        return search.getEndCreationDate();
    }

    public void setSearchEndCreationDate(Date val) {
        if (val != null) {
            search.setEndCreationDate(val);
        } else {
            search.unsetEndCreationDate();
        }
    }

    public boolean isSearchEndCreationDateValid() {
        return search.isEndCreationDateSet();
    }

    public void setSearchEndCreationDateValid(boolean val) {
        if (val) {
            search.setEndCreationDate(search.getEndCreationDate());
        } else {
            search.unsetEndCreationDate();
        }
    }

    public Integer getSearchStartExpiration() {
        return search.getStartExpiration();
    }

    public void setSearchStartExpiration(Integer val) {
        if (val != null) {
            search.setStartExpiration(val);
        } else {
            search.unsetStartExpiration();
        }
    }

    public boolean isSearchStartExpirationValid() {
        return search.isStartExpirationSet();
    }

    public void setSearchStartExpirationValid(boolean val) {
        if (val) {
            search.setStartExpiration(search.getStartExpiration());
        } else {
            search.unsetStartExpiration();
        }
    }

    public Integer getSearchEndExpiration() {
        return search.getEndExpiration();
    }

    public void setSearchEndExpiration(Integer val) {
        if (val != null) {
            search.setEndExpiration(val);
        } else {
            search.unsetEndExpiration();
        }
    }

    public boolean isSearchEndExpirationValid() {
        return search.isEndExpirationSet();
    }

    public void setSearchEndExpirationValid(boolean val) {
        if (val) {
            search.setEndExpiration(search.getEndExpiration());
        } else {
            search.unsetEndExpiration();
        }
    }

    public BillPaymentMode getSearchPaymentMode() {
        return search.getPaymentMode();
    }

    public void setSearchPaymentMode(BillPaymentMode val) {
        if (search.isPaymentModeSet()) {
            search.setPaymentMode(val);
        }
    }

    public boolean isSearchPaymentModeValid() {
        return search.isPaymentModeSet();
    }

    public void setSearchPaymentModeValid(boolean val) {
        if (val) {
            search.setPaymentMode(search.getPaymentMode());
        } else {
            search.unsetPaymentMode();
        }
    }

    public BillState getSearchState() {
        return search.getState();
    }

    public void setSearchState(BillState val) {
        if (search.isStateSet()) {
            search.setState(val);
        }
    }

    public boolean isSearchStateValid() {
        return search.isStateSet();
    }

    public void setSearchStateValid(boolean val) {
        if (val) {
            search.setState(search.getState());
        } else {
            search.unsetState();
        }
    }


    public String getSearchNumber() {
        return search.getNumber();
    }

    public void setSearchNumber(String val) {
        if (search.isNumberSet()) {
            search.setNumber(val);
        }
    }

    public boolean isSearchNumberValid() {
        return search.isNumberSet();
    }

    public void setSearchNumberValid(boolean val) {
        if (val) {
            search.setNumber(search.getNumber());
        } else {
            search.unsetNumber();
        }
    }

    public String getSearchName() {
        return search.getName();
    }

    public void setSearchName(String val) {
        if (search.isNameSet()) {
            search.setName(val);
        }
    }

    public boolean isSearchNameValid() {
        return search.isNameSet();
    }

    public void setSearchNameValid(boolean val) {
        if (val) {
            search.setName(search.getName());
        } else {
            search.unsetName();
        }
    }

    public String getSearchFile() {
        return search.getFile();
    }

    public void setSearchFile(String val) {
        if (search.isFileSet()) {
            search.setFile(val);
        }
    }

    public boolean isSearchFileValid() {
        return search.isFileSet();
    }

    public void setSearchFileValid(boolean val) {
        if (val) {
            search.setFile(search.getFile());
        } else {
            search.unsetFile();
        }
    }

    public String getSearchFileMime() {
        return search.getFileMime();
    }

    public void setSearchFileMime(String val) {
        if (search.isFileMimeSet()) {
            search.setFileMime(val);
        }
    }

    public boolean isSearchFileMimeValid() {
        return search.isFileMimeSet();
    }

    public void setSearchFileMimeValid(boolean val) {
        if (val) {
            search.setFileMime(search.getFileMime());
        } else {
            search.unsetFileMime();
        }
    }

    public Project getSearchProject() {
        return search.getProject();
    }

    public void setSearchProject(Project val) {
        if (search.isProjectSet()) {
            search.setProject(val);
        }
    }

    public boolean isSearchProjectValid() {
        return search.isProjectSet();
    }

    public void setSearchProjectValid(boolean val) {
        if (val) {
            search.setProject(search.getProject());
        } else {
            search.unsetProject();
        }
    }

    // Getters and setters to handle uploads

    public void setUploadFile(UploadedFile uploadFile) {
        if (uploadFile != null) {
            oldFile = bill.getFile();
            this.uploadFile = uploadFile;
            setFile(FileUtil.getFileName(uploadFile.getName()));
            setFileMime(uploadFile.getContentType());
        }
    }

    public UploadedFile getUploadFile() {
        return uploadFile;
    }

    // Getters and setters to manipulate active Bill object
    public java.lang.Integer getId() {
        return bill.getId();
    }

    public Date getCreationDate() {
        return bill.getCreationDate();
    }

    public void setCreationDate(Date creationDate) {
        bill.setCreationDate(creationDate);
    }

    public int getExpiration() {
        return bill.getExpiration();
    }

    public void setExpiration(int expiration) {
        bill.setExpiration(expiration);
    }

    public BillPaymentMode getPaymentMode() {
        return bill.getPaymentMode();
    }

    public void setPaymentMode(BillPaymentMode paymentMode) {
        bill.setPaymentMode(paymentMode);
    }

    public BillState getState() {
        return bill.getState();
    }

    public void setState(BillState state) {
        bill.setState(state);
    }


    public String getNumber() {
        return bill.getNumber();
    }

    public void setNumber(String number) {
        bill.setNumber(number);
    }

    public String getNumberDefault() {
        if (getNumber() == null) {
            int maximo = 0;
            NumberFormat nf = NumberFormat.getInstance();
            nf.setMinimumIntegerDigits(2);
            String regex = "F\\d+/";
            String anio = nf.format(new Date().getYear() - 100);
            regex = regex.concat(anio);
            BillSearch billSearch = new BillSearch();
            billSearch.setBillType(BillType.ISSUED);
            List<Bill> list = billDAO.search(billSearch, null);
            for (Bill o : list) {
                String number = o.getNumber();
                if (number != null && number.matches(regex)) {
                    int x = Integer.parseInt(number.substring(1, number.indexOf('/')));
                    if (x > maximo)
                        maximo = x;
                }
            }
            nf.setMinimumIntegerDigits(3);
            return "F".concat(nf.format(maximo + 1)).concat("/").concat(anio);
        } else {
            return getNumber();
        }
    }

    public void setNumberDefault(String number) {
        setNumber(number);
    }

    public String getName() {
        return bill.getName();
    }

    public void setName(String name) {
        bill.setName(name);
    }

    public String getFile() {
        return bill.getFile();
    }

    public void setFile(String file) {
        bill.setFile(file);
    }

    public String getFileMime() {
        return bill.getFileMime();
    }

    public void setFileMime(String fileMime) {
        bill.setFileMime(fileMime);
    }

    public String getObservations() {
        return bill.getObservations();
    }

    public void setObservations(String observations) {
        bill.setObservations(observations);
    }

    public Project getProject() {
        return bill.getProject();
    }

    public void setProject(Project project) {
        bill.setProject(project);
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public BillSearch getSearch() {
        return search;
    }

    /* bill - generated by stajanov (do not edit/delete) */

    /*
      * Create a new empty instance of the one-to-many field @return forward to
      * the same page
      */

    public String createBreakDown() {
        BillBreakDown item = new BillBreakDown();
        item.setBill(bill);
        item.setIva(new BigDecimal(ConfigurationUtil.getDefault().getIva()));
        item.setUnits(new BigDecimal(1));
        if (bill.getBreakDown() == null) {
            bill.setBreakDown(new HashSet<BillBreakDown>());
        }
        bill.getBreakDown().add(item);
        return null;
    }

    /**
     * Delete selected instance of the one-to-many field
     *
     * @return forward to the same page
     */
    public String deleteBreakDown() {
        UIData table = (UIData) FacesUtils.getComponent("bill:breakDown");
        bill.getBreakDown().remove((BillBreakDown) table.getRowData());
        return null;
    }

    /**
     * Get the list of all breakDowns
     *
     * @return the list of all breakDowns
     */
    public List<SelectItem> getBreakDowns() {
        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        List<BillBreakDown> refs = BillBreakDownManager.getDefault().getAllEntities(null, new SortCriteria("concept"));
        for (BillBreakDown ref : refs) {
            ret.add(new SelectItem(ref, ref.getConcept()));
        }
        return ret;
    }

    List<BillBreakDown> bitacoreBreakDowns = null;

    public List<BillBreakDown> getAllBitacoreBreakDowns() {

        List<BillBreakDown> desgloses = manager.getAllBitacoreBreakDowns(getStartBillDate(), getEndBillDate(),
                getProject().getRoles(), getProject().getCosts());

        setBitacoreBreakDowns(desgloses);
        return desgloses;
    }

    public List<BillBreakDown> getSearchBreakDown() {
        return getSearch().getBreakDown();
    }

    public void setSearchBreakDown(List<BillBreakDown> val) {
        if (search.isBreakDownSet()) {
            search.setBreakDown(val);
        }
    }

    public boolean isSearchBreakDownValid() {
        return search.isBreakDownSet();
    }

    public void setSearchBreakDownValid(boolean val) {
        if (val) {
            search.setBreakDown(search.getBreakDown());
        } else {
            search.unsetBreakDown();
        }
    }

    public Set<BillBreakDown> getBreakDown() {
        return bill.getBreakDown();
    }

    public void setBreakDown(Set<BillBreakDown> breakDown) {
        bill.setBreakDown(breakDown);
    }

    public Date getSearchStartStartBillDate() {
        return search.getStartStartBillDate();
    }

    public void setSearchStartStartBillDate(Date val) {
        if (val != null) {
            search.setStartStartBillDate(val);
        } else {
            search.unsetStartStartBillDate();
        }
    }

    public boolean isSearchStartStartBillDateValid() {
        return search.isStartStartBillDateSet();
    }

    public void setSearchStartStartBillDateValid(boolean val) {
        if (val) {
            search.setStartStartBillDate(search.getStartStartBillDate());
        } else {
            search.unsetStartStartBillDate();
        }
    }

    public Date getSearchEndStartBillDate() {
        return search.getEndStartBillDate();
    }

    public void setSearchEndStartBillDate(Date val) {
        if (val != null) {
            search.setEndStartBillDate(val);
        } else {
            search.unsetEndStartBillDate();
        }
    }

    public boolean isSearchEndStartBillDateValid() {
        return search.isEndStartBillDateSet();
    }

    public void setSearchEndStartBillDateValid(boolean val) {
        if (val) {
            search.setEndStartBillDate(search.getEndStartBillDate());
        } else {
            search.unsetEndStartBillDate();
        }
    }

    public Date getSearchStartEndBillDate() {
        return search.getStartEndBillDate();
    }

    public void setSearchStartEndBillDate(Date val) {
        if (val != null) {
            search.setStartEndBillDate(val);
        } else {
            search.unsetStartEndBillDate();
        }
    }

    public boolean isSearchStartEndBillDateValid() {
        return search.isStartEndBillDateSet();
    }

    public void setSearchStartEndBillDateValid(boolean val) {
        if (val) {
            search.setStartEndBillDate(search.getStartEndBillDate());
        } else {
            search.unsetStartEndBillDate();
        }
    }

    public Date getSearchEndEndBillDate() {
        return search.getEndEndBillDate();
    }

    public void setSearchEndEndBillDate(Date val) {
        if (val != null) {
            search.setEndEndBillDate(val);
        } else {
            search.unsetEndEndBillDate();
        }
    }

    public boolean isSearchEndEndBillDateValid() {
        return search.isEndEndBillDateSet();
    }

    public void setSearchEndEndBillDateValid(boolean val) {
        if (val) {
            search.setEndEndBillDate(search.getEndEndBillDate());
        } else {
            search.unsetEndEndBillDate();
        }
    }

    public Date getStartBillDate() {
        return bill.getStartBillDate();
    }

    public void setStartBillDate(Date startBillDate) {
        bill.setStartBillDate(startBillDate);
    }

    public Date getEndBillDate() {
        return bill.getEndBillDate();
    }

    public void setEndBillDate(Date endBillDate) {
        bill.setEndBillDate(endBillDate);
    }

    public BillType getSelectedType() {
        return selectedType;
    }

    public void setSelectedType(BillType selectedType) {
        this.selectedType = selectedType;
    }

    public boolean getPuedoImprimir() {
        return (bill.getBillType() == BillType.ISSUED)
                && (bill.getId() != null && bill.getClient() != null);
    }

    public boolean getPuedoPintarProvider() {
        return (getSelectedType() == BillType.RECIEVED);

    }


    public Account getSelectedAccount() {
        return selectedAccount;
    }

    public void setSelectedAccount(Account selectedAccount) {
        this.selectedAccount = selectedAccount;
    }

    public String importBitacore() {
        for (BillBreakDown bbd : getBitacoreBreakDowns()) {
            if (bbd.isSelected()) {
                BillBreakDown item = new BillBreakDown();
                item.setConcept(bbd.getConcept());
                item.setBill(bill);
                item.setIva(bbd.getIva());
                item.setAmount(bbd.getAmount());
                item.setUnits(bbd.getUnits());
                if (bill.getBreakDown() == null) {
                    bill.setBreakDown(new HashSet<BillBreakDown>());
                }
                bill.getBreakDown().add(item);
            }

        }

        return NavigationResults.IMPORT_BITACORE;
    }

    public List<BillBreakDown> getBitacoreBreakDowns() {
        return bitacoreBreakDowns;
    }

    public void setBitacoreBreakDowns(List<BillBreakDown> bitacoreBreakDowns) {
        this.bitacoreBreakDowns = bitacoreBreakDowns;
    }

    /**
     * Whether or not create button is available for user
     *
     * @return true if user can create objects of type Bill
     */
    public boolean isCreateAvailable() {
        return SpringUtils.isRolePermissionGranted(Permission.Entity_Create(Bill.class));
    }

    /**
     * Whether or not edit button is available for user
     *
     * @return true if user can edit current object
     */
    public boolean isEditAvailable() {
        return SpringUtils.isAclPermissionGranted(bill, BasePermission.WRITE);
    }

    /**
     * Whether or not delete button is available for user
     *
     * @return true if user can delete current object
     */
    public boolean isDeleteAvailable() {
        return (bill.getId() != null) &&
                SpringUtils.isAclPermissionGranted(bill, BasePermission.DELETE);
    }

    /**
     * Go to detail page
     *
     * @return forward to DETAIL page
     */
    public String detail() {
        Integer id = Integer.parseInt(FacesUtils.getRequestParameter("id"));
        bill = manager.getEntityById(id);

        return SpringUtils.isAclPermissionGranted(bill, BasePermission.WRITE)
                ? NavigationResults.EDIT
                : NavigationResults.DETAIL;
    }

    /**
     * Reset search criteria
     *
     * @return forward to LIST page
     */
    public String reset() {
        search.reset();
        return list();
    }

    public void setClient(Organization organization) {
        bill.setClient(organization);
    }

    public Organization getClient() {
        return bill.getClient();
    }


    public Organization getSearchClient() {
        return search.getClient();
    }

    public void setSearchClient(Organization val) {
        if (search.isClientSet()) {
            search.setClient(val);
        }
    }

    public boolean isSearchClientValid() {
        return search.isClientSet();
    }

    public void setSearchClientValid(boolean val) {
        if (val) {
            search.setClient(search.getClient());
        } else {
            search.unsetClient();
        }
    }

    public String detail_desde_fuera() {
        return "bill_" + detail();
    }


    public static Uploader getUploader() {
        return uploader;
    }

    public String deleteFile() throws IOException {
        if (bill != null && bill.getId() != null && !"".equals(bill.getFile()))
            uploader.delete(String.valueOf(bill.getId()), bill.getFile());
        bill.setFile(null);
        bill.setFileMime(null);
        return null; //return to same page
    }

    /**
     * Get the list of all Departament values
     *
     * @return the list of all Department values
     */
    public List<SelectItem> getLineaTrabajos() {
        List<Department> refs = DepartmentManager.getDefault().getAllEntities(null, new SortCriteria("name"));
        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        for (Department ref : refs) {
            ret.add(new SelectItem(ref, ref.getRuta()));
        }
        Collections.sort(ret, new Comparator<SelectItem>() {
            public int compare(SelectItem o1, SelectItem o2) {
                Department este = (Department) o1.getValue();
                Department otro = (Department) o2.getValue();
                return este.getRuta().compareTo(otro.getRuta());
            }
        });
        return ret;
    }

    public Department getSearchLineaTrabajo() {
        return search.getLineaTrabajo();
    }

    public void setSearchLineaTrabajo(Department val) {
        if (search.isLineaTrabajoSet()) {
            search.setLineaTrabajo(val);
        }
    }

    public boolean isSearchLineaTrabajoValid() {
        return search.isLineaTrabajoSet();
    }

    public void setSearchLineaTrabajoValid(boolean val) {
        if (val) {
            search.setLineaTrabajo(search.getLineaTrabajo());
        } else {
            search.unsetLineaTrabajo();
        }
    }
}
