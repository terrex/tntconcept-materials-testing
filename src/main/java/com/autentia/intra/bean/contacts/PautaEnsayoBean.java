package com.autentia.intra.bean.contacts;

import com.autentia.intra.bean.BaseBean;
import com.autentia.intra.businessobject.Ensayo;
import com.autentia.intra.businessobject.ParametroString;
import com.autentia.intra.businessobject.PautaEnsayo;
import com.autentia.intra.businessobject.PautaEnsayoDimension;
import com.autentia.intra.dao.hibernate.PautaEnsayoDAO;
import com.autentia.intra.manager.contacts.PautaEnsayoManager;
import com.autentia.intra.util.FacesUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.faces.component.UIData;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PautaEnsayoBean extends BaseBean {
    private PautaEnsayoDAO pautaEnsayoDAO = PautaEnsayoDAO.getDefault();

    public String createDimension() {
        PautaEnsayoDimension item = new PautaEnsayoDimension();
        item.setPautaEnsayo(pautaEnsayo);
        if (pautaEnsayo.getDimensiones() == null) {
            pautaEnsayo.setDimensiones(new ArrayList<PautaEnsayoDimension>());
        }
        pautaEnsayo.getDimensiones().add(item);
        return null;
    }

    public String editDimension() {
        PautaEnsayoDimension item = new PautaEnsayoDimension();
        if (pautaEnsayo.getDimensiones() == null) {
            pautaEnsayo.setDimensiones(new ArrayList<PautaEnsayoDimension>());
        }
        pautaEnsayo.getDimensiones().add(item);
        return null;
    }

    public String deleteDimension() {
        UIData table = (UIData) FacesUtils.getComponent("pautaEnsayo:dimensiones");
        pautaEnsayo.getDimensiones().remove((PautaEnsayoDimension) table.getRowData());
        return null;
    }

    public String editarEstePautaEnsayo() {
        UIData table = (UIData) FacesUtils.getComponent("pauta:ensayos");
        pautaEnsayo = (PautaEnsayo) table.getRowData();
        pautaEnsayo = (PautaEnsayo) pautaEnsayoDAO.merge(pautaEnsayo);
        return "editPautaEnsayo";
    }

    /**
     * Create a new empty instance of the one-to-many field
     *
     * @return forward to the same page
     */
    public String createRequerimiento() {
        ParametroString item = new ParametroString();
        item.setPautaEnsayo(pautaEnsayo);
        if (pautaEnsayo.getRequerimientos() == null) {
            pautaEnsayo.setRequerimientos(new ArrayList<ParametroString>());
        }
        pautaEnsayo.getRequerimientos().add(item);
        return null;
    }

    /**
     * Create a new empty instance of the one-to-many field
     *
     * @return forward to the same page
     */
    public String editRequerimiento() {
        ParametroString item = new ParametroString();
        if (pautaEnsayo.getRequerimientos() == null) {
            pautaEnsayo.setRequerimientos(new ArrayList<ParametroString>());
        }
        pautaEnsayo.getRequerimientos().add(item);
        return null;
    }

    /**
     * Delete selected instance of the one-to-many field
     *
     * @return forward to the same page
     */
    public String deleteRequerimiento() {
        UIData table = (UIData) FacesUtils.getComponent("pautaEnsayo:requerimientos");
        pautaEnsayo.getRequerimientos().remove((ParametroString) table.getRowData());
        return null;
    }


    /**
     * Logger
     */
    private static final Log log = LogFactory.getLog(PautaEnsayoBean.class);

    /**
     * Manager
     */
    private static PautaEnsayoManager manager = PautaEnsayoManager.getDefault();


    public PautaEnsayo getPautaEnsayo() {
        return pautaEnsayo;
    }

    /**
     * Active ProjectEnsayo object
     */
    private PautaEnsayo pautaEnsayo;

    // Getters to list possible values of related entities

    // Getters to list possible values of enum fields

    // Methods to create/remove instances of one-to-many entities (slave entities)

    /**
     * Save bean and stay on it
     *
     * @return forward to list page
     */
    public String save() {
        ((PautaBean) FacesUtils.getBean("pautaBean")).save();
        if (pautaEnsayo.getId() == null) {
            manager.insertEntity(pautaEnsayo);
        } else {
            manager.updateEntity(pautaEnsayo);
        }
        return null;
    }

    /**
     * Check if we have an active object.
     *
     * @return true is an object is selected
     */
    public boolean isPautaEnsayoSelected() {
        return pautaEnsayo != null;
    }

    // Getters and setters to handle uploads

    // Getters and setters to manipulate active ProjectEnsayo object

    public Integer getId() {
        return pautaEnsayo.getId();
    }


    public String getName() {
        return pautaEnsayo.getEnsayo().getName();
    }

    public void setName(String name) {

    }

    public Ensayo getEnsayo() {
        return pautaEnsayo.getEnsayo();
    }

    public void setEnsayo(Ensayo ensayo) {
        pautaEnsayo.setEnsayo(ensayo);
    }


    public String getProcedimiento() {
        return pautaEnsayo.getProcedimiento();
    }

    public void setProcedimiento(String procedimiento) {
        pautaEnsayo.setProcedimiento(procedimiento);
    }


    public List<ParametroString> getRequerimientos() {
        return pautaEnsayo.getRequerimientos();
    }

    public void setRequerimientos(List<ParametroString> requerimiento) {
        pautaEnsayo.setRequerimientos(requerimiento);
    }


    public Integer getOwnerId() {
        return pautaEnsayo.getOwnerId();
    }

    public void setOwnerId(Integer ownerId) {
        pautaEnsayo.setOwnerId(ownerId);
    }


    public Integer getDepartmentId() {
        return pautaEnsayo.getDepartmentId();
    }

    public void setDepartmentId(Integer departmentId) {
        pautaEnsayo.setDepartmentId(departmentId);
    }


    public Date getInsertDate() {
        return pautaEnsayo.getInsertDate();
    }

    public void setInsertDate(Date insertDate) {
        pautaEnsayo.setInsertDate(insertDate);
    }


    public Date getUpdateDate() {
        return pautaEnsayo.getUpdateDate();
    }

    public void setUpdateDate(Date updateDate) {
        pautaEnsayo.setUpdateDate(updateDate);
    }


    public void setPautaEnsayo(PautaEnsayo pautaEnsayo) {
        this.pautaEnsayo = pautaEnsayo;
    }
}