<%@page language="java" contentType="text/html; charset=UTF-8" %>

<%@include file="/inc/tlibs.jsp" %>

<html>
<head>
    <%@include file="/inc/uiCore.jsp" %>
</head>
<body>

<!-- searchDocumentCategory.jsp: generated by stajanov code generator -->
<f:loadBundle basename="com.autentia.intra.resources.messages" var="msg"/>
<i:location place="searchDocumentCategory" msg="${msg}"/>

<f:view>
    <%@include file="/inc/header.jsp" %>
    <h:form id="search" enctype="multipart/form-data">

        <%-- Header --%>
        <i:titleBar msg="${msg}">
            <h:commandLink action="#{documentCategoryBean.list}">
                <h:graphicImage title="#{msg.entityActions_run}" value="/img/run.png" styleClass="titleImg"/>
            </h:commandLink>
        </i:titleBar>

        <%-- Search form --%>
        <table class="searchTable" cellpadding="0" cellspacing="0">


                <%-- Ignored field: id --%>


                <%-- Field: name --%>
            <tr>
                <td class="searchLabel">${msg['documentCategory.name']}:</td>
                <td class="searchFieldCell">


                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="name"/>
                        <h:selectBooleanCheckbox id="nameValid" value="#{documentCategoryBean.searchNameValid}"
                                                 onclick="setEnabled('search:name',this.checked)"/>
                        <h:inputText id="name" value="#{documentCategoryBean.searchName}" size="70"/>
                    </h:panelGroup>
                    <script>
                        setEnabled('search:name', ${documentCategoryBean.searchNameValid})
                    </script>


                </td>
            </tr>

                <%-- Field: description --%>
            <tr>
                <td class="searchLabel">${msg['documentCategory.description']}:</td>
                <td class="searchFieldCell">


                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="description"/>
                        <h:selectBooleanCheckbox id="descriptionValid"
                                                 value="#{documentCategoryBean.searchDescriptionValid}"
                                                 onclick="setEnabled('search:description',this.checked)"/>
                        <h:inputText id="description" value="#{documentCategoryBean.searchDescription}" size="70"/>
                    </h:panelGroup>
                    <script>
                        setEnabled('search:description', ${documentCategoryBean.searchDescriptionValid})
                    </script>


                </td>
            </tr>

                <%-- Field: code --%>
            <tr>
                <td class="searchLabel">${msg['documentCategory.code']}:</td>
                <td class="searchFieldCell">


                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="code"/>
                        <h:selectBooleanCheckbox id="codeValid" value="#{documentCategoryBean.searchCodeValid}"
                                                 onclick="setEnabled('search:code',this.checked)"/>
                        <h:inputText id="code" value="#{documentCategoryBean.searchCode}" size="70"/>
                    </h:panelGroup>
                    <script>
                        setEnabled('search:code', ${documentCategoryBean.searchCodeValid})
                    </script>


                </td>
            </tr>

                <%-- Field: documentsLastUpdate --%>
            <tr>
                <td class="searchLabel">${msg['documentCategory.documentsLastUpdate']}:</td>
                <td class="searchFieldCell">


                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false"
                                   for="startDocumentsLastUpdate"/>
                        <h:selectBooleanCheckbox id="startDocumentsLastUpdateValid"
                                                 value="#{documentCategoryBean.searchStartDocumentsLastUpdateValid}"
                                                 onclick="setEnabled('search:startDocumentsLastUpdate',this.checked)"/>
                        <t:inputCalendar id="startDocumentsLastUpdate"
                                         value="#{documentCategoryBean.searchStartDocumentsLastUpdate}"
                                         renderAsPopup="true" popupDateFormat="d/MM/yyyy"
                                         renderPopupButtonAsImage="true"
                                         popupTodayString="#{msg['calendar.today']}"
                                         popupWeekString="#{msg['calendar.week']}"
                                         onchange="setEnabled('search:startDocumentsLastUpdate',true);setChecked('search:startDocumentsLastUpdateValid',true);"
                                />
                    </h:panelGroup>
                    -
                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false"
                                   for="endDocumentsLastUpdate"/>
                        <h:selectBooleanCheckbox id="endDocumentsLastUpdateValid"
                                                 value="#{documentCategoryBean.searchEndDocumentsLastUpdateValid}"
                                                 onclick="setEnabled('search:endDocumentsLastUpdate',this.checked)"/>
                        <t:inputCalendar id="endDocumentsLastUpdate"
                                         value="#{documentCategoryBean.searchEndDocumentsLastUpdate}"
                                         renderAsPopup="true" popupDateFormat="d/MM/yyyy"
                                         renderPopupButtonAsImage="true"
                                         popupTodayString="#{msg['calendar.today']}"
                                         popupWeekString="#{msg['calendar.week']}"
                                         onchange="setEnabled('search:endDocumentsLastUpdate',true);setChecked('search:endDocumentsLastUpdateValid',true);"
                                />
                    </h:panelGroup>
                    <script>
                        setEnabled('search:startDocumentsLastUpdate', ${documentCategoryBean.searchStartDocumentsLastUpdateValid})
                        setEnabled('search:endDocumentsLastUpdate', ${documentCategoryBean.searchEndDocumentsLastUpdateValid})
                    </script>


                </td>
            </tr>

                <%-- Ignored field: ownerId --%>


                <%-- Ignored field: departmentId --%>


                <%-- Ignored field: insertDate --%>


                <%-- Ignored field: updateDate --%>


                <%-- Field: padre --%>
            <tr>
                <td class="searchLabel">${msg['documentCategory.padre']}:</td>
                <td class="searchFieldCell">


                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="padre"/>
                        <h:selectBooleanCheckbox id="padreValid" value="#{documentCategoryBean.searchPadreValid}"
                                                 onclick="setEnabled('search:padre',this.checked)"/>
                        <h:selectOneMenu id="padre" value="#{documentCategoryBean.searchPadre}"
                                         onclick="setEnabled('search:padre',true)">
                            <f:selectItems value="#{documentCategoryBean.padres}"/>
                            <f:converter converterId="autentia.EntityConverter"/>
                        </h:selectOneMenu>
                    </h:panelGroup>
                    <script>
                        setEnabled('search:padre', ${documentCategoryBean.searchPadreValid})
                    </script>


                </td>
            </tr>

        </table>

    </h:form>
</f:view>

</body>
</html>  		