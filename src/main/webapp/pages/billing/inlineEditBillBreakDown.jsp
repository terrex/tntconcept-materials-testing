<%@page language="java" contentType="text/html; charset=UTF-8" %>

<%@include file="/inc/tlibs.jsp" %>

<%@include file="/inc/uiCore.jsp" %>

<%-- List of billBreakDowns --%>
<t:dataTable id="breakDown" var="item" value="#{billBean.breakDown}" preserveDataModel="false"
             cellpadding="0" cellspacing="0" styleClass="editListTable"
             headerClass="editListHeaderCell" footerClass="editListFooter"
             rowClasses="editListRowO,editListRowE"
             columnClasses="listCmdCell,editListBillBreakDownConcept,editListBillBreakDownUnits,editListBillBreakDownAmount,editListBillBreakDownIva">

    <%-- Commands --%>
    <h:column>
        <f:facet name="header">
            <h:panelGroup>
                <t:commandLink action="#{billBean.createBreakDown}">
                    <h:graphicImage title="#{msg.entityActions_new}" value="/img/new.gif" styleClass="cmdImg"/>
                </t:commandLink>
                <t:commandLink action="#{billBean.searchInBitacore}" immediate="true"
                               rendered="#{billBean.puedoBuscarBitacore}">
                    <h:graphicImage title="#{msg['bill.useBitacore']}" value="/img/calendar_on.gif"
                                    styleClass="cmdImg"/>
                </t:commandLink>
            </h:panelGroup>
        </f:facet>
        <t:commandLink action="#{billBean.deleteBreakDown}"
                       onclick="if( !confirm('#{msg['question.confirmDelete']}') ) return false;">
            <h:graphicImage title="#{msg.entityActions_delete}" value="/img/delete.gif" styleClass="cmdImg"/>
        </t:commandLink>
    </h:column>


    <%-- Ignored field: id --%>


    <h:column>

        <f:facet name="header">
            <h:outputText value="*#{msg['billBreakDown.concept']}" styleClass="editListHeader"/>
        </f:facet>

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="concept"/>
            <h:inputTextarea id="concept" value="#{item.concept}" rows="3" cols="68" required="true"/>
        </h:panelGroup>


    </h:column>


    <h:column>

        <f:facet name="header">
            <h:outputText value="*#{msg['billBreakDown.units']}" styleClass="editListHeader"/>
        </f:facet>

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="units"/>
            <h:inputText id="units" value="#{item.units}" maxlength="11" size="11" required="true"
                         styleClass="requiredFieldClass"/>
        </h:panelGroup>


    </h:column>


    <h:column>

        <f:facet name="header">
            <h:outputText value="*#{msg['billBreakDown.amount']}" styleClass="editListHeader"/>
        </f:facet>

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="amount"/>
            <h:inputText id="amount" value="#{item.amount}" maxlength="11" size="12" required="true"
                         styleClass="requiredFieldClass"/>
        </h:panelGroup>


    </h:column>


    <h:column>

        <f:facet name="header">
            <h:outputText value="*#{msg['billBreakDown.iva']}" styleClass="editListHeader"/>
        </f:facet>

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="iva"/>
            <h:inputText id="iva" value="#{item.iva}" maxlength="5" size="5" required="true"
                         styleClass="requiredFieldClass"/>
        </h:panelGroup>


    </h:column>

    <h:column>

        <f:facet name="header">
            <h:outputText value="#{msg['billBreakDown.total']}" styleClass="editListHeader"/>
        </f:facet>

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="total"/>
            <h:outputText id="total" value="#{item.total}" styleClass="requiredFieldClass"/>
        </h:panelGroup>


    </h:column>
    <%-- Ignored field: ownerId --%>


    <%-- Ignored field: departmentId --%>


    <%-- Ignored field: insertDate --%>


    <%-- Ignored field: updateDate --%>


    <%-- Ignored field: bill --%>


</t:dataTable>



















                
                

                

                

