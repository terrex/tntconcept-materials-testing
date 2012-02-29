package com.autentia.intra.manager.security.impl.fixed;

import org.acegisecurity.GrantedAuthority;

import java.util.Map;

/**
 * @author Ivan Zaera Avellon
 */
public interface ISecurityConfiguration {
    /**
     * Get matrix of ACL_WRITE permissions
     */
    public Map<AclMatrixKey, AclMatrixValue> getWriteMatrix();

    /**
     * Get matrix of ACL_READ permissions
     */
    public Map<AclMatrixKey, AclMatrixValue> getReadMatrix();

    /**
     * Get matrix of ACL_DELETE permissions
     */
    public Map<AclMatrixKey, AclMatrixValue> getDeleteMatrix();

    /**
     * Get matrix of role-based permissions
     */
    public Map<Integer, GrantedAuthority[]> getRolesMatrix();
}

