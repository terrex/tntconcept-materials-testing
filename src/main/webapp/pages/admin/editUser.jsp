<%-- 
 * TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L.  
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
 *
 --%>


<%@page language="java" contentType="text/html; charset=UTF-8" %>

<%@include file="/inc/tlibs.jsp" %>

<html>
<head>
    <%@include file="/inc/uiCore.jsp" %>
</head>
<body>

<!-- editUser.jsp: generated by stajanov code generator -->
<f:loadBundle basename="com.autentia.intra.resources.messages" var="msg"/>
<i:location place="editUser" msg="${msg}"/>

<f:view>
<%@include file="/inc/header.jsp" %>
<h:form id="user" enctype="multipart/form-data">

<%-- Header --%>
<i:titleBar name="${userBean.name}" msg="${msg}">
    <h:commandLink action="#{userBean.save}"
                   onclick="if(!askSave('#{userBean.id}','#{msg['question.confirmSave']}')) return false;">
        <h:graphicImage title="#{msg.entityActions_save}" value="/img/save.gif" styleClass="titleImg"/>
    </h:commandLink>
    <h:commandLink action="#{userBean.delete}"
                   onclick="if( !confirm('#{msg['question.confirmDelete']}') ) return false;">
        <h:graphicImage title="#{msg.entityActions_delete}" value="/img/delete.gif" styleClass="titleImg"/>
    </h:commandLink>
    <h:commandLink action="#{userBean.list}" immediate="true">
        <h:graphicImage title="#{msg.entityActions_back}" value="/img/back.gif" styleClass="titleImg"/>
    </h:commandLink>
    <h:commandLink rendered="#{userBean.resetPasswordAvailable}" action="#{userBean.resetPassword}"
                   onclick="if(!confirm('#{msg['question.confirmResetPassword']}')) return false;">
        <h:graphicImage title="#{msg.entityActions_resetPassword}" value="/img/resetPassword.png"
                        styleClass="titleImg"/>
    </h:commandLink>
</i:titleBar>

<%-- Edition form --%>
<table class="editTable" cellpadding="0" cellspacing="0">

    <%-- Field: photo --%>
<div style="margin: 5pt;position: absolute;left: 450px;top: 100px;">
    <i:img type="user" objectId="${userBean.id}" file="${userBean.photo}"/>

</div>


    <%-- user - generated by stajanov (do not edit/delete) --%>


    <%-- Ignored field: id --%>


    <%-- Field: active --%>
<tr>
    <td class="editLabelRW">${msg['user.active']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="active"/>
            <h:selectBooleanCheckbox id="active" value="#{userBean.active}"/>
        </h:panelGroup>

    </td>
</tr>


    <%-- Field: login --%>
<tr>
    <td class="editLabelRW">*${msg['user.login']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="login"/>
            <h:inputText id="login" value="#{userBean.login}" maxlength="50" required="true"
                         styleClass="requiredFieldClass"/>
        </h:panelGroup>

    </td>
</tr>


    <%-- Ignored field: password --%>


    <%-- Field: name --%>
<tr>
    <td class="editLabelRW">*${msg['user.name']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="name"/>
            <h:inputText id="name" value="#{userBean.name}" size="70" maxlength="200" required="true"
                         styleClass="requiredFieldClass"/>
        </h:panelGroup>

    </td>
</tr>


    <%-- Field: startDate --%>
<tr>
    <td class="editLabelRW">*${msg['user.startDate']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="startDate"/>
            <t:inputCalendar id="startDate" value="#{userBean.startDate}" required="true"
                             styleClass="requiredFieldClass"
                             renderAsPopup="true" popupDateFormat="d/MM/yyyy" renderPopupButtonAsImage="true"
                             popupTodayString="#{msg['calendar.today']}" popupWeekString="#{msg['calendar.week']}"
                    />
        </h:panelGroup>

    </td>
</tr>


    <%-- Field: nif --%>
<tr>
    <td class="editLabelRW">${msg['user.nif']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="nif"/>
            <h:inputText id="nif" value="#{userBean.nif}" maxlength="16">
                <f:validator validatorId="autentia.genericIdentityCardValidator"/>
            </h:inputText>
        </h:panelGroup>

    </td>
</tr>


    <%-- Field: genre --%>
<tr>
    <td class="editLabelRW">${msg['user.genre']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="genre"/>
            <h:selectOneMenu id="genre" value="#{userBean.genre}">
                <f:converter converterId="autentia.EnumConverter"/>
                <f:selectItems value="#{userBean.genres}"/>
            </h:selectOneMenu>
        </h:panelGroup>

    </td>
</tr>


    <%-- Field: socialSecurityNumber --%>
<tr>
    <td class="editLabelRW">${msg['user.socialSecurityNumber']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="socialSecurityNumber"/>
            <h:inputText id="socialSecurityNumber" value="#{userBean.socialSecurityNumber}"/>
        </h:panelGroup>

    </td>
</tr>


    <%-- Field: workingInClient --%>
<tr>
    <td class="editLabelRW">${msg['user.workingInClient']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="workingInClient"/>
            <h:selectBooleanCheckbox id="workingInClient" value="#{userBean.workingInClient}"/>
        </h:panelGroup>

    </td>
</tr>


    <%-- Field: birthDate --%>
<tr>
    <td class="editLabelRW">${msg['user.birthDate']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="birthDate"/>
            <t:inputCalendar id="birthDate" value="#{userBean.birthDate}"
                             renderAsPopup="true" popupDateFormat="d/MM/yyyy" renderPopupButtonAsImage="true"
                             popupTodayString="#{msg['calendar.today']}" popupWeekString="#{msg['calendar.week']}"
                    />
        </h:panelGroup>

    </td>
</tr>


    <%-- Field: married --%>
<tr>
    <td class="editLabelRW">${msg['user.married']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="married"/>
            <h:selectBooleanCheckbox id="married" value="#{userBean.married}"/>
        </h:panelGroup>

    </td>
</tr>


    <%-- Field: childrenNumber --%>
<tr>
    <td class="editLabelRW">${msg['user.childrenNumber']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="childrenNumber"/>
            <h:inputText id="childrenNumber" value="#{userBean.childrenNumber}" size="10"/>
        </h:panelGroup>

    </td>
</tr>


    <%-- Field: travelAvailability --%>
<tr>
    <td class="editLabelRW">${msg['user.travelAvailability']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="travelAvailability"/>
            <h:inputText id="travelAvailability" value="#{userBean.travelAvailability}" size="70" maxlength="128"/>
        </h:panelGroup>

    </td>
</tr>


    <%-- Field: academicQualification --%>
<tr>
    <td class="editLabelRW">${msg['user.academicQualification']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="academicQualification"/>
            <h:inputText id="academicQualification" value="#{userBean.academicQualification}" size="70"
                         maxlength="200"/>
        </h:panelGroup>

    </td>
</tr>


    <%-- Field: email --%>
<tr>
    <td class="editLabelRW">${msg['user.email']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="email"/>
            <h:inputText id="email" value="#{userBean.email}" size="70" maxlength="128"/>
        </h:panelGroup>

    </td>
</tr>


    <%-- Field: phone --%>
<tr>
    <td class="editLabelRW">${msg['user.phone']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="phone"/>
            <h:inputText id="phone" value="#{userBean.phone}" maxlength="12"/>
        </h:panelGroup>

    </td>
</tr>


    <%-- Field: mobile --%>
<tr>
    <td class="editLabelRW">${msg['user.mobile']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="mobile"/>
            <h:inputText id="mobile" value="#{userBean.mobile}" maxlength="12"/>
        </h:panelGroup>

    </td>
</tr>


    <%-- Field: street --%>
<tr>
    <td class="editLabelRW">${msg['user.street']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="street"/>
            <h:inputText id="street" value="#{userBean.street}" size="70" maxlength="100"/>
        </h:panelGroup>

    </td>
</tr>


    <%-- Field: postalCode --%>
<tr>
    <td class="editLabelRW">${msg['user.postalCode']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="postalCode"/>
            <h:inputText id="postalCode" value="#{userBean.postalCode}" size="10" maxlength="5"/>
        </h:panelGroup>

    </td>
</tr>


    <%-- Field: city --%>
<tr>
    <td class="editLabelRW">${msg['user.city']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="city"/>
            <h:inputText id="city" value="#{userBean.city}" size="70" maxlength="100"/>
        </h:panelGroup>

    </td>
</tr>


    <%-- Field: bank --%>
<tr>
    <td class="editLabelRW">${msg['user.bank']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="bank"/>
            <h:inputText id="bank" value="#{userBean.bank}" size="70" maxlength="100"/>
        </h:panelGroup>

    </td>
</tr>


    <%-- Field: account --%>
<tr>
    <td class="editLabelRW">${msg['user.account']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="account"/>
            <h:inputText id="account" value="#{userBean.account}" size="20" maxlength="34"/>
        </h:panelGroup>

    </td>
</tr>


    <%-- Field: salary --%>
<tr>
    <td class="editLabelRW">${msg['user.salary']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="salary"/>
            <h:inputText id="salary" value="#{userBean.salary}" size="10"/>
        </h:panelGroup>

    </td>
</tr>


    <%-- Field: salaryExtras --%>
<tr>
    <td class="editLabelRW">${msg['user.salaryExtras']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="salaryExtras"/>
            <h:inputText id="salaryExtras" value="#{userBean.salaryExtras}" size="10"/>
        </h:panelGroup>

    </td>
</tr>


    <%-- Field: drivenLicenseType --%>
<tr>
    <td class="editLabelRW">${msg['user.drivenLicenseType']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="drivenLicenseType"/>
            <h:inputText id="drivenLicenseType" value="#{userBean.drivenLicenseType}" maxlength="10"/>
        </h:panelGroup>

    </td>
</tr>


    <%-- Field: vehicleType --%>
<tr>
    <td class="editLabelRW">${msg['user.vehicleType']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="vehicleType"/>
            <h:inputText id="vehicleType" value="#{userBean.vehicleType}" size="70" maxlength="50"/>
        </h:panelGroup>

    </td>
</tr>


    <%-- Field: licensePlate --%>
<tr>
    <td class="editLabelRW">${msg['user.licensePlate']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="licensePlate"/>
            <h:inputText id="licensePlate" value="#{userBean.licensePlate}" size="10" maxlength="45"/>
        </h:panelGroup>

    </td>
</tr>


    <%-- Field: securityCard --%>
<tr>
    <td class="editLabelRW">${msg['user.securityCard']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="securityCard"/>
            <h:inputText id="securityCard" value="#{userBean.securityCard}" maxlength="64"/>
        </h:panelGroup>

    </td>
</tr>


    <%-- Field: healthInsurance --%>
<tr>
    <td class="editLabelRW">${msg['user.healthInsurance']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="healthInsurance"/>
            <h:inputText id="healthInsurance" value="#{userBean.healthInsurance}" maxlength="64"/>
        </h:panelGroup>

    </td>
</tr>


    <%-- Field: notes --%>
<tr>
    <td class="editLabelRW">${msg['user.notes']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="notes"/>
            <h:inputText id="notes" value="#{userBean.notes}" size="70" maxlength="1024"/>
        </h:panelGroup>

    </td>
</tr>


    <%-- Field: cargo --%>
<tr>
    <td class="editLabelRW">${msg['user.cargo']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="cargo"/>
            <h:inputText id="cargo" value="#{userBean.user.cargo}" size="70" maxlength="128"/>
        </h:panelGroup>

    </td>
</tr>


    <%-- Field: organization --%>
<tr>
    <td class="editLabelRW">${msg['user.organization']}:</td>
    <td class="editFieldCell">
        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="organization"/>
            <h:selectOneMenu id="organization" value="#{userBean.user.organization}">
                <f:selectItems value="#{userBean.organizations}"/>
                <f:converter converterId="autentia.EntityConverter"/>
            </h:selectOneMenu>
        </h:panelGroup>
    </td>
</tr>

    <%-- Field: photo --%>
<tr>
    <td class="editLabelRW">${msg['user.photo']}:</td>

    <td class="editFieldCell">
        <h:commandLink action="#{userBean.deleteFile}" immediate="true" rendered="#{userBean.photo != null}">
            <h:graphicImage title="#{msg.entityActions_delete}" value="/img/delete.gif"
                            styleClass="titleImg"/>
        </h:commandLink>
        <i:fileLink type="user" objectId="${userBean.id}" file="${userBean.photo}"/>
        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="photo"/>
            <t:inputFileUpload id="photo" size="58" value="#{userBean.uploadPhoto}" storage="file"/>
        </h:panelGroup>

    </td>
</tr>


    <%-- Field: endTestPeriodDate --%>
<tr>
    <td class="editLabelRW">${msg['user.endTestPeriodDate']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="endTestPeriodDate"/>
            <t:inputCalendar id="endTestPeriodDate" value="#{userBean.endTestPeriodDate}"
                             renderAsPopup="true" popupDateFormat="d/MM/yyyy" renderPopupButtonAsImage="true"
                             popupTodayString="#{msg['calendar.today']}" popupWeekString="#{msg['calendar.week']}"
                    />
        </h:panelGroup>

    </td>
</tr>


    <%-- Field: endContractDate --%>
<tr>
    <td class="editLabelRW">${msg['user.endContractDate']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="endContractDate"/>
            <t:inputCalendar id="endContractDate" value="#{userBean.endContractDate}"
                             renderAsPopup="true" popupDateFormat="d/MM/yyyy" renderPopupButtonAsImage="true"
                             popupTodayString="#{msg['calendar.today']}" popupWeekString="#{msg['calendar.week']}"
                    />
        </h:panelGroup>

    </td>
</tr>


    <%-- Field: duration --%>
<tr>
    <td class="editLabelRW">*${msg['user.dayDuration']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="dayDuration"/>
            <h:inputText id="dayDuration" value="#{userBean.dayDuration}" size="10" required="true"
                         styleClass="requiredFieldClass">
                <f:converter converterId="autentia.MinuteToHourConverter"/>
            </h:inputText>
        </h:panelGroup>

    </td>
</tr>

    <%-- Field: agreement --%>
<tr>
    <td class="editLabelRW">*${msg['user.agreement']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="agreement"/>
            <h:selectOneMenu id="agreement" value="#{userBean.agreement}" required="true"
                             styleClass="requiredFieldClass">
                <f:selectItems value="#{userBean.agreements}"/>
                <f:converter converterId="autentia.EntityConverter"/>
            </h:selectOneMenu>
        </h:panelGroup>

    </td>
</tr>


    <%-- Field: contractObservations --%>
<tr>
    <td class="editLabelRW">${msg['user.contractObservations']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="contractObservations"/>
            <h:inputTextarea id="contractObservations" value="#{userBean.contractObservations}" rows="5" cols="68"/>
        </h:panelGroup>

    </td>
</tr>


    <%-- Ignored field: insertDate --%>


    <%-- Ignored field: updateDate --%>


    <%-- Field: role --%>
<tr>
    <td class="editLabelRW">*${msg['user.role']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="role"/>
            <h:selectOneMenu id="role" value="#{userBean.role}" rendered="#{userBean.roleAvailable}" required="true"
                             styleClass="requiredFieldClass">
                <f:selectItems value="#{userBean.roles}"/>
                <f:converter converterId="autentia.EntityConverter"/>
            </h:selectOneMenu>
        </h:panelGroup>

    </td>
</tr>


    <%-- Field: category --%>
<tr>
    <td class="editLabelRW">*${msg['user.category']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="category"/>
            <h:selectOneMenu id="category" value="#{userBean.category}" required="true" styleClass="requiredFieldClass">
                <f:selectItems value="#{userBean.categorys}"/>
                <f:converter converterId="autentia.EntityConverter"/>
            </h:selectOneMenu>
        </h:panelGroup>

    </td>
</tr>


    <%-- Field: province --%>
<tr>
    <td class="editLabelRW">${msg['user.province']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="province"/>
            <h:selectOneMenu id="province" value="#{userBean.province}">
                <f:selectItems value="#{userBean.provinces}"/>
                <f:converter converterId="autentia.EntityConverter"/>
            </h:selectOneMenu>
        </h:panelGroup>

    </td>
</tr>


    <%-- Ignored field: documentCategory --%>


    <%-- Field: department --%>
<tr>
    <td class="editLabelRW">*${msg['user.department']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="department"/>
            <h:selectOneMenu id="department" value="#{userBean.department}" required="true"
                             styleClass="requiredFieldClass">
                <f:selectItems value="#{userBean.departments}"/>
                <f:converter converterId="autentia.EntityConverter"/>
            </h:selectOneMenu>
        </h:panelGroup>

    </td>
</tr>


    <%-- Field: contractType --%>
<tr>
    <td class="editLabelRW">${msg['user.contractType']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="contractType"/>
            <h:selectOneMenu id="contractType" value="#{userBean.contractType}">
                <f:selectItems value="#{userBean.contractTypes}"/>
                <f:converter converterId="autentia.EntityConverter"/>
            </h:selectOneMenu>
        </h:panelGroup>

    </td>
</tr>


    <%-- user - generated by stajanov (do not edit/delete) --%>
    <%-- Field: documents --%>
<tr>
    <td class="editLabelRW">${msg['user.documents']}:</td>
    <td class="editFieldCell">
        <h:commandLink action="#{userBean.goDocuments}"
                       onclick="if(!askSave('#{userBean.id}','#{msg['question.confirmSaveOnExit']}')) return false;">
            <h:graphicImage title="#{msg.entityActions_goToOtherPage}" value="/img/back_right.gif"
                            styleClass="titleImg"/>
            <h:outputText value=" #{msg.user_documents_show}"/>
        </h:commandLink>
    </td>
</tr>

</table>

</h:form>
</f:view>

</body>
</html>  		

