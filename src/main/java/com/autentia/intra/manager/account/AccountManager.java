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

import com.autentia.intra.businessobject.Account;
import com.autentia.intra.dao.SortCriteria;
import com.autentia.intra.dao.hibernate.AccountDAO;
import com.autentia.intra.dao.search.AccountSearch;
import com.autentia.intra.util.SpringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;


public class AccountManager {

/* Account - generated by stajanov (do not edit/delete) */


    /**
     * Logger
     */
    private static final Log log = LogFactory.getLog(AccountManager.class);

    /**
     * Account DAO *
     */
    private AccountDAO accountDAO;

    /**
     * Get default AccountManager as defined in Spring's configuration file.
     *
     * @return the default singleton AccountManager
     */
    public static AccountManager getDefault() {
        return (AccountManager) SpringUtils.getSpringBean("managerAccount");
    }

    /**
     * Empty constructor needed by CGLIB (Spring AOP)
     */
    protected AccountManager() {
    }

    /**
     * Default constructor
     *
     * @deprecated do not construct managers alone: use Spring's declared beans
     */
    public AccountManager(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    /**
     * List accounts.
     *
     * @param search search filter to apply
     * @param sort   sorting criteria
     * @return the list of all accounts sorted by requested criterion
     */
    public List<Account> getAllEntities(AccountSearch search, SortCriteria sort) {
        return accountDAO.search(search, sort);
    }

    /**
     * Get account by primary key.
     *
     * @return account selected by id.
     */
    public Account getEntityById(int id) {
        return accountDAO.getById(id);
    }

    /**
     * Insert account.
     */
    public void insertEntity(Account account) {
        accountDAO.insert(account);
    }

    /**
     * Update account.
     */
    public void updateEntity(Account account) {
        accountDAO.update(account);
    }

    /**
     * Delete account.
     */
    public void deleteEntity(Account account) {
        accountDAO.delete(account);
    }

/* Account - generated by stajanov (do not edit/delete) */


}
