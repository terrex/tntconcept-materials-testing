<%@page language="java" contentType="text/html; charset=UTF-8" %>

<%@include file="/inc/tlibs.jsp" %>

<html>
<head>
    <%@include file="/inc/uiCore.jsp" %>
</head>
<body>

<!-- searchPeriodicalAccountEntry.jsp: generated by stajanov code generator -->
<f:loadBundle basename="com.autentia.intra.resources.messages" var="msg"/>
<i:location place="searchPeriodicalAccountEntry" msg="${msg}"/>

<f:view>
<%@include file="/inc/header.jsp" %>
<h:form id="search" enctype="multipart/form-data">

<%-- Header --%>
<i:titleBar msg="${msg}">
    <h:commandLink action="#{periodicalAccountEntryBean.list}">
        <h:graphicImage title="#{msg.entityActions_run}" value="/img/run.png" styleClass="titleImg"/>
    </h:commandLink>
</i:titleBar>

<%-- Search form --%>
<table class="searchTable" cellpadding="0" cellspacing="0">


    <%-- Ignored field: id --%>


    <%-- Field: concept --%>
<tr>
    <td class="searchLabel">${msg['periodicalAccountEntry.concept']}:</td>
    <td class="searchFieldCell">


        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="concept"/>
            <h:selectBooleanCheckbox id="conceptValid" value="#{periodicalAccountEntryBean.searchConceptValid}"
                                     onclick="setEnabled('search:concept',this.checked)"/>
            <h:inputText id="concept" value="#{periodicalAccountEntryBean.searchConcept}" size="70"/>
        </h:panelGroup>
        <script>
            setEnabled('search:concept', ${periodicalAccountEntryBean.searchConceptValid})
        </script>


    </td>
</tr>

    <%-- Field: date --%>
<tr>
    <td class="searchLabel">${msg['periodicalAccountEntry.date']}:</td>
    <td class="searchFieldCell">


        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="startDate"/>
            <h:selectBooleanCheckbox id="startDateValid" value="#{periodicalAccountEntryBean.searchStartDateValid}"
                                     onclick="setEnabled('search:startDate',this.checked)"/>
            <t:inputCalendar id="startDate" value="#{periodicalAccountEntryBean.searchStartDate}"
                             renderAsPopup="true" popupDateFormat="d/MM/yyyy" renderPopupButtonAsImage="true"
                             popupTodayString="#{msg['calendar.today']}" popupWeekString="#{msg['calendar.week']}"
                             onchange="setEnabled('search:startDate',true);setChecked('search:startDateValid',true);"
                    />
        </h:panelGroup>
        -
        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="endDate"/>
            <h:selectBooleanCheckbox id="endDateValid" value="#{periodicalAccountEntryBean.searchEndDateValid}"
                                     onclick="setEnabled('search:endDate',this.checked)"/>
            <t:inputCalendar id="endDate" value="#{periodicalAccountEntryBean.searchEndDate}"
                             renderAsPopup="true" popupDateFormat="d/MM/yyyy" renderPopupButtonAsImage="true"
                             popupTodayString="#{msg['calendar.today']}" popupWeekString="#{msg['calendar.week']}"
                             onchange="setEnabled('search:endDate',true);setChecked('search:endDateValid',true);"
                    />
        </h:panelGroup>
        <script>
            setEnabled('search:startDate', ${periodicalAccountEntryBean.searchStartDateValid})
            setEnabled('search:endDate', ${periodicalAccountEntryBean.searchEndDateValid})
        </script>


    </td>
</tr>

    <%-- Field: amount --%>
<tr>
    <td class="searchLabel">${msg['periodicalAccountEntry.amount']}:</td>
    <td class="searchFieldCell">


        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="startAmount"/>
            <h:selectBooleanCheckbox id="startAmountValid" value="#{periodicalAccountEntryBean.searchStartAmountValid}"
                                     onclick="setEnabled('search:startAmount',this.checked)"/>
            <h:inputText id="startAmount" value="#{periodicalAccountEntryBean.searchStartAmount}" size="31"/>
        </h:panelGroup>
        -
        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="endAmount"/>
            <h:selectBooleanCheckbox id="endAmountValid" value="#{periodicalAccountEntryBean.searchEndAmountValid}"
                                     onclick="setEnabled('search:endAmount',this.checked)"/>
            <h:inputText id="endAmount" value="#{periodicalAccountEntryBean.searchEndAmount}" size="31"/>
        </h:panelGroup>
        <script>
            setEnabled('search:startAmount', ${periodicalAccountEntryBean.searchStartAmountValid})
            setEnabled('search:endAmount', ${periodicalAccountEntryBean.searchEndAmountValid})
        </script>


    </td>
</tr>

    <%-- Field: rise --%>
<tr>
    <td class="searchLabel">${msg['periodicalAccountEntry.rise']}:</td>
    <td class="searchFieldCell">


        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="startRise"/>
            <h:selectBooleanCheckbox id="startRiseValid" value="#{periodicalAccountEntryBean.searchStartRiseValid}"
                                     onclick="setEnabled('search:startRise',this.checked)"/>
            <h:inputText id="startRise" value="#{periodicalAccountEntryBean.searchStartRise}" size="31"/>
        </h:panelGroup>
        -
        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="endRise"/>
            <h:selectBooleanCheckbox id="endRiseValid" value="#{periodicalAccountEntryBean.searchEndRiseValid}"
                                     onclick="setEnabled('search:endRise',this.checked)"/>
            <h:inputText id="endRise" value="#{periodicalAccountEntryBean.searchEndRise}" size="31"/>
        </h:panelGroup>
        <script>
            setEnabled('search:startRise', ${periodicalAccountEntryBean.searchStartRiseValid})
            setEnabled('search:endRise', ${periodicalAccountEntryBean.searchEndRiseValid})
        </script>


    </td>
</tr>

    <%-- Ignored field: observations --%>


    <%-- Ignored field: ownerId --%>


    <%-- Ignored field: departmentId --%>


    <%-- Ignored field: insertDate --%>


    <%-- Ignored field: updateDate --%>


    <%-- Field: account --%>
<tr>
    <td class="searchLabel">${msg['periodicalAccountEntry.account']}:</td>
    <td class="searchFieldCell">


        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="account"/>
            <h:selectBooleanCheckbox id="accountValid" value="#{periodicalAccountEntryBean.searchAccountValid}"
                                     onclick="setEnabled('search:account',this.checked)"/>
            <h:selectOneMenu id="account" value="#{periodicalAccountEntryBean.searchAccount}"
                             onclick="setEnabled('search:account',true)">
                <f:selectItems value="#{periodicalAccountEntryBean.accounts}"/>
                <f:converter converterId="autentia.EntityConverter"/>
            </h:selectOneMenu>
        </h:panelGroup>
        <script>
            setEnabled('search:account', ${periodicalAccountEntryBean.searchAccountValid})
        </script>


    </td>
</tr>

    <%-- Field: type --%>
<tr>
    <td class="searchLabel">${msg['periodicalAccountEntry.type']}:</td>
    <td class="searchFieldCell">


        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="type"/>
            <h:selectBooleanCheckbox id="typeValid" value="#{periodicalAccountEntryBean.searchTypeValid}"
                                     onclick="setEnabled('search:type',this.checked)"/>
            <h:selectOneMenu id="type" value="#{periodicalAccountEntryBean.searchType}"
                             onclick="setEnabled('search:type',true)">
                <f:selectItems value="#{periodicalAccountEntryBean.types}"/>
                <f:converter converterId="autentia.EntityConverter"/>
            </h:selectOneMenu>
        </h:panelGroup>
        <script>
            setEnabled('search:type', ${periodicalAccountEntryBean.searchTypeValid})
        </script>


    </td>
</tr>

    <%-- Field: frequency --%>
<tr>
    <td class="searchLabel">${msg['periodicalAccountEntry.frequency']}:</td>
    <td class="searchFieldCell">


        <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="frequency"/>
            <h:selectBooleanCheckbox id="frequencyValid" value="#{periodicalAccountEntryBean.searchFrequencyValid}"
                                     onclick="setEnabled('search:frequency',this.checked)"/>
            <h:selectOneMenu id="frequency" value="#{periodicalAccountEntryBean.searchFrequency}"
                             onclick="setEnabled('search:frequency',true)">
                <f:selectItems value="#{periodicalAccountEntryBean.frequencys}"/>
                <f:converter converterId="autentia.EntityConverter"/>
            </h:selectOneMenu>
        </h:panelGroup>
        <script>
            setEnabled('search:frequency', ${periodicalAccountEntryBean.searchFrequencyValid})
        </script>


    </td>
</tr>

</table>

</h:form>
</f:view>

</body>
</html>  		
