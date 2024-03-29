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

<!-- editOrganization.jsp: generated by stajanov code generator -->
<f:loadBundle basename="com.autentia.intra.resources.messages" var="msg"/>
<i:location place="editOrganization" msg="${msg}"/>

<f:view>
<%@include file="/inc/header.jsp" %>
<h:form id="organization" enctype="multipart/form-data">

<%-- Header --%>
<i:titleBar name="${organizationBean.name}" msg="${msg}">
    <h:commandLink action="#{organizationBean.save}"
                   onclick="if(!askSave('#{organizationBean.id}','#{msg['question.confirmSave']}')) return false;">
        <h:graphicImage value="/img/save.gif" styleClass="titleImg"/>
    </h:commandLink>
    <h:commandLink action="#{organizationBean.delete}"
                   onclick="if( !confirm('#{msg['question.confirmDelete']}') ) return false;">
        <h:graphicImage value="/img/delete.gif" styleClass="titleImg"/>
    </h:commandLink>
    <h:commandLink action="#{organizationBean.list}" immediate="true">
        <h:graphicImage value="/img/back.gif" styleClass="titleImg"/>
    </h:commandLink>
</i:titleBar>

<%-- Edition form --%>
<table class="editTable" cellpadding="0" cellspacing="0">

    <%-- organization - generated by stajanov (do not edit/delete) --%>


    <%-- Ignored field: id --%>


    <%-- Field: name --%>
<tr>
    <td class="editLabelRW">*${msg['organization.name']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="name"/>
            <h:inputText id="name" value="#{organizationBean.name}" size="70" maxlength="128" required="true"
                         styleClass="requiredFieldClass"/>
        </h:panelGroup>

    </td>
</tr>

    <%-- Field: acronimo --%>
<tr>
    <td class="editLabelRW">${msg['organization.acronimo']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="acronimo"/>
            <h:inputText id="acronimo" value="#{organizationBean.acronimo}" size="70" maxlength="128"/>
        </h:panelGroup>

    </td>
</tr>


    <%-- Field: cif --%>
<tr>
    <td class="editLabelRW">${msg['organization.cif']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="cif"/>
            <h:inputText id="cif" value="#{organizationBean.cif}" maxlength="50"/>
        </h:panelGroup>

    </td>
</tr>


    <%-- Field: numeroProveedor --%>
<tr>
    <td class="editLabelRW">${msg['organization.numeroProveedor']}:</td>
    <td class="editFieldCell">
        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="numeroProveedor"/>
            <h:inputText id="numeroProveedor" value="#{organizationBean.organization.numeroProveedor}" maxlength="16"/>
        </h:panelGroup>
    </td>
</tr>


    <%-- Field: street --%>
<tr>
    <td class="editLabelRW">${msg['organization.street']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="street"/>
            <h:inputText id="street" value="#{organizationBean.street}" size="70" maxlength="256"/>
        </h:panelGroup>

    </td>
</tr>


    <%-- Field: number --%>
<tr>
    <td class="editLabelRW">${msg['organization.number']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="number"/>
            <h:inputText id="number" value="#{organizationBean.number}" maxlength="16"/>
        </h:panelGroup>

    </td>
</tr>


    <%-- Field: locator --%>
<tr>
    <td class="editLabelRW">${msg['organization.locator']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="locator"/>
            <h:inputText id="locator" value="#{organizationBean.locator}" size="70" maxlength="256"/>
        </h:panelGroup>

    </td>
</tr>


    <%-- Field: postalCode --%>
<tr>
    <td class="editLabelRW">${msg['organization.postalCode']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="postalCode"/>
            <h:inputText id="postalCode" value="#{organizationBean.postalCode}" maxlength="32"/>
        </h:panelGroup>

    </td>
</tr>


    <%-- Field: city --%>
<tr>
    <td class="editLabelRW">${msg['organization.city']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="city"/>
            <h:inputText id="city" value="#{organizationBean.city}" size="70" maxlength="256"/>
        </h:panelGroup>

    </td>
</tr>


    <%-- Field: state --%>
<tr>
    <td class="editLabelRW">${msg['organization.state']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="state"/>
            <h:inputText id="state" value="#{organizationBean.state}" size="70" maxlength="256"/>
        </h:panelGroup>

    </td>
</tr>


    <%-- Field: country --%>
<tr>
    <td class="editLabelRW">${msg['organization.country']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="country"/>
            <h:inputText id="country" value="#{organizationBean.country}" size="70" maxlength="256"/>
        </h:panelGroup>

    </td>
</tr>


    <%-- Field: phone --%>
<tr>
    <td class="editLabelRW">${msg['organization.phone']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="phone"/>
            <h:inputText id="phone" value="#{organizationBean.phone}" maxlength="15"/>
        </h:panelGroup>

    </td>
</tr>


    <%-- Field: fax --%>
<tr>
    <td class="editLabelRW">${msg['organization.fax']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="fax"/>
            <h:inputText id="fax" value="#{organizationBean.fax}" size="70" maxlength="16"/>
        </h:panelGroup>

    </td>
</tr>


    <%-- Field: email --%>
<tr>
    <td class="editLabelRW">${msg['organization.email']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="email"/>
            <h:inputText id="email" value="#{organizationBean.email}" size="70" maxlength="256"/>
        </h:panelGroup>

    </td>
</tr>


    <%-- Field: website --%>
<tr>
    <td class="editLabelRW">${msg['organization.website']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="website"/>
            <h:inputText id="website" value="#{organizationBean.website}" size="70" maxlength="256"/>
        </h:panelGroup>

    </td>
</tr>


    <%-- Field: ftpsite --%>
<tr>
    <td class="editLabelRW">${msg['organization.ftpsite']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="ftpsite"/>
            <h:inputText id="ftpsite" value="#{organizationBean.ftpsite}" size="70" maxlength="256"/>
        </h:panelGroup>

    </td>
</tr>


    <%-- Field: notes --%>
<tr>
    <td class="editLabelRW">${msg['organization.notes']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="notes"/>
            <h:inputText id="notes" value="#{organizationBean.notes}" size="70" maxlength="1024"/>
        </h:panelGroup>

    </td>
</tr>


    <%-- Ignored field: ownerId --%>


    <%-- Ignored field: departmentId --%>


    <%-- Ignored field: insertDate --%>


    <%-- Ignored field: updateDate --%>


    <%-- Field: type --%>
<tr>
    <td class="editLabelRW">*${msg['organization.type']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="type"/>
            <h:selectOneMenu id="type" value="#{organizationBean.type}" required="true" styleClass="requiredFieldClass">
                <f:selectItems value="#{organizationBean.types}"/>
                <f:converter converterId="autentia.EntityConverter"/>
            </h:selectOneMenu>
        </h:panelGroup>

    </td>
</tr>


    <%-- Field: category --%>
<tr>
    <td class="editLabelRW">*${msg['organization.category']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="category"/>
            <h:selectOneMenu id="category" value="#{organizationBean.category}" required="true"
                             styleClass="requiredFieldClass">
                <f:selectItems value="#{organizationBean.categorys}"/>
                <f:converter converterId="autentia.EntityConverter"/>
            </h:selectOneMenu>
        </h:panelGroup>

    </td>
</tr>


    <%-- Field: province --%>
<tr>
    <td class="editLabelRW">*${msg['organization.province']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="province"/>
            <h:selectOneMenu id="province" value="#{organizationBean.province}" required="true"
                             styleClass="requiredFieldClass">
                <f:selectItems value="#{organizationBean.provinces}"/>
                <f:converter converterId="autentia.EntityConverter"/>
            </h:selectOneMenu>
        </h:panelGroup>

    </td>
</tr>


    <%-- Field: columnaRefCli --%>
<tr>
    <td class="editLabelRW">${msg['organization.columnaRefCli']}:</td>

    <td class="editFieldCell">

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="columnaRefCli"/>
            <h:inputText id="columnaRefCli" value="#{organizationBean.columnaRefCli}" size="64"/>
        </h:panelGroup>

    </td>
</tr>

    <%-- Field: description --%>
<tr>
    <td class="editLabelRW">*${msg['organization.procedmientoAdministrativo']}:</td>
    <td class="editFieldCell">
        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="procedimientoAdministrativo"/>
            <h:inputTextarea id="procedimientoAdministrativo"
                             value="#{organizationBean.organization.procedimientoAdministrativo}" rows="5" cols="68"/>
        </h:panelGroup>
    </td>
</tr>

<tr>
    <td class="editLabelRW">${msg['organization.direccionEnvioInformes']}:</td>
    <td class="editFieldCell">
        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="direccionEnvioInformes"/>
            <h:inputTextarea id="direccionEnvioInformes" value="#{organizationBean.organization.direccionEnvioInformes}"
                             rows="5"
                             cols="68"/>
        </h:panelGroup>
    </td>
</tr>

<tr>
    <td class="editLabelRW">${msg['organization.direccionEnvioFactura']}:</td>
    <td class="editFieldCell">
        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="direccionEnvioFactura"/>
            <h:inputTextarea id="direccionEnvioFactura" value="#{organizationBean.organization.direccionEnvioFactura}"
                             rows="5" cols="68"/>
        </h:panelGroup>
    </td>
</tr>

<tr>
    <td class="editLabelRW">${msg['organization.fechaAlta']}:</td>
    <td class="editFieldCell">
        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="fechaAlta"/>
            <t:inputCalendar id="fechaAlta" value="#{organizationBean.organization.fechaAlta}"
                             renderAsPopup="true" popupDateFormat="d/MM/yyyy" renderPopupButtonAsImage="true"
                             popupTodayString="#{msg['calendar.today']}" popupWeekString="#{msg['calendar.week']}"
                    />
        </h:panelGroup>
    </td>
</tr>

<tr>
    <td class="editLabelRW">${msg['organization.fechaCaducidadCertificado']}:</td>
    <td class="editFieldCell">
        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="fechaCaducidadCertificado"/>
            <t:inputCalendar id="fechaCaducidadCertificado"
                             value="#{organizationBean.organization.fechaCaducidadCertificado}"
                             renderAsPopup="true" popupDateFormat="d/MM/yyyy" renderPopupButtonAsImage="true"
                             popupTodayString="#{msg['calendar.today']}" popupWeekString="#{msg['calendar.week']}"
                    />
        </h:panelGroup>
    </td>
</tr>

<tr>
    <td class="editLabelRW">${msg['organization.certificado']}:</td>
    <td class="editFieldCell">
        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="certificado"/>
            <h:selectOneMenu id="certificado" value="#{organizationBean.organization.certificado}">
                <f:converter converterId="autentia.EnumConverter"/>
                <f:selectItems value="#{organizationBean.organizationCertificados}"/>
            </h:selectOneMenu>
        </h:panelGroup>
    </td>
</tr>

<tr>
    <td class="editLabelRW">${msg['organization.estado']}:</td>
    <td class="editFieldCell">
        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="estado"/>
            <h:selectOneMenu id="estado" value="#{organizationBean.organization.estado}">
                <f:converter converterId="autentia.EnumConverter"/>
                <f:selectItems value="#{organizationBean.organizationEstados}"/>
            </h:selectOneMenu>
        </h:panelGroup>
    </td>
</tr>
    <%-- organization - generated by stajanov (do not edit/delete) --%>

</table>

<h:commandLink action="#{historicoBean.ver_historico}" immediate="true" target="_blank"
               rendered="#{organizationBean.id != null}">
    <f:param name="idObjeto" value="#{organizationBean.id}"/>
    <f:param name="klazz" value="#{organizationBean.organization.class.name}"/>
    <h:graphicImage value="/img/historico.png" styleClass="titleImg"/>
</h:commandLink>

</h:form>
</f:view>

</body>
</html>

<%-- organization - generated by stajanov (do not edit/delete) --%>






