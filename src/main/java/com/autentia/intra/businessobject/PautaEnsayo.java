package com.autentia.intra.businessobject;

import com.autentia.intra.dao.ITransferObject;
import com.autentia.intra.dao.hibernate.EnsayoDAO;
import com.autentia.intra.util.FacesUtils;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.util.*;

public class PautaEnsayo implements Serializable, ITransferObject {
    private Integer updatedById;
    private List<PautaEnsayoDimension> dimensiones;

    public List<PautaEnsayoDimension> getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(List<PautaEnsayoDimension> dimensiones) {
        this.dimensiones = dimensiones;
    }

    public Integer getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Integer updatedById) {
        this.updatedById = updatedById;
    }

    public void onSelectedEnsayoChanged(ValueChangeEvent event) {
        setEnsayo((Ensayo) event.getNewValue());
        FacesUtils.renderResponse();
    }

    class comparaProcedimientos implements Comparator<SelectItem> {
        public int compare(SelectItem o1, SelectItem o2) {
            return o1.getLabel().compareTo(o2.getLabel());
        }
    }

    public List<SelectItem> getProcedimientosBySelectedEnsayo() {
        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();

        if (this.ensayo == null) {
            return ret;
        }

        this.ensayo = (Ensayo) EnsayoDAO.getDefault().merge(this.ensayo); //fixes #3
        Set<EnsayoProcedimiento> refs = this.ensayo.getProcedimientos();
        for (EnsayoProcedimiento ref : refs) {
            ret.add(new SelectItem(ref.getName()));
        }
        Collections.sort(ret, new comparaProcedimientos());
        return ret;
    }

    private Integer id;
    private String procedimiento;
    private Integer ownerId;
    private Integer departmentId;
    private Date insertDate;
    private Date updateDate;
    private Pauta pauta;
    private List<ParametroString> requerimientos;
    private Ensayo ensayo;

    public Ensayo getEnsayo() {
        return ensayo;
    }

    public void setEnsayo(Ensayo ensayo) {
        this.ensayo = ensayo;
    }

    public List<ParametroString> getRequerimientos() {
        return requerimientos;
    }

    public void setRequerimientos(List<ParametroString> requerimientos) {
        this.requerimientos = requerimientos;
    }

    public Pauta getPauta() {
        return pauta;
    }

    public void setPauta(Pauta pauta) {
        this.pauta = pauta;
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

    public String getProcedimiento() {
        return procedimiento;
    }

    public void setProcedimiento(String procedimiento) {
        this.procedimiento = procedimiento;
    }

    public Integer getId() {
        return id;
    }

    private void setId(Integer id) {
        this.id = id;
    }
}
