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


package com.autentia.intra.manager.contacts;

import com.autentia.intra.businessobject.PautaPrecio;
import com.autentia.intra.dao.SortCriteria;
import com.autentia.intra.dao.hibernate.PautaPrecioDAO;
import com.autentia.intra.dao.search.PautaPrecioSearch;
import com.autentia.intra.util.SpringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;


public class PautaPrecioManager {

/* generated by stajanov (do not edit/delete) */


    /**
     * Logger
     */
    private static final Log log = LogFactory.getLog(PautaPrecioManager.class);

    /**
     * PautaPrecio DAO *
     */
    private PautaPrecioDAO pautaPrecioDAO;

    /**
     * Get default PautaPrecioManager as defined in Spring's configuration file.
     *
     * @return the default singleton PautaPrecioManager
     */
    public static PautaPrecioManager getDefault() {
        return (PautaPrecioManager) SpringUtils.getSpringBean("managerPautaPrecio");
    }

    /**
     * Empty constructor needed by CGLIB (Spring AOP)
     */
    protected PautaPrecioManager() {
    }

    /**
     * Default constructor
     *
     * @deprecated do not construct managers alone: use Spring's declared beans
     */
    public PautaPrecioManager(PautaPrecioDAO pautaPrecioDAO) {
        this.pautaPrecioDAO = pautaPrecioDAO;
    }

    /**
     * List pautaPrecios.
     *
     * @param search search filter to apply
     * @param sort   sorting criteria
     * @return the list of all pautaPrecios sorted by requested criterion
     */
    public List<PautaPrecio> getAllEntities(PautaPrecioSearch search, SortCriteria sort) {
        return pautaPrecioDAO.search(search, sort);
    }

    /**
     * Get pautaPrecio by primary key.
     *
     * @return pautaPrecio selected by id.
     */
    public PautaPrecio getEntityById(int id) {
        return pautaPrecioDAO.getById(id);
    }

    /**
     * Insert pautaPrecio.
     */
    public void insertEntity(PautaPrecio pautaPrecio) {
        pautaPrecioDAO.insert(pautaPrecio);
    }

    /**
     * Update pautaPrecio.
     */
    public void updateEntity(PautaPrecio pautaPrecio) {
        pautaPrecioDAO.update(pautaPrecio);
    }

    /**
     * Delete pautaPrecio.
     */
    public void deleteEntity(PautaPrecio pautaPrecio) {
        pautaPrecioDAO.delete(pautaPrecio);
    }
/* generated by stajanov (do not edit/delete) */
}