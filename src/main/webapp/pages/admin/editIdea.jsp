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

<!-- editIdea.jsp: generated by stajanov code generator -->
<f:loadBundle basename="com.autentia.intra.resources.messages" var="msg"/>
<i:location place="editIdea" msg="${msg}"/>

<f:view>
    <%@include file="/inc/header.jsp" %>
    <h:form id="idea" enctype="multipart/form-data">

        <%-- Header --%>
        <i:titleBar name="${ideaBean.name}" msg="${msg}">
            <h:commandLink action="#{ideaBean.save}">
                <h:graphicImage value="/img/save.gif" styleClass="titleImg"/>
            </h:commandLink>
            <h:commandLink action="#{ideaBean.delete}"
                           onclick="if( !confirm('#{msg['question.confirmDelete']}') ) return false;">
                <h:graphicImage value="/img/delete.gif" styleClass="titleImg"/>
            </h:commandLink>
            <h:commandLink action="#{ideaBean.list}" immediate="true">
                <h:graphicImage value="/img/back.gif" styleClass="titleImg"/>
            </h:commandLink>
        </i:titleBar>

        <%-- Edition form --%>
        <table class="editTable" cellpadding="0" cellspacing="0">

                <%-- idea - generated by stajanov (do not edit/delete) --%>


                <%-- Ignored field: id --%>


                <%-- Field: creationDate --%>
            <tr>
                <td class="editLabelRW">*${msg['idea.creationDate']}:</td>

                <td class="editFieldCell">

                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="creationDate"/>
                        <t:inputCalendar id="creationDate" value="#{ideaBean.creationDate}" required="true"
                                         styleClass="requiredFieldClass"
                                         renderAsPopup="true" popupDateFormat="d/MM/yyyy"
                                         renderPopupButtonAsImage="true"
                                         popupTodayString="#{msg['calendar.today']}"
                                         popupWeekString="#{msg['calendar.week']}"
                                />
                    </h:panelGroup>

                </td>
            </tr>


                <%-- Field: name --%>
            <tr>
                <td class="editLabelRW">*${msg['idea.name']}:</td>

                <td class="editFieldCell">

                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="name"/>
                        <h:inputText id="name" value="#{ideaBean.name}" size="70" required="true"
                                     styleClass="requiredFieldClass"/>
                    </h:panelGroup>

                </td>
            </tr>


                <%-- Field: description --%>
            <tr>
                <td class="editLabelRW">*${msg['idea.description']}:</td>

                <td class="editFieldCell">

                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="description"/>
                        <h:inputTextarea id="description" value="#{ideaBean.description}" rows="5" cols="68"
                                         required="true"
                                         styleClass="requiredFieldClass"/>
                    </h:panelGroup>

                </td>
            </tr>


                <%-- Field: benefits --%>
            <tr>
                <td class="editLabelRW">${msg['idea.benefits']}:</td>

                <td class="editFieldCell">

                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="benefits"/>
                        <h:inputTextarea id="benefits" value="#{ideaBean.benefits}" rows="5" cols="68"/>
                    </h:panelGroup>

                </td>
            </tr>


                <%-- Field: cost --%>
            <tr>
                <td class="editLabelRW">${msg['idea.cost']}:</td>

                <td class="editFieldCell">

                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="cost"/>
                        <h:inputTextarea id="cost" value="#{ideaBean.cost}" rows="5" cols="68"/>
                    </h:panelGroup>

                </td>
            </tr>


                <%-- Ignored field: departmentId --%>


                <%-- Ignored field: insertDate --%>


                <%-- Ignored field: updateDate --%>


                <%-- Ignored field: user --%>


                <%-- idea - generated by stajanov (do not edit/delete) --%>

        </table>

    </h:form>
</f:view>

</body>
</html>  		

