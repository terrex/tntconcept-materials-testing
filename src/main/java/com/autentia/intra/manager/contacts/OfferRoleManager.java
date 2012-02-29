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

import com.autentia.intra.businessobject.OfferRole;
import com.autentia.intra.dao.SortCriteria;
import com.autentia.intra.dao.hibernate.OfferRoleDAO;
import com.autentia.intra.dao.search.OfferRoleSearch;
import com.autentia.intra.util.SpringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;


public class OfferRoleManager {

/* generated by stajanov (do not edit/delete) */


    /**
     * Logger
     */
    private static final Log log = LogFactory.getLog(OfferRoleManager.class);

    /**
     * OfferRole DAO *
     */
    private OfferRoleDAO offerRoleDAO;

    /**
     * Get default OfferRoleManager as defined in Spring's configuration file.
     *
     * @return the default singleton OfferRoleManager
     */
    public static OfferRoleManager getDefault() {
        return (OfferRoleManager) SpringUtils.getSpringBean("managerOfferRole");
    }

    /**
     * Empty constructor needed by CGLIB (Spring AOP)
     */
    protected OfferRoleManager() {
    }

    /**
     * Default constructor
     *
     * @deprecated do not construct managers alone: use Spring's declared beans
     */
    public OfferRoleManager(OfferRoleDAO offerRoleDAO) {
        this.offerRoleDAO = offerRoleDAO;
    }

    /**
     * List offerRoles.
     *
     * @param search search filter to apply
     * @param sort   sorting criteria
     * @return the list of all offerRoles sorted by requested criterion
     */
    public List<OfferRole> getAllEntities(OfferRoleSearch search, SortCriteria sort) {
        return offerRoleDAO.search(search, sort);
    }

    /**
     * Get offerRole by primary key.
     *
     * @return offerRole selected by id.
     */
    public OfferRole getEntityById(int id) {
        return offerRoleDAO.getById(id);
    }

    /**
     * Insert offerRole.
     */
    public void insertEntity(OfferRole offerRole) {
        offerRoleDAO.insert(offerRole);
    }

    /**
     * Update offerRole.
     */
    public void updateEntity(OfferRole offerRole) {
        offerRoleDAO.update(offerRole);
    }

    /**
     * Delete offerRole.
     */
    public void deleteEntity(OfferRole offerRole) {
        offerRoleDAO.delete(offerRole);
    }
/* generated by stajanov (do not edit/delete) */
}
