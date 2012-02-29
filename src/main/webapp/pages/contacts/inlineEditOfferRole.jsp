<%@page language="java" contentType="text/html; charset=UTF-8" %>

<%@include file="/inc/tlibs.jsp" %>

<%@include file="/inc/uiCore.jsp" %>

<%-- List of offerRoles --%>
<t:dataTable id="roles" var="item" value="#{offerBean.roles}" preserveDataModel="false"
             cellpadding="0" cellspacing="0" styleClass="editListTable"
             headerClass="editListHeaderCell" footerClass="editListFooter"
             rowClasses="editListRowO,editListRowE"
             columnClasses="listCmdCell,editListOfferRoleName,editListOfferRoleCostPerHour,editListOfferRoleExpectedHours">

    <%-- Commands --%>
    <h:column>
        <f:facet name="header">
            <t:commandLink action="#{offerBean.createRoles}">
                <h:graphicImage title="#{msg.entityActions_new}" value="/img/new.gif" styleClass="cmdImg"/>
            </t:commandLink>
        </f:facet>
        <t:commandLink action="#{offerBean.deleteRoles}"
                       onclick="if( !confirm('#{msg['question.confirmDelete']}') ) return false;">
            <h:graphicImage title="#{msg.entityActions_delete}" value="/img/delete.gif" styleClass="cmdImg"/>
        </t:commandLink>
    </h:column>


    <%-- Ignored field: id --%>


    <h:column>

        <f:facet name="header">
            <h:outputText value="*#{msg['offerRole.name']}" styleClass="editListHeader"/>
        </f:facet>

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="name"/>
            <h:inputText id="name" value="#{item.name}" maxlength="128" required="true"
                         styleClass="requiredFieldClass"/>
        </h:panelGroup>


    </h:column>


    <h:column>

        <f:facet name="header">
            <h:outputText value="*#{msg['offerRole.costPerHour']}" styleClass="editListHeader"/>
        </f:facet>

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="costPerHour"/>
            <h:inputText id="costPerHour" value="#{item.costPerHour}" size="10" required="true"
                         styleClass="requiredFieldClass"/>
        </h:panelGroup>


    </h:column>


    <h:column>

        <f:facet name="header">
            <h:outputText value="*#{msg['offerRole.expectedHours']}" styleClass="editListHeader"/>
        </f:facet>

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="expectedHours"/>
            <h:inputText id="expectedHours" value="#{item.expectedHours}" size="10" required="true"
                         styleClass="requiredFieldClass"/>
        </h:panelGroup>


    </h:column>


    <%-- Ignored field: ownerId --%>


    <%-- Ignored field: departmentId --%>


    <%-- Ignored field: insertDate --%>


    <%-- Ignored field: updateDate --%>


    <%-- Ignored field: offer --%>


</t:dataTable>



















                
                

                

                

