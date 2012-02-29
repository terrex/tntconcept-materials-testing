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

/**
 * Tag to render a link to an attached file. If the file is empty nothing is rendered.
 * This tag relies on a JS function with signature openFile(type,id,file,mime) that
 * must be added by hand to the HTML page (for user's convenience, there is a default
 * implementation of this function in uiCore.jsp when inside the intraweb project).
 *
 * @author ivan
 */
public class FileLinkTag extends TagSupport {
    /** */
    private static final long serialVersionUID = -8676144583207734318L;

    /**
     * Type of object the file is attached to
     */
    private String type;

    /**
     * Id of object the file is attached to
     */
    private int objectId;

    /**
     * File name
     */
    private String file;

    /**
     * File mime type
     */
    private String mime;

    @Override
    public void release() {
        super.release();
        type = null;
        objectId = 0;
        file = null;
        mime = null;
    }

    @Override
    public int doStartTag() throws JspException {
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        JspWriter out = pageContext.getOut();

        try {
            if (file != null && !file.equals("")) {
                out.print("<a href=\"#\" onclick=\"openFile(");
                out.print("'" + type + "',");
                out.print(objectId + ",");
                out.print("'" + file + "',");
                out.println("'" + mime + "');return false;\">");

                out.print("<img src='" + request.getContextPath() + "/img/yellow-folder-open.png'");
                out.println(" style='border:0; vertical-align:middle;'>");

                out.print(" " + file);
                out.print("</a>");
                out.print("<br/>");
            }
        }
        catch (IOException e) {
            throw new JspException("Error rendering fileLink", e);
        }

        return EVAL_PAGE;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
