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

<!-- financialRatios.jsp: generated by stajanov code generator -->
<f:loadBundle basename="com.autentia.intra.resources.messages" var="msg"/>
<i:location place="financialRatios" msg="${msg}"/>

<f:view>
<%@include file="/inc/header.jsp" %>
<h:form id="financialRatios">

<%-- Header --%>
<i:titleBar msg="${msg}">
    <h:commandLink action="#{financialRatioBean.search}">
        <h:graphicImage rendered="#{ financialRatioBean.search.searchActive}" title="#{msg.entityActions_filtered}"
                        value="/img/search_applied.gif" styleClass="titleImg"/>
        <h:graphicImage rendered="#{!financialRatioBean.search.searchActive}" title="#{msg.entityActions_search}"
                        value="/img/search.gif" styleClass="titleImg"/>
    </h:commandLink>
    <h:commandLink action="#{financialRatioBean.reset}">
        <h:graphicImage rendered="#{financialRatioBean.search.searchActive}" title="#{msg.entityActions_reset}"
                        value="/img/eraser.png" styleClass="titleImg"/>
    </h:commandLink>
    <h:commandLink action="#{financialRatioBean.create}" rendered="#{financialRatioBean.createAvailable}">
        <h:graphicImage title="#{msg.entityActions_create}" value="/img/new.gif" styleClass="titleImg"/>
    </h:commandLink>
</i:titleBar>


<%-- ABC quick pager --%>
<div class="abcPager">
    <h:outputText value="#{msg['abcPager.title']}" styleClass="abcPagerTitle"/>
    <a:abcPager styleClass="abcPagerLetter" selectedStyleClass="abcPagerSelectedLetter"
                value="#{financialRatioBean.letter}" action="#{financialRatioBean.letterClicked}"
                allowUnselect="true" unselectImage="/img/no_funnel.png"/>
</div>


<%-- List of financialRatios --%>
<t:dataTable id="list" var="financialRatio" value="#{financialRatioBean.all}" preserveDataModel="false"
             cellpadding="0" cellspacing="0" styleClass="listTable"
             headerClass="listHeaderCell" footerClass="listFooter"
             rows="#{settingBean.mySettings.listSize}" rowClasses="listRowO,listRowE"
             columnClasses="listCmdCell,listFinancialRatioTitle,listFinancialRatioRatioDate"
             sortColumn="#{financialRatioBean.sortColumn}" sortAscending="#{financialRatioBean.sortAscending}"
             rowOnMouseOver="this.savedClassName=this.className;this.className='listRowSel';"
             rowOnMouseOut="this.className=this.savedClassName;">

<%-- Commands --%>
<h:column>
    <f:facet name="header">
        <f:verbatim>-</f:verbatim>
    </f:facet>
    <t:commandLink action="#{financialRatioBean.detail}" immediate="true">
        <f:param name="id" value="#{financialRatio.id}"/>
        <h:graphicImage title="#{msg.entityActions_detail}" value="/img/detail.gif" styleClass="cmdImg"/>
    </t:commandLink>
</h:column>


<%-- Ignored field: id --%>


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
            <f:verbatim>${msg['financialRatio.title']}</f:verbatim>
        </t:commandSortHeader>
    </f:facet>

    <t:commandLink action="#{financialRatioBean.detail}" immediate="true">
        <f:param name="id" value="#{financialRatio.id}"/>
        <%-- String field --%>
        <h:outputText value="#{financialRatio.title}"/>
    </t:commandLink>

</h:column>


<%-- Field: ratioDate --%>
<h:column>
    <f:facet name="header">
        <t:commandSortHeader styleClass="listHeader" columnName="ratioDate">
            <f:facet name="ascending">
                <t:graphicImage value="/img/ascending-arrow.gif" border="0"/>
            </f:facet>
            <f:facet name="descending">
                <t:graphicImage value="/img/descending-arrow.gif" border="0"/>
            </f:facet>
            <f:verbatim>${msg['financialRatio.ratioDate']}</f:verbatim>
        </t:commandSortHeader>
    </f:facet>

    <t:commandLink action="#{financialRatioBean.detail}" immediate="true">
        <f:param name="id" value="#{financialRatio.id}"/>
        <%-- Date field --%>
        <h:outputText value="#{financialRatio.ratioDate}" converter="autentia.dateConverter"/>

    </t:commandLink>

</h:column>


<%-- Ignored field: banksAccounts --%>


<%-- Ignored field: customers --%>


<%-- Ignored field: stocks --%>


<%-- Ignored field: amortizations --%>


<%-- Ignored field: infrastructures --%>


<%-- Ignored field: shortTermLiability --%>


<%-- Ignored field: obligationBond --%>


<%-- Ignored field: capital --%>


<%-- Ignored field: reserves --%>


<%-- Ignored field: incomes --%>


<%-- Ignored field: expenses --%>


<%-- Ignored field: otherExploitationExpenses --%>


<%-- Ignored field: financialExpenses --%>


<%-- Ignored field: taxes --%>


<%-- Ignored field: netAsset --%>


<%-- Ignored field: totalAsset --%>


<%-- Ignored field: totalOtherResources --%>


<%-- Ignored field: totalOurResources --%>


<%-- Ignored field: totalLiability --%>


<%-- Ignored field: margin --%>


<%-- Ignored field: bait --%>


<%-- Ignored field: bat --%>


<%-- Ignored field: bdt --%>


<%-- Ignored field: baitd --%>


<%-- Ignored field: liquidity --%>


<%-- Ignored field: exchequer --%>


<%-- Ignored field: availability --%>


<%-- Ignored field: expensesCoverage --%>


<%-- Ignored field: workingCapital --%>


<%-- Ignored field: solvency --%>


<%-- Ignored field: debtRatio --%>


<%-- Ignored field: debtTotalLiability --%>


<%-- Ignored field: guarantee --%>


<%-- Ignored field: financeCoverage --%>


<%-- Ignored field: collectionPeriod --%>


<%-- Ignored field: payPeriod --%>


<%-- Ignored field: turnoverAsset --%>


<%-- Ignored field: turnoverPermanentAsset --%>


<%-- Ignored field: turnoverFM --%>


<%-- Ignored field: grossSpread --%>


<%-- Ignored field: baitSpread --%>


<%-- Ignored field: bdtSpread --%>


<%-- Ignored field: baitROA --%>


<%-- Ignored field: baitdROA --%>


<%-- Ignored field: ROE --%>


<%-- Ignored field: leveraging --%>


<%-- Ignored field: ROCE --%>


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

