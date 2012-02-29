<%@page language="java" contentType="text/html; charset=UTF-8" %>

<%@include file="/inc/tlibs.jsp" %>

<html>
<head>
    <%@include file="/inc/uiCore.jsp" %>
    <script type="text/javascript">
        function openPDF(id, idOrg) {
            openReportParameters("document/notaSalida.pdf", "?id=" + id, "");
        }
        function openRTF(id, idOrg) {
            openReportParameters("document/notaSalida.rtf", "?id=" + id, "");
        }
    </script>
</head>
<body>

<f:loadBundle basename="com.autentia.intra.resources.messages" var="msg"/>
<i:location place="editNotaSalida" msg="${msg}"/>

<f:view>
    <%@include file="/inc/header.jsp" %>
    <h:form id="notaSalida" enctype="multipart/form-data">

        <%-- Header --%>
        <i:titleBar name="${notaSalidaBean.name}" msg="${msg}">
            <h:commandLink action="#{notaSalidaBean.save}"
                           onclick="if(!askSave('#{notaSalidaBean.id}','#{msg['question.confirmSave']}')) return false;">
                <h:graphicImage title="#{msg.entityActions_save}" value="/img/save.gif" styleClass="titleImg"/>
            </h:commandLink>
            <h:commandLink action="#{notaSalidaBean.delete}"
                           onclick="if( !confirm('#{msg['question.confirmDelete']}') ) return false;"
                           rendered="#{notaSalidaBean.puedoBorrar}">
                <h:graphicImage title="#{msg.entityActions_delete}" value="/img/delete.gif" styleClass="titleImg"/>
            </h:commandLink>
            <h:commandLink action="#{notaSalidaBean.list}" immediate="true">
                <h:graphicImage title="#{msg.entityActions_back}" value="/img/back.gif" styleClass="titleImg"/>
            </h:commandLink>
            <h:commandLink rendered="#{notaSalidaBean.id != null}" action="#"
                           onclick="openPDF('#{notaSalidaBean.id}')" immediate="true">
                <h:graphicImage title="#{msg['notaSalida.printPDF']}" value="/img/document.png" styleClass="titleImg"/>
            </h:commandLink>
            <h:commandLink action="#" title="Imprimir etiquetas"
                           onclick="openReportParameters('document/etiqueta_direccion.pdf','?id='+#{notaSalidaBean.notaSalida.clienteOrigen.id}+'&tipoDireccion=direccionEnvioInformes','');"
                           immediate="true" rendered="#{notaSalidaBean.notaSalida.clienteOrigen != null}">
                <h:graphicImage value="/img/envelope.png" styleClass="titleImg"/>
            </h:commandLink>
        </i:titleBar>

        <%-- Edition form --%>
        <table class="editTable" cellpadding="0" cellspacing="0">

                <%-- Ignored field: id --%>

                <%-- Field: name --%>
            <tr>
                <td class="editLabelRW">*${msg['notaSalida.name']}:</td>
                <td class="editFieldCell">
                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="name"/>
                        <h:inputText id="name" value="#{notaSalidaBean.name}" required="true"
                                     styleClass="requiredFieldClass"
                                     maxlength="64" validator="#{notaSalidaBean.nameValidate}"/>
                    </h:panelGroup>
                </td>
            </tr>


                <%-- Field: fecha --%>
            <tr>
                <td class="editLabelRW">*${msg['notaSalida.fecha']}:</td>

                <td class="editFieldCell">

                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="fecha"/>
                        <t:inputCalendar id="fecha" value="#{notaSalidaBean.fecha}" styleClass="requiredFieldClass"
                                         renderAsPopup="true" popupDateFormat="d/MM/yyyy"
                                         renderPopupButtonAsImage="true"
                                         popupTodayString="#{msg['calendar.today']}"
                                         popupWeekString="#{msg['calendar.week']}"
                                />
                    </h:panelGroup>

                </td>
            </tr>

                <%-- Ignored field: ownerId --%>


                <%-- Ignored field: departmentId --%>


                <%-- Ignored field: insertDate --%>


                <%-- Ignored field: updateDate --%>


                <%-- Field: peticionario --%>
            <tr>
                <td class="editLabelRW">*${msg['notaSalida.peticionario']}:</td>
                <td class="editFieldCell">
                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="peticionario"/>
                        <h:selectOneMenu id="peticionario" value="#{notaSalidaBean.notaSalida.peticionario}">
                            <f:selectItems value="#{notaSalidaBean.clients}"/>
                            <f:converter converterId="autentia.EntityConverter"/>
                        </h:selectOneMenu>
                    </h:panelGroup>
                </td>
            </tr>


                <%-- Field: clienteOrigen --%>
            <tr>
                <td class="editLabelRW">*${msg['notaSalida.clienteOrigen']}:</td>
                <td class="editFieldCell">
                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="clienteOrigen"/>
                        <h:selectOneMenu id="clienteOrigen" value="#{notaSalidaBean.notaSalida.clienteOrigen}">
                            <f:selectItems value="#{notaSalidaBean.clients}"/>
                            <f:converter converterId="autentia.EntityConverter"/>
                        </h:selectOneMenu>
                    </h:panelGroup>
                </td>
            </tr>


                <%-- buscar Informes --%>
            <tr>
                <td class="editLabelRW">Buscar informes:</td>
                <td class="editFieldCell">
                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="selectedProject"/>
                        <h:selectOneMenu id="selectedProject" value="#{notaSalidaBean.selectedProject}" immediate="true"
                                         onchange="submit()"
                                         valueChangeListener="#{notaSalidaBean.onSelectedProjectChanged}">
                            <f:selectItems value="#{notaSalidaBean.projects}"/>
                            <f:converter converterId="autentia.EntityConverter"/>
                        </h:selectOneMenu>
                    </h:panelGroup>
                </td>
            </tr>

                <%-- projectEnsayos --%>
            <tr>
                <td class="editLabelRW">${msg['notaSalida.projectEnsayos']}:</td>
                <td class="editFieldCell">
                    <%@include file="inlineEditNotaSalidaProjectEnsayo.jsp" %>
                </td>
            </tr>

                <%-- Field: unico --%>
            <tr>
                <td class="editLabelRW">${msg['notaSalida.unico']}:</td>
                <td class="editFieldCell">
                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="unico"/>
                        <h:selectBooleanCheckbox id="unico" value="#{notaSalidaBean.notaSalida.unico}"/>
                        <h:outputText value="(si está marcado, agrupa los ensayos del mismo informe)"/>
                    </h:panelGroup>
                </td>
            </tr>


                <%-- Field: observaciones --%>
            <tr>
                <td class="editLabelRW">${msg['notaSalida.observaciones']}:</td>
                <td class="editFieldCell">
                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="observaciones"/>
                        <h:inputTextarea id="observaciones" value="#{notaSalidaBean.notaSalida.observaciones}" rows="5"
                                         cols="68"/>
                    </h:panelGroup>
                </td>
            </tr>

                <%-- NotaSalida - generated by stajanov (do not edit/delete) --%>
        </table>

        <h:commandLink action="#{historicoBean.ver_historico}" immediate="true" target="_blank"
                       rendered="#{notaSalidaBean.id != null}">
            <f:param name="idObjeto" value="#{notaSalidaBean.id}"/>
            <f:param name="klazz" value="#{notaSalidaBean.notaSalida.class.name}"/>
            <h:graphicImage value="/img/historico.png" styleClass="titleImg"/>
        </h:commandLink>

    </h:form>
</f:view>

</body>
</html>  	
