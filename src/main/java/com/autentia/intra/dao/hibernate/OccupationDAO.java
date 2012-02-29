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

import com.autentia.intra.businessobject.Occupation;
import com.autentia.intra.dao.DataAccException;
import com.autentia.intra.dao.IDataAccessObject;
import com.autentia.intra.dao.SearchCriteria;
import com.autentia.intra.dao.SortCriteria;
import com.autentia.intra.util.SpringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;


public class OccupationDAO extends HibernateManagerBase implements IDataAccessObject<Occupation> {

/* occupation - generated by stajanov (do not edit/delete) */


    /**
     * Logger
     */
    private static final Log log = LogFactory.getLog(OccupationDAO.class);

    /**
     * Get default OccupationDAO as defined in Spring's configuration file.
     *
     * @return the default singleton OccupationDAO
     */
    public static OccupationDAO getDefault() {
        return (OccupationDAO) SpringUtils.getSpringBean("daoOccupation");
    }

    /**
     * Constructor
     *
     * @deprecated do not construct DAOs alone: use Spring's declared beans
     */
    public OccupationDAO() {
        super(false);
    }

    /**
     * Retrieve a Occupation object from database given its id
     *
     * @param id primary key of Occupation object
     * @return the Occupation object identified by the id
     * @throws DataAccException on error
     */
    public Occupation getById(int id) throws DataAccException {
        return super.getByPk(Occupation.class, id);
    }

    /**
     * Get all Occupation objects from database sorted by the given criteria
     *
     * @param crit the sorting criteria
     * @return a list with all existing Occupation objects
     * @throws DataAccException on error
     */
    public List<Occupation> search(SortCriteria crit) throws DataAccException {
        return super.list(Occupation.class, crit);
    }

    /**
     * Get specified Occupation objects from database sorted by the given criteria
     *
     * @param search search criteria
     * @param sort   the sorting criteria
     * @return a list with Occupation objects matching the search criteria
     * @throws DataAccException on error
     */
    public List<Occupation> search(SearchCriteria search, SortCriteria sort) throws DataAccException {
        return super.search(Occupation.class, search, sort);
    }

    /**
     * Insert a new Occupation object in database
     *
     * @param dao the Occupation object to insert
     * @throws DataAccException on error
     */
    public void insert(Occupation dao) throws DataAccException {
        super.insert(dao);
    }

    /**
     * Update an existing Occupation object in database
     *
     * @param dao the Occupation object to update
     * @throws DataAccException on error
     */
    public void update(Occupation dao) throws DataAccException {
        super.update(dao, dao.getId());
    }

    /**
     * Delete an existing Occupation object in database
     *
     * @param dao the Occupation object to update
     * @throws DataAccException on error
     */
    public void delete(Occupation dao) throws DataAccException {
        super.delete(dao, dao.getId());
    }

/* occupation - generated by stajanov (do not edit/delete) */
}
