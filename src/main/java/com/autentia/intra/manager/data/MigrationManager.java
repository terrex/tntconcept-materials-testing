package com.autentia.intra.manager.data;

import com.autentia.intra.manager.data.exception.DataException;
import com.autentia.intra.util.HibernateUtil;
import com.autentia.intra.version.Version;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

/**
 * @author ivan
 */
public class MigrationManager {

    /**
     * Logger
     */
    private static final Log log = LogFactory.getLog(MigrationManager.class);

    /**
     * Package where SQL scripts live
     */
    private static final String SCRIPT_PREFIX = "com/autentia/intra/sql/mysql/";

    /**
     * Script file name
     */
    private static final String SCRIPT_SUFFIX = "/upgrade.sql";

    /**
     * Default DB migration handler
     */
    private static MigrationManager defaultManager = new MigrationManager();

    /**
     * @return
     */
    public static MigrationManager getDefault() {
        return defaultManager;
    }

    /**
     * @return the original database version
     * @throws com.autentia.intra.manager.data.exception.DataException
     *
     */
    public Version upgradeDatabase() throws DataException {
        Version ret = null;
        Version db = null;
        Version code = Version.getApplicationVersion();
        Session ses = HibernateUtil.getSession();
        Connection con = ses.connection();
        Statement stmt = null;
        LineNumberReader file = null;
        String delimiter = ";";

        try {
            db = Version.getDatabaseVersion();
            ret = db.clone();
            log.info("upgradeDatabase - >>>> STARTING MIGRATION FROM " + db + " TO " + code + " <<<<");

            // Disable auto-commit (just in case...)
            log.info("upgradeDatabase - disabling auto commit");
            con.setAutoCommit(false);

            // Create statement
            stmt = con.createStatement();

            // While necessary, upgrade database
            while (db.compareTo(code, Version.MINOR) < 0) {
                log.info("upgradeDatabase - " + db);

                // Compose script name and open it
                String script = SCRIPT_PREFIX + db.toString(Version.MINOR) + SCRIPT_SUFFIX;
                log.info("upgradeDatabase - loading script " + script);
                InputStream sqlScript = getClass().getClassLoader().getResourceAsStream(script);
                if (sqlScript == null) {
                    throw FileNotFoundException(script);
                }
                file = new LineNumberReader(new InputStreamReader(new BufferedInputStream(sqlScript), "UTF-8"));
                int _c;

                // Add batched SQL sentences to statement
                StringBuilder sentence = new StringBuilder();
                String line;
                while ((line = file.readLine()) != null) {
                    line = line.trim();
                    if (!line.startsWith("--")) {
                        // Interpret "DELIMITER" sentences
                        if (line.trim().toUpperCase(Locale.ENGLISH).startsWith("DELIMITER")) {
                            delimiter = line.trim().substring("DELIMITER".length()).trim();
                        } else {
                            // Add line to sentence
                            if (line.endsWith(delimiter)) {
                                // Remove delimiter
                                String lastLine = line.substring(0, line.length() - delimiter.length());

                                // Append line to sentence
                                sentence.append(lastLine);

                                // Execute sentence
                                log.info(" " + sentence.toString());
                                stmt.addBatch(sentence.toString());

                                // Prepare new sentence
                                sentence = new StringBuilder();
                            } else {
                                // Append line to sentence
                                sentence.append(line);

                                // Append separator for next line
                                sentence.append(" ");
                            }
                        }
                    }
                }

                // Execute batch
                log.info("upgradeDatabase - executing batch of commands");
                stmt.executeBatch();

                // Re-read database version
                Version old = db;
                db = Version.getDatabaseVersion(con);
                if (old.equals(db)) {
                    throw new DataException("Script was applied but did not upgrade database: " + script);
                }
                log.info("upgradeDatabase - database upgraded to version " + db);
            }

            // Commit transaction
            log.info("upgradeDatabase - commiting changes to database");
            con.commit();

            // Report end of migration
            log.info("upgradeDatabase - >>>> MIGRATION SUCCESSFULLY FINISHED <<<<");
        }
        catch (Exception e) {
            log.error("upgradeDatabase - >>>> MIGRATION FAILED: WILL BE ROLLED BACK <<<<", e);
            throw new DataException("Script was applied but did not upgrade database: ", e);
        }
        finally {
            if (file != null) {
                try {
                    file.close();
                }
                catch (IOException e2) {
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (SQLException e2) {
                }
            }
            if (con != null) {
                try {
                    con.rollback();
                }
                catch (SQLException e2) {
                }
            }
            ses.close();
        }

        return ret;
    }

    private Exception FileNotFoundException(String script) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
