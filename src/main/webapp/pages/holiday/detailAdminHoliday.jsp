<%-- adminHoliday - generated by stajanov (do not edit/delete) --%>


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

<!-- editAdminHoliday.jsp: generated by stajanov code generator -->
<f:loadBundle basename="com.autentia.intra.resources.messages" var="msg"/>
<i:location place="detailAdminHoliday" msg="${msg}"/>

<f:view>
    <%@include file="/inc/header.jsp" %>
    <h:form id="adminHoliday" enctype="multipart/form-data">

        <%-- Header --%>
        <i:titleBar name="${adminHolidayBean.beginDate}" msg="${msg}">
            <t:commandLink action="#{adminHolidayBean.edit}" immediate="true"
                           rendered="#{adminHolidayBean.editAvailable}">
                <f:param name="id" value="#{adminHolidayBean.id}"/>
                <h:graphicImage title="#{msg.entityActions_edit}" value="/img/edit.gif" styleClass="cmdImg"/>
            </t:commandLink>
            <h:commandLink action="#{adminHolidayBean.delete}" rendered="#{adminHolidayBean.deleteAvailable}"
                           onclick="if( !confirm('#{msg['question.confirmDelete']}') ) return false;">
                <h:graphicImage title="#{msg.entityActions_delete}" value="/img/delete.gif" styleClass="titleImg"/>
            </h:commandLink>
            <h:commandLink action="#{adminHolidayBean.list}" immediate="true">
                <h:graphicImage title="#{msg.entityActions_back}" value="/img/back.gif" styleClass="titleImg"/>
            </h:commandLink>
        </i:titleBar>


        <%-- Detail form --%>
        <table class="detailTable" cellpadding="0" cellspacing="0">


                <%-- Ignored field: id --%>


                <%-- Field: beginDate --%>
            <tr>
                <td class="detailLabelRW">${msg['adminHoliday.beginDate']}:</td>
                <td class="detailFieldCell">
                    <h:outputText value="#{adminHolidayBean.beginDate}" converter="autentia.dateConverter"/>
                </td>
            </tr>


                <%-- Field: finalDate --%>
            <tr>
                <td class="detailLabelRW">${msg['adminHoliday.finalDate']}:</td>
                <td class="detailFieldCell">
                    <h:outputText value="#{adminHolidayBean.finalDate}" converter="autentia.dateConverter"/>
                </td>
            </tr>


                <%-- Field: state --%>
            <tr>
                <td class="detailLabelRW">${msg['adminHoliday.state']}:</td>
                <td class="detailFieldCell">
                    <h:outputText value="#{adminHolidayBean.stateFormatted}"/>
                </td>
            </tr>


                <%-- Field: observations --%>
            <tr>
                <td class="detailLabelRW">${msg['adminHoliday.observations']}:</td>
                <td class="detailFieldCell">
                    <h:outputText value="#{adminHolidayBean.observations}"/>
                </td>
            </tr>


                <%-- Field: userComment --%>
            <tr>
                <td class="detailLabelRW">${msg['adminHoliday.userComment']}:</td>
                <td class="detailFieldCell">
                    <h:outputText value="#{adminHolidayBean.userComment}"/>
                </td>
            </tr>


                <%-- Field: chargeYear --%>
            <tr>
                <td class="detailLabelRW">${msg['adminHoliday.chargeYear']}:</td>
                <td class="detailFieldCell">
                    <h:outputText value="#{adminHolidayBean.chargeYear}" converter="autentia.date2YearConverter"/>
                </td>
            </tr>


                <%-- Ignored field: departmentId --%>


                <%-- Ignored field: insertDate --%>


                <%-- Ignored field: updateDate --%>


                <%-- Field: userRequest --%>
            <tr>
                <td class="detailLabelRW">${msg['adminHoliday.userRequest']}:</td>
                <td class="detailFieldCell">
                    <h:outputText value="#{adminHolidayBean.userRequest.name}"/>
                </td>
            </tr>

        </table>

    </h:form>
</f:view>

</body>
</html>

<%-- adminHoliday - generated by stajanov (do not edit/delete) --%>
