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
import com.autentia.intra.dao.search.OrganizationSearch;
import com.autentia.intra.dao.search.PautaSearch;
import com.autentia.intra.dao.search.ProductoPrecioSearch;
import com.autentia.intra.manager.admin.ProvinceManager;
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

import javax.faces.component.UIComponent;
import javax.faces.component.UIData;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.model.SelectItem;
import java.util.*;

/**
 * UI bean for Organization objects.
 *
 * @author stajanov code generator
 */
public class OrganizationBean extends BaseBean {
    private EnsayoManager ensayoManager = EnsayoManager.getDefault();


    public String duplicar() {
        Integer id = Integer.parseInt(FacesUtils.getRequestParameter("id"));
        Organization original = manager.getEntityById(id);
        organization = original.clone();
        organization.setName(organization.getName() + " (copia)");
        manager.insertEntity(organization);
        return NavigationResults.EDIT;
    }

    public String savePrecios() {
        if (isEsCliente()) {
            save();
        }

        if (isEsProveedor()) {
            ProductoPrecioManager productoPrecioManager = ProductoPrecioManager.getDefault();
            for (ProductoPrecio pp : this.productoPrecios) {
                if (pp.getCost() == null
                        || (pp.getProducto() == null || pp.getProducto().length() == 0)) {
                    if (pp.getId() != null) {
                        productoPrecioManager.deleteEntity(pp);
                    }
                } else {
                    if (pp.getId() == null) {
                        productoPrecioManager.insertEntity(pp);
                    } else {
                        productoPrecioManager.updateEntity(pp);
                    }
                }
            }
        }
        return NavigationResults.LIST;
    }

    public List<EnsayoPrecio> getEnsayoPrecios() {
        List<EnsayoPrecio> result = new ArrayList<EnsayoPrecio>(organization.getEnsayoPrecios());
        Collections.sort(result, new Comparator<EnsayoPrecio>() {
            public int compare(EnsayoPrecio o1, EnsayoPrecio o2) {
                if (o1.getEnsayo() == null) {
                    return 1;
                }
                if (o2.getEnsayo() == null) {
                    return -1;
                }
                try {
                    return o1.getEnsayo().getName().compareTo(o2.getEnsayo().getName());
                } catch (Exception NullPointerException) {
                    return 0;
                }
            }
        });
        return result;
    }

    public void setEnsayoPrecios(List<EnsayoPrecio> ensayoPrecios) {
        organization.setEnsayoPrecios(new HashSet<EnsayoPrecio>(ensayoPrecios));
    }

    public List<SelectItem> getMisPautas() {
        PautaManager pautaManager = PautaManager.getDefault();
        PautaSearch pautaSearch = new PautaSearch();

        pautaSearch.setCliente(organization); //mis pautas
        List<Pauta> pautas = pautaManager.getAllEntities(pautaSearch, null);
        pautaSearch.setCliente(null); //pautas de nadie
        pautas.addAll(pautaManager.getAllEntities(pautaSearch, null));

        List<SelectItem> result = new ArrayList<SelectItem>();
        for (Pauta p : pautas) {
            result.add(new SelectItem(p, p.getName()));
        }
        result.add(0, new SelectItem(""));
        Collections.sort(result, new Comparator<SelectItem>() {
            public int compare(SelectItem o1, SelectItem o2) {
                return o1.getLabel().compareTo(o2.getLabel());
            }
        });

        return result;
    }

    public List<SelectItem> getMisEnsayos() {
        List<Ensayo> ensayos = ensayoManager.getAllEntities(null, null);
        if (organization.getEnsayoPrecios() == null)
            organization.setEnsayoPrecios(new HashSet<EnsayoPrecio>());

        List<SelectItem> result = new ArrayList<SelectItem>();
        for (Ensayo e : ensayos) {
            result.add(new SelectItem(e, e.getName()));
        }
        result.add(0, new SelectItem(""));
        Collections.sort(result, new Comparator<SelectItem>() {
            public int compare(SelectItem o1, SelectItem o2) {
                return o1.getLabel().compareTo(o2.getLabel());
            }
        });

        return result;
    }

    public List<PautaPrecio> getPautaPrecios() {
        List<PautaPrecio> result = new ArrayList<PautaPrecio>(organization.getPautaPrecios());
        Collections.sort(result, new Comparator<PautaPrecio>() {
            public int compare(PautaPrecio o1, PautaPrecio o2) {
                if (o1.getPauta() == null) {
                    return 1;
                }
                if (o2.getPauta() == null) {
                    return -1;
                }
                try {
                    return o1.getPauta().getName().compareTo(o2.getPauta().getName());
                } catch (Exception NullPointerException) {
                    return 0;
                }
            }
        });
        return result;
    }

    public void setPautaPrecios(List<PautaPrecio> pautaPrecios) {
        organization.setPautaPrecios(new HashSet<PautaPrecio>(pautaPrecios));
    }

    List<ProductoPrecio> productoPrecios = new ArrayList<ProductoPrecio>();

    public List<ProductoPrecio> getProductoPrecios() {
        ProductoPrecioSearch productoPrecioSearch = new ProductoPrecioSearch();
        productoPrecioSearch.setClient(organization);
        ProductoPrecioManager productoPrecioManager = ProductoPrecioManager.getDefault();
        this.productoPrecios = productoPrecioManager.getAllEntities(productoPrecioSearch, new SortCriteria("producto"));
        for (int i = 0; i < 5; ++i) {
            ProductoPrecio pp = new ProductoPrecio();
            pp.setClient(organization);
            this.productoPrecios.add(pp);
        }
        return this.productoPrecios;
    }

    public void setProductoPrecios(List<ProductoPrecio> productoPrecios) {
        this.productoPrecios = productoPrecios;
    }

    /**
     * Serial version field
     */
    private static final long serialVersionUID = -1L;

    /**
     * JSF outcome to go to organization contacts
     */
    private static final String OUTCOME_SEARCH_CONTACTS = "searchContacts";

    /**
     * Method to navigate to organization's contacts
     *
     * @return OrganizationBean#OUTCOME_SEARCH_CONTACTS
     */
    public String searchContacts() {
        // Remove ourself from session to avoid Hibernate errors
        FacesUtils.removeFromSession("organizationBean");

        // Set an active search in contactBean to show only contacts related to
        // this organization
        UIData list = (UIData) FacesUtils.getComponent("organizations:list");
        Organization org = (Organization) list.getRowData();
        ContactBean contactBean = (ContactBean) FacesUtils
                .getBean("contactBean");
        contactBean.getSearch().setOrganization(org);

        return OUTCOME_SEARCH_CONTACTS;
    }

/* organization - generated by stajanov (do not edit/delete) */


    /**
     * Logger
     */
    private static final Log log = LogFactory.getLog(OrganizationBean.class);

    /**
     * Active search object
     */
    private OrganizationSearch search = new OrganizationSearch();

    /**
     * Manager
     */
    private static OrganizationManager manager = OrganizationManager.getDefault();

    /**
     * Upload service
     */
    private static final Uploader uploader = UploaderFactory.getInstance("organization");


    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    /**
     * Active Organization object
     */
    private Organization organization;

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
     * List organizations. Order depends on Faces parameter sort.
     *
     * @return the list of all organizations sorted by requested criterion
     */
    public List<Organization> getAll() {
        return manager.getAllEntities(search, new SortCriteria(sortColumn, sortAscending));
    }

    // Getters to list possible values of related entities


    /**
     * Get the list of all types
     *
     * @return the list of all types
     */
    public List<SelectItem> getTypes() {
        List<OrganizationType> refs = OrganizationTypeManager.getDefault().getAllEntities(null, new SortCriteria("name"));

        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        for (OrganizationType ref : refs) {
            ret.add(new SelectItem(ref, ref.getName()));
        }
        return ret;
    }


    /**
     * Get the list of all categorys
     *
     * @return the list of all categorys
     */
    public List<SelectItem> getCategorys() {
        List<OrganizationISOCategory> refs = OrganizationISOCategoryManager.getDefault().getAllEntities(null, new SortCriteria("name"));

        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        for (OrganizationISOCategory ref : refs) {
            ret.add(new SelectItem(ref, ref.getName()));
        }
        return ret;
    }


    /**
     * Get the list of all provinces
     *
     * @return the list of all provinces
     */
    public List<SelectItem> getProvinces() {
        List<Province> refs = ProvinceManager.getDefault().getAllEntities(null, new SortCriteria("name"));

        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        for (Province ref : refs) {
            ret.add(new SelectItem(ref, ref.getName()));
        }
        return ret;
    }

    // Getters to list possible values of enum fields

    // Methods to create/remove instances of one-to-many entities (slave entities)

    /**
     * Whether or not create button is available for user
     *
     * @return true if user can create objects of type Organization
     */
    public boolean isCreateAvailable() {
        return SpringUtils.isRolePermissionGranted(Permission.Entity_Create(Organization.class));
    }

    /**
     * Whether or not edit button is available for user
     *
     * @return true if user can edit current object
     */
    public boolean isEditAvailable() {
        return SpringUtils.isAclPermissionGranted(organization, BasePermission.WRITE);
    }

    /**
     * Whether or not delete button is available for user
     *
     * @return true if user can delete current object
     */
    public boolean isDeleteAvailable() {
        return (organization.getId() != null) &&
                SpringUtils.isAclPermissionGranted(organization, BasePermission.DELETE);
    }

    /**
     * Go to create page
     *
     * @return forward to CREATE page
     */
    public String create() {
        organization = new Organization();

        return NavigationResults.CREATE;
    }

    /**
     * Go to detail page
     *
     * @return forward to DETAIL page
     */
    public String detail() {
        Integer id = Integer.parseInt(FacesUtils.getRequestParameter("id"));
        organization = manager.getEntityById(id);

        return SpringUtils.isAclPermissionGranted(organization, BasePermission.WRITE)
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

        if (organization.getId() == null) {
            manager.insertEntity(organization);
        } else {
            manager.updateEntity(organization);
        }

        // Calls an after save action
        String result = doAfterSave(NavigationResults.LIST);

        // Unselect object
        //organization = null;

        return null;
    }

    /**
     * Delete bean and go back to beans list
     *
     * @return forward to LIST page
     */
    public String delete() {
        manager.deleteEntity(organization);
        organization = null;
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
    public boolean isOrganizationSelected() {
        return organization != null;
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
    public OrganizationSearch getSearch() {
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


    public String getSearchCif() {
        return search.getCif();
    }

    public void setSearchCif(String val) {
        if (search.isCifSet()) {
            search.setCif(val);
        }
    }

    public boolean isSearchCifValid() {
        return search.isCifSet();
    }

    public void setSearchCifValid(boolean val) {
        if (val) {
            search.setCif(search.getCif());
        } else {
            search.unsetCif();
        }
    }


    public String getSearchNumeroProveedor() {
        return search.getNumeroProveedor();
    }

    public void setSearchNumeroProveedor(String val) {
        if (search.isNumeroProveedorSet()) {
            search.setNumeroProveedor(val);
        }
    }

    public boolean isSearchNumeroProveedorValid() {
        return search.isNumeroProveedorSet();
    }

    public void setSearchNumeroProveedorValid(boolean val) {
        if (val) {
            search.setNumeroProveedor(search.getNumeroProveedor());
        } else {
            search.unsetNumeroProveedor();
        }
    }


    public String getSearchStreet() {
        return search.getStreet();
    }

    public void setSearchStreet(String val) {
        if (search.isStreetSet()) {
            search.setStreet(val);
        }
    }

    public boolean isSearchStreetValid() {
        return search.isStreetSet();
    }

    public void setSearchStreetValid(boolean val) {
        if (val) {
            search.setStreet(search.getStreet());
        } else {
            search.unsetStreet();
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


    public String getSearchLocator() {
        return search.getLocator();
    }

    public void setSearchLocator(String val) {
        if (search.isLocatorSet()) {
            search.setLocator(val);
        }
    }

    public boolean isSearchLocatorValid() {
        return search.isLocatorSet();
    }

    public void setSearchLocatorValid(boolean val) {
        if (val) {
            search.setLocator(search.getLocator());
        } else {
            search.unsetLocator();
        }
    }


    public String getSearchPostalCode() {
        return search.getPostalCode();
    }

    public void setSearchPostalCode(String val) {
        if (search.isPostalCodeSet()) {
            search.setPostalCode(val);
        }
    }

    public boolean isSearchPostalCodeValid() {
        return search.isPostalCodeSet();
    }

    public void setSearchPostalCodeValid(boolean val) {
        if (val) {
            search.setPostalCode(search.getPostalCode());
        } else {
            search.unsetPostalCode();
        }
    }


    public String getSearchCity() {
        return search.getCity();
    }

    public void setSearchCity(String val) {
        if (search.isCitySet()) {
            search.setCity(val);
        }
    }

    public boolean isSearchCityValid() {
        return search.isCitySet();
    }

    public void setSearchCityValid(boolean val) {
        if (val) {
            search.setCity(search.getCity());
        } else {
            search.unsetCity();
        }
    }


    public String getSearchState() {
        return search.getState();
    }

    public void setSearchState(String val) {
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


    public String getSearchCountry() {
        return search.getCountry();
    }

    public void setSearchCountry(String val) {
        if (search.isCountrySet()) {
            search.setCountry(val);
        }
    }

    public boolean isSearchCountryValid() {
        return search.isCountrySet();
    }

    public void setSearchCountryValid(boolean val) {
        if (val) {
            search.setCountry(search.getCountry());
        } else {
            search.unsetCountry();
        }
    }


    public String getSearchPhone() {
        return search.getPhone();
    }

    public void setSearchPhone(String val) {
        if (search.isPhoneSet()) {
            search.setPhone(val);
        }
    }

    public boolean isSearchPhoneValid() {
        return search.isPhoneSet();
    }

    public void setSearchPhoneValid(boolean val) {
        if (val) {
            search.setPhone(search.getPhone());
        } else {
            search.unsetPhone();
        }
    }


    public String getSearchFax() {
        return search.getFax();
    }

    public void setSearchFax(String val) {
        if (search.isFaxSet()) {
            search.setFax(val);
        }
    }

    public boolean isSearchFaxValid() {
        return search.isFaxSet();
    }

    public void setSearchFaxValid(boolean val) {
        if (val) {
            search.setFax(search.getFax());
        } else {
            search.unsetFax();
        }
    }


    public String getSearchEmail() {
        return search.getEmail();
    }

    public void setSearchEmail(String val) {
        if (search.isEmailSet()) {
            search.setEmail(val);
        }
    }

    public boolean isSearchEmailValid() {
        return search.isEmailSet();
    }

    public void setSearchEmailValid(boolean val) {
        if (val) {
            search.setEmail(search.getEmail());
        } else {
            search.unsetEmail();
        }
    }


    public String getSearchWebsite() {
        return search.getWebsite();
    }

    public void setSearchWebsite(String val) {
        if (search.isWebsiteSet()) {
            search.setWebsite(val);
        }
    }

    public boolean isSearchWebsiteValid() {
        return search.isWebsiteSet();
    }

    public void setSearchWebsiteValid(boolean val) {
        if (val) {
            search.setWebsite(search.getWebsite());
        } else {
            search.unsetWebsite();
        }
    }


    public String getSearchFtpsite() {
        return search.getFtpsite();
    }

    public void setSearchFtpsite(String val) {
        if (search.isFtpsiteSet()) {
            search.setFtpsite(val);
        }
    }

    public boolean isSearchFtpsiteValid() {
        return search.isFtpsiteSet();
    }

    public void setSearchFtpsiteValid(boolean val) {
        if (val) {
            search.setFtpsite(search.getFtpsite());
        } else {
            search.unsetFtpsite();
        }
    }


    public String getSearchNotes() {
        return search.getNotes();
    }

    public void setSearchNotes(String val) {
        if (search.isNotesSet()) {
            search.setNotes(val);
        }
    }

    public boolean isSearchNotesValid() {
        return search.isNotesSet();
    }

    public void setSearchNotesValid(boolean val) {
        if (val) {
            search.setNotes(search.getNotes());
        } else {
            search.unsetNotes();
        }
    }


    public String getSearchColumnaRefCli() {
        return search.getColumnaRefCli();
    }

    public void setSearchColumnaRefCli(String val) {
        if (search.isColumnaRefCliSet()) {
            search.setColumnaRefCli(val);
        }
    }

    public boolean isSearchColumnaRefCliValid() {
        return search.isColumnaRefCliSet();
    }

    public void setSearchColumnaRefCliValid(boolean val) {
        if (val) {
            search.setColumnaRefCli(search.getColumnaRefCli());
        } else {
            search.unsetColumnaRefCli();
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


    public OrganizationType getSearchType() {
        return search.getType();
    }

    public void setSearchType(OrganizationType val) {
        if (search.isTypeSet()) {
            search.setType(val);
        }
    }

    public boolean isSearchTypeValid() {
        return search.isTypeSet();
    }

    public void setSearchTypeValid(boolean val) {
        if (val) {
            search.setType(search.getType());
        } else {
            search.unsetType();
        }
    }


    public OrganizationISOCategory getSearchCategory() {
        return search.getCategory();
    }

    public void setSearchCategory(OrganizationISOCategory val) {
        if (search.isCategorySet()) {
            search.setCategory(val);
        }
    }

    public boolean isSearchCategoryValid() {
        return search.isCategorySet();
    }

    public void setSearchCategoryValid(boolean val) {
        if (val) {
            search.setCategory(search.getCategory());
        } else {
            search.unsetCategory();
        }
    }


    public Province getSearchProvince() {
        return search.getProvince();
    }

    public void setSearchProvince(Province val) {
        if (search.isProvinceSet()) {
            search.setProvince(val);
        }
    }

    public boolean isSearchProvinceValid() {
        return search.isProvinceSet();
    }

    public void setSearchProvinceValid(boolean val) {
        if (val) {
            search.setProvince(search.getProvince());
        } else {
            search.unsetProvince();
        }
    }

    /**
     * Handle an ABC pager letter click: filter objects by specified starting letter
     */
    public void letterClicked() {
        if (letter != null) {
            UIComponent comp = FacesUtils.getComponent("organizations:list");
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

    // Getters and setters to manipulate active Organization object

    public java.lang.Integer getId() {
        return organization.getId();
    }


    public String getAcronimo() {
        return organization.getAcronimo();
    }

    public void setAcronimo(String acronimo) {
        organization.setAcronimo(acronimo);
    }


    public String getName() {
        return organization.getName();
    }

    public void setName(String name) {
        organization.setName(name);
    }


    public String getCif() {
        return organization.getCif();
    }

    public void setCif(String cif) {
        organization.setCif(cif);
    }


    public String getStreet() {
        return organization.getStreet();
    }

    public void setStreet(String street) {
        organization.setStreet(street);
    }


    public String getNumber() {
        return organization.getNumber();
    }

    public void setNumber(String number) {
        organization.setNumber(number);
    }


    public String getLocator() {
        return organization.getLocator();
    }

    public void setLocator(String locator) {
        organization.setLocator(locator);
    }


    public String getPostalCode() {
        return organization.getPostalCode();
    }

    public void setPostalCode(String postalCode) {
        organization.setPostalCode(postalCode);
    }


    public String getCity() {
        return organization.getCity();
    }

    public void setCity(String city) {
        organization.setCity(city);
    }


    public String getState() {
        return organization.getState();
    }

    public void setState(String state) {
        organization.setState(state);
    }


    public String getCountry() {
        return organization.getCountry();
    }

    public void setCountry(String country) {
        organization.setCountry(country);
    }


    public String getPhone() {
        return organization.getPhone();
    }

    public void setPhone(String phone) {
        organization.setPhone(phone);
    }


    public String getFax() {
        return organization.getFax();
    }

    public void setFax(String fax) {
        organization.setFax(fax);
    }


    public String getEmail() {
        return organization.getEmail();
    }

    public void setEmail(String email) {
        organization.setEmail(email);
    }


    public String getWebsite() {
        return organization.getWebsite();
    }

    public void setWebsite(String website) {
        organization.setWebsite(website);
    }


    public String getFtpsite() {
        return organization.getFtpsite();
    }

    public void setFtpsite(String ftpsite) {
        organization.setFtpsite(ftpsite);
    }


    public String getNotes() {
        return organization.getNotes();
    }

    public void setNotes(String notes) {
        organization.setNotes(notes);
    }


    public String getColumnaRefCli() {
        return organization.getColumnaRefCli();
    }

    public void setColumnaRefCli(String columnaRefCli) {
        organization.setColumnaRefCli(columnaRefCli);
    }


    public Integer getOwnerId() {
        return organization.getOwnerId();
    }

    public void setOwnerId(Integer ownerId) {
        organization.setOwnerId(ownerId);
    }


    public Integer getDepartmentId() {
        return organization.getDepartmentId();
    }

    public void setDepartmentId(Integer departmentId) {
        organization.setDepartmentId(departmentId);
    }


    public Date getInsertDate() {
        return organization.getInsertDate();
    }

    public void setInsertDate(Date insertDate) {
        organization.setInsertDate(insertDate);
    }


    public Date getUpdateDate() {
        return organization.getUpdateDate();
    }

    public void setUpdateDate(Date updateDate) {
        organization.setUpdateDate(updateDate);
    }


    public OrganizationType getType() {
        return organization.getType();
    }

    public void setType(OrganizationType type) {
        organization.setType(type);
    }


    public OrganizationISOCategory getCategory() {
        return organization.getCategory();
    }

    public void setCategory(OrganizationISOCategory category) {
        organization.setCategory(category);
    }


    public Province getProvince() {
        return organization.getProvince();
    }

    public void setProvince(Province province) {
        organization.setProvince(province);
    }

/* organization - generated by stajanov (do not edit/delete) */
/*
	public void createDefaultProject() {

		FacesContext context = FacesContext.getCurrentInstance();
	    Locale locale = context.getViewRoot().getLocale();
	    ResourceBundle bundle = ResourceBundle.getBundle("com.autentia.intra.resources.messages", locale);      

		Project projecto = new Project();
		projecto.setClient(organization);
		projecto.setName(bundle.getString("defaultName.project") + " ".concat(organization.getName()));
		projecto.setStartDate(new Date());
		projecto.setOpen(true);
		
		ProjectManager.getDefault().insertEntity(projecto);
				
	}
	*/

    public String doAfterSave(String result) {
        return result;
    }


    public String editarPrecios() {
        Integer id = Integer.parseInt(FacesUtils.getRequestParameter("id"));
        organization = manager.getEntityById(id);

        return SpringUtils.isAclPermissionGranted(organization, BasePermission.WRITE)
                ? NavigationResults.EDITAR_PRECIOS_DE_CLIENTE
                : null;
    }

    public boolean isEsCliente() {
        return organization.getType().getId() == ConfigurationUtil.getDefault().getOrganizationTypeClient()
                || organization.getType().getId() == ConfigurationUtil.getDefault().getOrganizationTypeProviderAndClient();
    }

    public boolean isEsProveedor() {
        return organization.getType().getId() == ConfigurationUtil.getDefault().getOrganizationTypeProvider()
                || organization.getType().getId() == ConfigurationUtil.getDefault().getOrganizationTypeProviderAndClient();
    }

    public List<SelectItem> getOrganizationCertificados() {
        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        OrganizationCertificado[] vals = OrganizationCertificado.values();
        for (OrganizationCertificado val : vals) {
            ret.add(new SelectItem(val, FacesUtils.formatMessage("OrganizationCertificado." + val.name())));
        }
        return ret;
    }

    public List<SelectItem> getOrganizationEstados() {
        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        OrganizationEstado[] vals = OrganizationEstado.values();
        for (OrganizationEstado val : vals) {
            ret.add(new SelectItem(val, FacesUtils.formatMessage("OrganizationEstado." + val.name())));
        }
        return ret;
    }

    public String createEnsayoPrecios() {
        EnsayoPrecio item = new EnsayoPrecio();
        item.setClient(organization);
        if (organization.getEnsayoPrecios() == null) {
            organization.setEnsayoPrecios(new HashSet<EnsayoPrecio>());
        }
        organization.getEnsayoPrecios().add(item);
        return null;
    }

    public String deleteEnsayoPrecios() {
        UIData table = (UIData) FacesUtils.getComponent("precios:ensayoPrecios");
        organization.getEnsayoPrecios().remove(table.getRowData());
        return null;
    }

    public String createPautaPrecios() {
        PautaPrecio item = new PautaPrecio();
        item.setClient(organization);
        if (organization.getPautaPrecios() == null) {
            organization.setPautaPrecios(new HashSet<PautaPrecio>());
        }
        organization.getPautaPrecios().add(item);
        return null;
    }

    public String deletePautaPrecios() {
        UIData table = (UIData) FacesUtils.getComponent("precios:pautaPrecios");
        organization.getPautaPrecios().remove(table.getRowData());
        return null;
    }
}

