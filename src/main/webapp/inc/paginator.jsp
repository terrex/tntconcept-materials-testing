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

<%-- Paginator control --%>
<t:dataScroller for="list" fastStep="10" paginator="true"
                paginatorMaxPages="10" renderFacetsIfSinglePage="false"
                styleClass="scroller" paginatorTableClass="paginator"
                paginatorColumnClass="paginatorCell"
                paginatorActiveColumnClass="paginatorActiveCell">
    <f:facet name="first">
        <t:graphicImage value="/img/arrow-first.gif" border="0"/>
    </f:facet>
    <f:facet name="last">
        <t:graphicImage value="/img/arrow-last.gif" border="0"/>
    </f:facet>
    <f:facet name="previous">
        <t:graphicImage value="/img/arrow-previous.gif" border="0"/>
    </f:facet>
    <f:facet name="next">
        <t:graphicImage value="/img/arrow-next.gif" border="0"/>
    </f:facet>
    <f:facet name="fastrewind">
        <t:graphicImage value="/img/arrow-fr.gif" border="0"/>
    </f:facet>
    <f:facet name="fastforward">
        <t:graphicImage value="/img/arrow-ff.gif" border="0"/>
    </f:facet>
</t:dataScroller>
