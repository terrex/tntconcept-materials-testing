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

<!-- editObjective.jsp: generated by stajanov code generator -->
<f:loadBundle basename="com.autentia.intra.resources.messages" var="msg"/>
<i:location place="editObjective" msg="${msg}"/>

<f:view>
    <%@include file="/inc/header.jsp" %>
    <h:form id="objective" enctype="multipart/form-data">

        <%-- Header --%>
        <i:titleBar name="${objectiveBean.name}" msg="${msg}">
            <h:commandLink action="#{objectiveBean.save}">
                <h:graphicImage value="/img/save.gif" styleClass="titleImg"/>
            </h:commandLink>
            <h:commandLink action="#{objectiveBean.delete}"
                           onclick="if( !confirm('#{msg['question.confirmDelete']}') ) return false;">
                <h:graphicImage value="/img/delete.gif" styleClass="titleImg"/>
            </h:commandLink>
            <h:commandLink action="#{objectiveBean.list}" immediate="true">
                <h:graphicImage value="/img/back.gif" styleClass="titleImg"/>
            </h:commandLink>
        </i:titleBar>

        <%-- Edition form --%>
        <table class="editTable" cellpadding="0" cellspacing="0">

                <%-- objective - generated by stajanov (do not edit/delete) --%>


                <%-- Ignored field: id --%>


                <%-- Field: state --%>
            <tr>
                <td class="editLabelRW">${msg['objective.state']}:</td>
                <td class="editFieldCell">

                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="state"/>
                        <h:selectOneMenu id="state" value="#{objectiveBean.state}">
                            <f:converter converterId="autentia.EnumConverter"/>
                            <f:selectItems value="#{objectiveBean.states}"/>
                        </h:selectOneMenu>
                    </h:panelGroup>

                </td>
            </tr>

                <%-- Field: name --%>
            <tr>
                <td class="editLabelRW">*${msg['objective.name']}:</td>
                <td class="editFieldCell">

                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="name"/>
                        <h:inputTextarea id="name" value="#{objectiveBean.name}" rows="2" cols="68" required="true"
                                         styleClass="requiredFieldClass"/>
                    </h:panelGroup>

                </td>
            </tr>

                <%-- Ignored field: log --%>


                <%-- Ignored field: startDate --%>

                <%-- Field: endDate --%>

            <tr>
                <td class="editLabelRW">*${msg['objective.endDate']}:</td>
                <td class="editFieldCell">

                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="endDate"/>
                        <t:inputCalendar id="startDate" value="#{objectiveBean.endDate}"
                                         required="true" styleClass="requiredFieldClass"
                                         renderAsPopup="true" popupDateFormat="d/MM/yyyy"
                                         renderPopupButtonAsImage="true"
                                         popupTodayString="#{msg['calendar.today']}"
                                         popupWeekString="#{msg['calendar.week']}"
                                />
                    </h:panelGroup>

                </td>
            </tr>


                <%-- Ignored field: user --%>


                <%-- Field: organization --%>
            <tr>
                <td class="editLabelRW">*${msg['editObjective.organization']}:</td>
                <td class="editFieldCell">
                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="organization"/>
                        <h:selectOneMenu id="organization" value="#{objectiveBean.selectedOrganization}"
                                         immediate="true"
                                         onchange="submit()"
                                         styleClass="requiredFieldClass"
                                         valueChangeListener="#{objectiveBean.onSelectedOrganizationChanged}">
                            <f:selectItems value="#{objectiveBean.organizations}"/>
                            <f:converter converterId="autentia.EntityConverter"/>
                        </h:selectOneMenu>
                    </h:panelGroup>
                </td>
            </tr>

                <%-- Field: project --%>
            <tr>
                <td class="editLabelRW">*${msg['objective.project']}:</td>
                <td class="editFieldCell">

                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="project"/>
                        <h:selectOneListbox id="project" value="#{objectiveBean.project}" required="true"
                                            styleClass="requiredFieldClass" size="5">
                            <f:selectItems value="#{objectiveBean.projectsOpenBySelectedOrganization}"/>
                            <f:converter converterId="autentia.EntityConverter"/>
                        </h:selectOneListbox>
                    </h:panelGroup>

                </td>
            </tr>


                <%-- objective - generated by stajanov (do not edit/delete) --%>

        </table>

    </h:form>
</f:view>

</body>
</html>  		
