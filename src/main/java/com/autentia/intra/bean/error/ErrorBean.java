/* 
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
 */
package com.autentia.intra.bean.error;

import com.autentia.intra.bean.BaseBean;
import com.autentia.intra.util.ConfigurationUtil;
import com.autentia.intra.util.FacesUtils;

import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * UI bean for manage errors
 *
 * @author Autentia
 */
public class ErrorBean extends BaseBean {

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = -4430250071117226889L;

    /**
     * The url paths to images
     */
    private final String URL_LOGO_ENABLED = "/img/showLogsEnabled.gif";
    private final String URL_LOGO_DISABLED = "/img/showLogsDisabled.gif";

    /**
     * Language resources
     */
    private static final ResourceBundle msg = ResourceBundle.getBundle("com.autentia.intra.resources.messages");

    /**
     * Current image
     */
    private String urlLogo;

    /**
     * Current image title
     */
    private String title;

    /**
     * Variable que se recoge del autentia.properties para mostrar las trazas de
     * las excepciones en la página error.jsp
     */
    private boolean showLogs = ConfigurationUtil.getDefault().getShowLogs();

    /**
     * Constructor
     */
    public ErrorBean() {
        if (showLogs) {
            urlLogo = URL_LOGO_ENABLED;
        } else {
            urlLogo = URL_LOGO_DISABLED;
        }
    }

    /**
     * Get the logs to exception produced
     *
     * @return the logs
     */
    public String getStackTrace() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map requestMap = context.getExternalContext().getRequestMap();
        Throwable ex = (Throwable) requestMap.get("javax.servlet.error.exception");
        StringWriter writer = new StringWriter();
        PrintWriter pw = new PrintWriter(writer);
        fillStackTrace(ex, pw);

        return writer.toString();
    }

    /**
     * Print the logs
     *
     * @param ex
     * @param pw
     */
    private void fillStackTrace(Throwable ex, PrintWriter pw) {
        if (null == ex) {
            return;
        }

        ex.printStackTrace(pw);

        if (ex instanceof ServletException) {
            Throwable cause = ((ServletException) ex).getRootCause();

            if (null != cause) {
                pw.println("Root Cause:");
                fillStackTrace(cause, pw);
            }
        } else {
            Throwable cause = ex.getCause();

            if (null != cause) {
                pw.println("Cause:");
                fillStackTrace(cause, pw);
            }
        }
    }

    /**
     * Change the state to showLogs var
     */
    public void changeLogMode() {
        setShowLogs(!showLogs);

        FacesUtils.renderResponse();
    }

    /**
     * @return the showLogs
     */
    public boolean isShowLogs() {
        return showLogs;
    }

    /**
     * @param showLogs the showLogs to set
     */
    public void setShowLogs(boolean showLogs) {
        this.showLogs = showLogs;
    }

    /**
     * @return the urlLogo
     */
    public String getUrlLogo() {
        if (showLogs) {
            urlLogo = URL_LOGO_ENABLED;
            title = msg.getString("error.enabledLogs");
        } else {
            urlLogo = URL_LOGO_DISABLED;
            title = msg.getString("error.disabledLogs");
        }
        return urlLogo;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
	}
}
