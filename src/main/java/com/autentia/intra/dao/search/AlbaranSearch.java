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

import com.autentia.intra.businessobject.Bill;
import com.autentia.intra.businessobject.Organization;
import com.autentia.intra.businessobject.Project;
import com.autentia.intra.dao.ITransferObject;
import com.autentia.intra.dao.SearchCriteria;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;


public class AlbaranSearch extends SearchCriteria {


    private void customGetArguments(ArrayList<Object> ret) {
        // TODO Auto-generated method stub

    }

    private void customGetHQL(StringBuilder ret, int argNum) {
        // TODO Auto-generated method stub

    }

    private void customToString(StringBuilder ret) {
        // TODO Auto-generated method stub

    }

/* Albaran - generated by stajanov (do not edit/delete) */


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


        if (isInformeSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            if (getInforme() == null) {
                ret.append("id IN (SELECT albaran.id FROM Project WHERE referenciaLaboratorio IS NULL)");
            } else {
                ret.append("id IN (SELECT albaran.id FROM Project WHERE referenciaLaboratorio LIKE :arg" + (iArgNum++) + ")");
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


        if (isBillSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            if (getBill() == null) {
                ret.append("bill is NULL");
            } else {
                ret.append("bill = :arg" + (iArgNum++));
            }
        }


        if (isStartFechaSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            if (startFecha == null) {
                ret.append("fecha=:arg" + (iArgNum++));
            } else {
                ret.append("fecha>=:arg" + (iArgNum++));
            }
        }
        if (isEndFechaSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            if (endFecha == null) {
                ret.append("fecha=:arg" + (iArgNum++));
            } else {
                ret.append("fecha<=:arg" + (iArgNum++));
            }
        }


        if (isDevueltoSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            if (getDevuelto() == null) {
                ret.append("devuelto is NULL");
            } else {
                ret.append("devuelto = :arg" + (iArgNum++));
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


        if (isProjectsSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            ret.append("project.id IN (:arg" + (iArgNum++) + ")");
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


        if (isInformeSet() && getInforme() != null) {
            ret.add(informe);
        }


        if (isClientSet() && getClient() != null) {
            ret.add(client);
        }


        if (isBillSet() && getBill() != null) {
            ret.add(bill);
        }


        if (isStartFechaSet()) {
            ret.add(startFecha);
        }
        if (isEndFechaSet()) {
            ret.add(endFecha);
        }


        if (isDevueltoSet() && getDevuelto() != null) {
            ret.add(devuelto);
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


        if (isProjectsSet()) {
            List<Integer> params = new ArrayList<Integer>();
            for (ITransferObject to : projects) {
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


        unsetInforme();


        unsetClient();


        unsetBill();


        unsetStartFecha();
        unsetEndFecha();


        unsetDevuelto();


        unsetOwnerId();


        unsetDepartmentId();


        unsetStartInsertDate();
        unsetEndInsertDate();


        unsetStartUpdateDate();
        unsetEndUpdateDate();


        unsetProjects();


        customReset();
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("AlbaranSearch{");


        if (isNameSet()) {
            ret.append("(name");
            ret.append("=" + name);
            ret.append(")");
        }


        if (isInformeSet()) {
            ret.append("(informe");
            ret.append("=" + informe);
            ret.append(")");
        }


        if (isClientSet()) {
            ret.append("(client");
            ret.append("=" + client);
            ret.append(")");
        }


        if (isBillSet()) {
            ret.append("(bill");
            ret.append("=" + bill);
            ret.append(")");
        }


        if (isStartFechaSet()) {
            ret.append("(startFecha");
            ret.append("=" + startFecha);
            ret.append(")");
        }
        if (isEndFechaSet()) {
            ret.append("(endFecha");
            ret.append("=" + endFecha);
            ret.append(")");
        }


        if (isDevueltoSet()) {
            ret.append("(devuelto");
            ret.append("=" + devuelto);
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


        if (isProjectsSet()) {
            ret.append("(projects={");
            for (Object o : projects.toArray()) {
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


    public boolean isInformeSet() {
        return informeSet;
    }

    public String getInforme() {
        return informe;
    }

    public void setInforme(String informe) {
        this.informe = informe;
        this.informeSet = true;
    }

    public void unsetInforme() {
        this.informeSet = false;
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


    public boolean isBillSet() {
        return billSet;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
        this.billSet = true;
    }

    public void unsetBill() {
        this.billSet = false;
    }


    public boolean isStartFechaSet() {
        return startFechaSet;
    }

    public Date getStartFecha() {
        return startFecha;
    }

    public void setStartFecha(Date startFecha) {
        this.startFecha = startFecha;
        this.startFechaSet = true;
    }

    public void unsetStartFecha() {
        this.startFechaSet = false;
    }

    public boolean isEndFechaSet() {
        return endFechaSet;
    }

    public Date getEndFecha() {
        return endFecha;
    }

    public void setEndFecha(Date endFecha) {
        this.endFecha = endFecha;
        this.endFechaSet = true;
    }

    public void unsetEndFecha() {
        this.endFechaSet = false;
    }


    public boolean isDevueltoSet() {
        return devueltoSet;
    }

    public Boolean getDevuelto() {
        return devuelto;
    }

    public void setDevuelto(Boolean devuelto) {
        this.devuelto = devuelto;
        this.devueltoSet = true;
    }

    public void unsetDevuelto() {
        this.devueltoSet = false;
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


    public boolean isProjectsSet() {
        return projectsSet;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
        this.projectsSet = true;
    }

    public void unsetProjects() {
        this.projectsSet = false;
    }

    // Fields


    private boolean nameSet;
    private String name;


    private boolean informeSet;
    private String informe;


    private boolean clientSet;
    private Organization client;


    private boolean billSet;
    private Bill bill;


    private boolean startFechaSet;
    private Date startFecha;
    private boolean endFechaSet;
    private Date endFecha;


    private boolean devueltoSet;
    private Boolean devuelto;


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


    private boolean projectsSet;
    private Set<Project> projects;


    // Returns if there are a search condition active
    public boolean isSearchActive() {
        return customIsSearchActive() || nameSet || informeSet || clientSet || billSet || startFechaSet || endFechaSet || devueltoSet || ownerIdSet || departmentIdSet || startInsertDateSet || endInsertDateSet || startUpdateDateSet || endUpdateDateSet || projectsSet;
    }


    /* Offer - generated by stajanov (do not edit/delete) */
    private boolean customIsSearchActive() {
        return false;
    }

    private void customReset() {
        // TODO Auto-generated method stub

    }

}
