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
<i:location place="editDepartment" msg="${msg}"/>

<f:view>
    <%@include file="/inc/header.jsp" %>
    <h:form id="department" enctype="multipart/form-data">

        <%-- Header --%>
        <i:titleBar name="${departmentBean.name}" msg="${msg}">
            <h:commandLink action="#{departmentBean.save}">
                <h:graphicImage value="/img/save.gif" styleClass="titleImg"/>
            </h:commandLink>
            <h:commandLink action="#{departmentBean.delete}"
                           onclick="if( !confirm('#{msg['question.confirmDelete']}') ) return false;">
                <h:graphicImage value="/img/delete.gif" styleClass="titleImg"/>
            </h:commandLink>
            <h:commandLink action="#{departmentBean.list}" immediate="true">
                <h:graphicImage value="/img/back.gif" styleClass="titleImg"/>
            </h:commandLink>
        </i:titleBar>

        <%-- Edition form --%>
        <table class="editDepartment" cellpadding="0" cellspacing="0">


                <%-- Department - generated by stajanov (do not edit/delete) --%>


                <%-- Ignored field: id --%>


                <%-- Field: name --%>
            <tr>
                <td class="editLabelRW">*${msg['department.name']}:</td>

                <td class="editFieldCell">

                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="name"/>
                        <h:inputText id="name" value="#{departmentBean.name}" size="70" maxlength="128" required="true"
                                     styleClass="requiredFieldClass"/>
                    </h:panelGroup>

                </td>
            </tr>


                <%-- Field: description --%>
            <tr>
                <td class="editLabelRW">${msg['department.description']}:</td>

                <td class="editFieldCell">

                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="description"/>
                        <h:inputTextarea id="description" value="#{departmentBean.description}" rows="5" cols="68"/>
                    </h:panelGroup>

                </td>
            </tr>


                <%-- Ignored field: ownerId --%>


                <%-- Ignored field: departmentId --%>


                <%-- Ignored field: insertDate --%>


                <%-- Ignored field: updateDate --%>


                <%-- Field: parent --%>
            <tr>
                <td class="editLabelRW">${msg['department.parent']}:</td>

                <td class="editFieldCell">

                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="parent"/>
                        <h:selectOneMenu id="parent" value="#{departmentBean.parent}">
                            <f:selectItems value="#{departmentBean.parents}"/>
                            <f:converter converterId="autentia.EntityConverter"/>
                        </h:selectOneMenu>
                    </h:panelGroup>

                </td>
            </tr>


                <%-- Department - generated by stajanov (do not edit/delete) --%>


        </table>

    </h:form>
</f:view>

</body>
</html>  		
