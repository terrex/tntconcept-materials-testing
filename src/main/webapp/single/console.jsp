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

    <f:loadBundle basename="com.autentia.intra.resources.messages" var="msg"/>

    <title>
        ${msg['console.title']}
    </title>

    <script>
        var shownCommand = null;

        function showCommand(commandId)
        {
            if (shownCommand != null) {
                document.getElementById(shownCommand).style.display = "none";
            }
            document.getElementById(commandId).style.display = "block";
            showCommand = commandId;
        }
    </script>

</head>
<body>

<f:view>
    <h:form>

        <h:outputText value="#{msg['console.prompt']}"/>
        <br/>
        <br/>

        <h:messages layout="table"/>
        <br/>

        <%-- Command links --%>
        <div style="height: 14em; overflow: auto;">
            <h:commandLink action="logout" value="#{msg['console.logout']}"/><br/>
            <a href="#" onclick="showCommand('migrateDB')">${msg['console.migrateDB']}</a><br/>
        </div>
        <br/>

        <%-- Command link explanations --%>
        <div style="height: 14em; overflow: auto;">
            <div id="migrateDB" style="display: none;">
                <h:commandButton action="#{consoleBean.migrateDB}" value="#{msg['console.migrateDB']}"/>
                <h:outputText value="#{msg['console.migrateDB.explanation']}" escape="false"/>
            </div>
        </div>

    </h:form>
</f:view>

</body>
</html>
