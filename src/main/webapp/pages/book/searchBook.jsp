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

<!-- searchBook.jsp: generated by stajanov code generator -->
<f:loadBundle basename="com.autentia.intra.resources.messages" var="msg"/>
<i:location place="searchBook" msg="${msg}"/>

<f:view>
    <%@include file="/inc/header.jsp" %>
    <h:form id="search" enctype="multipart/form-data">

        <%-- Header --%>
        <i:titleBar msg="${msg}">
            <h:commandLink action="#{bookBean.list}">
                <h:graphicImage title="#{msg.entityActions_run}" value="/img/run.png" styleClass="titleImg"/>
            </h:commandLink>
        </i:titleBar>

        <%-- Search form --%>
        <table class="searchTable" cellpadding="0" cellspacing="0">


                <%-- Ignored field: id --%>


                <%-- Field: name --%>
            <tr>
                <td class="searchLabel">${msg['book.name']}:</td>
                <td class="searchFieldCell">


                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="name"/>
                        <h:selectBooleanCheckbox id="nameValid" value="#{bookBean.searchNameValid}"
                                                 onclick="setEnabled('search:name',this.checked)"/>
                        <h:inputText id="name" value="#{bookBean.searchName}" size="70"/>
                    </h:panelGroup>
                    <script>
                        setEnabled('search:name', ${bookBean.searchNameValid})
                    </script>


                </td>
            </tr>

                <%-- Field: author --%>
            <tr>
                <td class="searchLabel">${msg['book.author']}:</td>
                <td class="searchFieldCell">


                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="author"/>
                        <h:selectBooleanCheckbox id="authorValid" value="#{bookBean.searchAuthorValid}"
                                                 onclick="setEnabled('search:author',this.checked)"/>
                        <h:inputText id="author" value="#{bookBean.searchAuthor}" size="70"/>
                    </h:panelGroup>
                    <script>
                        setEnabled('search:author', ${bookBean.searchAuthorValid})
                    </script>


                </td>
            </tr>

                <%-- Field: ISBN --%>
            <tr>
                <td class="searchLabel">${msg['book.ISBN']}:</td>
                <td class="searchFieldCell">


                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="ISBN"/>
                        <h:selectBooleanCheckbox id="ISBNValid" value="#{bookBean.searchISBNValid}"
                                                 onclick="setEnabled('search:ISBN',this.checked)"/>
                        <h:inputText id="ISBN" value="#{bookBean.searchISBN}" size="70"/>
                    </h:panelGroup>
                    <script>
                        setEnabled('search:ISBN', ${bookBean.searchISBNValid})
                    </script>


                </td>
            </tr>

                <%-- Field: URL --%>
            <tr>
                <td class="searchLabel">${msg['book.URL']}:</td>
                <td class="searchFieldCell">


                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="URL"/>
                        <h:selectBooleanCheckbox id="URLValid" value="#{bookBean.searchURLValid}"
                                                 onclick="setEnabled('search:URL',this.checked)"/>
                        <h:inputText id="URL" value="#{bookBean.searchURL}" size="70"/>
                    </h:panelGroup>
                    <script>
                        setEnabled('search:URL', ${bookBean.searchURLValid})
                    </script>


                </td>
            </tr>

                <%-- Field: price --%>
            <tr>
                <td class="searchLabel">${msg['book.price']}:</td>
                <td class="searchFieldCell">


                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="price"/>
                        <h:selectBooleanCheckbox id="priceValid" value="#{bookBean.searchPriceValid}"
                                                 onclick="setEnabled('search:price',this.checked)"/>
                        <h:inputText id="price" value="#{bookBean.searchPrice}" size="70"/>
                    </h:panelGroup>
                    <script>
                        setEnabled('search:price', ${bookBean.searchPriceValid})
                    </script>


                </td>
            </tr>

                <%-- Field: purchaseDate --%>
            <tr>
                <td class="searchLabel">${msg['book.purchaseDate']}:</td>
                <td class="searchFieldCell">


                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="startPurchaseDate"/>
                        <h:selectBooleanCheckbox id="startPurchaseDateValid"
                                                 value="#{bookBean.searchStartPurchaseDateValid}"
                                                 onclick="setEnabled('search:startPurchaseDate',this.checked)"/>
                        <t:inputCalendar id="startPurchaseDate" value="#{bookBean.searchStartPurchaseDate}"
                                         renderAsPopup="true" popupDateFormat="d/MM/yyyy"
                                         renderPopupButtonAsImage="true"
                                         popupTodayString="#{msg['calendar.today']}"
                                         popupWeekString="#{msg['calendar.week']}"
                                         onchange="setEnabled('search:startPurchaseDate',true);setChecked('search:startPurchaseDateValid',true);"
                                />
                    </h:panelGroup>
                    -
                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="endPurchaseDate"/>
                        <h:selectBooleanCheckbox id="endPurchaseDateValid"
                                                 value="#{bookBean.searchEndPurchaseDateValid}"
                                                 onclick="setEnabled('search:endPurchaseDate',this.checked)"/>
                        <t:inputCalendar id="endPurchaseDate" value="#{bookBean.searchEndPurchaseDate}"
                                         renderAsPopup="true" popupDateFormat="d/MM/yyyy"
                                         renderPopupButtonAsImage="true"
                                         popupTodayString="#{msg['calendar.today']}"
                                         popupWeekString="#{msg['calendar.week']}"
                                         onchange="setEnabled('search:endPurchaseDate',true);setChecked('search:endPurchaseDateValid',true);"
                                />
                    </h:panelGroup>
                    <script>
                        setEnabled('search:startPurchaseDate', ${bookBean.searchStartPurchaseDateValid})
                        setEnabled('search:endPurchaseDate', ${bookBean.searchEndPurchaseDateValid})
                    </script>


                </td>
            </tr>

                <%-- Ignored field: ownerId --%>


                <%-- Ignored field: departmentId --%>


                <%-- Ignored field: insertDate --%>


                <%-- Ignored field: updateDate --%>


                <%-- Field: lentTo --%>
            <tr>
                <td class="searchLabel">${msg['book.lentTo']}:</td>
                <td class="searchFieldCell">


                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="lentTo"/>
                        <h:selectBooleanCheckbox id="lentToValid" value="#{bookBean.searchLentToValid}"
                                                 onclick="setEnabled('search:lentTo',this.checked)"/>
                        <h:selectOneMenu id="lentTo" value="#{bookBean.searchLentTo}"
                                         onclick="setEnabled('search:lentTo',true)">
                            <f:selectItems value="#{bookBean.lentTos}"/>
                            <f:converter converterId="autentia.EntityConverter"/>
                        </h:selectOneMenu>
                    </h:panelGroup>
                    <script>
                        setEnabled('search:lentTo', ${bookBean.searchLentToValid})
                    </script>


                </td>
            </tr>

        </table>

    </h:form>
</f:view>

</body>
</html>  		
