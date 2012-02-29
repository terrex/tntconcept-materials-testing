<tr>
    <td class="detailLabelRatiosTitle" colspan="2">${msg['financialRatio.efficiencyRatio']}:</td>
</tr>
<%-- Field: collectionPeriod --%>
<tr>
    <td class="detailLabelRatios">${msg['financialRatio.collectionPeriod']}:</td>
    <td class="detailFieldCell">
        <h:outputText value="#{financialRatioBean.collectionPeriod}">
            <f:convertNumber maxFractionDigits="2"/>
        </h:outputText>
    </td>
</tr>


<%-- Field: payPeriod --%>
<tr>
    <td class="detailLabelRatios">${msg['financialRatio.payPeriod']}:</td>
    <td class="detailFieldCell">
        <h:outputText value="#{financialRatioBean.payPeriod}">
            <f:convertNumber maxFractionDigits="2"/>
        </h:outputText>
    </td>
</tr>


<%-- Field: turnoverAsset --%>
<tr>
    <td class="detailLabelRatios">${msg['financialRatio.turnoverAsset']}:</td>
    <td class="detailFieldCell">
        <h:outputText value="#{financialRatioBean.turnoverAsset}">
            <f:convertNumber maxFractionDigits="2"/>
        </h:outputText>
    </td>
</tr>
<%-- Field: turnoverPermanentAsset --%>
<tr>
    <td class="detailLabelRatios">${msg['financialRatio.turnoverPermanentAsset']}:</td>
    <td class="detailFieldCell">
        <h:outputText value="#{financialRatioBean.turnoverPermanentAsset}">
            <f:convertNumber maxFractionDigits="2"/>
        </h:outputText>
    </td>
</tr>


<%-- Field: turnoverFM --%>
<tr>
    <td class="detailLabelRatios">${msg['financialRatio.turnoverFM']}:</td>
    <td class="detailFieldCell">
        <h:outputText value="#{financialRatioBean.turnoverFM}">
            <f:convertNumber maxFractionDigits="2"/>
        </h:outputText>
    </td>
</tr>
                                
 