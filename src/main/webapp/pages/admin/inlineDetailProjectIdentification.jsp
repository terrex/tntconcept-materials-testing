<%@page language="java" contentType="text/html; charset=UTF-8" %>

<%@include file="/inc/tlibs.jsp" %>

<%@include file="/inc/uiCore.jsp" %>

<%-- List of identificacionesCliente --%>
<t:dataTable id="identificacionesCliente" var="item" value="#{projectBean.project.referenciasCliente}"
             preserveDataModel="false"
             cellpadding="0" cellspacing="0" styleClass="detailListTable"
             headerClass="detailListHeaderCell" footerClass="detailListFooter"
             rowClasses="detailListRowO,detailListRowE"
             columnClasses="listCmdCell,detailListProjectRoleName,detailListProjectRoleCostPerHour,detailListProjectRoleExpectedHours">


    <%-- Ignored field: id --%>


    <h:column>

        <f:facet name="header">
            <h:outputText value="#{msg['identificacionCliente.name']}" styleClass="detailListHeader"/>
        </f:facet>

        <%-- Field: name --%>

        <h:outputText value="#{item.name}"/>

    </h:column>


    <h:column>

        <f:facet name="header">
            <h:outputText value="#{msg['identificacionCliente.valor']}" styleClass="detailListHeader"/>
        </f:facet>

        <%-- Field: valor --%>

        <h:outputText value="#{item.valor}"/>

    </h:column>


    <%-- Ignored field: ownerId --%>


    <%-- Ignored field: departmentId --%>


    <%-- Ignored field: insertDate --%>


    <%-- Ignored field: updateDate --%>


    <%-- Ignored field: project --%>


</t:dataTable>