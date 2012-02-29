<%@page language="java" contentType="text/html; charset=UTF-8" %>

<%@include file="/inc/tlibs.jsp" %>

<html>
<head>
    <%@include file="/inc/uiCore.jsp" %>
</head>
<body>

<!-- notaSalidas.jsp -->
<f:loadBundle basename="com.autentia.intra.resources.messages" var="msg"/>
<i:location place="notaSalidas" msg="${msg}"/>

<f:view>
    <%@include file="/inc/header.jsp" %>
    <h:form id="notaSalidas">

        <%-- Header --%>
        <i:titleBar msg="${msg}">
            <h:commandLink action="#{notaSalidaBean.search}">
                <h:graphicImage rendered="#{ notaSalidaBean.search.searchActive}" title="#{msg.entityActions_filtered}"
                                value="/img/search_applied.gif" styleClass="titleImg"/>
                <h:graphicImage rendered="#{!notaSalidaBean.search.searchActive}" title="#{msg.entityActions_search}"
                                value="/img/search.gif" styleClass="titleImg"/>
            </h:commandLink>
            <h:commandLink action="#{notaSalidaBean.reset}">
                <h:graphicImage rendered="#{notaSalidaBean.search.searchActive}" title="#{msg.entityActions_reset}"
                                value="/img/eraser.png" styleClass="titleImg"/>
            </h:commandLink>
            <h:commandLink action="#{notaSalidaBean.create}" rendered="#{notaSalidaBean.createAvailable}">
                <h:graphicImage title="#{msg.entityActions_create}" value="/img/new.gif" styleClass="titleImg"/>
            </h:commandLink>
        </i:titleBar>


        <%-- ABC quick pager --%>
        <div class="abcPager">
            <h:outputText value="#{msg['abcPager.title']}" styleClass="abcPagerTitle"/>
            <a:abcPager styleClass="abcPagerLetter" selectedStyleClass="abcPagerSelectedLetter"
                        value="#{notaSalidaBean.letter}" action="#{notaSalidaBean.letterClicked}"
                        allowUnselect="true" unselectImage="/img/no_funnel.png"/>
        </div>


        <%-- List of notaSalidas --%>
        <t:dataTable id="list" var="notaSalida" value="#{notaSalidaBean.all}" preserveDataModel="false"
                     cellpadding="0" cellspacing="0" styleClass="listTable"
                     headerClass="listHeaderCell" footerClass="listFooter"
                     rows="#{settingBean.mySettings.listSize}" rowClasses="listRowO,listRowE"
                     columnClasses="listCmdCell,listNotaSalidaNombre,listNotaSalidaFecha,listNotaSalidaNumero"
                     sortColumn="#{notaSalidaBean.sortColumn}" sortAscending="#{notaSalidaBean.sortAscending}"
                     rowOnMouseOver="this.savedClassName=this.className;this.className='listRowSel';"
                     rowOnMouseOut="this.className=this.savedClassName;">

            <%-- Commands --%>
            <h:column>
                <f:facet name="header">
                    <f:verbatim>-</f:verbatim>
                </f:facet>
                <t:commandLink action="#{notaSalidaBean.detail}" immediate="true">
                    <f:param name="id" value="#{notaSalida.id}"/>
                    <h:graphicImage title="#{msg.entityActions_detail}" value="/img/detail.gif" styleClass="cmdImg"/>
                </t:commandLink>
            </h:column>


            <%-- Ignored field: id --%>

            <%-- Field: name --%>
            <h:column>
                <f:facet name="header">
                    <t:commandSortHeader styleClass="listHeader" columnName="name">
                        <f:facet name="ascending">
                            <t:graphicImage value="/img/ascending-arrow.gif" border="0"/>
                        </f:facet>
                        <f:facet name="descending">
                            <t:graphicImage value="/img/descending-arrow.gif" border="0"/>
                        </f:facet>
                        <f:verbatim>Nombre</f:verbatim>
                    </t:commandSortHeader>
                </f:facet>

                <t:commandLink action="#{notaSalidaBean.detail}" immediate="true">
                    <f:param name="id" value="#{notaSalida.id}"/>
                    <%-- Referenced field --%>
                    <h:outputText value="#{notaSalida.name}"/>

                </t:commandLink>

            </h:column>


            <%-- Field: fecha --%>
            <h:column>
                <f:facet name="header">
                    <t:commandSortHeader styleClass="listHeader" columnName="fecha">
                        <f:facet name="ascending">
                            <t:graphicImage value="/img/ascending-arrow.gif" border="0"/>
                        </f:facet>
                        <f:facet name="descending">
                            <t:graphicImage value="/img/descending-arrow.gif" border="0"/>
                        </f:facet>
                        <f:verbatim>Fecha</f:verbatim>
                    </t:commandSortHeader>
                </f:facet>

                <t:commandLink action="#{notaSalidaBean.detail}" immediate="true">
                    <f:param name="id" value="#{notaSalida.id}"/>
                    <%-- Date field --%>
                    <h:outputText value="#{notaSalida.fecha}" converter="autentia.dateConverter"/>

                </t:commandLink>

            </h:column>


            <%-- Ignored field: ownerId --%>


            <%-- Ignored field: departmentId --%>


            <%-- Ignored field: insertDate --%>


            <%-- Ignored field: updateDate --%>


        </t:dataTable>

        <%-- Paginator control --%>
        <%@include file="/inc/paginator.jsp" %>

    </h:form>
</f:view>

</body>
</html>  		

