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
package com.autentia.intra.bean.admin;

import com.autentia.intra.bean.BaseBean;
import com.autentia.intra.bean.NavigationResults;
import com.autentia.intra.bean.document.DocumentBean;
import com.autentia.intra.businessobject.*;
import com.autentia.intra.dao.SortCriteria;
import com.autentia.intra.dao.hibernate.UserDAO;
import com.autentia.intra.dao.search.UserSearch;
import com.autentia.intra.manager.admin.*;
import com.autentia.intra.manager.contacts.OrganizationManager;
import com.autentia.intra.manager.document.DocumentCategoryManager;
import com.autentia.intra.manager.security.AuthenticationManager;
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

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.model.SelectItem;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * UI bean for User objects.
 *
 * @author stajanov code generator
 */
public class UserBean extends BaseBean {
    /**
     * Serial version field
     */
    private static final long serialVersionUID = -1L;

    /**
     * Default authentication manager
     */
    private static final AuthenticationManager authManager = AuthenticationManager.getDefault();

    /**
     * Default DocumentCategory manager
     */
    private static final DocumentCategoryManager dcManager = DocumentCategoryManager.getDefault();

    /**
     * New generated password when user clicks on resetPassword
     */
    private String changedPassword;

    /**
     * Saves and goes to user's document category
     */
    public String goDocuments() {
        User currentUser = getUser();
        // Save user's data
        save();
        // Prepares the category bean filter
        DocumentBean documentBean = (DocumentBean) FacesUtils.getBean("documentBean");
        List<DocumentCategory> categories = new ArrayList<DocumentCategory>();
        categories.add(currentUser.getDocumentCategory());
        documentBean.setSearchCategoriesValid(true);
        documentBean.setSearchCategories(categories);
        return "goDocuments";
    }

    /**
     * Creates user's category when the user or not has a category
     */
    @Override
    public String doBeforeSave() {
        String result = super.doBeforeSave();
        // if the user not has a category
        user = (User) UserDAO.getDefault().merge(user); //arregla lazy see #38
        if (getUser().getDocumentCategory() == null || getUser().getDocumentCategory().getId() == null) {

            DocumentCategory padre = dcManager.getDocumentCategoryParent();
            // User category
            DocumentCategory userDocCategory = new DocumentCategory();
            userDocCategory.setName(getUser().getName());
            userDocCategory.setDescription(getUser().getName());
            userDocCategory.setPadre(padre);
            getUser().setDocumentCategory(userDocCategory);
        } else {
            // Updates the category name
            getUser().getDocumentCategory().setName(getUser().getName());
            getUser().getDocumentCategory().setDescription(getUser().getName());
        }
        return result;
    }

    /**
     * Reset current user password
     *
     * @return "userPasswordReset"
     */
    public String resetPassword() {
        // Change user password
        changedPassword = authManager.resetPassword(getUser());
        manager.updateEntity(getUser());

        return "userPasswordReset";
    }

    public String getChangedPassword() {
        return changedPassword;
    }

    public boolean isResetPasswordAvailable() {
        return SpringUtils.isRolePermissionGranted(Permission.Action_ResetPassword);
    }

    /**
     * Wheter or not role field is available to user
     *
     * @return true if user is administrator
     */
    public boolean isRoleAvailable() {
        return SpringUtils.isRolePermissionGranted(Permission.Action_ChangeRole);
    }

/* user - generated by stajanov (do not edit/delete) */


    /**
     * Logger
     */
    private static final Log log = LogFactory.getLog(UserBean.class);

    /**
     * Active search object
     */
    private UserSearch search = new UserSearch();

    /**
     * Manager
     */
    private static UserManager manager = UserManager.getDefault();

    /**
     * Upload service
     */
    private static final Uploader uploader = UploaderFactory.getInstance("user");


    /**
     * Temporary photo field
     */
    private String oldPhoto;

    /**
     * Uploaded photo object
     */
    private UploadedFile uploadPhoto;


    /**
     * Active User object
     */
    private User user;

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
     * List users. Order depends on Faces parameter sort.
     *
     * @return the list of all users sorted by requested criterion
     */
    public List<User> getAll() {
        return manager.getAllEntities(search, new SortCriteria(sortColumn, sortAscending));
    }

    // Getters to list possible values of related entities


    /**
     * Get the list of all roles
     *
     * @return the list of all roles
     */
    public List<SelectItem> getRoles() {
        List<Role> refs = RoleManager.getDefault().getAllEntities(null, new SortCriteria("name"));

        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        for (Role ref : refs) {
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
        List<UserCategory> refs = UserCategoryManager.getDefault().getAllEntities(null, new SortCriteria("name"));

        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        for (UserCategory ref : refs) {
            ret.add(new SelectItem(ref, ref.getName()));
        }
        return ret;
    }

    /**
     * Get the list of all organizations
     *
     * @return the list of all organizations
     */
    public List<SelectItem> getOrganizations() {
        Organization lab = OrganizationManager.getDefault().getEntityById(ConfigurationUtil.getDefault().getIdLabCompany());
        Organization cpy = OrganizationManager.getDefault().getEntityById(ConfigurationUtil.getDefault().getIdOurCompany());

        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        ret.add(new SelectItem(lab, lab.getName()));
        ret.add(new SelectItem(cpy, cpy.getName()));
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


    /**
     * Get the list of all documentCategorys
     *
     * @return the list of all documentCategorys
     */
    public List<SelectItem> getDocumentCategorys() {
        List<DocumentCategory> refs = DocumentCategoryManager.getDefault().getAllEntities(null, new SortCriteria("name"));

        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        for (DocumentCategory ref : refs) {
            ret.add(new SelectItem(ref, ref.getName()));
        }
        return ret;
    }


    /**
     * Get the list of all departments
     *
     * @return the list of all departments
     */
    public List<SelectItem> getDepartments() {
        List<Department> refs = DepartmentManager.getDefault().getAllEntities(null, new SortCriteria("name"));

        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        for (Department ref : refs) {
            ret.add(new SelectItem(ref, ref.getName()));
        }
        return ret;
    }


    /**
     * Get the list of all contractTypes
     *
     * @return the list of all contractTypes
     */
    public List<SelectItem> getContractTypes() {
        List<ContractType> refs = ContractTypeManager.getDefault().getAllEntities(null, new SortCriteria("name"));

        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        for (ContractType ref : refs) {
            ret.add(new SelectItem(ref, ref.getName()));
        }
        return ret;
    }


    /**
     * Get the list of all agreements
     *
     * @return the list of all agreements
     */
    public List<SelectItem> getAgreements() {
        List<WorkingAgreement> refs = WorkingAgreementManager.getDefault().getAllEntities(null, new SortCriteria("name"));

        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        for (WorkingAgreement ref : refs) {
            ret.add(new SelectItem(ref, ref.getName()));
        }
        return ret;
    }

    // Getters to list possible values of enum fields

    public String getGenreFormatted() {
        if (this.getGenre() != null) {
            return FacesUtils.formatMessage("UserGenre." + this.getGenre().name());
        } else {
            return "";
        }
    }

    /**
     * Get the list of all Genre values
     *
     * @return the list of all Genre values
     */
    public List<SelectItem> getGenres() {
        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        UserGenre[] vals = UserGenre.values();
        for (UserGenre val : vals) {
            ret.add(new SelectItem(val, FacesUtils.formatMessage("UserGenre." + val.name())));
        }
        return ret;
    }

    // Methods to create/remove instances of one-to-many entities (slave entities)

    /**
     * Whether or not create button is available for user
     *
     * @return true if user can create objects of type User
     */
    public boolean isCreateAvailable() {
        return SpringUtils.isRolePermissionGranted(Permission.Entity_Create(User.class));
    }

    /**
     * Whether or not edit button is available for user
     *
     * @return true if user can edit current object
     */
    public boolean isEditAvailable() {
        return SpringUtils.isAclPermissionGranted(getUser(), BasePermission.WRITE);
    }

    /**
     * Whether or not delete button is available for user
     *
     * @return true if user can delete current object
     */
    public boolean isDeleteAvailable() {
        return (getUser().getId() != null) &&
                SpringUtils.isAclPermissionGranted(getUser(), BasePermission.DELETE);
    }

    /**
     * Go to create page
     *
     * @return forward to CREATE page
     */
    public String create() {
        setUser(new User());
        // Set default password to "password"
        getUser().setPassword("5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8");
// Set default hours to 8*60 
        getUser().setDayDuration(480);
        return NavigationResults.CREATE;
    }

    /**
     * Go to detail page
     *
     * @return forward to DETAIL page
     */
    public String detail() {
        Integer id = Integer.parseInt(FacesUtils.getRequestParameter("id"));
        setUser(manager.getEntityById(id));

        return SpringUtils.isAclPermissionGranted(getUser(), BasePermission.WRITE)
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

        if (getUser().getId() == null) {
            manager.insertEntity(getUser());
        } else {
            manager.updateEntity(getUser());
        }

        // Handle uploads for photo field
        if (uploadPhoto != null) {
            try {
                uploader.replace(Integer.toString(getUser().getId()), oldPhoto, uploadPhoto);
            } catch (IOException e) {
                log.error("save - exception uploading field photo", e);
                FacesUtils.addErrorMessage("photo", "error.fileTransfer", e.getMessage());
            }
        }

        // Calls an after save action
        String result = doAfterSave(NavigationResults.LIST);

        // Unselect object
        //setUser(null);

        return null;
    }

    /**
     * Delete bean and go back to beans list
     *
     * @return forward to LIST page
     */
    public String delete() {
        manager.deleteEntity(getUser());
        setUser(null);
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
    public boolean isUserSelected() {
        return getUser() != null;
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
    public UserSearch getSearch() {
        return search;
    }


    public java.lang.Boolean getSearchActive() {
        return search.getActive();
    }

    public void setSearchActive(java.lang.Boolean val) {
        if (search.isActiveSet()) {
            search.setActive(val);
        }
    }

    public boolean isSearchActiveValid() {
        return search.isActiveSet();
    }

    public void setSearchActiveValid(boolean val) {
        if (val) {
            search.setActive(search.getActive());
        } else {
            search.unsetActive();
        }
    }


    public String getSearchLogin() {
        return search.getLogin();
    }

    public void setSearchLogin(String val) {
        if (search.isLoginSet()) {
            search.setLogin(val);
        }
    }

    public boolean isSearchLoginValid() {
        return search.isLoginSet();
    }

    public void setSearchLoginValid(boolean val) {
        if (val) {
            search.setLogin(search.getLogin());
        } else {
            search.unsetLogin();
        }
    }


    public String getSearchPassword() {
        return search.getPassword();
    }

    public void setSearchPassword(String val) {
        if (search.isPasswordSet()) {
            search.setPassword(val);
        }
    }

    public boolean isSearchPasswordValid() {
        return search.isPasswordSet();
    }

    public void setSearchPasswordValid(boolean val) {
        if (val) {
            search.setPassword(search.getPassword());
        } else {
            search.unsetPassword();
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


    public Date getSearchStartStartDate() {
        return search.getStartStartDate();
    }

    public void setSearchStartStartDate(Date val) {
        if (val != null) {
            search.setStartStartDate(val);
        } else {
            search.unsetStartStartDate();
        }
    }

    public boolean isSearchStartStartDateValid() {
        return search.isStartStartDateSet();
    }

    public void setSearchStartStartDateValid(boolean val) {
        if (val) {
            search.setStartStartDate(search.getStartStartDate());
        } else {
            search.unsetStartStartDate();
        }
    }

    public Date getSearchEndStartDate() {
        return search.getEndStartDate();
    }

    public void setSearchEndStartDate(Date val) {
        if (val != null) {
            search.setEndStartDate(val);
        } else {
            search.unsetEndStartDate();
        }
    }

    public boolean isSearchEndStartDateValid() {
        return search.isEndStartDateSet();
    }

    public void setSearchEndStartDateValid(boolean val) {
        if (val) {
            search.setEndStartDate(search.getEndStartDate());
        } else {
            search.unsetEndStartDate();
        }
    }


    public String getSearchNif() {
        return search.getNif();
    }

    public void setSearchNif(String val) {
        if (search.isNifSet()) {
            search.setNif(val);
        }
    }

    public boolean isSearchNifValid() {
        return search.isNifSet();
    }

    public void setSearchNifValid(boolean val) {
        if (val) {
            search.setNif(search.getNif());
        } else {
            search.unsetNif();
        }
    }


    public UserGenre getSearchGenre() {
        return search.getGenre();
    }

    public void setSearchGenre(UserGenre val) {
        if (search.isGenreSet()) {
            search.setGenre(val);
        }
    }

    public boolean isSearchGenreValid() {
        return search.isGenreSet();
    }

    public void setSearchGenreValid(boolean val) {
        if (val) {
            search.setGenre(search.getGenre());
        } else {
            search.unsetGenre();
        }
    }


    public String getSearchSocialSecurityNumber() {
        return search.getSocialSecurityNumber();
    }

    public void setSearchSocialSecurityNumber(String val) {
        if (search.isSocialSecurityNumberSet()) {
            search.setSocialSecurityNumber(val);
        }
    }

    public boolean isSearchSocialSecurityNumberValid() {
        return search.isSocialSecurityNumberSet();
    }

    public void setSearchSocialSecurityNumberValid(boolean val) {
        if (val) {
            search.setSocialSecurityNumber(search.getSocialSecurityNumber());
        } else {
            search.unsetSocialSecurityNumber();
        }
    }


    public java.lang.Boolean getSearchWorkingInClient() {
        return search.getWorkingInClient();
    }

    public void setSearchWorkingInClient(java.lang.Boolean val) {
        if (search.isWorkingInClientSet()) {
            search.setWorkingInClient(val);
        }
    }

    public boolean isSearchWorkingInClientValid() {
        return search.isWorkingInClientSet();
    }

    public void setSearchWorkingInClientValid(boolean val) {
        if (val) {
            search.setWorkingInClient(search.getWorkingInClient());
        } else {
            search.unsetWorkingInClient();
        }
    }


    public Date getSearchStartBirthDate() {
        return search.getStartBirthDate();
    }

    public void setSearchStartBirthDate(Date val) {
        if (val != null) {
            search.setStartBirthDate(val);
        } else {
            search.unsetStartBirthDate();
        }
    }

    public boolean isSearchStartBirthDateValid() {
        return search.isStartBirthDateSet();
    }

    public void setSearchStartBirthDateValid(boolean val) {
        if (val) {
            search.setStartBirthDate(search.getStartBirthDate());
        } else {
            search.unsetStartBirthDate();
        }
    }

    public Date getSearchEndBirthDate() {
        return search.getEndBirthDate();
    }

    public void setSearchEndBirthDate(Date val) {
        if (val != null) {
            search.setEndBirthDate(val);
        } else {
            search.unsetEndBirthDate();
        }
    }

    public boolean isSearchEndBirthDateValid() {
        return search.isEndBirthDateSet();
    }

    public void setSearchEndBirthDateValid(boolean val) {
        if (val) {
            search.setEndBirthDate(search.getEndBirthDate());
        } else {
            search.unsetEndBirthDate();
        }
    }


    public java.lang.Boolean getSearchMarried() {
        return search.getMarried();
    }

    public void setSearchMarried(java.lang.Boolean val) {
        if (search.isMarriedSet()) {
            search.setMarried(val);
        }
    }

    public boolean isSearchMarriedValid() {
        return search.isMarriedSet();
    }

    public void setSearchMarriedValid(boolean val) {
        if (val) {
            search.setMarried(search.getMarried());
        } else {
            search.unsetMarried();
        }
    }


    public java.lang.Integer getSearchChildrenNumber() {
        return search.getChildrenNumber();
    }

    public void setSearchChildrenNumber(java.lang.Integer val) {
        if (search.isChildrenNumberSet()) {
            search.setChildrenNumber(val);
        }
    }

    public boolean isSearchChildrenNumberValid() {
        return search.isChildrenNumberSet();
    }

    public void setSearchChildrenNumberValid(boolean val) {
        if (val) {
            search.setChildrenNumber(search.getChildrenNumber());
        } else {
            search.unsetChildrenNumber();
        }
    }


    public String getSearchTravelAvailability() {
        return search.getTravelAvailability();
    }

    public void setSearchTravelAvailability(String val) {
        if (search.isTravelAvailabilitySet()) {
            search.setTravelAvailability(val);
        }
    }

    public boolean isSearchTravelAvailabilityValid() {
        return search.isTravelAvailabilitySet();
    }

    public void setSearchTravelAvailabilityValid(boolean val) {
        if (val) {
            search.setTravelAvailability(search.getTravelAvailability());
        } else {
            search.unsetTravelAvailability();
        }
    }


    public String getSearchAcademicQualification() {
        return search.getAcademicQualification();
    }

    public void setSearchAcademicQualification(String val) {
        if (search.isAcademicQualificationSet()) {
            search.setAcademicQualification(val);
        }
    }

    public boolean isSearchAcademicQualificationValid() {
        return search.isAcademicQualificationSet();
    }

    public void setSearchAcademicQualificationValid(boolean val) {
        if (val) {
            search.setAcademicQualification(search.getAcademicQualification());
        } else {
            search.unsetAcademicQualification();
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


    public String getSearchMobile() {
        return search.getMobile();
    }

    public void setSearchMobile(String val) {
        if (search.isMobileSet()) {
            search.setMobile(val);
        }
    }

    public boolean isSearchMobileValid() {
        return search.isMobileSet();
    }

    public void setSearchMobileValid(boolean val) {
        if (val) {
            search.setMobile(search.getMobile());
        } else {
            search.unsetMobile();
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


    public String getSearchBank() {
        return search.getBank();
    }

    public void setSearchBank(String val) {
        if (search.isBankSet()) {
            search.setBank(val);
        }
    }

    public boolean isSearchBankValid() {
        return search.isBankSet();
    }

    public void setSearchBankValid(boolean val) {
        if (val) {
            search.setBank(search.getBank());
        } else {
            search.unsetBank();
        }
    }


    public String getSearchAccount() {
        return search.getAccount();
    }

    public void setSearchAccount(String val) {
        if (search.isAccountSet()) {
            search.setAccount(val);
        }
    }

    public boolean isSearchAccountValid() {
        return search.isAccountSet();
    }

    public void setSearchAccountValid(boolean val) {
        if (val) {
            search.setAccount(search.getAccount());
        } else {
            search.unsetAccount();
        }
    }


    public BigDecimal getSearchSalary() {
        return search.getSalary();
    }

    public void setSearchSalary(BigDecimal val) {
        if (search.isSalarySet()) {
            search.setSalary(val);
        }
    }

    public boolean isSearchSalaryValid() {
        return search.isSalarySet();
    }

    public void setSearchSalaryValid(boolean val) {
        if (val) {
            search.setSalary(search.getSalary());
        } else {
            search.unsetSalary();
        }
    }


    public BigDecimal getSearchSalaryExtras() {
        return search.getSalaryExtras();
    }

    public void setSearchSalaryExtras(BigDecimal val) {
        if (search.isSalaryExtrasSet()) {
            search.setSalaryExtras(val);
        }
    }

    public boolean isSearchSalaryExtrasValid() {
        return search.isSalaryExtrasSet();
    }

    public void setSearchSalaryExtrasValid(boolean val) {
        if (val) {
            search.setSalaryExtras(search.getSalaryExtras());
        } else {
            search.unsetSalaryExtras();
        }
    }


    public String getSearchDrivenLicenseType() {
        return search.getDrivenLicenseType();
    }

    public void setSearchDrivenLicenseType(String val) {
        if (search.isDrivenLicenseTypeSet()) {
            search.setDrivenLicenseType(val);
        }
    }

    public boolean isSearchDrivenLicenseTypeValid() {
        return search.isDrivenLicenseTypeSet();
    }

    public void setSearchDrivenLicenseTypeValid(boolean val) {
        if (val) {
            search.setDrivenLicenseType(search.getDrivenLicenseType());
        } else {
            search.unsetDrivenLicenseType();
        }
    }


    public String getSearchVehicleType() {
        return search.getVehicleType();
    }

    public void setSearchVehicleType(String val) {
        if (search.isVehicleTypeSet()) {
            search.setVehicleType(val);
        }
    }

    public boolean isSearchVehicleTypeValid() {
        return search.isVehicleTypeSet();
    }

    public void setSearchVehicleTypeValid(boolean val) {
        if (val) {
            search.setVehicleType(search.getVehicleType());
        } else {
            search.unsetVehicleType();
        }
    }


    public String getSearchLicensePlate() {
        return search.getLicensePlate();
    }

    public void setSearchLicensePlate(String val) {
        if (search.isLicensePlateSet()) {
            search.setLicensePlate(val);
        }
    }

    public boolean isSearchLicensePlateValid() {
        return search.isLicensePlateSet();
    }

    public void setSearchLicensePlateValid(boolean val) {
        if (val) {
            search.setLicensePlate(search.getLicensePlate());
        } else {
            search.unsetLicensePlate();
        }
    }


    public String getSearchSecurityCard() {
        return search.getSecurityCard();
    }

    public void setSearchSecurityCard(String val) {
        if (search.isSecurityCardSet()) {
            search.setSecurityCard(val);
        }
    }

    public boolean isSearchSecurityCardValid() {
        return search.isSecurityCardSet();
    }

    public void setSearchSecurityCardValid(boolean val) {
        if (val) {
            search.setSecurityCard(search.getSecurityCard());
        } else {
            search.unsetSecurityCard();
        }
    }


    public String getSearchHealthInsurance() {
        return search.getHealthInsurance();
    }

    public void setSearchHealthInsurance(String val) {
        if (search.isHealthInsuranceSet()) {
            search.setHealthInsurance(val);
        }
    }

    public boolean isSearchHealthInsuranceValid() {
        return search.isHealthInsuranceSet();
    }

    public void setSearchHealthInsuranceValid(boolean val) {
        if (val) {
            search.setHealthInsurance(search.getHealthInsurance());
        } else {
            search.unsetHealthInsurance();
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


    public String getSearchPhoto() {
        return search.getPhoto();
    }

    public void setSearchPhoto(String val) {
        if (search.isPhotoSet()) {
            search.setPhoto(val);
        }
    }

    public boolean isSearchPhotoValid() {
        return search.isPhotoSet();
    }

    public void setSearchPhotoValid(boolean val) {
        if (val) {
            search.setPhoto(search.getPhoto());
        } else {
            search.unsetPhoto();
        }
    }


    public Date getSearchStartEndTestPeriodDate() {
        return search.getStartEndTestPeriodDate();
    }

    public void setSearchStartEndTestPeriodDate(Date val) {
        if (val != null) {
            search.setStartEndTestPeriodDate(val);
        } else {
            search.unsetStartEndTestPeriodDate();
        }
    }

    public boolean isSearchStartEndTestPeriodDateValid() {
        return search.isStartEndTestPeriodDateSet();
    }

    public void setSearchStartEndTestPeriodDateValid(boolean val) {
        if (val) {
            search.setStartEndTestPeriodDate(search.getStartEndTestPeriodDate());
        } else {
            search.unsetStartEndTestPeriodDate();
        }
    }

    public Date getSearchEndEndTestPeriodDate() {
        return search.getEndEndTestPeriodDate();
    }

    public void setSearchEndEndTestPeriodDate(Date val) {
        if (val != null) {
            search.setEndEndTestPeriodDate(val);
        } else {
            search.unsetEndEndTestPeriodDate();
        }
    }

    public boolean isSearchEndEndTestPeriodDateValid() {
        return search.isEndEndTestPeriodDateSet();
    }

    public void setSearchEndEndTestPeriodDateValid(boolean val) {
        if (val) {
            search.setEndEndTestPeriodDate(search.getEndEndTestPeriodDate());
        } else {
            search.unsetEndEndTestPeriodDate();
        }
    }


    public Date getSearchStartEndContractDate() {
        return search.getStartEndContractDate();
    }

    public void setSearchStartEndContractDate(Date val) {
        if (val != null) {
            search.setStartEndContractDate(val);
        } else {
            search.unsetStartEndContractDate();
        }
    }

    public boolean isSearchStartEndContractDateValid() {
        return search.isStartEndContractDateSet();
    }

    public void setSearchStartEndContractDateValid(boolean val) {
        if (val) {
            search.setStartEndContractDate(search.getStartEndContractDate());
        } else {
            search.unsetStartEndContractDate();
        }
    }

    public Date getSearchEndEndContractDate() {
        return search.getEndEndContractDate();
    }

    public void setSearchEndEndContractDate(Date val) {
        if (val != null) {
            search.setEndEndContractDate(val);
        } else {
            search.unsetEndEndContractDate();
        }
    }

    public boolean isSearchEndEndContractDateValid() {
        return search.isEndEndContractDateSet();
    }

    public void setSearchEndEndContractDateValid(boolean val) {
        if (val) {
            search.setEndEndContractDate(search.getEndEndContractDate());
        } else {
            search.unsetEndEndContractDate();
        }
    }


    public java.lang.Integer getSearchDayDuration() {
        return search.getDayDuration();
    }

    public void setSearchDayDuration(java.lang.Integer val) {
        if (search.isDayDurationSet()) {
            search.setDayDuration(val);
        }
    }

    public boolean isSearchDayDurationValid() {
        return search.isDayDurationSet();
    }

    public void setSearchDayDurationValid(boolean val) {
        if (val) {
            search.setDayDuration(search.getDayDuration());
        } else {
            search.unsetDayDuration();
        }
    }


    public String getSearchContractObservations() {
        return search.getContractObservations();
    }

    public void setSearchContractObservations(String val) {
        if (search.isContractObservationsSet()) {
            search.setContractObservations(val);
        }
    }

    public boolean isSearchContractObservationsValid() {
        return search.isContractObservationsSet();
    }

    public void setSearchContractObservationsValid(boolean val) {
        if (val) {
            search.setContractObservations(search.getContractObservations());
        } else {
            search.unsetContractObservations();
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


    public Role getSearchRole() {
        return search.getRole();
    }

    public void setSearchRole(Role val) {
        if (search.isRoleSet()) {
            search.setRole(val);
        }
    }

    public boolean isSearchRoleValid() {
        return search.isRoleSet();
    }

    public void setSearchRoleValid(boolean val) {
        if (val) {
            search.setRole(search.getRole());
        } else {
            search.unsetRole();
        }
    }


    public UserCategory getSearchCategory() {
        return search.getCategory();
    }

    public void setSearchCategory(UserCategory val) {
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


    public DocumentCategory getSearchDocumentCategory() {
        return search.getDocumentCategory();
    }

    public void setSearchDocumentCategory(DocumentCategory val) {
        if (search.isDocumentCategorySet()) {
            search.setDocumentCategory(val);
        }
    }

    public boolean isSearchDocumentCategoryValid() {
        return search.isDocumentCategorySet();
    }

    public void setSearchDocumentCategoryValid(boolean val) {
        if (val) {
            search.setDocumentCategory(search.getDocumentCategory());
        } else {
            search.unsetDocumentCategory();
        }
    }


    public Department getSearchDepartment() {
        return search.getDepartment();
    }

    public void setSearchDepartment(Department val) {
        if (search.isDepartmentSet()) {
            search.setDepartment(val);
        }
    }

    public boolean isSearchDepartmentValid() {
        return search.isDepartmentSet();
    }

    public void setSearchDepartmentValid(boolean val) {
        if (val) {
            search.setDepartment(search.getDepartment());
        } else {
            search.unsetDepartment();
        }
    }


    public ContractType getSearchContractType() {
        return search.getContractType();
    }

    public void setSearchContractType(ContractType val) {
        if (search.isContractTypeSet()) {
            search.setContractType(val);
        }
    }

    public boolean isSearchContractTypeValid() {
        return search.isContractTypeSet();
    }

    public void setSearchContractTypeValid(boolean val) {
        if (val) {
            search.setContractType(search.getContractType());
        } else {
            search.unsetContractType();
        }
    }


    public WorkingAgreement getSearchAgreement() {
        return search.getAgreement();
    }

    public void setSearchAgreement(WorkingAgreement val) {
        if (search.isAgreementSet()) {
            search.setAgreement(val);
        }
    }

    public boolean isSearchAgreementValid() {
        return search.isAgreementSet();
    }

    public void setSearchAgreementValid(boolean val) {
        if (val) {
            search.setAgreement(search.getAgreement());
        } else {
            search.unsetAgreement();
        }
    }

    /**
     * Handle an ABC pager letter click: filter objects by specified starting letter
     */
    public void letterClicked() {
        if (letter != null) {
            UIComponent comp = FacesUtils.getComponent("users:list");
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

    public void setUploadPhoto(UploadedFile uploadPhoto) {
        if (uploadPhoto != null) {
            oldPhoto = getUser().getPhoto();
            this.uploadPhoto = uploadPhoto;
            setPhoto(FileUtil.getFileName(uploadPhoto.getName()));
        }
    }

    public UploadedFile getUploadPhoto() {
        return uploadPhoto;
    }


    // Getters and setters to manipulate active User object
    public java.lang.Integer getId() {
        return getUser().getId();
    }


    public boolean isActive() {
        return getUser().isActive();
    }

    public void setActive(boolean active) {
        getUser().setActive(active);
    }


    public String getLogin() {
        return getUser().getLogin();
    }

    public void setLogin(String login) {
        getUser().setLogin(login);
    }


    public String getPassword() {
        return getUser().getPassword();
    }

    public void setPassword(String password) {
        getUser().setPassword(password);
    }


    public String getName() {
        return getUser().getName();
    }

    public void setName(String name) {
        getUser().setName(name);
    }


    public Date getStartDate() {
        return getUser().getStartDate();
    }

    public void setStartDate(Date startDate) {
        getUser().setStartDate(startDate);
    }


    public String getNif() {
        return getUser().getNif();
    }

    public void setNif(String nif) {
        getUser().setNif(nif);
    }


    public UserGenre getGenre() {
        return getUser().getGenre();
    }

    public void setGenre(UserGenre genre) {
        getUser().setGenre(genre);
    }


    public String getSocialSecurityNumber() {
        return getUser().getSocialSecurityNumber();
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        getUser().setSocialSecurityNumber(socialSecurityNumber);
    }


    public boolean isWorkingInClient() {
        return getUser().isWorkingInClient();
    }

    public void setWorkingInClient(boolean workingInClient) {
        getUser().setWorkingInClient(workingInClient);
    }


    public Date getBirthDate() {
        return getUser().getBirthDate();
    }

    public void setBirthDate(Date birthDate) {
        getUser().setBirthDate(birthDate);
    }


    public boolean isMarried() {
        return getUser().isMarried();
    }

    public void setMarried(boolean married) {
        getUser().setMarried(married);
    }


    public int getChildrenNumber() {
        return getUser().getChildrenNumber();
    }

    public void setChildrenNumber(int childrenNumber) {
        getUser().setChildrenNumber(childrenNumber);
    }


    public String getTravelAvailability() {
        return getUser().getTravelAvailability();
    }

    public void setTravelAvailability(String travelAvailability) {
        getUser().setTravelAvailability(travelAvailability);
    }


    public String getAcademicQualification() {
        return getUser().getAcademicQualification();
    }

    public void setAcademicQualification(String academicQualification) {
        getUser().setAcademicQualification(academicQualification);
    }


    public String getEmail() {
        return getUser().getEmail();
    }

    public void setEmail(String email) {
        getUser().setEmail(email);
    }


    public String getPhone() {
        return getUser().getPhone();
    }

    public void setPhone(String phone) {
        getUser().setPhone(phone);
    }


    public String getMobile() {
        return getUser().getMobile();
    }

    public void setMobile(String mobile) {
        getUser().setMobile(mobile);
    }


    public String getStreet() {
        return getUser().getStreet();
    }

    public void setStreet(String street) {
        getUser().setStreet(street);
    }


    public String getPostalCode() {
        return getUser().getPostalCode();
    }

    public void setPostalCode(String postalCode) {
        getUser().setPostalCode(postalCode);
    }


    public String getCity() {
        return getUser().getCity();
    }

    public void setCity(String city) {
        getUser().setCity(city);
    }


    public String getBank() {
        return getUser().getBank();
    }

    public void setBank(String bank) {
        getUser().setBank(bank);
    }


    public String getAccount() {
        return getUser().getAccount();
    }

    public void setAccount(String account) {
        getUser().setAccount(account);
    }


    public BigDecimal getSalary() {
        return getUser().getSalary();
    }

    public void setSalary(BigDecimal salary) {
        getUser().setSalary(salary);
    }


    public BigDecimal getSalaryExtras() {
        return getUser().getSalaryExtras();
    }

    public void setSalaryExtras(BigDecimal salaryExtras) {
        getUser().setSalaryExtras(salaryExtras);
    }


    public String getDrivenLicenseType() {
        return getUser().getDrivenLicenseType();
    }

    public void setDrivenLicenseType(String drivenLicenseType) {
        getUser().setDrivenLicenseType(drivenLicenseType);
    }


    public String getVehicleType() {
        return getUser().getVehicleType();
    }

    public void setVehicleType(String vehicleType) {
        getUser().setVehicleType(vehicleType);
    }


    public String getLicensePlate() {
        return getUser().getLicensePlate();
    }

    public void setLicensePlate(String licensePlate) {
        getUser().setLicensePlate(licensePlate);
    }


    public String getSecurityCard() {
        return getUser().getSecurityCard();
    }

    public void setSecurityCard(String securityCard) {
        getUser().setSecurityCard(securityCard);
    }


    public String getHealthInsurance() {
        return getUser().getHealthInsurance();
    }

    public void setHealthInsurance(String healthInsurance) {
        getUser().setHealthInsurance(healthInsurance);
    }


    public String getNotes() {
        return getUser().getNotes();
    }

    public void setNotes(String notes) {
        getUser().setNotes(notes);
    }


    public String getPhoto() {
        return getUser().getPhoto();
    }

    public void setPhoto(String photo) {
        getUser().setPhoto(photo);
    }


    public Date getEndTestPeriodDate() {
        return getUser().getEndTestPeriodDate();
    }

    public void setEndTestPeriodDate(Date endTestPeriodDate) {
        getUser().setEndTestPeriodDate(endTestPeriodDate);
    }


    public Date getEndContractDate() {
        return getUser().getEndContractDate();
    }

    public void setEndContractDate(Date endContractDate) {
        getUser().setEndContractDate(endContractDate);
    }


    public int getDayDuration() {
        return getUser().getDayDuration();
    }

    public void setDayDuration(int dayDuration) {
        getUser().setDayDuration(dayDuration);
    }


    public String getContractObservations() {
        return getUser().getContractObservations();
    }

    public void setContractObservations(String contractObservations) {
        getUser().setContractObservations(contractObservations);
    }


    public Date getInsertDate() {
        return getUser().getInsertDate();
    }

    public void setInsertDate(Date insertDate) {
        getUser().setInsertDate(insertDate);
    }


    public Date getUpdateDate() {
        return getUser().getUpdateDate();
    }

    public void setUpdateDate(Date updateDate) {
        getUser().setUpdateDate(updateDate);
    }


    public Role getRole() {
        return getUser().getRole();
    }

    public void setRole(Role role) {
        getUser().setRole(role);
    }


    public UserCategory getCategory() {
        return getUser().getCategory();
    }

    public void setCategory(UserCategory category) {
        getUser().setCategory(category);
    }


    public Province getProvince() {
        return getUser().getProvince();
    }

    public void setProvince(Province province) {
        getUser().setProvince(province);
    }


    public DocumentCategory getDocumentCategory() {
        return getUser().getDocumentCategory();
    }

    public void setDocumentCategory(DocumentCategory documentCategory) {
        getUser().setDocumentCategory(documentCategory);
    }


    public Department getDepartment() {
        return getUser().getDepartment();
    }

    public void setDepartment(Department department) {
        getUser().setDepartment(department);
    }


    public ContractType getContractType() {
        return getUser().getContractType();
    }

    public void setContractType(ContractType contractType) {
        getUser().setContractType(contractType);
    }


    public WorkingAgreement getAgreement() {
        return getUser().getAgreement();
    }

    public void setAgreement(WorkingAgreement agreement) {
        getUser().setAgreement(agreement);
    }

/* user - generated by stajanov (do not edit/delete) */

    public boolean isAmI() {
        return (SpringUtils.getPrincipal().getId() == getUser().getId().intValue());
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static Uploader getUploader() {
        return uploader;
    }

    public String deleteFile() throws IOException {
        if (user != null && user.getId() != null && !"".equals(user.getPhoto()))
            uploader.delete(String.valueOf(user.getId()), user.getPhoto());
        user.setPhoto(null);
        return null; //return to same page
    }
}
