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
    <script src="<%=request.getContextPath()%>/script/bitacore.js" language="Javascript" type="text/javascript"
            charset="UTF-8"></script>
    <script type="text/javascript">
        function checkBillable() {
            var billable = document.getElementById('activity:billable');
            var defaultBillable = document.getElementById('activity:defaultBillable');

            if (billable.checked && defaultBillable.value == "false") {
                alert("Cuidado. El proyecto seleccionado se supone NO facturable.");
            } else if (!billable.checked && defaultBillable.value == "true") {
                alert("Cuidado. El proyecto seleccionado se supone SI facturable");
            }
        }

    </script>
</head>
<body>

<f:loadBundle basename="com.autentia.intra.resources.messages" var="msg"/>
<i:location place="editActivity" msg="${msg}"/>

<f:view>
    <%@include file="/inc/header.jsp" %>
    <h:form id="activity" enctype="multipart/form-data">
        <%@include file="/inc/tlibs.jsp" %>

        <%-- Header --%>
        <i:titleBar msg="${msg}">
            <t:commandLink action="#{activityBean.list}"
                           title="#{msg['activitys.alt.back']}" immediate="true">
                <t:graphicImage value="/img/back.gif" styleClass="titleImg"/>
            </t:commandLink>

            <h:commandLink action="#{activityBean.save}">
                <h:graphicImage value="/img/save.gif" styleClass="titleImg"/>
            </h:commandLink>
            <h:commandLink action="#{activityBean.delete}" rendered="#{activityBean.deleteAvailable}"
                           onclick="if( !confirm('#{msg['question.confirmDelete']}') ) return false;">
                <h:graphicImage value="/img/delete.gif" styleClass="titleImg"/>
            </h:commandLink>
        </i:titleBar>

        <%-- Edition form --%>
        <h:panelGrid styleClass="editTable" cellpadding="0" cellspacing="0" columns="2"
                     columnClasses="editLabelRW,editFieldCell" rendered="#{activityBean.activitySelected}">

            <%-- Field: startDateS --%>
            <h:outputText value="#{msg['activity.startDate']}:"/>
            <h:outputText value="#{activityBean.startDate}">
                <f:convertDateTime pattern="dd/MM/yy"/>
            </h:outputText>

            <%-- Field: startTime --%>
            <h:outputText value="*#{msg['activity.startTime']}:"/>
            <h:panelGroup>
                <h:message styleClass="error" showSummary="true" showDetail="true" for="startDate"/>
                <t:inputText id="startTimeHour" value="#{activityBean.startTimeHour}" size="2" maxlength="2"
                             validator="#{activityBean.validateHours}">
                    <t:jsValueChangeListener for="endTimeHour" property="value"
                                             expressionValue="actualizeTimeFields($srcElem);"/>
                    <f:validateDoubleRange minimum="0" maximum="23"/>
                </t:inputText>
                <h:outputText value=":"/>
                <t:inputText id="startTimeMinute" value="#{activityBean.startTimeMinute}" size="2" maxlength="2"
                             validator="#{activityBean.validateHours}">
                    <t:jsValueChangeListener for="endTimeMinute" property="value"
                                             expressionValue="actualizeTimeFields($srcElem);"/>
                    <f:validateDoubleRange minimum="0" maximum="59"/>
                </t:inputText>
            </h:panelGroup>
            <%-- Field: endTime --%>
            <h:outputText value="*#{msg['activity.finishTime']}:"/>
            <h:panelGroup>
                <h:message styleClass="error" showSummary="true" showDetail="true" for="endDate"/>
                <t:inputText id="endTimeHour" value="#{activityBean.endTimeHour}" size="2" maxlength="2"
                             validator="#{activityBean.validateHours}">
                    <t:jsValueChangeListener for="duration" property="value"
                                             expressionValue="actualizeTimeFields($srcElem);"/>
                    <f:validateDoubleRange minimum="0" maximum="23"/>
                </t:inputText>
                <h:outputText value=":"/>
                <t:inputText id="endTimeMinute" value="#{activityBean.endTimeMinute}" size="2" maxlength="2"
                             validator="#{activityBean.validateHours}">
                    <t:jsValueChangeListener for="duration" property="value"
                                             expressionValue="actualizeTimeFields($srcElem);"/>
                    <f:validateDoubleRange minimum="0" maximum="59"/>
                </t:inputText>
            </h:panelGroup>

            <%-- Field: duration --%>
            <h:outputText value="*#{msg['activity.duration']}"/>
            <h:panelGroup>
                <h:message styleClass="error" showSummary="true" showDetail="false" for="duration"/>
                <h:inputText id="duration" value="#{activityBean.duration}" required="true"
                             styleClass="requiredFieldClass" size="3"
                             validator="#{activityBean.validateHours}">
                    <t:jsValueChangeListener for="endTimeHour" property="value"
                                             expressionValue="actualizeTimeFields($srcElem);"/>
                    <f:converter converterId="autentia.MinuteToHourConverter"/>
                    <f:validateDoubleRange minimum="0"/>
                </h:inputText>
            </h:panelGroup>

            <%-- Field: description --%>
            <h:outputText value="*#{msg['activity.description']}:"/>
            <h:panelGroup>
                <h:message styleClass="error" showSummary="true" showDetail="false" for="description"/>
                <h:inputTextarea id="description" value="#{activityBean.description}" required="true"
                                 styleClass="requiredFieldClass" rows="5" cols="68" immediate="true"/>
            </h:panelGroup>


            <%-- Field: billable --%>
            <h:outputText value="#{msg['activity.billable']}:"/>
            <h:panelGroup>
                <h:message styleClass="error" showSummary="true" showDetail="false" for="billable"/>
                <h:selectBooleanCheckbox id="billable" value="#{activityBean.billable}" onchange="checkBillable()"/>

            </h:panelGroup>


            <%-- Field: organization --%>
            <h:outputText value="*#{msg['editActivity.organization']}:"/>
            <h:panelGroup>
                <h:message styleClass="error" showSummary="true" showDetail="false" for="organization"/>
                <t:selectOneMenu id="organization" value="#{activityBean.selectedOrganization}" onchange="submit()"
                                 styleClass="requiredFieldClass"
                                 valueChangeListener="#{activityBean.onSelectedOrganizationChanged}">
                    <f:selectItems value="#{activityBean.organizations}"/>
                    <f:converter converterId="autentia.EntityConverter"/>
                </t:selectOneMenu>
            </h:panelGroup>

            <%-- Ignored field: user --%>

            <%-- Customized: project list --%>
            <h:outputText value="*#{msg['editActivity.projects']}:"/>
            <h:panelGroup>
                <h:message styleClass="error" showSummary="true" showDetail="false" for="projects"/>
                <t:selectOneListbox id="projects" value="#{activityBean.selectedProject}" onchange="submit()" size="5"
                                    required="activityBean.projectsOpenBySelectedOrganization.size > 0"
                                    styleClass="requiredFieldClass"
                                    valueChangeListener="#{activityBean.onSelectedProjectChanged}">
                    <f:selectItems value="#{activityBean.projectsOpenBySelectedOrganization}"/>
                    <f:converter converterId="autentia.EntityConverter"/>
                </t:selectOneListbox>
            </h:panelGroup>

            <%-- Field: role --%>
            <h:outputText value="#{msg['activity.role']}:"/>
            <h:panelGroup>
                <h:message styleClass="error" showSummary="true" showDetail="false" for="role"/>
                <t:selectOneRadio layout="pageDirection" id="role" value="#{activityBean.role}">
                    <f:selectItems value="#{activityBean.rolesBySelectedProject}"/>
                    <f:converter converterId="autentia.EntityConverter"/>
                </t:selectOneRadio>
            </h:panelGroup>

            <%-- Field: ensayo --%>
            <h:outputText value="Ensayo:"/>
            <h:panelGroup>
                <h:message styleClass="error" showSummary="true" showDetail="false" for="ensayo"/>
                <t:selectOneRadio layout="pageDirection" id="ensayo" value="#{activityBean.ensayo}">
                    <f:selectItems value="#{activityBean.ensayosBySelectedProject}"/>
                    <f:converter converterId="autentia.EntityConverter"/>
                </t:selectOneRadio>
            </h:panelGroup>

        </h:panelGrid>

        <h:inputHidden id="defaultBillable" value="#{activityBean.defaultBillable}"/>

    </h:form>
</f:view>
</body>
</html>
