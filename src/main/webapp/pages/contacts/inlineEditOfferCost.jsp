<%@page language="java" contentType="text/html; charset=UTF-8" %>

<%@include file="/inc/tlibs.jsp" %>

<%@include file="/inc/uiCore.jsp" %>

<%-- List of offerCosts --%>
<t:dataTable id="costs" var="item" value="#{offerBean.costs}" preserveDataModel="false"
             cellpadding="0" cellspacing="0" styleClass="editListTable"
             headerClass="editListHeaderCell" footerClass="editListFooter"
             rowClasses="editListRowO,editListRowE"
             columnClasses="listCmdCell,editListOfferCostName,editListOfferCostCost,editListOfferCostBillable">

    <%-- Commands --%>
    <h:column>
        <f:facet name="header">
            <t:commandLink action="#{offerBean.createCosts}">
                <h:graphicImage title="#{msg.entityActions_new}" value="/img/new.gif" styleClass="cmdImg"/>
            </t:commandLink>
        </f:facet>
        <t:commandLink action="#{offerBean.deleteCosts}"
                       onclick="if( !confirm('#{msg['question.confirmDelete']}') ) return false;">
            <h:graphicImage title="#{msg.entityActions_delete}" value="/img/delete.gif" styleClass="cmdImg"/>
        </t:commandLink>
    </h:column>


    <%-- Ignored field: id --%>


    <h:column>

        <f:facet name="header">
            <h:outputText value="*#{msg['offerCost.name']}" styleClass="editListHeader"/>
        </f:facet>

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="name"/>
            <h:inputText id="name" value="#{item.name}" maxlength="128" required="true"
                         styleClass="requiredFieldClass"/>
        </h:panelGroup>


    </h:column>


    <h:column>

        <f:facet name="header">
            <h:outputText value="*#{msg['offerCost.cost']}" styleClass="editListHeader"/>
        </f:facet>

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="cost"/>
            <h:inputText id="cost" value="#{item.cost}" size="10" required="true" styleClass="requiredFieldClass"/>
        </h:panelGroup>


    </h:column>


    <h:column>

        <f:facet name="header">
            <h:outputText value="*#{msg['offerCost.billable']}" styleClass="editListHeader"/>
        </f:facet>

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="billable"/>
            <h:selectBooleanCheckbox id="billable" value="#{item.billable}" required="true"
                                     styleClass="requiredFieldClass"/>
        </h:panelGroup>


    </h:column>


    <%-- Ignored field: ownerId --%>


    <%-- Ignored field: departmentId --%>


    <%-- Ignored field: insertDate --%>


    <%-- Ignored field: updateDate --%>


    <%-- Ignored field: offer --%>


</t:dataTable>



















                
                

                

                

