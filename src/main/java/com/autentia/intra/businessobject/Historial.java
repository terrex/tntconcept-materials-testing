package com.autentia.intra.businessobject;

import com.autentia.intra.dao.ITransferObject;

import java.io.Serializable;
import java.util.Date;

public class Historial implements Serializable, ITransferObject {
    /**
     * Serial version field
     */
    private static final long serialVersionUID = -1L;

    private Integer id;
    private Integer ownerId;
    private Integer departmentId;
    private Date insertDate;
    private Date updateDate;
    private User usuario;
    private Date fechaHora;
    private Class klazz;
    private Integer idObjeto;
    private HistorialType tipoCambio;

    public HistorialType getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(HistorialType tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public Integer getIdObjeto() {
        return idObjeto;
    }

    public void setIdObjeto(Integer idObjeto) {
        this.idObjeto = idObjeto;
    }

    public Class getKlazz() {
        return klazz;
    }

    public void setKlazz(Class klazz) {
        this.klazz = klazz;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public Integer getId() {
        return id;
    }

    private void setId(Integer id) {
        this.id = id;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
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

    public boolean equals(Object that) {
        try {
            if (that == null)
                return false;
            else
                return this.getId().equals(((Historial) that).getId());
        } catch (Exception e) {
            return false;
        }
    }

    public int hashCode() {
        if (this.getId() == null)
            return super.hashCode();
        else
            return this.getId().intValue();
    }

    private Integer updatedById;

    public Integer getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Integer updatedById) {
        this.updatedById = updatedById;
    }
}
