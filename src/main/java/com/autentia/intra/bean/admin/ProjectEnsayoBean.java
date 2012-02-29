package com.autentia.intra.bean.admin;

import com.autentia.intra.bean.BaseBean;
import com.autentia.intra.bean.NavigationResults;
import com.autentia.intra.businessobject.*;
import com.autentia.intra.dao.SortCriteria;
import com.autentia.intra.dao.hibernate.ProjectEnsayoDAO;
import com.autentia.intra.dao.hibernate.UserDAO;
import com.autentia.intra.manager.admin.ProjectEnsayoManager;
import com.autentia.intra.upload.Uploader;
import com.autentia.intra.upload.UploaderFactory;
import com.autentia.intra.util.FacesUtils;
import com.autentia.intra.util.FileUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.custom.fileupload.UploadedFile;

import javax.faces.component.UIData;
import javax.faces.model.SelectItem;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectEnsayoBean extends BaseBean {
    private ProjectEnsayoDAO projectEnsayoDAO = ProjectEnsayoDAO.getDefault();

    public String createDimension() {
        ProjectEnsayoDimension item = new ProjectEnsayoDimension();
        item.setProjectEnsayo(projectEnsayo);
        if (projectEnsayo.getDimensiones() == null) {
            projectEnsayo.setDimensiones(new ArrayList<ProjectEnsayoDimension>());
        }
        projectEnsayo.getDimensiones().add(item);
        return null;
    }

    public String editDimension() {
        ProjectEnsayoDimension item = new ProjectEnsayoDimension();
        if (projectEnsayo.getDimensiones() == null) {
            projectEnsayo.setDimensiones(new ArrayList<ProjectEnsayoDimension>());
        }
        projectEnsayo.getDimensiones().add(item);
        return null;
    }

    public String deleteDimension() {
        UIData table = (UIData) FacesUtils.getComponent("projectEnsayo:dimensiones");
        projectEnsayo.getDimensiones().remove((ProjectEnsayoDimension) table.getRowData());
        return null;
    }


    public String createIdentificacionCliente() {
        ProjectEnsayoIdentificacion item = new ProjectEnsayoIdentificacion();
        item.setProjectEnsayo(projectEnsayo);
        if (projectEnsayo.getReferenciasCliente() == null) {
            projectEnsayo.setReferenciasCliente(new ArrayList<ProjectEnsayoIdentificacion>());
        }
        projectEnsayo.getReferenciasCliente().add(item);
        return null;
    }

    public String editIdentificacionCliente() {
        ProjectEnsayoIdentificacion item = new ProjectEnsayoIdentificacion();
        if (projectEnsayo.getReferenciasCliente() == null) {
            projectEnsayo.setReferenciasCliente(new ArrayList<ProjectEnsayoIdentificacion>());
        }
        projectEnsayo.getReferenciasCliente().add(item);
        return null;
    }

    public String deleteIdentificacionCliente() {
        UIData table = (UIData) FacesUtils.getComponent("projectEnsayo:identificacionesCliente");
        projectEnsayo.getReferenciasCliente().remove((ProjectEnsayoIdentificacion) table.getRowData());
        return null;
    }


    /**
     * Create a new empty instance of the one-to-many field
     *
     * @return forward to the same page
     */
    public String createRequerimiento() {
        ProjectEnsayoRequerimiento item = new ProjectEnsayoRequerimiento();
        item.setProjectEnsayo(projectEnsayo);
        if (projectEnsayo.getRequerimientos() == null) {
            projectEnsayo.setRequerimientos(new ArrayList<ProjectEnsayoRequerimiento>());
        }
        projectEnsayo.getRequerimientos().add(item);
        return null;
    }

    /**
     * Create a new empty instance of the one-to-many field
     *
     * @return forward to the same page
     */
    public String editRequerimiento() {
        ProjectEnsayoRequerimiento item = new ProjectEnsayoRequerimiento();
        if (projectEnsayo.getRequerimientos() == null) {
            projectEnsayo.setRequerimientos(new ArrayList<ProjectEnsayoRequerimiento>());
        }
        projectEnsayo.getRequerimientos().add(item);
        return null;
    }

    /**
     * Delete selected instance of the one-to-many field
     *
     * @return forward to the same page
     */
    public String deleteRequerimiento() {
        UIData table = (UIData) FacesUtils.getComponent("projectEnsayo:requerimientos");
        projectEnsayo.getRequerimientos().remove((ProjectEnsayoRequerimiento) table.getRowData());
        return null;
    }


    public List<ProjectEnsayoRequerimiento> getRequerimientos() {
        return projectEnsayo.getRequerimientos();
    }

    public void setRequerimientos(List<ProjectEnsayoRequerimiento> requerimiento) {
        projectEnsayo.setRequerimientos(requerimiento);
    }


    /**
     * Logger
     */
    private static final Log log = LogFactory.getLog(ProjectEnsayoBean.class);

    /**
     * Manager
     */
    private static ProjectEnsayoManager manager = ProjectEnsayoManager.getDefault();

    /**
     * Temporary photo field
     */
    private String oldPhoto;

    public String getOldPhoto() {
        return oldPhoto;
    }

    public void setOldPhoto(String oldPhoto) {
        this.oldPhoto = oldPhoto;
    }

    public UploadedFile getUploadPhoto() {
        return uploadPhoto;
    }

    public void setUploadPhoto(UploadedFile uploadPhoto) {
        if (uploadPhoto != null) {
            oldPhoto = projectEnsayo.getPhoto();
            this.uploadPhoto = uploadPhoto;
            projectEnsayo.setPhoto(FileUtil.getFileName(uploadPhoto.getName()));
        }
        this.uploadPhoto = uploadPhoto;
    }

    /**
     * Uploaded photo object
     */
    private UploadedFile uploadPhoto;


    public String list() {
        return NavigationResults.EDIT;
    }

    public ProjectEnsayo getProjectEnsayo() {
        return projectEnsayo;
    }

    public void setProjectEnsayo(ProjectEnsayo projectEnsayo) {
        this.projectEnsayo = projectEnsayo;
    }

    /**
     * Active ProjectEnsayo object
     */
    private ProjectEnsayo projectEnsayo;

    // Getters to list possible values of related entities

    // Getters to list possible values of enum fields

    // Methods to create/remove instances of one-to-many entities (slave entities)

    /**
     * Delete selected instance of the one-to-many field
     *
     * @return forward to the same page
     */
    public String editarEsteEnsayo() {
        UIData table = (UIData) FacesUtils.getComponent("project:ensayos");
        projectEnsayo = (ProjectEnsayo) table.getRowData();

        //arregla all-delete-orphan
        if (projectEnsayo.getDimensiones() == null)
            projectEnsayo.setDimensiones(new ArrayList<ProjectEnsayoDimension>());
        if (projectEnsayo.getReferenciasCliente() == null)
            projectEnsayo.setReferenciasCliente(new ArrayList<ProjectEnsayoIdentificacion>());
        if (projectEnsayo.getRequerimientos() == null)
            projectEnsayo.setRequerimientos(new ArrayList<ProjectEnsayoRequerimiento>());
        if (projectEnsayo.getId() != null) /* perhaps fixes #88 */
            projectEnsayo = (ProjectEnsayo) projectEnsayoDAO.merge(projectEnsayo);
        //cargar ids de cliente del proyecto padre si este ensayo aun no tiene ids de cliente.
        if (projectEnsayo.getReferenciasCliente() == null
                || projectEnsayo.getReferenciasCliente().isEmpty() &&
                projectEnsayo.getProject().getReferenciasCliente() != null) {
            for (ProjectIdentificacion pi : projectEnsayo.getProject().getReferenciasCliente()) {
                ProjectEnsayoIdentificacion pei = new ProjectEnsayoIdentificacion();
                pei.setName(pi.getName());
                pei.setValor(pi.getValor());
                pei.setProjectEnsayo(projectEnsayo);
                if (projectEnsayo.getReferenciasCliente() == null) {
                    projectEnsayo.setReferenciasCliente(new ArrayList<ProjectEnsayoIdentificacion>());
                }
                projectEnsayo.getReferenciasCliente().add(pei);
            }
        }
        users = null;
        return "editProjectEnsayo";
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
     * Upload service
     */
    private static final Uploader uploader = UploaderFactory.getInstance("projectEnsayo");


    /**
     * Save bean and stay on it
     *
     * @return forward to list page
     */
    public String save() {
        if (projectEnsayo.getId() == null) {
            manager.insertEntity(projectEnsayo);
        } else {
            manager.updateEntity(projectEnsayo);
        }

        // Handle uploads for photo field
        if (uploadPhoto != null) {
            try {
                uploader.replace(Integer.toString(projectEnsayo.getId()), oldPhoto, uploadPhoto);
            } catch (IOException e) {
                log.error("save - exception uploading field photo", e);
                FacesUtils.addErrorMessage("photo", "error.fileTransfer", e.getMessage());
            }
        }

        //projectEnsayo = null;

        return null;
    }

    /**
     * Check if we have an active object.
     *
     * @return true is an object is selected
     */
    public boolean isProjectEnsayoSelected() {
        return projectEnsayo != null;
    }

    // Getters and setters to handle uploads

    // Getters and setters to manipulate active ProjectEnsayo object

    public java.lang.Integer getId() {
        return projectEnsayo.getId();
    }


    public String getName() {
        return projectEnsayo.getEnsayo().getName();
    }

    public void setName(String name) {

    }


    public String getProcedimiento() {
        return projectEnsayo.getProcedimiento();
    }

    public void setProcedimiento(String procedimiento) {
        projectEnsayo.setProcedimiento(procedimiento);
    }


    public String getRequerimiento() {
        return projectEnsayo.getRequerimiento();
    }

    public void setRequerimiento(String requerimiento) {
        projectEnsayo.setRequerimiento(requerimiento);
    }


    public String getValor() {
        return projectEnsayo.getValor();
    }

    public void setValor(String valor) {
        projectEnsayo.setValor(valor);
    }


    public String getConformidad() {
        return projectEnsayo.getConformidad();
    }

    public void setConformidad(String conformidad) {
        projectEnsayo.setConformidad(conformidad);
    }


    public Boolean getTerminado() {
        return projectEnsayo.getTerminado();
    }

    public void setTerminado(Boolean terminado) {
        projectEnsayo.setTerminado(terminado);
    }


    public Integer getOwnerId() {
        return projectEnsayo.getOwnerId();
    }

    public void setOwnerId(Integer ownerId) {
        projectEnsayo.setOwnerId(ownerId);
    }


    public Integer getDepartmentId() {
        return projectEnsayo.getDepartmentId();
    }

    public void setDepartmentId(Integer departmentId) {
        projectEnsayo.setDepartmentId(departmentId);
    }


    public Date getInsertDate() {
        return projectEnsayo.getInsertDate();
    }

    public void setInsertDate(Date insertDate) {
        projectEnsayo.setInsertDate(insertDate);
    }


    public Date getUpdateDate() {
        return projectEnsayo.getUpdateDate();
    }

    public void setUpdateDate(Date updateDate) {
        projectEnsayo.setUpdateDate(updateDate);
    }


    public static Uploader getUploader() {
        return uploader;
    }

    public String deleteFile() throws IOException {
        if (projectEnsayo != null && projectEnsayo.getId() != null && !"".equals(projectEnsayo.getPhoto()))
            uploader.delete(String.valueOf(projectEnsayo.getId()), projectEnsayo.getPhoto());
        projectEnsayo.setPhoto(null);
        return null; //return to same page
    }
}
