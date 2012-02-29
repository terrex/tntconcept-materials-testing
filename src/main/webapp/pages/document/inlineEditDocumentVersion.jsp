<%@page language="java" contentType="text/html; charset=UTF-8" %>

<%@include file="/inc/tlibs.jsp" %>

<%@include file="/inc/uiCore.jsp" %>

<%-- List of documentVersions --%>
<t:dataTable id="versions" var="item" value="#{documentBean.versions}" preserveDataModel="false"
             cellpadding="0" cellspacing="0" styleClass="editListTable"
             headerClass="editListHeaderCell" footerClass="editListFooter"
             rowClasses="editListRowO,editListRowE"
             columnClasses="listCmdCell,editListDocumentVersionDocumentPath,editListDocumentVersionCreationDate,editListDocumentVersionVersion">

    <%-- Commands --%>
    <h:column>
        <f:facet name="header">
            <t:commandLink action="#{documentBean.createVersions}">
                <h:graphicImage title="#{msg.entityActions_new}" value="/img/new.gif" styleClass="cmdImg"/>
            </t:commandLink>
        </f:facet>
        <t:commandLink action="#{documentBean.deleteVersions}">
            <h:graphicImage title="#{msg.entityActions_delete}" value="/img/delete.gif" styleClass="cmdImg"/>
        </t:commandLink>
    </h:column>


    <%-- Ignored field: id --%>


    <h:column>

        <f:facet name="header">
            <h:outputText value="*#{msg['documentVersion.documentPath']}" styleClass="editListHeader"/>
        </f:facet>

        <f:verbatim>
            <h:outputText
                    value="<a href=\"#\" onclick=\"openDocumentFile('#{item.documentPath}');return false;\"><img src='../../img/yellow-folder-open.png' style='border:0; vertical-align:middle;'>&nbsp;#{item.documentPath}</a>"
                    escape="false"/>
        </f:verbatim>
        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="true" for="uploadDocument"/>
            <t:inputFileUpload id="uploadDocument" size="40" value="#{item.uploadDocument}" storage="file"
                               rendered="#{item.documentPath==null}" required="true" styleClass="requiredFieldClass"/>
        </h:panelGroup>


    </h:column>


    <h:column>

        <f:facet name="header">
            <h:outputText value="*#{msg['documentVersion.creationDate']}" styleClass="editListHeader"/>
        </f:facet>

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="creationDate"/>
            <h:outputText id="creationDate" value="#{item.creationDate}"/>
        </h:panelGroup>


    </h:column>


    <h:column>

        <f:facet name="header">
            <h:outputText value="*#{msg['documentVersion.version']}" styleClass="editListHeader"/>
        </f:facet>

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="version"/>
            <h:inputText id="version" value="#{item.version}" maxlength="255" required="true"
                         styleClass="requiredFieldClass"/>
        </h:panelGroup>


    </h:column>


    <%-- Ignored field: ownerId --%>


    <%-- Ignored field: departmentId --%>


    <%-- Ignored field: insertDate --%>


    <%-- Ignored field: updateDate --%>


    <%-- Ignored field: document --%>


</t:dataTable>



















                
                

                

                

