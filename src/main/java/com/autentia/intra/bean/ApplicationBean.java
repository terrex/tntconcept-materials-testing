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
package com.autentia.intra.bean;

import com.autentia.intra.version.Version;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.faces.FacesException;
import java.util.ArrayList;
import java.util.List;
//

/**
 * Bean con scope de Application. Soporta propiedades y métodos comunes en la aplicación
 *
 * @author <a href="www.autentia.com">AUTENTIA</a>
 */

/**
 * @author Jose
 */
public class ApplicationBean extends BaseBean {
    /**
     * Número de serie
     */
    private static final long serialVersionUID = -7036483525362670757L;

    //El logger de esta clases
    private Log logger = LogFactory.getLog(this.getClass());

    //Lista con todos los perfiles de la aplicación
    private List perfilSelectItems;

    /**
     * Constructor por defecto
     *
     * @throws FacesException si ocurre un error al cargar la información de perfiles
     */
    public ApplicationBean() {
        this.perfilSelectItems = new ArrayList();
        this.logger.debug("ApplicationBean ha sido creado");
    }

    /**
     * Inicializa the ApplicationBean.
     * <p/>
     * Carga todas las tablas de referencia
     *
     * @see BaseBean#init()
     */
    protected void init() {
        try {
            this.logger.debug("Inicializa el ApplicationBean");
            /*
               List list = this.serviceLocator.getCatalogService().getAllCategories();

               for (int i=0; i<list.size(); i++) {
                   Category c = (Category)list.get(i);
                   this.categorySelectItems.add(new SelectItem(c.getId(), c.getName(), c.getDescription()));
               }*/
            this.logger.debug("Application bean está inicializado");
        } catch (Exception e) {
            String msg = "No puedo inicializar el ApplicationBean ApplicationBean " + e.toString();
            this.logger.error(msg);
            throw new FacesException(msg);
        }
    }

    public List getPerfilSelectItems() {
        return this.perfilSelectItems;
    }

    public String getDummyVariable() {
        return null;
    }

    public String getImagePath() {
        return "/img";
    }

    /**
     * Get application build date
     *
     * @return application build date
     */
    public String getBuild() {
        return Version.getApplicationVersion().toString();
    }

}
