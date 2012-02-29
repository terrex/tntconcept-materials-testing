package com.autentia.intra.dao.search;

import com.autentia.intra.businessobject.Ensayo;
import com.autentia.intra.dao.ITransferObject;
import com.autentia.intra.dao.SearchCriteria;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class to search for Pauta objects
 *
 * @author terrex
 */
public class PautaEnsayoSearch extends SearchCriteria {
    private boolean ensayoSet;
    private Ensayo ensayo;


    @Override
    public String getHQL() {
        StringBuilder ret = new StringBuilder();
        int iArgNum = 0;


        if (isEnsayoSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            if (getEnsayo() == null) {
                ret.append("ensayo is NULL");
            } else {
                ret.append("ensayo = :arg" + (iArgNum++));
            }
        }


        if (isProcedimientoSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            if (getProcedimiento() == null) {
                ret.append("procedimiento is NULL");
            } else {
                ret.append("procedimiento like :arg" + (iArgNum++));
            }
        }


        if (isOwnerIdSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            if (getOwnerId() == null) {
                ret.append("ownerId is NULL");
            } else {
                ret.append("ownerId = :arg" + (iArgNum++));
            }
        }


        if (isDepartmentIdSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            if (getDepartmentId() == null) {
                ret.append("departmentId is NULL");
            } else {
                ret.append("departmentId = :arg" + (iArgNum++));
            }
        }


        if (isStartInsertDateSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            if (startInsertDate == null) {
                ret.append("insertDate=:arg" + (iArgNum++));
            } else {
                ret.append("insertDate>=:arg" + (iArgNum++));
            }
        }
        if (isEndInsertDateSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            if (endInsertDate == null) {
                ret.append("insertDate=:arg" + (iArgNum++));
            } else {
                ret.append("insertDate<=:arg" + (iArgNum++));
            }
        }


        if (isStartUpdateDateSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            if (startUpdateDate == null) {
                ret.append("updateDate=:arg" + (iArgNum++));
            } else {
                ret.append("updateDate>=:arg" + (iArgNum++));
            }
        }
        if (isEndUpdateDateSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            if (endUpdateDate == null) {
                ret.append("updateDate=:arg" + (iArgNum++));
            } else {
                ret.append("updateDate<=:arg" + (iArgNum++));
            }
        }


        if (isRequerimientosSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            ret.append("requerimientos.id IN (:arg" + (iArgNum++) + ")");
        }


        customGetHQL(ret, iArgNum);
        return ret.toString();
    }

    @Override
    public Object[] getArguments() {
        ArrayList<Object> ret = new ArrayList<Object>();


        if (isEnsayoSet() && getEnsayo() != null) {
            ret.add(ensayo);
        }

        if (isProcedimientoSet() && getProcedimiento() != null) {
            ret.add(procedimiento);
        }

        if (isOwnerIdSet() && getOwnerId() != null) {
            ret.add(ownerId);
        }


        if (isDepartmentIdSet() && getDepartmentId() != null) {
            ret.add(departmentId);
        }


        if (isStartInsertDateSet()) {
            ret.add(startInsertDate);
        }
        if (isEndInsertDateSet()) {
            ret.add(endInsertDate);
        }


        if (isStartUpdateDateSet()) {
            ret.add(startUpdateDate);
        }
        if (isEndUpdateDateSet()) {
            ret.add(endUpdateDate);
        }


        if (isRequerimientosSet()) {
            List<Integer> params = new ArrayList<Integer>();
            for (ITransferObject to : requerimientos) {
                params.add(to.getId());
            }
            ret.add(params);
        }


        customGetArguments(ret);
        return ret.toArray();
    }

    @Override
    public void reset() {


        unsetEnsayo();

        unsetProcedimiento();


        unsetOwnerId();


        unsetDepartmentId();


        unsetStartInsertDate();
        unsetEndInsertDate();


        unsetStartUpdateDate();
        unsetEndUpdateDate();


        unsetRequerimientos();


        customReset();
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("PautaSearch{");


        if (isEnsayoSet()) {
            ret.append("(ensayo");
            ret.append("=" + ensayo);
            ret.append(")");
        }


        if (isProcedimientoSet()) {
            ret.append("(procedimiento");
            ret.append("=" + procedimiento);
            ret.append(")");
        }


        if (isOwnerIdSet()) {
            ret.append("(ownerId");
            ret.append("=" + ownerId);
            ret.append(")");
        }


        if (isDepartmentIdSet()) {
            ret.append("(departmentId");
            ret.append("=" + departmentId);
            ret.append(")");
        }


        if (isStartInsertDateSet()) {
            ret.append("(startInsertDate");
            ret.append("=" + startInsertDate);
            ret.append(")");
        }
        if (isEndInsertDateSet()) {
            ret.append("(endInsertDate");
            ret.append("=" + endInsertDate);
            ret.append(")");
        }


        if (isStartUpdateDateSet()) {
            ret.append("(startUpdateDate");
            ret.append("=" + startUpdateDate);
            ret.append(")");
        }
        if (isEndUpdateDateSet()) {
            ret.append("(endUpdateDate");
            ret.append("=" + endUpdateDate);
            ret.append(")");
        }


        if (isRequerimientosSet()) {
            ret.append("(requerimientos={");
            for (Object o : requerimientos.toArray()) {
                ret.append("," + o);
            }
            ret.append("})");
        }


        customToString(ret);
        ret.append("}");
        return ret.toString();
    }

    // Getters and setters

    public boolean isEnsayoSet() {
        return ensayoSet;
    }

    public Ensayo getEnsayo() {
        return ensayo;
    }

    public void setEnsayo(Ensayo ensayo) {
        this.ensayo = ensayo;
        this.ensayoSet = true;
    }

    public void unsetEnsayo() {
        this.ensayoSet = false;
    }


    public boolean isProcedimientoSet() {
        return procedimientoSet;
    }

    public String getProcedimiento() {
        return procedimiento;
    }

    public void setProcedimiento(String procedimiento) {
        this.procedimiento = procedimiento;
        this.procedimientoSet = true;
    }

    public void unsetProcedimiento() {
        this.procedimientoSet = false;
    }


    public boolean isOwnerIdSet() {
        return ownerIdSet;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
        this.ownerIdSet = true;
    }

    public void unsetOwnerId() {
        this.ownerIdSet = false;
    }


    public boolean isDepartmentIdSet() {
        return departmentIdSet;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
        this.departmentIdSet = true;
    }

    public void unsetDepartmentId() {
        this.departmentIdSet = false;
    }


    public boolean isStartInsertDateSet() {
        return startInsertDateSet;
    }

    public Date getStartInsertDate() {
        return startInsertDate;
    }

    public void setStartInsertDate(Date startInsertDate) {
        this.startInsertDate = startInsertDate;
        this.startInsertDateSet = true;
    }

    public void unsetStartInsertDate() {
        this.startInsertDateSet = false;
    }

    public boolean isEndInsertDateSet() {
        return endInsertDateSet;
    }

    public Date getEndInsertDate() {
        return endInsertDate;
    }

    public void setEndInsertDate(Date endInsertDate) {
        this.endInsertDate = endInsertDate;
        this.endInsertDateSet = true;
    }

    public void unsetEndInsertDate() {
        this.endInsertDateSet = false;
    }


    public boolean isStartUpdateDateSet() {
        return startUpdateDateSet;
    }

    public Date getStartUpdateDate() {
        return startUpdateDate;
    }

    public void setStartUpdateDate(Date startUpdateDate) {
        this.startUpdateDate = startUpdateDate;
        this.startUpdateDateSet = true;
    }

    public void unsetStartUpdateDate() {
        this.startUpdateDateSet = false;
    }

    public boolean isEndUpdateDateSet() {
        return endUpdateDateSet;
    }

    public Date getEndUpdateDate() {
        return endUpdateDate;
    }

    public void setEndUpdateDate(Date endUpdateDate) {
        this.endUpdateDate = endUpdateDate;
        this.endUpdateDateSet = true;
    }

    public void unsetEndUpdateDate() {
        this.endUpdateDateSet = false;
    }


    public boolean isRequerimientosSet() {
        return requerimientosSet;
    }

    public List<Ensayo> getRequerimientos() {
        return requerimientos;
    }

    public void setRequerimientos(List<Ensayo> requerimientos) {
        this.requerimientos = requerimientos;
        this.requerimientosSet = true;
    }

    public void unsetRequerimientos() {
        this.requerimientosSet = false;
    }

    // Fields


    private boolean procedimientoSet;
    private String procedimiento;


    private boolean ownerIdSet;
    private Integer ownerId;


    private boolean departmentIdSet;
    private Integer departmentId;


    private boolean startInsertDateSet;
    private Date startInsertDate;
    private boolean endInsertDateSet;
    private Date endInsertDate;


    private boolean startUpdateDateSet;
    private Date startUpdateDate;
    private boolean endUpdateDateSet;
    private Date endUpdateDate;


    private boolean requerimientosSet;
    private List<Ensayo> requerimientos;


    // Returns if there are a search condition active
    public boolean isSearchActive() {
        return customIsSearchActive() || ensayoSet || procedimientoSet || ownerIdSet || departmentIdSet || startInsertDateSet || endInsertDateSet || startUpdateDateSet || endUpdateDateSet || requerimientosSet;
    }


    private void customGetHQL(StringBuilder ret, int iArgNum) {
    }

    private boolean customIsSearchActive() {
        return false;
    }

    private void customToString(StringBuilder ret) {
    }

    private void customReset() {
    }

    private void customGetArguments(ArrayList ret) {
    }
}