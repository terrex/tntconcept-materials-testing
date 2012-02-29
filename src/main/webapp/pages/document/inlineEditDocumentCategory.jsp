<%@page language="java" contentType="text/html; charset=UTF-8" %>

<%@include file="/inc/tlibs.jsp" %>

<%@include file="/inc/uiCore.jsp" %>

<%-- List of documentCategorys --%>
<t:dataTable id="categories" var="item" value="#{documentBean.categories}" preserveDataModel="false"
             cellpadding="0" cellspacing="0" styleClass="editListTable"
             headerClass="editListHeaderCell" footerClass="editListFooter"
             rowClasses="editListRowO,editListRowE"
             columnClasses="listCmdCell,editListDocumentCategoryName,editListDocumentCategoryOwnerId,editListDocumentCategoryDepartmentId,editListDocumentCategoryInsertDate,editListDocumentCategoryUpdateDate">

    <%-- Commands --%>
    <h:column>
        <f:facet name="header">
            <t:commandLink action="#{documentBean.createCategories}">
                <h:graphicImage title="#{msg.entityActions_new}" value="/img/new.gif" styleClass="cmdImg"/>
            </t:commandLink>
        </f:facet>
        <t:commandLink action="#{documentBean.deleteCategories}">
            <h:graphicImage title="#{msg.entityActions_delete}" value="/img/delete.gif" styleClass="cmdImg"/>
        </t:commandLink>
    </h:column>


    <%-- Ignored field: id --%>


    <h:column>

        <f:facet name="header">
            <h:outputText value="*#{msg['documentCategory.name']}" styleClass="editListHeader"/>
        </f:facet>

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="name"/>
            <h:inputText id="name" value="#{item.name}" size="70" maxlength="255" required="true"
                         styleClass="requiredFieldClass"/>
        </h:panelGroup>


    </h:column>


    <%-- Ignored field: description --%>


    <%-- Ignored field: code --%>


    <%-- Ignored field: documentsLastUpdate --%>


    <h:column>

        <f:facet name="header">
            <h:outputText value="#{msg['documentCategory.ownerId']}" styleClass="editListHeader"/>
        </f:facet>

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="ownerId"/>
            <h:inputText id="ownerId" value="#{item.ownerId}" size="10"/>
        </h:panelGroup>


    </h:column>


    <h:column>

        <f:facet name="header">
            <h:outputText value="#{msg['documentCategory.departmentId']}" styleClass="editListHeader"/>
        </f:facet>

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="departmentId"/>
            <h:inputText id="departmentId" value="#{item.departmentId}" size="10"/>
        </h:panelGroup>


    </h:column>


    <h:column>

        <f:facet name="header">
            <h:outputText value="#{msg['documentCategory.insertDate']}" styleClass="editListHeader"/>
        </f:facet>

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="insertDate"/>
            <t:inputCalendar id="insertDate" value="#{item.insertDate}"
                             renderAsPopup="true" popupDateFormat="d/MM/yyyy" renderPopupButtonAsImage="true"
                             popupTodayString="#{msg['calendar.today']}" popupWeekString="#{msg['calendar.week']}"/>
        </h:panelGroup>


    </h:column>


    <h:column>

        <f:facet name="header">
            <h:outputText value="#{msg['documentCategory.updateDate']}" styleClass="editListHeader"/>
        </f:facet>

        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="updateDate"/>
            <t:inputCalendar id="updateDate" value="#{item.updateDate}"
                             renderAsPopup="true" popupDateFormat="d/MM/yyyy" renderPopupButtonAsImage="true"
                             popupTodayString="#{msg['calendar.today']}" popupWeekString="#{msg['calendar.week']}"/>
        </h:panelGroup>


    </h:column>


    <%-- Ignored field: padre --%>


</t:dataTable>



















                
                

                

                

