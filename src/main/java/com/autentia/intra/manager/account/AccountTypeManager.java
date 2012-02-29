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

package com.autentia.intra.manager.account;

import com.autentia.intra.businessobject.AccountType;
import com.autentia.intra.dao.SortCriteria;
import com.autentia.intra.dao.hibernate.AccountTypeDAO;
import com.autentia.intra.dao.search.AccountTypeSearch;
import com.autentia.intra.util.SpringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;


public class AccountTypeManager {
/* generated by stajanov (do not edit/delete) */


    /**
     * Logger
     */
    private static final Log log = LogFactory.getLog(AccountTypeManager.class);

    /**
     * AccountType DAO *
     */
    private AccountTypeDAO accountTypeDAO;

    /**
     * Get default AccountTypeManager as defined in Spring's configuration file.
     *
     * @return the default singleton AccountTypeManager
     */
    public static AccountTypeManager getDefault() {
        return (AccountTypeManager) SpringUtils.getSpringBean("managerAccountType");
    }

    /**
     * Empty constructor needed by CGLIB (Spring AOP)
     */
    protected AccountTypeManager() {
    }

    /**
     * Default constructor
     *
     * @deprecated do not construct managers alone: use Spring's declared beans
     */
    public AccountTypeManager(AccountTypeDAO accountTypeDAO) {
        this.accountTypeDAO = accountTypeDAO;
    }

    /**
     * List accountTypes.
     *
     * @param search search filter to apply
     * @param sort   sorting criteria
     * @return the list of all accountTypes sorted by requested criterion
     */
    public List<AccountType> getAllEntities(AccountTypeSearch search, SortCriteria sort) {
        return accountTypeDAO.search(search, sort);
    }

    /**
     * Get accountType by primary key.
     *
     * @return accountType selected by id.
     */
    public AccountType getEntityById(int id) {
        return accountTypeDAO.getById(id);
    }

    /**
     * Insert accountType.
     */
    public void insertEntity(AccountType accountType) {
        accountTypeDAO.insert(accountType);
    }

    /**
     * Update accountType.
     */
    public void updateEntity(AccountType accountType) {
        accountTypeDAO.update(accountType);
    }

    /**
     * Delete accountType.
     */
    public void deleteEntity(AccountType accountType) {
        accountTypeDAO.delete(accountType);
    }
/* generated by stajanov (do not edit/delete) */
}
