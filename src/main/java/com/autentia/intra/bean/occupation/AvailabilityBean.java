package com.autentia.intra.bean.occupation;

import com.autentia.intra.bean.BaseBean;
import com.autentia.intra.businessobject.*;
import com.autentia.intra.dao.SortCriteria;
import com.autentia.intra.dao.search.HolidaySearch;
import com.autentia.intra.dao.search.OccupationSearch;
import com.autentia.intra.dao.search.RequestHolidaySearch;
import com.autentia.intra.dao.search.UserSearch;
import com.autentia.intra.manager.activity.OccupationManager;
import com.autentia.intra.manager.admin.UserManager;
import com.autentia.intra.manager.holiday.HolidayManager;
import com.autentia.intra.manager.holiday.RequestHolidayManager;
import com.autentia.intra.util.DateUtils;
import com.autentia.intra.util.FacesUtils;
import com.autentia.jsf.component.ocupation.OcupationEntry;
import com.autentia.jsf.component.ocupation.OcupationModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class AvailabilityBean extends BaseBean {


    private static final long serialVersionUID = 1L;
    private final UserManager userMgr = UserManager.getDefault();
    private Date selectedDate = null;


    public AvailabilityBean() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMinimum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));

        selectedDate = cal.getTime();
    }

    public List<Availability> getAll() {


        List<Availability> ret = new ArrayList<Availability>();

        UserSearch searchUser = new UserSearch();
        searchUser.setActive(true);
        SortCriteria sCriteria = new SortCriteria("name");

        List<User> users = userMgr.getAllEntities(searchUser, sCriteria);

        for (User user : users) {

            List<OcupationEntry> listOccupations = new ArrayList<OcupationEntry>();
            OcupationModelImpl model = new OcupationModelImpl();

            Availability availability = new Availability();
            availability.setUser(user);
            availability.setModel(model);


            fillModelHolidays(user, model);
            fillAvailability(user, model);
            ret.add(availability);

        }

        return ret;
    }


    private void fillAvailability(User user, OcupationModel model) {
        Calendar calMin = Calendar.getInstance();
        Calendar calMax = Calendar.getInstance();

        calMin.setTime(selectedDate);
        calMin.set(Calendar.DAY_OF_MONTH, calMin.getMinimum(Calendar.DAY_OF_MONTH));
        calMin.set(Calendar.HOUR_OF_DAY, calMin.getMinimum(Calendar.HOUR_OF_DAY));
        calMin.set(Calendar.MINUTE, calMin.getMinimum(Calendar.MINUTE));
        calMin.set(Calendar.SECOND, calMin.getMinimum(Calendar.SECOND));
        calMin.set(Calendar.MILLISECOND, calMin.getMinimum(Calendar.MILLISECOND));

        calMax.setTime(selectedDate);
        calMax.set(Calendar.DAY_OF_MONTH, calMax.getMaximum(Calendar.DAY_OF_MONTH));
        calMax.set(Calendar.HOUR_OF_DAY, calMax.getMaximum(Calendar.HOUR_OF_DAY));
        calMax.set(Calendar.MINUTE, calMax.getMaximum(Calendar.MINUTE));
        calMax.set(Calendar.SECOND, calMax.getMaximum(Calendar.SECOND));
        calMax.set(Calendar.MILLISECOND, calMax.getMaximum(Calendar.MILLISECOND));

        calMin.add(Calendar.MONTH, -1);
        calMax.add(Calendar.MONTH, 1);


        List<Occupation> listOcFinal = new ArrayList<Occupation>();
        OccupationManager ocManager = OccupationManager.getDefault();
        OccupationSearch ocSearch = new OccupationSearch();
        ocSearch.setUser(user);

        //                                Mes
        // Primer rango. Ocupaciones  |-------- |
        //                       Ocupacion
        //       				| --------|
        ocSearch.setStartEndDate(calMin.getTime());
        ocSearch.setEndEndDate(calMax.getTime());
        List<Occupation> listOcs1 = ocManager.getAllEntities(ocSearch, null);

        ocSearch = new OccupationSearch();
        ocSearch.setUser(user);
        //                                Mes
        // Segundo rango. Ocupaciones| -------- |
        //                       Ocupacion
        //       		                   |--------|
        ocSearch.setStartStartDate(calMin.getTime());
        ocSearch.setEndStartDate(calMax.getTime());
        List<Occupation> listOcs2 = ocManager.getAllEntities(ocSearch, null);
        //                                                  Mes
        // Tercer rango: Incluido en los anteriores.   | -------------|
        //                                                 |-----|
        for (Occupation oc : listOcs1) {
            if (!listOcFinal.contains(oc)) {
                listOcFinal.add(oc);
            }
        }

        for (Occupation oc : listOcs2) {
            if (!listOcFinal.contains(oc)) {
                listOcFinal.add(oc);
            }
        }


        for (Occupation oc : listOcFinal) {
            OcupationEntryImpl oce = new OcupationEntryImpl();
            oce.setStart(DateUtils.minHourInDate(oc.getStartDate()));
            oce.setEnd(DateUtils.maxHourInDate(oc.getEndDate()));
            oce.setVacances(false);
            oce.setDescription(oc.getProject().getName() + "(" + oc.getProject().getClient().getName() + "). " + oc.getDescription());
            oce.setDuration(oc.getDuration());
            model.addOcupationEntry(oce);
        }


    }


    /**
     * Fill holidays in listOccupations for that user.
     *
     * @param user
     * @param listOccupations
     */

    private void fillModelHolidays(User user, OcupationModel model) {
        Calendar calMin = Calendar.getInstance();
        Calendar calMax = Calendar.getInstance();

        calMin.setTime(selectedDate);
        calMin.set(Calendar.DAY_OF_MONTH, calMin.getMinimum(Calendar.DAY_OF_MONTH));
        calMin.set(Calendar.HOUR_OF_DAY, calMin.getMinimum(Calendar.HOUR_OF_DAY));
        calMin.set(Calendar.MINUTE, calMin.getMinimum(Calendar.MINUTE));
        calMin.set(Calendar.SECOND, calMin.getMinimum(Calendar.SECOND));
        calMin.set(Calendar.MILLISECOND, calMin.getMinimum(Calendar.MILLISECOND));

        calMax.setTime(selectedDate);
        calMax.set(Calendar.DAY_OF_MONTH, calMax.getMaximum(Calendar.DAY_OF_MONTH));
        calMax.set(Calendar.HOUR_OF_DAY, calMax.getMaximum(Calendar.HOUR_OF_DAY));
        calMax.set(Calendar.MINUTE, calMax.getMaximum(Calendar.MINUTE));
        calMax.set(Calendar.SECOND, calMax.getMaximum(Calendar.SECOND));
        calMax.set(Calendar.MILLISECOND, calMax.getMaximum(Calendar.MILLISECOND));

        calMin.add(Calendar.MONTH, -1);
        calMax.add(Calendar.MONTH, 1);


        RequestHolidayManager rhManager = RequestHolidayManager.getDefault();
        RequestHolidaySearch rhSearch = new RequestHolidaySearch();
        rhSearch.setUserRequest(user);
        rhSearch.setState(HolidayState.ACCEPT);
        rhSearch.setStartBeginDate(calMin.getTime());
        rhSearch.setEndBeginDate(calMax.getTime());
        rhSearch.setStartFinalDate(calMin.getTime());
        rhSearch.setEndFinalDate(calMax.getTime());

        List<RequestHoliday> listH = rhManager.getAllEntities(rhSearch, null);

        for (RequestHoliday rH : listH) {
            OcupationEntryImpl oc = new OcupationEntryImpl();
            oc.setStart(DateUtils.minHourInDate(rH.getBeginDate()));
            oc.setEnd(DateUtils.maxHourInDate(rH.getFinalDate()));
            oc.setVacances(true);
            oc.setHoliday(false);
            oc.setDescription(FacesUtils.getMessage("activity.acceptedHolidays"));
            model.addOcupationEntry(oc);
        }

        HolidaySearch monthSearch = new HolidaySearch();
        HolidayManager holidayManager = HolidayManager.getDefault();

        monthSearch.setStartDate(calMin.getTime());
        monthSearch.setEndDate(calMax.getTime());
        List<Holiday> listaHolidays = holidayManager.getAllEntities(monthSearch, null);

        for (Holiday holiday : listaHolidays) {
            OcupationEntryImpl oc = new OcupationEntryImpl();
            oc.setStart(DateUtils.minHourInDate(holiday.getDate()));
            oc.setEnd(DateUtils.maxHourInDate(holiday.getDate()));
            oc.setVacances(false);
            oc.setHoliday(true);
            oc.setDescription(holiday.getDescription());

            model.addOcupationEntry(oc);
        }
    }


    public Date getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(Date selectedDate) {
        this.selectedDate = selectedDate;
    }


}
