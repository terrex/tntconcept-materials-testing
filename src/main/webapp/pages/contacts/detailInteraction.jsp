<%-- interaction - generated by stajanov (do not edit/delete) --%>


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

<!-- editInteraction.jsp: generated by stajanov code generator -->
<f:loadBundle basename="com.autentia.intra.resources.messages" var="msg"/>
<i:location place="detailInteraction" msg="${msg}"/>

<f:view>
    <%@include file="/inc/header.jsp" %>
    <h:form id="interaction" enctype="multipart/form-data">

        <%-- Header --%>
        <i:titleBar name="${interactionBean.why}" msg="${msg}">
            <t:commandLink action="#{interactionBean.edit}" immediate="true"
                           rendered="#{interactionBean.editAvailable}">
                <f:param name="id" value="#{interactionBean.id}"/>
                <h:graphicImage title="#{msg.entityActions_edit}" value="/img/edit.gif" styleClass="cmdImg"/>
            </t:commandLink>
            <h:commandLink action="#{interactionBean.delete}" rendered="#{interactionBean.deleteAvailable}"
                           onclick="if( !confirm('#{msg['question.confirmDelete']}') ) return false;">
                <h:graphicImage title="#{msg.entityActions_delete}" value="/img/delete.gif" styleClass="titleImg"/>
            </h:commandLink>
            <h:commandLink action="#{interactionBean.list}" immediate="true">
                <h:graphicImage title="#{msg.entityActions_back}" value="/img/back.gif" styleClass="titleImg"/>
            </h:commandLink>
        </i:titleBar>


        <%-- Detail form --%>
        <table class="detailTable" cellpadding="0" cellspacing="0">


                <%-- Ignored field: id --%>


                <%-- Field: creationDate --%>
            <tr>
                <td class="detailLabelRW">${msg['interaction.creationDate']}:</td>
                <td class="detailFieldCell">
                    <h:outputText value="#{interactionBean.creationDate}" converter="autentia.dateConverter"/>
                </td>
            </tr>


                <%-- Field: interest --%>
            <tr>
                <td class="detailLabelRW">${msg['interaction.interest']}:</td>
                <td class="detailFieldCell">
                    <h:outputText value="#{interactionBean.interestFormatted}"/>
                </td>
            </tr>


                <%-- Field: medio --%>
            <tr>
                <td class="detailLabelRW">${msg['interaction.medio']}:</td>
                <td class="detailFieldCell">
                    <h:outputText value="#{interactionBean.medioFormatted}"/>
                </td>
            </tr>


                <%-- Field: why --%>
            <tr>
                <td class="detailLabelRW">${msg['interaction.why']}:</td>
                <td class="detailFieldCell">
                    <h:outputText value="#{interactionBean.why}"/>
                </td>
            </tr>


                <%-- Field: description --%>
            <tr>
                <td class="detailLabelRW">${msg['interaction.description']}:</td>
                <td class="detailFieldCell">
                    <h:outputText value="#{interactionBean.description}"/>
                </td>
            </tr>


                <%-- Field: file --%>
            <tr>
                <td class="detailLabelRW">${msg['interaction.file']}:</td>
                <td class="detailFieldCell">
                    <i:fileLink type="interaction" objectId="${interactionBean.id}" file="${interactionBean.file}"/>
                </td>
            </tr>


                <%-- Ignored field: fileMime --%>


                <%-- Field: observations --%>
            <tr>
                <td class="detailLabelRW">${msg['interaction.observations']}:</td>
                <td class="detailFieldCell">
                    <h:outputText value="#{interactionBean.observations}"/>
                </td>
            </tr>


                <%-- Ignored field: departmentId --%>


                <%-- Ignored field: insertDate --%>


                <%-- Ignored field: updateDate --%>


                <%-- Field: project --%>
            <tr>
                <td class="detailLabelRW">${msg['interaction.project']}:</td>
                <td class="detailFieldCell">
                    <h:outputText value="#{interactionBean.project.name}"/>
                </td>
            </tr>


                <%-- Field: type --%>
            <tr>
                <td class="detailLabelRW">${msg['interaction.type']}:</td>
                <td class="detailFieldCell">
                    <h:outputText value="#{interactionBean.type.name}"/>
                </td>
            </tr>


                <%-- Field: user --%>
            <tr>
                <td class="detailLabelRW">${msg['interaction.user']}:</td>
                <td class="detailFieldCell">
                    <h:outputText value="#{interactionBean.user.name}"/>
                </td>
            </tr>


                <%-- Ignored field: offer --%>


        </table>

    </h:form>
</f:view>

</body>
</html>
<%-- interaction - generated by stajanov (do not edit/delete) --%>
