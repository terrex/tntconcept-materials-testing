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
package com.autentia.intra.bean.document;

import com.autentia.intra.bean.BaseBean;
import com.autentia.intra.bean.NavigationResults;
import com.autentia.intra.businessobject.DocumentCategory;
import com.autentia.intra.dao.SortCriteria;
import com.autentia.intra.dao.search.DocumentCategorySearch;
import com.autentia.intra.manager.document.DocumentCategoryManager;
import com.autentia.intra.manager.security.Permission;
import com.autentia.intra.upload.Uploader;
import com.autentia.intra.upload.UploaderFactory;
import com.autentia.intra.util.ConfigurationUtil;
import com.autentia.intra.util.FacesUtils;
import com.autentia.intra.util.SpringUtils;
import org.acegisecurity.acls.domain.BasePermission;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * UI bean for BulletinBoard objects.
 *
 * @author stajanov code generator
 */
public class DocumentCategoryBean extends BaseBean {
    /**
     * Serial version field
     */
    private static final long serialVersionUID = -1L;

/* documentCategory - generated by stajanov (do not edit/delete) */


    /**
     * Logger
     */
    private static final Log log = LogFactory.getLog(DocumentCategoryBean.class);

    /**
     * Active search object
     */
    private DocumentCategorySearch search = new DocumentCategorySearch();

    /**
     * Manager
     */
    private static DocumentCategoryManager manager = DocumentCategoryManager.getDefault();

    /**
     * Upload service
     */
    private static final Uploader uploader = UploaderFactory.getInstance("documentCategory");


    /**
     * Active DocumentCategory object
     */
    private DocumentCategory documentCategory;

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
     * List documentCategorys. Order depends on Faces parameter sort.
     *
     * @return the list of all documentCategorys sorted by requested criterion
     */
    public List<DocumentCategory> getAll() {
        return manager.getAllEntities(search, new SortCriteria(sortColumn, sortAscending));
    }

    // Getters to list possible values of related entities


    /**
     * Get the list of all padres
     *
     * @return the list of all padres
     */
    public List<SelectItem> getPadres() {
        List<DocumentCategory> refs = DocumentCategoryManager.getDefault().getAllEntities(null, new SortCriteria("name"));

        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        for (DocumentCategory ref : refs) {
            ret.add(new SelectItem(ref, ref.getName()));
        }
        return ret;
    }

    // Getters to list possible values of enum fields

    // Methods to create/remove instances of one-to-many entities (slave entities)

    /**
     * Whether or not create button is available for user
     *
     * @return true if user can create objects of type DocumentCategory
     */
    public boolean isCreateAvailable() {
        return SpringUtils.isRolePermissionGranted(Permission.Entity_Create(DocumentCategory.class));
    }

    /**
     * Whether or not edit button is available for user
     *
     * @return true if user can edit current object
     */
    public boolean isEditAvailable() {
        return SpringUtils.isAclPermissionGranted(documentCategory, BasePermission.WRITE);
    }

    /**
     * Whether or not delete button is available for user
     *
     * @return true if user can delete current object
     */
    public boolean isDeleteAvailable() {
        return (documentCategory.getId() != null) &&
                SpringUtils.isAclPermissionGranted(documentCategory, BasePermission.DELETE);
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
        documentCategory = new DocumentCategory();


        return NavigationResults.CREATE;
    }

    /**
     * Go to detail page
     *
     * @return forward to DETAIL page
     */
    public String detail() {
        Integer id = Integer.parseInt(FacesUtils.getRequestParameter("id"));
        documentCategory = manager.getEntityById(id);

        return SpringUtils.isAclPermissionGranted(documentCategory, BasePermission.WRITE)
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

        if (documentCategory.getId() == null) {
            manager.insertEntity(documentCategory);
        } else {
            manager.updateEntity(documentCategory);
        }

        // Calls an after save action
        String result = doAfterSave(NavigationResults.LIST);

        // Unselect object
        documentCategory = null;

        return result;
    }

    /**
     * Delete bean and go back to beans list
     *
     * @return forward to LIST page
     */
    public String delete() {
        manager.deleteEntity(documentCategory);
        documentCategory = null;
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
    public boolean isDocumentCategorySelected() {
        return documentCategory != null;
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
    public DocumentCategorySearch getSearch() {
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


    public String getSearchCode() {
        return search.getCode();
    }

    public void setSearchCode(String val) {
        if (search.isCodeSet()) {
            search.setCode(val);
        }
    }

    public boolean isSearchCodeValid() {
        return search.isCodeSet();
    }

    public void setSearchCodeValid(boolean val) {
        if (val) {
            search.setCode(search.getCode());
        } else {
            search.unsetCode();
        }
    }


    public Date getSearchStartDocumentsLastUpdate() {
        return search.getStartDocumentsLastUpdate();
    }

    public void setSearchStartDocumentsLastUpdate(Date val) {
        if (val != null) {
            search.setStartDocumentsLastUpdate(val);
        } else {
            search.unsetStartDocumentsLastUpdate();
        }
    }

    public boolean isSearchStartDocumentsLastUpdateValid() {
        return search.isStartDocumentsLastUpdateSet();
    }

    public void setSearchStartDocumentsLastUpdateValid(boolean val) {
        if (val) {
            search.setStartDocumentsLastUpdate(search.getStartDocumentsLastUpdate());
        } else {
            search.unsetStartDocumentsLastUpdate();
        }
    }

    public Date getSearchEndDocumentsLastUpdate() {
        return search.getEndDocumentsLastUpdate();
    }

    public void setSearchEndDocumentsLastUpdate(Date val) {
        if (val != null) {
            search.setEndDocumentsLastUpdate(val);
        } else {
            search.unsetEndDocumentsLastUpdate();
        }
    }

    public boolean isSearchEndDocumentsLastUpdateValid() {
        return search.isEndDocumentsLastUpdateSet();
    }

    public void setSearchEndDocumentsLastUpdateValid(boolean val) {
        if (val) {
            search.setEndDocumentsLastUpdate(search.getEndDocumentsLastUpdate());
        } else {
            search.unsetEndDocumentsLastUpdate();
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


    public DocumentCategory getSearchPadre() {
        return search.getPadre();
    }

    public void setSearchPadre(DocumentCategory val) {
        if (search.isPadreSet()) {
            search.setPadre(val);
        }
    }

    public boolean isSearchPadreValid() {
        return search.isPadreSet();
    }

    public void setSearchPadreValid(boolean val) {
        if (val) {
            search.setPadre(search.getPadre());
        } else {
            search.unsetPadre();
        }
    }

    /**
     * Handle an ABC pager letter click: filter objects by specified starting letter
     */
    public void letterClicked() {
        if (letter != null) {
            UIComponent comp = FacesUtils.getComponent("documentCategorys:list");
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

    // Getters and setters to manipulate active DocumentCategory object

    public java.lang.Integer getId() {
        return documentCategory.getId();
    }


    public String getName() {
        return documentCategory.getName();
    }

    public void setName(String name) {
        documentCategory.setName(name);
    }


    public String getDescription() {
        return documentCategory.getDescription();
    }

    public void setDescription(String description) {
        documentCategory.setDescription(description);
    }


    public String getCode() {
        return documentCategory.getCode();
    }

    public void setCode(String code) {
        documentCategory.setCode(code);
    }


    public Date getDocumentsLastUpdate() {
        return documentCategory.getDocumentsLastUpdate();
    }

    public void setDocumentsLastUpdate(Date documentsLastUpdate) {
        documentCategory.setDocumentsLastUpdate(documentsLastUpdate);
    }


    public Integer getOwnerId() {
        return documentCategory.getOwnerId();
    }

    public void setOwnerId(Integer ownerId) {
        documentCategory.setOwnerId(ownerId);
    }


    public Integer getDepartmentId() {
        return documentCategory.getDepartmentId();
    }

    public void setDepartmentId(Integer departmentId) {
        documentCategory.setDepartmentId(departmentId);
    }


    public Date getInsertDate() {
        return documentCategory.getInsertDate();
    }

    public void setInsertDate(Date insertDate) {
        documentCategory.setInsertDate(insertDate);
    }


    public Date getUpdateDate() {
        return documentCategory.getUpdateDate();
    }

    public void setUpdateDate(Date updateDate) {
        documentCategory.setUpdateDate(updateDate);
    }


    public DocumentCategory getPadre() {
        return documentCategory.getPadre();
    }

    public void setPadre(DocumentCategory padre) {
        documentCategory.setPadre(padre);
    }

/* documentCategory - generated by stajanov (do not edit/delete) */

    /**
     * Go to documents page
     *
     * @return forward to documents page
     */
    public String documents() {
        Integer id = Integer.parseInt(FacesUtils.getRequestParameter("id"));
        documentCategory = manager.getEntityById(id);
        DocumentBean documentBean = (DocumentBean) FacesUtils.getBean("documentBean");
        List<DocumentCategory> categories = new ArrayList<DocumentCategory>();
        categories.add(documentCategory);
        documentBean.setSearchCategoriesValid(true);
        documentBean.setSearchCategories(categories);
        return "documents";
    }

    public DocumentCategoryBean() {
        super();

        this.getSearch().setPadre(DocumentCategoryManager.getDefault().getEntityById(ConfigurationUtil.getDefault().getQualityDocumentCategoryId()));
    }

    /**
     * Get the list of all padres
     *
     * @return the list of all padres
     */
    public List<SelectItem> getPadresWithoutMe() {

        search.setDifferentId(documentCategory.getId());
        List<DocumentCategory> refs = manager.getAllEntities(search, new SortCriteria("name"));

        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();

        for (DocumentCategory ref : refs) {
            ret.add(new SelectItem(ref, ref.getName()));
        }
        return ret;

    }

}