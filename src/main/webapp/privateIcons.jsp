<%@ page import="com.autentia.intra.manager.security.AuthenticationManager" %>
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
<h:form id="private">
    <h:panelGrid styleClass="iconsToolbar" columns="9" cellpadding="0"
                 cellspacing="0"
                 columnClasses="betweenIcons,alignTop,betweenIcons,alignTop,betweenIcons,alignTop,betweenIcons,alignTop">
        <h:column></h:column>
        <h:column>
            <f:verbatim>
                <a href="<%=request.getContextPath()%>/pages/control_panel.jsf"><img
                        src="<%=request.getContextPath()%>/img/home.png"></a>
            </f:verbatim>
        </h:column>
        <h:column></h:column>
        <h:column>
            <h:outputLink onclick="refresh()" styleClass="icon">
                <t:graphicImage value="/img/reload.png" styleClass="titleImg"
                                title="Actualizar" border="0"/>
            </h:outputLink>
        </h:column>
        <h:column></h:column>
        <h:column>
            <t:graphicImage onclick="printScreen();" value="/img/printer.png"
                            styleClass="titleImg" title="Imprimir" border="0"
                            style="cursor: pointer"/>
        </h:column>
        <h:column></h:column>
        <h:column>
            <t:commandLink action="#{errorBean.changeLogMode}" immediate="true">
                <t:graphicImage value="#{errorBean.urlLogo}" styleClass="titleImg"
                                title="#{errorBean.title}" border="0"/>
            </t:commandLink>
            <h:outputText
                    value="&nbsp;&plusmn;&nbsp;&le;&nbsp;&ge;&nbsp;&mu;&nbsp;&Delta;&nbsp;&sup2;&nbsp;&sup3;&nbsp;"
                    escape="false"/>
        </h:column>
    </h:panelGrid>
</h:form>
