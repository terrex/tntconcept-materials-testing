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

package com.autentia.intra.manager.billing;

import com.autentia.intra.businessobject.*;
import com.autentia.intra.dao.SortCriteria;
import com.autentia.intra.dao.hibernate.ActivityDAO;
import com.autentia.intra.dao.hibernate.BillDAO;
import com.autentia.intra.dao.search.ActivitySearch;
import com.autentia.intra.dao.search.BillSearch;
import com.autentia.intra.util.ConfigurationUtil;
import com.autentia.intra.util.SpringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class BillManager {

    /**
     * Account DAO *
     */

    public List<BillBreakDown> getAllBitacoreBreakDowns(Date start, Date end, Set<ProjectRole> roles, Set<ProjectCost> costes) {

        List<BillBreakDown> desgloses = new ArrayList<BillBreakDown>();

        ActivityDAO activityDAO = ActivityDAO.getDefault();
        ActivitySearch actSearch = new ActivitySearch();
        actSearch.setBillable(new Boolean(true));
        actSearch.setStartStartDate(start);
        actSearch.setEndStartDate(end);


        List<Activity> actividadesTotal = new ArrayList<Activity>();
        Hashtable user_roles = new Hashtable();

        for (ProjectRole proyRole : roles) {
            actSearch.setRole(proyRole);
            List<Activity> actividades = activityDAO.search(actSearch,
                    new SortCriteria("startDate", false));
            actividadesTotal.addAll(actividades);
        }

        for (Activity act : actividadesTotal) {
            String key = act.getRole().getId().toString()
                    + act.getUser().getId().toString();

            if (!user_roles.containsKey(key)) {
                Hashtable value = new Hashtable();
                value.put("ROLE", act.getRole());
                value.put("USER", act.getUser());
                user_roles.put(key, value);
            }
        }

        Enumeration en = user_roles.keys();

        while (en.hasMoreElements()) {
            String key = (String) en.nextElement();
            Hashtable pair = (Hashtable) user_roles.get(key);
            actSearch.setBillable(new Boolean(true));
            actSearch.setStartStartDate(start);
            actSearch.setEndStartDate(end);

            ProjectRole pR = (ProjectRole) pair.get("ROLE");
            User u = (User) pair.get("USER");
            actSearch.setRole(pR);
            actSearch.setUser(u);
            List<Activity> actividadesUsuarioRol = activityDAO.search(
                    actSearch, new SortCriteria("startDate", false));

            BillBreakDown brd = new BillBreakDown();
            brd.setConcept("Imputaciones (usuario - rol): " + u.getName()
                    + " - " + pR.getName());
            brd.setAmount(pR.getCostPerHour());
            brd.setIva(new BigDecimal(ConfigurationUtil.getDefault().getIva()));
            BigDecimal unitsTotal = new BigDecimal(0);
            for (Activity act : actividadesUsuarioRol) {
                BigDecimal unitsActual = new BigDecimal(act.getDuration());
                unitsActual = unitsActual.divide(new BigDecimal(60), 2,
                        RoundingMode.HALF_UP);
                unitsTotal = unitsTotal.add(unitsActual);
            }
            brd.setUnits(unitsTotal);
            brd.setSelected(true);
            desgloses.add(brd);
        }


        for (ProjectCost proyCost : costes) {
            BillBreakDown brd = new BillBreakDown();
            brd.setConcept("Coste: " + proyCost.getName());
            brd.setUnits(new BigDecimal(1));
            brd.setAmount(proyCost.getCost());
            brd.setIva(new BigDecimal(ConfigurationUtil.getDefault().getIva()));
            brd.setSelected(true);
            desgloses.add(brd);
        }

        return desgloses;

    }

    /**
     * Get a list of bills between start and end dates
     *
     * @param search
     * @param sort
     * @param start
     * @param end
     * @return
     */
    public List<Bill> getEntities(BillSearch search, SortCriteria sort, Date start, Date end) {
        List<Bill> res = billDAO.search(search, sort);
        List<Bill> list = new ArrayList<Bill>();
        Calendar calendar = Calendar.getInstance();
        Bill newBill = null;

        // Recorremos la lista de Bills
        for (Bill item : res) {
            // Metemos en el calendar la fecha del item
            calendar.setTime(item.getCreationDate());
            calendar.add(Calendar.DAY_OF_MONTH, item.getExpiration());

            // Si la fecha está dentro del rango de fechas seleccionado se añade a la lista con la fecha calculada del próximo pago
            if ((calendar.getTimeInMillis() >= start.getTime()) && (calendar.getTimeInMillis() <= end.getTime())) {
                // Creamos un nuevo PeriodicalAccountEntry
                newBill = createNewBill(item, new Date(calendar.getTimeInMillis()));

                // Añadimos a la lista
                list.add(newBill);
            }
        }

        return list;
    }

    /**
     * Create a new Bill
     *
     * @param bill
     * @param nextDate
     * @return
     */
    private Bill createNewBill(Bill bill, Date nextDate) {
        Bill newBill = new Bill();

        newBill.setCreationDate(nextDate);
        newBill.setAmount(bill.getAmount());
        newBill.setBillType(bill.getBillType());
        newBill.setBreakDown(bill.getBreakDown());
        newBill.setContact(bill.getContact());
        newBill.setEndBillDate(bill.getEndBillDate());
        newBill.setEntries(bill.getEntries());
        newBill.setEntriesNumber(bill.getEntriesNumber());
        newBill.setExpiration(bill.getExpiration());
        newBill.setFile(bill.getFile());
        newBill.setFileMime(bill.getFileMime());
        newBill.setName(bill.getName());
        newBill.setNumber(bill.getNumber());
        newBill.setObservations(bill.getObservations());
        newBill.setPaymentMode(bill.getPaymentMode());
        newBill.setProject(bill.getProject());
        newBill.setProvider(bill.getProvider());
        newBill.setStartBillDate(bill.getStartBillDate());
        newBill.setState(bill.getState());
        return newBill;
    }

/* Bill - generated by stajanov (do not edit/delete) */


    /**
     * Logger
     */
    private static final Log log = LogFactory.getLog(BillManager.class);

    /**
     * Bill DAO *
     */
    private BillDAO billDAO;

    /**
     * Get default BillManager as defined in Spring's configuration file.
     *
     * @return the default singleton BillManager
     */
    public static BillManager getDefault() {
        return (BillManager) SpringUtils.getSpringBean("managerBill");
    }

    /**
     * Empty constructor needed by CGLIB (Spring AOP)
     */
    protected BillManager() {
    }

    /**
     * Default constructor
     *
     * @deprecated do not construct managers alone: use Spring's declared beans
     */
    public BillManager(BillDAO billDAO) {
        this.billDAO = billDAO;
    }

    /**
     * List bills.
     *
     * @param search search filter to apply
     * @param sort   sorting criteria
     * @return the list of all bills sorted by requested criterion
     */
    public List<Bill> getAllEntities(BillSearch search, SortCriteria sort) {
        return billDAO.search(search, sort);
    }

    /**
     * Get bill by primary key.
     *
     * @return bill selected by id.
     */
    public Bill getEntityById(int id) {
        return billDAO.getById(id);
    }

    /**
     * Insert bill.
     */
    public void insertEntity(Bill bill) {
        billDAO.insert(bill);
    }

    /**
     * Update bill.
     */
    public void updateEntity(Bill bill) {
        billDAO.update(bill);
    }

    /**
     * Delete bill.
     */
    public void deleteEntity(Bill bill) {
        billDAO.delete(bill);
    }
/* Bill - generated by stajanov (do not edit/delete) */

}
