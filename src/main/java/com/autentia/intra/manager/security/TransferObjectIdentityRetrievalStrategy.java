package com.autentia.intra.manager.security;

import com.autentia.intra.dao.ITransferObject;
import org.acegisecurity.acls.objectidentity.ObjectIdentity;
import org.acegisecurity.acls.objectidentity.ObjectIdentityRetrievalStrategy;

/**
 * @author Ivan Zaera Avellon
 */
public class TransferObjectIdentityRetrievalStrategy implements ObjectIdentityRetrievalStrategy {
    public ObjectIdentity getObjectIdentity(Object domainObject) {
        if (domainObject instanceof ITransferObject) {
            return new TransferObjectIdentity((ITransferObject) domainObject);
        } else {
            throw new IllegalArgumentException("TransferObjectIdentityRetrievalStrategy only supports ITransferObjects");
        }
    }

}
