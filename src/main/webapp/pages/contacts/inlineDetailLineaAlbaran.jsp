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

<%@include file="/inc/uiCore.jsp" %>

<%-- List of lineaAlbarans --%>
<t:dataTable id="lineaAlbarans" var="item" value="#{albaranBean.lineaAlbarans}" preserveDataModel="false"
             cellpadding="0" cellspacing="0" styleClass="detailListTable"
             headerClass="detailListHeaderCell" footerClass="detailListFooter"
             rowClasses="detailListRowO,detailListRowE"
             columnClasses="listCmdCell,detailListLineaAlbaranProject">


    <%-- Ignored field: id --%>


    <h:column>

        <f:facet name="header">
            <h:outputText value="Cliente/Proyecto" styleClass="detailListHeader"/>
        </f:facet>


        <h:panelGroup>
            <%-- Field: organization --%>
            <h:outputText value="#{item.client}"/>

            <%-- Field: project --%>
            <h:outputText value="#{item.project}"/>

        </h:panelGroup>


    </h:column>


    <%-- Ignored field: ownerId --%>


    <%-- Ignored field: departmentId --%>


    <%-- Ignored field: insertDate --%>


    <%-- Ignored field: updateDate --%>


</t:dataTable>
