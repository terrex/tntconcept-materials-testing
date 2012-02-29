package com.autentia.intra.manager.security;

import com.autentia.intra.dao.ITransferObject;
import org.acegisecurity.acls.objectidentity.ObjectIdentity;

import java.io.Serializable;

/**
 * @author Ivan Zaera Avellon
 */
public class TransferObjectIdentity implements ObjectIdentity {
    private ITransferObject dto;
    private Class type;

    public TransferObjectIdentity(ITransferObject dto) {
        this.dto = dto;

        // Find real ITransferObject class to avoid interferences with Hibernate proxies
        this.type = null;
        Class candidate = dto.getClass();
        while (this.type == null && !candidate.equals(Object.class)) {
            Class[] ifaces = candidate.getInterfaces();
            for (Class iface : ifaces) {
                if (iface.equals(ITransferObject.class)) {
                    this.type = candidate;
                }
            }
            candidate = candidate.getSuperclass();
        }
    }

    public Serializable getIdentifier() {
        return dto.getId();
    }

    public Class getJavaType() {
        return type;
    }

    public ITransferObject getTransferObject() {
        return dto;
    }
}
