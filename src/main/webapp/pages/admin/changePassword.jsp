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
    <meta HTTP-EQUIV="Content-Type" CONTENT="text/html;charset=iso-8859-1">
    <title>AUTENTIA - CAMBIO DE PASSWORD</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/estilos.css"/>
</head>
<body>
<f:loadBundle basename="com.autentia.intra.resources.messages" var="mensajes"/>
<i:location place="changePassword" msg="${mensajes}"/>

<f:view>
    <%@include file="/inc/header.jsp" %>
    <h:form id="changePasswordBean">


        <%-- Header --%>
        <i:titleBar msg="${mensajes}">
            <h:commandLink action="#{changePasswordBean.changePassword}">
                <h:graphicImage title="#{mensajes.entityActions_save}" value="/img/save.gif" styleClass="titleImg"/>
            </h:commandLink>
        </i:titleBar>


        <table class="editTable" cellpadding="0" cellspacing="0">
            <tr>
                <td class="editLabelRW">${mensajes['changePassword.oldPassword']}:</td>
                <td class="editFieldCell">

                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="passwordOld"/>
                        <h:inputSecret id="passwordOld" value="#{changePasswordBean.passwordOld}" size="70"
                                       maxlength="50" required="true">
                            <f:validateLength maximum="50" minimum="5"/>
                        </h:inputSecret>
                    </h:panelGroup>

                </td>
            <tr>
                <td class="editLabelRW">${mensajes['changePassword.newPassword']}:</td>
                <td class="editFieldCell">

                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="password"/>
                        <h:inputSecret id="password" value="#{changePasswordBean.password}" size="70" maxlength="50"
                                       required="true">
                            <f:validateLength maximum="50" minimum="5"/>
                        </h:inputSecret>
                    </h:panelGroup>

                </td>
            </tr>

            <tr>
                <td class="editLabelRW">${mensajes['changePassword.repeatPassword']}:</td>
                <td class="editFieldCell">

                    <h:panelGroup>
                        <h:message styleClass="error" showSummary="true" showDetail="false" for="passwordRepe"/>
                        <h:inputSecret id="passwordRepe" value="#{changePasswordBean.passwordRepe}" size="70"
                                       maxlength="50" required="true">
                            <f:validateLength maximum="50" minimum="5"/>
                        </h:inputSecret>
                    </h:panelGroup>

                    <table border=0 width="100%">
                        <tr>
                            <td width="100%" align="center">
                                <h:messages id="messageList" styleClass="error" showSummary="false" showDetail="true"/>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>

    </h:form>

</f:view>

</body>
</html>  
