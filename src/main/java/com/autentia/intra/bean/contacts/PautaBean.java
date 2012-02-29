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
import com.autentia.intra.dao.search.PautaSearch;
import com.autentia.intra.manager.contacts.EnsayoManager;
import com.autentia.intra.manager.contacts.OrganizationManager;
import com.autentia.intra.manager.contacts.PautaManager;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * UI bean for Pauta objects.
 *
 * @author stajanov code generator
 */
public class PautaBean extends BaseBean {
    public String duplicar() {
        Integer id = Integer.parseInt(FacesUtils.getRequestParameter("id"));
        Pauta original = manager.getEntityById(id);
        pauta = new Pauta();
        pauta.setCost(original.getCost());
        pauta.setDescription(original.getDescription());
        pauta.setEnsayos(new ArrayList<PautaEnsayo>());
        List<PautaEnsayo> pes = original.getEnsayos();
        for (PautaEnsayo pe : pes) {
            PautaEnsayo mipe = new PautaEnsayo();
            mipe.setEnsayo(pe.getEnsayo());
            mipe.setPauta(pauta);
            mipe.setProcedimiento(pe.getProcedimiento());
            mipe.setRequerimientos(new ArrayList<ParametroString>());
            List<ParametroString> pss = pe.getRequerimientos();
            for (ParametroString ps : pss) {
                ParametroString mips = new ParametroString();
                mips.setNombre(ps.getNombre());
                mips.setNombreIngles(ps.getNombreIngles());
                mips.setPautaEnsayo(mipe);
                mips.setRequerimiento(ps.getRequerimiento());
                mipe.getRequerimientos().add(mips);
            }
            pauta.getEnsayos().add(mipe);
        }
        pauta.setEnsayosSolicitados(original.getEnsayosSolicitados());
        pauta.setName(original.getName() + " (copia)");
        pauta.setTitulo(original.getTitulo());
        pauta.setFamilia(original.getFamilia());
        pauta.setReferenciasCliente(new ArrayList<PautaIdentificacion>());
        for (PautaIdentificacion pi : original.getReferenciasCliente()) {
            PautaIdentificacion pimia = new PautaIdentificacion();
            pimia.setName(pi.getName());
            pimia.setPauta(pauta);
            pimia.setValor(pi.getValor());
            pauta.getReferenciasCliente().add(pimia);
        }
        pauta.setTiempoRespuesta(original.getTiempoRespuesta());
        manager.insertEntity(pauta);
        return NavigationResults.EDIT;
    }

    private PautaEnsayo pautaEnsayo;


    /**
     * Create a new empty instance of the one-to-many field
     *
     * @return forward to the same page
     */
    public String createIdentificacionCliente() {
        PautaIdentificacion item = new PautaIdentificacion();
        item.setPauta(pauta);
        if (pauta.getReferenciasCliente() == null) {
            pauta.setReferenciasCliente(new ArrayList<PautaIdentificacion>());
        }
        pauta.getReferenciasCliente().add(item);
        return null;
    }

    /**
     * Delete selected instance of the one-to-many field
     *
     * @return forward to the same page
     */
    public String deleteIdentificacionCliente() {
        UIData table = (UIData) FacesUtils.getComponent("pauta:identificacionesCliente");
        pauta.getReferenciasCliente().remove(table.getRowData());
        return null;
    }

    public List<SelectItem> getPautas() {
        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        List<Pauta> refs = getAllAlfabetico();
        for (Pauta ref : refs) {
            ret.add(new SelectItem(ref, ref.getName()));
        }

        //	Añadiendo el campo vacio
        ret.add(0, new SelectItem(""));

        return ret;
    }

    /**
     * Serial version field
     */
    private static final long serialVersionUID = -1L;

/* pauta - generated by stajanov (do not edit/delete) */

    /**
     * Logger
     */
    private static final Log log = LogFactory.getLog(PautaBean.class);

    /**
     * Active search object
     */
    private PautaSearch search = new PautaSearch();

    /**
     * Manager
     */
    private static PautaManager manager = PautaManager.getDefault();

    /**
     * Upload service
     */
    private static final Uploader uploader = UploaderFactory.getInstance("pauta");


    /**
     * Active Pauta object
     */
    private Pauta pauta;

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
     * List pautas. Order depends on Faces parameter sort.
     *
     * @return the list of all pautas sorted by requested criterion
     */
    public List<Pauta> getAll() {
        return manager.getAllEntities(search, new SortCriteria(sortColumn, sortAscending));
    }

    public List<Pauta> getAllAlfabetico() {
        return manager.getAllEntities(null, new SortCriteria("name", true));
    }

    // Getters to list possible values of related entities


    /**
     * Get the list of all ensayoss
     *
     * @return the list of all ensayoss
     */
    public List<SelectItem> getEnsayoss() {
        List<Ensayo> refs = EnsayoManager.getDefault().getAllEntities(null, new SortCriteria("name"));

        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        for (Ensayo ref : refs) {
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
    public String createEnsayos() {
        PautaEnsayo item = new PautaEnsayo();
        item.setPauta(pauta);
        if (pauta.getEnsayos() == null) {
            pauta.setEnsayos(new ArrayList<PautaEnsayo>());
        }
        pauta.getEnsayos().add(item);
        return null;
    }

    /**
     * Create a new empty instance of the one-to-many field
     *
     * @return forward to the same page
     */
    public String editEnsayos() {
        PautaEnsayo item = new PautaEnsayo();
        if (pauta.getEnsayos() == null) {
            pauta.setEnsayos(new ArrayList<PautaEnsayo>());
        }
        pauta.getEnsayos().add(item);
        return null;
    }

    /**
     * Delete selected instance of the one-to-many field
     *
     * @return forward to the same page
     */
    public String deleteEnsayos() {
        UIData table = (UIData) FacesUtils.getComponent("pauta:ensayos");
        pauta.getEnsayos().remove(table.getRowData());
        return null;
    }

    /**
     * Whether or not create button is available for user
     *
     * @return true if user can create objects of type Pauta
     */
    public boolean isCreateAvailable() {
        return SpringUtils.isRolePermissionGranted(Permission.Entity_Create(Pauta.class));
    }

    /**
     * Whether or not edit button is available for user
     *
     * @return true if user can edit current object
     */
    public boolean isEditAvailable() {
        return SpringUtils.isAclPermissionGranted(pauta, BasePermission.WRITE);
    }

    /**
     * Whether or not delete button is available for user
     *
     * @return true if user can delete current object
     */
    public boolean isDeleteAvailable() {
        return (pauta.getId() != null) &&
                SpringUtils.isAclPermissionGranted(pauta, BasePermission.DELETE);
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
        pauta = new Pauta();
        return NavigationResults.CREATE;
    }

    /**
     * Go to detail page
     *
     * @return forward to DETAIL page
     */
    public String detail() {
        Integer id = Integer.parseInt(FacesUtils.getRequestParameter("id"));
        pauta = manager.getEntityById(id);

        return SpringUtils.isAclPermissionGranted(pauta, BasePermission.WRITE)
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

        if (pauta.getId() == null) {
            manager.insertEntity(pauta);
        } else {
            manager.updateEntity(pauta);
        }

        // Calls an after save action
        String result = doAfterSave(NavigationResults.LIST);

        // Unselect object
        //pauta = null;

        return null;
    }

    /**
     * Delete bean and go back to beans list
     *
     * @return forward to LIST page
     */
    public String delete() {
        manager.deleteEntity(pauta);
        pauta = null;
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
    public boolean isPautaSelected() {
        return pauta != null;
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
    public PautaSearch getSearch() {
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

    public List<PautaEnsayo> getSearchEnsayos() {
        return search.getEnsayos();
    }

    public void setSearchEnsayos(List<PautaEnsayo> val) {
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

    /**
     * Get the list of all clients
     *
     * @return the list of all clients
     */
    public List<SelectItem> getClientes() {
        List<Organization> refs = OrganizationManager.getDefault().getAllEntities(null, new SortCriteria("name"));

        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        for (Organization ref : refs) {
            ret.add(new SelectItem(ref, ref.getName() + (ref.getAcronimo() == null || ref.getAcronimo().equals("") ? "" : " (" + ref.getAcronimo() + ")")));
        }
        ret.add(0, new SelectItem(""));
        return ret;
    }

    public Organization getSearchCliente() {
        return search.getCliente();
    }

    public void setSearchCliente(Organization val) {
        if (search.isClienteSet()) {
            search.setCliente(val);
        }
    }

    public boolean isSearchClienteValid() {
        return search.isClienteSet();
    }

    public void setSearchClienteValid(boolean val) {
        if (val) {
            search.setCliente(search.getCliente());
        } else {
            search.unsetCliente();
        }
    }

    public String getSearchMaterial() {
        return search.getMaterial();
    }

    public void setSearchMaterial(String val) {
        if (search.isMaterialSet()) {
            search.setMaterial(val);
        }
    }

    public boolean isSearchMaterialValid() {
        return search.isMaterialSet();
    }

    public void setSearchMaterialValid(boolean val) {
        if (val) {
            search.setMaterial(search.getMaterial());
        } else {
            search.unsetMaterial();
        }
    }

    public String getSearchAgrupacion() {
        return search.getAgrupacion();
    }

    public void setSearchAgrupacion(String val) {
        if (search.isAgrupacionSet()) {
            search.setAgrupacion(val);
        }
    }

    public boolean isSearchAgrupacionValid() {
        return search.isAgrupacionSet();
    }

    public void setSearchAgrupacionValid(boolean val) {
        if (val) {
            search.setAgrupacion(search.getAgrupacion());
        } else {
            search.unsetAgrupacion();
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
        if (letter != null) {
            UIComponent comp = FacesUtils.getComponent("pautas:list");
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

    // Getters and setters to manipulate active Pauta object

    public BigDecimal getCost() {
        return pauta.getCost();
    }

    public void setCost(BigDecimal cost) {
        pauta.setCost(cost);
    }

    public Date getInsertDate() {
        return pauta.getInsertDate();
    }

    public void setInsertDate(Date insertDate) {
        pauta.setInsertDate(insertDate);
    }

    public void setEnsayos(List<PautaEnsayo> ensayos) {
        pauta.setEnsayos(ensayos);
    }

    public List<PautaEnsayo> getEnsayos() {
        return pauta.getEnsayos();
    }

    public void setEnsayos2(List<PautaEnsayo> ensayos) {
        try {
            setEnsayos(new ArrayList<PautaEnsayo>(ensayos));
        } catch (NullPointerException e) {
            setEnsayos(new ArrayList<PautaEnsayo>());
        }
    }

    public List<PautaEnsayo> getEnsayos2() {
        try {
            return new ArrayList<PautaEnsayo>(getEnsayos());
        } catch (NullPointerException e) {
            return new ArrayList<PautaEnsayo>();
        }
    }

    public Integer getDepartmentId() {
        return pauta.getDepartmentId();
    }

    public void setDepartmentId(Integer departmentId) {
        pauta.setDepartmentId(departmentId);
    }

    public Date getUpdateDate() {
        return pauta.getUpdateDate();
    }

    public void setUpdateDate(Date updateDate) {
        pauta.setUpdateDate(updateDate);
    }

    public String getName() {
        return pauta.getName();
    }

    public void setName(String name) {
        pauta.setName(name);
    }

    public String getDescription() {
        return pauta.getDescription();
    }

    public void setDescription(String description) {
        pauta.setDescription(description);
    }

    public Integer getId() {
        return pauta.getId();
    }

    public Integer getOwnerId() {
        return pauta.getOwnerId();
    }

    public void setOwnerId(Integer ownerId) {
        pauta.setOwnerId(ownerId);
    }

/* pauta - generated by stajanov (do not edit/delete) */

    public String doBeforeSave() {
        return null;
    }

    public PautaEnsayo getPautaEnsayo() {
        return pautaEnsayo;
    }

    public void setPautaEnsayo(PautaEnsayo pautaEnsayo) {
        this.pautaEnsayo = pautaEnsayo;
    }

    public Pauta getPauta() {
        return pauta;
    }

    public void setPauta(Pauta pauta) {
        this.pauta = pauta;
    }
}
