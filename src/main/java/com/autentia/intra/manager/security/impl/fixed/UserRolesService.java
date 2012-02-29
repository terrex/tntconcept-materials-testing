package com.autentia.intra.manager.security.impl.fixed;

import com.autentia.intra.businessobject.User;
import com.autentia.intra.manager.security.IUserRolesService;
import com.autentia.intra.manager.security.exception.SecException;
import org.acegisecurity.GrantedAuthority;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class retrieves user roles based on their role id. The set of roles is
 * fixed for each role but in the future it could be read from the database or
 * other places.
 *
 * @author Ivan Zaera Avellon
 */
public class UserRolesService implements IUserRolesService {
    private static final Log log = LogFactory.getLog(UserRolesService.class);

    private ISecurityConfiguration secCfg;

    public UserRolesService(ISecurityConfiguration secCfg) {
        this.secCfg = secCfg;
    }

    public GrantedAuthority[] getAuthorities(User user) throws SecException {
        int roleId = user.getRole().getId();
        GrantedAuthority[] ret = secCfg.getRolesMatrix().get(roleId);
        if (ret == null) {
            throw new UnsupportedOperationException("Role id " + roleId + " not knwon to UserRolesService");
        }
        return ret;
    }
}
