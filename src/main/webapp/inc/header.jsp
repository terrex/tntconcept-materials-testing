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
<h:panelGrid styleClass="mainMenu"
             rowClasses="greyMenu,greyMenu" columns="1">
    <h:column>
        <h:panelGroup>
            <h:form>
                <t:jscookMenu layout="hbr" theme="ThemeOffice"
                              styleLocation="/css/jscookmenu">
                    <t:navigationMenuItems id="navigationBar"
                                           value="#{dynMenuBean.navItems}"/>
                </t:jscookMenu>
            </h:form>
        </h:panelGroup>
    </h:column>

    <h:column>
        <h:panelGroup>
            <h:panelGrid cellpadding="0" cellspacing="0" styleClass="mainMenu"
                         columnClasses="alignLeft,userInfo" columns="1">
                <h:column>
                    <%@include file="/privateIcons.jsp" %>
                </h:column>
                <%-- <h:column>
                        <f:verbatim>
                            <%@include file="/pages/userInfo.jsp"%>
                        </f:verbatim>
                    </h:column> --%>
            </h:panelGrid>
        </h:panelGroup>
    </h:column>

</h:panelGrid>
