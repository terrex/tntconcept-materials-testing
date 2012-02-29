<%-- user - generated by stajanov (do not edit/delete) --%>


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
<i:location place="detailUser" msg="${msg}"/>

<f:view>
<%@include file="/inc/header.jsp" %>
<h:form id="user" enctype="multipart/form-data">

<%-- Header --%>
<i:titleBar name="${userBean.name}" msg="${msg}">
    <t:commandLink action="#{userBean.edit}" immediate="true" rendered="#{userBean.editAvailable}">
        <f:param name="id" value="#{userBean.id}"/>
        <h:graphicImage title="#{msg.entityActions_edit}" value="/img/edit.gif" styleClass="cmdImg"/>
    </t:commandLink>
    <h:commandLink action="#{userBean.delete}" rendered="#{userBean.deleteAvailable}"
                   onclick="if( !confirm('#{msg['question.confirmDelete']}') ) return false;">
        <h:graphicImage title="#{msg.entityActions_delete}" value="/img/delete.gif" styleClass="titleImg"/>
    </h:commandLink>
    <h:commandLink action="#{userBean.list}" immediate="true">
        <h:graphicImage title="#{msg.entityActions_back}" value="/img/back.gif" styleClass="titleImg"/>
    </h:commandLink>
</i:titleBar>


<%-- Detail form --%>
<table class="detailTable" cellpadding="0" cellspacing="0">

<div style="margin: 5pt;position: absolute;left: 450px;top: 15px;">
    <i:img type="user" objectId="${userBean.id}" file="${userBean.photo}"/>

</div>

    <%-- Ignored field: id --%>


<c:if test="${userBean.amI}">
    <%-- Field: active --%>
    <tr>
        <td class="detailLabelRW">${msg['user.active']}:</td>

        <td class="detailFieldCell">

            <h:selectBooleanCheckbox id="active" value="#{userBean.active}" disabled="true"/>
        </td>
    </tr>
</c:if>

    <%-- Field: login --%>
<c:if test="${userBean.amI}">
    <tr>
        <td class="detailLabelRW">${msg['user.login']}:</td>

        <td class="detailFieldCell">

            <h:outputText value="#{userBean.login}"/>
        </td>
    </tr>
</c:if>

    <%-- Ignored field: password --%>


    <%-- Field: name --%>
<tr>
    <td class="detailLabelRW">${msg['user.name']}:</td>

    <td class="detailFieldCell">

        <h:outputText value="#{userBean.name}"/>
    </td>
</tr>


<c:if test="${userBean.amI}">
    <%-- Field: startDate --%>
    <tr>
        <td class="detailLabelRW">${msg['user.startDate']}:</td>

        <td class="detailFieldCell">

            <h:outputText value="#{userBean.startDate}" converter="autentia.dateConverter"/>
        </td>
    </tr>
</c:if>

<c:if test="${userBean.amI}">
    <%-- Field: nif --%>
    <tr>
        <td class="detailLabelRW">${msg['user.nif']}:</td>

        <td class="detailFieldCell">

            <h:outputText value="#{userBean.nif}"/>
        </td>
    </tr>
</c:if>

    <%-- Field: genre --%>
<tr>
    <td class="detailLabelRW">${msg['user.genre']}:</td>

    <td class="detailFieldCell">

        <h:outputText value="#{userBean.genreFormatted}"/>
    </td>
</tr>


    <%-- Field: socialSecurityNumber --%>
<c:if test="${userBean.amI}">
    <tr>
        <td class="detailLabelRW">${msg['user.socialSecurityNumber']}:</td>

        <td class="detailFieldCell">

            <h:outputText value="#{userBean.socialSecurityNumber}"/>
        </td>
    </tr>
</c:if>

<c:if test="${userBean.amI}">
    <%-- Field: workingInClient --%>
    <tr>
        <td class="detailLabelRW">${msg['user.workingInClient']}:</td>

        <td class="detailFieldCell">

            <h:selectBooleanCheckbox id="workingInClient" value="#{userBean.workingInClient}" disabled="true"/>
        </td>
    </tr>
</c:if>

<c:if test="${userBean.amI}">
    <%-- Field: birthDate --%>
    <tr>
        <td class="detailLabelRW">${msg['user.birthDate']}:</td>

        <td class="detailFieldCell">

            <h:outputText value="#{userBean.birthDate}" converter="autentia.dateConverter"/>
        </td>
    </tr>
</c:if>

<c:if test="${userBean.amI}">
    <%-- Field: married --%>
    <tr>
        <td class="detailLabelRW">${msg['user.married']}:</td>

        <td class="detailFieldCell">

            <h:selectBooleanCheckbox id="married" value="#{userBean.married}" disabled="true"/>
        </td>
    </tr>
</c:if>
<c:if test="${userBean.amI}">
    <%-- Field: childrenNumber --%>
    <tr>
        <td class="detailLabelRW">${msg['user.childrenNumber']}:</td>

        <td class="detailFieldCell">

            <h:outputText value="#{userBean.childrenNumber}"/>
        </td>
    </tr>
</c:if>
<c:if test="${userBean.amI}">
    <%-- Field: travelAvailability --%>
    <tr>
        <td class="detailLabelRW">${msg['user.travelAvailability']}:</td>

        <td class="detailFieldCell">

            <h:outputText value="#{userBean.travelAvailability}"/>
        </td>
    </tr>
</c:if>

<c:if test="${userBean.amI}">
    <%-- Field: academicQualification --%>
    <tr>
        <td class="detailLabelRW">${msg['user.academicQualification']}:</td>

        <td class="detailFieldCell">

            <h:outputText value="#{userBean.academicQualification}"/>
        </td>
    </tr>
</c:if>

    <%-- Field: email --%>
<tr>
    <td class="detailLabelRW">${msg['user.email']}:</td>

    <td class="detailFieldCell">

        <h:outputText value="#{userBean.email}"/>
    </td>
</tr>


    <%-- Field: phone --%>
<tr>
    <td class="detailLabelRW">${msg['user.phone']}:</td>

    <td class="detailFieldCell">

        <h:outputText value="#{userBean.phone}"/>
    </td>
</tr>


    <%-- Field: mobile --%>
<tr>
    <td class="detailLabelRW">${msg['user.mobile']}:</td>

    <td class="detailFieldCell">

        <h:outputText value="#{userBean.mobile}"/>
    </td>
</tr>


    <%-- Field: street --%>
<c:if test="${userBean.amI}">
    <tr>
        <td class="detailLabelRW">${msg['user.street']}:</td>

        <td class="detailFieldCell">

            <h:outputText value="#{userBean.street}"/>
        </td>
    </tr>
</c:if>

<c:if test="${userBean.amI}">
    <%-- Field: postalCode --%>
    <tr>
        <td class="detailLabelRW">${msg['user.postalCode']}:</td>

        <td class="detailFieldCell">

            <h:outputText value="#{userBean.postalCode}"/>
        </td>
    </tr>
</c:if>

<c:if test="${userBean.amI}">
    <%-- Field: city --%>
    <tr>
        <td class="detailLabelRW">${msg['user.city']}:</td>

        <td class="detailFieldCell">

            <h:outputText value="#{userBean.city}"/>
        </td>
    </tr>
</c:if>

<c:if test="${userBean.amI}">
    <%-- Field: bank --%>
    <tr>
        <td class="detailLabelRW">${msg['user.bank']}:</td>

        <td class="detailFieldCell">

            <h:outputText value="#{userBean.bank}"/>
        </td>
    </tr>
</c:if>

<c:if test="${userBean.amI}">
    <%-- Field: account --%>
    <tr>
        <td class="detailLabelRW">${msg['user.account']}:</td>

        <td class="detailFieldCell">

            <h:outputText value="#{userBean.account}"/>
        </td>
    </tr>
</c:if>

<c:if test="${userBean.amI}">
    <%-- Field: salary --%>
    <tr>
        <td class="detailLabelRW">${msg['user.salary']}:</td>

        <td class="detailFieldCell">

            <h:outputText value="#{userBean.salary}"/>
        </td>
    </tr>
</c:if>

<c:if test="${userBean.amI}">
    <%-- Field: salaryExtras --%>
    <tr>
        <td class="detailLabelRW">${msg['user.salaryExtras']}:</td>

        <td class="detailFieldCell">

            <h:outputText value="#{userBean.salaryExtras}"/>
        </td>
    </tr>
</c:if>


    <%-- Field: drivenLicenseType --%>


<c:if test="${userBean.amI}">
    <tr>
        <td class="detailLabelRW">${msg['user.drivenLicenseType']}:</td>
        <td class="detailFieldCell">
            <h:outputText value="#{userBean.drivenLicenseType}"/>
        </td>
    </tr>
</c:if>


    <%-- Field: vehicleType --%>
<c:if test="${userBean.amI}">
    <tr>
        <td class="detailLabelRW">${msg['user.vehicleType']}:</td>
        <td class="detailFieldCell">
            <h:outputText value="#{userBean.vehicleType}"/>
        </td>
    </tr>
</c:if>


    <%-- Field: licensePlate --%>
<c:if test="${userBean.amI}">
    <tr>
        <td class="detailLabelRW">${msg['user.licensePlate']}:</td>
        <td class="detailFieldCell">
            <h:outputText value="#{userBean.licensePlate}"/>
        </td>
    </tr>
</c:if>

<c:if test="${userBean.amI}">
    <%-- Field: securityCard --%>
    <tr>
        <td class="detailLabelRW">${msg['user.securityCard']}:</td>

        <td class="detailFieldCell">

            <h:outputText value="#{userBean.securityCard}"/>
        </td>
    </tr>
</c:if>


<c:if test="${userBean.amI}">
    <%-- Field: healthInsurance --%>
    <tr>
        <td class="detailLabelRW">${msg['user.healthInsurance']}:</td>

        <td class="detailFieldCell">

            <h:outputText value="#{userBean.healthInsurance}"/>
        </td>
    </tr>
</c:if>

<c:if test="${userBean.amI}">
    <%-- Field: notes --%>
    <tr>
        <td class="detailLabelRW">${msg['user.notes']}:</td>

        <td class="detailFieldCell">

            <h:outputText value="#{userBean.notes}"/>
        </td>
    </tr>
</c:if>


<c:if test="${userBean.amI}">
    <%-- Field: endTestPeriodDate --%>
    <tr>
        <td class="detailLabelRW">${msg['user.endTestPeriodDate']}:</td>

        <td class="detailFieldCell">

            <h:outputText value="#{userBean.endTestPeriodDate}" converter="autentia.dateConverter"/>
        </td>
    </tr>
</c:if>

<c:if test="${userBean.amI}">
    <%-- Field: endContractDate --%>
    <tr>
        <td class="detailLabelRW">${msg['user.endContractDate']}:</td>

        <td class="detailFieldCell">

            <h:outputText value="#{userBean.endContractDate}" converter="autentia.dateConverter"/>
        </td>
    </tr>
</c:if>

<c:if test="${userBean.amI}">
    <%-- Field: contractObservations --%>
    <tr>
        <td class="detailLabelRW">${msg['user.contractObservations']}:</td>

        <td class="detailFieldCell">

            <h:outputText value="#{userBean.contractObservations}"/>
        </td>
    </tr>
</c:if>
<c:if test="${userBean.amI}">
    <%-- Field: role --%>
    <tr>
        <td class="detailLabelRW">${msg['user.role']}:</td>

        <td class="detailFieldCell">

            <h:outputText value="#{userBean.role.name}"/>
        </td>
    </tr>
</c:if>
<c:if test="${userBean.amI}">
    <%-- Field: category --%>
    <tr>
        <td class="detailLabelRW">${msg['user.category']}:</td>

        <td class="detailFieldCell">

            <h:outputText value="#{userBean.category.name}"/>
        </td>
    </tr>
</c:if>
<c:if test="${userBean.amI}">
    <%-- Field: province --%>
    <tr>
        <td class="detailLabelRW">${msg['user.province']}:</td>

        <td class="detailFieldCell">

            <h:outputText value="#{userBean.province.name}"/>
        </td>
    </tr>
</c:if>
<c:if test="${userBean.amI}">
    <%-- Field: documentCategory --%>
    <tr>
        <td class="detailLabelRW">${msg['user.documentCategory']}:</td>

        <td class="detailFieldCell">

            <h:outputText value="#{userBean.documentCategory.name}"/>
        </td>
    </tr>

</c:if>
    <%-- Field: department --%>
<tr>
    <td class="detailLabelRW">${msg['user.department']}:</td>

    <td class="detailFieldCell">
        <h:outputText value="#{userBean.department.name}"/>
    </td>
</tr>

<c:if test="${userBean.amI}">
    <%-- Field: contractType --%>
    <tr>
        <td class="detailLabelRW">${msg['user.contractType']}:</td>

        <td class="detailFieldCell">

            <h:outputText value="#{userBean.contractType.name}"/>
        </td>
    </tr>
</c:if>

<c:if test="${userBean.amI}">
    <%-- Field: duration --%>
    <tr>
        <td class="detailLabelRW">${msg['user.dayDuration']}:</td>
        <td class="detailFieldCell">
            <h:outputText value="#{userBean.dayDuration}">
                <f:converter converterId="autentia.MinuteToHourConverter"/>
            </h:outputText>
        </td>
    </tr>
</c:if>

<c:if test="${userBean.amI}">
    <%-- Field: agreement --%>
    <tr>
        <td class="detailLabelRW">${msg['user.agreement']}:</td>
        <td class="detailFieldCell">
            <h:outputText value="#{userBean.agreement.name}"/>
        </td>
    </tr>
</c:if>

<c:if test="${userBean.amI}">
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
</c:if>
</table>

</h:form>
</f:view>

</body>
</html>
<%-- user - generated by stajanov (do not edit/delete) --%>
