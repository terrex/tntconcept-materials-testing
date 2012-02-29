package com.autentia.intra.manager.activity;

import com.autentia.intra.util.SpringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/* Activity - generated by stajanov (do not edit/delete) */


public class GlobalHourReportManager {


    /**
     * Logger
     */
    private static final Log log = LogFactory.getLog(GlobalHourReportManager.class);


    public static GlobalHourReportManager getDefault() {
        return (GlobalHourReportManager) SpringUtils.getSpringBean("managerGlobalHoursReport");
    }

    /**
     * Empty constructor needed by CGLIB (Spring AOP)
     */
    protected GlobalHourReportManager() {
    }


}