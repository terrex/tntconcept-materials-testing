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
/*
 * BaseBean.java
 */
package com.autentia.intra.bean;

import com.autentia.intra.util.BeanUtils;
import com.autentia.intra.util.FacesUtils;
import com.autentia.intra.util.HibernateUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import java.util.Iterator;


/**
 * Bean del que heredan todos los bean de la aplicaci�n con operaciones comunes en todos ellos.
 * Adem�s implementa el interfaz javax.faces.event.PhaseListener para controlar las aperturas
 * y cierres de conexiones de hibernate
 *
 * @author <a href="www.autentia.com">AUTENTIA</a>
 */
public class BaseBean implements PhaseListener {

    /**
     * N�mero de serie
     */
    private static final long serialVersionUID = 6390940830550036297L;
    /**
     * Logger de esta clase
     */
    protected final Log logger = LogFactory.getLog(this.getClass());

    /**
     * Constructor
     */
    public BaseBean() {
    }

    /**
     * Inicializa los beans
     */
    protected void init() {
    }

    /**
     * Escucha el comienzo de cada fase de JSF
     *
     * @param pe Evento
     */
    public void beforePhase(PhaseEvent pe) {

        this.logger.debug("beforePhase " + pe.getPhaseId());

    }

    /**
     * Escucha el final de cada fase de JSF
     *
     * @param pe Evento
     */
    public void afterPhase(PhaseEvent pe) {
        this.logger.debug("afterPhase " + pe.getPhaseId());
        // En la fase de render_response, que es la �ltima que tiene un bean, cierro
        // la conexi�n de hibernate

        if (pe.getPhaseId() == PhaseId.RENDER_RESPONSE) {
            this.logger.debug("afterPhase. " + pe.getClass() + ": cierro la conexi�n de hibernate...");
            HibernateUtil.closeSession();
            this.logger.debug("afterPhase. " + pe.getClass() + ": sessi�n cerrada!!!");
        }
    }

    /**
     * Devuelve un identificador de la fase
     */
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

    /**
     * Gesti�n de errores. TODO: completar
     */
    public String getReturnError(String msg, Throwable ex) {
        printError(msg, ex);
        return NavigationResults.GO_ERROR;
    }

    /**
     * Imprime un error
     */
    public void printError(String msg, Throwable ex) {
        String pila = BeanUtils.getErrorDesc(ex);
        this.logger.error(pila, ex);
        StringBuffer totalError = new StringBuffer(msg);
        totalError.append("<br><br>");
        totalError.append(pila);
        FacesUtils.addErrorMessage(null, totalError.toString() + ": Intra error.");
    }

    /**
     * JSF es est�pidamente listillo y para comandos "immediate" pasa totalmente
     * de los cambios que hagamos en el modelo. Por alguna est�pida raz�n,
     * con "immediate" conserva SIEMPRE los datos introducidos por el usuario.
     * Ya pod�an ponerle una opci�n, m�xime cuando esto ya ha dado problemas
     * al menos desde 2004 (ver http://swforum.sun.com/jive/thread.jspa?threadID=48817&messageID=162382)
     */
    protected void refreshDatos() {
        refreshDatos((UIComponent) FacesContext.getCurrentInstance().getViewRoot());
    }

    /**
     * Refresca los datos de todos los componentes editables
     *
     * @param comp el componente
     */
    protected void refreshDatos(UIComponent comp) {
        if (comp instanceof EditableValueHolder)
            ((EditableValueHolder) comp).setSubmittedValue(null);
        for (Iterator i = comp.getChildren().iterator(); i.hasNext();)
            refreshDatos((UIComponent) i.next());
    }

    /**
     * Refresca el valor de un determinado componente
     *
     * @param idComponente
     */
    protected void refreshDato(String idComponente) {
        EditableValueHolder comp = (EditableValueHolder) FacesContext.getCurrentInstance().getViewRoot().findComponent(idComponente);
        comp.setSubmittedValue(null);
    }

    /**
     * Método que se ejecuta después de Save() y que cada implementación
     * podrá sobre escribir con funcionalidad adicional.
     *
     * @param result
     * @return
     */
    public String doAfterSave(String result) {
        return result;
    }

    /**
     * Método que se ejecuta antes de Save() y que cada implementación
     * podrá sobre escribir con funcionalidad adicional.
     *
     * @return
     */
    public String doBeforeSave() {
        return null;
    }

}
