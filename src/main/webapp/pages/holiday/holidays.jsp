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

<!-- holidays.jsp: generated by stajanov code generator -->
<f:loadBundle basename="com.autentia.intra.resources.messages" var="msg"/>
<i:location place="holidays" msg="${msg}"/>

<f:view>
    <%@include file="/inc/header.jsp" %>
    <h:form id="holidays">

        <%-- Header --%>
        <i:titleBar msg="${msg}">
            <h:commandLink action="#{holidayBean.search}">
                <h:graphicImage rendered="#{ holidayBean.search.searchActive}" title="#{msg.entityActions_filtered}"
                                value="/img/search_applied.gif" styleClass="titleImg"/>
                <h:graphicImage rendered="#{!holidayBean.search.searchActive}" title="#{msg.entityActions_search}"
                                value="/img/search.gif" styleClass="titleImg"/>
            </h:commandLink>

            <h:commandLink action="#{holidayBean.reset}">
                <h:graphicImage title="#{msg.entityActions_reset}" rendered="#{ holidayBean.search.searchActive}"
                                value="/img/eraser.png" styleClass="titleImg"/>
            </h:commandLink>

            <h:commandLink action="#{holidayBean.create}" rendered="#{holidayBean.createAvailable}">
                <h:graphicImage title="#{msg.entityActions_create}" value="/img/new.gif" styleClass="titleImg"/>
            </h:commandLink>

        </i:titleBar>


        <%-- ABC quick pager --%>
        <div class="abcPager">
            <h:outputText value="#{msg['abcPager.title']}" styleClass="abcPagerTitle"/>
            <a:abcPager styleClass="abcPagerLetter" selectedStyleClass="abcPagerSelectedLetter"
                        value="#{holidayBean.letter}" action="#{holidayBean.letterClicked}"
                        allowUnselect="true" unselectImage="/img/no_funnel.png"/>
        </div>

        <h:panelGrid columns="3" cellpadding="0" cellspacing="0" styleClass="editTable"
                     columnClasses="editLabelRW,editFieldCellGlobal,editFieldCellGlobal">
            <h:outputText value="#{msg['holiday.selectedYear']}:"/>
            <t:inputCalendar id="startDate" value="#{holidayBean.selectedYear}" styleClass="requiredFieldClass"
                             renderAsPopup="true" popupDateFormat="yyyy" renderPopupButtonAsImage="true"
                             popupTodayString="#{msg['calendar.today']}" popupWeekString="#{msg['calendar.week']}"/>

            <h:commandButton action="#" title="#{msg['holiday.show']}" value="#{msg['holiday.show']}"/>
        </h:panelGrid>


        <%-- List of holidays --%>
        <t:dataTable id="list" var="holiday" value="#{holidayBean.all}" preserveDataModel="false"
                     cellpadding="0" cellspacing="0" styleClass="listTable"
                     headerClass="listHeaderCell" footerClass="listFooter"
                     rows="#{settingBean.mySettings.listSize}" rowClasses="listRowO,listRowE"
                     columnClasses="listCmdCell,listHolidayDescription,listHolidayDate"
                     sortColumn="#{holidayBean.sortColumn}" sortAscending="#{holidayBean.sortAscending}"
                     rowOnMouseOver="this.savedClassName=this.className;this.className='listRowSel';"
                     rowOnMouseOut="this.className=this.savedClassName;">

            <%-- Commands --%>
            <h:column>
                <f:facet name="header">
                    <f:verbatim>-</f:verbatim>
                </f:facet>
                <t:commandLink action="#{holidayBean.detail}" immediate="true">
                    <f:param name="id" value="#{holiday.id}"/>
                    <h:graphicImage title="#{msg.entityActions_detail}" value="/img/detail.gif" styleClass="cmdImg"/>
                </t:commandLink>
            </h:column>


            <%-- Ignored field: id --%>


            <%-- Field: description --%>
            <h:column>
                <f:facet name="header">
                    <t:commandSortHeader styleClass="listHeader" columnName="description">
                        <f:facet name="ascending">
                            <t:graphicImage value="/img/ascending-arrow.gif" border="0"/>
                        </f:facet>
                        <f:facet name="descending">
                            <t:graphicImage value="/img/descending-arrow.gif" border="0"/>
                        </f:facet>
                        <f:verbatim>${msg['holiday.description']}</f:verbatim>
                    </t:commandSortHeader>
                </f:facet>

                <t:commandLink action="#{holidayBean.detail}" immediate="true">
                    <f:param name="id" value="#{holiday.id}"/>
                    <%-- String field --%>
                    <h:outputText value="#{holiday.description}"/>
                </t:commandLink>

            </h:column>


            <%-- Field: date --%>
            <h:column>
                <f:facet name="header">
                    <t:commandSortHeader styleClass="listHeader" columnName="date">
                        <f:facet name="ascending">
                            <t:graphicImage value="/img/ascending-arrow.gif" border="0"/>
                        </f:facet>
                        <f:facet name="descending">
                            <t:graphicImage value="/img/descending-arrow.gif" border="0"/>
                        </f:facet>
                        <f:verbatim>${msg['holiday.date']}</f:verbatim>
                    </t:commandSortHeader>
                </f:facet>

                <t:commandLink action="#{holidayBean.detail}" immediate="true">
                    <f:param name="id" value="#{holiday.id}"/>
                    <%-- Date field --%>
                    <h:outputText value="#{holiday.date}" converter="autentia.dateConverter"/>

                </t:commandLink>

            </h:column>


            <%-- Ignored field: ownerId --%>


            <%-- Ignored field: departmentId --%>


            <%-- Ignored field: insertDate --%>


            <%-- Ignored field: updateDate --%>

        </t:dataTable>

        <%-- Paginator control --%>
        <%@include file="/inc/paginator.jsp" %>

    </h:form>
</f:view>

</body>
</html>  		

