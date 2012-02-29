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
package com.autentia.intra.bean.activity;

import com.autentia.intra.bean.BaseBean;
import com.autentia.intra.businessobject.Activity;
import com.autentia.intra.businessobject.GlobalHourReport;
import com.autentia.intra.businessobject.Project;
import com.autentia.intra.businessobject.User;
import com.autentia.intra.dao.SortCriteria;
import com.autentia.intra.dao.search.ActivitySearch;
import com.autentia.intra.dao.search.UserSearch;
import com.autentia.intra.manager.activity.ActivityManager;
import com.autentia.intra.manager.admin.ProjectManager;
import com.autentia.intra.manager.admin.UserManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.*;

/**
 * UI bean for Activity objects.
 *
 * @author stajanov code generator
 */
public class GlobalHoursReportBean extends BaseBean {

    /**
     * Manager
     */
    private static ActivityManager manager = ActivityManager
            .getDefault();
    /**
     * Serial version field
     */
    private static final long serialVersionUID = 1L;


    /**
     * Project DAO *
     */
    private static final ProjectManager projectManager = ProjectManager
            .getDefault();


    private static UserManager userManager = UserManager
            .getDefault();

    private List<User> usuarios = null;


    private Iterator<User> iterator = null;


    /**
     * Constructor
     */
    public GlobalHoursReportBean() {

    }


    /**
     * Logger
     */
    private static final Log log = LogFactory.getLog(GlobalHoursReportBean.class);


    /**
     * Start date to show report
     */
    private Date startDate = new Date();

    /**
     * End date to show report
     */
    private Date endDate = new Date();

    /**
     * End date to show report
     */
    private Boolean billable = new Boolean(false);


    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<GlobalHourReport> getAll() {

        // Retrieve activities for every User during that period of time
        ActivitySearch search = new ActivitySearch();

        Calendar init = Calendar.getInstance();
        init.setTime(startDate);
        Calendar last = Calendar.getInstance();
        last.setTime(endDate);

        init.set(Calendar.HOUR_OF_DAY, init.getMinimum(Calendar.HOUR_OF_DAY));
        init.set(Calendar.MINUTE, init.getMinimum(Calendar.MINUTE));
        init.set(Calendar.SECOND, init.getMinimum(Calendar.SECOND));
        init.set(Calendar.MILLISECOND, init.getMinimum(Calendar.MILLISECOND));

        last.set(Calendar.HOUR_OF_DAY, last.getMaximum(Calendar.HOUR_OF_DAY));
        last.set(Calendar.MINUTE, last.getMaximum(Calendar.MINUTE));
        last.set(Calendar.SECOND, last.getMaximum(Calendar.SECOND));
        last.set(Calendar.MILLISECOND, last.getMaximum(Calendar.MILLISECOND));

        search.setStartStartDate(init.getTime());
        search.setEndStartDate(last.getTime());


        List<GlobalHourReport> listGlobal = new ArrayList<GlobalHourReport>();

        if (billable)
            search.setBillable(true);

        // Search activities during indicated dates
        List<Activity> activities = manager.getAllEntities(search, new SortCriteria("role.project.client.name"));

        // Search for projects in activities and create the global list.

        for (Activity act : activities) {
            Project proj = act.getRole().getProject();
            GlobalHourReport unit = new GlobalHourReport();
            unit.setProject(proj);

            // an entry in the list represents a project
            if (!listGlobal.contains(unit))
                listGlobal.add(unit);

            // Retrieve the stored unit and save hours
            GlobalHourReport storedUnit = listGlobal.get(listGlobal.indexOf(unit));
            float horas = act.getDuration() / 60.0f;

            storedUnit.setUserHours(act.getUser(), horas);
            storedUnit.setIterator(usuarios.iterator());


        }


        return listGlobal;

    }

    public String search() {
        return null;
    }


    public String getSize() {

        return "" + usuarios.size();
    }


    public List<User> getUsers() {
        // Retrieve active users:
        UserSearch userSearch = new UserSearch();
        userSearch.setActive(true);
        usuarios = userManager.getAllEntities(userSearch, new SortCriteria("name"));
        return usuarios;

    }

    public float getTotalHours() {

        ActivitySearch search = new ActivitySearch();

        Calendar init = Calendar.getInstance();
        init.setTime(startDate);
        Calendar last = Calendar.getInstance();
        last.setTime(endDate);

        init.set(Calendar.HOUR_OF_DAY, init.getMinimum(Calendar.HOUR_OF_DAY));
        init.set(Calendar.MINUTE, init.getMinimum(Calendar.MINUTE));
        init.set(Calendar.SECOND, init.getMinimum(Calendar.SECOND));
        init.set(Calendar.MILLISECOND, init.getMinimum(Calendar.MILLISECOND));

        last.set(Calendar.HOUR_OF_DAY, last.getMaximum(Calendar.HOUR_OF_DAY));
        last.set(Calendar.MINUTE, last.getMaximum(Calendar.MINUTE));
        last.set(Calendar.SECOND, last.getMaximum(Calendar.SECOND));
        last.set(Calendar.MILLISECOND, last.getMaximum(Calendar.MILLISECOND));

        search.setStartStartDate(init.getTime());
        search.setEndStartDate(last.getTime());

        if (billable)
            search.setBillable(true);
        // Search activities during indicated dates
        List<Activity> activities = manager.getAllEntities(search, new SortCriteria("role.project.client.name"));

        // Search for projects in activities and create the global list.
        float totalHours = 0.0f;
        for (Activity act : activities) {

            float horas = act.getDuration() / 60.0f;

            totalHours = totalHours + horas;

        }


        return totalHours;
    }


    public float getNextUserTotal() {


        ActivitySearch search = new ActivitySearch();

        Calendar init = Calendar.getInstance();
        init.setTime(startDate);
        Calendar last = Calendar.getInstance();
        last.setTime(endDate);

        init.set(Calendar.HOUR_OF_DAY, init.getMinimum(Calendar.HOUR_OF_DAY));
        init.set(Calendar.MINUTE, init.getMinimum(Calendar.MINUTE));
        init.set(Calendar.SECOND, init.getMinimum(Calendar.SECOND));
        init.set(Calendar.MILLISECOND, init.getMinimum(Calendar.MILLISECOND));

        last.set(Calendar.HOUR_OF_DAY, last.getMaximum(Calendar.HOUR_OF_DAY));
        last.set(Calendar.MINUTE, last.getMaximum(Calendar.MINUTE));
        last.set(Calendar.SECOND, last.getMaximum(Calendar.SECOND));
        last.set(Calendar.MILLISECOND, last.getMaximum(Calendar.MILLISECOND));

        search.setStartStartDate(init.getTime());
        search.setEndStartDate(last.getTime());

        if (billable)
            search.setBillable(true);

        if (iterator == null)
            iterator = getUsers().iterator();
        User user = null;
        try {
            user = iterator.next();
        } catch (NoSuchElementException e) {
            iterator = getUsers().iterator();
            user = iterator.next();
        }

        search.setUser(user);

        // Search activities during indicated dates
        List<Activity> activities = manager.getAllEntities(search, new SortCriteria("role.project.client.name"));

        // Search for projects in activities and create the global list.
        float totalHours = 0.0f;
        for (Activity act : activities) {

            float horas = act.getDuration() / 60.0f;

            totalHours = totalHours + horas;

        }


        return totalHours;
    }

    public Boolean getBillable() {
        return billable;
    }

    public void setBillable(Boolean billable) {
        this.billable = billable;
    }


}
