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

package com.autentia.intra.util;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.*;

public class ConfigurationUtil {
    private static final Log log = LogFactory.getLog(ConfigurationUtil.class);

    /**
     * Get default configuration bean defined in Spring.
     *
     * @return the default configuration bean
     */
    public static ConfigurationUtil getDefault() {
        return (ConfigurationUtil) SpringUtils.getSpringBean("configuration");
    }

    /**
     * Configuration object
     */
    private PropertiesConfiguration config = null;

    /**
     * Configuration directory
     */
    private String configDir = null;

    /**
     * Constructor
     *
     * @param jndiPathVar JNDI variable in which configuration directory is stored
     * @param file        path to configuration file
     */
    public ConfigurationUtil(String jndiPathVar, String file) throws ConfigurationException, NamingException {
        Context ctx = new InitialContext();
        configDir = (String) ctx.lookup(jndiPathVar);
        if (!configDir.endsWith("/") && !configDir.endsWith("\\")) {
            configDir += "/";
        }
        config = new PropertiesConfiguration(configDir + file);
    }

    /**
     * Get configuration directory (with ending "/")
     *
     * @return configuration directory (with ending "/")
     */
    public String getConfigDir() {
        return configDir;
    }

    /**
     * Get id of public bulletin board category.
     *
     * @return id of public bulletin board category
     */
    public int getIdPublicCategory() {
        return Integer.parseInt(getProperty("idPublicCategory", "1"));
    }

    /**
     * Get id of public bulletin board category.
     * Get id of public bulletin board category.
     *
     * @return id of public bulletin board category
     */
    public int getIdOurCompany() {
        return Integer.parseInt(getProperty("idOurCompany", "1"));
    }

    /**
     * Get id of public bulletin board category.
     * Get id of public bulletin board category.
     *
     * @return id of public bulletin board category
     */
    public int getIdLabCompany() {
        return Integer.parseInt(getProperty("idLabCompany", "2"));
    }

    /**
     * Get id of the main Category's Id for Quality Documents.
     *
     * @return id of quality document's category
     */
    public int getQualityDocumentCategoryId() {
        return Integer.parseInt(getProperty("qualityDocumentCategoryId", "1"));
    }

    /**
     * Get id of the Category's Id for User Documents.
     *
     * @return id of User Documents Category.
     */
    public int getUserDocumentCategoryId() {
        return Integer.parseInt(getProperty("userDocumentCategoryId", "4"));
    }

    /**
     * Get uploaded files path (always with a trailing /).
     *
     * @return uploaded files root path
     */
    public String getUploadPath() {
        String ret = getProperty("pathFicheros", "/var/lib/tntconcept/upload");
        if (!ret.endsWith("\\") || !ret.endsWith("/")) {
            ret += File.separator;
        }
        return ret;
    }

    public String getUploadFileWholePath(String type, Integer id, String filename) {
        return getUploadPath() + type + File.separator + id + File.separator + filename;
    }

    /**
     * Get personal reports path (always with a trailing /).
     *
     * @return personal reports path
     */
    public String getReportPath() {
        String ret = getProperty("pathReports", "/var/lib/tntconcept/reports");
        if (!ret.endsWith("\\") || !ret.endsWith("/")) {
            ret += File.separator;
        }
        return ret;
    }

    /**
     * Get number of children objectives that will be created when a previous
     * objective expires.
     *
     * @return number of children objectives
     */
    public int getChildObjectivesCount() {
        return Integer.parseInt(getProperty("childObjectivesCount", "3"));
    }

    /**
     * Get minimum number of children objectives that can be created when a previous
     * objective expires.
     *
     * @return number of children objectives
     */
    public int getMinChildObjectivesCount() {
        return Integer.parseInt(getProperty("minChildObjectivesCount", "2"));
    }

    /**
     * Get maximum number of children objectives that can be created when a previous
     * objective expires.
     *
     * @return number of children objectives
     */
    public int getMaxChildObjectivesCount() {
        return Integer.parseInt(getProperty("maxChildObjectivesCount", "5"));
    }

    /**
     * Get the number of days in milliseconds before a category is considered as updated
     *
     * @return number of days in milliseconds
     */
    public long getUpdatedCategoryDaysInMillis() {
        return (long) Long.parseLong(getProperty("updatedCategoryDays", "5")) * 24 * 60 * 60 * 1000;
    }

    public int getCostId() {
        return Integer.parseInt(getProperty("costId", "2"));
    }

    public int getIncomeId() {
        return Integer.parseInt(getProperty("incomeId", "1"));
    }

    public int getInitialEntryId() {
        return Integer.parseInt(getProperty("initialEntryId", "4"));
    }

    public int getRoleAdminId() {
        return Integer.parseInt(getProperty("roleAdminId", "1"));
    }

    public int getRoleSupervisorId() {
        return Integer.parseInt(getProperty("roleSupervisorId", "2"));
    }

    public int getRoleUserId() {
        return Integer.parseInt(getProperty("roleUserId", "3"));
    }

    public int getRoleStaffId() {
        return Integer.parseInt(getProperty("roleStaffId", "4"));
    }

    public int getRoleClientId() {
        return Integer.parseInt(getProperty("roleClientId", "5"));
    }

    public float getIva() {
        return Float.parseFloat(getProperty("iva", "16"));
    }

    public String getIdentityCardValidator() {
        return getProperty("identityCardValidator", "com.autentia.intra.validator.NifValidator");
    }

    public String getMoneyValidator() {
        return getProperty("moneyValidator", "com.autentia.intra.validator.EuroValidator");
    }

    public String getAccountEntryValidator() {
        return getProperty("accountEntryValidator", "com.autentia.intra.validator.AccountEntryValidator");
    }

    public String getPeriodicalAccountEntryValidator() {
        return getProperty("periodicalAccountEntryValidator", "com.autentia.intra.validator.PeriodicalAccountEntryValidator");
    }

    public int getAccountEntryMaximumYears() {
        return Integer.parseInt(getProperty("accountEntryMaximumYears", "4"));
    }

    public String getReportCSVDelimiter() {
        return getProperty("reportCSVDelimiter", ";");
    }

    public int getLoadingReportOnLoad() {
        return Integer.parseInt(getProperty("loadingReportOnLoad", "1"));
    }


    public int getOrganizationTypeClient() {
        return Integer.parseInt(getProperty("organizationTypeClient", "1"));
    }

    public int getOrganizationTypeProvider() {
        return Integer.parseInt(getProperty("organizationTypeProvider", "2"));
    }

    public int getOrganizationTypeProviderAndClient() {
        return Integer.parseInt(getProperty("organizationTypeProviderAndClient", "3"));
    }

    public boolean getShowLogs() {
        return Boolean.parseBoolean(getProperty("showLogs", "false"));
    }

    /**
     * Get a configuration property by name.
     *
     * @param propertyName property name
     * @return a named property
     */
    private String getProperty(String propertyName, String defaultValue) {
        String ret = config.getString(propertyName);
        if (ret == null) ret = defaultValue;
        return ret;
    }

    /**
     * return the name of the logo file for reports. (just the name)
     */
    public String getLogoName() {
        return getProperty("reportLogoName", "logo.bmp");
    }


    /**
     * Return the path of the logo (including name)
     *
     * @return
     */

    public String getLogoPath() {
        return getConfigDir() + getLogoName();
    }


    public InputStream getLogoAsStream() {
        File f = new File(getLogoPath());

        try {
            return new BufferedInputStream(new FileInputStream(f));
        } catch (FileNotFoundException e) {
            log.error("Error reading logo file", e);
            return null;
        }


    }

    /**
     * Return a boolean value representing the value of isUsingExternalCss.
     *
     * @return
     */
    public boolean isUsingExternalCss() {
        String valor = getProperty("isUsingExternalCss", "false");
        Boolean ret = new Boolean(valor);
        return ret.booleanValue();
    }

    /**
     * Return the name of the folder of docroot (external files)
     *
     * @return
     */
    public String getDocumentoRootFolder() {
        return getProperty("documentRoot", "docroot");
    }

    /**
     * Return the absolute path of the folder docroot (external files) with ending /
     *
     * @return
     */
    public String getDocumentRootPath() {
        String path = getDefault().getConfigDir();

        if (!path.endsWith("\\") || !path.endsWith("/")) {
            path += File.separator;
        }

        path += getDefault().getDocumentoRootFolder();

        if (!path.endsWith("\\") || !path.endsWith("/")) {
            path += File.separator;
        }

        return path;


    }

    public String getSecurityMatrix() {
        return getProperty("securityMatrix", "securityConfiguration.xml");
    }


}
