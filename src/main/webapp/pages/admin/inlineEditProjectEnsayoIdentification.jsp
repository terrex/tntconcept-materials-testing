<%@page language="java" contentType="text/html; charset=UTF-8" %>

<%@include file="/inc/tlibs.jsp" %>

<%@include file="/inc/uiCore.jsp" %>

<%-- List of ensayos --%>
<t:dataTable id="identificacionesCliente" var="item" value="#{projectEnsayoBean.projectEnsayo.referenciasCliente}"
             preserveDataModel="false"
             cellpadding="0" cellspacing="0" styleClass="editListTable"
             headerClass="editListHeaderCell" footerClass="editListFooter"
             rowClasses="editListRowO,editListRowE"
             columnClasses="listCmdCell,editListEnsayoProjectEnsayo">

    <%-- Commands --%>
    <h:column>
        <f:facet name="header">
            <t:commandLink action="#{projectEnsayoBean.createIdentificacionCliente}">
                <h:graphicImage title="#{msg.entityActions_new}" value="/img/new.gif" styleClass="cmdImg"/>
            </t:commandLink>
        </f:facet>
        <t:commandLink action="#{projectEnsayoBean.deleteIdentificacionCliente}"
                       onclick="if( !confirm('#{msg['question.confirmDelete']}') ) return false;">
            <h:graphicImage title="#{msg.entityActions_delete}" value="/img/delete.gif" styleClass="cmdImg"/>
        </t:commandLink>
    </h:column>


    <%-- Ignored field: id --%>

    <%-- Field: ensayo --%>
    <h:column>

        <f:facet name="header">
            <h:outputText value="*#{msg['identificacionCliente.name']}" styleClass="editListHeader"/>
        </f:facet>

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="name"/>
            <h:inputText id="name" value="#{item.name}" size="40"/>
        </h:panelGroup>

    </h:column>


    <h:column>

        <f:facet name="header">
            <h:outputText value="*#{msg['identificacionCliente.valor']}" styleClass="editListHeader"/>
        </f:facet>

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="valor"/>
            <h:inputText id="valor" value="#{item.valor}" size="40"/>
        </h:panelGroup>

    </h:column>

    <%-- Ignored field: ownerId --%>


    <%-- Ignored field: departmentId --%>


    <%-- Ignored field: insertDate --%>


    <%-- Ignored field: updateDate --%>


    <%-- Ignored field: pauta --%>


</t:dataTable>

