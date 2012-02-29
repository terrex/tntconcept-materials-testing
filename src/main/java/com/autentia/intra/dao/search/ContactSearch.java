package com.autentia.intra.dao.search;

import com.autentia.intra.businessobject.Organization;
import com.autentia.intra.dao.SearchCriteria;

import java.util.ArrayList;
import java.util.Date;

/**
 * Class to search for Contact objects
 *
 * @author stajanov code generator
 */
public class ContactSearch extends SearchCriteria {
/* generated by stajanov (do not edit/delete) */


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


        if (isEmailSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            if (getEmail() == null) {
                ret.append("email is NULL");
            } else {
                ret.append("email like :arg" + (iArgNum++));
            }
        }


        if (isPhoneSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            if (getPhone() == null) {
                ret.append("phone is NULL");
            } else {
                ret.append("phone = :arg" + (iArgNum++));
            }
        }


        if (isMobileSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            if (getMobile() == null) {
                ret.append("mobile is NULL");
            } else {
                ret.append("mobile = :arg" + (iArgNum++));
            }
        }


        if (isNotifiedSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            if (getNotified() == null) {
                ret.append("notified is NULL");
            } else {
                ret.append("notified = :arg" + (iArgNum++));
            }
        }


        if (isPositionSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            if (getPosition() == null) {
                ret.append("position is NULL");
            } else {
                ret.append("position like :arg" + (iArgNum++));
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


        if (isOrganizationSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            if (getOrganization() == null) {
                ret.append("organization is NULL");
            } else {
                ret.append("organization = :arg" + (iArgNum++));
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


        if (isEmailSet() && getEmail() != null) {
            ret.add(email);
        }


        if (isPhoneSet() && getPhone() != null) {
            ret.add(phone);
        }


        if (isMobileSet() && getMobile() != null) {
            ret.add(mobile);
        }


        if (isNotifiedSet() && getNotified() != null) {
            ret.add(notified);
        }


        if (isPositionSet() && getPosition() != null) {
            ret.add(position);
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


        if (isOrganizationSet() && getOrganization() != null) {
            ret.add(organization);
        }

        customGetArguments(ret);
        return ret.toArray();
    }

    @Override
    public void reset() {


        unsetName();


        unsetEmail();


        unsetPhone();


        unsetMobile();


        unsetNotified();


        unsetPosition();


        unsetOwnerId();


        unsetDepartmentId();


        unsetStartInsertDate();
        unsetEndInsertDate();


        unsetStartUpdateDate();
        unsetEndUpdateDate();


        unsetOrganization();

        customReset();
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("ContactSearch{");


        if (isNameSet()) {
            ret.append("(name");
            ret.append("=" + name);
            ret.append(")");
        }


        if (isEmailSet()) {
            ret.append("(email");
            ret.append("=" + email);
            ret.append(")");
        }


        if (isPhoneSet()) {
            ret.append("(phone");
            ret.append("=" + phone);
            ret.append(")");
        }


        if (isMobileSet()) {
            ret.append("(mobile");
            ret.append("=" + mobile);
            ret.append(")");
        }


        if (isNotifiedSet()) {
            ret.append("(notified");
            ret.append("=" + notified);
            ret.append(")");
        }


        if (isPositionSet()) {
            ret.append("(position");
            ret.append("=" + position);
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


        if (isOrganizationSet()) {
            ret.append("(organization");
            ret.append("=" + organization);
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


    public boolean isEmailSet() {
        return emailSet;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        this.emailSet = true;
    }

    public void unsetEmail() {
        this.emailSet = false;
    }


    public boolean isPhoneSet() {
        return phoneSet;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
        this.phoneSet = true;
    }

    public void unsetPhone() {
        this.phoneSet = false;
    }


    public boolean isMobileSet() {
        return mobileSet;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
        this.mobileSet = true;
    }

    public void unsetMobile() {
        this.mobileSet = false;
    }


    public boolean isNotifiedSet() {
        return notifiedSet;
    }

    public java.lang.Boolean getNotified() {
        return notified;
    }

    public void setNotified(java.lang.Boolean notified) {
        this.notified = notified;
        this.notifiedSet = true;
    }

    public void unsetNotified() {
        this.notifiedSet = false;
    }


    public boolean isPositionSet() {
        return positionSet;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
        this.positionSet = true;
    }

    public void unsetPosition() {
        this.positionSet = false;
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


    public boolean isOrganizationSet() {
        return organizationSet;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
        this.organizationSet = true;
    }

    public void unsetOrganization() {
        this.organizationSet = false;
    }

    // Fields


    private boolean nameSet;
    private String name;


    private boolean emailSet;
    private String email;


    private boolean phoneSet;
    private String phone;


    private boolean mobileSet;
    private String mobile;


    private boolean notifiedSet;
    private java.lang.Boolean notified;


    private boolean positionSet;
    private String position;


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


    private boolean organizationSet;
    private Organization organization;


    // Returns if there are a search condition active
    public boolean isSearchActive() {
        return customIsSearchActive() || nameSet || emailSet || phoneSet || mobileSet || notifiedSet || positionSet || ownerIdSet || departmentIdSet || startInsertDateSet || endInsertDateSet || startUpdateDateSet || endUpdateDateSet || organizationSet;
    }

/* generated by stajanov (do not edit/delete) */

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