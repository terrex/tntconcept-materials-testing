package com.autentia.intra.manager.security.impl.fixed;

/**
 * Possible values for ACL matrices.
 *
 * @author Ivan Zaera Avellon
 */
public enum AclMatrixValue {
    /**
     * Permission for all objects of specified class
     */
    ALL,
    /**
     * Permission for all objects of specified class in user's area
     */
    AREA,
    /**
     * Permission for all objects of specified class owned by the user
     */
    OWN,
    /**
     * Deny permission for all objects of specified class
     */
    DENY
}
