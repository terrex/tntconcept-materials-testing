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
</head>
<body>
<f:loadBundle basename="com.autentia.intra.resources.messages" var="msg"/>
<f:view>
<%@include file="/inc/header.jsp" %>
<%
    if (AuthenticationManager.getDefault().getCurrentPrincipal().getRoleId() != ConfigurationUtil.getDefault().getRoleClientId()) {
%>


<h:form id="idLogin">
    <table class="controlHomeTable" cellspacing="0" cellpadding="0">
        <tr>
            <td class="controlHomeCatList">

                <table width="100%" height="100%" cellspacing="0" cellpadding="0">
                    <tr>
                        <td class="bullTopLeft"></td>
                        <td class="bullTopCenterBack">
                            <div class="bullTopCenterFore">
                      <span class="bullTopCenterTitle">
                        Tablón de anuncios (categorías)
                      </span>
                            </div>
                        </td>
                        <td class="bullTopRight"></td>
                    </tr>
                    <tr>
                        <td class="bullLeft"></td>
                        <td class="bullCenter">

                            <t:dataTable id="dataCategories" styleClass="bullTable"
                                         headerClass="bullTableHeader"
                                         footerClass="bullTableHeader"
                                         columnClasses="bullTableColCategory"
                                         var="category" value="#{bulletinBoardCategoryBean.all}"
                                         preserveDataModel="true" rows="15" cellpadding="0" cellspacing="0"
                                    >
                                <h:column>
                                    <h:panelGroup>
                                        <t:commandLink action="#{bulletinBoardBean.selectCategory}"
                                                       styleClass="bullTableCatLink" immediate="true">
                                            <f:param name="id" value="#{category.id}"/>
                                            <f:verbatim><img src="<%=request.getContextPath()%>/img/arrow.gif"
                                                             border="0"/>&nbsp;</f:verbatim>
                                            <h:outputText value="#{category.name}"/>
                                            <f:verbatim>&nbsp;&nbsp;(</f:verbatim>
                                            <h:outputText value="#{category.numMessages}"/>
                                            <f:verbatim> mensajes)</f:verbatim>
                                        </t:commandLink>
                                    </h:panelGroup>
                                </h:column>
                            </t:dataTable>

                        </td>
                        <td class="bullRight"></td>
                    </tr>
                    <tr>
                        <td colspan="3" class="bullBottom"></td>
                    </tr>
                </table>

            </td>
        </tr>
        <tr>
            <td class="controlHomeMsgList">

                <table width="100%" height="100%" cellspacing="0" cellpadding="0">
                    <tr>
                        <td class="bullTopLeft"></td>
                        <td class="bullTopCenterBack">
                            <div class="bullTopCenterFore">
                      <span class="bullTopCenterTitle">
                        Lista de mensajes
                      </span>
                            </div>
                        </td>
                        <td class="bullTopRight"></td>
                    </tr>
                    <tr>
                        <td class="bullLeft"></td>
                        <td class="bullCenter">
                            <t:dataTable id="dataMensajes" styleClass="bullTable"
                                         headerClass="bullTableHeader"
                                         footerClass="bullTableHeader"
                                         rowClasses="bullTableRowO,bullTableRowE"
                                         columnClasses="bullTableColTitle,bullTableColDate,bullTableColUser"
                                         rowOnMouseOver="this.style.backgroundColor='#DBDBD1'"
                                         rowOnMouseOut="this.style.backgroundColor='#FFFFE0'"
                                         rowOnClick="this.style.backgroundColor='#DBDBD1'"
                                         rowOnDblClick="this.style.backgroundColor='#DBDBD1'"
                                         var="message" value="#{bulletinBoardBean.all}"
                                         preserveDataModel="true" rows="15" cellpadding="0" cellspacing="0"
                                         rendered="#{bulletinBoardBean.searchCategory != null}">

                                <h:column>
                                    <f:facet name="header">
                                        &nbsp;&nbsp;<h:outputText value="#{msg['bulletinBoard.title']}"/>
                                    </f:facet>
                                    <h:panelGroup>
                                        <t:commandLink action="#{bulletinBoardBean.detail}" immediate="true">
                                            <h:outputText value="#{message.title}"/>
                                            <f:param name="id" value="#{message.id}"/>
                                        </t:commandLink>
                                        <h:graphicImage url="/img/paperclip.gif"
                                                        rendered="#{! empty message.documentPath}"/>
                                    </h:panelGroup>
                                </h:column>

                                <h:column>
                                    <f:facet name="header">
                                        &nbsp;&nbsp;<h:outputText value="#{msg['bulletinBoard.creationDate']}"/>
                                    </f:facet>
                                    <f:verbatim>&nbsp;&nbsp;</f:verbatim>
                                    <h:outputText value="#{message.creationDate}" converter="autentia.dateConverter"/>
                                </h:column>

                                <h:column>
                                    <f:facet name="header">
                                        <h:outputText value="#{msg['bulletinBoard.user']}"/>
                                    </f:facet>
                                    <h:outputText value="#{message.user.name}"/>
                                </h:column>

                            </t:dataTable>
                        </td>
                        <td class="bullRight"></td>
                    </tr>
                    <tr>
                        <td colspan="3" class="bullBottom"></td>
                    </tr>
                </table>

            </td>
        </tr>
    </table>

</h:form>


<% } else { %>
<h:form id="idLogin">
    <table class="controlHomeTable" cellspacing="0" cellpadding="0">
        <tr>
            <td class="controlHomeMsgList">

                <table width="100%" height="100%" cellspacing="0" cellpadding="0">
                    <tr>
                        <td class="bullTopLeft"></td>
                        <td class="bullTopCenterBack">
                            <div class="bullTopCenterFore">
                      <span class="bullTopCenterTitle">
                        Lista de mensajes p&uacute;blicos
                      </span>
                            </div>
                        </td>
                        <td class="bullTopRight"></td>
                    </tr>
                    <tr>
                        <td class="bullLeft"></td>
                        <td class="bullCenter">
                            <t:dataTable id="dataMensajes" styleClass="bullTable"
                                         headerClass="bullTableHeader"
                                         footerClass="bullTableHeader"
                                         rowClasses="bullTableRowO,bullTableRowE"
                                         columnClasses="bullTableColTitle,bullTableColDate,bullTableColUser,bullTableColMessage"
                                         rowOnMouseOver="this.style.backgroundColor='#DBDBD1'"
                                         rowOnMouseOut="this.style.backgroundColor='#FFFFE0'"
                                         rowOnClick="this.style.backgroundColor='#DBDBD1'"
                                         rowOnDblClick="this.style.backgroundColor='#DBDBD1'"
                                         var="message" value="#{bulletinBoardBean.public}"
                                         preserveDataModel="true" rows="15" cellpadding="0" cellspacing="0">

                                <h:column>
                                    <f:facet name="header">
                                        &nbsp;&nbsp;<h:outputText value="#{msg['bulletinBoard.title']}"/>
                                    </f:facet>
                                    <h:panelGroup>
                                        <t:commandLink action="#{bulletinBoardBean.detailPublic}" immediate="true">
                                            <h:outputText value="#{message.title}"/>
                                            <f:param name="id" value="#{message.id}"/>
                                        </t:commandLink>
                                        <h:graphicImage url="/img/paperclip.gif"
                                                        rendered="#{! empty message.documentPath}"/>
                                    </h:panelGroup>
                                </h:column>

                                <h:column>
                                    <f:facet name="header">
                                        &nbsp;&nbsp;<h:outputText value="#{msg['bulletinBoard.creationDate']}"/>
                                    </f:facet>
                                    <f:verbatim>&nbsp;&nbsp;</f:verbatim>
                                    <h:outputText value="#{message.creationDate}" converter="autentia.dateConverter"/>
                                </h:column>

                                <h:column>
                                    <f:facet name="header">
                                        <h:outputText value="#{msg['bulletinBoard.user']}"/>
                                    </f:facet>
                                    <h:outputText value="#{message.user.name}"/>
                                </h:column>

                                <h:column>
                                    <f:facet name="header">
                                        <h:outputText value="#{msg['bulletinBoard.message']}"/>
                                    </f:facet>
                                    <h:outputText value="#{message.message}"/>
                                </h:column>

                            </t:dataTable>
                        </td>
                        <td class="bullRight"></td>
                    </tr>
                    <tr>
                        <td colspan="4" class="bullBottom"></td>
                    </tr>
                </table>

            </td>
        </tr>
    </table>

</h:form>


<% } %>


</f:view>


</body>
</html>  		

