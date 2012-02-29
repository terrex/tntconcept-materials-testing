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
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>/css/bitacore.css">
</head>
<body>

<!-- activitys.jsp: generated by stajanov code generator -->
<f:loadBundle basename="com.autentia.intra.resources.messages" var="msg"/>
<i:location place="preferences" msg="${msg}"/>

<f:view>
<%@include file="/inc/header.jsp" %>
<h:form>
<%-- Header --%>
<i:titleBar msg="${msg}">
    <h:commandLink action="#{settingBean.saveSettings}">
        <h:graphicImage value="/img/save.gif" styleClass="titleImg"/>
    </h:commandLink>
</i:titleBar>
<%-- Edition form --%>
<h:panelGrid styleClass="editTable" cellpadding="0" cellspacing="0"
             columns="2" columnClasses="editLabelRW,editFieldCell">


<h:column>
    <h:outputText value="#{msg['general.settings']}"/>
</h:column>
<h:column>
    <h:outputText value="#{msg['general.settings.help']}"/>
</h:column>

<%-- Field: listSize --%>
<h:column>
    <t:popup styleClass="settingActivityPopup"
             displayAtDistanceX="25"
             displayAtDistanceY="25">
        <h:graphicImage value="/img/help.gif"/>
        <f:facet name="popup">
            <h:panelGroup>
                <h:panelGrid columns="1">
                    <h:outputText value="#{msg['general.settings.help.listSize']}"/>
                </h:panelGrid>
            </h:panelGroup>
        </f:facet>
    </t:popup>
    <h:outputText
            value="#{msg['general.settings.listSize']}:"/>
</h:column>
<h:column>
    <h:panelGroup>
        <h:message styleClass="error" showSummary="true" showDetail="false"
                   for="listSize"/>
        <h:inputText id="listSize"
                     value="#{settingBean.mySettings.listSize}" size="4">
            <f:validateLongRange minimum="0"/>
        </h:inputText>
    </h:panelGroup>
</h:column>


<h:column>
    <h:outputText value="#{msg['activitys.settings']}"/>
</h:column>
<h:column>
    <h:outputText value="#{msg['activitys.settings.help']}"/>
</h:column>
<%-- Field: theme --%>
<h:column>
    <t:popup styleClass="settingActivityPopup"
             displayAtDistanceX="25"
             displayAtDistanceY="25">
        <h:graphicImage value="/img/help.gif"/>
        <f:facet name="popup">
            <h:panelGroup>
                <h:panelGrid columns="1">
                    <h:outputText value="#{msg['activitys.settings.help.theme']}"/>
                </h:panelGrid>
            </h:panelGroup>
        </f:facet>
    </t:popup>
    <h:outputText value="#{msg['activitys.settings.theme']}:"/>
</h:column>
<h:column>
    <h:panelGroup>
        <t:selectOneMenu value="#{settingBean.mySettings.theme}">
            <f:selectItems value="#{settingBean.mySettings.themeItems}"/>
        </t:selectOneMenu>
    </h:panelGroup>
</h:column>
<%-- Field: mode --%>
<h:column>
    <t:popup styleClass="settingActivityPopup"
             displayAtDistanceX="25"
             displayAtDistanceY="25">
        <h:graphicImage value="/img/help.gif"/>
        <f:facet name="popup">
            <h:panelGroup>
                <h:panelGrid columns="1">
                    <h:outputFormat value="#{msg['activitys.settings.help.mode']}">
                        <f:param value="#{msg['activitys.settings.mode.month']}"/>
                        <f:param value="#{msg['activitys.settings.mode.week']}"/>
                        <f:param value="#{msg['activitys.settings.mode.workweek']}"/>
                        <f:param value="#{msg['activitys.settings.mode.day']}"/>
                    </h:outputFormat>
                </h:panelGrid>
            </h:panelGroup>
        </f:facet>
    </t:popup>
    <h:outputText value="#{msg['activitys.settings.mode']}:"/>
</h:column>
<h:column>
    <h:panelGroup>
        <t:selectOneMenu value="#{settingBean.mySettings.mode}">
            <f:selectItems value="#{settingBean.mySettings.modeItems}"/>
        </t:selectOneMenu>
    </h:panelGroup>
</h:column>

<%-- Field: displayHourFrom --%>
<h:column>
    <t:popup styleClass="settingActivityPopup"
             displayAtDistanceX="25"
             displayAtDistanceY="25">
        <h:graphicImage value="/img/help.gif"/>
        <f:facet name="popup">
            <h:panelGroup>
                <h:panelGrid columns="1">
                    <h:outputText value="#{msg['activitys.settings.help.displayHourFrom']}"/>
                </h:panelGrid>
            </h:panelGroup>
        </f:facet>
    </t:popup>
    <h:outputText value="#{msg['activitys.settings.displayHourFrom']}"/>
</h:column>
<h:column>
    <h:panelGroup>
        <h:message styleClass="error" showSummary="true" showDetail="false"
                   for="displayHourFrom"/>
        <h:inputText id="displayHourFrom"
                     value="#{settingBean.mySettings.displayHourFrom}" size="4"/>
    </h:panelGroup>
</h:column>

<%-- Field: displayHourUntil --%>
<h:column>
    <t:popup styleClass="settingActivityPopup"
             displayAtDistanceX="25"
             displayAtDistanceY="25">
        <h:graphicImage value="/img/help.gif"/>
        <f:facet name="popup">
            <h:panelGroup>
                <h:panelGrid columns="1">
                    <h:outputText value="#{msg['activitys.settings.help.displayHourUntil']}"/>
                </h:panelGrid>
            </h:panelGroup>
        </f:facet>
    </t:popup>
    <h:outputText value="#{msg['activitys.settings.displayHourUntil']}:"/>
</h:column>
<h:column>
    <h:panelGroup>
        <h:message styleClass="error" showSummary="true" showDetail="false"
                   for="displayHourUntil"/>
        <h:inputText id="displayHourUntil"
                     value="#{settingBean.mySettings.displayHourUntil}" size="4"/>
    </h:panelGroup>
</h:column>

<%-- Field: WorkingHours --%>
<h:column>
    <t:popup styleClass="settingActivityPopup"
             displayAtDistanceX="25"
             displayAtDistanceY="25">
        <h:graphicImage value="/img/help.gif"/>
        <f:facet name="popup">
            <h:panelGroup>
                <h:panelGrid columns="1">
                    <h:outputText value="#{msg['activitys.settings.help.workingDayHourStarts']}"/>
                </h:panelGrid>
            </h:panelGroup>
        </f:facet>
    </t:popup>
    <h:outputText
            value="#{msg['activitys.settings.workingDayHourStarts']}:"/>
</h:column>
<h:column>
    <h:panelGroup>
        <h:message styleClass="error" showSummary="true" showDetail="false"
                   for="workingDayHourStarts"/>
        <h:inputText id="workingDayHourStarts"
                     value="#{settingBean.mySettings.workingDayHourStarts}" size="4"/>
    </h:panelGroup>
</h:column>

<%-- Field: WorkingHours --%>
<h:column>
    <t:popup styleClass="settingActivityPopup"
             displayAtDistanceX="25"
             displayAtDistanceY="25">
        <h:graphicImage value="/img/help.gif"/>
        <f:facet name="popup">
            <h:panelGroup>
                <h:panelGrid columns="1">
                    <h:outputText value="#{msg['activitys.settings.help.workingHours']}"/>
                </h:panelGrid>
            </h:panelGroup>
        </f:facet>
    </t:popup>
    <h:outputText
            value="#{msg['activitys.settings.workingHours']}:"/>
</h:column>
<h:column>
    <h:panelGroup>
        <h:message styleClass="error" showSummary="true" showDetail="false"
                   for="workingHours"/>
        <h:inputText id="workingHours"
                     value="#{settingBean.mySettings.workingHours}" size="4"/>
    </h:panelGroup>
</h:column>

<%-- Field: headerFormat --%>
<h:column>
    <t:popup styleClass="settingActivityPopup"
             displayAtDistanceX="25"
             displayAtDistanceY="25">
        <h:graphicImage value="/img/help.gif"/>
        <f:facet name="popup">
            <h:panelGroup>
                <h:panelGrid columns="1">
                    <h:outputText value="#{msg['activitys.settings.help.headerFormat']}"/>
                </h:panelGrid>
            </h:panelGroup>
        </f:facet>
    </t:popup>
    <h:outputText value="#{msg['activitys.settings.headerFormat']}:"/>
</h:column>
<h:column>
    <h:panelGroup>
        <h:message styleClass="error" showSummary="true" showDetail="false"
                   for="headerFormat"/>
        <h:inputText id="headerFormat"
                     value="#{settingBean.mySettings.headerFormat}" size="10"/>
    </h:panelGroup>
</h:column>

</h:panelGrid>

</h:form>
</f:view>
</body>
</html>