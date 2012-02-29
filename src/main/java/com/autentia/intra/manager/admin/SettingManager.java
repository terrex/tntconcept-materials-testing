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


package com.autentia.intra.manager.admin;

import com.autentia.intra.businessobject.Setting;
import com.autentia.intra.businessobject.SettingType;
import com.autentia.intra.dao.SortCriteria;
import com.autentia.intra.dao.hibernate.SettingDAO;
import com.autentia.intra.dao.search.SettingSearch;
import com.autentia.intra.manager.security.AuthenticationManager;
import com.autentia.intra.util.SpringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;


public class SettingManager {
    /**
     * Save a property for current user
     *
     * @param propertyPath property path
     * @param value        property value
     */
    public static void setValue(Setting val, String value) {
        val.setType(SettingType.STRING);
        val.setValue(value);
    }

    /**
     * Save a property for current user
     *
     * @param propertyPath property path
     * @param value        property value
     */
    public static void setValue(Setting val, int value) {
        val.setType(SettingType.INT);
        val.setValue(Integer.toString(value));
    }

    /**
     * Save a property for current user
     *
     * @param propertyPath property path
     * @param value        property value
     */
    public static void setValue(Setting val, float value) {
        val.setType(SettingType.FLOAT);
        val.setValue(Float.toString(value));

    }

    /**
     * Save a property for current user
     *
     * @param propertyPath property path
     * @param value        property value
     */
    public static void setValue(Setting val, boolean value) {
        val.setType(SettingType.BOOLEAN);
        val.setValue(Boolean.toString(value));
    }

    /**
     * Get a property value as String
     *
     * @param propertyPath property path
     * @param defaultValue default value for property
     * @return property value
     */
    public static String getString(Setting val, String defaultValue) {

        return (val == null || val.getValue() == null)
                ? defaultValue
                : val.getValue();
    }

    /**
     * Get a property value as int
     *
     * @param propertyPath property path
     * @param defaultValue default value for property
     * @return property value
     */
    public static int getInt(Setting val, int defaultValue) {

        return (val == null || val.getValue() == null)
                ? defaultValue
                : Integer.parseInt(val.getValue());
    }

    /**
     * Get a property value as float
     *
     * @param propertyPath property path
     * @param defaultValue default value for property
     * @return property value
     */
    public static float getFloat(Setting val, float defaultValue) {

        return (val == null || val.getValue() == null)
                ? defaultValue
                : Float.parseFloat(val.getValue());
    }

    /**
     * Get a property value as boolean
     *
     * @param propertyPath property path
     * @param defaultValue default value for property
     * @return property value
     */
    public static boolean getBoolean(Setting val, boolean defaultValue) {

        return (val == null || val.getValue() == null)
                ? defaultValue
                : Boolean.parseBoolean(val.getValue());
    }

    /**
     * Insert/update a Setting
     *
     * @param val Setting to save to database
     */
    public void save(Setting val) {
        if (val.getId() == null) {
            settingDAO.insert(val);
        } else {
            settingDAO.update(val);
        }
    }

    /**
     * Get a property's persistent object
     *
     * @param propertyPath         property path
     * @param returnNewIfNotExists return a newly created object if it does not exist (null otherwise)
     * @return the Setting object which stores the property in database (if it exists) or a new Setting or null
     *         <p/>
     *         Paco changed the visibility of this method... (ask him why)
     */
    public Setting get(String propertyPath, boolean returnNewIfNotExists) {
        SettingSearch s = new SettingSearch();
        s.setName(propertyPath);
        s.setOwnerId(AuthenticationManager.getDefault().getCurrentPrincipal().getUser().getId());
        List<Setting> vals = settingDAO.search(s, null);
        if (vals.size() > 0) {
            return vals.get(0);
        } else if (returnNewIfNotExists) {
            Setting val = new Setting();
            val.setName(propertyPath);
            return val;
        } else {
            return null;
        }
    }

/* generated by stajanov (do not edit/delete) */


    /**
     * Logger
     */
    private static final Log log = LogFactory.getLog(SettingManager.class);

    /**
     * Setting DAO *
     */
    private SettingDAO settingDAO;

    /**
     * Get default SettingManager as defined in Spring's configuration file.
     *
     * @return the default singleton SettingManager
     */
    public static SettingManager getDefault() {
        return (SettingManager) SpringUtils.getSpringBean("managerSetting");
    }

    /**
     * Empty constructor needed by CGLIB (Spring AOP)
     */
    protected SettingManager() {
    }

    /**
     * Default constructor
     *
     * @deprecated do not construct managers alone: use Spring's declared beans
     */
    public SettingManager(SettingDAO settingDAO) {
        this.settingDAO = settingDAO;
    }

    /**
     * List settings.
     *
     * @param search search filter to apply
     * @param sort   sorting criteria
     * @return the list of all settings sorted by requested criterion
     */
    public List<Setting> getAllEntities(SettingSearch search, SortCriteria sort) {
        return settingDAO.search(search, sort);
    }

    /**
     * Get setting by primary key.
     *
     * @return setting selected by id.
     */
    public Setting getEntityById(int id) {
        return settingDAO.getById(id);
    }

    /**
     * Insert setting.
     */
    public void insertEntity(Setting setting) {
        settingDAO.insert(setting);
    }

    /**
     * Update setting.
     */
    public void updateEntity(Setting setting) {
        settingDAO.update(setting);
    }

    /**
     * Delete setting.
     */
    public void deleteEntity(Setting setting) {
        settingDAO.delete(setting);
    }

/* generated by stajanov (do not edit/delete) */


}
