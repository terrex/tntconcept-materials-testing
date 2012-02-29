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
import com.autentia.intra.businessobject.Offer;
import com.autentia.intra.businessobject.OfferPauta;
import com.autentia.intra.businessobject.Pauta;
import com.autentia.intra.dao.SortCriteria;
import com.autentia.intra.dao.search.OfferPautaSearch;
import com.autentia.intra.manager.contacts.OfferManager;
import com.autentia.intra.manager.contacts.OfferPautaManager;
import com.autentia.intra.manager.contacts.PautaManager;
import com.autentia.intra.manager.security.Permission;
import com.autentia.intra.upload.Uploader;
import com.autentia.intra.upload.UploaderFactory;
import com.autentia.intra.util.FacesUtils;
import com.autentia.intra.util.SpringUtils;
import org.acegisecurity.acls.domain.BasePermission;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.faces.model.SelectItem;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * UI bean for OfferPauta objects.
 *
 * @author stajanov code generator
 */
public class OfferPautaBean extends BaseBean {
    /**
     * Serial version field
     */
    private static final long serialVersionUID = -1L;

/* offerPauta - generated by stajanov (do not edit/delete) */

    /**
     * Logger
     */
    private static final Log log = LogFactory.getLog(OfferPautaBean.class);

    /**
     * Active search object
     */
    private OfferPautaSearch search = new OfferPautaSearch();

    /**
     * Manager
     */
    private static OfferPautaManager manager = OfferPautaManager.getDefault();

    /**
     * Upload service
     */
    private static final Uploader uploader = UploaderFactory.getInstance("offerPauta");


    /**
     * Active OfferPauta object
     */
    private OfferPauta offerPauta;

    /**
     * Default sort column
     */
    private String sortColumn = "id";

    /**
     * Default sort order
     */
    private boolean sortAscending = true;

    /**
     * Quick search letter for ABC pager control
     */
    private Character letter;

    /**
     * List offerPautas. Order depends on Faces parameter sort.
     *
     * @return the list of all offerPautas sorted by requested criterion
     */
    public List<OfferPauta> getAll() {
        return manager.getAllEntities(search, new SortCriteria(sortColumn, sortAscending));
    }

    // Getters to list possible values of related entities


    /**
     * Get the list of all offers
     *
     * @return the list of all offers
     */
    public List<SelectItem> getOffers() {
        List<Offer> refs = OfferManager.getDefault().getAllEntities(null, new SortCriteria("title"));

        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        for (Offer ref : refs) {
            ret.add(new SelectItem(ref, ref.getTitle()));
        }
        return ret;
    }

    /**
     * Get the list of all pautas
     *
     * @return the list of all pautas
     */
    public List<SelectItem> getPautas() {
        List<Pauta> refs = PautaManager.getDefault().getAllEntities(null, new SortCriteria("name"));

        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        for (Pauta ref : refs) {
            ret.add(new SelectItem(ref, ref.getName()));
        }
        return ret;
    }

    // Getters to list possible values of enum fields

    // Methods to create/remove instances of one-to-many entities (slave entities)


    /**
     * Whether or not create button is available for user
     *
     * @return true if user can create objects of type OfferPauta
     */
    public boolean isCreateAvailable() {
        return SpringUtils.isRolePermissionGranted(Permission.Entity_Create(OfferPauta.class));
    }

    /**
     * Whether or not edit button is available for user
     *
     * @return true if user can edit current object
     */
    public boolean isEditAvailable() {
        return SpringUtils.isAclPermissionGranted(offerPauta, BasePermission.WRITE);
    }

    /**
     * Whether or not delete button is available for user
     *
     * @return true if user can delete current object
     */
    public boolean isDeleteAvailable() {
        return (offerPauta.getId() != null) &&
                SpringUtils.isAclPermissionGranted(offerPauta, BasePermission.DELETE);
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
        offerPauta = new OfferPauta();
        return NavigationResults.CREATE;
    }

    /**
     * Go to detail page
     *
     * @return forward to DETAIL page
     */
    public String detail() {
        Integer id = Integer.parseInt(FacesUtils.getRequestParameter("id"));
        offerPauta = manager.getEntityById(id);

        return SpringUtils.isAclPermissionGranted(offerPauta, BasePermission.WRITE)
                ? NavigationResults.EDIT
                : NavigationResults.DETAIL;
    }

    /**
     * Save bean and stay on it
     *
     * @return forward to list page
     */
    public String save() {
        ((OfferBean) FacesUtils.getBean("offerBean")).save();

        doBeforeSave();

        if (offerPauta.getId() == null) {
            manager.insertEntity(offerPauta);
        } else {
            manager.updateEntity(offerPauta);
        }

        // Calls an after save action
        String result = doAfterSave(NavigationResults.LIST);

        // Unselect object
        //offerPauta = null;

        return null;
    }

    /**
     * Delete bean and go back to beans list
     *
     * @return forward to LIST page
     */
    public String delete() {
        manager.deleteEntity(offerPauta);
        offerPauta = null;
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
    public boolean isOfferPautaSelected() {
        return offerPauta != null;
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
    public OfferPautaSearch getSearch() {
        return search;
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

    public Date getSearchStartUpdateDate() {
        return search.getStartUpdateDate();
    }

    public void setSearchStartUpdateDate(Date val) {
        if (search.isStartUpdateDateSet()) {
            search.setStartUpdateDate(val);
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
        if (search.isEndUpdateDateSet()) {
            search.setEndUpdateDate(val);
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

    public Offer getSearchOffer() {
        return search.getOffer();
    }

    public void setSearchOffer(Offer val) {
        if (search.isOfferSet()) {
            search.setOffer(val);
        }
    }

    public boolean isSearchOfferValid() {
        return search.isOfferSet();
    }

    public void setSearchOfferValid(boolean val) {
        if (val) {
            search.setOffer(search.getOffer());
        } else {
            search.unsetOffer();
        }
    }

    public Integer getSearchUnidades() {
        return search.getUnidades();
    }

    public void setSearchUnidades(Integer val) {
        if (search.isUnidadesSet()) {
            search.setUnidades(val);
        }
    }

    public boolean isSearchUnidadesValid() {
        return search.isUnidadesSet();
    }

    public void setSearchUnidadesValid(boolean val) {
        if (val) {
            search.setUnidades(search.getUnidades());
        } else {
            search.unsetUnidades();
        }
    }

    public Date getSearchStartInsertDate() {
        return search.getStartInsertDate();
    }

    public void setSearchStartInsertDate(Date val) {
        if (search.isStartInsertDateSet()) {
            search.setStartInsertDate(val);
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
        if (search.isEndInsertDateSet()) {
            search.setEndInsertDate(val);
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

    public Pauta getSearchPauta() {
        return search.getPauta();
    }

    public void setSearchPauta(Pauta val) {
        if (search.isPautaSet()) {
            search.setPauta(val);
        }
    }

    public boolean isSearchPautaValid() {
        return search.isPautaSet();
    }

    public void setSearchPautaValid(boolean val) {
        if (val) {
            search.setPauta(search.getPauta());
        } else {
            search.unsetPauta();
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


    /**
     * Handle an ABC pager letter click: filter objects by specified starting letter
     */
    public void letterClicked() {
    }

    public Character getLetter() {
        return letter;
    }

    public void setLetter(Character letter) {
        this.letter = letter;
    }

    // Getters and setters to handle uploads

    // Getters and setters to manipulate active OfferPauta object

    public BigDecimal getCost() {
        return offerPauta.getCost();
    }

    public void setCost(BigDecimal cost) {
        offerPauta.setCost(cost);
    }

    public Integer getDepartmentId() {
        return offerPauta.getDepartmentId();
    }

    public void setDepartmentId(Integer departmentId) {
        offerPauta.setDepartmentId(departmentId);
    }

    public Date getUpdateDate() {
        return offerPauta.getUpdateDate();
    }

    public void setUpdateDate(Date updateDate) {
        offerPauta.setUpdateDate(updateDate);
    }

    public Offer getOffer() {
        return offerPauta.getOffer();
    }

    public void setOffer(Offer offer) {
        offerPauta.setOffer(offer);
    }

    public Integer getUnidades() {
        return offerPauta.getUnidades();
    }

    public void setUnidades(Integer unidades) {
        offerPauta.setUnidades(unidades);
    }

    public Date getInsertDate() {
        return offerPauta.getInsertDate();
    }

    public void setInsertDate(Date insertDate) {
        offerPauta.setInsertDate(insertDate);
    }

    public Pauta getPauta() {
        return offerPauta.getPauta();
    }

    public void setPauta(Pauta pauta) {
        offerPauta.setPauta(pauta);
    }

    public Integer getId() {
        return offerPauta.getId();
    }

    public Integer getOwnerId() {
        return offerPauta.getOwnerId();
    }

    public void setOwnerId(Integer ownerId) {
        offerPauta.setOwnerId(ownerId);
    }

/* offerPauta - generated by stajanov (do not edit/delete) */

    public String doBeforeSave() {
        return null;
    }

}
