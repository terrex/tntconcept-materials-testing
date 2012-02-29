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

<f:loadBundle basename="com.autentia.intra.resources.messages" var="msg"/>
<i:location place="globalHoursReport" msg="${msg}"/>

<f:view>
    <%@include file="/inc/header.jsp" %>
    <h:form id="globalHoursReport">

        <%-- Header --%>
        <i:titleBar msg="${msg}">

        </i:titleBar>

        <%-- Dates --%>
        <h:panelGrid columns="4" cellpadding="0" cellspacing="0" styleClass="editTable"
                     columnClasses="editLabelRW,editFieldCellGlobal,editLabelRW,editFieldCellGlobal">
            <h:outputText value="#{msg['globalHoursReport.startDate']}:"/>
            <t:inputCalendar id="startDate" value="#{globalHoursReportBean.startDate}" required="true"
                             styleClass="requiredFieldClass"
                             renderAsPopup="true" popupDateFormat="dd/MM/yyyy" renderPopupButtonAsImage="true"
                             popupTodayString="#{msg['calendar.today']}" popupWeekString="#{msg['calendar.week']}"
                             onchange="submit()"/>

            <h:outputText value="#{msg['globalHoursReport.endDate']}:"/>
            <t:inputCalendar id="endDate" value="#{globalHoursReportBean.endDate}" required="true"
                             styleClass="requiredFieldClass"
                             renderAsPopup="true" popupDateFormat="dd/MM/yyyy" renderPopupButtonAsImage="true"
                             popupTodayString="#{msg['calendar.today']}" popupWeekString="#{msg['calendar.week']}"
                             onchange="submit()"/>


            <h:outputText value="#{msg['globalHoursReport.billable']}:"/>
            <h:selectBooleanCheckbox id="billable" value="#{globalHoursReportBean.billable}" onchange="submit()"/>

            <h:commandButton action="#" title="#{msg['globalHoursReport.show']}"
                             value="#{msg['globalHoursReport.show']}"/>


        </h:panelGrid>


        <t:dataTable id="list" var="globalHourReport" value="#{globalHoursReportBean.all}" preserveDataModel="false"
                     cellpadding="1" cellspacing="1" styleClass="listTable"
                     headerClass="listHeaderCell" footerClass="listFooterGlobal"
                     rowClasses="listRowO,listRowE"
                     rowOnMouseOver="this.savedClassName=this.className;this.className='listRowSel';"
                     rowOnMouseOut="this.className=this.savedClassName;">

            <h:column>
                <f:facet name="header">
                    <f:verbatim>${msg['globalHoursReport.organization']}</f:verbatim>
                </f:facet>
                <h:outputText value="#{globalHourReport.project.client.name}"/>
            </h:column>

            <h:column>
                <f:facet name="header">
                    <f:verbatim>${msg['globalHoursReport.project']}</f:verbatim>
                </f:facet>
                <h:outputText value="#{globalHourReport.project.name}"/>
                <f:facet name="footer">
                    <f:verbatim>${msg['globalHoursReport.total']}</f:verbatim>
                </f:facet>
            </h:column>

            <c:forEach var="user" items="${globalHoursReportBean.users}">
                <h:column>
                    <f:facet name="header">
                        <f:verbatim> ${user.name}</f:verbatim>
                    </f:facet>
                    <h:outputText value="#{globalHourReport.nextHours}"/>


                    <f:facet name="footer">
                        <h:outputText value="#{globalHoursReportBean.nextUserTotal}"/>
                    </f:facet>

                </h:column>

            </c:forEach>

            <h:column>
                <f:facet name="header">
                    <f:verbatim>${msg['globalHoursReport.total']}</f:verbatim>
                </f:facet>

                <h:outputText value="#{globalHourReport.total}" styleClass="listFooterGlobal"/>

                <f:facet name="footer">
                    <h:outputText value="#{globalHoursReportBean.totalHours}"/>
                </f:facet>
            </h:column>


        </t:dataTable>


    </h:form>

</f:view>
</body>
</html>
