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

<!-- searchWorkingAgreement.jsp: generated by stajanov code generator -->
<f:loadBundle basename="com.autentia.intra.resources.messages" var="msg"/>
<i:location place="searchWorkingAgreement" msg="${msg}"/>

<f:view>
    <%@include file="/inc/header.jsp" %>
    <h:form id="search" enctype="multipart/form-data">

        <%-- Header --%>
        <i:titleBar msg="${msg}">
            <h:commandLink action="#{workingAgreementBean.list}">
                <h:graphicImage title="#{msg.entityActions_run}" value="/img/run.png" styleClass="titleImg"/>
            </h:commandLink>
        </i:titleBar>

        <%-- Search form --%>
        <table class="searchTable" cellpadding="0" cellspacing="0">


                <%-- Ignored field: id --%>


                <%-- Field: name --%>
            <tr>
                <td class="searchLabel">${msg['workingAgreement.name']}:</td>
                <td class="searchFieldCell">


                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="name"/>
                        <h:selectBooleanCheckbox id="nameValid" value="#{workingAgreementBean.searchNameValid}"
                                                 onclick="setEnabled('search:name',this.checked)"/>
                        <h:inputText id="name" value="#{workingAgreementBean.searchName}" size="70"/>
                    </h:panelGroup>
                    <script>
                        setEnabled('search:name', ${workingAgreementBean.searchNameValid})
                    </script>


                </td>
            </tr>

                <%-- Ignored field: description --%>


                <%-- Field: holidays --%>
            <tr>
                <td class="searchLabel">${msg['workingAgreement.holidays']}:</td>
                <td class="searchFieldCell">


                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="startHolidays"/>
                        <h:selectBooleanCheckbox id="startHolidaysValid"
                                                 value="#{workingAgreementBean.searchStartHolidaysValid}"
                                                 onclick="setEnabled('search:startHolidays',this.checked)"/>
                        <h:inputText id="startHolidays" value="#{workingAgreementBean.searchStartHolidays}" size="31"/>
                    </h:panelGroup>
                    -
                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="endHolidays"/>
                        <h:selectBooleanCheckbox id="endHolidaysValid"
                                                 value="#{workingAgreementBean.searchEndHolidaysValid}"
                                                 onclick="setEnabled('search:endHolidays',this.checked)"/>
                        <h:inputText id="endHolidays" value="#{workingAgreementBean.searchEndHolidays}" size="31"/>
                    </h:panelGroup>
                    <script>
                        setEnabled('search:startHolidays', ${workingAgreementBean.searchStartHolidaysValid})
                        setEnabled('search:endHolidays', ${workingAgreementBean.searchEndHolidaysValid})
                    </script>


                </td>
            </tr>

                <%-- Ignored field: ownerId --%>


                <%-- Ignored field: departmentId --%>


                <%-- Ignored field: insertDate --%>


                <%-- Ignored field: updateDate --%>


        </table>

    </h:form>
</f:view>

</body>
</html>  		
