<%-- document - generated by stajanov (do not edit/delete) --%>


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

<!-- editDocument.jsp: generated by stajanov code generator -->
<f:loadBundle basename="com.autentia.intra.resources.messages" var="msg"/>
<i:location place="detailDocument" msg="${msg}"/>

<f:view>
    <%@include file="/inc/header.jsp" %>
    <h:form id="document" enctype="multipart/form-data">

        <%-- Header --%>
        <i:titleBar name="${documentBean.name}" msg="${msg}">
            <t:commandLink action="#{documentBean.edit}" immediate="true" rendered="#{documentBean.editAvailable}">
                <f:param name="id" value="#{documentBean.id}"/>
                <h:graphicImage title="#{msg.entityActions_edit}" value="/img/edit.gif" styleClass="cmdImg"/>
            </t:commandLink>
            <h:commandLink action="#{documentBean.delete}" rendered="#{documentBean.deleteAvailable}"
                           onclick="if( !confirm('#{msg['question.confirmDelete']}') ) return false;">
                <h:graphicImage title="#{msg.entityActions_delete}" value="/img/delete.gif" styleClass="titleImg"/>
            </h:commandLink>
            <h:commandLink action="#{documentBean.list}" immediate="true">
                <h:graphicImage title="#{msg.entityActions_back}" value="/img/back.gif" styleClass="titleImg"/>
            </h:commandLink>
        </i:titleBar>


        <%-- Detail form --%>
        <table class="detailTable" cellpadding="0" cellspacing="0">


                <%-- Ignored field: id --%>


                <%-- Field: creationDate --%>
            <tr>
                <td class="detailLabelRW">${msg['document.creationDate']}:</td>
                <td class="detailFieldCell">
                    <h:outputText value="#{documentBean.creationDate}" converter="autentia.dateConverter"/>
                </td>
            </tr>


                <%-- Field: name --%>
            <tr>
                <td class="detailLabelRW">${msg['document.name']}:</td>
                <td class="detailFieldCell">
                    <h:outputText value="#{documentBean.name}"/>
                </td>
            </tr>


                <%-- Field: description --%>
            <tr>
                <td class="detailLabelRW">${msg['document.description']}:</td>
                <td class="detailFieldCell">
                    <h:outputText value="#{documentBean.description}"/>
                </td>
            </tr>


                <%-- Field: categories --%>
            <tr>
                <td class="detailLabelRW">${msg['document.categories']}:</td>
                <td class="detailFieldCell">
                    <h:selectManyListbox id="categories" size="4">
                        <f:selectItems value="#{documentBean.categoriesSelected}"/>
                        <f:converter converterId="autentia.EntityConverter"/>
                        <f:attribute name="com.autentia.intra.converter.EntityConverter.valueClass"
                                     value="com.autentia.intra.businessobject.DocumentCategory"/>
                    </h:selectManyListbox>
                </td>
            </tr>


                <%-- Field: versions --%>
            <tr>
                <td class="detailLabelRW">${msg['document.versions']}:</td>
                <td class="detailFieldCell">
                    <%@include file="inlineDetailDocumentVersion.jsp" %>
                </td>
            </tr>

        </table>

    </h:form>
</f:view>

</body>
</html>
<%-- document - generated by stajanov (do not edit/delete) --%>
