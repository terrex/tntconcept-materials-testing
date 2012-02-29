package com.autentia.intra.bean.single;

import com.autentia.intra.bean.BaseBean;
import com.autentia.intra.manager.data.MigrationManager;
import com.autentia.intra.manager.data.exception.DataException;
import com.autentia.intra.util.ApplicationLock;
import com.autentia.intra.util.FacesUtils;
import com.autentia.intra.version.Version;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author ivan
 */
public class ConsoleBean extends BaseBean {
    private static final Log log = LogFactory.getLog(ConsoleBean.class);

    public String getLockReason() {
        return ApplicationLock.getReason();
    }

    public String migrateDB() {
        MigrationManager mig = MigrationManager.getDefault();

        try {
            // Migrate database
            Version oldVersion = mig.upgradeDatabase();

            // Tell user
            FacesUtils.addInfoMessage(null, "msg.migrationSuccess", oldVersion, Version.getApplicationVersion());

            // Refresh lock filter state
            ApplicationLock.refresh();
        }
        catch (DataException e) {
            FacesUtils.addErrorMessage(null, "error.migration");
            log.error("migrateDB - exception", e);
        }

        return null;
    }

}
