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
<i:location place="editBook" msg="${msg}"/>

<f:view>
    <%@include file="/inc/header.jsp" %>
    <h:form id="book" enctype="multipart/form-data">

        <%-- Header --%>
        <i:titleBar name="${bookBean.name}" msg="${msg}">
            <h:commandLink action="#{bookBean.save}">
                <h:graphicImage value="/img/save.gif" styleClass="titleImg"/>
            </h:commandLink>
            <h:commandLink action="#{bookBean.delete}"
                           onclick="if( !confirm('#{msg['question.confirmDelete']}') ) return false;">
                <h:graphicImage value="/img/delete.gif" styleClass="titleImg"/>
            </h:commandLink>
            <h:commandLink action="#{bookBean.list}" immediate="true">
                <h:graphicImage value="/img/back.gif" styleClass="titleImg"/>
            </h:commandLink>
        </i:titleBar>

        <%-- Edition form --%>
        <table class="editBook" cellpadding="0" cellspacing="0">


                <%-- book - generated by stajanov (do not edit/delete) --%>


                <%-- Ignored field: id --%>


                <%-- Field: name --%>
            <tr>
                <td class="editLabelRW">*${msg['book.name']}:</td>

                <td class="editFieldCell">

                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="name"/>
                        <h:inputText id="name" value="#{bookBean.name}" maxlength="255" size="75" required="true"
                                     styleClass="requiredFieldClass"/>
                    </h:panelGroup>

                </td>
            </tr>


                <%-- Field: author --%>
            <tr>
                <td class="editLabelRW">${msg['book.author']}:</td>

                <td class="editFieldCell">

                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="author"/>
                        <h:inputText id="author" value="#{bookBean.author}" maxlength="255" size="75"/>
                    </h:panelGroup>

                </td>
            </tr>


                <%-- Field: ISBN --%>
            <tr>
                <td class="editLabelRW">${msg['book.ISBN']}:</td>

                <td class="editFieldCell">

                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="ISBN"/>
                        <h:inputText id="ISBN" value="#{bookBean.ISBN}" maxlength="13"/>
                    </h:panelGroup>

                </td>
            </tr>


                <%-- Field: URL --%>
            <tr>
                <td class="editLabelRW">${msg['book.URL']}:</td>

                <td class="editFieldCell">

                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="URL"/>
                        <h:inputText id="URL" value="#{bookBean.URL}" maxlength="255" size="75"/>
                    </h:panelGroup>

                </td>
            </tr>


                <%-- Field: price --%>
            <tr>
                <td class="editLabelRW">${msg['book.price']}:</td>

                <td class="editFieldCell">

                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="price"/>
                        <h:inputText id="price" value="#{bookBean.price}" size="10" maxlength="11">
                            <f:validator validatorId="autentia.genericMoneyValidator"/>
                            <f:attribute name="scale" value="2"/>
                            <f:attribute name="maxSize" value="10"/>
                        </h:inputText>
                    </h:panelGroup>

                </td>
            </tr>


                <%-- Field: purchaseDate --%>
            <tr>
                <td class="editLabelRW">${msg['book.purchaseDate']}:</td>

                <td class="editFieldCell">

                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="purchaseDate"/>
                        <t:inputCalendar id="purchaseDate" value="#{bookBean.purchaseDate}"
                                         renderAsPopup="true" popupDateFormat="d/MM/yyyy"
                                         renderPopupButtonAsImage="true"
                                         popupTodayString="#{msg['calendar.today']}"
                                         popupWeekString="#{msg['calendar.week']}"
                                />
                    </h:panelGroup>

                </td>
            </tr>


                <%-- Ignored field: ownerId --%>


                <%-- Ignored field: departmentId --%>


                <%-- Ignored field: insertDate --%>


                <%-- Ignored field: updateDate --%>


                <%-- Field: lentTo --%>
            <tr>
                <td class="editLabelRW">${msg['book.lentTo']}:</td>

                <td class="editFieldCell">

                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="lentTo"/>
                        <h:selectOneMenu id="lentTo" value="#{bookBean.lentTo}">
                            <f:selectItems value="#{bookBean.lentTos}"/>
                            <f:converter converterId="autentia.EntityConverter"/>
                        </h:selectOneMenu>
                    </h:panelGroup>

                </td>
            </tr>


                <%-- book - generated by stajanov (do not edit/delete) --%>


        </table>

    </h:form>
</f:view>

</body>
</html>  		