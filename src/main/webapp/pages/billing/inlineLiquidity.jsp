<tr>
    <td class="detailLabelRatiosTitle" colspan="2">${msg['financialRatio.liquidityRatio']}:</td>
</tr>
<%-- Field: liquidity --%>
<tr>
    <td class="detailLabelRatios">${msg['financialRatio.liquidity']}:</td>
    <td class="detailFieldCell">
        <h:outputText value="#{financialRatioBean.liquidity}">
            <f:convertNumber maxFractionDigits="2"/>
        </h:outputText>
    </td>
</tr>


<%-- Field: exchequer --%>
<tr>
    <td class="detailLabelRatios">${msg['financialRatio.exchequer']}:</td>
    <td class="detailFieldCell">
        <h:outputText value="#{financialRatioBean.exchequer}">
            <f:convertNumber maxFractionDigits="2"/>
        </h:outputText>
    </td>
</tr>
<%-- Field: availability --%>
<tr>
    <td class="detailLabelRatios">${msg['financialRatio.availability']}:</td>
    <td class="detailFieldCell">
        <h:outputText value="#{financialRatioBean.availability}">
            <f:convertNumber maxFractionDigits="2"/>
        </h:outputText>
    </td>
</tr>


<%-- Field: expensesCoverage --%>
<tr>
    <td class="detailLabelRatios">${msg['financialRatio.expensesCoverage']}:</td>
    <td class="detailFieldCell">
        <h:outputText value="#{financialRatioBean.expensesCoverage}">
            <f:convertNumber maxFractionDigits="2"/>
        </h:outputText>
    </td>
</tr>


<%-- Field: workingCapital --%>
<tr>
    <td class="detailLabelRatios">${msg['financialRatio.workingCapital']}:</td>
    <td class="detailFieldCell">
        <h:outputText value="#{financialRatioBean.workingCapital}">
            <f:convertNumber maxFractionDigits="2" type="percent"/>
        </h:outputText>
    </td>
</tr>
                                
 