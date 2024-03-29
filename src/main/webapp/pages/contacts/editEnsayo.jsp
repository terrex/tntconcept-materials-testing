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

<!-- editEnsayo.jsp: generated by stajanov code generator -->
<f:loadBundle basename="com.autentia.intra.resources.messages" var="msg"/>
<i:location place="editEnsayo" msg="${msg}"/>

<f:view>
    <%@include file="/inc/header.jsp" %>
    <h:form id="ensayo" enctype="multipart/form-data">

        <%-- Header --%>
        <i:titleBar name="${ensayoBean.name}" msg="${msg}">
            <h:commandLink action="#{ensayoBean.save}"
                           onclick="if(!askSave('#{ensayoBean.id}','#{msg['question.confirmSave']}')) return false;">
                <h:graphicImage title="#{msg.entityActions_save}" value="/img/save.gif" styleClass="titleImg"/>
            </h:commandLink>
            <h:commandLink rendered="#{ensayoBean.id!=null}" action="#{ensayoBean.delete}"
                           onclick="if( !confirm('#{msg['question.confirmDelete']}') ) return false;">
                <h:graphicImage title="#{msg.entityActions_delete}" value="/img/delete.gif" styleClass="titleImg"/>
            </h:commandLink>
            <h:commandLink action="#{ensayoBean.list}" immediate="true">
                <h:graphicImage title="#{msg.entityActions_back}" value="/img/back.gif" styleClass="titleImg"/>
            </h:commandLink>
        </i:titleBar>

        <%-- Edition form --%>
        <table class="editTable" cellpadding="0" cellspacing="0">

                <%-- Ensayo - generated by stajanov (do not edit/delete) --%>


                <%-- Ignored field: id --%>


                <%-- Field: name --%>
            <tr>
                <td class="editLabelRW">*${msg['ensayo.name']}:</td>

                <td class="editFieldCell">

                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="name"/>
                        <h:inputText id="name" value="#{ensayoBean.name}" size="70" maxlength="255" required="true"
                                     styleClass="requiredFieldClass"/>
                    </h:panelGroup>

                </td>
            </tr>

                <%-- Field: name --%>
            <tr>
                <td class="editLabelRW">*${msg['ensayo.nameIngles']}:</td>

                <td class="editFieldCell">

                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="nameIngles"/>
                        <h:inputText id="nameIngles" value="#{ensayoBean.nameIngles}" size="70" maxlength="255"/>
                    </h:panelGroup>

                </td>
            </tr>

                <%-- Field: description --%>
            <tr>
                <td class="editLabelRW">${msg['ensayo.description']}:</td>
                <td class="editFieldCell">
                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="description"/>
                        <h:inputTextarea id="description" value="#{ensayoBean.description}" rows="5" cols="68"/>
                    </h:panelGroup>
                </td>
            </tr>


                <%-- Field: procedimientos --%>
            <tr>
                <td class="editLabelRW">${msg['ensayo.procedimientos']}:</td>

                <td class="editFieldCell">

                    <%@include file="inlineEditEnsayoProcedimiento.jsp" %>

                </td>
            </tr>


                <%-- Field: cost --%>
            <tr>
                <td class="editLabelRW">${msg['ensayo.cost']}:</td>

                <td class="editFieldCell">

                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="cost"/>
                        <h:inputText id="cost" value="#{ensayoBean.cost}" size="10"/>
                    </h:panelGroup>

                </td>
            </tr>


            <tr>
                <td class="editLabelRW">${msg['ensayo.dimensional']}:</td>
                <td class="editFieldCell">
                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="dimensional"/>
                        <h:selectBooleanCheckbox id="dimensional" value="#{ensayoBean.ensayo.dimensional}"/>
                    </h:panelGroup>
                </td>
            </tr>


                <%-- Ignored field: ownerId --%>


                <%-- Ignored field: departmentId --%>


                <%-- Ignored field: insertDate --%>


                <%-- Ignored field: updateDate --%>


                <%-- Ensayo - generated by stajanov (do not edit/delete) --%>
        </table>

        <h:commandLink action="#{historicoBean.ver_historico}" immediate="true" target="_blank"
                       rendered="#{ensayoBean.id != null}">
            <f:param name="idObjeto" value="#{ensayoBean.id}"/>
            <f:param name="klazz" value="#{ensayoBean.ensayo.class.name}"/>
            <h:graphicImage value="/img/historico.png" styleClass="titleImg"/>
        </h:commandLink>

    </h:form>
</f:view>

</body>
</html>  		

