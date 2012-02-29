<%@page language="java" contentType="text/html; charset=UTF-8" %>

<%@include file="/inc/tlibs.jsp" %>

<html>
<head>
    <%@include file="/inc/uiCore.jsp" %>
</head>
<body>

<f:loadBundle basename="com.autentia.intra.resources.messages" var="msg"/>
<i:location place="interactionReports" msg="${msg}"/>

<f:view>
    <%@include file="/inc/header.jsp" %>
    <c:if test="${interactionReportBean.launch}">
        <script>
            openReportParameters("interaction/${interactionReportBean.selectedReport}.${interactionReportBean.format}", "${interactionReportBean.parameters}", "${interactionReportBean.selectMany}");
        </script>
    </c:if>
    <%-- Report arguments user interface --%>
    <h:form id="interactionReports">

        <%-- Header --%>
        <i:titleBar msg="${msg}">
            <h:commandLink action="#{interactionReportBean.run}">
                <h:graphicImage value="/img/run.png" styleClass="titleImg"/>
            </h:commandLink>
        </i:titleBar>

        <%-- Fixed Arguments --%>
        <h:panelGrid columns="2" cellpadding="0" cellspacing="0" styleClass="editTable"
                     columnClasses="editLabelRW,editFieldCell">

            <h:outputText value="#{msg['interactionReports.report']}:"/>
            <h:selectOneMenu id="interactionReport" value="#{interactionReportBean.selectedReport}"
                             required="true" immediate="true"
                             valueChangeListener="#{interactionReportBean.selectedReportChanged}" onchange="submit();">
                <f:selectItems value="#{interactionReportBean.reports}"/>
            </h:selectOneMenu>

            <h:outputText value="#{msg['interactionReports.format']}:"/>
            <h:selectOneMenu id="reportFormat" value="#{interactionReportBean.format}" required="true">
                <f:selectItems value="#{interactionReportBean.formats}"/>
            </h:selectOneMenu>

        </h:panelGrid>

        <%-- Variable Arguments --%>
        <h:dataTable id="params" var="reportParameter"
                     value="#{interactionReportBean.reportParametersDefinitions}"
                     columnClasses="editLabelRW,editFieldCell" styleClass="editTable"
                     cellpadding="0" cellspacing="0">

            <%-- Label column --%>
            <h:column id="label">
                <h:outputText value="*#{reportParameter.label}:"
                              rendered="#{reportParameter.hiddenType != true}"></h:outputText>
            </h:column>

            <%-- Data column --%>
            <h:column id="data">
                <h:panelGroup id="info" rendered="#{reportParameter.infoType == true}">
                    <h:outputText value="#{reportParameter.value}"></h:outputText>
                </h:panelGroup>

                <h:panelGroup id="text" rendered="#{reportParameter.textType == true}">
                    <h:inputText value="#{reportParameter.value}" size="30" required="true"></h:inputText>
                </h:panelGroup>
                <h:panelGroup id="hidden" rendered="#{reportParameter.hiddenType == true}">
                    <h:inputHidden value="#{reportParameter.value}"></h:inputHidden>
                </h:panelGroup>
                <h:panelGroup id="date" rendered="#{reportParameter.dateType == true}">
                    <h:message styleClass="error" showSummary="true" showDetail="false" for="paramDate"/>
                    <t:inputCalendar id="paramDate" renderPopupButtonAsImage="true"
                                     value="#{reportParameter.dateValue}" size="12"
                                     renderAsPopup="true"
                                     popupDateFormat="dd/MM/yyyy" required="true"
                                     popupTodayString="#{msg['calendar.today']}"
                                     popupWeekString="#{msg['calendar.week']}"
                                     styleClass="requiredFieldClass">
                    </t:inputCalendar>
                </h:panelGroup>
                <h:panelGroup id="selectOnes" rendered="#{reportParameter.selectOneType == true}">
                    <h:selectOneMenu id="selectOne" value="#{reportParameter.value}" required="true">
                        <f:selectItems value="#{reportParameter.items}"/>
                    </h:selectOneMenu>
                </h:panelGroup>


                <h:panelGroup id="selectManys" rendered="#{reportParameter.selectManyType == true}">
                    <h:selectManyListbox size="8" id="selectMany" value="#{reportParameter.valueMany}" required="true">
                        <f:selectItems value="#{reportParameter.items}"/>
                    </h:selectManyListbox>
                </h:panelGroup>

            </h:column>

        </h:dataTable>

    </h:form>
</f:view>
</body>
</html>









