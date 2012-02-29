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
<%@page import="com.autentia.intra.util.ConfigurationUtil" %>
<%-- Common things to include in document's HEAD part --%>

<script src="<%=request.getContextPath()%>/script/common.jsf" language="Javascript" type="text/javascript"
        charset="UTF-8"></script>
<% if (ConfigurationUtil.getDefault().isUsingExternalCss()) { %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/docroot/css/estilos.css" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/docroot/css/dynamicStyles.css" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/docroot/css/staticStyles.css" type="text/css">
<% } else { %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/estilos.css" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/dynamicStyles.jsf" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/staticStyles.css" type="text/css">
<% } %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">