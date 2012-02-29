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
import com.autentia.intra.businessobject.*;
import com.autentia.intra.dao.SortCriteria;
import com.autentia.intra.dao.hibernate.EnsayoDAO;
import com.autentia.intra.dao.hibernate.ProjectDAO;
import com.autentia.intra.dao.hibernate.UserDAO;
import com.autentia.intra.dao.search.ProjectSearch;
import com.autentia.intra.manager.admin.ProjectCostManager;
import com.autentia.intra.manager.admin.ProjectEnsayoManager;
import com.autentia.intra.manager.admin.ProjectManager;
import com.autentia.intra.manager.admin.ProjectRoleManager;
import com.autentia.intra.manager.contacts.OrganizationManager;
import com.autentia.intra.manager.security.Permission;
import com.autentia.intra.upload.Uploader;
import com.autentia.intra.upload.UploaderFactory;
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
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;

/**
 * UI bean for Project objects.
 *
 * @author stajanov code generator
 */
public class ProjectBean extends BaseBean {
    private ProjectDAO projectDAO = ProjectDAO.getDefault();

    public void referenciaLaboratorioValidate(FacesContext context,
                                              UIComponent toValidate,
                                              Object value) throws ValidatorException {
        if (value == null)
            return;

        String referenciaLaboratorio = (String) value;

        List<Project> projects = projectDAO.search(null);

        if (projects == null || projects.isEmpty())
            return;

        for (Project p : projects) {
            if (p.getReferenciaLaboratorio() != null && p.getReferenciaLaboratorio().equals(referenciaLaboratorio) && !p.getId().equals(project.getId())) {
                //((UIInput) toValidate).setValid(false);
                //context.addMessage(toValidate.getClientId(context), new FacesMessage("Ya existe otra oferta con este número"));
                throw new ValidatorException(new FacesMessage("Ya existe otro informe con este número"));
            }
        }
    }


    private List<SelectItem> users;

    public List<SelectItem> getUsers() {
        if (users != null)
            return users;

        List<User> refs = UserDAO.getDefault().search(null, new SortCriteria("name"));

        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        for (User ref : refs) {
            ret.add(new SelectItem(ref, ref.getName()));
        }
        users = ret;
        return ret;
    }

    /**
     * Create a new empty instance of the one-to-many field
     *
     * @return forward to the same page
     */
    public String createIdentificacionCliente() {
        ProjectIdentificacion item = new ProjectIdentificacion();
        item.setProject(project);
        if (project.getReferenciasCliente() == null) {
            project.setReferenciasCliente(new ArrayList<ProjectIdentificacion>());
        }
        project.getReferenciasCliente().add(item);
        return null;
    }

    /**
     * Create a new empty instance of the one-to-many field
     *
     * @return forward to the same page
     */
    public String editIdentificacionCliente() {
        ProjectIdentificacion item = new ProjectIdentificacion();
        if (project.getReferenciasCliente() == null) {
            project.setReferenciasCliente(new ArrayList<ProjectIdentificacion>());
        }
        project.getReferenciasCliente().add(item);
        return null;
    }

    /**
     * Delete selected instance of the one-to-many field
     *
     * @return forward to the same page
     */
    public String deleteIdentificacionCliente() {
        UIData table = (UIData) FacesUtils.getComponent("project:identificacionesCliente");
        project.getReferenciasCliente().remove(table.getRowData());
        return null;
    }


    public String getSiguienteRefLab() {
        if (project.getClient() != null && (project.getReferenciaLaboratorio() == null
                || project.getReferenciaLaboratorio().equals(""))) {
            NumberFormat nf = NumberFormat.getInstance();
            nf.setMinimumIntegerDigits(2);
            nf.setGroupingUsed(false);
            String anio = nf.format(new Date().getYear() - 100);

            ProjectSearch ps = new ProjectSearch();
            String acronimo = project.getClient().getAcronimo();
            ps.setReferenciaLaboratorio(acronimo + "%/" + anio + "%");
            List<Project> todos = manager.getAllEntities(ps, new SortCriteria("id", false));
            nf.setMinimumIntegerDigits(4);
            int t = 0;
            if (todos.size() >= 0) {
                try {
                    String x = todos.get(0).getReferenciaLaboratorio();
                    x = x.replaceFirst(acronimo, "");
                    t = Integer.parseInt(x.substring(0, x.indexOf('/')));
                } catch (Exception ex) {
                }
            }
            return acronimo + nf.format(t + 1) + "/" + anio;
        } else {
            return project.getReferenciaLaboratorio();
        }
    }

    public void setSiguienteRefLab(String referenciaLaboratorio) {
        project.setReferenciaLaboratorio(referenciaLaboratorio);
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    /**
     * Serial version field
     */
    private static final long serialVersionUID = -1L;

    public BigDecimal getTotalCost() {
        return project.getTotalCost();
    }

    /**
     * Go to edit page
     *
     * @return forward to EDIT page
     */

    public String copy() {
        Integer id = Integer.parseInt(FacesUtils.getRequestParameter("id"));
        Offer oferta = manager.copyFromOffer(id);

        project = new Project();

        project.setClient(oferta.getOrganization());
        project.setStartDate(new Date());
        project.setOpen(true);
        project.setBillable(true);
        String titulo = oferta.getTitle();
        String numero = oferta.getNumber();
        if (!titulo.equals(numero))
            project.setName(numero.concat(" ").concat(titulo));
        else
            project.setName(numero);

        project.setDescription(oferta.getDescription());
        project.setOrderNumber(oferta.getOrderNumber());


        HashSet<ProjectRole> roles = new HashSet<ProjectRole>();
        HashSet<ProjectCost> costs = new HashSet<ProjectCost>();
        List<ProjectEnsayo> ensayos = new ArrayList<ProjectEnsayo>();

        for (OfferRole role : oferta.getRoles()) {
            ProjectRole pr = new ProjectRole();
            pr.setName(role.getName());
            pr.setCostPerHour(role.getCostPerHour());
            pr.setExpectedHours(role.getExpectedHours());
            roles.add(pr);
        }

        for (OfferCost cost : oferta.getCosts()) {
            ProjectCost pc = new ProjectCost();
            pc.setName(cost.getName());
            pc.setBillable(cost.isBillable());
            pc.setCost(cost.getCost());
            costs.add(pc);
        }


        for (OfferEnsayo ensayo : oferta.getEnsayos()) {
            ProjectEnsayo pe = new ProjectEnsayo();
            pe.setEnsayo(ensayo.getEnsayo());
            pe.setCost(ensayo.getCost());
            ensayos.add(pe);
        }


        project.setRoles(roles);
        project.setCosts(costs);
        project.setEnsayos(ensayos);


        return NavigationResults.COPY_FROM_OFFER;
    }

/* project - generated by stajanov (do not edit/delete) */


    /**
     * Logger
     */
    private static final Log log = LogFactory.getLog(ProjectBean.class);

    /**
     * Active search object
     */
    private ProjectSearch search = new ProjectSearch();

    /**
     * Manager
     */
    private static ProjectManager manager = ProjectManager.getDefault();

    /**
     * Upload service
     */
    private static final Uploader uploader = UploaderFactory.getInstance("project");


    /**
     * Active Project object
     */
    private Project project;

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
     * List projects. Order depends on Faces parameter sort.
     *
     * @return the list of all projects sorted by requested criterion
     */
    public List<Project> getAll() {
        return manager.getAllEntities(search, new SortCriteria(sortColumn, sortAscending));
    }

    // Getters to list possible values of related entities


    /**
     * Get the list of all clients
     *
     * @return the list of all clients
     */
    public List<SelectItem> getClients() {
        List<Organization> refs = OrganizationManager.getDefault().getAllEntities(null, new SortCriteria("name"));

        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        for (Organization ref : refs) {
            ret.add(new SelectItem(ref, ref.getName() + (ref.getAcronimo() == null || ref.getAcronimo().equals("") ? "" : " (" + ref.getAcronimo() + ")")));
        }
        return ret;
    }


    /**
     * Get the list of all roless
     *
     * @return the list of all roless
     */
    public List<SelectItem> getRoless() {
        List<ProjectRole> refs = ProjectRoleManager.getDefault().getAllEntities(null, new SortCriteria("name"));

        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        for (ProjectRole ref : refs) {
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
        List<ProjectEnsayo> refs = ProjectEnsayoManager.getDefault().getAllEntities(null, new SortCriteria("id"));

        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        for (ProjectEnsayo ref : refs) {
            ret.add(new SelectItem(ref, ref.getEnsayo().getName()));
        }
        return ret;
    }


    /**
     * Get the list of all costss
     *
     * @return the list of all costss
     */
    public List<SelectItem> getCostss() {
        List<ProjectCost> refs = ProjectCostManager.getDefault().getAllEntities(null, new SortCriteria("name"));

        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        for (ProjectCost ref : refs) {
            ret.add(new SelectItem(ref, ref.getName()));
        }
        return ret;
    }

    // Getters to list possible values of enum fields

    // Methods to create/remove instances of one-to-many entities (slave entities)

    /**
     * Create a new empty instance of the one-to-many field
     *
     * @return forward to the same page
     */
    public String createRoles() {
        ProjectRole item = new ProjectRole();
        item.setProject(project);
        if (project.getRoles() == null) {
            project.setRoles(new HashSet<ProjectRole>());
        }
        project.getRoles().add(item);
        return null;
    }

    /**
     * Create a new empty instance of the one-to-many field
     *
     * @return forward to the same page
     */
    public String editRoles() {
        ProjectRole item = new ProjectRole();
        item.setProject(project);
        if (project.getRoles() == null) {
            project.setRoles(new HashSet<ProjectRole>());
        }
        project.getRoles().add(item);
        return null;
    }

    /**
     * Delete selected instance of the one-to-many field
     *
     * @return forward to the same page
     */
    public String deleteRoles() {
        UIData table = (UIData) FacesUtils.getComponent("project:roles");
        project.getRoles().remove(table.getRowData());
        return null;
    }

    /**
     * Create a new empty instance of the one-to-many field
     *
     * @return forward to the same page
     */
    public String createCosts() {
        ProjectCost item = new ProjectCost();
        item.setProject(project);
        if (project.getCosts() == null) {
            project.setCosts(new HashSet<ProjectCost>());
        }
        project.getCosts().add(item);
        return null;
    }


    /**
     * Create a new empty instance of the one-to-many field
     *
     * @return forward to the same page
     */
    public String createEnsayos() {
        ProjectEnsayo item = new ProjectEnsayo();
        try {
            project.getEnsayos();
        } catch (Exception ex) {
            project = (Project) projectDAO.merge(project);
        }
        item.setProject(project);
        if (project.getEnsayos() == null) {
            project.setEnsayos(new ArrayList<ProjectEnsayo>());
        }
        project.getEnsayos().add(item);
        return null;
    }

    /**
     * Create a new empty instance of the one-to-many field
     *
     * @return forward to the same page
     */
    public String editCosts() {
        ProjectCost item = new ProjectCost();
        item.setProject(project);
        if (project.getCosts() == null) {
            project.setCosts(new HashSet<ProjectCost>());
        }
        project.getCosts().add(item);
        return null;
    }

    /**
     * Create a new empty instance of the one-to-many field
     *
     * @return forward to the same page
     */
    public String editEnsayos() {
        ProjectEnsayo item = new ProjectEnsayo();
        item.setProject(project);
        if (project.getEnsayos() == null) {
            project.setEnsayos(new ArrayList<ProjectEnsayo>());
        }
        project.getEnsayos().add(item);
        return null;
    }


    /**
     * Delete selected instance of the one-to-many field
     *
     * @return forward to the same page
     */
    public String deleteCosts() {
        UIData table = (UIData) FacesUtils.getComponent("project:costs");
        project.getCosts().remove(table.getRowData());
        return null;
    }

    /**
     * Delete selected instance of the one-to-many field
     *
     * @return forward to the same page
     */
    public String deleteEnsayos() {
        UIData table = (UIData) FacesUtils.getComponent("project:ensayos");
        project.getEnsayos().remove(table.getRowData());
        return null;
    }

    /**
     * Whether or not create button is available for user
     *
     * @return true if user can create objects of type Project
     */
    public boolean isCreateAvailable() {
        return SpringUtils.isRolePermissionGranted(Permission.Entity_Create(Project.class));
    }

    /**
     * Whether or not edit button is available for user
     *
     * @return true if user can edit current object
     */
    public boolean isEditAvailable() {
        return SpringUtils.isAclPermissionGranted(project, BasePermission.WRITE);
    }

    /**
     * Whether or not delete button is available for user
     *
     * @return true if user can delete current object
     */
    public boolean isDeleteAvailable() {
        return (project.getId() != null) &&
                SpringUtils.isAclPermissionGranted(project, BasePermission.DELETE);
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
        project = new Project();
        project.setStartDate(new Date());
        project.setBillable(true);
        users = null;
        ensayosPosibles = null;
        return NavigationResults.CREATE;
    }

    /**
     * Go to detail page
     *
     * @return forward to DETAIL page
     */
    public String detail() {
        Integer id = Integer.parseInt(FacesUtils.getRequestParameter("id"));
        project = manager.getEntityById(id);
        users = null;
        ensayosPosibles = null;

        return SpringUtils.isAclPermissionGranted(project, BasePermission.WRITE)
                ? NavigationResults.EDIT
                : NavigationResults.DETAIL;
    }

    public String detail_desde_fuera() {
        return "project_" + detail();
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
     * Save bean and stay on it
     *
     * @return forward to list page
     */
    public String save() {
        doBeforeSave();
        project = (Project) projectDAO.merge(project); //arregla LazyFetching
        if (project.getEnsayos() != null) {
            for (ProjectEnsayo pe : project.getEnsayos()) {
                if (pe.getRealizadoFecha() == null) {
                    pe.setRealizadoFecha(project.getRealizadoFecha());
                }
                if (project.getReferenciaCliente() != null && !"".equals(project.getReferenciaCliente())) {
                    pe.setIdentificacionCliente(project.getReferenciaCliente());
                }
            }
        }
        if (project.getId() == null) {
            manager.insertEntity(project);
        } else {
            try {
                manager.updateEntity(project);
            } catch (Exception ex) {
                project = (Project) projectDAO.merge(project);
                manager.updateEntity(project);
            }
        }

        // Calls an after save action
        String result = doAfterSave(NavigationResults.LIST);

        // Unselect object
        //project = null;

        return null;
    }

    /**
     * Delete bean and go back to beans list
     *
     * @return forward to LIST page
     */
    public String delete() {
        manager.deleteEntity(project);
        project = null;
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
    public boolean isProjectSelected() {
        return project != null;
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
    public ProjectSearch getSearch() {
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


    public Date getSearchStartEndDate() {
        return search.getStartEndDate();
    }

    public void setSearchStartEndDate(Date val) {
        if (val != null) {
            search.setStartEndDate(val);
        } else {
            search.unsetStartEndDate();
        }
    }

    public boolean isSearchStartEndDateValid() {
        return search.isStartEndDateSet();
    }

    public void setSearchStartEndDateValid(boolean val) {
        if (val) {
            search.setStartEndDate(search.getStartEndDate());
        } else {
            search.unsetStartEndDate();
        }
    }

    public Date getSearchEndEndDate() {
        return search.getEndEndDate();
    }

    public void setSearchEndEndDate(Date val) {
        if (val != null) {
            search.setEndEndDate(val);
        } else {
            search.unsetEndEndDate();
        }
    }

    public boolean isSearchEndEndDateValid() {
        return search.isEndEndDateSet();
    }

    public void setSearchEndEndDateValid(boolean val) {
        if (val) {
            search.setEndEndDate(search.getEndEndDate());
        } else {
            search.unsetEndEndDate();
        }
    }


    public Boolean getSearchOpen() {
        return search.getOpen();
    }

    public void setSearchOpen(Boolean val) {
        if (search.isOpenSet()) {
            search.setOpen(val);
        }
    }

    public boolean isSearchOpenValid() {
        return search.isOpenSet();
    }

    public void setSearchOpenValid(boolean val) {
        if (val) {
            search.setOpen(search.getOpen());
        } else {
            search.unsetOpen();
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


    public String getSearchReferenciaCliente() {
        return search.getReferenciaCliente();
    }

    public void setSearchReferenciaCliente(String val) {
        if (search.isReferenciaClienteSet()) {
            search.setReferenciaCliente(val);
        }
    }

    public boolean isSearchReferenciaClienteValid() {
        return search.isReferenciaClienteSet();
    }

    public void setSearchReferenciaClienteValid(boolean val) {
        if (val) {
            search.setReferenciaCliente(search.getReferenciaCliente());
        } else {
            search.unsetReferenciaCliente();
        }
    }


    public String getSearchReferenciaLaboratorio() {
        return search.getReferenciaLaboratorio();
    }

    public void setSearchReferenciaLaboratorio(String val) {
        if (search.isReferenciaLaboratorioSet()) {
            search.setReferenciaLaboratorio(val);
        }
    }

    public boolean isSearchReferenciaLaboratorioValid() {
        return search.isReferenciaLaboratorioSet();
    }

    public void setSearchReferenciaLaboratorioValid(boolean val) {
        if (val) {
            search.setReferenciaLaboratorio(search.getReferenciaLaboratorio());
        } else {
            search.unsetReferenciaLaboratorio();
        }
    }


    public String getSearchEspecificacion() {
        return search.getEspecificacion();
    }

    public void setSearchEspecificacion(String val) {
        if (search.isEspecificacionSet()) {
            search.setEspecificacion(val);
        }
    }

    public boolean isSearchEspecificacionValid() {
        return search.isEspecificacionSet();
    }

    public void setSearchEspecificacionValid(boolean val) {
        if (val) {
            search.setEspecificacion(search.getEspecificacion());
        } else {
            search.unsetEspecificacion();
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


    public List<ProjectRole> getSearchRoles() {
        return search.getRoles();
    }

    public void setSearchRoles(List<ProjectRole> val) {
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


    public List<ProjectEnsayo> getSearchEnsayos() {
        return search.getEnsayos();
    }

    public void setSearchEnsayos(List<ProjectEnsayo> val) {
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


    public List<ProjectCost> getSearchCosts() {
        return search.getCosts();
    }

    public void setSearchCosts(List<ProjectCost> val) {
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
            UIComponent comp = FacesUtils.getComponent("projects:list");
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

    // Getters and setters to manipulate active Project object

    public java.lang.Integer getId() {
        return project.getId();
    }


    public Date getStartDate() {
        return project.getStartDate();
    }

    public void setStartDate(Date startDate) {
        project.setStartDate(startDate);
    }


    public Date getEndDate() {
        return project.getEndDate();
    }

    public void setEndDate(Date endDate) {
        project.setEndDate(endDate);
    }


    public Boolean getOpen() {
        return project.getOpen();
    }

    public void setOpen(Boolean open) {
        project.setOpen(open);
    }


    public Boolean getClosed() {
        if (project.getEndDate() != null) {
            project.setOpen(false);
            return true;
        } else {
            return !project.getOpen();
        }
    }

    public void setClosed(Boolean closed) {
        project.setOpen(!closed);
    }

    public String getName() {
        return project.getName();
    }

    public void setName(String name) {
        project.setName(name);
    }

    public String getOrderNumber() {
        return project.getOrderNumber();
    }

    public void setOrderNumber(String orderNumber) {
        project.setOrderNumber(orderNumber);
    }


    public String getDescription() {
        return project.getDescription();
    }

    public void setDescription(String description) {
        project.setDescription(description);
    }


    public String getEnsayosSolicitados() {
        return project.getEnsayosSolicitados();
    }

    public void setEnsayosSolicitados(String ensayosSolicitados) {
        project.setEnsayosSolicitados(ensayosSolicitados);
    }


    public Boolean getBillable() {
        return project.getBillable();
    }

    public void setBillable(Boolean billable) {
        project.setBillable(billable);
    }


    public Integer getOwnerId() {
        return project.getOwnerId();
    }

    public void setOwnerId(Integer ownerId) {
        project.setOwnerId(ownerId);
    }


    public Integer getDepartmentId() {
        return project.getDepartmentId();
    }

    public void setDepartmentId(Integer departmentId) {
        project.setDepartmentId(departmentId);
    }


    public Date getInsertDate() {
        return project.getInsertDate();
    }

    public void setInsertDate(Date insertDate) {
        project.setInsertDate(insertDate);
    }


    public Date getUpdateDate() {
        return project.getUpdateDate();
    }

    public void setUpdateDate(Date updateDate) {
        project.setUpdateDate(updateDate);
    }


    public Organization getClient() {
        return project.getClient();
    }

    public void setClient(Organization client) {
        project.setClient(client);
    }


    public Set<ProjectRole> getRoles() {
        return project.getRoles();
    }

    public void setRoles(Set<ProjectRole> roles) {
        project.setRoles(roles);
    }


    public List<ProjectEnsayo> getEnsayos() {
        return project.getEnsayos();
    }

    public void setEnsayos(List<ProjectEnsayo> ensayos) {
        project.setEnsayos(ensayos);
    }


    public Set<ProjectCost> getCosts() {
        return project.getCosts();
    }

    public void setCosts(Set<ProjectCost> costs) {
        project.setCosts(costs);
    }

/* project - generated by stajanov (do not edit/delete) */

    // Para arreglar un problema que surge en copy from Offer

    public String doBeforeSave() {
        if (project != null) {
            if (project.getRoles() != null) {
                for (ProjectRole pr : project.getRoles()) {
                    if (pr.getProject() == null)
                        pr.setProject(project);

                }
            }

            if (project.getCosts() != null) {
                for (ProjectCost pc : project.getCosts()) {
                    if (pc.getProject() == null)
                        pc.setProject(project);

                }
            }

            if (project.getEnsayos() != null) {
                for (ProjectEnsayo pe : project.getEnsayos()) {
                    if (pe.getProject() == null)
                        pe.setProject(project);

                }
            }
            if ("".equals(project.getReferenciaLaboratorio()))
                project.setReferenciaLaboratorio(getSiguienteRefLab());
        }

        return null;
    }

    public String getEstadoFormatted() {
        if (this.getEstado() != null) {
            return FacesUtils.formatMessage("ProjectEstado." + this.getEstado().name());
        } else {
            return "";
        }
    }

    public List<SelectItem> getEstados() {
        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        ProjectEstado[] vals = ProjectEstado.values();
        for (ProjectEstado val : vals) {
            ret.add(new SelectItem(val, FacesUtils.formatMessage("ProjectEstado." + val.name())));
        }
        return ret;
    }

    public ProjectEstado getSearchEstado() {
        return search.getEstado();
    }

    public void setSearchEstado(ProjectEstado val) {
        if (search.isEstadoSet()) {
            search.setEstado(val);
        }
    }

    public boolean isSearchEstadoValid() {
        return search.isEstadoSet();
    }

    public void setSearchEstadoValid(boolean val) {
        if (val) {
            search.setEstado(search.getEstado());
        } else {
            search.unsetEstado();
        }
    }

    public ProjectEstado getEstado() {
        return project.getEstado();
    }

    public void setEstado(ProjectEstado estado) {
        project.setEstado(estado);
    }
}
