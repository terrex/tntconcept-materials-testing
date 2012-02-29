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

<%-- List of interactions --%>
<t:dataTable id="interactions" var="item" value="#{offerBean.interactions}" preserveDataModel="false"
             cellpadding="0" cellspacing="0" styleClass="detailListTable"
             headerClass="detailListHeaderCell" footerClass="detailListFooter"
             rowClasses="detailListRowO,detailListRowE"
             columnClasses="listCmdCell,detailListInteractionCreationDate,detailListInteractionWhy,detailListInteractionProject,detailListInteractionProject,detailListInteractionType,detailListInteractionUser">


    <%-- Commands --%>
    <h:column>
        <f:facet name="header">
            <h:panelGroup>
                <t:commandLink action="#{offerBean.createInteractions}" rendered="#{offerBean.createAvailable}">
                    <h:graphicImage title="#{msg.entityActions_new}" value="/img/new.gif" styleClass="cmdImg"/>
                </t:commandLink>
            </h:panelGroup>
        </f:facet>
        <t:commandLink action="#{offerBean.editInteractions}" rendered="#{offerBean.editAvailable}">
            <h:graphicImage title="#{msg.entityActions_edit}" value="/img/edit.gif" styleClass="cmdImg"/>
        </t:commandLink>
        <t:commandLink action="#{offerBean.deleteInteractions}" rendered="#{offerBean.editAvailable}"
                       onclick="if( !confirm('#{msg['question.confirmDelete']}') ) return false;">
            <h:graphicImage title="#{msg.entityActions_delete}" value="/img/delete.gif" styleClass="cmdImg"/>
        </t:commandLink>
    </h:column>


    <%-- Ignored field: id --%>


    <h:column>

        <f:facet name="header">
            <h:outputText value="#{msg['interaction.creationDate']}" styleClass="detailListHeader"/>
        </f:facet>

        <%-- Field: creationDate --%>

        <h:outputText value="#{item.creationDate}" converter="autentia.dateConverter"/>

    </h:column>


    <%-- Ignored field: interest --%>


    <h:column>

        <f:facet name="header">
            <h:outputText value="#{msg['interaction.why']}" styleClass="detailListHeader"/>
        </f:facet>

        <%-- Field: why --%>

        <h:outputText value="#{item.why}"/>

    </h:column>


    <%-- Ignored field: description --%>


    <%-- Ignored field: file --%>


    <%-- Ignored field: fileMime --%>


    <%-- Ignored field: observations --%>


    <%-- Ignored field: departmentId --%>


    <%-- Ignored field: insertDate --%>


    <%-- Ignored field: updateDate --%>


    <h:column>

        <f:facet name="header">
            <h:outputText value="#{msg['interaction.organization']}" styleClass="detailListHeader"/>
        </f:facet>

        <%-- Field: project --%>

        <h:outputText value="#{item.clientName}"/>

    </h:column>


    <h:column>

        <f:facet name="header">
            <h:outputText value="#{msg['interaction.project']}" styleClass="detailListHeader"/>
        </f:facet>

        <%-- Field: project --%>

        <h:outputText value="#{item.project.name}"/>

    </h:column>


    <h:column>

        <f:facet name="header">
            <h:outputText value="#{msg['interaction.type']}" styleClass="detailListHeader"/>
        </f:facet>

        <%-- Field: type --%>

        <h:outputText value="#{item.type.name}"/>

    </h:column>


    <h:column>

        <f:facet name="header">
            <h:outputText value="#{msg['interaction.user']}" styleClass="detailListHeader"/>
        </f:facet>

        <%-- Field: user --%>

        <h:outputText value="#{item.user.name}"/>

    </h:column>


    <%-- Ignored field: offer --%>


</t:dataTable>
