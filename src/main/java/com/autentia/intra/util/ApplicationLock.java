package com.autentia.intra.util;

import com.autentia.intra.version.Version;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class to represent application state
 *
 * @author Ivan Zaera Avellon
 */
public class ApplicationLock {
    private static final Log log = LogFactory.getLog(ApplicationLock.class);

    private static boolean locked = true;
    private static String reason = "msg.appLockNotInit";

    /**
     * No instances of this class can be created
     */
    private ApplicationLock() {
    }

    public static boolean isLocked() {
        return locked;
    }

    public static String getReason() {
        return reason;
    }

    public static void refresh() {
        try {
            Version db = Version.getDatabaseVersion();
            Version code = Version.getApplicationVersion();

            int cmp = db.compareTo(code, Version.MINOR);
            if (cmp == 0) {
                locked = false;
                reason = "msg.notLocked";
                log.info("refresh - database version correct: application unlocked");
            } else if (cmp < 0) {
                locked = true;
                reason = "msg.dbNeedsUpdate";
                log.info("refresh - database version not correct: database needs update");
            } else {
                locked = true;
                reason = "msg.appNeedsUpdate";
                log.info("refresh - database version not correct: application code needs update");
            }
        }
        catch (Exception e) {
            log.fatal("refresh - exception initializing ApplicationLock: application will not be available", e);
            locked = true;
            reason = e.getMessage() + " (" + e.getClass().getSimpleName() + ")";
        }
    }
}
