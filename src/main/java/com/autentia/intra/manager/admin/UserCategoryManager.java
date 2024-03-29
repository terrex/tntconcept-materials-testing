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

import com.autentia.intra.businessobject.UserCategory;
import com.autentia.intra.dao.SortCriteria;
import com.autentia.intra.dao.hibernate.UserCategoryDAO;
import com.autentia.intra.dao.search.UserCategorySearch;
import com.autentia.intra.util.SpringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;


public class UserCategoryManager {

/* userCategory - generated by stajanov (do not edit/delete) */


    /**
     * Logger
     */
    private static final Log log = LogFactory.getLog(UserCategoryManager.class);

    /**
     * UserCategory DAO *
     */
    private UserCategoryDAO userCategoryDAO;

    /**
     * Get default UserCategoryManager as defined in Spring's configuration file.
     *
     * @return the default singleton UserCategoryManager
     */
    public static UserCategoryManager getDefault() {
        return (UserCategoryManager) SpringUtils.getSpringBean("managerUserCategory");
    }

    /**
     * Empty constructor needed by CGLIB (Spring AOP)
     */
    protected UserCategoryManager() {
    }

    /**
     * Default constructor
     *
     * @deprecated do not construct managers alone: use Spring's declared beans
     */
    public UserCategoryManager(UserCategoryDAO userCategoryDAO) {
        this.userCategoryDAO = userCategoryDAO;
    }

    /**
     * List userCategorys.
     *
     * @param search search filter to apply
     * @param sort   sorting criteria
     * @return the list of all userCategorys sorted by requested criterion
     */
    public List<UserCategory> getAllEntities(UserCategorySearch search, SortCriteria sort) {
        return userCategoryDAO.search(search, sort);
    }

    /**
     * Get userCategory by primary key.
     *
     * @return userCategory selected by id.
     */
    public UserCategory getEntityById(int id) {
        return userCategoryDAO.getById(id);
    }

    /**
     * Insert userCategory.
     */
    public void insertEntity(UserCategory userCategory) {
        userCategoryDAO.insert(userCategory);
    }

    /**
     * Update userCategory.
     */
    public void updateEntity(UserCategory userCategory) {
        userCategoryDAO.update(userCategory);
    }

    /**
     * Delete userCategory.
     */
    public void deleteEntity(UserCategory userCategory) {
        userCategoryDAO.delete(userCategory);
    }

/* userCategory - generated by stajanov (do not edit/delete) */


}
