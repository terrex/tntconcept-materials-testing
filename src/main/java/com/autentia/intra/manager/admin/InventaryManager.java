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

package com.autentia.intra.manager.admin;

import com.autentia.intra.businessobject.Inventary;
import com.autentia.intra.dao.SortCriteria;
import com.autentia.intra.dao.hibernate.InventaryDAO;
import com.autentia.intra.dao.search.InventarySearch;
import com.autentia.intra.util.SpringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;


public class InventaryManager {

/* Inventary - generated by stajanov (do not edit/delete) */


    /**
     * Logger
     */
    private static final Log log = LogFactory.getLog(InventaryManager.class);

    /**
     * Inventary DAO *
     */
    private InventaryDAO inventaryDAO;

    /**
     * Get default InventaryManager as defined in Spring's configuration file.
     *
     * @return the default singleton InventaryManager
     */
    public static InventaryManager getDefault() {
        return (InventaryManager) SpringUtils.getSpringBean("managerInventary");
    }

    /**
     * Empty constructor needed by CGLIB (Spring AOP)
     */
    protected InventaryManager() {
    }

    /**
     * Default constructor
     *
     * @deprecated do not construct managers alone: use Spring's declared beans
     */
    public InventaryManager(InventaryDAO inventaryDAO) {
        this.inventaryDAO = inventaryDAO;
    }

    /**
     * List inventarys.
     *
     * @param search search filter to apply
     * @param sort   sorting criteria
     * @return the list of all inventarys sorted by requested criterion
     */
    public List<Inventary> getAllEntities(InventarySearch search, SortCriteria sort) {
        return inventaryDAO.search(search, sort);
    }

    /**
     * Get inventary by primary key.
     *
     * @return inventary selected by id.
     */
    public Inventary getEntityById(int id) {
        return inventaryDAO.getById(id);
    }

    /**
     * Insert inventary.
     */
    public void insertEntity(Inventary inventary) {
        inventaryDAO.insert(inventary);
    }

    /**
     * Update inventary.
     */
    public void updateEntity(Inventary inventary) {
        inventaryDAO.update(inventary);
    }

    /**
     * Delete inventary.
     */
    public void deleteEntity(Inventary inventary) {
        inventaryDAO.delete(inventary);
    }

/* Inventary - generated by stajanov (do not edit/delete) */


}
