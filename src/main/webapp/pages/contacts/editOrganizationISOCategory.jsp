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

<!-- editOrganizationISOCategory.jsp: generated by stajanov code generator -->
<f:loadBundle basename="com.autentia.intra.resources.messages" var="msg"/>
<i:location place="editOrganizationISOCategory" msg="${msg}"/>

<f:view>
    <%@include file="/inc/header.jsp" %>
    <h:form id="organizationISOCategory" enctype="multipart/form-data">

        <%-- Header --%>
        <i:titleBar name="${organizationISOCategoryBean.name}" msg="${msg}">
            <h:commandLink action="#{organizationISOCategoryBean.save}"
                           onclick="if(!askSave('#{organizationISOCategoryBean.id}','#{msg['question.confirmSave']}')) return false;">
                <h:graphicImage title="#{msg.entityActions_save}" value="/img/save.gif" styleClass="titleImg"/>
            </h:commandLink>
            <h:commandLink rendered="#{organizationISOCategoryBean.id!=null}"
                           action="#{organizationISOCategoryBean.delete}"
                           onclick="if( !confirm('#{msg['question.confirmDelete']}') ) return false;">
                <h:graphicImage title="#{msg.entityActions_delete}" value="/img/delete.gif" styleClass="titleImg"/>
            </h:commandLink>
            <h:commandLink action="#{organizationISOCategoryBean.list}" immediate="true">
                <h:graphicImage title="#{msg.entityActions_back}" value="/img/back.gif" styleClass="titleImg"/>
            </h:commandLink>
        </i:titleBar>

        <%-- Edition form --%>
        <table class="editTable" cellpadding="0" cellspacing="0">

                <%-- OrganizationISOCategory - generated by stajanov (do not edit/delete) --%>


                <%-- Ignored field: id --%>


                <%-- Field: name --%>
            <tr>
                <td class="editLabelRW">*${msg['organizationISOCategory.name']}:</td>

                <td class="editFieldCell">

                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="name"/>
                        <h:inputText id="name" value="#{organizationISOCategoryBean.name}" maxlength="128"
                                     required="true" styleClass="requiredFieldClass"/>
                    </h:panelGroup>

                </td>
            </tr>


                <%-- Field: description --%>
            <tr>
                <td class="editLabelRW">${msg['organizationISOCategory.description']}:</td>

                <td class="editFieldCell">

                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="description"/>
                        <h:inputTextarea id="description" value="#{organizationISOCategoryBean.description}" rows="5"
                                         cols="68"/>
                    </h:panelGroup>

                </td>
            </tr>


                <%-- Ignored field: ownerId --%>


                <%-- Ignored field: departmentId --%>


                <%-- Ignored field: insertDate --%>


                <%-- Ignored field: updateDate --%>


                <%-- OrganizationISOCategory - generated by stajanov (do not edit/delete) --%>

        </table>

    </h:form>
</f:view>

</body>
</html>  		
