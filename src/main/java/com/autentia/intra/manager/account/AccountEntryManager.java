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

import com.autentia.intra.businessobject.AccountEntry;
import com.autentia.intra.businessobject.Bill;
import com.autentia.intra.dao.SortCriteria;
import com.autentia.intra.dao.hibernate.AccountEntryDAO;
import com.autentia.intra.dao.search.AccountEntrySearch;
import com.autentia.intra.util.SpringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;


public class AccountEntryManager {

/* AccountEntry - generated by stajanov (do not edit/delete) */


    /**
     * Logger
     */
    private static final Log log = LogFactory.getLog(AccountEntryManager.class);

    /**
     * AccountEntry DAO *
     */
    private AccountEntryDAO accountEntryDAO;

    /**
     * Get default AccountEntryManager as defined in Spring's configuration file.
     *
     * @return the default singleton AccountEntryManager
     */
    public static AccountEntryManager getDefault() {
        return (AccountEntryManager) SpringUtils.getSpringBean("managerAccountEntry");
    }

    /**
     * Empty constructor needed by CGLIB (Spring AOP)
     */
    protected AccountEntryManager() {
    }

    /**
     * Default constructor
     *
     * @deprecated do not construct managers alone: use Spring's declared beans
     */
    public AccountEntryManager(AccountEntryDAO accountEntryDAO) {
        this.accountEntryDAO = accountEntryDAO;
    }

    /**
     * List accountEntrys.
     *
     * @param search search filter to apply
     * @param sort   sorting criteria
     * @return the list of all accountEntrys sorted by requested criterion
     */
    public List<AccountEntry> getAllEntities(AccountEntrySearch search, SortCriteria sort) {
        return accountEntryDAO.search(search, sort);
    }

    /**
     * Get accountEntry by primary key.
     *
     * @return accountEntry selected by id.
     */
    public AccountEntry getEntityById(int id) {
        return accountEntryDAO.getById(id);
    }

    /**
     * Insert accountEntry.
     */
    public void insertEntity(AccountEntry accountEntry) {
        accountEntryDAO.insert(accountEntry);
    }

    /**
     * Update accountEntry.
     */
    public void updateEntity(AccountEntry accountEntry) {
        accountEntryDAO.update(accountEntry);
    }

    /**
     * Delete accountEntry.
     */
    public void deleteEntity(AccountEntry accountEntry) {
        accountEntryDAO.delete(accountEntry);
    }

/* AccountEntry - generated by stajanov (do not edit/delete) */

    public List<AccountEntry> getAllMinusBill(int year, Bill bill, SortCriteria criteria) {
        AccountEntrySearch searchA = new AccountEntrySearch();
        searchA.setYear(year);
        searchA.setBill(bill);
        return accountEntryDAO.search(searchA, criteria);
    }


}
