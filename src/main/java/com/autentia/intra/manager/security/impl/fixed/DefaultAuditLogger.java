package com.autentia.intra.manager.security.impl.fixed;

import org.acegisecurity.acls.AccessControlEntry;
import org.acegisecurity.acls.domain.AuditLogger;
import org.acegisecurity.acls.objectidentity.ObjectIdentity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class implements TNTConcept's default audit logger. Current
 * implementation just dumps auditing to LOG4J console.
 * added using this class.
 *
 * @author Ivan Zaera Avellon
 */
public class DefaultAuditLogger implements AuditLogger {
    private static final Log log = LogFactory.getLog(DefaultAuditLogger.class);

    public void logIfNeeded(boolean granted, AccessControlEntry ace) {
        ObjectIdentity id = ace.getAcl().getObjectIdentity();
        log.info((granted ? "Grant [" : "Deny [") + ace.getPermission().getPattern() + "]" +
                " on " + id.getJavaType().getSimpleName() + "[" + id.getIdentifier() + "]" +
                " to " + ace.getSid());
    }
}
