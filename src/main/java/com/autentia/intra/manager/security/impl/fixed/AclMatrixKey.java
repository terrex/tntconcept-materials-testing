package com.autentia.intra.manager.security.impl.fixed;

import com.autentia.intra.dao.ITransferObject;

/**
 * @author Ivan Zaera Avellon
 */
class AclMatrixKey {
    private Class<? extends ITransferObject> type;
    private int roleId;

    public AclMatrixKey(Class<? extends ITransferObject> type, int roleId) {
        this.type = type;
        this.roleId = roleId;
    }

    public Class<? extends ITransferObject> getType() {
        return type;
    }

    public int getRoleId() {
        return roleId;
    }

    public boolean equals(Object obj) {
        if (obj instanceof AclMatrixKey) {
            AclMatrixKey that = (AclMatrixKey) obj;
            return this.roleId == that.roleId && this.type.equals(that.type);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return type.hashCode() + roleId;
    }

    public String toString() {
        return "AclMatrixKey(" + type.getSimpleName() + "," + roleId + ")";
    }
}
