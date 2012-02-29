<%@page language="java" contentType="text/html; charset=UTF-8" %>

<%@include file="/inc/tlibs.jsp" %>

<%@include file="/inc/uiCore.jsp" %>

<t:dataTable id="albarans" var="item" value="#{billBean.allMyAlbarans}" preserveDataModel="false"
             cellpadding="0" cellspacing="0" styleClass="editListTable"
             headerClass="editListHeaderCell" footerClass="editListFooter"
             rowClasses="editListRowO,editListRowE"
             columnClasses="listCmdCell,editListOfferEpautaPauta,editListOfferPautaCost,editListOfferPautaUnidades">

    <h:column>
        <f:facet name="header">
        </f:facet>
        <h:panelGroup>

            <t:commandLink action="#{billBean.anadeAlbaran}" rendered="#{item.bill == null}">
                <h:graphicImage title="#{msg.entityActions_new}" value="/img/new.gif" styleClass="cmdImg"/>
            </t:commandLink>

            <t:commandLink action="#{billBean.quitaAlbaran}" rendered="#{item.bill != null}">
                <h:graphicImage title="#{msg.entityActions_delete}" value="/img/delete.gif" styleClass="cmdImg"/>
            </t:commandLink>

        </h:panelGroup>
    </h:column>

    <h:column>
        <f:facet name="header">
            <h:outputText value="#{msg['albaran.name']}" styleClass="editListHeader"/>
        </f:facet>
        <h:panelGroup>
            <t:commandLink action="#{albaranBean.detail_desde_fuera}" immediate="true" styleClass="enlaceAzul">
                <f:param name="id" value="#{item.id}"/>
                <h:outputText value="#{item.name}" rendered="#{item.bill == null}"/>
                <h:outputText value="#{item.name}" rendered="#{item.bill != null}"
                              style="font-weight: bold;"/>
            </t:commandLink>
        </h:panelGroup>
    </h:column>

</t:dataTable>
