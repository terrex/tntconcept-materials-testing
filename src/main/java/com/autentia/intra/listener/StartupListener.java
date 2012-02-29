package com.autentia.intra.listener;

import com.autentia.intra.manager.report.ReportManager;
import com.autentia.intra.util.ApplicationLock;
import com.autentia.intra.util.ConfigurationUtil;
import com.autentia.intra.util.SpringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author Ivan Zaera Avellon
 */
public class StartupListener implements ServletContextListener {
    private static final Log log = LogFactory.getLog(StartupListener.class);

    private static final String FILE_LOG4J = "/log4j.properties";

    public void contextInitialized(ServletContextEvent sce) {
        try {
            // Dump traces now as if nothing had happended before
            log.info("--------------------------------------------------------------------------------");
            log.info("contextInitialized - starting up application");
            log.info("contextInitialized - saving Spring's context for use by all application");

            // Save Spring context
            SpringUtils.configure(WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext()));

            // Get configuration directory
            String cfgDir = ConfigurationUtil.getDefault().getConfigDir();
            log.info("contextInitialized - configuration directory set to '" + cfgDir + "'");

            // Normalized cfgDir
            if (cfgDir.endsWith("/") || cfgDir.endsWith("\\")) {
                cfgDir = cfgDir.substring(0, cfgDir.length() - 1);
            }

            // Check configuration directory
            if (!new File(cfgDir).isDirectory()) {
                throw new FileNotFoundException(cfgDir);
            }

            // Configure LOG4J
            String log4jProperties = cfgDir + FILE_LOG4J;
            if (!new File(log4jProperties).exists()) {
                throw new FileNotFoundException(log4jProperties);
            }
            PropertyConfigurator.configure(log4jProperties);
            log.info("contextInitialized - configuring LOG4J system with file " + log4jProperties);

            // Try to create essential directories
            File uploadPath = new File(ConfigurationUtil.getDefault().getUploadPath());
            log.info("contextInitialized - checking upload directory " + uploadPath.getAbsolutePath());
            uploadPath.mkdirs();
            if (!uploadPath.isDirectory()) {
                throw new FileNotFoundException(uploadPath.getAbsolutePath());
            }

            // Load reports
            if (ConfigurationUtil.getDefault().getLoadingReportOnLoad() > 0) {
                log.info("contextInitialized - loading reports in report manager");
                ReportManager.getReportManager();
            } else {
                log.info("contextInitialized - loading reports on startup disabled");
            }

            // Check database
            log.info("contextInitialized - checking database version");
            ApplicationLock.refresh();
        }
        catch (FileNotFoundException e) {
            log.fatal("contextInitialized - configuration dir or file not found (" + e.getMessage() + "): application will not be started", e);
            throw new IllegalStateException("Config dir or file not found", e);
        }
        /* Moved to Spring
        catch( ConfigurationException e )
        {
          log.fatal("contextInitialized - error reading application configuration: application will not be started",e);
          throw new IllegalStateException("Error reading applocation configuration",e);
        }
        */
    }

    public void contextDestroyed(ServletContextEvent sce) {
        log.info("contextDestroyed - shutting down application");
    }
}
