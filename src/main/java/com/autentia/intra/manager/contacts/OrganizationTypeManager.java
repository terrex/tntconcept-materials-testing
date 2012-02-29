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

import com.autentia.intra.businessobject.OrganizationType;
import com.autentia.intra.dao.SortCriteria;
import com.autentia.intra.dao.hibernate.OrganizationTypeDAO;
import com.autentia.intra.dao.search.OrganizationTypeSearch;
import com.autentia.intra.util.SpringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;


public class OrganizationTypeManager {

/* OrganizationType - generated by stajanov (do not edit/delete) */


    /**
     * Logger
     */
    private static final Log log = LogFactory.getLog(OrganizationTypeManager.class);

    /**
     * OrganizationType DAO *
     */
    private OrganizationTypeDAO organizationTypeDAO;

    /**
     * Get default OrganizationTypeManager as defined in Spring's configuration file.
     *
     * @return the default singleton OrganizationTypeManager
     */
    public static OrganizationTypeManager getDefault() {
        return (OrganizationTypeManager) SpringUtils.getSpringBean("managerOrganizationType");
    }

    /**
     * Empty constructor needed by CGLIB (Spring AOP)
     */
    protected OrganizationTypeManager() {
    }

    /**
     * Default constructor
     *
     * @deprecated do not construct managers alone: use Spring's declared beans
     */
    public OrganizationTypeManager(OrganizationTypeDAO organizationTypeDAO) {
        this.organizationTypeDAO = organizationTypeDAO;
    }

    /**
     * List organizationTypes.
     *
     * @param search search filter to apply
     * @param sort   sorting criteria
     * @return the list of all organizationTypes sorted by requested criterion
     */
    public List<OrganizationType> getAllEntities(OrganizationTypeSearch search, SortCriteria sort) {
        return organizationTypeDAO.search(search, sort);
    }

    /**
     * Get organizationType by primary key.
     *
     * @return organizationType selected by id.
     */
    public OrganizationType getEntityById(int id) {
        return organizationTypeDAO.getById(id);
    }

    /**
     * Insert organizationType.
     */
    public void insertEntity(OrganizationType organizationType) {
        organizationTypeDAO.insert(organizationType);
    }

    /**
     * Update organizationType.
     */
    public void updateEntity(OrganizationType organizationType) {
        organizationTypeDAO.update(organizationType);
    }

    /**
     * Delete organizationType.
     */
    public void deleteEntity(OrganizationType organizationType) {
        organizationTypeDAO.delete(organizationType);
    }

/* OrganizationType - generated by stajanov (do not edit/delete) */


}