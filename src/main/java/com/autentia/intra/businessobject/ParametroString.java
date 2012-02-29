package com.autentia.intra.businessobject;

import com.autentia.intra.dao.ITransferObject;

import java.io.Serializable;
import java.util.Date;

public class ParametroString implements Serializable, ITransferObject {
    private Integer id;
    private String nombre;
    private String nombreIngles;
    private String requerimiento;
    private Integer ownerId;
    private Integer departmentId;
    private Date insertDate;
    private Date updateDate;
    private PautaEnsayo pautaEnsayo;

    public PautaEnsayo getPautaEnsayo() {
        return pautaEnsayo;
    }

    public void setPautaEnsayo(PautaEnsayo pautaEnsayo) {
        this.pautaEnsayo = pautaEnsayo;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getRequerimiento() {
        return requerimiento;
    }

    public void setRequerimiento(String requerimiento) {
        this.requerimiento = requerimiento;
    }

    public String getNombreIngles() {
        return nombreIngles;
    }

    public void setNombreIngles(String nombreIngles) {
        this.nombreIngles = nombreIngles;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    private void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object that) {
        try {
            if (that == null)
                return false;
            else
                return this.getId().equals(((ParametroString) that).getId());
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

    private Integer updatedById;

    public Integer getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Integer updatedById) {
        this.updatedById = updatedById;
    }
}
