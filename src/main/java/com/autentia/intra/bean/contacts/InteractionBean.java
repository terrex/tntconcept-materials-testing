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
import com.autentia.intra.dao.hibernate.InteractionDAO;
import com.autentia.intra.dao.hibernate.OrganizationDAO;
import com.autentia.intra.dao.search.InteractionSearch;
import com.autentia.intra.dao.search.OfferSearch;
import com.autentia.intra.manager.admin.ProjectManager;
import com.autentia.intra.manager.admin.UserManager;
import com.autentia.intra.manager.contacts.InteractionManager;
import com.autentia.intra.manager.contacts.InteractionTypeManager;
import com.autentia.intra.manager.contacts.OfferManager;
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

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * UI bean for Interaction objects.
 *
 * @author stajanov code generator
 */
public class InteractionBean extends BaseBean {
    /**
     * Serial version field
     */
    private static final long serialVersionUID = -1L;

    private OrganizationDAO organizationDAO = new OrganizationDAO();

    private static AuthenticationManager authMgr = AuthenticationManager.getDefault();

    /**
     * Get the list of all projects
     *
     * @return the list of all projects
     */
    public List<SelectItem> getOpenprojects() {
        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();

        List<Project> refs = ProjectManager.getDefault().getOpenProjects(new SortCriteria("name"));
        for (Project proy : refs) {
            ret.add(new SelectItem(proy, proy.getNameDescriptivo()));
        }
        return ret;
    }

    public List<Interaction> getAllMine() {
        int actualUserId = authMgr.getCurrentPrincipal().getUser().getRole().getId();
        if (actualUserId != ConfigurationUtil.getDefault().getRoleAdminId() &&
                actualUserId != ConfigurationUtil.getDefault().getRoleSupervisorId()) {
            search.setUser(authMgr.getCurrentPrincipal().getUser());
        }
        return manager.getAllEntities(search, new SortCriteria(sortColumn, sortAscending));
    }


    /**
     * Get the list of all organizations
     *
     * @return the list of all organizations
     */
    public List<SelectItem> getOrganizations() {
        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        List<Organization> refs = organizationDAO.search(new SortCriteria("name"));
        for (Organization ref : refs) {
            ret.add(new SelectItem(ref, ref.getName() + (ref.getAcronimo() == null || ref.getAcronimo().equals("") ? "" : " (" + ref.getAcronimo() + ")")));
        }
        ret.add(0, new SelectItem(""));
        return ret;
    }

    /**
     * Selected organization *
     */
    private Organization selectedOrganization;

    public Organization getSelectedOrganization() {
        interaction = (Interaction) InteractionDAO.getDefault().merge(interaction);
        if (interaction != null && interaction.getClient() != null) {
            if (selectedOrganization == null) {
                return interaction.getClient();
            }
            return selectedOrganization;
        }

        if (interaction != null && interaction.getProject() != null) {
            if (selectedOrganization == null) {
                selectedOrganization = interaction.getProject().getClient();
            }
            return selectedOrganization;
        }

        /*
      if (selectedOrganization == null) {
          OrganizationDAO organizationDAO = new OrganizationDAO();
          selectedOrganization = organizationDAO.getById(ConfigurationUtil.getDefault().getIdOurCompany());
      }
        */

        return selectedOrganization;
    }

    public void setSelectedOrganization(Organization organization) {
        selectedOrganization = organization;

        if (interaction != null)
            interaction.setClient(organization);
    }

    public List<SelectItem> getProjectsBySelectedOrganization() {

        List<Project> refs = ProjectManager.getDefault().getProjectsByOrganization(getSelectedOrganization());
        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        for (Object ref : refs) {
            Project proy = (Project) ref;
            String asterisco = proy.isFinished() ? "(*)" : "";
            ret.add(new SelectItem(proy, proy.getNameDescriptivo() + asterisco));
        }
        ret.add(0, new SelectItem(""));

        return ret;
    }


    public List<SelectItem> getOffersBySelectedOrganization() {
        OfferSearch os = new OfferSearch();
        os.setOrganization(getSelectedOrganization());
        List<Offer> refs = OfferManager.getDefault().getAllEntities(os, new SortCriteria("number"));
        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        for (Offer ref : refs) {
            ret.add(new SelectItem(ref, ref.getNumber() + " " + ref.getTitle()));
        }
        ret.add(0, new SelectItem(""));

        return ret;
    }


    public void onSelectedOrganizationChanged(ValueChangeEvent event) {
        setSelectedOrganization((Organization) event.getNewValue());
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

/* interaction - generated by stajanov (do not edit/delete) */


    /**
     * Logger
     */
    private static final Log log = LogFactory.getLog(InteractionBean.class);

    /**
     * Active search object
     */
    private InteractionSearch search = new InteractionSearch();

    /**
     * Manager
     */
    private static InteractionManager manager = InteractionManager.getDefault();

    /**
     * Upload service
     */
    private static final Uploader uploader = UploaderFactory.getInstance("interaction");


    /**
     * Temporary file field
     */
    private String oldFile;

    /**
     * Uploaded file object
     */
    private UploadedFile uploadFile;


    /**
     * Active Interaction object
     */
    private Interaction interaction;

    /**
     * Default sort column
     */
    private String sortColumn = "creationDate";

    /**
     * Default sort order
     */
    private boolean sortAscending = false;


    /**
     * List interactions. Order depends on Faces parameter sort.
     *
     * @return the list of all interactions sorted by requested criterion
     */
    public List<Interaction> getAll() {
        return manager.getAllEntities(search, new SortCriteria(sortColumn, sortAscending));
    }

    // Getters to list possible values of related entities


    /**
     * Get the list of all projects
     *
     * @return the list of all projects
     */
    public List<SelectItem> getProjects() {
        List<Project> refs = ProjectManager.getDefault().getAllEntities(null, new SortCriteria("referenciaLaboratorio"));

        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        for (Project ref : refs) {
            ret.add(new SelectItem(ref, ref.getNameDescriptivo()));
        }
        return ret;
    }


    /**
     * Get the list of all types
     *
     * @return the list of all types
     */
    public List<SelectItem> getTypes() {
        List<InteractionType> refs = InteractionTypeManager.getDefault().getAllEntities(null, new SortCriteria("name"));

        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        for (InteractionType ref : refs) {
            ret.add(new SelectItem(ref, ref.getName()));
        }
        return ret;
    }


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
     * Get the list of all offers
     *
     * @return the list of all offers
     */
    public List<SelectItem> getOffers() {
        OfferSearch os = new OfferSearch();
        os.setOrganization(selectedOrganization);
        List<Offer> refs = OfferManager.getDefault().getAllEntities(os, new SortCriteria("title"));

        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        for (Offer ref : refs) {
            ret.add(new SelectItem(ref, ref.getTitle()));
        }
        ret.add(0, new SelectItem(""));
        return ret;
    }

    // Getters to list possible values of enum fields

    public String getInterestFormatted() {
        if (this.getInterest() != null) {
            return FacesUtils.formatMessage("InteractionInterest." + this.getInterest().name());
        } else {
            return "";
        }
    }

    /**
     * Get the list of all Interest values
     *
     * @return the list of all Interest values
     */
    public List<SelectItem> getInterests() {
        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        InteractionInterest[] vals = InteractionInterest.values();
        for (InteractionInterest val : vals) {
            ret.add(new SelectItem(val, FacesUtils.formatMessage("InteractionInterest." + val.name())));
        }
        return ret;
    }

    public String getMedioFormatted() {
        if (this.getMedio() != null) {
            return FacesUtils.formatMessage("InteractionMedio." + this.getMedio().name());
        } else {
            return "";
        }
    }

    /**
     * Get the list of all Medio values
     *
     * @return the list of all Medio values
     */
    public List<SelectItem> getMedios() {
        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        InteractionMedio[] vals = InteractionMedio.values();
        for (InteractionMedio val : vals) {
            ret.add(new SelectItem(val, FacesUtils.formatMessage("InteractionMedio." + val.name())));
        }
        return ret;
    }

    // Methods to create/remove instances of one-to-many entities (slave entities)

    /**
     * Whether or not create button is available for user
     *
     * @return true if user can create objects of type Interaction
     */
    public boolean isCreateAvailable() {
        return SpringUtils.isRolePermissionGranted(Permission.Entity_Create(Interaction.class));
    }

    /**
     * Whether or not edit button is available for user
     *
     * @return true if user can edit current object
     */
    public boolean isEditAvailable() {
        return SpringUtils.isAclPermissionGranted(interaction, BasePermission.WRITE);
    }

    /**
     * Whether or not delete button is available for user
     *
     * @return true if user can delete current object
     */
    public boolean isDeleteAvailable() {
        return (interaction.getId() != null) &&
                SpringUtils.isAclPermissionGranted(interaction, BasePermission.DELETE);
    }

    /**
     * Go to create page
     *
     * @return forward to CREATE page
     */
    public String create() {
        interaction = new Interaction();
        // Usuario
        if (getUser() == null) {
            setUser(authMgr.getCurrentPrincipal().getUser());
        }
        interaction.setCreationDate(new Date());
        interaction.setMedio(InteractionMedio.EMAIL);
        return NavigationResults.CREATE;
    }

    /**
     * Go to detail page
     *
     * @return forward to DETAIL page
     */
    public String detail() {
        Integer id = Integer.parseInt(FacesUtils.getRequestParameter("id"));
        interaction = manager.getEntityById(id);
        selectedOrganization = null;

        return SpringUtils.isAclPermissionGranted(interaction, BasePermission.WRITE)
                ? NavigationResults.EDIT
                : NavigationResults.DETAIL;
    }

    public String doBeforeSave() {
        if (interaction.getOffer() != null && interaction.getOffer().getId() == null)
            interaction.setOffer(null);
        if (interaction.getOffer() != null && interaction.getOffer().getId() != null) {
            Offer offer = interaction.getOffer();
            if (offer.getInteractions() == null) {
                offer.setInteractions(new HashSet());
            }
            offer.getInteractions().add(interaction);
        }
        if (interaction.getProject() != null && interaction.getProject().getId() == null)
            interaction.setProject(null);

        return super.doBeforeSave();
    }

    /**
     * Save bean and stay on it
     *
     * @return forward to list page
     */
    public String save() {

        doBeforeSave();

        if (interaction.getId() == null) {
            manager.insertEntity(interaction);
        } else {
            manager.updateEntity(interaction);
        }

        // Handle uploads for file field
        if (uploadFile != null) {
            try {
                uploader.replace(Integer.toString(interaction.getId()), oldFile, uploadFile);
            } catch (IOException e) {
                log.error("save - exception uploading field file", e);
                FacesUtils.addErrorMessage("file", "error.fileTransfer", e.getMessage());
            }
        }

        // Calls an after save action
        String result = doAfterSave(NavigationResults.LIST);

        // Unselect object
        //interaction = null;

        return null;
    }

    /**
     * Delete bean and go back to beans list
     *
     * @return forward to LIST page
     */
    public String delete() {
        manager.deleteEntity(interaction);
        interaction = null;
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
    public boolean isInteractionSelected() {
        return interaction != null;
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
    public InteractionSearch getSearch() {
        return search;
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


    public InteractionInterest getSearchInterest() {
        return search.getInterest();
    }

    public void setSearchInterest(InteractionInterest val) {
        if (search.isInterestSet()) {
            search.setInterest(val);
        }
    }

    public boolean isSearchInterestValid() {
        return search.isInterestSet();
    }

    public void setSearchInterestValid(boolean val) {
        if (val) {
            search.setInterest(search.getInterest());
        } else {
            search.unsetInterest();
        }
    }


    public InteractionMedio getSearchMedio() {
        return search.getMedio();
    }

    public void setSearchMedio(InteractionMedio val) {
        if (search.isMedioSet()) {
            search.setMedio(val);
        }
    }

    public boolean isSearchMedioValid() {
        return search.isMedioSet();
    }

    public void setSearchMedioValid(boolean val) {
        if (val) {
            search.setMedio(search.getMedio());
        } else {
            search.unsetMedio();
        }
    }


    public String getSearchWhy() {
        return search.getWhy();
    }

    public void setSearchWhy(String val) {
        if (search.isWhySet()) {
            search.setWhy(val);
        }
    }

    public boolean isSearchWhyValid() {
        return search.isWhySet();
    }

    public void setSearchWhyValid(boolean val) {
        if (val) {
            search.setWhy(search.getWhy());
        } else {
            search.unsetWhy();
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


    public Project getSearchProject() {
        return search.getProject();
    }

    public void setSearchProject(Project val) {
/*
        if (search.isProjectSet()) {
            search.setProject(val);
        }
*/
        search.setProject(val);
    }

    public boolean isSearchProjectValid() {
        return search.isProjectSet() || selectedOrganization != null;
    }

    public void setSearchProjectValid(boolean val) {
        if (val) {
            search.setProject(search.getProject());
        } else {
            search.unsetProject();
        }
    }


    public Organization getSearchClient() {
        return search.getClient();
    }

    public void setSearchClient(Organization val) {
/*
        if (search.isClientSet()) {
            search.setClient(val);
        }
*/
        search.setClient(val);
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


    public InteractionType getSearchType() {
        return search.getType();
    }

    public void setSearchType(InteractionType val) {
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

    // Getters and setters to handle uploads

    public void setUploadFile(UploadedFile uploadFile) {
        if (uploadFile != null) {
            oldFile = interaction.getFile();
            this.uploadFile = uploadFile;
            setFile(FileUtil.getFileName(uploadFile.getName()));
            setFileMime(uploadFile.getContentType());
        }
    }

    public UploadedFile getUploadFile() {
        return uploadFile;
    }


    // Getters and setters to manipulate active Interaction object
    public java.lang.Integer getId() {
        return interaction.getId();
    }


    public Date getCreationDate() {
        return interaction.getCreationDate();
    }

    public void setCreationDate(Date creationDate) {
        interaction.setCreationDate(creationDate);
    }


    public InteractionInterest getInterest() {
        return interaction.getInterest();
    }

    public void setInterest(InteractionInterest interest) {
        interaction.setInterest(interest);
    }


    public InteractionMedio getMedio() {
        return interaction.getMedio();
    }

    public void setMedio(InteractionMedio medio) {
        interaction.setMedio(medio);
    }


    public String getWhy() {
        return interaction.getWhy();
    }

    public void setWhy(String why) {
        interaction.setWhy(why);
    }


    public String getDescription() {
        return interaction.getDescription();
    }

    public void setDescription(String description) {
        interaction.setDescription(description);
    }


    public String getFile() {
        return interaction.getFile();
    }

    public void setFile(String file) {
        interaction.setFile(file);
    }


    public String getFileMime() {
        return interaction.getFileMime();
    }

    public void setFileMime(String fileMime) {
        interaction.setFileMime(fileMime);
    }


    public String getObservations() {
        return interaction.getObservations();
    }

    public void setObservations(String observations) {
        interaction.setObservations(observations);
    }


    public Integer getDepartmentId() {
        return interaction.getDepartmentId();
    }

    public void setDepartmentId(Integer departmentId) {
        interaction.setDepartmentId(departmentId);
    }


    public Date getInsertDate() {
        return interaction.getInsertDate();
    }

    public void setInsertDate(Date insertDate) {
        interaction.setInsertDate(insertDate);
    }


    public Date getUpdateDate() {
        return interaction.getUpdateDate();
    }

    public void setUpdateDate(Date updateDate) {
        interaction.setUpdateDate(updateDate);
    }


    public Project getProject() {
        return interaction.getProject();
    }

    public void setProject(Project project) {
        interaction.setProject(project);
    }


    public Organization getClient() {
        return interaction.getClient();
    }

    public void setClient(Organization client) {
        interaction.setClient(client);
    }


    public InteractionType getType() {
        return interaction.getType();
    }

    public void setType(InteractionType type) {
        interaction.setType(type);
    }


    public User getUser() {
        return interaction.getUser();
    }

    public void setUser(User user) {
        interaction.setUser(user);
    }


    public Offer getOffer() {
        return interaction.getOffer();
    }

    public void setOffer(Offer offer) {
        interaction.setOffer(offer);
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

/* interaction - generated by stajanov (do not edit/delete) */


    public void setInteractionFromOffer(Interaction i) {
        this.interaction = i;
    }

    /**
     * Save bean and stay on it
     *
     * @return forward to list page
     */
    public String saveFromOffer() {


        if (interaction.getId() == null) {
            manager.insertEntity(interaction);
        } else {
            manager.updateEntity(interaction);
        }

        // Handle uploads for file field
        if (uploadFile != null) {
            try {
                uploader.replace(Integer.toString(interaction.getId()), oldFile, uploadFile);
            } catch (IOException e) {
                log.error("save - exception uploading field file", e);
                FacesUtils.addErrorMessage("file", "error.fileTransfer", e.getMessage());
            }
        }

        // Unselect object
        interaction = null;

        return NavigationResults.OFFER_SAVE_INTERACTION;
    }

    /**
     * Save bean and stay on it
     *
     * @return forward to list page
     */
    public String listFromOffer() {

        // Unselect object
        interaction = null;

        return NavigationResults.OFFER_LIST_INTERACTION;
    }


    public static Uploader getUploader() {
        return uploader;
    }

    public String deleteFile() throws IOException {
        if (interaction != null && interaction.getId() != null && !"".equals(interaction.getFile()))
            uploader.delete(String.valueOf(interaction.getId()), interaction.getFile());
        interaction.setFile(null);
        interaction.setFileMime(null);
        return null; //return to same page
    }

    public Interaction getInteraction() {
        return interaction;
    }
}
