package com.autentia.intra.businessobject;

import com.autentia.intra.dao.ITransferObject;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;


/**
 * Transfer object to store NotaSalida
 *
 * @author terrex
 */
public class NotaSalida implements Serializable, ITransferObject {
    public static boolean historial = true;

    private static final long serialVersionUID = -1L;

    private Integer id;
    private String name;
    private Date fecha;
    private Set<ProjectEnsayo> projectEnsayos;
    private Integer departmentId;
    private Date insertDate;
    private Date updateDate;
    private Integer ownerId;
    private Integer updatedById;
    private Organization peticionario;
    private Organization clienteOrigen;
    private String observaciones;
    private Boolean unico;

    public Boolean isUnico() {
        return unico;
    }

    public Boolean getUnico() {
        return unico;
    }

    public void setUnico(Boolean unico) {
        this.unico = unico;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Organization getClienteOrigen() {
        return clienteOrigen;
    }

    public void setClienteOrigen(Organization clienteOrigen) {
        this.clienteOrigen = clienteOrigen;
    }

    public Organization getPeticionario() {
        return peticionario;
    }

    public void setPeticionario(Organization peticionario) {
        this.peticionario = peticionario;
    }

    @Override
    public boolean equals(Object that) {
        try {
            if (that == null)
                return false;
            else
                return this.getId().equals(((NotaSalida) that).getId());
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public int hashCode() {
        if (this.getId() == null)
            return super.hashCode();
        else
            return this.getId().intValue();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Set<ProjectEnsayo> getProjectEnsayos() {
        return projectEnsayos;
    }

    public void setProjectEnsayos(Set<ProjectEnsayo> projectEnsayos) {
        this.projectEnsayos = projectEnsayos;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Integer updatedById) {
        this.updatedById = updatedById;
    }
}


