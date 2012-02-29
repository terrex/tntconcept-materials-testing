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
<i:location place="editBill" msg="${msg}"/>
<f:view>
    <%@include file="/inc/header.jsp" %>
    <h:form>
        <%-- Header --%>
        <i:titleBar msg="${msg}">
            <h:commandLink action="#{billBean.edit}" immediate="true">
                <f:param name="id" value="#{billBean.bill.id}"/>
                <h:graphicImage title="#{msg.entityActions_back}" value="/img/back.gif" styleClass="titleImg"/>
            </h:commandLink>

            <h:commandButton action="#{billBean.importProjects}" title="#{msg['bill.import_projects']}"
                             value="#{msg['bill.import_projects']}">

            </h:commandButton>

        </i:titleBar>


        <%-- List of billBreakDowns --%>
        <t:dataTable id="list" var="item" value="#{billBean.allProjectsBreakDowns}" preserveDataModel="false"
                     cellpadding="0" cellspacing="0" styleClass="listTable"
                     headerClass="editListHeaderCell" footerClass="listFooter"
                     rows="#{settingBean.mySettings.listSize}" rowClasses="listRowO,listRowE"
                     columnClasses="listCmdCell,editListBillBreakDownConcept,editListBillBreakDownUnits,editListBillBreakDownAmount,editListBillBreakDownIva">

            <%-- Commands --%>
            <h:column>
                <f:facet name="header">
                    <h:panelGroup>

                    </h:panelGroup>
                </f:facet>
                <h:selectBooleanCheckbox id="selected" value="#{item.selected}"
                                         immediate="true"></h:selectBooleanCheckbox>

            </h:column>


            <%-- Ignored field: id --%>


            <h:column>

                <f:facet name="header">
                    <h:outputText value="#{msg['billBreakDown.bitacore.concept']}" styleClass="editListHeader"/>
                </f:facet>

                <h:panelGroup>
                    <h:message styleClass="error" showSummary="true" showDetail="false" for="concept"/>
                    <h:outputText id="concept" value="#{item.concept}"/>
                </h:panelGroup>


            </h:column>


            <h:column>

                <f:facet name="header">
                    <h:outputText value="#{msg['billBreakDown.bitacore.units']}" styleClass="editListHeader"/>
                </f:facet>

                <h:panelGroup>
                    <h:message styleClass="error" showSummary="true" showDetail="false" for="units"/>
                    <h:outputText id="units" value="#{item.units}"/>
                </h:panelGroup>


            </h:column>


            <h:column>

                <f:facet name="header">
                    <h:outputText value="#{msg['billBreakDown.bitacore.amount']}" styleClass="editListHeader"/>
                </f:facet>

                <h:panelGroup>
                    <h:message styleClass="error" showSummary="true" showDetail="false" for="amount"/>
                    <h:outputText id="amount" value="#{item.amount}"/>
                </h:panelGroup>


            </h:column>


            <h:column>

                <f:facet name="header">
                    <h:outputText value="#{msg['billBreakDown.iva']}" styleClass="editListHeader"/>
                </f:facet>

                <h:panelGroup>
                    <h:message styleClass="error" showSummary="true" showDetail="false" for="iva"/>
                    <h:outputText id="iva" value="#{item.iva}"/>
                </h:panelGroup>


            </h:column>


            <h:column>

                <f:facet name="header">
                    <h:outputText value="#{msg['billBreakDown.total']}" styleClass="editListHeader"/>
                </f:facet>

                <h:panelGroup>
                    <h:message styleClass="error" showSummary="true" showDetail="false" for="total"/>
                    <h:outputText id="total" value="#{item.total}"/>
                </h:panelGroup>


            </h:column>


            <%-- Ignored field: bill --%>


        </t:dataTable>

        <%-- Paginator control --%>
        <%@include file="/inc/paginator.jsp" %>

    </h:form>
</f:view>

</body>
</html>