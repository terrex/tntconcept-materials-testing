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
package com.autentia.intra.bean.contacts;

import com.autentia.intra.bean.BaseBean;
import com.autentia.intra.bean.NavigationResults;
import com.autentia.intra.businessobject.*;
import com.autentia.intra.dao.SortCriteria;
import com.autentia.intra.dao.hibernate.EnsayoDAO;
import com.autentia.intra.dao.hibernate.OfferDAO;
import com.autentia.intra.dao.search.ContactSearch;
import com.autentia.intra.dao.search.EnsayoPrecioSearch;
import com.autentia.intra.dao.search.OfferSearch;
import com.autentia.intra.dao.search.PautaPrecioSearch;
import com.autentia.intra.manager.admin.DepartmentManager;
import com.autentia.intra.manager.admin.ProjectManager;
import com.autentia.intra.manager.admin.UserManager;
import com.autentia.intra.manager.contacts.*;
import com.autentia.intra.manager.security.Permission;
import com.autentia.intra.upload.Uploader;
import com.autentia.intra.upload.UploaderFactory;
import com.autentia.intra.util.ConfigurationUtil;
import com.autentia.intra.util.FacesUtils;
import com.autentia.intra.util.SpringUtils;
import org.acegisecurity.acls.domain.BasePermission;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIData;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;


public class OfferBean extends BaseBean {
    private OfferDAO offerDAO = OfferDAO.getDefault();

    public String copiarTodos() {
        offer = (Offer) offerDAO.merge(offer);

        List<String> ids = new ArrayList<String>();

        ProjectManager pm = ProjectManager.getDefault();
        if (offer.getEnsayos() != null && !offer.getEnsayos().isEmpty()) {
            Project p = new Project(offer);
            pm.insertEntity(p);
            ids.add(p.getReferenciaLaboratorio());
        }

        if (offer.getPautas() != null) {
            for (OfferPauta op : offer.getPautas()) {
                for (int i = 0; i < op.getUnidades(); i++) {
                    Project p = new Project(op);
                    pm.insertEntity(p);
                    ids.add(p.getReferenciaLaboratorio());
                }
            }
        }

        String msg = "";
        for (String s : ids)
            msg += s + ", ";
        FacesUtils.addInfoMessage(null, "offerBean.informesCreadosMsg", msg);
        offer.setInformesCreados(true);
        save();
        return null;
    }


    public void onSelectedEnsayoChanged(ValueChangeEvent event) {
        Ensayo nuevo = (Ensayo) event.getNewValue();
        //UIData linea = (UIData) event.getComponent().getParent().getParent().getParent();
        //cambiar precio si nulo.
        //((OfferEnsayo)linea.getValue()).setEnsayo(nuevo);
        //((OfferEnsayo)linea.getValue()).setCost(new BigDecimal(7));

        UIData table = (UIData) FacesUtils.getComponent("offer:ensayos");
        OfferEnsayo oe = (OfferEnsayo) table.getRowData();
        oe.setEnsayo(nuevo);
        BigDecimal coste = nuevo.getCost();

        EnsayoPrecioManager epm = EnsayoPrecioManager.getDefault();
        EnsayoPrecioSearch busqueda = new EnsayoPrecioSearch();
        busqueda.setClient(getOrganization());
        busqueda.setEnsayo(nuevo);
        List<EnsayoPrecio> todos = epm.getAllEntities(busqueda, null);
        for (EnsayoPrecio ep : todos) //debe haber solo uno o ninguno
        {
            if (ep.getCost() != null)
                coste = ep.getCost();
        }

        oe.setCost(coste);

        FacesUtils.renderResponse();
    }


    public void onSelectedPautaChanged(ValueChangeEvent event) {
        Pauta nuevo = (Pauta) event.getNewValue();

        UIData table = (UIData) FacesUtils.getComponent("offer:pautas");
        OfferPauta op = (OfferPauta) table.getRowData();
        op.setPauta(nuevo);
        BigDecimal coste = nuevo.getCost();

        PautaPrecioManager ppm = PautaPrecioManager.getDefault();
        PautaPrecioSearch busqueda = new PautaPrecioSearch();
        busqueda.setClient(getOrganization());
        busqueda.setPauta(nuevo);
        List<PautaPrecio> todos = ppm.getAllEntities(busqueda, null);
        for (PautaPrecio pp : todos) //debe haber solo uno o ninguno
        {
            if (pp.getCost() != null)
                coste = pp.getCost();
        }

        op.setCost(coste);

        FacesUtils.renderResponse();
    }

/* Offer - generated by stajanov (do not edit/delete) */


    /**
     * Logger
     */
    private static final Log log = LogFactory.getLog(OfferBean.class);

    /**
     * Active search object
     */
    private OfferSearch search = new OfferSearch();

    /**
     * Manager
     */
    private static OfferManager manager = OfferManager.getDefault();

    /**
     * Upload service
     */
    private static final Uploader uploader = UploaderFactory.getInstance("offer");


    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    /**
     * Active Offer object
     */
    private Offer offer;

    /**
     * Default sort column
     */
    private String sortColumn = "title";

    /**
     * Default sort order
     */
    private boolean sortAscending = false;

    /**
     * Quick search letter for ABC pager control
     */
    private Character letter;

    /**
     * List offers. Order depends on Faces parameter sort.
     *
     * @return the list of all offers sorted by requested criterion
     */
    public List<Offer> getAll() {
        return manager.getAllEntities(search, new SortCriteria(sortColumn, sortAscending));
    }

    // Getters to list possible values of related entities


    /**
     * Get the list of all users
     *
     * @return the list of all users
     */
    public List<SelectItem> getUsers() {
        List<User> refs = UserManager.getDefault().getAllEntities(null, new SortCriteria("name"));

        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        for (User ref : refs) {
            ret.add(new SelectItem(ref, ref.getName()));
        }
        return ret;
    }


    /**
     * Get the list of all contacts
     *
     * @return the list of all contacts
     */
    public List<SelectItem> getContacts() {
        List<Contact> refs = ContactManager.getDefault().getAllEntities(null, new SortCriteria("name"));

        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        for (Contact ref : refs) {
            ret.add(new SelectItem(ref, ref.getName()));
        }
        return ret;
    }


    /**
     * Get the list of all interactionss
     *
     * @return the list of all interactionss
     */
    public List<SelectItem> getInteractionss() {
        List<Interaction> refs = InteractionManager.getDefault().getAllEntities(null, new SortCriteria("creationDate"));

        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        for (Interaction ref : refs) {
            ret.add(new SelectItem(ref, ref.getWhy()));
        }
        return ret;
    }


    /**
     * Get the list of all roless
     *
     * @return the list of all roless
     */
    public List<SelectItem> getRoless() {
        List<OfferRole> refs = OfferRoleManager.getDefault().getAllEntities(null, new SortCriteria("name"));

        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        for (OfferRole ref : refs) {
            ret.add(new SelectItem(ref, ref.getName()));
        }
        return ret;
    }


    /**
     * Get the list of all ensayoss
     *
     * @return the list of all ensayoss
     */
    public List<SelectItem> getEnsayoss() {
        List<OfferEnsayo> refs = OfferEnsayoManager.getDefault().getAllEntities(null, new SortCriteria("name"));

        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        for (OfferEnsayo ref : refs) {
            ret.add(new SelectItem(ref, ref.getEnsayo().getName()));
        }
        return ret;
    }


    /**
     * Get the list of all pautass
     *
     * @return the list of all pautass
     */
    public List<SelectItem> getPautass() {
        List<OfferPauta> refs = OfferPautaManager.getDefault().getAllEntities(null, new SortCriteria("name"));

        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        for (OfferPauta ref : refs) {
            ret.add(new SelectItem(ref, ref.getPauta().getName()));
        }
        return ret;
    }


    /**
     * Get the list of all pautass
     *
     * @return the list of all pautass
     */
    public List<SelectItem> getPautassBlanco() {
        List<OfferPauta> refs = OfferPautaManager.getDefault().getAllEntities(null, new SortCriteria("name"));

        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        for (OfferPauta ref : refs) {
            ret.add(new SelectItem(ref, ref.getPauta().getName()));
        }
        ret.add(0, new SelectItem(""));
        return ret;
    }


    /**
     * Get the list of all costss
     *
     * @return the list of all costss
     */
    public List<SelectItem> getCostss() {
        List<OfferCost> refs = OfferCostManager.getDefault().getAllEntities(null, new SortCriteria("name"));

        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        for (OfferCost ref : refs) {
            ret.add(new SelectItem(ref, ref.getName()));
        }
        return ret;
    }

    // Getters to list possible values of enum fields

    public String getOfferStateFormatted() {
        if (this.getOfferState() != null) {
            return FacesUtils.formatMessage("OfferState." + this.getOfferState().name());
        } else {
            return "";
        }
    }

    /**
     * Get the list of all OfferState values
     *
     * @return the list of all OfferState values
     */
    public List<SelectItem> getOfferStates() {
        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        OfferState[] vals = OfferState.values();
        for (OfferState val : vals) {
            ret.add(new SelectItem(val, FacesUtils.formatMessage("OfferState." + val.name())));
        }
        return ret;
    }

    public String getOfferPotentialFormatted() {
        if (this.getOfferPotential() != null) {
            return FacesUtils.formatMessage("OfferPotential." + this.getOfferPotential().name());
        } else {
            return "";
        }
    }

    /**
     * Get the list of all OfferPotential values
     *
     * @return the list of all OfferPotential values
     */
    public List<SelectItem> getOfferPotentials() {
        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        OfferPotential[] vals = OfferPotential.values();
        for (OfferPotential val : vals) {
            ret.add(new SelectItem(val, FacesUtils.formatMessage("OfferPotential." + val.name())));
        }
        return ret;
    }

    /**
     * Get the list of all Departament values
     *
     * @return the list of all Department values
     */
    public List<SelectItem> getLineaTrabajos() {
        class comparator implements Comparator<SelectItem> {
            public int compare(SelectItem o1, SelectItem o2) {
                Department este = (Department) o1.getValue();
                Department otro = (Department) o2.getValue();
                return este.getRuta().compareTo(otro.getRuta());
            }
        }
        List<Department> refs = DepartmentManager.getDefault().getAllEntities(null, new SortCriteria("name"));
        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        for (Department ref : refs) {
            ret.add(new SelectItem(ref, ref.getRuta()));
        }
        Collections.sort(ret, new comparator());
        ret.add(0, new SelectItem(""));
        return ret;
    }

    // Methods to create/remove instances of one-to-many entities (slave entities)


    /**
     * Create a new empty instance of the one-to-many field
     *
     * @return forward to the same page
     */
    public String createRoles() {
        OfferRole item = new OfferRole();
        item.setOffer(offer);
        if (offer.getRoles() == null) {
            offer.setRoles(new HashSet<OfferRole>());
        }
        offer.getRoles().add(item);
        return null;
    }

    /**
     * Create a new empty instance of the one-to-many field
     *
     * @return forward to the same page
     */
    public String createEnsayos() {
        OfferEnsayo item = new OfferEnsayo();
        item.setOffer(offer);
        if (offer.getEnsayos() == null) {
            offer.setEnsayos(new ArrayList<OfferEnsayo>());
        }
        offer.getEnsayos().add(item);
        return null;
    }

    /**
     * Create a new empty instance of the one-to-many field
     *
     * @return forward to the same page
     */
    public String createPautas() {
        OfferPauta item = new OfferPauta();
        item.setOffer(offer);
        item.setUnidades(1);
        if (offer.getPautas() == null) {
            offer.setPautas(new ArrayList<OfferPauta>());
        }
        offer.getPautas().add(item);
        return null;
    }

    /**
     * Create a new empty instance of the one-to-many field
     *
     * @return forward to the same page
     */
    public String editRoles() {
        OfferRole item = new OfferRole();
        item.setOffer(offer);
        if (offer.getRoles() == null) {
            offer.setRoles(new HashSet<OfferRole>());
        }
        offer.getRoles().add(item);
        return null;
    }

    /**
     * Create a new empty instance of the one-to-many field
     *
     * @return forward to the same page
     */
    public String editEnsayos() {
        OfferEnsayo item = new OfferEnsayo();
        item.setOffer(offer);
        if (offer.getEnsayos() == null) {
            offer.setEnsayos(new ArrayList<OfferEnsayo>());
        }
        offer.getEnsayos().add(item);
        return null;
    }

    /**
     * Create a new empty instance of the one-to-many field
     *
     * @return forward to the same page
     */
    public String editPautas() {
        OfferPauta item = new OfferPauta();
        item.setOffer(offer);
        if (offer.getPautas() == null) {
            offer.setPautas(new ArrayList<OfferPauta>());
        }
        offer.getPautas().add(item);
        return null;
    }

    /**
     * Delete selected instance of the one-to-many field
     *
     * @return forward to the same page
     */
    public String deleteRoles() {
        UIData table = (UIData) FacesUtils.getComponent("offer:roles");
        offer.getRoles().remove(table.getRowData());
        return null;
    }

    /**
     * Delete selected instance of the one-to-many field
     *
     * @return forward to the same page
     */
    public String deleteEnsayos() {
        UIData table = (UIData) FacesUtils.getComponent("offer:ensayos");
        offer.getEnsayos().remove(table.getRowData());
        return null;
    }

    /**
     * Delete selected instance of the one-to-many field
     *
     * @return forward to the same page
     */
    public String deletePautas() {
        UIData table = (UIData) FacesUtils.getComponent("offer:pautas");
        offer.getPautas().remove(table.getRowData());
        return null;
    }

    private List<SelectItem> ensayosPosibles;

    public List<SelectItem> getEnsayosPosibles() {
        if (ensayosPosibles != null)
            return ensayosPosibles;

        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        List<Ensayo> refs = EnsayoDAO.getDefault().search(null, new SortCriteria("name"));
        for (Ensayo ref : refs) {
            ret.add(new SelectItem(ref, ref.getName()));
        }

        //	Añadiendo el campo vacio
        ret.add(0, new SelectItem(""));

        ensayosPosibles = ret;

        return ret;
    }

    /**
     * Create a new empty instance of the one-to-many field
     *
     * @return forward to the same page
     */
    public String createCosts() {
        OfferCost item = new OfferCost();
        item.setOffer(offer);
        if (offer.getCosts() == null) {
            offer.setCosts(new HashSet<OfferCost>());
        }
        offer.getCosts().add(item);
        return null;
    }

    /**
     * Create a new empty instance of the one-to-many field
     *
     * @return forward to the same page
     */
    public String editCosts() {
        OfferCost item = new OfferCost();
        item.setOffer(offer);
        if (offer.getCosts() == null) {
            offer.setCosts(new HashSet<OfferCost>());
        }
        offer.getCosts().add(item);
        return null;
    }

    /**
     * Delete selected instance of the one-to-many field
     *
     * @return forward to the same page
     */
    public String deleteCosts() {
        UIData table = (UIData) FacesUtils.getComponent("offer:costs");
        offer.getCosts().remove(table.getRowData());
        return null;
    }

    /**
     * Whether or not create button is available for user
     *
     * @return true if user can create objects of type Offer
     */
    public boolean isCreateAvailable() {
        return SpringUtils.isRolePermissionGranted(Permission.Entity_Create(Offer.class));
    }

    /**
     * Whether or not edit button is available for user
     *
     * @return true if user can edit current object
     */
    public boolean isEditAvailable() {
        return SpringUtils.isAclPermissionGranted(offer, BasePermission.WRITE);
    }

    /**
     * Whether or not delete button is available for user
     *
     * @return true if user can delete current object
     */
    public boolean isDeleteAvailable() {
        return (offer.getId() != null) &&
                SpringUtils.isAclPermissionGranted(offer, BasePermission.DELETE);
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

    /**
     * Go to create page
     *
     * @return forward to CREATE page
     */
    public String create() {
        offer = new Offer();
        offer.setUser(SpringUtils.getPrincipal().getUser());
        offer.setIva(new BigDecimal(ConfigurationUtil.getDefault().getIva()));
        offer.setCreationDate(new Date());
        ensayosPosibles = null;
        return NavigationResults.CREATE;
    }

    /**
     * Go to detail page
     *
     * @return forward to DETAIL page
     */
    public String detail() {
        ensayosPosibles = null;
        Integer id = Integer.parseInt(FacesUtils.getRequestParameter("id"));
        offer = manager.getEntityById(id);

        return SpringUtils.isAclPermissionGranted(offer, BasePermission.WRITE)
                ? NavigationResults.EDIT
                : NavigationResults.DETAIL;
    }

    /**
     * Save bean and stay on it
     *
     * @return forward to list page
     */
    public String save() {

        doBeforeSave();

        if (offer.getId() == null) {
            manager.insertEntity(offer);
        } else {
            manager.updateEntity(offer);
        }

        // Calls an after save action
        String result = doAfterSave(NavigationResults.LIST);

        // Unselect object
        //offer = null;

        return null;//quedarse en el mismo sitio
    }

    /**
     * Delete bean and go back to beans list
     *
     * @return forward to LIST page
     */
    public String delete() {
        manager.deleteEntity(offer);
        offer = null;
        return NavigationResults.LIST;
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
    public boolean isOfferSelected() {
        return offer != null;
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
    public OfferSearch getSearch() {
        return search;
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


    public String getSearchTitle() {
        return search.getTitle();
    }

    public void setSearchTitle(String val) {
        if (search.isTitleSet()) {
            search.setTitle(val);
        }
    }

    public boolean isSearchTitleValid() {
        return search.isTitleSet();
    }

    public void setSearchTitleValid(boolean val) {
        if (val) {
            search.setTitle(search.getTitle());
        } else {
            search.unsetTitle();
        }
    }


    public String getSearchDescription() {
        return search.getDescription();
    }

    public void setSearchDescription(String val) {
        if (search.isDescriptionSet()) {
            search.setDescription(val);
        }
    }

    public boolean isSearchDescriptionValid() {
        return search.isDescriptionSet();
    }

    public void setSearchDescriptionValid(boolean val) {
        if (val) {
            search.setDescription(search.getDescription());
        } else {
            search.unsetDescription();
        }
    }


    public String getSearchObservations() {
        return search.getObservations();
    }

    public void setSearchObservations(String val) {
        if (search.isObservationsSet()) {
            search.setObservations(val);
        }
    }

    public boolean isSearchObservationsValid() {
        return search.isObservationsSet();
    }

    public void setSearchObservationsValid(boolean val) {
        if (val) {
            search.setObservations(search.getObservations());
        } else {
            search.unsetObservations();
        }
    }


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


    public Date getSearchStartValidityDate() {
        return search.getStartValidityDate();
    }

    public void setSearchStartValidityDate(Date val) {
        if (val != null) {
            search.setStartValidityDate(val);
        } else {
            search.unsetStartValidityDate();
        }
    }

    public boolean isSearchStartValidityDateValid() {
        return search.isStartValidityDateSet();
    }

    public void setSearchStartValidityDateValid(boolean val) {
        if (val) {
            search.setStartValidityDate(search.getStartValidityDate());
        } else {
            search.unsetStartValidityDate();
        }
    }

    public Date getSearchEndValidityDate() {
        return search.getEndValidityDate();
    }

    public void setSearchEndValidityDate(Date val) {
        if (val != null) {
            search.setEndValidityDate(val);
        } else {
            search.unsetEndValidityDate();
        }
    }

    public boolean isSearchEndValidityDateValid() {
        return search.isEndValidityDateSet();
    }

    public void setSearchEndValidityDateValid(boolean val) {
        if (val) {
            search.setEndValidityDate(search.getEndValidityDate());
        } else {
            search.unsetEndValidityDate();
        }
    }


    public Date getSearchStartMaturityDate() {
        return search.getStartMaturityDate();
    }

    public void setSearchStartMaturityDate(Date val) {
        if (val != null) {
            search.setStartMaturityDate(val);
        } else {
            search.unsetStartMaturityDate();
        }
    }

    public boolean isSearchStartMaturityDateValid() {
        return search.isStartMaturityDateSet();
    }

    public void setSearchStartMaturityDateValid(boolean val) {
        if (val) {
            search.setStartMaturityDate(search.getStartMaturityDate());
        } else {
            search.unsetStartMaturityDate();
        }
    }

    public Date getSearchEndMaturityDate() {
        return search.getEndMaturityDate();
    }

    public void setSearchEndMaturityDate(Date val) {
        if (val != null) {
            search.setEndMaturityDate(val);
        } else {
            search.unsetEndMaturityDate();
        }
    }

    public boolean isSearchEndMaturityDateValid() {
        return search.isEndMaturityDateSet();
    }

    public void setSearchEndMaturityDateValid(boolean val) {
        if (val) {
            search.setEndMaturityDate(search.getEndMaturityDate());
        } else {
            search.unsetEndMaturityDate();
        }
    }


    public OfferState getSearchOfferState() {
        return search.getOfferState();
    }

    public void setSearchOfferState(OfferState val) {
        if (search.isOfferStateSet()) {
            search.setOfferState(val);
        }
    }

    public boolean isSearchOfferStateValid() {
        return search.isOfferStateSet();
    }

    public void setSearchOfferStateValid(boolean val) {
        if (val) {
            search.setOfferState(search.getOfferState());
        } else {
            search.unsetOfferState();
        }
    }


    public OfferPotential getSearchOfferPotential() {
        return search.getOfferPotential();
    }

    public void setSearchOfferPotential(OfferPotential val) {
        if (search.isOfferPotentialSet()) {
            search.setOfferPotential(val);
        }
    }

    public boolean isSearchOfferPotentialValid() {
        return search.isOfferPotentialSet();
    }

    public void setSearchOfferPotentialValid(boolean val) {
        if (val) {
            search.setOfferPotential(search.getOfferPotential());
        } else {
            search.unsetOfferPotential();
        }
    }


    public BigDecimal getSearchTotalRoles() {
        return search.getTotalRoles();
    }

    public void setSearchTotalRoles(BigDecimal val) {
        if (search.isTotalRolesSet()) {
            search.setTotalRoles(val);
        }
    }

    public boolean isSearchTotalRolesValid() {
        return search.isTotalRolesSet();
    }

    public void setSearchTotalRolesValid(boolean val) {
        if (val) {
            search.setTotalRoles(search.getTotalRoles());
        } else {
            search.unsetTotalRoles();
        }
    }


    public BigDecimal getSearchTotalEnsayos() {
        return search.getTotalEnsayos();
    }

    public void setSearchTotalEnsayos(BigDecimal val) {
        if (search.isTotalEnsayosSet()) {
            search.setTotalEnsayos(val);
        }
    }

    public boolean isSearchTotalEnsayosValid() {
        return search.isTotalEnsayosSet();
    }

    public void setSearchTotalEnsayosValid(boolean val) {
        if (val) {
            search.setTotalEnsayos(search.getTotalEnsayos());
        } else {
            search.unsetTotalEnsayos();
        }
    }


    public BigDecimal getSearchTotalCosts() {
        return search.getTotalCosts();
    }

    public void setSearchTotalCosts(BigDecimal val) {
        if (search.isTotalCostsSet()) {
            search.setTotalCosts(val);
        }
    }

    public boolean isSearchTotalCostsValid() {
        return search.isTotalCostsSet();
    }

    public void setSearchTotalCostsValid(boolean val) {
        if (val) {
            search.setTotalCosts(search.getTotalCosts());
        } else {
            search.unsetTotalCosts();
        }
    }


    public BigDecimal getSearchTotal() {
        return search.getTotal();
    }

    public void setSearchTotal(BigDecimal val) {
        if (search.isTotalSet()) {
            search.setTotal(val);
        }
    }

    public boolean isSearchTotalValid() {
        return search.isTotalSet();
    }

    public void setSearchTotalValid(boolean val) {
        if (val) {
            search.setTotal(search.getTotal());
        } else {
            search.unsetTotal();
        }
    }


    public Integer getSearchOwnerId() {
        return search.getOwnerId();
    }

    public void setSearchOwnerId(Integer val) {
        if (search.isOwnerIdSet()) {
            search.setOwnerId(val);
        }
    }

    public boolean isSearchOwnerIdValid() {
        return search.isOwnerIdSet();
    }

    public void setSearchOwnerIdValid(boolean val) {
        if (val) {
            search.setOwnerId(search.getOwnerId());
        } else {
            search.unsetOwnerId();
        }
    }


    public Integer getSearchDepartmentId() {
        return search.getDepartmentId();
    }

    public void setSearchDepartmentId(Integer val) {
        if (search.isDepartmentIdSet()) {
            search.setDepartmentId(val);
        }
    }

    public boolean isSearchDepartmentIdValid() {
        return search.isDepartmentIdSet();
    }

    public void setSearchDepartmentIdValid(boolean val) {
        if (val) {
            search.setDepartmentId(search.getDepartmentId());
        } else {
            search.unsetDepartmentId();
        }
    }


    public Date getSearchStartInsertDate() {
        return search.getStartInsertDate();
    }

    public void setSearchStartInsertDate(Date val) {
        if (val != null) {
            search.setStartInsertDate(val);
        } else {
            search.unsetStartInsertDate();
        }
    }

    public boolean isSearchStartInsertDateValid() {
        return search.isStartInsertDateSet();
    }

    public void setSearchStartInsertDateValid(boolean val) {
        if (val) {
            search.setStartInsertDate(search.getStartInsertDate());
        } else {
            search.unsetStartInsertDate();
        }
    }

    public Date getSearchEndInsertDate() {
        return search.getEndInsertDate();
    }

    public void setSearchEndInsertDate(Date val) {
        if (val != null) {
            search.setEndInsertDate(val);
        } else {
            search.unsetEndInsertDate();
        }
    }

    public boolean isSearchEndInsertDateValid() {
        return search.isEndInsertDateSet();
    }

    public void setSearchEndInsertDateValid(boolean val) {
        if (val) {
            search.setEndInsertDate(search.getEndInsertDate());
        } else {
            search.unsetEndInsertDate();
        }
    }


    public Date getSearchStartUpdateDate() {
        return search.getStartUpdateDate();
    }

    public void setSearchStartUpdateDate(Date val) {
        if (val != null) {
            search.setStartUpdateDate(val);
        } else {
            search.unsetStartUpdateDate();
        }
    }

    public boolean isSearchStartUpdateDateValid() {
        return search.isStartUpdateDateSet();
    }

    public void setSearchStartUpdateDateValid(boolean val) {
        if (val) {
            search.setStartUpdateDate(search.getStartUpdateDate());
        } else {
            search.unsetStartUpdateDate();
        }
    }

    public Date getSearchEndUpdateDate() {
        return search.getEndUpdateDate();
    }

    public void setSearchEndUpdateDate(Date val) {
        if (val != null) {
            search.setEndUpdateDate(val);
        } else {
            search.unsetEndUpdateDate();
        }
    }

    public boolean isSearchEndUpdateDateValid() {
        return search.isEndUpdateDateSet();
    }

    public void setSearchEndUpdateDateValid(boolean val) {
        if (val) {
            search.setEndUpdateDate(search.getEndUpdateDate());
        } else {
            search.unsetEndUpdateDate();
        }
    }


    public User getSearchUser() {
        return search.getUser();
    }

    public void setSearchUser(User val) {
        if (search.isUserSet()) {
            search.setUser(val);
        }
    }

    public boolean isSearchUserValid() {
        return search.isUserSet();
    }

    public void setSearchUserValid(boolean val) {
        if (val) {
            search.setUser(search.getUser());
        } else {
            search.unsetUser();
        }
    }


    public Organization getSearchOrganization() {
        return search.getOrganization();
    }

    public void setSearchOrganization(Organization val) {
        if (search.isOrganizationSet()) {
            search.setOrganization(val);
        }
    }

    public boolean isSearchOrganizationValid() {
        return search.isOrganizationSet();
    }

    public void setSearchOrganizationValid(boolean val) {
        if (val) {
            search.setOrganization(search.getOrganization());
        } else {
            search.unsetOrganization();
        }
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


    public OfferRejectReason getSearchOfferRejectReason() {
        return search.getOfferRejectReason();
    }

    public void setSearchOfferRejectReason(OfferRejectReason val) {
        if (search.isOfferRejectReasonSet()) {
            search.setOfferRejectReason(val);
        }
    }

    public boolean isSearchOfferRejectReasonValid() {
        return search.isOfferRejectReasonSet();
    }

    public void setSearchOfferRejectReasonValid(boolean val) {
        if (val) {
            search.setOfferRejectReason(search.getOfferRejectReason());
        } else {
            search.unsetOfferRejectReason();
        }
    }


    public List<Interaction> getSearchInteractions() {
        return search.getInteractions();
    }

    public void setSearchInteractions(List<Interaction> val) {
        if (search.isInteractionsSet()) {
            search.setInteractions(val);
        }
    }

    public boolean isSearchInteractionsValid() {
        return search.isInteractionsSet();
    }

    public void setSearchInteractionsValid(boolean val) {
        if (val) {
            search.setInteractions(search.getInteractions());
        } else {
            search.unsetInteractions();
        }
    }


    public List<OfferRole> getSearchRoles() {
        return search.getRoles();
    }

    public void setSearchRoles(List<OfferRole> val) {
        if (search.isRolesSet()) {
            search.setRoles(val);
        }
    }

    public boolean isSearchRolesValid() {
        return search.isRolesSet();
    }

    public void setSearchRolesValid(boolean val) {
        if (val) {
            search.setRoles(search.getRoles());
        } else {
            search.unsetRoles();
        }
    }


    public List<OfferEnsayo> getSearchEnsayos() {
        return search.getEnsayos();
    }

    public void setSearchEnsayos(List<OfferEnsayo> val) {
        if (search.isEnsayosSet()) {
            search.setEnsayos(val);
        }
    }

    public boolean isSearchEnsayosValid() {
        return search.isEnsayosSet();
    }

    public void setSearchEnsayosValid(boolean val) {
        if (val) {
            search.setEnsayos(search.getEnsayos());
        } else {
            search.unsetEnsayos();
        }
    }


    public List<OfferCost> getSearchCosts() {
        return search.getCosts();
    }

    public void setSearchCosts(List<OfferCost> val) {
        if (search.isCostsSet()) {
            search.setCosts(val);
        }
    }

    public boolean isSearchCostsValid() {
        return search.isCostsSet();
    }

    public void setSearchCostsValid(boolean val) {
        if (val) {
            search.setCosts(search.getCosts());
        } else {
            search.unsetCosts();
        }
    }

    /**
     * Handle an ABC pager letter click: filter objects by specified starting letter
     */
    public void letterClicked() {
        if (letter != null) {
            UIComponent comp = FacesUtils.getComponent("offers:list");
            HtmlDataTable tabla = (HtmlDataTable) comp;
            tabla.setFirst(0);

            search.setTitle(letter + "%");
        } else {
            search.unsetTitle();
        }
    }

    public Character getLetter() {
        return letter;
    }

    public void setLetter(Character letter) {
        this.letter = letter;
    }

    // Getters and setters to handle uploads

    // Getters and setters to manipulate active Offer object

    public java.lang.Integer getId() {
        return offer.getId();
    }


    public String getNumber() {
        return offer.getNumber();
    }

    public void setNumber(String number) {
        offer.setNumber(number);
    }

    public String getNumberDefault() {
        if (getNumber() == null) {
            int maximo = 0;
            NumberFormat nf = NumberFormat.getInstance();
            nf.setMinimumIntegerDigits(2);
            String regex = "P\\d+/";
            String anio = nf.format(new Date().getYear() - 100);
            regex = regex.concat(anio);
            List<Offer> list = offerDAO.search(null);
            for (Offer o : list) {
                String number = o.getNumber();
                if (number != null && number.matches(regex)) {
                    int x = Integer.parseInt(number.substring(1, number.indexOf('/')));
                    if (x > maximo)
                        maximo = x;
                }
            }
            nf.setMinimumIntegerDigits(3);
            return "P".concat(nf.format(maximo + 1)).concat("/").concat(anio);
        } else {
            return getNumber();
        }
    }

    public void numberValidate(FacesContext context,
                               UIComponent toValidate,
                               Object value) throws ValidatorException {
        if (value == null)
            return;

        String number = (String) value;

        List<Offer> offers = offerDAO.search(null);

        if (offers == null || offers.isEmpty())
            return;

        for (Offer o : offers) {
            if (o.getNumber() != null && o.getNumber().equals(number) && !o.getId().equals(offer.getId())) {
                //((UIInput) toValidate).setValid(false);
                //context.addMessage(toValidate.getClientId(context), new FacesMessage("Ya existe otra oferta con este número"));
                throw new ValidatorException(new FacesMessage("Ya existe otro presupuesto con este número"));
            }
        }
    }

    public void setNumberDefault(String number) {
        setNumber(number);
    }


    public String getOrderNumber() {
        return offer.getOrderNumber();
    }

    public void setOrderNumber(String orderNumber) {
        offer.setOrderNumber(orderNumber);
    }

    public String getTitle() {
        return offer.getTitle();
    }

    public void setTitle(String title) {
        offer.setTitle(title);
    }


    public String getDescription() {
        return offer.getDescription();
    }

    public void setDescription(String description) {
        offer.setDescription(description);
    }


    public String getObservations() {
        return offer.getObservations();
    }

    public void setObservations(String observations) {
        offer.setObservations(observations);
    }


    public Date getCreationDate() {
        return offer.getCreationDate();
    }

    public void setCreationDate(Date creationDate) {
        offer.setCreationDate(creationDate);
    }


    public Date getValidityDate() {
        return offer.getValidityDate();
    }

    public void setValidityDate(Date validityDate) {
        offer.setValidityDate(validityDate);
    }


    public Date getValidityDateDefault() {
        if (getValidityDate() == null) {
            Date ret = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(ret);
            calendar.add(Calendar.DAY_OF_MONTH, 30);
            ret = calendar.getTime();
            return ret;
        } else {
            return getValidityDate();
        }
    }

    public void setValidityDateDefault(Date validityDate) {
        setValidityDate(validityDate);
    }


    public Date getMaturityDate() {
        return offer.getMaturityDate();
    }

    public void setMaturityDate(Date maturityDate) {
        offer.setMaturityDate(maturityDate);
    }


    public OfferState getOfferState() {
        return offer.getOfferState();
    }

    public void setOfferState(OfferState offerState) {
        offer.setOfferState(offerState);
    }


    public OfferPotential getOfferPotential() {
        return offer.getOfferPotential();
    }

    public void setOfferPotential(OfferPotential offerPotential) {
        offer.setOfferPotential(offerPotential);
    }


    public BigDecimal getTotalRoles() {
        return offer.getTotalRoles();
    }

    public void setTotalRoles(BigDecimal totalRoles) {
        offer.setTotalRoles(totalRoles);
    }


    public BigDecimal getTotalEnsayos() {
        return offer.getTotalEnsayos();
    }

    public void setTotalEnsayos(BigDecimal totalEnsayos) {
        offer.setTotalEnsayos(totalEnsayos);
    }


    public BigDecimal getTotalPautas() {
        return offer.getTotalPautas();
    }

    public void setTotalPautas(BigDecimal totalPautas) {
        offer.setTotalPautas(totalPautas);
    }


    public BigDecimal getTotalCosts() {
        return offer.getTotalCosts();
    }

    public void setTotalCosts(BigDecimal totalCosts) {
        offer.setTotalCosts(totalCosts);
    }


    public BigDecimal getTotal() {
        return offer.getTotal();
    }

    public void setTotal(BigDecimal total) {
        offer.setTotal(total);
    }


    public Integer getOwnerId() {
        return offer.getOwnerId();
    }

    public void setOwnerId(Integer ownerId) {
        offer.setOwnerId(ownerId);
    }


    public Integer getDepartmentId() {
        return offer.getDepartmentId();
    }

    public void setDepartmentId(Integer departmentId) {
        offer.setDepartmentId(departmentId);
    }


    public Date getInsertDate() {
        return offer.getInsertDate();
    }

    public void setInsertDate(Date insertDate) {
        offer.setInsertDate(insertDate);
    }


    public Date getUpdateDate() {
        return offer.getUpdateDate();
    }

    public void setUpdateDate(Date updateDate) {
        offer.setUpdateDate(updateDate);
    }


    public User getUser() {
        return offer.getUser();
    }

    public void setUser(User user) {
        offer.setUser(user);
    }


    public Organization getOrganization() {
        return offer.getOrganization();
    }

    public void setOrganization(Organization organization) {
        offer.setOrganization(organization);
    }


    public Contact getContact() {
        return offer.getContact();
    }

    public void setContact(Contact contact) {
        offer.setContact(contact);
    }


    public OfferRejectReason getOfferRejectReason() {
        return offer.getOfferRejectReason();
    }

    public void setOfferRejectReason(OfferRejectReason offerRejectReason) {
        offer.setOfferRejectReason(offerRejectReason);
    }


    public Set<Interaction> getInteractions() {
        return offer.getInteractions();
    }

    public void setInteractions(Set<Interaction> interactions) {
        offer.setInteractions(interactions);
    }


    public Set<OfferRole> getRoles() {
        return offer.getRoles();
    }

    public void setRoles(Set<OfferRole> roles) {
        offer.setRoles(roles);
    }


    public List<OfferEnsayo> getEnsayos() {
        return offer.getEnsayos();
    }

    public void setEnsayos(List<OfferEnsayo> ensayos) {
        offer.setEnsayos(ensayos);
    }


    public List<OfferPauta> getPautas() {
        return offer.getPautas();
    }

    public void setPautas(List<OfferPauta> pautas) {
        offer.setPautas(pautas);
    }


    public Set<OfferCost> getCosts() {
        return offer.getCosts();
    }

    public void setCosts(Set<OfferCost> costs) {
        offer.setCosts(costs);
    }

    public BigDecimal getIva() {
        return offer.getIva();
    }

    public void setIva(BigDecimal iva) {
        offer.setIva(iva);
    }

/* Offer - generated by stajanov (do not edit/delete) */


    /**
     * Get the list of all organizations
     *
     * @return the list of all organizations
     */
    public List<SelectItem> getOrganizations() {
        List<Organization> refs = OrganizationManager.getDefault().getAllEntities(null, new SortCriteria("name"));

        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        for (Organization ref : refs) {
            ret.add(new SelectItem(ref, ref.getName() + (ref.getAcronimo() == null || ref.getAcronimo().equals("") ? "" : " (" + ref.getAcronimo() + ")")));
        }

        ret.add(0, new SelectItem(""));

        return ret;
    }


    /**
     * Selected organization *
     */


    public List<SelectItem> getContactsBySelectedOrganization() {
        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();


        ContactSearch cs = new ContactSearch();
        cs.setOrganization(getOrganization());
        List<Contact> refs = ContactManager.getDefault().getAllEntities(cs, new SortCriteria("name"));
        for (Contact ref : refs) {
            ret.add(new SelectItem(ref, ref.getName()));
        }

        //	Añadiendo el campo vacio
        ret.add(0, new SelectItem(""));

        return ret;
    }

    public void onSelectedOrganizationChanged(ValueChangeEvent event) {
        setOrganization((Organization) event.getNewValue());

        InteractionBean ib = (InteractionBean) FacesUtils.getBean("interactionBean");
        ib.setSelectedOrganization(getOrganization());

        FacesUtils.renderResponse();
    }

    /**
     * Get the list of all offerRejectReasons
     *
     * @return the list of all offerRejectReasons
     */
    public List<SelectItem> getOfferRejectReasons() {
        List<OfferRejectReason> refs = OfferRejectReasonManager.getDefault().getAllEntities(null, new SortCriteria("title"));

        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        for (OfferRejectReason ref : refs) {
            ret.add(new SelectItem(ref, ref.getTitle()));
        }

        //	Añadiendo el campo vacio
        ret.add(0, new SelectItem(""));
        return ret;
    }


    /**
     * Create a new empty instance of the one-to-many field
     *
     * @return forward to the same page
     */
    public String createInteractions() {
        Interaction item = new Interaction();
        item.setOffer(offer);
        if (item.getUser() == null) {
            item.setUser(SpringUtils.getPrincipal().getUser());
        }
        item.setCreationDate(new Date());

        InteractionBean iB = (InteractionBean) FacesUtils.getBean("interactionBean");
        iB.setInteractionFromOffer(item);
        iB.setSelectedOrganization(getOrganization());

        if (offer.getInteractions() == null) {
            offer.setInteractions(new HashSet<Interaction>());
        }
        offer.getInteractions().add(item);


        return NavigationResults.OFFER_CREATE_INTERACTION;
    }

    /**
     * Create a new empty instance of the one-to-many field
     *
     * @return forward to the same page
     */
    public String editInteractions() {

        UIData table = (UIData) FacesUtils.getComponent("offer:interactions");
        InteractionBean iB = (InteractionBean) FacesUtils.getBean("interactionBean");
        iB.setInteractionFromOffer((Interaction) table.getRowData());
        iB.setSelectedOrganization(getOrganization());

        return NavigationResults.OFFER_CREATE_INTERACTION;
    }

    /**
     * Delete selected instance of the one-to-many field
     *
     * @return forward to the same page
     */
    public String deleteInteractions() {
        UIData table = (UIData) FacesUtils.getComponent("offer:interactions");
        offer.getInteractions().remove(table.getRowData());
        Integer idActual = offer.getId();
        save();
        offer = OfferManager.getDefault().getEntityById(idActual);
        return null;
    }

    public boolean getPuedoCrearProyecto() {
        return offer.getId() != null;
    }

    public boolean getPuedoImprimirOferta() {
        return offer.getId() != null;
    }

    private Integer idSelectedOrganization = 0;

    public Integer getIdSelectedOrganization() {

        idSelectedOrganization = ConfigurationUtil.getDefault().getIdOurCompany();
        return idSelectedOrganization;
    }

    public void setIdSelectedOrganization(Integer idOrganization) {
        idSelectedOrganization = idOrganization;
    }

    public boolean isInteractionsAvailable() {
        return (offer.getId() != null);
    }

    public String detail_desde_fuera() {
        return "offer_" + detail();
    }
}
