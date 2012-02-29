package com.autentia.intra.manager.security;

import com.autentia.intra.businessobject.User;
import com.autentia.intra.manager.security.exception.SecException;
import org.acegisecurity.GrantedAuthority;

/**
 * @author Ivan Zaera Avellon
 */
public interface IUserRolesService {
    public GrantedAuthority[] getAuthorities(User user) throws SecException;
}
