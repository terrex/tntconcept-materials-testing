<%@page language="java" contentType="text/html; charset=UTF-8" %>

<%@include file="/inc/tlibs.jsp" %>

<%@include file="/inc/uiCore.jsp" %>

<%-- List of projectEnsayos --%>
<t:dataTable id="ensayos" var="item" value="#{projectBean.ensayos}" preserveDataModel="false"
             cellpadding="0" cellspacing="0" styleClass="editListTable"
             headerClass="editListHeaderCell" footerClass="editListFooter"
             rowClasses="editListRowO,editListRowE"
             columnClasses="listCmdCell,editListProjectEensayoEnsayo,editListProjectEnsayoCost">

    <%-- Commands --%>
    <h:column>
        <f:facet name="header">
            <t:commandLink action="#{projectBean.createEnsayos}">
                <h:graphicImage title="#{msg.entityActions_new}" value="/img/new.gif" styleClass="cmdImg"/>
            </t:commandLink>
        </f:facet>
        <t:commandLink action="#{projectBean.deleteEnsayos}"
                       onclick="if( !confirm('#{msg['question.confirmDelete']}') ) return false;">
            <h:graphicImage title="#{msg.entityActions_delete}" value="/img/delete.gif" styleClass="cmdImg"/>
        </t:commandLink>
    </h:column>


    <%-- Ignored field: id --%>


    <h:column>

        <f:facet name="header">
            <h:outputText value="*#{msg['projectEnsayo.ensayo']}" styleClass="editListHeader"/>
        </f:facet>

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="ensayo"/>
            <h:selectOneMenu id="ensayo" value="#{item.ensayo}" required="true" styleClass="requiredFieldClass">
                <f:selectItems value="#{projectBean.ensayosPosibles}"/>
                <f:converter converterId="autentia.EntityConverter"/>
            </h:selectOneMenu>
        </h:panelGroup>


    </h:column>


    <!-- nota de salida -->
    <h:column>
        <f:facet name="header">
            <h:outputText value="*#{msg['projectEnsayo.notaSalida']}" styleClass="editListHeader"/>
        </f:facet>

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="ensayo"/>
            <h:outputText value="#{item.notaSalida.name}"/>
        </h:panelGroup>
    </h:column>


    <h:column>

        <f:facet name="header">
            -
        </f:facet>
        <t:commandLink action="#{projectEnsayoBean.editarEsteEnsayo}" rendered="#{item.id != null}">
            <h:outputText value="Editar" styleClass="editListHeader"/>
        </t:commandLink>
    </h:column>


    <h:column>

        <f:facet name="header">
            <h:outputText value="#{msg['projectEnsayo.identificacionLab']}" styleClass="editListHeader"/>
        </f:facet>

        <h:panelGroup>
            <h:outputText value="#{item.identificacionLab}" styleClass="editListHeader"/>
        </h:panelGroup>
    </h:column>


    <h:column>

        <f:facet name="header">
            <h:outputText value="*#{msg['projectEnsayo.cost']}" styleClass="editListHeader"/>
        </f:facet>

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="cost"/>
            <h:inputText id="cost" value="#{item.cost}" size="10" required="true" styleClass="requiredFieldClass"/>
        </h:panelGroup>


    </h:column>


    <%-- Ignored field: ownerId --%>


    <%-- Ignored field: departmentId --%>


    <%-- Ignored field: insertDate --%>


    <%-- Ignored field: updateDate --%>


    <%-- Ignored field: project --%>


</t:dataTable>



















                
                

                

                

