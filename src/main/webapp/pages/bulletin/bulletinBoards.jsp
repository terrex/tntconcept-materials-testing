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

<!-- bulletinBoards.jsp: generated by stajanov code generator -->
<f:loadBundle basename="com.autentia.intra.resources.messages" var="msg"/>
<i:location place="bulletinBoards" msg="${msg}"/>

<f:view>
    <%@include file="/inc/header.jsp" %>
    <h:form id="bulletinBoards">

        <%-- Header --%>
        <i:titleBar msg="${msg}">
            <h:commandLink action="#{bulletinBoardBean.search}">
                <h:graphicImage rendered="#{ bulletinBoardBean.search.searchActive}"
                                title="#{msg.entityActions_filtered}"
                                value="/img/search_applied.gif" styleClass="titleImg"/>
                <h:graphicImage rendered="#{!bulletinBoardBean.search.searchActive}" title="#{msg.entityActions_search}"
                                value="/img/search.gif" styleClass="titleImg"/>
            </h:commandLink>
            <h:commandLink action="#{bulletinBoardBean.reset}">
                <h:graphicImage rendered="#{bulletinBoardBean.search.searchActive}" title="#{msg.entityActions_reset}"
                                value="/img/eraser.png" styleClass="titleImg"/>
            </h:commandLink>
            <h:commandLink action="#{bulletinBoardBean.create}">
                <h:graphicImage title="#{msg.entityActions_create}" value="/img/new.gif" styleClass="titleImg"/>
            </h:commandLink>
        </i:titleBar>


        <%-- ABC quick pager --%>
        <div class="abcPager">
            <h:outputText value="#{msg['abcPager.title']}" styleClass="abcPagerTitle"/>
            <a:abcPager styleClass="abcPagerLetter" selectedStyleClass="abcPagerSelectedLetter"
                        value="#{bulletinBoardBean.letter}" action="#{bulletinBoardBean.letterClicked}"
                        allowUnselect="true" unselectImage="/img/no_funnel.png"/>
        </div>

        <%-- List of bulletinBoards --%>
        <t:dataTable id="list" var="bulletinBoard" value="#{bulletinBoardBean.all}" preserveDataModel="false"
                     cellpadding="0" cellspacing="0" styleClass="listTable"
                     headerClass="listHeaderCell" footerClass="listFooter"
                     rows="#{settingBean.mySettings.listSize}" rowClasses="listRowO,listRowE"
                     columnClasses="listCmdCell,listBulletinBoardCreationDate,listBulletinBoardTitle,listBulletinBoardCategory,listBulletinBoardUser"
                     sortColumn="#{bulletinBoardBean.sortColumn}" sortAscending="#{bulletinBoardBean.sortAscending}">

            <%-- Commands --%>
            <h:column>
                <f:facet name="header">
                    <f:verbatim>-</f:verbatim>
                </f:facet>
                <t:commandLink action="#{bulletinBoardBean.detail}" immediate="true">
                    <f:param name="id" value="#{bulletinBoard.id}"/>
                    <h:graphicImage title="#{msg.entityActions_detail}" value="/img/detail.gif" styleClass="cmdImg"/>
                </t:commandLink>
            </h:column>


            <%-- Ignored field: id --%>


            <%-- Field: creationDate --%>
            <h:column>
                <f:facet name="header">
                    <t:commandSortHeader styleClass="listHeader" columnName="creationDate">
                        <f:facet name="ascending">
                            <t:graphicImage value="/img/ascending-arrow.gif" border="0"/>
                        </f:facet>
                        <f:facet name="descending">
                            <t:graphicImage value="/img/descending-arrow.gif" border="0"/>
                        </f:facet>
                        <f:verbatim>${msg['bulletinBoard.creationDate']}</f:verbatim>
                    </t:commandSortHeader>
                </f:facet>

                <%-- Date field --%>
                <h:outputText value="#{bulletinBoard.creationDate}" converter="autentia.dateConverter"/>


            </h:column>


            <%-- Field: title --%>
            <h:column>
                <f:facet name="header">
                    <t:commandSortHeader styleClass="listHeader" columnName="title">
                        <f:facet name="ascending">
                            <t:graphicImage value="/img/ascending-arrow.gif" border="0"/>
                        </f:facet>
                        <f:facet name="descending">
                            <t:graphicImage value="/img/descending-arrow.gif" border="0"/>
                        </f:facet>
                        <f:verbatim>${msg['bulletinBoard.title']}</f:verbatim>
                    </t:commandSortHeader>
                </f:facet>

                <%-- String field --%>
                <h:outputText value="#{bulletinBoard.title}"/>

            </h:column>


            <%-- Ignored field: message --%>


            <%-- Ignored field: documentPath --%>


            <%-- Ignored field: documentContentType --%>


            <%-- Field: category --%>
            <h:column>
                <f:facet name="header">
                    <t:commandSortHeader styleClass="listHeader" columnName="category.name">
                        <f:facet name="ascending">
                            <t:graphicImage value="/img/ascending-arrow.gif" border="0"/>
                        </f:facet>
                        <f:facet name="descending">
                            <t:graphicImage value="/img/descending-arrow.gif" border="0"/>
                        </f:facet>
                        <f:verbatim>${msg['bulletinBoard.category']}</f:verbatim>
                    </t:commandSortHeader>
                </f:facet>

                <%-- Referenced field --%>
                <h:outputText value="#{bulletinBoard.category.name}"/>


            </h:column>


            <%-- Field: user --%>
            <h:column>
                <f:facet name="header">
                    <t:commandSortHeader styleClass="listHeader" columnName="user.name">
                        <f:facet name="ascending">
                            <t:graphicImage value="/img/ascending-arrow.gif" border="0"/>
                        </f:facet>
                        <f:facet name="descending">
                            <t:graphicImage value="/img/descending-arrow.gif" border="0"/>
                        </f:facet>
                        <f:verbatim>${msg['bulletinBoard.user']}</f:verbatim>
                    </t:commandSortHeader>
                </f:facet>

                <%-- Referenced field --%>
                <h:outputText value="#{bulletinBoard.user.name}"/>


            </h:column>

        </t:dataTable>

        <%-- Paginator control --%>
        <%@include file="/inc/paginator.jsp" %>

    </h:form>
</f:view>

</body>
</html>  		

