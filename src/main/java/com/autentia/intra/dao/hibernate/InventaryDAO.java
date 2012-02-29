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

package com.autentia.intra.dao.hibernate;

import com.autentia.intra.businessobject.Inventary;
import com.autentia.intra.dao.DataAccException;
import com.autentia.intra.dao.IDataAccessObject;
import com.autentia.intra.dao.SearchCriteria;
import com.autentia.intra.dao.SortCriteria;
import com.autentia.intra.util.SpringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * DAO for Inventary objects.
 *
 * @author stajanov code generator
 */
public class InventaryDAO extends HibernateManagerBase implements IDataAccessObject<Inventary> {
/* inventary - generated by stajanov (do not edit/delete) */


    /**
     * Logger
     */
    private static final Log log = LogFactory.getLog(InventaryDAO.class);

    /**
     * Get default InventaryDAO as defined in Spring's configuration file.
     *
     * @return the default singleton InventaryDAO
     */
    public static InventaryDAO getDefault() {
        return (InventaryDAO) SpringUtils.getSpringBean("daoInventary");
    }

    /**
     * Constructor
     *
     * @deprecated do not construct DAOs alone: use Spring's declared beans
     */
    public InventaryDAO() {
        super(false);
    }

    /**
     * Retrieve a Inventary object from database given its id
     *
     * @param id primary key of Inventary object
     * @return the Inventary object identified by the id
     * @throws DataAccException on error
     */
    public Inventary getById(int id) throws DataAccException {
        return super.getByPk(Inventary.class, id);
    }

    /**
     * Get all Inventary objects from database sorted by the given criteria
     *
     * @param crit the sorting criteria
     * @return a list with all existing Inventary objects
     * @throws DataAccException on error
     */
    public List<Inventary> search(SortCriteria crit) throws DataAccException {
        return super.list(Inventary.class, crit);
    }

    /**
     * Get specified Inventary objects from database sorted by the given criteria
     *
     * @param search search criteria
     * @param sort   the sorting criteria
     * @return a list with Inventary objects matching the search criteria
     * @throws DataAccException on error
     */
    public List<Inventary> search(SearchCriteria search, SortCriteria sort) throws DataAccException {
        return super.search(Inventary.class, search, sort);
    }

    /**
     * Insert a new Inventary object in database
     *
     * @param dao the Inventary object to insert
     * @throws DataAccException on error
     */
    public void insert(Inventary dao) throws DataAccException {
        super.insert(dao);
    }

    /**
     * Update an existing Inventary object in database
     *
     * @param dao the Inventary object to update
     * @throws DataAccException on error
     */
    public void update(Inventary dao) throws DataAccException {
        super.update(dao, dao.getId());
    }

    /**
     * Delete an existing Inventary object in database
     *
     * @param dao the Inventary object to update
     * @throws DataAccException on error
     */
    public void delete(Inventary dao) throws DataAccException {
        super.delete(dao, dao.getId());
    }

/* inventary - generated by stajanov (do not edit/delete) */
}
