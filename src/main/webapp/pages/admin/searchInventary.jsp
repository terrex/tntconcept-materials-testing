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

<!-- searchInventary.jsp: generated by stajanov code generator -->
<f:loadBundle basename="com.autentia.intra.resources.messages" var="msg"/>
<i:location place="searchInventary" msg="${msg}"/>

<f:view>
<%@include file="/inc/header.jsp" %>
<h:form id="search" enctype="multipart/form-data">

<%-- Header --%>
<i:titleBar msg="${msg}">
    <h:commandLink action="#{inventaryBean.list}">
        <h:graphicImage title="#{msg.entityActions_run}" value="/img/run.png" styleClass="titleImg"/>
    </h:commandLink>
</i:titleBar>

<%-- Search form --%>
<table class="searchTable" cellpadding="0" cellspacing="0">


    <%-- Ignored field: id --%>


    <%-- Field: buyDate --%>
<tr>
    <td class="searchLabel">${msg['inventary.buyDate']}:</td>
    <td class="searchFieldCell">


        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="startBuyDate"/>
            <h:selectBooleanCheckbox id="startBuyDateValid" value="#{inventaryBean.searchStartBuyDateValid}"
                                     onclick="setEnabled('search:startBuyDate',this.checked)"/>
            <t:inputCalendar id="startBuyDate" value="#{inventaryBean.searchStartBuyDate}"
                             renderAsPopup="true" popupDateFormat="d/MM/yyyy" renderPopupButtonAsImage="true"
                             popupTodayString="#{msg['calendar.today']}" popupWeekString="#{msg['calendar.week']}"
                             onchange="setEnabled('search:startBuyDate',true);setChecked('search:startBuyDateValid',true);"
                    />
        </h:panelGroup>
        -
        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="endBuyDate"/>
            <h:selectBooleanCheckbox id="endBuyDateValid" value="#{inventaryBean.searchEndBuyDateValid}"
                                     onclick="setEnabled('search:endBuyDate',this.checked)"/>
            <t:inputCalendar id="endBuyDate" value="#{inventaryBean.searchEndBuyDate}"
                             renderAsPopup="true" popupDateFormat="d/MM/yyyy" renderPopupButtonAsImage="true"
                             popupTodayString="#{msg['calendar.today']}" popupWeekString="#{msg['calendar.week']}"
                             onchange="setEnabled('search:endBuyDate',true);setChecked('search:endBuyDateValid',true);"
                    />
        </h:panelGroup>
        <script>
            setEnabled('search:startBuyDate', ${inventaryBean.searchStartBuyDateValid})
            setEnabled('search:endBuyDate', ${inventaryBean.searchEndBuyDateValid})
        </script>


    </td>
</tr>

    <%-- Field: renting --%>
<tr>
    <td class="searchLabel">${msg['inventary.renting']}:</td>
    <td class="searchFieldCell">


        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="renting"/>
            <h:selectBooleanCheckbox id="rentingValid" value="#{inventaryBean.searchRentingValid}"
                                     onclick="setEnabledByName('search:renting',this.checked)"/>

            <h:outputLabel for="renting" value="#{msg['msg.yesno']}"></h:outputLabel>
            <h:selectBooleanCheckbox id="renting" value="#{inventaryBean.searchRenting}"/>


        </h:panelGroup>
        <script>
            setEnabledByName('search:renting', ${inventaryBean.searchRentingValid})
        </script>


    </td>
</tr>

    <%-- Field: cost --%>
<tr>
    <td class="searchLabel">${msg['inventary.cost']}:</td>
    <td class="searchFieldCell">


        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="startCost"/>
            <h:selectBooleanCheckbox id="startCostValid" value="#{inventaryBean.searchStartCostValid}"
                                     onclick="setEnabled('search:startCost',this.checked)"/>
            <h:inputText id="startCost" value="#{inventaryBean.searchStartCost}" size="31"/>
        </h:panelGroup>
        -
        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="endCost"/>
            <h:selectBooleanCheckbox id="endCostValid" value="#{inventaryBean.searchEndCostValid}"
                                     onclick="setEnabled('search:endCost',this.checked)"/>
            <h:inputText id="endCost" value="#{inventaryBean.searchEndCost}" size="31"/>
        </h:panelGroup>
        <script>
            setEnabled('search:startCost', ${inventaryBean.searchStartCostValid})
            setEnabled('search:endCost', ${inventaryBean.searchEndCostValid})
        </script>


    </td>
</tr>

    <%-- Field: amortizable --%>
<tr>
    <td class="searchLabel">${msg['inventary.amortizable']}:</td>
    <td class="searchFieldCell">


        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="amortizable"/>
            <h:selectBooleanCheckbox id="amortizableValid" value="#{inventaryBean.searchAmortizableValid}"
                                     onclick="setEnabledByName('search:amortizable',this.checked)"/>

            <h:outputLabel for="amortizable" value="#{msg['msg.yesno']}"></h:outputLabel>
            <h:selectBooleanCheckbox id="amortizable" value="#{inventaryBean.searchAmortizable}"/>


        </h:panelGroup>
        <script>
            setEnabledByName('search:amortizable', ${inventaryBean.searchAmortizableValid})
        </script>


    </td>
</tr>

    <%-- Field: serialNumber --%>
<tr>
    <td class="searchLabel">${msg['inventary.serialNumber']}:</td>
    <td class="searchFieldCell">


        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="serialNumber"/>
            <h:selectBooleanCheckbox id="serialNumberValid" value="#{inventaryBean.searchSerialNumberValid}"
                                     onclick="setEnabled('search:serialNumber',this.checked)"/>
            <h:inputText id="serialNumber" value="#{inventaryBean.searchSerialNumber}" size="70"/>
        </h:panelGroup>
        <script>
            setEnabled('search:serialNumber', ${inventaryBean.searchSerialNumberValid})
        </script>


    </td>
</tr>

    <%-- Field: provider --%>
<tr>
    <td class="searchLabel">${msg['inventary.provider']}:</td>
    <td class="searchFieldCell">


        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="provider"/>
            <h:selectBooleanCheckbox id="providerValid" value="#{inventaryBean.searchProviderValid}"
                                     onclick="setEnabled('search:provider',this.checked)"/>
            <h:inputText id="provider" value="#{inventaryBean.searchProvider}" size="70"/>
        </h:panelGroup>
        <script>
            setEnabled('search:provider', ${inventaryBean.searchProviderValid})
        </script>


    </td>
</tr>

    <%-- Field: trademark --%>
<tr>
    <td class="searchLabel">${msg['inventary.trademark']}:</td>
    <td class="searchFieldCell">


        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="trademark"/>
            <h:selectBooleanCheckbox id="trademarkValid" value="#{inventaryBean.searchTrademarkValid}"
                                     onclick="setEnabled('search:trademark',this.checked)"/>
            <h:inputText id="trademark" value="#{inventaryBean.searchTrademark}" size="70"/>
        </h:panelGroup>
        <script>
            setEnabled('search:trademark', ${inventaryBean.searchTrademarkValid})
        </script>


    </td>
</tr>

    <%-- Field: model --%>
<tr>
    <td class="searchLabel">${msg['inventary.model']}:</td>
    <td class="searchFieldCell">


        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="model"/>
            <h:selectBooleanCheckbox id="modelValid" value="#{inventaryBean.searchModelValid}"
                                     onclick="setEnabled('search:model',this.checked)"/>
            <h:inputText id="model" value="#{inventaryBean.searchModel}" size="70"/>
        </h:panelGroup>
        <script>
            setEnabled('search:model', ${inventaryBean.searchModelValid})
        </script>


    </td>
</tr>

    <%-- Field: speed --%>
<tr>
    <td class="searchLabel">${msg['inventary.speed']}:</td>
    <td class="searchFieldCell">


        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="speed"/>
            <h:selectBooleanCheckbox id="speedValid" value="#{inventaryBean.searchSpeedValid}"
                                     onclick="setEnabled('search:speed',this.checked)"/>
            <h:inputText id="speed" value="#{inventaryBean.searchSpeed}" size="70"/>
        </h:panelGroup>
        <script>
            setEnabled('search:speed', ${inventaryBean.searchSpeedValid})
        </script>


    </td>
</tr>

    <%-- Field: storage --%>
<tr>
    <td class="searchLabel">${msg['inventary.storage']}:</td>
    <td class="searchFieldCell">


        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="storage"/>
            <h:selectBooleanCheckbox id="storageValid" value="#{inventaryBean.searchStorageValid}"
                                     onclick="setEnabled('search:storage',this.checked)"/>
            <h:inputText id="storage" value="#{inventaryBean.searchStorage}" size="70"/>
        </h:panelGroup>
        <script>
            setEnabled('search:storage', ${inventaryBean.searchStorageValid})
        </script>


    </td>
</tr>

    <%-- Field: ram --%>
<tr>
    <td class="searchLabel">${msg['inventary.ram']}:</td>
    <td class="searchFieldCell">


        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="ram"/>
            <h:selectBooleanCheckbox id="ramValid" value="#{inventaryBean.searchRamValid}"
                                     onclick="setEnabled('search:ram',this.checked)"/>
            <h:inputText id="ram" value="#{inventaryBean.searchRam}" size="70"/>
        </h:panelGroup>
        <script>
            setEnabled('search:ram', ${inventaryBean.searchRamValid})
        </script>


    </td>
</tr>

    <%-- Field: location --%>
<tr>
    <td class="searchLabel">${msg['inventary.location']}:</td>
    <td class="searchFieldCell">


        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="location"/>
            <h:selectBooleanCheckbox id="locationValid" value="#{inventaryBean.searchLocationValid}"
                                     onclick="setEnabled('search:location',this.checked)"/>
            <h:inputText id="location" value="#{inventaryBean.searchLocation}" size="70"/>
        </h:panelGroup>
        <script>
            setEnabled('search:location', ${inventaryBean.searchLocationValid})
        </script>


    </td>
</tr>

    <%-- Field: description --%>
<tr>
    <td class="searchLabel">${msg['inventary.description']}:</td>
    <td class="searchFieldCell">


        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="description"/>
            <h:selectBooleanCheckbox id="descriptionValid" value="#{inventaryBean.searchDescriptionValid}"
                                     onclick="setEnabled('search:description',this.checked)"/>
            <h:inputText id="description" value="#{inventaryBean.searchDescription}" size="70"/>
        </h:panelGroup>
        <script>
            setEnabled('search:description', ${inventaryBean.searchDescriptionValid})
        </script>


    </td>
</tr>

    <%-- Field: type --%>
<tr>
    <td class="searchLabel">${msg['inventary.type']}:</td>
    <td class="searchFieldCell">


        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="type"/>
            <h:selectBooleanCheckbox id="typeValid" value="#{inventaryBean.searchTypeValid}"
                                     onclick="setEnabled('search:type',this.checked)"/>
            <h:selectOneMenu id="type" value="#{inventaryBean.searchType}" onclick="setEnabled('search:type',true)">
                <f:converter converterId="autentia.EnumConverter"/>
                <f:selectItems value="#{inventaryBean.types}"/>
            </h:selectOneMenu>
        </h:panelGroup>
        <script>
            setEnabled('search:type', ${inventaryBean.searchTypeValid})
        </script>


    </td>
</tr>

    <%-- Ignored field: ownerId --%>


    <%-- Ignored field: departmentId --%>


    <%-- Ignored field: insertDate --%>


    <%-- Ignored field: updateDate --%>


    <%-- Field: assignedTo --%>
<tr>
    <td class="searchLabel">${msg['inventary.assignedTo']}:</td>
    <td class="searchFieldCell">


        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="assignedTo"/>
            <h:selectBooleanCheckbox id="assignedToValid" value="#{inventaryBean.searchAssignedToValid}"
                                     onclick="setEnabled('search:assignedTo',this.checked)"/>
            <h:selectOneMenu id="assignedTo" value="#{inventaryBean.searchAssignedTo}"
                             onclick="setEnabled('search:assignedTo',true)">
                <f:selectItems value="#{inventaryBean.assignedTos}"/>
                <f:converter converterId="autentia.EntityConverter"/>
            </h:selectOneMenu>
        </h:panelGroup>
        <script>
            setEnabled('search:assignedTo', ${inventaryBean.searchAssignedToValid})
        </script>


    </td>
</tr>

</table>

</h:form>
</f:view>

</body>
</html>  		
