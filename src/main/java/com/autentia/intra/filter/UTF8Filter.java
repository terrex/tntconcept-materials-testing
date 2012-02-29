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
package com.autentia.intra.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import java.io.IOException;

/**
 * Filtro para que la aplicación acepte codificación en formato UTF-8
 */

public class UTF8Filter implements Filter {
    private static final Log log = LogFactory.getLog(UTF8Filter.class);

    private String encoding;
    private String contentType;

    /**
     * Recogemos el tipo de codificación definido en el web.xml Si no se hubiera
     * especificado ninguno se toma "UTF-8" por defecto
     */

    public void init(FilterConfig cfg) throws ServletException {
        encoding = cfg.getInitParameter("requestEncoding");
        if (encoding == null) {
            encoding = "UTF-8";
        }
        contentType = "text/html; charset=" + encoding;

        log.warn("init - forcing content type to '" + contentType + "' for resources filtered by UTF8Filter (see web.xml)");
    }

    /**
     * Metemos en la request el formato de codificacion UTF-8
     */
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain fc) throws IOException, ServletException {
        response.setContentType(contentType);
        request.setCharacterEncoding(encoding);
        fc.doFilter(request, response);
    }

    public void destroy() {
    }

}