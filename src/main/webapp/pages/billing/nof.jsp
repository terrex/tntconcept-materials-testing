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
<i:location place="nof" msg="${msg}"/>

<f:view>
<%@include file="/inc/header.jsp" %>
<h:form id="nof">

<%-- Header --%>
<i:titleBar msg="${msg}">
</i:titleBar>

<%-- Dates --%>
<h:panelGrid columns="2" cellpadding="0" cellspacing="0" styleClass="editTable"
             columnClasses="editLabelRW,editFieldCell">
    <h:outputText value="#{msg['nof.actualDate']}:"/>
    <h:outputText value="#{nofBean.actualDate }">
        <f:convertDateTime pattern="dd/MM/yyyy" type="date"/>
    </h:outputText>

    <h:outputText value="#{msg['nof.endDate']}:"/>
    <t:inputCalendar id="endDate" value="#{nofBean.endDate}" required="true" styleClass="requiredFieldClass"
                     renderAsPopup="true" popupDateFormat="dd/MM/yyyy" renderPopupButtonAsImage="true"
                     onchange="submit()"
                     popupTodayString="#{msg['calendar.today']}" popupWeekString="#{msg['calendar.week']}"/>
</h:panelGrid>

<t:dataTable id="list" var="bill" value="#{nofBean.allPrevisionIncomes}"
             cellpadding="0" cellspacing="0" styleClass="listTable"
             headerClass="listHeaderCell" footerClass="listFooter"
             rows="#{settingBean.mySettings.listSize}" rowClasses="listRowO,listRowE" preserveDataModel="falses"
             columnClasses="listBillNOFExpirationDate,listBillNOFNumber,listBillNOFProvider,listBillNOFName,listBillNOFAmount"
             sortColumn="#{nofBean.sortColumnPrevisionIncomes}"
             sortAscending="#{nofBean.sortAscendingPrevisionIncomes}">

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
                <f:verbatim>${msg['nof.expirationDate']}</f:verbatim>
            </t:commandSortHeader>
        </f:facet>
        <%-- Date field --%>
        <h:outputText value="#{bill.creationDate}" converter="autentia.dateConverter"/>
    </h:column>

    <%-- Field: number --%>
    <h:column>
        <f:facet name="header">
            <t:commandSortHeader styleClass="listHeader" columnName="number">
                <f:facet name="ascending">
                    <t:graphicImage value="/img/ascending-arrow.gif" border="0"/>
                </f:facet>
                <f:facet name="descending">
                    <t:graphicImage value="/img/descending-arrow.gif" border="0"/>
                </f:facet>
                <f:verbatim>${msg['nof.number']}</f:verbatim>
            </t:commandSortHeader>
        </f:facet>
        <%-- String field --%>
        <h:outputText value="#{bill.number}"/>
    </h:column>

    <%-- Field: provider --%>
    <h:column>
        <f:facet name="header">
            <t:commandSortHeader styleClass="listHeader" columnName="provider">
                <f:facet name="ascending">
                    <t:graphicImage value="/img/ascending-arrow.gif" border="0"/>
                </f:facet>
                <f:facet name="descending">
                    <t:graphicImage value="/img/descending-arrow.gif" border="0"/>
                </f:facet>
                <f:verbatim>${msg['nof.company']}</f:verbatim>
            </t:commandSortHeader>
        </f:facet>
        <%-- Referenced field --%>
        <h:outputText value="#{bill.provider.name}"/>
    </h:column>

    <%-- Field: name --%>
    <h:column>
        <f:facet name="header">
            <t:commandSortHeader styleClass="listHeader" columnName="name">
                <f:facet name="ascending">
                    <t:graphicImage value="/img/ascending-arrow.gif" border="0"/>
                </f:facet>
                <f:facet name="descending">
                    <t:graphicImage value="/img/descending-arrow.gif" border="0"/>
                </f:facet>
                <f:verbatim>${msg['bill.name']}</f:verbatim>
            </t:commandSortHeader>
        </f:facet>
        <%-- String field --%>
        <h:outputText value="#{bill.name}"/>
    </h:column>

    <%-- Field: total --%>
    <h:column>
        <f:facet name="header">
            <t:commandSortHeader styleClass="listHeader" columnName="amount">
                <f:facet name="ascending">
                    <t:graphicImage value="/img/ascending-arrow.gif" border="0"/>
                </f:facet>
                <f:facet name="descending">
                    <t:graphicImage value="/img/descending-arrow.gif" border="0"/>
                </f:facet>
                <f:verbatim>${msg['bill.total']}</f:verbatim>
            </t:commandSortHeader>
        </f:facet>
        <%-- String field --%>
        <h:outputText value="#{bill.total}" styleClass="b"/>
    </h:column>

</t:dataTable>

<%-- Total incomes --%>
<t:panelGrid styleClass="listTable" columnClasses="listHeader">
    <h:panelGroup>
        <f:verbatim>&nbsp;${msg['nof.totalPrevisionIncomes']}:</f:verbatim>
        <f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
        <h:outputText value="#{nofBean.totalPrevisionIncomes}"/>
    </h:panelGroup>
</t:panelGrid>

<%-- Paginator control --%>
<%@include file="/inc/paginator.jsp" %>

<t:dataTable id="list2" var="periodicalAccountEntry" value="#{nofBean.allPeriodicalAccountEntry}"
             preserveDataModel="false"
             cellpadding="0" cellspacing="0" styleClass="listTable"
             headerClass="listHeaderCell" footerClass="listFooter"
             rows="#{settingBean.mySettings.listSize}" rowClasses="listRowO,listRowE"
             columnClasses="listPeriodicalAccountNOFDate,listPeriodicalAccountNOFConcept,listPeriodicalAccountNOFType,
	            			   listPeriodicalAccountNOFFrequency,listPeriodicalAccountNOFAmount"
             sortColumn="#{nofBean.sortColumnPeriodicalAccount}"
             sortAscending="#{nofBean.sortAscendingPeriodicalAccount}">

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
                <f:verbatim>${msg['nof.periodicalAccountDate']}</f:verbatim>
            </t:commandSortHeader>
        </f:facet>
        <%-- Date field --%>
        <h:outputText value="#{periodicalAccountEntry.date}" converter="autentia.dateConverter"/>
    </h:column>

    <%-- Field: concept --%>
    <h:column>
        <f:facet name="header">
            <t:commandSortHeader styleClass="listHeader" columnName="concept">
                <f:facet name="ascending">
                    <t:graphicImage value="/img/ascending-arrow.gif" border="0"/>
                </f:facet>
                <f:facet name="descending">
                    <t:graphicImage value="/img/descending-arrow.gif" border="0"/>
                </f:facet>
                <f:verbatim>${msg['periodicalAccountEntry.concept']}</f:verbatim>
            </t:commandSortHeader>
        </f:facet>
        <%-- String field --%>
        <h:outputText value="#{periodicalAccountEntry.concept}"/>
    </h:column>

    <%-- Field: type --%>
    <h:column>
        <f:facet name="header">
            <t:commandSortHeader styleClass="listHeader" columnName="type">
                <f:facet name="ascending">
                    <t:graphicImage value="/img/ascending-arrow.gif" border="0"/>
                </f:facet>
                <f:facet name="descending">
                    <t:graphicImage value="/img/descending-arrow.gif" border="0"/>
                </f:facet>
                <f:verbatim>${msg['periodicalAccountEntry.type']}</f:verbatim>
            </t:commandSortHeader>
        </f:facet>
        <%-- Referenced field --%>
        <h:outputText value="#{periodicalAccountEntry.type.name}"/>
    </h:column>

    <%-- Field: frequency --%>
    <h:column>
        <f:facet name="header">
            <t:commandSortHeader styleClass="listHeader" columnName="frequency">
                <f:facet name="ascending">
                    <t:graphicImage value="/img/ascending-arrow.gif" border="0"/>
                </f:facet>
                <f:facet name="descending">
                    <t:graphicImage value="/img/descending-arrow.gif" border="0"/>
                </f:facet>
                <f:verbatim>${msg['periodicalAccountEntry.frequency']}</f:verbatim>
            </t:commandSortHeader>
        </f:facet>
        <%-- Referenced field --%>
        <h:outputText value="#{periodicalAccountEntry.frequency.name}"/>
    </h:column>

    <%-- Field: amount --%>
    <h:column>
        <f:facet name="header">
            <t:commandSortHeader styleClass="listHeader" columnName="amount">
                <f:facet name="ascending">
                    <t:graphicImage value="/img/ascending-arrow.gif" border="0"/>
                </f:facet>
                <f:facet name="descending">
                    <t:graphicImage value="/img/descending-arrow.gif" border="0"/>
                </f:facet>
                <f:verbatim>${msg['periodicalAccountEntry.amount']}</f:verbatim>
            </t:commandSortHeader>
        </f:facet>
        <%-- String field --%>
        <h:outputText value="#{periodicalAccountEntry.amount}" styleClass="r"/>
    </h:column>
</t:dataTable>

<%-- Total periodical account --%>
<t:panelGrid styleClass="listTable" columnClasses="listHeader">
    <h:panelGroup>
        <f:verbatim>&nbsp;${msg['nof.totalPeriodicalAccount']}:</f:verbatim>
        <f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
        <h:outputText value="#{nofBean.totalPeriodicalAccount}"/>
    </h:panelGroup>
</t:panelGrid>

<%-- Paginator control --%>
<t:dataScroller for="list2" fastStep="10" paginator="true"
                paginatorMaxPages="10" renderFacetsIfSinglePage="false"
                styleClass="scroller" paginatorTableClass="paginator"
                paginatorColumnClass="paginatorCell"
                paginatorActiveColumnClass="paginatorActiveCell">

    <f:facet name="first">
        <t:graphicImage value="/img/arrow-first.gif" border="0"/>
    </f:facet>
    <f:facet name="last">
        <t:graphicImage value="/img/arrow-last.gif" border="0"/>
    </f:facet>
    <f:facet name="previous">
        <t:graphicImage value="/img/arrow-previous.gif" border="0"/>
    </f:facet>
    <f:facet name="next">
        <t:graphicImage value="/img/arrow-next.gif" border="0"/>
    </f:facet>
    <f:facet name="fastrewind">
        <t:graphicImage value="/img/arrow-fr.gif" border="0"/>
    </f:facet>
    <f:facet name="fastforward">
        <t:graphicImage value="/img/arrow-ff.gif" border="0"/>
    </f:facet>
</t:dataScroller>

<%-- Dates --%>
<h:panelGrid columns="2" cellpadding="0" cellspacing="0" styleClass="editTable"
             columnClasses="editLabelRW,editFieldCell">
    <h:outputText value="#{msg['nof.difference']}:"/>
    <h:outputText id="difference" value="#{nofBean.totalPeriodicalAccount + nofBean.totalPrevisionIncomes}"
                  styleClass="#{(nofBean.totalPeriodicalAccount + nofBean.totalPrevisionIncomes) > 0 ? 'b' : 'r'}">
    </h:outputText>
</h:panelGrid>

</h:form>
</f:view>
</body>
</html>  		