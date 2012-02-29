<tr>
    <td class="detailLabelRatiosTitle" colspan="2">${msg['financialRatio.solvencyRatio']}:</td>
</tr>
<%-- Field: solvency --%>
<tr>
    <td class="detailLabelRatios">${msg['financialRatio.solvency']}:</td>
    <td class="detailFieldCell">
        <h:outputText value="#{financialRatioBean.solvency}">
            <f:convertNumber maxFractionDigits="2"/>
        </h:outputText>
    </td>
</tr>


<%-- Field: debtRatio --%>
<tr>
    <td class="detailLabelRatios">${msg['financialRatio.debtRatio']}:</td>
    <td class="detailFieldCell">
        <h:outputText value="#{financialRatioBean.debtRatio}">
            <f:convertNumber maxFractionDigits="2"/>
        </h:outputText>
    </td>
</tr>
<%-- Field: debtTotalLiability --%>
<tr>
    <td class="detailLabelRatios">${msg['financialRatio.debtTotalLiability']}:</td>
    <td class="detailFieldCell">
        <h:outputText value="#{financialRatioBean.debtTotalLiability}">
            <f:convertNumber maxFractionDigits="2"/>
        </h:outputText>
    </td>
</tr>


<%-- Field: guarantee --%>
<tr>
    <td class="detailLabelRatios">${msg['financialRatio.guarantee']}:</td>
    <td class="detailFieldCell">
        <h:outputText value="#{financialRatioBean.guarantee}">
            <f:convertNumber maxFractionDigits="2"/>
        </h:outputText>
    </td>
</tr>


<%-- Field: financeCoverage --%>
<tr>
    <td class="detailLabelRatios">${msg['financialRatio.financeCoverage']}:</td>
    <td class="detailFieldCell">
        <h:outputText value="#{financialRatioBean.financeCoverage}">
            <f:convertNumber maxFractionDigits="2"/>
        </h:outputText>
    </td>
</tr>
 