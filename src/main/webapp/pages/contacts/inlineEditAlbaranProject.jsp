<%@page language="java" contentType="text/html; charset=UTF-8" %>

<%@include file="/inc/tlibs.jsp" %>

<%@include file="/inc/uiCore.jsp" %>

<t:dataTable id="projects" var="item" value="#{albaranBean.allMyProjects}" preserveDataModel="false"
             cellpadding="0" cellspacing="0" styleClass="editListTable"
             headerClass="editListHeaderCell" footerClass="editListFooter"
             rowClasses="editListRowO,editListRowE"
             columnClasses="listCmdCell,editListOfferEpautaPauta,editListOfferPautaCost,editListOfferPautaUnidades">

    <h:column>
        <f:facet name="header">
        </f:facet>
        <h:panelGroup>

            <t:commandLink action="#{albaranBean.anadeProject}" rendered="#{item.albaran == null}">
                <h:graphicImage title="#{msg.entityActions_new}" value="/img/new.gif" styleClass="cmdImg"/>
            </t:commandLink>

            <t:commandLink action="#{albaranBean.quitaProject}" rendered="#{item.albaran != null}">
                <h:graphicImage title="#{msg.entityActions_delete}" value="/img/delete.gif" styleClass="cmdImg"/>
            </t:commandLink>

        </h:panelGroup>
    </h:column>

    <h:column>
        <f:facet name="header">
            <h:outputText value="#{msg['project.nameDescriptivo']}" styleClass="editListHeader"/>
        </f:facet>
        <h:panelGroup>
            <h:outputText value="#{item.nameDescriptivo}" rendered="#{item.albaran == null}"/>
            <h:outputText value="#{item.nameDescriptivo}" rendered="#{item.albaran != null}"
                          style="font-weight: bold;"/>
        </h:panelGroup>
    </h:column>

</t:dataTable>
