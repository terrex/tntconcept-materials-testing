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

package com.autentia.intra.businessobject;

import com.autentia.intra.dao.ITransferObject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * Transfer object to store AccountEntrys
 *
 * @author stajanov code generator
 */
public class AccountEntry implements Serializable, ITransferObject {
    /**
     * Serial version field
     */
    private static final long serialVersionUID = -1L;

    private Set<Bill> bills;

    public int hashCode() {
        if (this.getId() == null)
            return super.hashCode();
        else
            return this.getId().intValue();
    }

    public Set<Bill> getBills() {
        return bills;
    }

    public void setBills(Set<Bill> bills) {
        this.bills = bills;
    }

    /* accountEntry - generated by stajanov (do not edit/delete) */

    // Fields

    private Integer id;

    private Date date;

    private Date amountDate;

    private String concept;

    private BigDecimal amount;

    private BigDecimal amountAccountNow;

    private String observations;

    private String entryNumber;

    private String docNumber;

    private Account account;

    private AccountEntryType type;

    private Integer ownerId;

    private Integer departmentId;

    private Date insertDate;

    private Date updateDate;

    // Setters and getters

    public Integer getId() {
        return id;
    }

    private void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getAmountDate() {
        return amountDate;
    }

    public void setAmountDate(Date amountDate) {
        this.amountDate = amountDate;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getEntryNumber() {
        return entryNumber;
    }

    public void setEntryNumber(String entryNumber) {
        this.entryNumber = entryNumber;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public AccountEntryType getType() {
        return type;
    }

    public void setType(AccountEntryType type) {
        this.type = type;
    }

    public boolean equals(Object that) {
        try {
            return this.getId().equals(((AccountEntry) that).getId());
        } catch (Exception e) {
            return false;
        }


    }

    public BigDecimal getAmountAccountNow() {
        return amountAccountNow;
    }

    public void setAmountAccountNow(BigDecimal amountAccountNow) {
        this.amountAccountNow = amountAccountNow;
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

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /* accountEntry - generated by stajanov (do not edit/delete) */


    private Integer updatedById;

    public Integer getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Integer updatedById) {
        this.updatedById = updatedById;
    }
}