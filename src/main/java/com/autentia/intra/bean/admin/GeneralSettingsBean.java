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
package com.autentia.intra.bean.admin;

import com.autentia.intra.bean.BaseBean;
import com.autentia.intra.businessobject.Setting;
import com.autentia.intra.manager.admin.SettingManager;
import com.autentia.intra.util.FacesUtils;
import com.autentia.intra.util.SettingPath;
import org.apache.myfaces.custom.schedule.model.ScheduleModel;
import org.apache.myfaces.custom.schedule.renderer.AbstractScheduleRenderer;

import javax.faces.model.SelectItem;

/**
 * Settings bean for bitacore
 *
 * @author german
 */
public class GeneralSettingsBean extends BaseBean {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private final SelectItem[] themeItems = {
            new SelectItem(AbstractScheduleRenderer.EVOLUTION_THEME, FacesUtils.formatMessage("activitys.settings.theme.evolution")),
            new SelectItem(AbstractScheduleRenderer.OUTLOOK_THEME, FacesUtils.formatMessage("activitys.settings.theme.outlookxp")),
            new SelectItem(AbstractScheduleRenderer.DEFAULT_THEME, FacesUtils.formatMessage("activitys.settings.theme.default"))
    };

    private final SelectItem[] modeItems = {
            new SelectItem(ScheduleModel.MONTH, FacesUtils.formatMessage("activitys.settings.mode.month")),
            new SelectItem(ScheduleModel.WEEK, FacesUtils.formatMessage("activitys.settings.mode.week")),
            new SelectItem(ScheduleModel.WORKWEEK, FacesUtils.formatMessage("activitys.settings.mode.workweek")),
            new SelectItem(ScheduleModel.DAY, FacesUtils.formatMessage("activitys.settings.mode.day"))
    };

    /**
     * Settings manager
     */
    private static final SettingManager settings = SettingManager
            .getDefault();

    /**
     * preferred theme of schedule
     */
    private String theme = SettingManager.getString(settings.get(SettingPath.BITACORE_PREFERRED_THEME, false), AbstractScheduleRenderer.OUTLOOK_THEME);


    /**
     * preferred mode of schedule
     */
    private int mode = SettingManager.getInt(settings.get(SettingPath.BITACORE_PREFERRED_MODE, false), ScheduleModel.MONTH);


    /**
     * preferred display hour from
     */
    private int displayHourFrom = SettingManager.getInt(settings.get(SettingPath.BITACORE_PREFERRED_DISPLAY_HOUR_FROM, false), 8);


    /**
     * preferred display hour until
     */
    private int displayHourUntil = SettingManager.getInt(settings.get(SettingPath.BITACORE_PREFERRED_DISPLAY_HOUR_UNTIL, false), 19);

    /**
     * preferred working hour day start
     */
    private int workingDayHourStarts = SettingManager.getInt(settings.get(SettingPath.BITACORE_PREFERRED_DAY_HOUR_START, false), 9);

    /**
     * preferred working hours for day
     */
    private int workingHours = SettingManager.getInt(settings.get(SettingPath.BITACORE_PREFERRED_DAY_HOURS, false), 8);


    /**
     * preferred header format
     */
    private String headerFormat = SettingManager.getString(settings.get(SettingPath.BITACORE_PREFERRED_HEADER_FORMAT, false), "dd/MM/yy");


    private boolean loadExtraDays = true;


    /**
     * Number of items in list
     */
    private int listSize = SettingManager.getInt(settings.get(SettingPath.GENERAL_PREFERRED_LIST_SIZE, false), 12);


    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public int getDisplayHourFrom() {
        return displayHourFrom;
    }

    public void setDisplayHourFrom(int displayHourFrom) {
        this.displayHourFrom = displayHourFrom;
    }

    public int getDisplayHourUntil() {
        return displayHourUntil;
    }

    public void setDisplayHourUntil(int displayHourUntil) {
        this.displayHourUntil = displayHourUntil;
    }

    public int getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(int workingHours) {
        this.workingHours = workingHours;
    }

    public String getHeaderFormat() {
        return headerFormat;
    }

    public void setLoadExtraDays(boolean loadExtraDays) {
        this.loadExtraDays = loadExtraDays;
    }

    public boolean getLoadExtraDays() {
        return loadExtraDays;
    }

    public void setHeaderFormat(String headerFormat) {
        this.headerFormat = headerFormat;
    }

    public SelectItem[] getThemeItems() {
        return themeItems;
    }

    public SelectItem[] getModeItems() {
        return modeItems;
    }

    public int getWorkingDayHourStarts() {
        return workingDayHourStarts;
    }

    public void setWorkingDayHourStarts(int workingDayHourStarts) {
        this.workingDayHourStarts = workingDayHourStarts;
    }

    public int getWorkingDayHourEnds() {
        return workingDayHourStarts + workingHours;
    }


    public int getListSize() {
        return listSize;
    }

    public void setListSize(int listSize) {
        this.listSize = listSize;
    }

    public void save() {
        Setting val = settings.get(SettingPath.BITACORE_PREFERRED_THEME, true);
        SettingManager.setValue(val, theme);
        settings.save(val);

        val = settings.get(SettingPath.BITACORE_PREFERRED_MODE, true);
        SettingManager.setValue(val, mode);
        settings.save(val);

        val = settings.get(SettingPath.BITACORE_PREFERRED_DISPLAY_HOUR_FROM, true);
        SettingManager.setValue(val, displayHourFrom);
        settings.save(val);

        val = settings.get(SettingPath.BITACORE_PREFERRED_DISPLAY_HOUR_UNTIL, true);
        SettingManager.setValue(val, displayHourUntil);
        settings.save(val);

        val = settings.get(SettingPath.BITACORE_PREFERRED_DAY_HOUR_START, true);
        SettingManager.setValue(val, workingDayHourStarts);
        settings.save(val);

        val = settings.get(SettingPath.BITACORE_PREFERRED_DAY_HOURS, true);
        SettingManager.setValue(val, workingHours);
        settings.save(val);

        val = settings.get(SettingPath.BITACORE_PREFERRED_HEADER_FORMAT, true);
        SettingManager.setValue(val, headerFormat);
        settings.save(val);


        val = settings.get(SettingPath.GENERAL_PREFERRED_LIST_SIZE, true);
        SettingManager.setValue(val, listSize);
        settings.save(val);

    }

}
