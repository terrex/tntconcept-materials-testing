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
package com.autentia.intra.bean.account;

import com.autentia.intra.bean.BaseBean;
import com.autentia.intra.businessobject.*;
import com.autentia.intra.dao.SortCriteria;
import com.autentia.intra.dao.search.BillSearch;
import com.autentia.intra.dao.search.PeriodicalAccountEntrySearch;
import com.autentia.intra.manager.account.PeriodicalAccountEntryManager;
import com.autentia.intra.manager.billing.BillManager;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * UI bean for NOF objects.
 *
 * @author Autentia
 */
public class NOFBean extends BaseBean {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -3165007831286521687L;

    /**
     * Active Bill object
     */
    private Bill bill;

    /**
     * Active PeriodicalAccountEntry object
     */
    private PeriodicalAccountEntry periodicalAccountEntry;

    /**
     * Default sort column in prevision incomes list
     */
    private String sortColumnPrevisionIncomes = "creationDate";

    /**
     * Default sort column in periodical account entry list
     */
    private String sortColumnPeriodicalAccount = "date";

    /**
     * Default sort order
     */
    private boolean sortAscendingPrevisionIncomes = true;

    /**
     * Default sort order
     */
    private boolean sortAscendingPeriodicalAccount = true;

    /**
     * Active search object
     */
    private BillSearch billSearch = new BillSearch();

    /**
     * Active search object
     */
    private PeriodicalAccountEntrySearch periodicalAccountSearch = new PeriodicalAccountEntrySearch();

    /**
     * Bill Manager
     */
    private static BillManager billManager = BillManager.getDefault();

    /**
     * Periodical Account Manager
     */
    private static PeriodicalAccountEntryManager periodicalAccountManager = PeriodicalAccountEntryManager.getDefault();

    /**
     * Total bills totalPrevisionIncomes
     */
    private BigDecimal totalPrevisionIncomes;

    /**
     * Total Account Periodical
     */
    private BigDecimal totalPeriodicalAccount;

    /**
     * End value to calculate the NOF
     */
    private Calendar endDate;

    /**
     * List bills. Order depends on Faces parameter sort.
     *
     * @return the list of all bills sorted by requested criterion
     */
    public List<Bill> getAllPrevisionIncomes() {

        /* Only search issued and emitted bills */
        billSearch.setBillType(BillType.ISSUED);
        billSearch.setState(BillState.EMITTED);

        List<Bill> res = billManager.getEntities(billSearch, new SortCriteria(sortColumnPrevisionIncomes, sortAscendingPrevisionIncomes),
                getActualDate(), getEndDate());

        if (res != null) {
            calcTotalsBill(res);
        }

        return res;
    }

    /**
     * List periodicalAccountEntrys. Order depends on Faces parameter sort.
     *
     * @return the list of all periodicalAccountEntrys sorted by requested criterion
     */
    public List<PeriodicalAccountEntry> getAllPeriodicalAccountEntry() {

        List<PeriodicalAccountEntry> res = periodicalAccountManager.getEntities(periodicalAccountSearch,
                new SortCriteria(sortColumnPeriodicalAccount, sortAscendingPeriodicalAccount), getActualDate(), getEndDate());

        /* Si el criterio de ordenación es por fecha se debe ordenar otra vez la lista ya que las fechas
           *  no coinciden con las de la base de datos */
        if (sortColumnPeriodicalAccount.equals("date")) {
            orderListByDate(res);
        }

        if (res != null) {
            calcTotalsPeriodicalAccount(res);
        }

        return res;
    }

    /**
     * Order list by date.
     *
     * @param res the list of PeriodicaAccountEntry
     */
    private void orderListByDate(List<PeriodicalAccountEntry> res) {

        PeriodicalAccountEntry temp = null;
        boolean sorted = false;

        for (int i = 0; i < res.size() && !sorted; i++) {
            sorted = true;

            for (int j = res.size() - 1; j > i; j--) {

                PeriodicalAccountEntry first = null;
                PeriodicalAccountEntry second = null;

                // Ascending order
                if (sortAscendingPeriodicalAccount) {
                    first = res.get(j);
                    second = res.get(j - 1);
                } else {
                    first = res.get(j - 1);
                    second = res.get(j);
                }

                if (first.getDate().getTime() < second.getDate().getTime()) {
                    temp = res.get(j);
                    res.set(j, res.get(j - 1));
                    res.set(j - 1, temp);
                    sorted = false;
                }
            }
        }
    }

    /**
     * Calculate the total bill costs
     *
     * @param res
     */
    private void calcTotalsBill(List<Bill> res) {

        BigDecimal value = new BigDecimal(0);
        for (Bill elem : res) {
            value = value.add(elem.getTotal());
        }

        setTotalPrevisionIncomes(value);
    }

    /**
     * Calculate the total periodical account costs
     *
     * @param res
     */
    private void calcTotalsPeriodicalAccount(List<PeriodicalAccountEntry> res) {

        BigDecimal value = new BigDecimal(0);
        for (PeriodicalAccountEntry elem : res) {
            value = value.add(elem.getAmount());
        }

        setTotalPeriodicalAccount(value);
    }

    /**
     * @return the totalPrevisionIncomes
     */
    public BigDecimal getTotalPrevisionIncomes() {
        return totalPrevisionIncomes;
    }

    /**
     * @param totalPrevisionIncomes the totalPrevisionIncomes to set
     */
    public void setTotalPrevisionIncomes(BigDecimal totalPrevisionIncomes) {
        this.totalPrevisionIncomes = totalPrevisionIncomes;
    }

    /**
     * @return the totalPeriodicalAccount
     */
    public BigDecimal getTotalPeriodicalAccount() {
        return totalPeriodicalAccount;
    }

    /**
     * @param totalPeriodicalAccount the totalPeriodicalAccount to set
     */
    public void setTotalPeriodicalAccount(BigDecimal totalPeriodicalAccount) {
        this.totalPeriodicalAccount = totalPeriodicalAccount;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        // The first value is calculated adding 1 month to actual date
        if (endDate == null) {
            endDate = Calendar.getInstance();
            endDate.add(Calendar.MONTH, 1);
        }
        return endDate.getTime();
    }

    /**
     * @return the actualDate
     */
    public Date getActualDate() {
        return Calendar.getInstance().getTime();
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate.setTime(endDate);
    }

    /**
     * @return the sortAscendingPrevisionIncomes
     */
    public boolean isSortAscendingPrevisionIncomes() {
        return sortAscendingPrevisionIncomes;
    }

    /**
     * @param sortAscendingPrevisionIncomes the sortAscending to set
     */
    public void setSortAscendingPrevisionIncomes(boolean sortAscendingPrevisionIncomes) {
        this.sortAscendingPrevisionIncomes = sortAscendingPrevisionIncomes;
    }

    /**
     * @return the sortColumnPrevisionIncomes
     */
    public String getSortColumnPrevisionIncomes() {
        return sortColumnPrevisionIncomes;
    }

    /**
     * @param sortColumnPrevisionIncomes the sortColumnPrevisionIncomes to set
     */
    public void setSortColumnPrevisionIncomes(String sortColumnPrevisionIncomes) {
        this.sortColumnPrevisionIncomes = sortColumnPrevisionIncomes;
    }

    /**
     * @return the sortAscendingPeriodicalAccount
     */
    public boolean isSortAscendingPeriodicalAccount() {
        return sortAscendingPeriodicalAccount;
    }

    /**
     * @param sortAscendingPeriodicalAccount the sortAscendingPeriodicalAccount to set
     */
    public void setSortAscendingPeriodicalAccount(
            boolean sortAscendingPeriodicalAccount) {
        this.sortAscendingPeriodicalAccount = sortAscendingPeriodicalAccount;
    }

    /**
     * @return the sortColumnPeriodicalAccount
     */
    public String getSortColumnPeriodicalAccount() {
        return sortColumnPeriodicalAccount;
    }

    /**
     * @param sortColumnPeriodicalAccount the sortColumnPeriodicalAccount to set
     */
    public void setSortColumnPeriodicalAccount(String sortColumnPeriodicalAccount) {
        this.sortColumnPeriodicalAccount = sortColumnPeriodicalAccount;
    }

    public Date getCreationDate() {
        return bill.getCreationDate();
    }

    public int getExpiration() {
        return bill.getExpiration();
    }

    public String getNumber() {
        return bill.getNumber();
    }

    public String getName() {
        return bill.getName();
    }

    public String getObservations() {
        return bill.getObservations();
    }

    public String getConcept() {
        return periodicalAccountEntry.getConcept();
    }

    public Date getDate() {
        return periodicalAccountEntry.getDate();
    }

    public BigDecimal getAmount() {
        return periodicalAccountEntry.getAmount();
    }

    public AccountEntryType getType() {
        return periodicalAccountEntry.getType();
    }

    public Frequency getFrequency() {
        return periodicalAccountEntry.getFrequency();
    }

}
