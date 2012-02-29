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

/**
 * Transfer object to store Users
 *
 * @author stajanov code generator
 */
public class User implements Serializable, ITransferObject {
    private String cargo;
    /**
     * Serial version field
     */
    private static final long serialVersionUID = -1L;

/* user - generated by stajanov (do not edit/delete) */

    // Fields


    private Integer id;


    private boolean active;


    private String login;


    private String password;


    private String name;


    private Date startDate;


    private String nif;


    private UserGenre genre;


    private String socialSecurityNumber;


    private boolean workingInClient;


    private Date birthDate;


    private boolean married;


    private int childrenNumber;


    private String travelAvailability;


    private String academicQualification;


    private String email;


    private String phone;


    private String mobile;


    private String street;


    private String postalCode;


    private String city;


    private String bank;


    private String account;


    private BigDecimal salary;


    private BigDecimal salaryExtras;


    private String drivenLicenseType;


    private String vehicleType;


    private String licensePlate;


    private String securityCard;


    private String healthInsurance;


    private String notes;


    private String photo;


    private Date endTestPeriodDate;


    private Date endContractDate;


    private int dayDuration;


    private String contractObservations;


    private Date insertDate;


    private Date updateDate;


    private Role role;


    private UserCategory category;


    private Province province;


    private DocumentCategory documentCategory;


    private Department department;


    private ContractType contractType;


    private WorkingAgreement agreement;
    private Organization organization;

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    // Setters and getters


    public Integer getId() {
        return id;
    }

    private void setId(Integer id) {
        this.id = id;
    }


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }


    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }


    public UserGenre getGenre() {
        return genre;
    }

    public void setGenre(UserGenre genre) {
        this.genre = genre;
    }


    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }


    public boolean isWorkingInClient() {
        return workingInClient;
    }

    public void setWorkingInClient(boolean workingInClient) {
        this.workingInClient = workingInClient;
    }


    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }


    public boolean isMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }


    public int getChildrenNumber() {
        return childrenNumber;
    }

    public void setChildrenNumber(int childrenNumber) {
        this.childrenNumber = childrenNumber;
    }


    public String getTravelAvailability() {
        return travelAvailability;
    }

    public void setTravelAvailability(String travelAvailability) {
        this.travelAvailability = travelAvailability;
    }


    public String getAcademicQualification() {
        return academicQualification;
    }

    public void setAcademicQualification(String academicQualification) {
        this.academicQualification = academicQualification;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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


    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }


    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }


    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }


    public BigDecimal getSalaryExtras() {
        return salaryExtras;
    }

    public void setSalaryExtras(BigDecimal salaryExtras) {
        this.salaryExtras = salaryExtras;
    }


    public String getDrivenLicenseType() {
        return drivenLicenseType;
    }

    public void setDrivenLicenseType(String drivenLicenseType) {
        this.drivenLicenseType = drivenLicenseType;
    }


    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }


    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }


    public String getSecurityCard() {
        return securityCard;
    }

    public void setSecurityCard(String securityCard) {
        this.securityCard = securityCard;
    }


    public String getHealthInsurance() {
        return healthInsurance;
    }

    public void setHealthInsurance(String healthInsurance) {
        this.healthInsurance = healthInsurance;
    }


    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }


    public Date getEndTestPeriodDate() {
        return endTestPeriodDate;
    }

    public void setEndTestPeriodDate(Date endTestPeriodDate) {
        this.endTestPeriodDate = endTestPeriodDate;
    }


    public Date getEndContractDate() {
        return endContractDate;
    }

    public void setEndContractDate(Date endContractDate) {
        this.endContractDate = endContractDate;
    }


    public int getDayDuration() {
        return dayDuration;
    }

    public void setDayDuration(int dayDuration) {
        this.dayDuration = dayDuration;
    }


    public String getContractObservations() {
        return contractObservations;
    }

    public void setContractObservations(String contractObservations) {
        this.contractObservations = contractObservations;
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


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    public UserCategory getCategory() {
        return category;
    }

    public void setCategory(UserCategory category) {
        this.category = category;
    }


    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }


    public DocumentCategory getDocumentCategory() {
        return documentCategory;
    }

    public void setDocumentCategory(DocumentCategory documentCategory) {
        this.documentCategory = documentCategory;
    }


    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }


    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }


    public WorkingAgreement getAgreement() {
        return agreement;
    }

    public void setAgreement(WorkingAgreement agreement) {
        this.agreement = agreement;
    }

    public Integer getOwnerId() {
        return id;
    }

    public Integer getDepartmentId() {
        return department.getId();
    }

    public void setOwnerId(Integer ownerId) {

    }

    public void setDepartmentId(Integer departmentId) {

    }

    @Override
    public boolean equals(Object that) {
        try {
            if (that == null)
                return false;
            else
                return this.getId().equals(((User) that).getId());
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

/* user - generated by stajanov (do not edit/delete) */

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    private Integer updatedById;

    public Integer getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Integer updatedById) {
        this.updatedById = updatedById;
    }
}