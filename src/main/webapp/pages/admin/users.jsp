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

<!-- users.jsp: generated by stajanov code generator -->
<f:loadBundle basename="com.autentia.intra.resources.messages" var="msg"/>
<i:location place="users" msg="${msg}"/>

<f:view>
<%@include file="/inc/header.jsp" %>
<h:form id="users">

<%-- Header --%>
<i:titleBar msg="${msg}">
    <h:commandLink action="#{userBean.search}">
        <h:graphicImage rendered="#{ userBean.search.searchActive}" title="#{msg.entityActions_filtered}"
                        value="/img/search_applied.gif" styleClass="titleImg"/>
        <h:graphicImage rendered="#{!userBean.search.searchActive}" title="#{msg.entityActions_search}"
                        value="/img/search.gif" styleClass="titleImg"/>
    </h:commandLink>
    <h:commandLink action="#{userBean.reset}">
        <h:graphicImage rendered="#{userBean.search.searchActive}" title="#{msg.entityActions_reset}"
                        value="/img/eraser.png" styleClass="titleImg"/>
    </h:commandLink>
    <h:commandLink action="#{userBean.create}" rendered="#{userBean.createAvailable}">
        <h:graphicImage title="#{msg.entityActions_create}" value="/img/new.gif" styleClass="titleImg"/>
    </h:commandLink>
</i:titleBar>


<%-- ABC quick pager --%>
<div class="abcPager">
    <h:outputText value="#{msg['abcPager.title']}" styleClass="abcPagerTitle"/>
    <a:abcPager styleClass="abcPagerLetter" selectedStyleClass="abcPagerSelectedLetter"
                value="#{userBean.letter}" action="#{userBean.letterClicked}"
                allowUnselect="true" unselectImage="/img/no_funnel.png"/>
</div>


<%-- List of users --%>
<t:dataTable id="list" var="user" value="#{userBean.all}" preserveDataModel="false"
             cellpadding="0" cellspacing="0" styleClass="listTable"
             headerClass="listHeaderCell" footerClass="listFooter"
             rows="#{settingBean.mySettings.listSize}" rowClasses="listRowO,listRowE"
             columnClasses="listCmdCell,listUserActive,listUserName,listUserEmail,listUserPhone,listUserMobile,listUserRole,listUserDepartment,listUserAgreement"
             sortColumn="#{userBean.sortColumn}" sortAscending="#{userBean.sortAscending}"
             rowOnMouseOver="this.savedClassName=this.className;this.className='listRowSel';"
             rowOnMouseOut="this.className=this.savedClassName;">

<%-- Commands --%>
<h:column>
    <f:facet name="header">
        <f:verbatim>-</f:verbatim>
    </f:facet>
    <t:commandLink action="#{userBean.detail}" immediate="true">
        <f:param name="id" value="#{user.id}"/>
        <h:graphicImage title="#{msg.entityActions_detail}" value="/img/detail.gif" styleClass="cmdImg"/>
    </t:commandLink>
</h:column>


<%-- Ignored field: id --%>


<%-- Field: active --%>
<h:column>
    <f:facet name="header">
        <t:commandSortHeader styleClass="listHeader" columnName="active">
            <f:facet name="ascending">
                <t:graphicImage value="/img/ascending-arrow.gif" border="0"/>
            </f:facet>
            <f:facet name="descending">
                <t:graphicImage value="/img/descending-arrow.gif" border="0"/>
            </f:facet>
            <f:verbatim>${msg['user.active']}</f:verbatim>
        </t:commandSortHeader>
    </f:facet>

    <t:commandLink action="#{userBean.detail}" immediate="true">
        <f:param name="id" value="#{user.id}"/>
        <%-- Boolean field --%>
        <t:graphicImage value="/img/true.png" rendered="#{user.active}"/>
        <t:graphicImage value="/img/false.png" rendered="#{!user.active}"/>

    </t:commandLink>

</h:column>


<%-- Ignored field: login --%>


<%-- Ignored field: password --%>


<%-- Field: name --%>
<h:column>
    <f:facet name="header">
        <t:commandSortHeader styleClass="listHeader" columnName="name">
            <f:facet name="ascending">
                <t:graphicImage value="/img/ascending-arrow.gif" border="0"/>
            </f:facet>
            <f:facet name="descending">
                <t:graphicImage value="/img/descending-arrow.gif" border="0"/>
            </f:facet>
            <f:verbatim>${msg['user.name']}</f:verbatim>
        </t:commandSortHeader>
    </f:facet>

    <t:commandLink action="#{userBean.detail}" immediate="true">
        <f:param name="id" value="#{user.id}"/>
        <%-- String field --%>
        <h:outputText value="#{user.name}"/>
    </t:commandLink>

</h:column>


<%-- Ignored field: startDate --%>


<%-- Ignored field: nif --%>


<%-- Ignored field: genre --%>


<%-- Ignored field: socialSecurityNumber --%>


<%-- Ignored field: workingInClient --%>


<%-- Ignored field: birthDate --%>


<%-- Ignored field: married --%>


<%-- Ignored field: childrenNumber --%>


<%-- Ignored field: travelAvailability --%>


<%-- Ignored field: academicQualification --%>


<%-- Field: email --%>
<h:column>
    <f:facet name="header">
        <t:commandSortHeader styleClass="listHeader" columnName="email">
            <f:facet name="ascending">
                <t:graphicImage value="/img/ascending-arrow.gif" border="0"/>
            </f:facet>
            <f:facet name="descending">
                <t:graphicImage value="/img/descending-arrow.gif" border="0"/>
            </f:facet>
            <f:verbatim>${msg['user.email']}</f:verbatim>
        </t:commandSortHeader>
    </f:facet>

    <t:commandLink action="#{userBean.detail}" immediate="true">
        <f:param name="id" value="#{user.id}"/>
        <%-- String field --%>
        <h:outputText value="#{user.email}"/>
    </t:commandLink>

</h:column>


<%-- Field: phone --%>
<h:column>
    <f:facet name="header">
        <t:commandSortHeader styleClass="listHeader" columnName="phone">
            <f:facet name="ascending">
                <t:graphicImage value="/img/ascending-arrow.gif" border="0"/>
            </f:facet>
            <f:facet name="descending">
                <t:graphicImage value="/img/descending-arrow.gif" border="0"/>
            </f:facet>
            <f:verbatim>${msg['user.phone']}</f:verbatim>
        </t:commandSortHeader>
    </f:facet>

    <t:commandLink action="#{userBean.detail}" immediate="true">
        <f:param name="id" value="#{user.id}"/>
        <%-- String field --%>
        <h:outputText value="#{user.phone}"/>
    </t:commandLink>

</h:column>


<%-- Field: mobile --%>
<h:column>
    <f:facet name="header">
        <t:commandSortHeader styleClass="listHeader" columnName="mobile">
            <f:facet name="ascending">
                <t:graphicImage value="/img/ascending-arrow.gif" border="0"/>
            </f:facet>
            <f:facet name="descending">
                <t:graphicImage value="/img/descending-arrow.gif" border="0"/>
            </f:facet>
            <f:verbatim>${msg['user.mobile']}</f:verbatim>
        </t:commandSortHeader>
    </f:facet>

    <t:commandLink action="#{userBean.detail}" immediate="true">
        <f:param name="id" value="#{user.id}"/>
        <%-- String field --%>
        <h:outputText value="#{user.mobile}"/>
    </t:commandLink>

</h:column>


<%-- Ignored field: street --%>


<%-- Ignored field: postalCode --%>


<%-- Ignored field: city --%>


<%-- Ignored field: bank --%>


<%-- Ignored field: account --%>


<%-- Ignored field: salary --%>


<%-- Ignored field: salaryExtras --%>


<%-- Ignored field: drivenLicenseType --%>


<%-- Ignored field: vehicleType --%>


<%-- Ignored field: licensePlate --%>


<%-- Ignored field: securityCard --%>


<%-- Ignored field: healthInsurance --%>


<%-- Ignored field: notes --%>


<%-- Ignored field: photo --%>


<%-- Ignored field: endTestPeriodDate --%>


<%-- Ignored field: endContractDate --%>


<%-- Ignored field: dayDuration --%>


<%-- Ignored field: contractObservations --%>


<%-- Ignored field: insertDate --%>


<%-- Ignored field: updateDate --%>


<%-- Field: role --%>
<h:column>
    <f:facet name="header">
        <t:commandSortHeader styleClass="listHeader" columnName="role.name">
            <f:facet name="ascending">
                <t:graphicImage value="/img/ascending-arrow.gif" border="0"/>
            </f:facet>
            <f:facet name="descending">
                <t:graphicImage value="/img/descending-arrow.gif" border="0"/>
            </f:facet>
            <f:verbatim>${msg['user.role']}</f:verbatim>
        </t:commandSortHeader>
    </f:facet>

    <t:commandLink action="#{userBean.detail}" immediate="true">
        <f:param name="id" value="#{user.id}"/>
        <%-- Referenced field --%>
        <h:outputText value="#{user.role.name}"/>

    </t:commandLink>

</h:column>


<%-- Ignored field: category --%>


<%-- Ignored field: province --%>


<%-- Ignored field: documentCategory --%>


<%-- Field: department --%>
<h:column>
    <f:facet name="header">
        <t:commandSortHeader styleClass="listHeader" columnName="department.name">
            <f:facet name="ascending">
                <t:graphicImage value="/img/ascending-arrow.gif" border="0"/>
            </f:facet>
            <f:facet name="descending">
                <t:graphicImage value="/img/descending-arrow.gif" border="0"/>
            </f:facet>
            <f:verbatim>${msg['user.department']}</f:verbatim>
        </t:commandSortHeader>
    </f:facet>

    <t:commandLink action="#{userBean.detail}" immediate="true">
        <f:param name="id" value="#{user.id}"/>
        <%-- Referenced field --%>
        <h:outputText value="#{user.department.name}"/>

    </t:commandLink>

</h:column>


<%-- Ignored field: contractType --%>


<%-- Field: agreement --%>
<h:column>
    <f:facet name="header">
        <t:commandSortHeader styleClass="listHeader" columnName="agreement.name">
            <f:facet name="ascending">
                <t:graphicImage value="/img/ascending-arrow.gif" border="0"/>
            </f:facet>
            <f:facet name="descending">
                <t:graphicImage value="/img/descending-arrow.gif" border="0"/>
            </f:facet>
            <f:verbatim>${msg['user.agreement']}</f:verbatim>
        </t:commandSortHeader>
    </f:facet>

    <t:commandLink action="#{userBean.detail}" immediate="true">
        <f:param name="id" value="#{user.id}"/>
        <%-- Referenced field --%>
        <h:outputText value="#{user.agreement.name}"/>

    </t:commandLink>

</h:column>

</t:dataTable>

<%-- Paginator control --%>
<%@include file="/inc/paginator.jsp" %>

</h:form>
</f:view>

</body>
</html>  		

