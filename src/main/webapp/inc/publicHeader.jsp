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
<f:verbatim>
    <form name="theForm"
    action="<%=request.getContextPath()%>/j_acegi_security_check"
    method="post">
</f:verbatim>
<h:panelGrid columns="3" styleClass="mainMenu"
             columnClasses="logoMenu,,loginMenu" cellpadding="0" cellspacing="0">
    <h:column>
        <h:outputLink value="http://tntconcept.sourceforge.net"
                      target="_blank">
            <h:graphicImage value="/img/logo-grey.png" title="TNT Concept"
                            alt="TNT Concept"/>
        </h:outputLink>
    </h:column>

    <h:column>
        <%@include file="/publicIcons.jsp" %>
    </h:column>
    <h:column>
        <f:verbatim>
            <%@include file="/login.jsp" %>
        </f:verbatim>
    </h:column>

</h:panelGrid>
<f:verbatim>
    </form>
</f:verbatim>
