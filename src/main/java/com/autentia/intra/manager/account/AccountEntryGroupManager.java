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

import com.autentia.intra.businessobject.AccountEntryGroup;
import com.autentia.intra.dao.SortCriteria;
import com.autentia.intra.dao.hibernate.AccountEntryGroupDAO;
import com.autentia.intra.dao.search.AccountEntryGroupSearch;
import com.autentia.intra.util.SpringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;


public class AccountEntryGroupManager {

/* generated by stajanov (do not edit/delete) */


    /**
     * Logger
     */
    private static final Log log = LogFactory.getLog(AccountEntryGroupManager.class);

    /**
     * AccountEntryGroup DAO *
     */
    private AccountEntryGroupDAO accountEntryGroupDAO;

    /**
     * Get default AccountEntryGroupManager as defined in Spring's configuration file.
     *
     * @return the default singleton AccountEntryGroupManager
     */
    public static AccountEntryGroupManager getDefault() {
        return (AccountEntryGroupManager) SpringUtils.getSpringBean("managerAccountEntryGroup");
    }

    /**
     * Empty constructor needed by CGLIB (Spring AOP)
     */
    protected AccountEntryGroupManager() {
    }

    /**
     * Default constructor
     *
     * @deprecated do not construct managers alone: use Spring's declared beans
     */
    public AccountEntryGroupManager(AccountEntryGroupDAO accountEntryGroupDAO) {
        this.accountEntryGroupDAO = accountEntryGroupDAO;
    }

    /**
     * List accountEntryGroups.
     *
     * @param search search filter to apply
     * @param sort   sorting criteria
     * @return the list of all accountEntryGroups sorted by requested criterion
     */
    public List<AccountEntryGroup> getAllEntities(AccountEntryGroupSearch search, SortCriteria sort) {
        return accountEntryGroupDAO.search(search, sort);
    }

    /**
     * Get accountEntryGroup by primary key.
     *
     * @return accountEntryGroup selected by id.
     */
    public AccountEntryGroup getEntityById(int id) {
        return accountEntryGroupDAO.getById(id);
    }

    /**
     * Insert accountEntryGroup.
     */
    public void insertEntity(AccountEntryGroup accountEntryGroup) {
        accountEntryGroupDAO.insert(accountEntryGroup);
    }

    /**
     * Update accountEntryGroup.
     */
    public void updateEntity(AccountEntryGroup accountEntryGroup) {
        accountEntryGroupDAO.update(accountEntryGroup);
    }

    /**
     * Delete accountEntryGroup.
     */
    public void deleteEntity(AccountEntryGroup accountEntryGroup) {
        accountEntryGroupDAO.delete(accountEntryGroup);
    }
/* generated by stajanov (do not edit/delete) */
}
