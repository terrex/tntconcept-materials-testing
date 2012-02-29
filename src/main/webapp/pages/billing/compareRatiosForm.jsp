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
<i:location place="compareFinancialRatio" msg="${msg}"/>

<f:view>
    <%@include file="/inc/header.jsp" %>
    <h:form id="financialRatioCompare" enctype="multipart/form-data">

        <%-- Header --%>
        <i:titleBar name="${financialRatioBean.title}" msg="${msg}">
            <h:commandLink action="#{financialRatioBean.detail}" immediate="true">
                <h:graphicImage value="/img/back.gif" styleClass="titleImg"/>
                <f:param name="id" value="#{financialRatioBean.id}"/>
            </h:commandLink>
            <h:commandLink action="#{financialRatioBean.compareRatios}">
                <h:graphicImage title="#{msg.entityActions_run}" value="/img/run.png" styleClass="titleImg"/>
            </h:commandLink>
        </i:titleBar>

        <%-- Edition form --%>
        <table class="editTable" cellpadding="0" cellspacing="0">


                <%-- Ignored field: id --%>


                <%-- Field: title --%>
            <tr>
                <td class="detailLabelRatios">${msg['financialRatio.title']}:</td>
                <td class="detailFieldCell">
                    <h:outputText value="#{financialRatioBean.title}"/>
                </td>
            </tr>


                <%-- Field: ratioDate --%>
            <tr>
                <td class="detailLabelRatios">${msg['financialRatio.ratioDate']}:</td>
                <td class="detailFieldCell">
                    <h:outputText value="#{financialRatioBean.ratioDate}" converter="autentia.dateConverter"/>
                </td>
            </tr>

            <tr>
                <td class="editLabelRW">*${msg['financialRatio.financialRatio1']}:</td>
                <td class="editFieldCell">

                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false"
                                   for="financialRatioCompOne"/>
                        <h:selectOneMenu id="financialRatioCompOne" converter="autentia.EntityConverter"
                                         value="#{financialRatioBean.financialRatioCompOne}" required="true"
                                         styleClass="requiredFieldClass">
                            <f:selectItems value="#{financialRatioBean.financialRatios}"/>
                        </h:selectOneMenu>
                    </h:panelGroup>


                </td>
            </tr>

            <tr>
                <td class="editLabelRW">${msg['financialRatio.financialRatio2']}:</td>
                <td class="editFieldCell">

                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false"
                                   for="financialRatioCompTwo"/>
                        <h:selectOneMenu id="financialRatioCompTwo" converter="autentia.EntityConverter"
                                         value="#{financialRatioBean.financialRatioCompTwo}" required="false"
                                         styleClass="requiredFieldClass">
                            <f:selectItems value="#{financialRatioBean.financialRatiosWithNull}"/>
                        </h:selectOneMenu>
                    </h:panelGroup>


                </td>
            </tr>


        </table>

    </h:form>
</f:view>

</body>
</html>  	
