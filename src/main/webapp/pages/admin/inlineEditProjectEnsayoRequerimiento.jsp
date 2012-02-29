<%@page language="java" contentType="text/html; charset=UTF-8" %>

<%@include file="/inc/tlibs.jsp" %>

<%@include file="/inc/uiCore.jsp" %>

<%-- List of ensayos --%>
<t:dataTable id="requerimientos" var="item" value="#{projectEnsayoBean.requerimientos}" preserveDataModel="false"
             cellpadding="0" cellspacing="0" styleClass="editListTable"
             headerClass="editListHeaderCell" footerClass="editListFooter"
             rowClasses="editListRowO,editListRowE"
             columnClasses="listCmdCell,editListEnsayoProject">

    <%-- Commands --%>
    <h:column>
        <f:facet name="header">
            <t:commandLink action="#{projectEnsayoBean.createRequerimiento}">
                <h:graphicImage title="#{msg.entityActions_new}" value="/img/new.gif" styleClass="cmdImg"/>
            </t:commandLink>
        </f:facet>
        <t:commandLink action="#{projectEnsayoBean.deleteRequerimiento}"
                       onclick="if( !confirm('#{msg['question.confirmDelete']}') ) return false;">
            <h:graphicImage title="#{msg.entityActions_delete}" value="/img/delete.gif" styleClass="cmdImg"/>
        </t:commandLink>
    </h:column>


    <%-- Ignored field: id --%>

    <%-- Field: ensayo --%>
    <h:column>

        <f:facet name="header">
            <h:outputText value="#{msg['parametroString.nombre']}" styleClass="editListHeader"/>
        </f:facet>

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="nombre"/>
            <h:inputText id="nombre" value="#{item.nombre}" size="30"/>
        </h:panelGroup>

    </h:column>


    <h:column>

        <f:facet name="header">
            <h:outputText value="#{msg['parametroString.nombreIngles']}" styleClass="editListHeader"/>
        </f:facet>

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="nombreIngles"/>
            <h:inputText id="nombreIngles" value="#{item.nombreIngles}" size="30"/>
        </h:panelGroup>

    </h:column>


    <h:column>

        <f:facet name="header">
            <h:outputText value="#{msg['parametroString.requerimiento']}" styleClass="editListHeader"/>
        </f:facet>

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="requerimiento"/>
            <h:inputText id="requerimiento" value="#{item.requerimiento}" size="30"/>
        </h:panelGroup>

    </h:column>

    <h:column>

        <f:facet name="header">
            <h:outputText value="#{msg['projectEnsayoRequerimiento.valor']}" styleClass="editListHeader"/>
        </f:facet>

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="valor"/>
            <h:inputText id="valor" value="#{item.valor}" size="30"/>
        </h:panelGroup>

    </h:column>

    <h:column>

        <f:facet name="header">
            <h:outputText value="#{msg['projectEnsayoRequerimiento.conformidad']}" styleClass="editListHeader"/>
        </f:facet>

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="conformidad"/>
            <h:inputText id="conformidad" value="#{item.conformidad}" size="30"/>
        </h:panelGroup>

    </h:column>

    <%-- Ignored field: ownerId --%>


    <%-- Ignored field: departmentId --%>


    <%-- Ignored field: insertDate --%>


    <%-- Ignored field: updateDate --%>


    <%-- Ignored field: pauta --%>


</t:dataTable>

