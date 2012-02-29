package com.autentia.intra.dao.search;

import com.autentia.intra.dao.SearchCriteria;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

/**
 * Class to search for Ensayo objects
 *
 * @author terrex
 */
public class EnsayoSearch extends SearchCriteria {
    private String nameIngles;
    private boolean nameInglesSet;


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


        if (isNameInglesSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            if (getNameIngles() == null) {
                ret.append("nameIngles is NULL");
            } else {
                ret.append("nameIngles like :arg" + (iArgNum++));
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


        customGetHQL(ret, iArgNum);
        return ret.toString();
    }

    @Override
    public Object[] getArguments() {
        ArrayList<Object> ret = new ArrayList<Object>();


        if (isNameSet() && getName() != null) {
            ret.add(name);
        }

        if (isNameInglesSet() && getNameIngles() != null) {
            ret.add(nameIngles);
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


        customGetArguments(ret);
        return ret.toArray();
    }

    @Override
    public void reset() {


        unsetName();


        unsetNameIngles();


        unsetDescription();


        unsetCost();


        unsetOwnerId();


        unsetDepartmentId();


        unsetStartInsertDate();
        unsetEndInsertDate();


        unsetStartUpdateDate();
        unsetEndUpdateDate();


        customReset();
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("EnsayoSearch{");


        if (isNameSet()) {
            ret.append("(name");
            ret.append("=" + name);
            ret.append(")");
        }


        if (isNameInglesSet()) {
            ret.append("(nameIngles");
            ret.append("=" + nameIngles);
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


    public boolean isNameInglesSet() {
        return nameInglesSet;
    }

    public String getNameIngles() {
        return nameIngles;
    }

    public void setNameIngles(String nameIngles) {
        this.nameIngles = nameIngles;
        this.nameInglesSet = true;
    }

    public void unsetNameIngles() {
        this.nameInglesSet = false;
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

    // Fields


    private boolean nameSet;
    private String name;


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


    // Returns if there are a search condition active
    public boolean isSearchActive() {
        return customIsSearchActive() || nameSet || nameInglesSet || descriptionSet || costSet || ownerIdSet || departmentIdSet || startInsertDateSet || endInsertDateSet || startUpdateDateSet || endUpdateDateSet;
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
