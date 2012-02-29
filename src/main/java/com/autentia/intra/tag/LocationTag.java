/* 
 *  TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L.  
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
 */

/**
 *
 */
package com.autentia.intra.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Map;

/**
 * Tag to define current logical location. This tag calls setLocation() javascript
 * function (defined in uiCore.jsp for convenience) to change location bar text, icons
 * and user information. It also adds a bean to request scope with name "location" so
 * that other tags or the page itselt can use it.
 *
 * @author ivan
 */
public class LocationTag extends TagSupport {
    /**
     * Attribute name of request under which the location place is stored
     */
    public static final String LOCATION_ATTR = "location";

    /** */
    private static final long serialVersionUID = -7464374730840030075L;

    /**
     * Logical name of location (usually JSP name without extension)
     */
    private String place;

    /**
     * Messages resource bundle
     */
    private Map msg;

    @Override
    public void release() {
        super.release();
        place = null;
        msg = null;
    }

    @Override
    public int doStartTag() throws JspException {
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        JspWriter out = pageContext.getOut();

        try {
            // Store location in request
            request.setAttribute(LOCATION_ATTR, place);
            // Render setLocation call
            out.println("<script>");
            out.print("setLocation( '");
            out.print(msg.get(place + ".location"));
            out.print("', '");
            out.print(request.getContextPath());
            out.print("/pages/privateIcons.jsf', '");
            out.print(request.getContextPath());
            out.print("/pages/userInfo.jsf' );");
            out.println("</script>");
        }
        catch (IOException e) {
            throw new JspException("Error rendering fileLink", e);
        }

        return EVAL_PAGE;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Map getMsg() {
        return msg;
    }

    public void setMsg(Map msg) {
        this.msg = msg;
    }
}