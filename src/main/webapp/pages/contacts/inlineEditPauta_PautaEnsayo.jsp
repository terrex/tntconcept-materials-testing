<%@page language="java" contentType="text/html; charset=UTF-8" %>

<%@include file="/inc/tlibs.jsp" %>

<%@include file="/inc/uiCore.jsp" %>

<%-- List of ensayos --%>
<t:dataTable id="ensayos" var="item" value="#{pautaBean.ensayos}" preserveDataModel="false"
             cellpadding="0" cellspacing="0" styleClass="editListTable"
             headerClass="editListHeaderCell" footerClass="editListFooter"
             rowClasses="editListRowO,editListRowE"
             columnClasses="listCmdCell,editListEnsayoProject">

    <%-- Commands --%>
    <h:column>
        <f:facet name="header">
            <t:commandLink action="#{pautaBean.createEnsayos}">
                <h:graphicImage title="#{msg.entityActions_new}" value="/img/new.gif" styleClass="cmdImg"/>
            </t:commandLink>
        </f:facet>
        <t:commandLink action="#{pautaBean.deleteEnsayos}"
                       onclick="if( !confirm('#{msg['question.confirmDelete']}') ) return false;">
            <h:graphicImage title="#{msg.entityActions_delete}" value="/img/delete.gif" styleClass="cmdImg"/>
        </t:commandLink>
    </h:column>


    <%-- Ignored field: id --%>

    <%-- Field: ensayo --%>
    <h:column>

        <f:facet name="header">
            <h:outputText value="*#{msg['editPauta_PautaEnsayo.ensayo']}" styleClass="editListHeader"/>
        </f:facet>

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="ensayo"/>
            <h:selectOneMenu id="ensayo" value="#{item.ensayo}" required="true" styleClass="requiredFieldClass"
                             onchange="submit()"
                             valueChangeListener="#{item.onSelectedEnsayoChanged}">
                <f:selectItems value="#{ensayoBean.ensayos}"/>
                <f:converter converterId="autentia.EntityConverter"/>
            </h:selectOneMenu>
        </h:panelGroup>


    </h:column>


    <h:column>

        <f:facet name="header">
            <h:outputText value="*#{msg['editPauta_PautaEnsayo.procedimiento']}" styleClass="editListHeader"/>
        </f:facet>

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="procedimiento"/>
            <h:selectOneMenu id="procedimiento" value="#{item.procedimiento}">
                <f:selectItems value="#{item.procedimientosBySelectedEnsayo}"/>
            </h:selectOneMenu>
        </h:panelGroup>

    </h:column>


    <h:column>

        <f:facet name="header">
            <h:outputText value="*#{msg['editPauta_PautaEnsayo.requerimientos']}" styleClass="editListHeader"/>
        </f:facet>

        <t:commandLink action="#{pautaEnsayoBean.editarEstePautaEnsayo}">
            <h:outputText value="Requerimientos" styleClass="editListHeader"/>
        </t:commandLink>

    </h:column>


    <%-- Ignored field: ownerId --%>


    <%-- Ignored field: departmentId --%>


    <%-- Ignored field: insertDate --%>


    <%-- Ignored field: updateDate --%>


    <%-- Ignored field: pauta --%>


</t:dataTable>

