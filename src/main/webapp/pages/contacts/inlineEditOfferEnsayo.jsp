<%@page language="java" contentType="text/html; charset=UTF-8" %>

<%@include file="/inc/tlibs.jsp" %>

<%@include file="/inc/uiCore.jsp" %>

<%-- List of offerEnsayos --%>
<t:dataTable id="ensayos" var="item" value="#{offerBean.ensayos}" preserveDataModel="false"
             cellpadding="0" cellspacing="0" styleClass="editListTable"
             headerClass="editListHeaderCell" footerClass="editListFooter"
             rowClasses="editListRowO,editListRowE"
             columnClasses="listCmdCell,editListOfferEensayoEnsayo,editListOfferEnsayoCost,editListOfferEnsayoUnidades">

    <%-- Commands --%>
    <h:column>
        <f:facet name="header">
            <t:commandLink action="#{offerBean.createEnsayos}">
                <h:graphicImage title="#{msg.entityActions_new}" value="/img/new.gif" styleClass="cmdImg"/>
            </t:commandLink>
        </f:facet>
        <t:commandLink action="#{offerBean.deleteEnsayos}"
                       onclick="if( !confirm('#{msg['question.confirmDelete']}') ) return false;">
            <h:graphicImage title="#{msg.entityActions_delete}" value="/img/delete.gif" styleClass="cmdImg"/>
        </t:commandLink>
    </h:column>


    <%-- Ignored field: id --%>


    <h:column>

        <f:facet name="header">
            <h:outputText value="*#{msg['offerEnsayo.ensayo']}" styleClass="editListHeader"/>
        </f:facet>

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="ensayo"/>
            <h:selectOneMenu id="ensayo" value="#{item.ensayo}" required="true" styleClass="requiredFieldClass"
                             immediate="true" onchange="submit()"
                             valueChangeListener="#{offerBean.onSelectedEnsayoChanged}">
                <f:selectItems value="#{offerBean.ensayosPosibles}"/>
                <f:converter converterId="autentia.EntityConverter"/>
            </h:selectOneMenu>
        </h:panelGroup>


    </h:column>


    <h:column>

        <f:facet name="header">
            <h:outputText value="*#{msg['offerEnsayo.cost']}" styleClass="editListHeader"/>
        </f:facet>

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="cost"/>
            <h:inputText id="cost" value="#{item.cost}" size="10" required="true" styleClass="requiredFieldClass"/>
        </h:panelGroup>


    </h:column>


    <h:column>

        <f:facet name="header">
            <h:outputText value="*#{msg['offerEnsayo.unidades']}" styleClass="editListHeader"/>
        </f:facet>

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="unidades"/>
            <h:inputText id="unidades" value="#{item.unidades}" size="10" required="true"
                         styleClass="requiredFieldClass"/>
        </h:panelGroup>


    </h:column>


    <%-- Ignored field: ownerId --%>


    <%-- Ignored field: departmentId --%>


    <%-- Ignored field: insertDate --%>


    <%-- Ignored field: updateDate --%>


    <%-- Ignored field: offer --%>


</t:dataTable>



















                
                

                

                

