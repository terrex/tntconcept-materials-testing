package com.autentia.intra.bean.holiday;

import com.autentia.intra.bean.BaseBean;
import com.autentia.intra.businessobject.*;
import com.autentia.intra.dao.SortCriteria;
import com.autentia.intra.dao.search.HolidaySearch;
import com.autentia.intra.dao.search.RequestHolidaySearch;
import com.autentia.intra.dao.search.UserSearch;
import com.autentia.intra.manager.admin.UserManager;
import com.autentia.intra.manager.holiday.HolidayManager;
import com.autentia.intra.manager.holiday.RequestHolidayManager;
import com.autentia.intra.manager.security.AuthenticationManager;

import javax.faces.event.ValueChangeEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class UserHolidaysStateBean extends BaseBean {


    /**
     * Default sort column
     */
    private String sortColumn = "name";

    /**
     * Default sort order
     */
    private boolean sortAscending = false;

    private Date chargeYear = null;

    public UserHolidaysStateBean() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.MONTH, cal.getMinimum(Calendar.MONTH));
        cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMinimum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));

        chargeYear = cal.getTime();
    }

    public void changeDate(ValueChangeEvent event) {
        Date d = (Date) event.getNewValue();
        setChargeYear(d);
        setUserState(calcUserHolidaysState(AuthenticationManager.getDefault().getCurrentPrincipal().getUser()));
    }

    UserHolidaysState userState = null;

    public List<UserHolidaysState> getAll() {

        List<UserHolidaysState> ret = new ArrayList<UserHolidaysState>();

        if (chargeYear == null)
            return ret;


        UserManager userManager = UserManager.getDefault();
        UserSearch searchUser = new UserSearch();
        searchUser.setActive(true);

        List<User> users = userManager.getAllEntities(searchUser, new SortCriteria(sortColumn, sortAscending));

        for (User us : users) {
            ret.add(calcUserHolidaysState(us));
        }

        return ret;

    }


    private UserHolidaysState calcUserHolidaysState(User usuario) {
        UserHolidaysState uhs = new UserHolidaysState();

        uhs.setUser(usuario);
        uhs.setTotalYear(usuario.getAgreement().getHolidays());

        int acceptedHolidays = 0;

        if (chargeYear != null) {

            HolidayManager fiestasManager = HolidayManager.getDefault();

            // We must take in account previous year holidays and next year holidays

            Calendar calMin = Calendar.getInstance();
            calMin.setTime(chargeYear);
            calMin.set(Calendar.MONTH, calMin.getMinimum(Calendar.MONTH));
            calMin.set(Calendar.DAY_OF_MONTH, calMin.getMinimum(Calendar.DAY_OF_MONTH));
            calMin.set(Calendar.HOUR_OF_DAY, calMin.getMinimum(Calendar.HOUR_OF_DAY));
            calMin.set(Calendar.MINUTE, calMin.getMinimum(Calendar.MINUTE));
            calMin.set(Calendar.SECOND, calMin.getMinimum(Calendar.SECOND));
            calMin.set(Calendar.MILLISECOND, calMin.getMinimum(Calendar.MILLISECOND));

            Calendar calMax = Calendar.getInstance();
            calMax.setTime(chargeYear);
            calMax.set(Calendar.MONTH, calMax.getMaximum(Calendar.MONTH));
            calMax.set(Calendar.DAY_OF_MONTH, calMax.getMaximum(Calendar.DAY_OF_MONTH));
            calMax.set(Calendar.HOUR_OF_DAY, calMax.getMaximum(Calendar.HOUR_OF_DAY));
            calMax.set(Calendar.MINUTE, calMax.getMaximum(Calendar.MINUTE));
            calMax.set(Calendar.SECOND, calMax.getMaximum(Calendar.SECOND));
            calMax.set(Calendar.MILLISECOND, calMax.getMaximum(Calendar.MILLISECOND));

            calMin.add(Calendar.YEAR, -1);
            calMax.add(Calendar.YEAR, 1);

            HolidaySearch fiestaSearch = new HolidaySearch();
            fiestaSearch.setStartDate(calMin.getTime());
            fiestaSearch.setEndDate(calMax.getTime());

            List<Holiday> listFiestas = fiestasManager.getAllEntities(fiestaSearch, null);


            calMin.setTime(chargeYear);
            calMin.set(Calendar.MONTH, calMin.getMinimum(Calendar.MONTH));
            calMin.set(Calendar.DAY_OF_MONTH, calMin.getMinimum(Calendar.DAY_OF_MONTH));
            calMin.set(Calendar.HOUR_OF_DAY, calMin.getMinimum(Calendar.HOUR_OF_DAY));
            calMin.set(Calendar.MINUTE, calMin.getMinimum(Calendar.MINUTE));
            calMin.set(Calendar.SECOND, calMin.getMinimum(Calendar.SECOND));
            calMin.set(Calendar.MILLISECOND, calMin.getMinimum(Calendar.MILLISECOND));


            calMax.setTime(chargeYear);
            calMax.set(Calendar.MONTH, calMax.getMaximum(Calendar.MONTH));
            calMax.set(Calendar.DAY_OF_MONTH, calMax.getMaximum(Calendar.DAY_OF_MONTH));
            calMax.set(Calendar.HOUR_OF_DAY, calMax.getMaximum(Calendar.HOUR_OF_DAY));
            calMax.set(Calendar.MINUTE, calMax.getMaximum(Calendar.MINUTE));
            calMax.set(Calendar.SECOND, calMax.getMaximum(Calendar.SECOND));
            calMax.set(Calendar.MILLISECOND, calMax.getMaximum(Calendar.MILLISECOND));


            RequestHolidayManager holyManager = RequestHolidayManager.getDefault();
            RequestHolidaySearch holSearch = new RequestHolidaySearch();
            holSearch.setUserRequest(uhs.getUser());
            holSearch.setState(HolidayState.ACCEPT);
            holSearch.setStartChargeYear(calMin.getTime());
            holSearch.setEndChargeYear(calMax.getTime());


            List<RequestHoliday> listH = holyManager.getAllEntities(holSearch, null);

            for (RequestHoliday rH : listH) {
                Calendar cActual = Calendar.getInstance();
                cActual.setTime(rH.getBeginDate());
                while (!cActual.getTime().after(rH.getFinalDate())) {
                    if (cActual.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
                            && cActual.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                        boolean isFiesta = false;

                        for (Holiday fiest : listFiestas) {
                            Calendar cFiesta = Calendar.getInstance();
                            cFiesta.setTime(fiest.getDate());
                            if (cFiesta.get(Calendar.YEAR) == cActual.get(Calendar.YEAR) &&
                                    cFiesta.get(Calendar.MONTH) == cActual.get(Calendar.MONTH) &&
                                    cFiesta.get(Calendar.DAY_OF_MONTH) == cActual.get(Calendar.DAY_OF_MONTH)) {
                                isFiesta = true;
                            }
                        }

                        if (!isFiesta) {
                            acceptedHolidays++;
                        }
                    }

                    cActual.add(Calendar.DAY_OF_MONTH, 1);
                }

            }
            uhs.setTotalAccepted(acceptedHolidays);

            Calendar calAuxCont = Calendar.getInstance();
            calAuxCont.setTime(uhs.getUser().getStartDate());

            Calendar calAux = Calendar.getInstance();
            calAux.setTime(chargeYear);
            int yearCharge = calAux.get(Calendar.YEAR);
            int yearContract = calAuxCont.get(Calendar.YEAR);

            if (yearCharge == yearContract) {
                // Dividimos los días de cada usuario entre los meses del año.
                double ratio = uhs.getUser().getAgreement().getHolidays() / 12.0;
                Calendar cHoy = Calendar.getInstance();
                cHoy.setTime(new Date());
                int monthToday = cHoy.get(Calendar.MONTH);
                int monthContract = calAuxCont.get(Calendar.MONTH);

                int meses = (monthToday - monthContract) + 1;


                double diasVacaciones = meses * ratio;
                double aux = Math.ceil(diasVacaciones);
                uhs.setTotalYear((int) aux);


            }

        }

        return uhs;


    }


    public String myHolidays() {
        setUserState(calcUserHolidaysState(AuthenticationManager.getDefault().getCurrentPrincipal().getUser()));
        return "myHolidays";
    }


    public Date getChargeYear() {
        return chargeYear;
    }

    public void setChargeYear(Date chargeYear) {
        this.chargeYear = chargeYear;
    }

    public String getSortColumn() {
        return sortColumn;
    }

    public void setSortColumn(String sortColumn) {
        this.sortColumn = sortColumn;
    }

    public boolean isSortAscending() {
        return sortAscending;
    }

    public void setSortAscending(boolean sortAscending) {
        this.sortAscending = sortAscending;
    }

    public UserHolidaysState getUserState() {
        return userState;
    }

    public void setUserState(UserHolidaysState userHolidaysState) {
        this.userState = userHolidaysState;
    }


}
