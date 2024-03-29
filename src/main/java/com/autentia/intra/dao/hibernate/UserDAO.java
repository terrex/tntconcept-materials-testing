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

import com.autentia.intra.businessobject.User;
import com.autentia.intra.dao.*;
import com.autentia.intra.dao.search.UserSearch;
import com.autentia.intra.util.SpringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * DAO for User objects.
 *
 * @author stajanov code generator
 */
public class UserDAO extends HibernateManagerBase implements IDataAccessObject<User> {
/* user - generated by stajanov (do not edit/delete) */


    /**
     * Logger
     */
    private static final Log log = LogFactory.getLog(UserDAO.class);

    /**
     * Get default UserDAO as defined in Spring's configuration file.
     *
     * @return the default singleton UserDAO
     */
    public static UserDAO getDefault() {
        return (UserDAO) SpringUtils.getSpringBean("daoUser");
    }

    /**
     * Constructor
     *
     * @deprecated do not construct DAOs alone: use Spring's declared beans
     */
    public UserDAO() {
        super(false);
    }

    /**
     * Retrieve a User object from database given its id
     *
     * @param id primary key of User object
     * @return the User object identified by the id
     * @throws DataAccException on error
     */
    public User getById(int id) throws DataAccException {
        return super.getByPk(User.class, id);
    }

    /**
     * Get all User objects from database sorted by the given criteria
     *
     * @param crit the sorting criteria
     * @return a list with all existing User objects
     * @throws DataAccException on error
     */
    public List<User> search(SortCriteria crit) throws DataAccException {
        return super.list(User.class, crit);
    }

    /**
     * Get specified User objects from database sorted by the given criteria
     *
     * @param search search criteria
     * @param sort   the sorting criteria
     * @return a list with User objects matching the search criteria
     * @throws DataAccException on error
     */
    public List<User> search(SearchCriteria search, SortCriteria sort) throws DataAccException {
        return super.search(User.class, search, sort);
    }

    /**
     * Insert a new User object in database
     *
     * @param dao the User object to insert
     * @throws DataAccException on error
     */
    public void insert(User dao) throws DataAccException {
        super.insert(dao);
    }

    /**
     * Update an existing User object in database
     *
     * @param dao the User object to update
     * @throws DataAccException on error
     */
    public void update(User dao) throws DataAccException {
        super.update(dao, dao.getId());
    }

    /**
     * Delete an existing User object in database
     *
     * @param dao the User object to update
     * @throws DataAccException on error
     */
    public void delete(User dao) throws DataAccException {
        super.delete(dao, dao.getId());
    }

/* user - generated by stajanov (do not edit/delete) */

    /**
     * Get User objects from database based on active flag sorted by the given criteria
     *
     * @param sort the sorting criteria
     * @return a list with User objects matching the search criteria
     * @throws DataAccException on error
     */
    public List<User> searchByActive(boolean active, SortCriteria sort) throws DataAccException {
        UserSearch search = new UserSearch();
        search.setActive(active);
        return super.search(User.class, search, sort);
    }

    /**
     * Get a user given her login.
     *
     * @param login login of user
     * @return the user
     * @throws com.autentia.intra.dao.DataNotFoundException
     *          if user does not exist
     * @throws com.autentia.intra.dao.DataIntegrityException
     *          if user is duplicated (bad thing)
     * @throws com.autentia.intra.dao.DataAccException
     *          if something fails accesind DB
     */
    public User searchByLogin(String login) throws DataAccException, DataNotFoundException, DataIntegrityException {
        UserSearch search = new UserSearch();
        search.setLogin(login);
        List<User> found = super.search(User.class, search, null);
        if (found.size() == 0) {
            throw new DataNotFoundException(User.class, search);
        } else if (found.size() > 1) {
            throw new DataIntegrityException(User.class, "duplicate login '" + login + "'");
        } else {
            return found.get(0);
        }
    }
}
