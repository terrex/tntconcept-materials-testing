<%--
* TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L.
*	Copyright (C) 2007 Autentia Real Bussiness Solution S.L.
*
* 	This program is free software; you can redistribute it and/or
* 	modify it under the terms of the GNU General Public License
* 	as published by the Free Software Foundation; either version 2
* 	of the License, or (at your option) any later version.
*
* 	This program is distributed in the hope that it will be useful,
* 	but WITHOUT ANY WARRANTY; without even the implied warranty of
* 	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* 	GNU General Public License for more details.
*
* 	You should have received a copy of the GNU General Public License
* 	along with this program; if not, write to the Free Software
* 	Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
*
* 	Autentia Real Bussiness Solution S.L.
* 	Tlf: +34 91 675 33 06, +34 655 99 11 72
* 	Fax: +34 91 656 65 04
* 	info@autentia.com
*
--%>


<%@page language="java" contentType="text/html; charset=UTF-8" %>

<%@include file="/inc/tlibs.jsp" %>

<html>
<head>
    <%@include file="/inc/uiCore.jsp" %>
    <script type="text/javascript">
        function openPDF(id, idOrg) {
            openReportParameters("document/albaran.pdf", "?id=" + id + "&idOrg=" + idOrg, "");
        }
        function openRTF(id, idOrg) {
            openReportParameters("document/albaran.rtf", "?id=" + id + "&idOrg=" + idOrg, "");
        }
    </script>
</head>
<body>

<!-- editAlbaran.jsp: generated by stajanov code generator -->
<f:loadBundle basename="com.autentia.intra.resources.messages" var="msg"/>
<i:location place="editAlbaran" msg="${msg}"/>

<f:view>
    <%@include file="/inc/header.jsp" %>
    <h:form id="albaran" enctype="multipart/form-data">

        <%-- Header --%>
        <i:titleBar name="${albaranBean.name}" msg="${msg}">
            <h:commandLink action="#{albaranBean.save}"
                           onclick="if(!askSave('#{albaranBean.id}','#{msg['question.confirmSave']}')) return false;">
                <h:graphicImage title="#{msg.entityActions_save}" value="/img/save.gif" styleClass="titleImg"/>
            </h:commandLink>
            <h:commandLink action="#{albaranBean.delete}"
                           onclick="if( !confirm('#{msg['question.confirmDelete']}') ) return false;"
                           rendered="#{albaranBean.puedoBorrar}">
                <h:graphicImage title="#{msg.entityActions_delete}" value="/img/delete.gif" styleClass="titleImg"/>
            </h:commandLink>
            <h:commandLink action="#{albaranBean.list}" immediate="true">
                <h:graphicImage title="#{msg.entityActions_back}" value="/img/back.gif" styleClass="titleImg"/>
            </h:commandLink>
            <h:commandLink rendered="#{albaranBean.id != null}" action="#"
                           onclick="openPDF('#{albaranBean.id}','#{albaranBean.idSelectedOrganization}')"
                           immediate="true">
                <h:graphicImage title="#{msg['albaran.printPDF']}" value="/img/document.png" styleClass="titleImg"/>
            </h:commandLink>
            <h:commandLink action="#" title="Imprimir etiquetas"
                           onclick="openReportParameters('document/etiqueta_direccion.pdf','?id='+#{albaranBean.client.id}+'&tipoDireccion=direccionEnvioInformes','');"
                           immediate="true" rendered="#{albaranBean.client != null}">
                <h:graphicImage value="/img/envelope.png" styleClass="titleImg"/>
            </h:commandLink>
        </i:titleBar>

        <%-- Edition form --%>
        <table class="editTable" cellpadding="0" cellspacing="0">


                <%-- Albaran - generated by stajanov (do not edit/delete) --%>


                <%-- Ignored field: id --%>


                <%-- Field: client --%>
            <tr>
                <td class="editLabelRW">*${msg['albaran.client']}:</td>
                <td class="editFieldCell">
                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="client"/>
                        <h:selectOneMenu id="client" value="#{albaranBean.client}" immediate="true" onchange="submit()"
                                         required="true" styleClass="requiredFieldClass"
                                         valueChangeListener="#{albaranBean.onSelectedOrganizationChanged}">
                            <f:selectItems value="#{albaranBean.clients}"/>
                            <f:converter converterId="autentia.EntityConverter"/>
                        </h:selectOneMenu>
                    </h:panelGroup>
                </td>
            </tr>


                <%-- Field: name --%>
            <tr>
                <td class="editLabelRW">*${msg['albaran.name']}:</td>
                <td class="editFieldCell">
                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="name"/>
                        <h:inputText id="name" value="#{albaranBean.name}" required="true"
                                     styleClass="requiredFieldClass"
                                     maxlength="64" validator="#{albaranBean.nameValidate}"/>
                    </h:panelGroup>
                </td>
            </tr>


                <%-- Field: fecha --%>
            <tr>
                <td class="editLabelRW">*${msg['albaran.fecha']}:</td>

                <td class="editFieldCell">

                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="fecha"/>
                        <t:inputCalendar id="fecha" value="#{albaranBean.fecha}" styleClass="requiredFieldClass"
                                         renderAsPopup="true" popupDateFormat="d/MM/yyyy"
                                         renderPopupButtonAsImage="true"
                                         popupTodayString="#{msg['calendar.today']}"
                                         popupWeekString="#{msg['calendar.week']}"
                                />
                    </h:panelGroup>

                </td>
            </tr>


                <%-- Field: devuelto --%>
            <tr>
                <td class="editLabelRW">${msg['albaran.devuelto']}:</td>
                <td class="editFieldCell">
                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="devuelto"/>
                        <h:selectBooleanCheckbox id="devuelto" value="#{albaranBean.devuelto}"/>
                        <h:outputText value="(indica si el envío incluye material no ensayado devuelto)"/>
                    </h:panelGroup>
                </td>
            </tr>

                <%-- Field: bill --%>
            <tr>
                <td class="editLabelRW">${msg['albaran.bill']}:</td>
                <td class="editFieldCell">
                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="bill"/>
                        <h:selectOneMenu id="bill" value="#{albaranBean.albaran.bill}"
                                         disabled="#{albaranBean.albaran.bill != null}"
                                         readonly="#{albaranBean.albaran.bill != null}">
                            <f:selectItems value="#{albaranBean.bills}"/>
                            <f:converter converterId="autentia.EntityConverter"/>
                        </h:selectOneMenu>
                    </h:panelGroup>
                </td>
            </tr>
                <%-- Ignored field: ownerId --%>


                <%-- Ignored field: departmentId --%>


                <%-- Ignored field: insertDate --%>


                <%-- Ignored field: updateDate --%>


                <%-- Field: interactions --%>

            <tr>
                <td class="editLabelRW">${msg['albaran.projects']}:</td>

                <td class="editFieldCell">
                    <%@include file="inlineEditAlbaranProject.jsp" %>
                </td>
            </tr>


            <tr>
                <td class="editLabelRW">${msg['albaran.observaciones']}:</td>
                <td class="editFieldCell">
                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="observaciones"/>
                        <h:inputTextarea id="observaciones" value="#{albaranBean.albaran.observaciones}" rows="5"
                                         cols="68"/>
                    </h:panelGroup>
                </td>
            </tr>

                <%-- Albaran - generated by stajanov (do not edit/delete) --%>
        </table>

        <h:commandLink action="#{historicoBean.ver_historico}" immediate="true" target="_blank"
                       rendered="#{albaranBean.id != null}">
            <f:param name="idObjeto" value="#{albaranBean.id}"/>
            <f:param name="klazz" value="#{albaranBean.albaran.class.name}"/>
            <h:graphicImage value="/img/historico.png" styleClass="titleImg"/>
        </h:commandLink>

    </h:form>
</f:view>

</body>
</html>  	