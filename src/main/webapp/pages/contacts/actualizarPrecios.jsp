<%@page language="java" contentType="text/html; charset=UTF-8" %>

<%@include file="/inc/tlibs.jsp" %>

<html>
<head>
    <%@include file="/inc/uiCore.jsp" %>
</head>
<body>

<f:loadBundle basename="com.autentia.intra.resources.messages" var="msg"/>
<i:location place="actualizarPrecios" msg="${msg}"/>

<f:view>
    <%@include file="/inc/header.jsp" %>
    <%-- Report arguments user interface --%>
    <h:form id="actualizarPrecios">

        <%-- Header --%>
        <i:titleBar msg="${msg}">
            <h:commandLink action="#{actualizarPreciosBean.run}" title="#{msg['actualizarPrecios.actualizar']}">
                <h:graphicImage value="/img/run.png" styleClass="titleImg"/>
            </h:commandLink>
        </i:titleBar>
        <h:messages layout="table"/>

        <%-- Fixed Arguments --%>
        <h:panelGrid columns="2" cellpadding="0" cellspacing="0" styleClass="editTable"
                     columnClasses="editLabelRW,editFieldCell">

            <h:outputText value="*#{msg['actualizarPrecios.actualizar']}:"/>
            <h:panelGroup>
                <h:message styleClass="error" showSummary="true" showDetail="false" for="actualizar"/>
                <t:selectManyCheckbox id="actualizar" title="Elegir pautas y/o ensayos"
                                      layout="pageDirection" value="#{actualizarPreciosBean.actualizar}"
                                      required="true">
                    <f:selectItem itemLabel="Ensayos" itemValue="ensayos"/>
                    <f:selectItem itemLabel="Pautas" itemValue="pautas"/>
                </t:selectManyCheckbox>
            </h:panelGroup>

            <h:outputText value="#{msg['actualizarPrecios.clientes']}:"/>
            <h:panelGroup>
                <h:message styleClass="error" showSummary="true" showDetail="false" for="clientes"/>
                <h:selectManyCheckbox id="clientes" value="#{actualizarPreciosBean.clientes}"
                                      layout="pageDirection" converter="autentia.EntityConverter">
                    <f:selectItems value="#{actualizarPreciosBean.todosClientes}"/>
                    <f:converter converterId="autentia.EntityConverter"/>
                    <f:attribute name="com.autentia.intra.converter.EntityConverter.valueClass"
                                 value="com.autentia.intra.businessobject.Organization"/>
                </h:selectManyCheckbox>
            </h:panelGroup>

            <h:outputText value="*#{msg['actualizarPrecios.factor']}:"/>
            <h:panelGroup>
                <h:message styleClass="error" showSummary="true" showDetail="false" for="factor"/>
                <h:inputText id="factor" value="#{actualizarPreciosBean.factor}" size="20" required="true"/>
            </h:panelGroup>

        </h:panelGrid>
    </h:form>
</f:view>
</body>
</html>









