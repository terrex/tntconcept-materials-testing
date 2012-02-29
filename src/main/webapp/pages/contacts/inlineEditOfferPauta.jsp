<%@page language="java" contentType="text/html; charset=UTF-8" %>

<%@include file="/inc/tlibs.jsp" %>

<%@include file="/inc/uiCore.jsp" %>

<%-- List of offerPautas --%>
<t:dataTable id="pautas" var="item" value="#{offerBean.pautas}" preserveDataModel="false"
             cellpadding="0" cellspacing="0" styleClass="editListTable"
             headerClass="editListHeaderCell" footerClass="editListFooter"
             rowClasses="editListRowO,editListRowE"
             columnClasses="listCmdCell,editListOfferEpautaPauta,editListOfferPautaCost,editListOfferPautaUnidades">

    <%-- Commands --%>
    <h:column>
        <f:facet name="header">
            <t:commandLink action="#{offerBean.createPautas}">
                <h:graphicImage title="#{msg.entityActions_new}" value="/img/new.gif" styleClass="cmdImg"/>
            </t:commandLink>
        </f:facet>
        <t:commandLink action="#{offerBean.deletePautas}"
                       onclick="if( !confirm('#{msg['question.confirmDelete']}') ) return false;">
            <h:graphicImage title="#{msg.entityActions_delete}" value="/img/delete.gif" styleClass="cmdImg"/>
        </t:commandLink>
    </h:column>


    <%-- Ignored field: id --%>


    <h:column>

        <f:facet name="header">
            <h:outputText value="*#{msg['offerPauta.pauta']}" styleClass="editListHeader"/>
        </f:facet>

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="pauta"/>
            <h:selectOneMenu id="pauta" value="#{item.pauta}" required="true" styleClass="requiredFieldClass"
                             immediate="true" onchange="submit()"
                             valueChangeListener="#{offerBean.onSelectedPautaChanged}">
                <f:selectItems value="#{pautaBean.pautas}"/>
                <f:converter converterId="autentia.EntityConverter"/>
            </h:selectOneMenu>
        </h:panelGroup>


    </h:column>


    <h:column>

        <f:facet name="header">
            <h:outputText value="*#{msg['offerPauta.cost']}" styleClass="editListHeader"/>
        </f:facet>

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="cost"/>
            <h:inputText id="cost" value="#{item.cost}" size="10" required="true" styleClass="requiredFieldClass"/>
        </h:panelGroup>


    </h:column>


    <h:column>

        <f:facet name="header">
            <h:outputText value="*#{msg['offerPauta.unidades']}" styleClass="editListHeader"/>
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



















                
                

                

                

