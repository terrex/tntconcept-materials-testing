<%@page language="java" contentType="text/html; charset=UTF-8" %>

<%@include file="/inc/tlibs.jsp" %>

<%@include file="/inc/uiCore.jsp" %>

<%-- List of procedimientos --%>
<t:dataTable id="procedimientos" var="item" value="#{ensayoBean.procedimientos}" preserveDataModel="false"
             cellpadding="0" cellspacing="0" styleClass="editListTable"
             headerClass="editListHeaderCell" footerClass="editListFooter"
             rowClasses="editListRowO,editListRowE"
             columnClasses="listCmdCell,editListEnsayoProcedimiento">

    <%-- Commands --%>
    <h:column>
        <f:facet name="header">
            <t:commandLink action="#{ensayoBean.createProcedimientos}">
                <h:graphicImage title="#{msg.entityActions_new}" value="/img/new.gif" styleClass="cmdImg"/>
            </t:commandLink>
        </f:facet>
        <t:commandLink action="#{ensayoBean.deleteProcedimientos}"
                       onclick="if( !confirm('#{msg['question.confirmDelete']}') ) return false;">
            <h:graphicImage title="#{msg.entityActions_delete}" value="/img/delete.gif" styleClass="cmdImg"/>
        </t:commandLink>
    </h:column>


    <%-- Ignored field: id --%>


    <h:column>

        <f:facet name="header">
            <h:outputText value="*#{msg['ensayoBean.procedimiento']}" styleClass="editListHeader"/>
        </f:facet>

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="procedimiento"/>
            <h:inputText id="procedimiento" value="#{item.name}" maxlength="128" required="true"
                         styleClass="requiredFieldClass" size="64"/>
        </h:panelGroup>


    </h:column>

    <%-- Ignored field: ownerId --%>


    <%-- Ignored field: departmentId --%>


    <%-- Ignored field: insertDate --%>


    <%-- Ignored field: updateDate --%>


    <%-- Ignored field: offer --%>


</t:dataTable>



















                
                

                

                

