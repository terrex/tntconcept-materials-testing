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
import com.autentia.intra.util.ConfigurationUtil;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Transfer object to store Organizations
 *
 * @author stajanov code generator
 */
public class Organization implements Serializable, ITransferObject, Cloneable {
    public static boolean historial = true;

    private String direccionEnvioInformes;
    private String direccionEnvioFactura;
    private Date fechaAlta;
    private OrganizationCertificado certificado;
    private OrganizationEstado estado;
    private Date fechaCaducidadCertificado;
    private Set<EnsayoPrecio> ensayoPrecios;
    private Set<PautaPrecio> pautaPrecios;
    private Set<ProductoPrecio> productoPrecios;
    private String numeroProveedor;

    public String getNumeroProveedor() {
        return numeroProveedor;
    }

    public void setNumeroProveedor(String numeroProveedor) {
        this.numeroProveedor = numeroProveedor;
    }

    public Set<ProductoPrecio> getProductoPrecios() {
        return productoPrecios;
    }

    public void setProductoPrecios(Set<ProductoPrecio> productoPrecios) {
        this.productoPrecios = productoPrecios;
    }

    public Set<PautaPrecio> getPautaPrecios() {
        return pautaPrecios;
    }

    public void setPautaPrecios(Set<PautaPrecio> pautaPrecios) {
        this.pautaPrecios = pautaPrecios;
    }

    public Set<EnsayoPrecio> getEnsayoPrecios() {
        return ensayoPrecios;
    }

    public void setEnsayoPrecios(Set<EnsayoPrecio> ensayoPrecios) {
        this.ensayoPrecios = ensayoPrecios;
    }

    public OrganizationEstado getEstado() {
        return estado;
    }

    public void setEstado(OrganizationEstado estado) {
        this.estado = estado;
    }

    public OrganizationCertificado getCertificado() {
        return certificado;
    }

    public void setCertificado(OrganizationCertificado certificado) {
        this.certificado = certificado;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getDireccionEnvioFactura() {
        return direccionEnvioFactura;
    }

    public void setDireccionEnvioFactura(String direccionEnvioFactura) {
        this.direccionEnvioFactura = direccionEnvioFactura;
    }

    public String getDireccionEnvioInformes() {
        return direccionEnvioInformes;
    }

    public void setDireccionEnvioInformes(String direccionEnvioInformes) {
        this.direccionEnvioInformes = direccionEnvioInformes;
    }

    @Override
    public Organization clone() {
        Organization o = new Organization();
        o.setAcronimo(acronimo);
        o.setCategory(category);
        o.setCif(cif);
        o.setCity(city);
        o.setColumnaRefCli(columnaRefCli);
        o.setCountry(country);
        o.setDepartmentId(departmentId);
        o.setEmail(email);
        o.setFax(fax);
        o.setFtpsite(ftpsite);
        o.setLocator(locator);
        o.setName(name);
        o.setNotes(notes);
        o.setNumber(number);
        o.setPhone(phone);
        o.setPostalCode(postalCode);
        o.setProcedimientoAdministrativo(procedimientoAdministrativo);
        o.setProvince(province);
        o.setState(state);
        o.setStreet(street);
        o.setType(type);
        o.setWebsite(website);
        return o;
    }

    /**
     * Serial version field
     */
    private static final long serialVersionUID = -1L;

/* organization - generated by stajanov (do not edit/delete) */


    private String acronimo;
    private String procedimientoAdministrativo;

    public String getProcedimientoAdministrativo() {
        return procedimientoAdministrativo;
    }

    public void setProcedimientoAdministrativo(String procedimientoAdministrativo) {
        this.procedimientoAdministrativo = procedimientoAdministrativo;
    }

    public String getAcronimo() {
        return acronimo;
    }

    public void setAcronimo(String acronimo) {
        this.acronimo = acronimo;
    }

    // Fields


    private Integer id;


    private String name;


    private String cif;


    private String street;


    private String number;


    private String locator;


    private String postalCode;


    private String city;


    private String state;


    private String country;


    private String phone;


    private String fax;


    private String email;


    private String website;


    private String ftpsite;


    private String notes;


    private String columnaRefCli;


    private Integer ownerId;


    private Integer departmentId;


    private Date insertDate;


    private Date updateDate;


    private OrganizationType type;


    private OrganizationISOCategory category;


    private Province province;

    // Setters and getters


    public Integer getId() {
        return id;
    }

    private void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }


    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    public String getLocator() {
        return locator;
    }

    public void setLocator(String locator) {
        this.locator = locator;
    }


    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }


    public String getFtpsite() {
        return ftpsite;
    }

    public void setFtpsite(String ftpsite) {
        this.ftpsite = ftpsite;
    }


    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


    public String getColumnaRefCli() {
        return columnaRefCli;
    }

    public void setColumnaRefCli(String columnaRefCli) {
        this.columnaRefCli = columnaRefCli;
    }


    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
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


    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }


    public OrganizationType getType() {
        return type;
    }

    public void setType(OrganizationType type) {
        this.type = type;
    }


    public OrganizationISOCategory getCategory() {
        return category;
    }

    public void setCategory(OrganizationISOCategory category) {
        this.category = category;
    }


    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }


    @Override
    public boolean equals(Object that) {
        try {
            if (that == null)
                return false;
            else
                return this.getId().equals(((Organization) that).getId());
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public int hashCode() {
        if (this.getId() == null)
            return super.hashCode();
        else
            return this.getId().intValue();
    }

/* organization - generated by stajanov (do not edit/delete) */

    private Integer updatedById;

    public Integer getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Integer updatedById) {
        this.updatedById = updatedById;
    }

    public void setFechaCaducidadCertificado(Date fechaCaducidadCertificado) {
        this.fechaCaducidadCertificado = fechaCaducidadCertificado;
    }

    public Date getFechaCaducidadCertificado() {
        return fechaCaducidadCertificado;
    }

    public String getNameAcro() {
        if (acronimo != null && acronimo.length() > 0)
            return name + " (" + acronimo + ")";
        else
            return name;
    }

    public boolean isEsCliente() {
        return type.getId() == ConfigurationUtil.getDefault().getOrganizationTypeClient()
                || type.getId() == ConfigurationUtil.getDefault().getOrganizationTypeProviderAndClient();
    }

    public boolean isEsProveedor() {
        return type.getId() == ConfigurationUtil.getDefault().getOrganizationTypeProvider()
                || type.getId() == ConfigurationUtil.getDefault().getOrganizationTypeProviderAndClient();
    }
}
