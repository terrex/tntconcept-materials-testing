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
<%@page isErrorPage="true" language="java" contentType="text/html; charset=UTF-8" %>

<%@include file="/inc/tlibs.jsp" %>


<html>
<f:view>
    <f:loadBundle basename="com.autentia.intra.resources.messages" var="msg"/>
    <head>
        <%@include file="/inc/uiCore.jsp" %>
        <title>
            <h:outputText value="#{msg['error.title']}"/>
        </title>

    </head>

    <body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
    <%@include file="/inc/publicHeader.jsp" %>
    <h:form>
        <div align="center" style="top:50px">
            <h:panelGrid cellpadding="5" cellspacing="20">
                <h:panelGrid columns="2" align="center" columnClasses="msgLabel,msgValue">
                    <h:graphicImage value="/img/error.gif" styleClass="titleImg"/>
                    <h:outputText value="#{msg['error.server_error']}" styleClass="errorPage"/>
                </h:panelGrid>

                <h:panelGrid align="center">
                    <h:commandLink onclick="redirectToBugs();">
                        <h:outputText value="#{msg['error.infoBug']}" styleClass="publicVersion"/>
                        <h:graphicImage value="/img/inform-bug.gif" styleClass="titleImg"/>
                    </h:commandLink>
                </h:panelGrid>

                <h:panelGrid columns="2" align="center">
                    <h:graphicImage value="/img/back.gif" styleClass="titleImg"/>
                    <h:commandLink action="index" value="#{msg['entityActions_home']}" styleClass="publicVersion"/>
                </h:panelGrid>
            </h:panelGrid>
        </div>
    </h:form>
    </body>
</f:view>
</html>