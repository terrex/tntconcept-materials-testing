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
package com.autentia.intra.bean.activity;

import com.autentia.intra.bean.BaseBean;
import com.autentia.intra.bean.NavigationResults;
import com.autentia.intra.bean.admin.SettingBean;
import com.autentia.intra.businessobject.*;
import com.autentia.intra.dao.SortCriteria;
import com.autentia.intra.dao.hibernate.ProjectEnsayoDAO;
import com.autentia.intra.dao.hibernate.ProjectRoleDAO;
import com.autentia.intra.dao.hibernate.UserDAO;
import com.autentia.intra.dao.search.ActivitySearch;
import com.autentia.intra.dao.search.HolidaySearch;
import com.autentia.intra.dao.search.OrganizationSearch;
import com.autentia.intra.dao.search.RequestHolidaySearch;
import com.autentia.intra.jsf.schedule.renderer.ActivityScheduleEntryRenderer;
import com.autentia.intra.manager.activity.ActivityManager;
import com.autentia.intra.manager.admin.ProjectEnsayoManager;
import com.autentia.intra.manager.admin.ProjectManager;
import com.autentia.intra.manager.admin.ProjectRoleManager;
import com.autentia.intra.manager.admin.SettingManager;
import com.autentia.intra.manager.contacts.OrganizationManager;
import com.autentia.intra.manager.holiday.HolidayManager;
import com.autentia.intra.manager.holiday.RequestHolidayManager;
import com.autentia.intra.manager.security.AuthenticationManager;
import com.autentia.intra.manager.security.Permission;
import com.autentia.intra.upload.Uploader;
import com.autentia.intra.upload.UploaderFactory;
import com.autentia.intra.util.DateUtils;
import com.autentia.intra.util.FacesUtils;
import com.autentia.intra.util.SettingPath;
import com.autentia.intra.util.SpringUtils;
import org.acegisecurity.acls.domain.BasePermission;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.custom.schedule.ScheduleMouseEvent;
import org.apache.myfaces.custom.schedule.model.ScheduleModel;
import org.apache.myfaces.custom.schedule.model.SimpleScheduleModel;
import org.apache.myfaces.custom.schedule.renderer.ScheduleEntryRenderer;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.component.html.HtmlSelectOneListbox;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import java.util.*;

/**
 * UI bean for Activity objects.
 *
 * @author stajanov code generator
 */
public class ActivityBean extends BaseBean {

    private boolean defaultBillable = false;


    /**
     * inner comparator class. comparares activities to order a list for start and duration
     *
     * @author german
     */
    private class compareActivitiesByStartAndDuration implements Comparator<Activity> {

        Calendar calInst1 = Calendar.getInstance();
        Calendar calInst2 = Calendar.getInstance();

        public int compare(Activity a1, Activity a2) {

            calInst1.setTime(a1.getStartDate());
            calInst2.setTime(a2.getStartDate());

            calInst1.add(Calendar.MINUTE, a1.getDuration());
            calInst2.add(Calendar.MINUTE, a2.getDuration());

            return (int) (calInst2.getTimeInMillis() - calInst1.getTimeInMillis());
        }

    }


    /**
     * Manager
     */
    private static ActivityManager manager = ActivityManager
            .getDefault();
    /**
     * Serial version field
     */
    private static final long serialVersionUID = 1L;

    /**
     * Selected date in bitacore window
     */
    private Date selectedDate = new Date();

    /**
     * Project DAO *
     */
    private static final ProjectManager projectManager = ProjectManager
            .getDefault();

    /**
     * Organization DAO *
     */
    private static final OrganizationManager organizationManager = OrganizationManager
            .getDefault();

    /**
     * Active search object
     */
    private OrganizationSearch organizationSearch = new OrganizationSearch();

    /**
     * Selected organization *
     */
    private Organization selectedOrganization = null;

    private List<Organization> organizations = new ArrayList<Organization>();

    /**
     * List of projects
     */
    private List<Project> projects = new ArrayList<Project>();

    private static AuthenticationManager authManager = AuthenticationManager
            .getDefault();

    private static HolidayManager holidayManager = HolidayManager
            .getDefault();

    /**
     * Settings manager
     */
    private static final SettingManager settings = SettingManager
            .getDefault();

    /**
     * Role manager
     */
    private static final ProjectRoleManager projectRoleMgr = ProjectRoleManager
            .getDefault();

    /**
     * Ensayo manager
     */
    private static final ProjectEnsayoManager projectEnsayoMgr = ProjectEnsayoManager.getDefault();

    /**
     * Selected project
     */
    private Project selectedProject;

    /**
     * data model
     */
    private SimpleScheduleModel scheduleModel = new SimpleScheduleModel();

    /**
     * to reload model
     */
    private boolean reloadModel = true;

    private String method = null;

    private final ScheduleEntryRenderer entryRenderer = new ActivityScheduleEntryRenderer();


    private final Calendar cal = Calendar.getInstance();

    /**
     * Constructor
     */
    public ActivityBean() {

        // Only show entries for current user
        search.setUser(authManager.getCurrentPrincipal().getUser());

    }

    /**
     * Get date selected in bitacore page
     *
     * @return date selected in bitacore page
     */
    public Date getSelectedDate() {
        return selectedDate;
    }

    /**
     * Set date selected in bitacore page
     *
     * @param date date selected in bitacore page
     */
    public void setSelectedDate(Date date) {
        if (date != null) {

            Calendar newCal = Calendar.getInstance();

            cal.setTime(selectedDate);
            newCal.setTime(date);

            if (cal.get(Calendar.MONTH) != newCal.get(Calendar.MONTH)
                    || cal.get(Calendar.YEAR) != newCal.get(Calendar.YEAR)) {
                reloadModel = true;
            }

            selectedDate = date;
            scheduleModel.setSelectedDate(selectedDate);
            scheduleModel.refresh();
        }
    }

    /**
     * Get the list of all projects
     *
     * @return the list of all projects
     */
    public List<SelectItem> getProjects() {
        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        List<Project> refs = projectManager.getAllEntities(null, new SortCriteria("referenciaLaboratorio"));
        for (Project ref : refs) {
            ret.add(new SelectItem(ref, ref.getNameDescriptivo()));
        }
        return ret;
    }

    public Project getSelectedProject() {
        if (selectedProject == null) {
            if (projects.size() != 0) {
                selectedProject = projects.get(0);
            }
        }
        if (activity != null) {
            if (activity.getProject() != null)
                selectedProject = activity.getProject();
        }
        return selectedProject;
    }

    public List<SelectItem> getRolesBySelectedProject() {

        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        if (projects.size() == 0) {
            return ret;
        }

        Set<ProjectRole> refs = getSelectedProject().getRoles();

        for (ProjectRole ref : refs) {
            ret.add(new SelectItem(ref, ref.getName()));
        }

        return ret;
    }

    public List<SelectItem> getEnsayosBySelectedProject() {

        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        if (projects.size() == 0) {
            return ret;
        }

        List<ProjectEnsayo> refs = getSelectedProject().getEnsayos();

        for (ProjectEnsayo ref : refs) {
            ret.add(new SelectItem(ref, ref.getEnsayo().getName()));
        }

        return ret;
    }

    public void onEditSelected(ActionEvent event) {
        // setSelectedProject( null );
    }

    /**
     * Set the selectedOrganization value when the combo value changes
     *
     * @param event
     */
    public void onSelectedOrganizationChanged(ValueChangeEvent event) {
        setSelectedOrganization((Organization) event.getNewValue());

        // Project
        List<Project> projectsByOrganization = ProjectManager.getDefault()
                .getProjectsByOrganization(selectedOrganization);
        if (projectsByOrganization != null && projectsByOrganization.size() != 0) {
            this.selectedProject = (Project) projectsByOrganization.get(0);
            FacesContext.getCurrentInstance().getViewRoot().getAttributes();
        } else {
            this.selectedProject = null;
        }

        HtmlSelectOneListbox projects = (HtmlSelectOneListbox) FacesUtils.getComponent("activity")
                .findComponent("projects");
        projects.setValue(selectedProject);

        if (selectedProject != null) {
            HtmlSelectBooleanCheckbox billHtml = (HtmlSelectBooleanCheckbox) FacesUtils.getComponent("activity").findComponent("billable");
            billHtml.setValue(selectedProject.getBillable());
            setBillable(selectedProject.getBillable());


            HtmlInputHidden hiddenHtml = (HtmlInputHidden) FacesUtils.getComponent("activity").findComponent("defaultBillable");
            hiddenHtml.setValue(selectedProject.getBillable());

            setDefaultBillable(selectedProject.getBillable());

        }


        FacesUtils.renderResponse();
    }

    /**
     * Set the projectOrganization value when the combo value changes
     *
     * @param event
     */
    public void onSelectedProjectChanged(ValueChangeEvent event) {
        setSelectedProject((Project) event.getNewValue());

        if (selectedProject != null) {
            HtmlSelectBooleanCheckbox billHtml = (HtmlSelectBooleanCheckbox) FacesUtils.getComponent("activity").findComponent("billable");
            billHtml.setValue(selectedProject.getBillable());
            setBillable(selectedProject.getBillable());


            HtmlInputHidden hiddenHtml = (HtmlInputHidden) FacesUtils.getComponent("activity").findComponent("defaultBillable");
            hiddenHtml.setValue(selectedProject.getBillable());
            setDefaultBillable(selectedProject.getBillable());
        }

        FacesUtils.renderResponse();
    }

    /**
     * Get the list of all projects
     *
     * @return the list of all projects
     */
    public List<SelectItem> getProjectsOpenBySelectedOrganization() {

        List<Project> refs = ProjectManager.getDefault().getOpenProjectsByOrganization(
                selectedOrganization);

        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();

        if (refs == null || refs.size() == 0) {
            projects.clear();
            return ret;
        }

        Project proj = null;
        for (Object ref : refs) {
            proj = (Project) ref;
            projects.add(proj);
            if (!proj.isFinished()) {
                ret.add(new SelectItem(proj, proj.getNameDescriptivo()));
            }
        }

        return ret;
    }

    /**
     * @param selectedOrganization the selectedOrganization to set
     */
    public void setSelectedOrganization(Organization selectedOrganization) {
        // Organization
        this.selectedOrganization = selectedOrganization;

    }

    public void setSelectedProject(Project project) {
        // Project
        this.selectedProject = project;
        if (this.activity != null)
            this.activity.setProject(project);
    }

    /**
     * Get the selectedOrganization value
     *
     * @return a Organization selected
     */
    public Organization getSelectedOrganization() {
        if (selectedOrganization == null) {
            selectedOrganization = organizations.get(0);
        }

        return selectedOrganization;
    }

    /**
     * Get the list of all organizations
     *
     * @return the list of all organizations
     */
    public List<SelectItem> getOrganizations() {
        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        organizations = organizationManager.getAllEntities(organizationSearch, new SortCriteria(
                "name", true));

        for (Organization ref : organizations) {
            ret.add(new SelectItem(ref, ref.getName() + (ref.getAcronimo() == null || ref.getAcronimo().equals("") ? "" : " (" + ref.getAcronimo() + ")")));
        }
        return ret;
    }

    @Override
    public String doAfterSave(String result) {

        Setting val = settings.get(SettingPath.BITACORE_LAST_BILLABLE, true);
        SettingManager.setValue(val, activity.isBillable());
        settings.save(val);

        if (activity.getRole() != null) {
            val = settings.get(SettingPath.BITACORE_LAST_ROLEID, true);
            SettingManager.setValue(val, activity.getRole().getId());
            settings.save(val);
        }

        return super.doAfterSave(result);
    }

    /* activity - generated by stajanov (do not edit/delete) */

    /**
     * Logger
     */
    private static final Log log = LogFactory.getLog(ActivityBean.class);

    // External entities' DAOs

    /**
     * User DAO *
     */
    private static final UserDAO userDAO = UserDAO.getDefault();

    /**
     * ProjectRole DAO *
     */
    private static final ProjectRoleDAO roleDAO = ProjectRoleDAO.getDefault();

    /**
     * ProjectEnsayo DAO *
     */
    private static final ProjectEnsayoDAO ensayoDAO = ProjectEnsayoDAO.getDefault();

    /**
     * Upload service
     */
    private static final Uploader uploader = UploaderFactory
            .getInstance("activity");

    /**
     * Active Activity object
     */
    private Activity activity;

    /**
     * Active search object
     */
    private ActivitySearch search = new ActivitySearch();

    /**
     * Default sort column
     */
    private String sortColumn = "startDate";

    /**
     * Default sort order
     */
    private boolean sortAscending = false;

    private float monthPerformedHours = 0;

    /**
     * List activitys. Order depends on Faces parameter sort.
     *
     * @return the list of all activitys sorted by requested criterion
     */
    public List<Activity> getAll() {

        return manager.getAllEntities(search, new SortCriteria(sortColumn, sortAscending));
        // return activityDAO.search(search, new SortCriteria(sortColumn,
        // sortAscending));
    }

    // Getters to list possible values of related entities

    /**
     * Get the list of all users
     *
     * @return the list of all users
     */
    public List<SelectItem> getUsers() {
        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        List<User> refs = userDAO.search(new SortCriteria("name"));
        for (User ref : refs) {
            ret.add(new SelectItem(ref, ref.getName()));
        }
        return ret;
    }

    /**
     * Get the list of all roles
     *
     * @return the list of all roles
     */
    public List<SelectItem> getRoles() {
        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        List<ProjectRole> refs = roleDAO.search(new SortCriteria("name"));
        for (ProjectRole ref : refs) {
            ret.add(new SelectItem(ref, ref.getName()));
        }
        return ret;
    }

    /**
     * Get the list of all ensayos
     *
     * @return the list of all ensayos
     */
    public List<SelectItem> getEnsayos() {
        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        List<ProjectEnsayo> refs = ensayoDAO.search(new SortCriteria("id"));
        for (ProjectEnsayo ref : refs) {
            ret.add(new SelectItem(ref, ref.getEnsayo().getName()));
        }
        return ret;
    }

    // Getters to list possible values of enum fields

    // Methods to create/remove instances of one-to-many entities (slave
    // entities)

    /**
     * Whether or not create button is available for user
     *
     * @return true if user can create objects of type Account
     */
    public boolean isCreateAvailable() {
        return SpringUtils.isRolePermissionGranted(Permission.Entity_Create(Activity.class));
    }

    /**
     * Whether or not edit button is available for user
     *
     * @return true if user can edit current object
     */
    public boolean isEditAvailable() {
        return SpringUtils.isAclPermissionGranted(activity, BasePermission.WRITE);
    }

    /**
     * Whether or not delete button is available for user
     *
     * @return true if user can delete current object
     */
    public boolean isDeleteAvailable() {
        return (activity.getId() != null)
                && SpringUtils.isAclPermissionGranted(activity, BasePermission.DELETE);
    }

    /**
     * Go to create page
     *
     * @return forward to CREATE page
     */
    public String create() {
        activity = new Activity();
        selectedOrganization = null;
        selectedProject = null;
        organizations.clear();
        projects.clear();

        cal.setTime(scheduleModel.getSelectedDate());

        if (scheduleModel.getMode() == ScheduleModel.MONTH || scheduleModel.getMode() == ScheduleModel.WEEK) {
            cal.set(Calendar.HOUR_OF_DAY, ((SettingBean) FacesUtils.getBean("settingBean")).getMySettings().getWorkingDayHourStarts());
        }

        activity.setStartDate(cal.getTime());
        activity.setDescription("Descripción");
        activity.setUser(authManager.getCurrentPrincipal().getUser());

        // Preselect last selected options
        Setting val = settings.get(SettingPath.BITACORE_LAST_ROLEID, false);
        int projectRoleId = SettingManager.getInt(val, -1);

        Project pr = null;

        if (projectRoleId != -1) {
            ProjectRole projectRole = projectRoleMgr.getEntityById(projectRoleId);
            setRole(projectRole);
            setSelectedProject(projectRole.getProject());
            setSelectedOrganization(getSelectedProject().getClient());
            pr = projectRole.getProject();
        }

        if (pr != null) {
            setBillable(pr.getBillable());
            setDefaultBillable(pr.getBillable());
        } else {
            val = settings.get(SettingPath.BITACORE_LAST_BILLABLE, false);
            setBillable(SettingManager.getBoolean(val, false));
        }

        if (scheduleModel.getMode() == ScheduleModel.MONTH || scheduleModel.getMode() == ScheduleModel.WEEK) {

            ActivitySearch searchToday = new ActivitySearch();

            Calendar initToday = Calendar.getInstance();
            initToday.setTime(scheduleModel.getSelectedDate());
            Calendar lastToday = Calendar.getInstance();
            lastToday.setTime(scheduleModel.getSelectedDate());

            initToday.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
            initToday.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
            initToday.set(Calendar.SECOND, cal.getMinimum(Calendar.SECOND));
            initToday.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));

            lastToday.set(Calendar.HOUR_OF_DAY, cal.getMaximum(Calendar.HOUR_OF_DAY));
            lastToday.set(Calendar.MINUTE, cal.getMaximum(Calendar.MINUTE));
            lastToday.set(Calendar.SECOND, cal.getMaximum(Calendar.SECOND));
            lastToday.set(Calendar.MILLISECOND, cal.getMaximum(Calendar.MILLISECOND));

            searchToday.setStartStartDate(initToday.getTime());
            searchToday.setEndStartDate(lastToday.getTime());

            // Try to obtain the last hour in day.

            searchToday.setUser(authManager.getCurrentPrincipal().getUser());

            List<Activity> activities = manager.getAllEntities(searchToday, new SortCriteria(
                    sortColumn, sortAscending));
            if (activities.size() > 0) {
                //sort is descendent
                Collections.sort(activities, new compareActivitiesByStartAndDuration());
                activity.setStartDate(activities.get(0).getEndDate());
            }

        }

        return NavigationResults.CREATE;
    }

    /**
     * Go to edit page
     *
     * @return forward to EDIT page
     */
    public String edit() {

        Integer id = Integer.parseInt(scheduleModel.getSelectedEntry().getId());
        // activity = activityDAO.getById(id);
        activity = manager.getEntityById(id);


        selectedProject = activity.getProject();

        selectedOrganization = selectedProject.getClient();

        setDefaultBillable(selectedProject.getBillable());

        return NavigationResults.EDIT;
    }

    /**
     * Save bean and stay on it
     *
     * @return forward to list page
     */
    public String save() {

        doBeforeSave();

        if (activity.getId() == null) {
            manager.insertEntity(activity);
        } else {
            manager.updateEntity(activity);
        }

        // Calls an after save action
        String result = doAfterSave(NavigationResults.LIST);

        // Unselect object
        activity = null;
        scheduleModel.setSelectedEntry(null);
        reloadModel = true;
        return result;
    }

    /**
     * Delete bean and go back to beans list
     *
     * @return forward to LIST page
     */
    public String delete() {
        // activityDAO.delete(activity);

        Activity act = manager.getEntityById(activity.getId());
        manager.deleteEntity(act);
        // manager.deleteEntity(activity);
        act = null;
        activity = null;
        scheduleModel.setSelectedEntry(null);
        reloadModel = true;
        scheduleModel.refresh();

        return NavigationResults.LIST;
    }

    /**
     * Go back to beans list
     *
     * @return forward to LIST page
     */
    public String list() {
        activity = null;

        scheduleModel.setSelectedEntry(null);
        reloadModel = true;
        scheduleModel.refresh();

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
    public boolean isActivitySelected() {
        return activity != null;
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
    public ActivitySearch getSearch() {
        return search;
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

    public Integer getSearchDuration() {
        return search.getDuration();
    }

    public void setSearchDuration(Integer val) {
        if (search.isDurationSet()) {
            search.setDuration(val);
        }
    }

    public boolean isSearchDurationValid() {
        return search.isDurationSet();
    }

    public void setSearchDurationValid(boolean val) {
        if (val) {
            search.setDuration(search.getDuration());
        } else {
            search.unsetDuration();
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

    public Boolean getSearchBillable() {
        return search.getBillable();
    }

    public void setSearchBillable(Boolean val) {
        if (search.isBillableSet()) {
            search.setBillable(val);
        }
    }

    public boolean isSearchBillableValid() {
        return search.isBillableSet();
    }

    public void setSearchBillableValid(boolean val) {
        if (val) {
            search.setBillable(search.getBillable());
        } else {
            search.unsetBillable();
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

    public ProjectRole getSearchRole() {
        return search.getRole();
    }

    public void setSearchRole(ProjectRole val) {
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

    // Getters and setters to handle uploads

    // Getters and setters to manipulate active Activity object

    public java.lang.Integer getId() {
        return activity.getId();
    }

    public int getStartTimeHour() {

        cal.clear();
        cal.setTime(getStartDate());

        return cal.get(Calendar.HOUR_OF_DAY);
    }

    public void setStartTimeHour(int startTime) {

        cal.clear();
        cal.setTime(getStartDate());
        cal.set(Calendar.HOUR_OF_DAY, startTime);

        setStartDate(cal.getTime());
    }

    public int getStartTimeMinute() {

        cal.clear();
        cal.setTime(getStartDate());

        return cal.get(Calendar.MINUTE);
    }

    public void setStartTimeMinute(int startTime) {

        cal.clear();
        cal.setTime(getStartDate());
        cal.set(Calendar.MINUTE, startTime);

        setStartDate(cal.getTime());
    }

    public int getEndTimeHour() {

        cal.clear();
        cal.setTime(getEndDate());

        return cal.get(Calendar.HOUR_OF_DAY);
    }

    public void setEndTimeHour(int endTime) {

        cal.clear();
        cal.setTime(getEndDate());
        cal.set(Calendar.HOUR_OF_DAY, endTime);

        setEndDate(cal.getTime());
    }

    public int getEndTimeMinute() {

        cal.clear();
        cal.setTime(getEndDate());

        return cal.get(Calendar.MINUTE);
    }

    public void setEndTimeMinute(int endTime) {

        cal.clear();
        cal.setTime(getEndDate());
        cal.set(Calendar.MINUTE, endTime);

        setEndDate(cal.getTime());
    }

    public Date getStartDate() {
        return activity.getStartDate();
    }

    public void setStartDate(Date startDate) {
        activity.setStartDate(startDate);
    }

    public Date getEndDate() {
        return activity.getEndDate();
    }

    public void setEndDate(Date endDate) {
        activity.setEndDate(endDate);
    }

    public int getDuration() {
        return activity.getDuration();
    }

    public void setDuration(int duration) {
        activity.setDuration(duration);
    }

    public String getDescription() {
        return activity.getDescription();
    }

    public void setDescription(String description) {
        activity.setDescription(description);
    }

    public boolean isBillable() {
        return activity.isBillable();
    }

    public void setBillable(boolean billable) {
        activity.setBillable(billable);
    }

    public User getUser() {
        return activity.getUser();
    }

    public void setUser(User user) {
        activity.setUser(user);
    }

    public ProjectRole getRole() {
        return activity.getRole();
    }

    public void setRole(ProjectRole role) {
        activity.setRole(role);
    }

    public ProjectEnsayo getEnsayo() {
        return activity.getEnsayo();
    }

    public void setEnsayo(ProjectEnsayo ensayo) {
        activity.setEnsayo(ensayo);
    }

    /* activity - generated by stajanov (do not edit/delete) */

    /**
     * @return
     */
    public ScheduleModel getScheduleModel() {

        loadModel();
        return scheduleModel;
    }

    /**
     * Load data from persistence tier to model for efficiency purposes only
     * load a month of data
     */
    /**
     *
     */
    public void loadModel() {

        if (reloadModel) {

            if (log.isTraceEnabled()) {
                log.trace("loading model from persistence tier");
            }

            Calendar calMin = Calendar.getInstance();
            Calendar calMax = Calendar.getInstance();

            scheduleModel = new SimpleScheduleModel();

            fillHolidays(scheduleModel);

            ActivitySearch searchMonth = new ActivitySearch();

            /*
                * normalize date to search only actual month's data and a week of
                * back / next month
                */
            calMin.setTime(selectedDate);
            calMin.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DAY_OF_MONTH));
            calMin.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
            calMin.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
            calMin.set(Calendar.SECOND, cal.getMinimum(Calendar.SECOND));
            calMin.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));

            calMax.setTime(selectedDate);
            calMax.set(Calendar.DAY_OF_MONTH, cal.getMaximum(Calendar.DAY_OF_MONTH));
            calMax.set(Calendar.HOUR_OF_DAY, cal.getMaximum(Calendar.HOUR_OF_DAY));
            calMax.set(Calendar.MINUTE, cal.getMaximum(Calendar.MINUTE));
            calMax.set(Calendar.SECOND, cal.getMaximum(Calendar.SECOND));
            calMax.set(Calendar.MILLISECOND, cal.getMaximum(Calendar.MILLISECOND));

            if (((SettingBean) FacesUtils.getBean("settingBean")).getMySettings().getLoadExtraDays()) {
                calMin.add(Calendar.DAY_OF_MONTH, -7);
                calMax.add(Calendar.DAY_OF_MONTH, 7);
            }

            searchMonth.setStartStartDate(calMin.getTime());
            searchMonth.setEndStartDate(calMax.getTime());
            searchMonth.setUser(authManager.getCurrentPrincipal().getUser());
            /* search data in persistence layer */
            List<Activity> activities = manager.getAllEntities(searchMonth, new SortCriteria(
                    sortColumn, sortAscending));

            /*
                * activityDAO.search(searchMonth, new SortCriteria(sortColumn,
                * sortAscending));
                */

            monthPerformedHours = 0;

            /* load data in component */
            for (Activity actualActivity : activities) {

                calMin.setTime(actualActivity.getStartDate());
                calMax.setTime(selectedDate);

                if (calMin.get(Calendar.MONTH) == calMax.get(Calendar.MONTH)) {
                    monthPerformedHours += actualActivity.getDuration();
                }

                ActivityScheduleEntry entry = new ActivityScheduleEntry(actualActivity);

                scheduleModel.addEntry(entry);
            }

            monthPerformedHours /= 60;

            scheduleModel.setMode(((SettingBean) FacesUtils.getBean("settingBean")).getMySettings().getMode());
            scheduleModel.setSelectedDate(selectedDate);
            scheduleModel.refresh();

            reloadModel = false;
        }

    }

    /**
     * @param event
     */
    public void activityClicked(ScheduleMouseEvent event) {

        setSelectedDate(event.getClickedDate());

        method = null;

        switch (event.getEventType()) {

            case ScheduleMouseEvent.SCHEDULE_BODY_CLICKED:
                method = "create";
                break;
            case ScheduleMouseEvent.SCHEDULE_ENTRY_CLICKED:
                method = "edit";
                break;
            case ScheduleMouseEvent.SCHEDULE_HEADER_CLICKED:
                method = "create";
                break;
            case ScheduleMouseEvent.SCHEDULE_NOTHING_CLICKED:
                break;

            default:
                break;
        }

    }

    /**
     * @return
     */
    public String actionPerformed() {

        Class[] types = new Class[0];
        Object[] args = new Object[0];
        String ret = NavigationResults.LIST;

        if (method != null) {
            try {
                ret = (String) this.getClass().getMethod(method, types).invoke(this, args);
            } catch (Exception e) {
                // doesn't ocurrs
            }
        }

        method = null;

        return ret;
    }

    /**
     * @return
     */
    public ScheduleEntryRenderer getEntryRenderer() {
        return entryRenderer;
    }

    /**
     * @param event
     */
    public void onSchedlueModeChanged(ValueChangeEvent event) {

        scheduleModel.setMode(((Integer) event.getNewValue()).intValue());
        scheduleModel.refresh();
    }


    /**
     * @return
     */
    public String editSettings() {

        return NavigationResults.SETTINGS;
    }


    /**
     * @return
     */
    public float getMonthPerformedHours() {

        /* we must reload model to obtain data */
        reloadModel = true;
        loadModel();
        return monthPerformedHours;
    }

    /**
     * @return
     */
    public float getMonthTotalHours() {

        ArrayList<Integer> diasContemplados = new ArrayList<Integer>();

        cal.setTime(selectedDate);

        float hoursPerDay = ((SettingBean) FacesUtils.getBean("settingBean")).getMySettings().getWorkingHours();

        int weekendsInMonth = 0;

        int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int i = 1; i <= daysInMonth; i++) {
            cal.set(Calendar.DAY_OF_MONTH, i);
            if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
                    || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                weekendsInMonth++;
                diasContemplados.add(i);
            }
        }

        // Restamos días de vacaciones aceptadas y dias de fiesta

        Calendar calMin = Calendar.getInstance();
        Calendar calMax = Calendar.getInstance();
        Calendar calAux = Calendar.getInstance();

        calMin.setTime(selectedDate);
        calMin.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DAY_OF_MONTH));
        calMin.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
        calMin.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
        calMin.set(Calendar.SECOND, cal.getMinimum(Calendar.SECOND));
        calMin.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));

        calMax.setTime(selectedDate);
        calMax.set(Calendar.DAY_OF_MONTH, cal.getMaximum(Calendar.DAY_OF_MONTH));
        calMax.set(Calendar.HOUR_OF_DAY, cal.getMaximum(Calendar.HOUR_OF_DAY));
        calMax.set(Calendar.MINUTE, cal.getMaximum(Calendar.MINUTE));
        calMax.set(Calendar.SECOND, cal.getMaximum(Calendar.SECOND));
        calMax.set(Calendar.MILLISECOND, cal.getMaximum(Calendar.MILLISECOND));


        HolidaySearch monthSearch = new HolidaySearch();

        monthSearch.setStartDate(calMin.getTime());
        monthSearch.setEndDate(calMax.getTime());
        List<Holiday> listaHolidays = holidayManager.getAllEntities(monthSearch, null);


        int holidays = 0;
        for (Holiday holiday : listaHolidays) {
            calAux.setTime(holiday.getDate());
            int day = calAux.get(Calendar.DAY_OF_MONTH);
            if (!diasContemplados.contains(day)) {
                holidays++;
                diasContemplados.add(day);
            }
        }

        int requestedHolidays = 0;
        RequestHolidayManager rhManager = RequestHolidayManager.getDefault();
        RequestHolidaySearch rhSearch = new RequestHolidaySearch();
        rhSearch.setUserRequest(authManager.getCurrentPrincipal().getUser());
        rhSearch.setState(HolidayState.ACCEPT);
        rhSearch.setStartBeginDate(calMin.getTime());
        rhSearch.setEndBeginDate(calMax.getTime());
        rhSearch.setStartFinalDate(calMin.getTime());
        rhSearch.setEndFinalDate(calMax.getTime());

        List<RequestHoliday> listH = rhManager.getAllEntities(rhSearch, null);

        for (RequestHoliday rH : listH) {
            Calendar cActual = Calendar.getInstance();
            cActual.setTime(rH.getBeginDate());
            while (!cActual.getTime().after(rH.getFinalDate())) {
                int day = cActual.get(Calendar.DAY_OF_MONTH);
                if (!diasContemplados.contains(day)) {
                    requestedHolidays++;
                    diasContemplados.add(day);
                }
                cActual.add(Calendar.DAY_OF_MONTH, 1);
            }

        }


        return (daysInMonth - weekendsInMonth - holidays - requestedHolidays) * hoursPerDay;
    }

    public void validateHours(FacesContext context, UIComponent toValidate, Object value) {

        HtmlInputText startTimeHour = (HtmlInputText) FacesUtils.getComponent("activity")
                .findComponent("startTimeHour");
        HtmlInputText startTimeMinute = (HtmlInputText) FacesUtils.getComponent("activity")
                .findComponent("startTimeMinute");

        HtmlInputText endTimeHour = (HtmlInputText) FacesUtils.getComponent("activity")
                .findComponent("endTimeHour");
        HtmlInputText endTimeMinute = (HtmlInputText) FacesUtils.getComponent("activity")
                .findComponent("endTimeMinute");

        HtmlInputText duration = (HtmlInputText) FacesUtils.getComponent("activity").findComponent(
                "duration");

        if (startTimeHour.getSubmittedValue() != null
                && startTimeMinute.getSubmittedValue() != null
                && endTimeHour.getSubmittedValue() != null
                && endTimeMinute.getSubmittedValue() != null) {

            Date startTime = DateUtils.timeToDate(Integer.valueOf(
                    startTimeHour.getSubmittedValue().toString()).intValue(), Integer.valueOf(
                    startTimeMinute.getSubmittedValue().toString()).intValue());
            Date endTime = DateUtils.timeToDate(Integer.valueOf(
                    endTimeHour.getSubmittedValue().toString()).intValue(), Integer.valueOf(
                    endTimeMinute.getSubmittedValue().toString()).intValue());

            if (toValidate.equals(startTimeHour) || toValidate.equals(startTimeMinute)) {

                if (startTime.after(endTime)) {

                    ((UIInput) toValidate).setValid(false);
                    FacesMessage message = new FacesMessage(
                            "La hora de inicio no puede ser posterior a la hora de fin");
                    context.addMessage(toValidate.getClientId(context), message);
                }
            }

            if (toValidate.equals(endTimeHour) || toValidate.equals(endTimeMinute)) {

                if (startTime.before(endTime)) {

                    ((UIInput) toValidate).setValid(false);
                    FacesMessage message = new FacesMessage(
                            "La hora de fin no puede ser anterior a la hora inicial");
                    context.addMessage(toValidate.getClientId(context), message);
                }
            }

            if (toValidate.equals(duration)) {

                if (Integer.valueOf(duration.getSubmittedValue().toString()).intValue() < 0) {

                    ((UIInput) toValidate).setValid(false);
                    FacesMessage message = new FacesMessage("La duracion no puede ser negativa");
                    context.addMessage(toValidate.getClientId(context), message);
                }
            }
        }
    }

    private void fillHolidays(SimpleScheduleModel model) {

        Calendar calMin = Calendar.getInstance();
        Calendar calMax = Calendar.getInstance();

        calMin.setTime(selectedDate);
        calMin.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DAY_OF_MONTH));
        calMin.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
        calMin.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
        calMin.set(Calendar.SECOND, cal.getMinimum(Calendar.SECOND));
        calMin.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));

        calMax.setTime(selectedDate);
        calMax.set(Calendar.DAY_OF_MONTH, cal.getMaximum(Calendar.DAY_OF_MONTH));
        calMax.set(Calendar.HOUR_OF_DAY, cal.getMaximum(Calendar.HOUR_OF_DAY));
        calMax.set(Calendar.MINUTE, cal.getMaximum(Calendar.MINUTE));
        calMax.set(Calendar.SECOND, cal.getMaximum(Calendar.SECOND));
        calMax.set(Calendar.MILLISECOND, cal.getMaximum(Calendar.MILLISECOND));

        if (((SettingBean) FacesUtils.getBean("settingBean")).getMySettings().getLoadExtraDays()) {
            calMin.add(Calendar.MONTH, -1);
            calMax.add(Calendar.MONTH, 1);
        }

        RequestHolidayManager rhManager = RequestHolidayManager.getDefault();
        RequestHolidaySearch rhSearch = new RequestHolidaySearch();
        rhSearch.setUserRequest(authManager.getCurrentPrincipal().getUser());
        rhSearch.setState(HolidayState.ACCEPT);
        rhSearch.setStartBeginDate(calMin.getTime());
        rhSearch.setEndBeginDate(calMax.getTime());
        rhSearch.setStartFinalDate(calMin.getTime());
        rhSearch.setEndFinalDate(calMax.getTime());

        List<RequestHoliday> listH = rhManager.getAllEntities(rhSearch, null);

        for (RequestHoliday rH : listH) {
            Calendar cActual = Calendar.getInstance();
            cActual.setTime(rH.getBeginDate());
            while (!cActual.getTime().after(rH.getFinalDate())) {
                if (cActual.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
                        && cActual.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                    model.setHoliday(cActual.getTime(), FacesUtils
                            .getMessage("activity.acceptedHolidays"));
                }
                cActual.add(Calendar.DAY_OF_MONTH, 1);
            }

        }

        HolidaySearch monthSearch = new HolidaySearch();

        monthSearch.setStartDate(calMin.getTime());
        monthSearch.setEndDate(calMax.getTime());
        List<Holiday> listaHolidays = holidayManager.getAllEntities(monthSearch, null);

        for (Holiday holiday : listaHolidays) {
            model.setHoliday(holiday.getDate(), holiday.getDescription());
        }

    }

    public boolean isDefaultBillable() {
        return defaultBillable;
    }

    public void setDefaultBillable(boolean defaultBillable) {
        this.defaultBillable = defaultBillable;
    }


}
