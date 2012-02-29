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

<!-- searchBulletinBoardCategory.jsp: generated by stajanov code generator -->
<f:loadBundle basename="com.autentia.intra.resources.messages" var="msg"/>
<i:location place="searchBulletinBoardCategory" msg="${msg}"/>

<f:view>
    <%@include file="/inc/header.jsp" %>
    <h:form id="search" enctype="multipart/form-data">

        <%-- Header --%>
        <i:titleBar msg="${msg}">
            <h:commandLink action="#{bulletinBoardCategoryBean.list}">
                <h:graphicImage title="#{msg.entityActions_run}" value="/img/run.png" styleClass="titleImg"/>
            </h:commandLink>
        </i:titleBar>

        <%-- Search form --%>
        <table class="searchTable" cellpadding="0" cellspacing="0">


                <%-- Ignored field: id --%>


                <%-- Field: name --%>
            <tr>
                <td class="searchLabel">${msg['bulletinBoardCategory.name']}:</td>
                <td class="searchFieldCell">


                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="name"/>
                        <h:selectBooleanCheckbox id="nameValid" value="#{bulletinBoardCategoryBean.searchNameValid}"
                                                 onclick="setEnabled('search:name',this.checked)"/>
                        <h:inputText id="name" value="#{bulletinBoardCategoryBean.searchName}" size="70"/>
                    </h:panelGroup>
                    <script>
                        setEnabled('search:name', ${bulletinBoardCategoryBean.searchNameValid})
                    </script>


                </td>
            </tr>

                <%-- Field: numMessages --%>
            <tr>
                <td class="searchLabel">${msg['bulletinBoardCategory.numMessages']}:</td>
                <td class="searchFieldCell">


                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="startNumMessages"/>
                        <h:selectBooleanCheckbox id="startNumMessagesValid"
                                                 value="#{bulletinBoardCategoryBean.searchStartNumMessagesValid}"
                                                 onclick="setEnabled('search:startNumMessages',this.checked)"/>
                        <h:inputText id="startNumMessages" value="#{bulletinBoardCategoryBean.searchStartNumMessages}"
                                     size="31"/>
                    </h:panelGroup>
                    -
                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="endNumMessages"/>
                        <h:selectBooleanCheckbox id="endNumMessagesValid"
                                                 value="#{bulletinBoardCategoryBean.searchEndNumMessagesValid}"
                                                 onclick="setEnabled('search:endNumMessages',this.checked)"/>
                        <h:inputText id="endNumMessages" value="#{bulletinBoardCategoryBean.searchEndNumMessages}"
                                     size="31"/>
                    </h:panelGroup>
                    <script>
                        setEnabled('search:startNumMessages', ${bulletinBoardCategoryBean.searchStartNumMessagesValid})
                        setEnabled('search:endNumMessages', ${bulletinBoardCategoryBean.searchEndNumMessagesValid})
                    </script>


                </td>
            </tr>

                <%-- Ignored field: ownerId --%>


                <%-- Ignored field: departmentId --%>


                <%-- Ignored field: insertDate --%>


                <%-- Ignored field: updateDate --%>


        </table>

    </h:form>
</f:view>

</body>
</html>  		
