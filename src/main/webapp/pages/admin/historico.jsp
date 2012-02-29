<%@page language="java" contentType="text/html; charset=UTF-8" %>

<%@include file="/inc/tlibs.jsp" %>

<html>
<head>
    <%@include file="/inc/uiCore.jsp" %>
</head>
<body>

<f:loadBundle basename="com.autentia.intra.resources.messages" var="msg"/>
<i:location place="historico" msg="${msg}"/>

<f:view>
    <%--<%@include file="/inc/header.jsp" %>--%>
    <h:form id="historico" enctype="multipart/form-data">

        <%-- Header --%>
        <i:titleBar name="Histórico" msg="${msg}"/>

        <%-- List of historicos --%>
        <t:dataTable id="historicos" var="item" value="#{historicoBean.historicos}" preserveDataModel="false"
                     cellpadding="0" cellspacing="0" styleClass="detailListTable"
                     headerClass="detailListHeaderCell" footerClass="detailListFooter"
                     rowClasses="detailListRowO,detailListRowE"
                     columnClasses="listCmdCell,detailListProjectCostName,detailListProjectCostCost,detailListProjectCostBillable">

            <h:column>
                <f:facet name="header">
                    <h:outputText value="#{msg['historico.usuario']}" styleClass="detailListHeader"/>
                </f:facet>
                <h:outputText value="#{item.usuario.name}">
                    <f:converter converterId="autentia.EntityConverter"/>
                </h:outputText>
            </h:column>

            <h:column>
                <f:facet name="header">
                    <h:outputText value="#{msg['historico.fechaHora']}" styleClass="detailListHeader"/>
                </f:facet>
                <h:outputText value="#{item.fechaHora}">
                    <f:convertDateTime pattern="dd/MM/yy HH:mm" timeZone="Europe/Madrid"/>
                </h:outputText>
            </h:column>

            <h:column>
                <f:facet name="header">
                    <h:outputText value="#{msg['historico.tipoCambio']}" styleClass="detailListHeader"/>
                </f:facet>
                <h:outputText value="#{item.tipoCambio}"/>
            </h:column>

        </t:dataTable>

    </h:form>
</f:view>

</body>
</html>
