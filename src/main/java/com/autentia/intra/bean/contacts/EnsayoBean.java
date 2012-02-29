package com.autentia.intra.bean.contacts;

import com.autentia.intra.bean.BaseBean;
import com.autentia.intra.bean.NavigationResults;
import com.autentia.intra.businessobject.Ensayo;
import com.autentia.intra.businessobject.EnsayoProcedimiento;
import com.autentia.intra.dao.SortCriteria;
import com.autentia.intra.dao.search.EnsayoSearch;
import com.autentia.intra.manager.contacts.EnsayoManager;
import com.autentia.intra.manager.security.Permission;
import com.autentia.intra.upload.Uploader;
import com.autentia.intra.upload.UploaderFactory;
import com.autentia.intra.util.FacesUtils;
import com.autentia.intra.util.SpringUtils;
import org.acegisecurity.acls.domain.BasePermission;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.faces.component.UIComponent;
import javax.faces.component.UIData;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.model.SelectItem;
import java.math.BigDecimal;
import java.util.*;

public class EnsayoBean extends BaseBean {
    public String deleteProcedimientos() {
        UIData table = (UIData) FacesUtils.getComponent("ensayo:procedimientos");
        ensayo.getProcedimientos().remove(table.getRowData());
        return null;
    }

    public Set<EnsayoProcedimiento> getProcedimientos() {
        return ensayo.getProcedimientos();
    }

    public void setProcedimientos(Set<EnsayoProcedimiento> procedimientos) {
        ensayo.setProcedimientos(procedimientos);
    }

    public String createProcedimientos() {
        EnsayoProcedimiento item = new EnsayoProcedimiento();
        item.setEnsayoId(ensayo);
        if (ensayo.getProcedimientos() == null) {
            ensayo.setProcedimientos(new HashSet<EnsayoProcedimiento>());
        }
        ensayo.getProcedimientos().add(item);
        return null;
    }

    public String editProcedimientos() {
        EnsayoProcedimiento item = new EnsayoProcedimiento();
        if (ensayo.getProcedimientos() == null) {
            ensayo.setProcedimientos(new HashSet<EnsayoProcedimiento>());
        }
        ensayo.getProcedimientos().add(item);
        return null;
    }


    public List<SelectItem> getEnsayos() {
        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        List<Ensayo> refs = getAllAlfabetico();
        for (Ensayo ref : refs) {
            ret.add(new SelectItem(ref, ref.getName()));
        }

        //	Añadiendo el campo vacio
        ret.add(0, new SelectItem(""));

        return ret;
    }


    /**
     * Logger
     */
    private static final Log log = LogFactory.getLog(EnsayoBean.class);

    /**
     * Active search object
     */
    private EnsayoSearch search = new EnsayoSearch();

    /**
     * Manager
     */
    private static EnsayoManager manager = EnsayoManager.getDefault();

    /**
     * Upload service
     */
    private static final Uploader uploader = UploaderFactory.getInstance("ensayo");


    /**
     * Active Ensayo object
     */
    private Ensayo ensayo;

    /**
     * Default sort column
     */
    private String sortColumn = "name";

    /**
     * Default sort order
     */
    private boolean sortAscending = true;

    /**
     * Quick search letter for ABC pager control
     */
    private Character letter;

    /**
     * List ensayos. Order depends on Faces parameter sort.
     *
     * @return the list of all ensayos sorted by requested criterion
     */
    public List<Ensayo> getAll() {
        return manager.getAllEntities(search, new SortCriteria(sortColumn, sortAscending));
    }

    public List<Ensayo> getAllAlfabetico() {
        return manager.getAllEntities(null, new SortCriteria("name", true));
    }

    // Getters to list possible values of related entities

    // Getters to list possible values of enum fields

    // Methods to create/remove instances of one-to-many entities (slave entities)

    /**
     * Whether or not create button is available for user
     *
     * @return true if user can create objects of type Ensayo
     */
    public boolean isCreateAvailable() {
        return SpringUtils.isRolePermissionGranted(Permission.Entity_Create(Ensayo.class));
    }

    /**
     * Whether or not edit button is available for user
     *
     * @return true if user can edit current object
     */
    public boolean isEditAvailable() {
        return SpringUtils.isAclPermissionGranted(ensayo, BasePermission.WRITE);
    }

    /**
     * Whether or not delete button is available for user
     *
     * @return true if user can delete current object
     */
    public boolean isDeleteAvailable() {
        return (ensayo.getId() != null) &&
                SpringUtils.isAclPermissionGranted(ensayo, BasePermission.DELETE);
    }

    /**
     * Go to create page
     *
     * @return forward to CREATE page
     */
    public String create() {
        ensayo = new Ensayo();

        return NavigationResults.CREATE;
    }

    /**
     * Go to detail page
     *
     * @return forward to DETAIL page
     */
    public String detail() {
        Integer id = Integer.parseInt(FacesUtils.getRequestParameter("id"));
        ensayo = manager.getEntityById(id);

        return SpringUtils.isAclPermissionGranted(ensayo, BasePermission.WRITE)
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

        if (ensayo.getId() == null) {
            manager.insertEntity(ensayo);
        } else {
            manager.updateEntity(ensayo);
        }

        // Calls an after save action
        String result = doAfterSave(NavigationResults.LIST);

        // Unselect object
        //ensayo = null;

        return null;
    }

    /**
     * Delete bean and go back to beans list
     *
     * @return forward to LIST page
     */
    public String delete() {
        manager.deleteEntity(ensayo);
        ensayo = null;
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
     * Reset search criteria
     *
     * @return forward to LIST page
     */
    public String reset() {
        search.reset();
        return list();
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
    public boolean isEnsayoSelected() {
        return ensayo != null;
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
    public EnsayoSearch getSearch() {
        return search;
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


    public BigDecimal getSearchCost() {
        return search.getCost();
    }

    public void setSearchCost(BigDecimal val) {
        if (search.isCostSet()) {
            search.setCost(val);
        }
    }

    public boolean isSearchCostValid() {
        return search.isCostSet();
    }

    public void setSearchCostValid(boolean val) {
        if (val) {
            search.setCost(search.getCost());
        } else {
            search.unsetCost();
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


    /**
     * Handle an ABC pager letter click: filter objects by specified starting letter
     */
    public void letterClicked() {
        if (letter != null) {
            UIComponent comp = FacesUtils.getComponent("ensayos:list");
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

    // Getters and setters to handle uploads

    // Getters and setters to manipulate active Ensayo object

    public java.lang.Integer getId() {
        return ensayo.getId();
    }


    public String getName() {
        return ensayo.getName();
    }

    public void setName(String name) {
        ensayo.setName(name);
    }


    public String getNameIngles() {
        return ensayo.getNameIngles();
    }

    public void setNameIngles(String name) {
        ensayo.setNameIngles(name);
    }


    public String getDescription() {
        return ensayo.getDescription();
    }

    public void setDescription(String description) {
        ensayo.setDescription(description);
    }


    public BigDecimal getCost() {
        return ensayo.getCost();
    }

    public void setCost(BigDecimal cost) {
        ensayo.setCost(cost);
    }

    public Integer getOwnerId() {
        return ensayo.getOwnerId();
    }

    public void setOwnerId(Integer ownerId) {
        ensayo.setOwnerId(ownerId);
    }


    public Integer getDepartmentId() {
        return ensayo.getDepartmentId();
    }

    public void setDepartmentId(Integer departmentId) {
        ensayo.setDepartmentId(departmentId);
    }


    public Date getInsertDate() {
        return ensayo.getInsertDate();
    }

    public void setInsertDate(Date insertDate) {
        ensayo.setInsertDate(insertDate);
    }


    public Date getUpdateDate() {
        return ensayo.getUpdateDate();
    }

    public void setUpdateDate(Date updateDate) {
        ensayo.setUpdateDate(updateDate);
    }


    public Ensayo getEnsayo() {
        return ensayo;
    }

    public void setEnsayo(Ensayo ensayo) {
        this.ensayo = ensayo;
    }
}
