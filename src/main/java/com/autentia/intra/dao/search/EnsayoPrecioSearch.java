/* 
 *  TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L.  
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


package com.autentia.intra.dao.search;

import com.autentia.intra.businessobject.Ensayo;
import com.autentia.intra.businessobject.Organization;
import com.autentia.intra.dao.SearchCriteria;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

public class EnsayoPrecioSearch extends SearchCriteria {

/* generated by stajanov (do not edit/delete) */


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


        if (isClientSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            if (getClient() == null) {
                ret.append("client is NULL");
            } else {
                ret.append("client = :arg" + (iArgNum++));
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

        if (isEnsayoSet() && getEnsayo() != null) {
            ret.add(ensayo);
        }


        if (isClientSet() && getClient() != null) {
            ret.add(client);
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

        unsetEnsayo();


        unsetClient();


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
        ret.append("EnsayoPrecioSearch{");


        if (isEnsayoSet()) {
            ret.append("(ensayo");
            ret.append("=" + ensayo);
            ret.append(")");
        }


        if (isClientSet()) {
            ret.append("(client");
            ret.append("=" + client);
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


    public boolean isClientSet() {
        return clientSet;
    }

    public Organization getClient() {
        return client;
    }

    public void setClient(Organization client) {
        this.client = client;
        this.clientSet = true;
    }

    public void unsetClient() {
        this.clientSet = false;
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

    private boolean ensayoSet;
    private Ensayo ensayo;


    private boolean clientSet;
    private Organization client;


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
        return customIsSearchActive() || ensayoSet || clientSet || costSet || ownerIdSet || departmentIdSet || startInsertDateSet || endInsertDateSet || startUpdateDateSet || endUpdateDateSet;
    }

    /* generated by stajanov (do not edit/delete) */
    private void customGetHQL(StringBuilder ret, int argNum) {
        // TODO Auto-generated method stub

    }

    private boolean customIsSearchActive() {
        // TODO Auto-generated method stub
        return false;
    }

    private void customGetArguments(ArrayList<Object> ret) {
        // TODO Auto-generated method stub

    }

    private void customToString(StringBuilder ret) {
        // TODO Auto-generated method stub

    }

    private void customReset() {
        // TODO Auto-generated method stub

    }

}
