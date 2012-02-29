package com.autentia.intra.dao.search;

import com.autentia.intra.businessobject.Organization;
import com.autentia.intra.businessobject.PautaEnsayo;
import com.autentia.intra.dao.ITransferObject;
import com.autentia.intra.dao.SearchCriteria;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class to search for Pauta objects
 *
 * @author terrex
 */
public class PautaSearch extends SearchCriteria {


    @Override
    public String getHQL() {
        StringBuilder ret = new StringBuilder();
        int iArgNum = 0;


        if (isNameSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            if (getName() == null) {
                ret.append("name is NULL");
            } else {
                ret.append("name like :arg" + (iArgNum++));
            }
        }


        if (isClienteSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            if (getCliente() == null) {
                ret.append("cliente is NULL");
            } else {
                ret.append("cliente = :arg" + (iArgNum++));
            }
        }

        if (isMaterialSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            if (getMaterial() == null) {
                ret.append("material is NULL");
            } else {
                ret.append("material like :arg" + (iArgNum++));
            }
        }


        if (isAgrupacionSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            if (getAgrupacion() == null) {
                ret.append("agrupacion is NULL");
            } else {
                ret.append("agrupacion like :arg" + (iArgNum++));
            }
        }

        if (isDescriptionSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            if (getDescription() == null) {
                ret.append("description is NULL");
            } else {
                ret.append("description = :arg" + (iArgNum++));
            }
        }


        if (isCostSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            if (getCost() == null) {
                ret.append("cost is NULL");
            } else {
                ret.append("cost = :arg" + (iArgNum++));
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


        if (isEnsayosSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            ret.append("ensayos.id IN (:arg" + (iArgNum++) + ")");
        }


        customGetHQL(ret, iArgNum);
        return ret.toString();
    }

    @Override
    public Object[] getArguments() {
        ArrayList<Object> ret = new ArrayList<Object>();


        if (isNameSet() && getName() != null) {
            ret.add(name);
        }

        if (isClienteSet() && getCliente() != null) {
            ret.add(cliente);
        }

        if (isMaterialSet() && getMaterial() != null) {
            ret.add(material);
        }

        if (isAgrupacionSet() && getAgrupacion() != null) {
            ret.add(agrupacion);
        }

        if (isDescriptionSet() && getDescription() != null) {
            ret.add(description);
        }


        if (isCostSet() && getCost() != null) {
            ret.add(cost);
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


        if (isEnsayosSet()) {
            List<Integer> params = new ArrayList<Integer>();
            for (ITransferObject to : ensayos) {
                params.add(to.getId());
            }
            ret.add(params);
        }


        customGetArguments(ret);
        return ret.toArray();
    }

    @Override
    public void reset() {


        unsetName();


        unsetCliente();


        unsetMaterial();


        unsetAgrupacion();


        unsetDescription();


        unsetCost();


        unsetOwnerId();


        unsetDepartmentId();


        unsetStartInsertDate();
        unsetEndInsertDate();


        unsetStartUpdateDate();
        unsetEndUpdateDate();


        unsetEnsayos();


        customReset();
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("PautaSearch{");


        if (isNameSet()) {
            ret.append("(name");
            ret.append("=" + name);
            ret.append(")");
        }


        if (isClienteSet()) {
            ret.append("(cliente");
            ret.append("=" + cliente);
            ret.append(")");
        }


        if (isMaterialSet()) {
            ret.append("(material");
            ret.append("=" + material);
            ret.append(")");
        }


        if (isAgrupacionSet()) {
            ret.append("(agrupacion");
            ret.append("=" + agrupacion);
            ret.append(")");
        }


        if (isDescriptionSet()) {
            ret.append("(description");
            ret.append("=" + description);
            ret.append(")");
        }


        if (isCostSet()) {
            ret.append("(cost");
            ret.append("=" + cost);
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


        if (isEnsayosSet()) {
            ret.append("(ensayos={");
            for (Object o : ensayos.toArray()) {
                ret.append("," + o);
            }
            ret.append("})");
        }


        customToString(ret);
        ret.append("}");
        return ret.toString();
    }

    // Getters and setters


    public boolean isNameSet() {
        return nameSet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.nameSet = true;
    }

    public void unsetName() {
        this.nameSet = false;
    }


    public boolean isClienteSet() {
        return clienteSet;
    }

    public Organization getCliente() {
        return cliente;
    }

    public void setCliente(Organization cliente) {
        this.cliente = cliente;
        this.clienteSet = true;
    }

    public void unsetCliente() {
        this.clienteSet = false;
    }


    public boolean isMaterialSet() {
        return materialSet;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
        this.materialSet = true;
    }

    public void unsetMaterial() {
        this.materialSet = false;
    }


    public boolean isAgrupacionSet() {
        return agrupacionSet;
    }

    public String getAgrupacion() {
        return agrupacion;
    }

    public void setAgrupacion(String agrupacion) {
        this.agrupacion = agrupacion;
        this.agrupacionSet = true;
    }

    public void unsetAgrupacion() {
        this.agrupacionSet = false;
    }


    public boolean isDescriptionSet() {
        return descriptionSet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        this.descriptionSet = true;
    }

    public void unsetDescription() {
        this.descriptionSet = false;
    }


    public boolean isCostSet() {
        return costSet;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
        this.costSet = true;
    }

    public void unsetCost() {
        this.costSet = false;
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


    public boolean isEnsayosSet() {
        return ensayosSet;
    }

    public List<PautaEnsayo> getEnsayos() {
        return ensayos;
    }

    public void setEnsayos(List<PautaEnsayo> ensayos) {
        this.ensayos = ensayos;
        this.ensayosSet = true;
    }

    public void unsetEnsayos() {
        this.ensayosSet = false;
    }

    // Fields


    private boolean nameSet;
    private String name;


    private boolean clienteSet;
    private Organization cliente;


    private boolean materialSet;
    private String material;


    private boolean agrupacionSet;
    private String agrupacion;


    private boolean descriptionSet;
    private String description;


    private boolean costSet;
    private BigDecimal cost;


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


    private boolean ensayosSet;
    private List<PautaEnsayo> ensayos;


    // Returns if there are a search condition active
    public boolean isSearchActive() {
        return customIsSearchActive() || nameSet || clienteSet || materialSet || agrupacionSet || descriptionSet || costSet || ownerIdSet || departmentIdSet || startInsertDateSet || endInsertDateSet || startUpdateDateSet || endUpdateDateSet || ensayosSet;
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
