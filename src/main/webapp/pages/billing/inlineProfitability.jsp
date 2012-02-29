<tr>
    <td class="detailLabelRatiosTitle" colspan="2">${msg['financialRatio.profitabilityRatio']}:</td>
</tr>

<%-- Field: grossSpread --%>
<tr>
    <td class="detailLabelRatios">${msg['financialRatio.grossSpread']}:</td>
    <td class="detailFieldCell">
        <h:outputText value="#{financialRatioBean.grossSpread}">
            <f:convertNumber maxFractionDigits="2" type="percent"/>
        </h:outputText>
    </td>
</tr>


<%-- Field: baitSpread --%>
<tr>
    <td class="detailLabelRatios">${msg['financialRatio.baitSpread']}:</td>
    <td class="detailFieldCell">
        <h:outputText value="#{financialRatioBean.baitSpread}">
            <f:convertNumber maxFractionDigits="2" type="percent"/>
        </h:outputText>
    </td>
</tr>


<%-- Field: bdtSpread --%>
<tr>
    <td class="detailLabelRatios">${msg['financialRatio.bdtSpread']}:</td>
    <td class="detailFieldCell">
        <h:outputText value="#{financialRatioBean.bdtSpread}">
            <f:convertNumber maxFractionDigits="2" type="percent"/>
        </h:outputText>
    </td>
</tr>
<%-- Field: baitROA --%>
<tr>
    <td class="detailLabelRatios">${msg['financialRatio.baitROA']}:</td>
    <td class="detailFieldCell">
        <h:outputText value="#{financialRatioBean.baitROA}">
            <f:convertNumber maxFractionDigits="2" type="percent"/>
        </h:outputText>
    </td>
</tr>


<%-- Field: baitdROA --%>
<tr>
    <td class="detailLabelRatios">${msg['financialRatio.baitdROA']}:</td>
    <td class="detailFieldCell">
        <h:outputText value="#{financialRatioBean.baitdROA}">
            <f:convertNumber maxFractionDigits="2" type="percent"/>
        </h:outputText>
    </td>
</tr>


<%-- Field: ROE --%>
<tr>
    <td class="detailLabelRatios">${msg['financialRatio.ROE']}:</td>
    <td class="detailFieldCell">
        <h:outputText value="#{financialRatioBean.ROE}">
            <f:convertNumber maxFractionDigits="2" type="percent"/>
        </h:outputText>
    </td>
</tr>

<%-- Field: leveraging --%>
<tr>
    <td class="detailLabelRatios">${msg['financialRatio.leveraging']}:</td>
    <td class="detailFieldCell">
        <h:outputText value="#{financialRatioBean.leveraging}">
            <f:convertNumber maxFractionDigits="2"/>
        </h:outputText>
    </td>
</tr>


<%-- Field: ROCE --%>
<tr>
    <td class="detailLabelRatios">${msg['financialRatio.ROCE']}:</td>
    <td class="detailFieldCell">
        <h:outputText value="#{financialRatioBean.ROCE}">
            <f:convertNumber maxFractionDigits="2" type="percent"/>
        </h:outputText>
    </td>
</tr>