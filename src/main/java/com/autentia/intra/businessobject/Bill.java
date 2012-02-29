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
import com.autentia.intra.dao.hibernate.DepartmentDAO;
import com.autentia.intra.util.ConfigurationUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Transfer object to store Bills
 *
 * @author stajanov code generator
 */
public class Bill implements Serializable, ITransferObject {
    public static boolean historial = true;

    /**
     * Serial version field
     */
    private static final long serialVersionUID = -1L;


    private BillType billType;

    private Organization provider;

    private Contact contact;

    private String orderNumber;

    private Set<AccountEntry> entries;

    private Integer ownerId;

    private Integer departmentId;

    private Date insertDate;

    private Date updateDate;
    private Organization client;
    private Set<Albaran> albarans = new HashSet<Albaran>();
    private Set<Offer> offers = new HashSet<Offer>();
    private Department lineaTrabajo;
    private Boolean pendienteDePedido;
    private Set<Project> projects;

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public Boolean isPendienteDePedido() {
        return pendienteDePedido;
    }

    public Boolean getPendienteDePedido() {
        return pendienteDePedido;
    }

    public void setPendienteDePedido(Boolean pendienteDePedido) {
        this.pendienteDePedido = pendienteDePedido;
    }

    public Department getLineaTrabajo() {
        if (lineaTrabajo == null)
            return this.getLineaTrabajoDefault();
        else
            return lineaTrabajo;
    }

    public void setLineaTrabajo(Department lineaTrabajo) {
        this.lineaTrabajo = lineaTrabajo;
    }

    public Set<Albaran> getAlbarans() {
        return albarans;
    }

    public void setAlbarans(Set<Albaran> albarans) {
        this.albarans = albarans;
    }

    public Set<Offer> getOffers() {
        return offers;
    }

    public void setOffers(Set<Offer> offers) {
        this.offers = offers;
    }

    public Organization getClient() {
        return client;
    }

    public void setClient(Organization client) {
        this.client = client;
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

    public Set<AccountEntry> getEntries() {
        return entries;
    }

    public void setEntries(Set<AccountEntry> entries) {
        this.entries = entries;
    }

    public BillType getBillType() {
        return billType;
    }

    public void setBillType(BillType billType) {
        this.billType = billType;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Organization getProvider() {
        return provider;
    }

    public void setProvider(Organization provider) {
        this.provider = provider;
    }

    public int getHasEntries() {
        return (entries != null) ? entries.size() : 0;
    }
    /* bill - generated by stajanov (do not edit/delete) */

    public Set<BillBreakDown> getBreakDown() {
        return breakDown;
    }

    public void setBreakDown(Set<BillBreakDown> breakDown) {
        this.breakDown = breakDown;
    }

    private Set<BillBreakDown> breakDown;

    /* bill - generated by stajanov (do not edit/delete) */


    public Date getStartBillDate() {
        return startBillDate;
    }

    public void setStartBillDate(Date startBillDate) {
        this.startBillDate = startBillDate;
    }


    public Date getEndBillDate() {
        return endBillDate;
    }

    public void setEndBillDate(Date endBillDate) {
        this.endBillDate = endBillDate;
    }

    // Fields

    private Date startBillDate;


    private Date endBillDate;

    private Integer id;


    private Date creationDate;


    private int expiration = 90;


    private BillPaymentMode paymentMode = BillPaymentMode.TRANSFER;


    private BillState state;


    private BigDecimal amount;


    private String number;


    private String name;


    private String file;


    private String fileMime;


    private String observations;


    private Project project;

    // Setters and getters


    public Integer getId() {
        return id;
    }

    private void setId(Integer id) {
        this.id = id;
    }


    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }


    public int getExpiration() {
        return expiration;
    }

    public void setExpiration(int expiration) {
        this.expiration = expiration;
    }


    public BillPaymentMode getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(BillPaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }


    public BillState getState() {
        return state;
    }

    public void setState(BillState state) {
        this.state = state;
    }

    /*
     public BigDecimal getAmount() {
         return amount;
     }
     public void setAmount( BigDecimal amount ) {
         this.amount = amount;
     }
      */


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }


    public String getFileMime() {
        return fileMime;
    }

    public void setFileMime(String fileMime) {
        this.fileMime = fileMime;
    }


    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }


    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
        if (project != null)
            this.client = project.getClient();
    }


    public boolean equals(Object that) {
        try {
            if (that == null)
                return false;
            else
                return this.getId().equals(((Bill) that).getId());
        } catch (Exception e) {
            return false;
        }
    }

    public int hashCode() {
        if (this.getId() == null)
            return super.hashCode();
        else
            return this.getId().intValue();
    }


    public BigDecimal getTotal() {
        BigDecimal valor = new BigDecimal(0);

        if (getBreakDown() != null) {
            for (BillBreakDown elem : getBreakDown()) {
                valor = valor.add(elem.getTotal());
            }

        }

        if (albarans != null) {
            for (Albaran a : albarans) {
                valor = valor.add(a.getImporte().multiply(new BigDecimal(ConfigurationUtil.getDefault().getIva() / 100 + 1)));
            }
        }

        if (offers != null) {
            for (Offer a : offers) {
                if (a.getTotalCosts() != null && a.getTotalRoles() != null)
                    valor = valor.add(a.getTotalCosts().add(a.getTotalRoles()).multiply(new BigDecimal(a.getIva().doubleValue() / 100 + 1)));
                else if (a.getTotalCosts() != null)
                    valor = valor.add(a.getTotalCosts().multiply(new BigDecimal(a.getIva().doubleValue() / 100 + 1)));
                else if (a.getTotalRoles() != null)
                    valor = valor.add(a.getTotalRoles().multiply(new BigDecimal(a.getIva().doubleValue() / 100 + 1)));
            }
        }

        valor = valor.setScale(2, RoundingMode.HALF_EVEN);
        return valor;
    }

    public BigDecimal getTotalNoTaxes() {
        BigDecimal valor = new BigDecimal(0);

        if (getBreakDown() != null) {
            for (BillBreakDown elem : getBreakDown()) {
                valor = valor.add(elem.getAmount().multiply(elem.getUnits()));
            }

        }

        try {
            for (Albaran a : albarans) {
                valor = valor.add(a.getImporte());
            }
        } catch (NullPointerException e) {
            //
        }

        try {
            for (Offer a : offers) {
                valor = valor.add(a.getTotalCosts());
            }
        } catch (NullPointerException e) {
            //
        }

        try {
            for (Offer a : offers) {
                valor = valor.add(a.getTotalRoles());
            }
        } catch (NullPointerException e) {
            //
        }

        valor = valor.setScale(2, RoundingMode.HALF_EVEN);
        return valor;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    private Integer entriesNumber;

    public Integer getEntriesNumber() {
        return entriesNumber;
    }

    public void setEntriesNumber(Integer entriesNumber) {
        this.entriesNumber = entriesNumber;
    }


    private Integer updatedById;

    public Integer getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Integer updatedById) {
        this.updatedById = updatedById;
    }

    public Department getLineaTrabajoDefault() {
        Department result = DepartmentDAO.getDefault().getById(1);
        for (Offer o : this.getOffers()) {
            if (o.getLineaTrabajo() != null && o.getLineaTrabajo().getId() != 1) {
                result = o.getLineaTrabajo();
            }
        }
        for (Albaran a : this.getAlbarans()) {
            for (Project p : a.getProjects()) {
                Offer o = p.getOffer();
                if (o != null && o.getLineaTrabajo() != null && o.getLineaTrabajo().getId() != 1) {
                    result = o.getLineaTrabajo();
                }
            }
        }
        return result;
    }
}
