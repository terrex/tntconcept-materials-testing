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
<%@page import="com.autentia.intra.manager.security.AuthenticationManager,com.autentia.intra.util.ApplicationLock" %>

<%-- 
  This page redirects to the correct page depending on the state of the application
  and user authentication.
--%>
<%
    String context = request.getContextPath();
    boolean loggedIn = (AuthenticationManager.getDefault().getCurrentPrincipal() != null);

    if (ApplicationLock.isLocked()) {
        if (loggedIn) {
            response.sendRedirect(context + "/single/console.jsf");
        } else {
            response.sendRedirect(context + "/consoleLogin.jsf");
        }
    } else {
        if (loggedIn) {
            response.sendRedirect(context + "/pages/control_panel.jsf");
        } else {
            response.sendRedirect(context + "/root.jsf");
        }
    }
%>


%>

























