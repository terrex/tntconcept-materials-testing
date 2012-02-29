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

import com.autentia.intra.businessobject.Ensayo;
import com.autentia.intra.dao.SortCriteria;
import com.autentia.intra.dao.hibernate.EnsayoDAO;
import com.autentia.intra.dao.search.EnsayoSearch;
import com.autentia.intra.util.SpringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;


public class EnsayoManager {


    /**
     * Logger
     */
    private static final Log log = LogFactory.getLog(EnsayoManager.class);

    /**
     * Ensayo DAO *
     */
    private EnsayoDAO ensayoDAO;

    /**
     * Get default EnsayoManager as defined in Spring's configuration file.
     *
     * @return the default singleton EnsayoManager
     */
    public static EnsayoManager getDefault() {
        return (EnsayoManager) SpringUtils.getSpringBean("managerEnsayo");
    }

    /**
     * Empty constructor needed by CGLIB (Spring AOP)
     */
    protected EnsayoManager() {
    }

    /**
     * Default constructor
     *
     * @deprecated do not construct managers alone: use Spring's declared beans
     */
    public EnsayoManager(EnsayoDAO ensayoDAO) {
        this.ensayoDAO = ensayoDAO;
    }

    /**
     * List ensayos.
     *
     * @param search search filter to apply
     * @param sort   sorting criteria
     * @return the list of all ensayos sorted by requested criterion
     */
    public List<Ensayo> getAllEntities(EnsayoSearch search, SortCriteria sort) {
        return ensayoDAO.search(search, sort);
    }

    /**
     * Get ensayo by primary key.
     *
     * @return ensayo selected by id.
     */
    public Ensayo getEntityById(int id) {
        return ensayoDAO.getById(id);
    }

    /**
     * Insert ensayo.
     */
    public void insertEntity(Ensayo ensayo) {
        ensayoDAO.insert(ensayo);
    }

    /**
     * Update ensayo.
     */
    public void updateEntity(Ensayo ensayo) {
        ensayoDAO.update(ensayo);
    }

    /**
     * Delete ensayo.
     */
    public void deleteEntity(Ensayo ensayo) {
        ensayoDAO.delete(ensayo);
    }

/* Ensayo - generated by stajanov (do not edit/delete) */


}