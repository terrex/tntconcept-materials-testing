package com.autentia.intra.manager.security;

import com.autentia.intra.businessobject.User;
import com.autentia.intra.dao.DataAccException;
import com.autentia.intra.dao.DataIntegrityException;
import com.autentia.intra.dao.DataNotFoundException;
import com.autentia.intra.dao.hibernate.UserDAO;
import com.autentia.intra.manager.security.exception.SecException;
import com.autentia.intra.util.ConfigurationUtil;
import com.autentia.intra.util.FacesUtils;
import com.autentia.intra.util.HibernateUtil;
import com.autentia.intra.util.SpringUtils;
import com.autentia.intra.version.Version;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author ivan
 */
public class AuthenticationManager implements UserDetailsService {
    private static final Log log = LogFactory.getLog(AuthenticationManager.class);

    /**
     * Algorithm used to store passwords in database
     */
    public static final String HASH_ALGORITHM = "SHA-1";

    /**
     * Administrator role id
     */
    private int administratorRoleId;

    /**
     * User DAO
     */
    private UserDAO userDAO = new UserDAO();

    /**
     * IUserRolesService implementation
     */
    private IUserRolesService userRolesService;

    /**
     * @deprecated the preferred way to get the default manager is using Spring injection
     */
    public static AuthenticationManager getDefault() {
        return (AuthenticationManager) SpringUtils.getSpringBean("userDetailsService");
    }

    /**
     * Constructor
     *
     * @param cfg              configuration object
     * @param userRolesService delegate for getting user's roles
     */
    public AuthenticationManager(ConfigurationUtil cfg, IUserRolesService userRolesService) {
        this.userRolesService = userRolesService;
        this.administratorRoleId = cfg.getRoleAdminId();
    }

    /**
     * Get currently logged-in principal
     *
     * @return the active Principal
     */
    public Principal getCurrentPrincipal() {
        Object ret = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (ret instanceof Principal) ? (Principal) ret : null;
    }

    /**
     * @param user
     * @param passwd
     * @return
     * @throws com.autentia.intra.util.HashEngineException
     *
     */
    public boolean checkPassword(User user, String passwd) {
        return DigestUtils.shaHex(passwd).equalsIgnoreCase(user.getPassword());
    }

    /**
     * @param user
     * @param passwd
     * @throws com.autentia.intra.util.HashEngineException
     *
     */
    public void changePassword(User user, String passwd) {
        user.setPassword(DigestUtils.shaHex(passwd));
    }

    /**
     * Load a User for ACEGI given its user name
     *
     * @param username user name
     * @return the user object description as specified by ACEGI
     * @throws org.acegisecurity.userdetails.UsernameNotFoundException
     *
     * @throws org.springframework.dao.DataAccessException
     *
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        try {
            Version db = Version.getDatabaseVersion();
            Version code = Version.getApplicationVersion();

            if (db.compareTo(code, Version.MINOR) == 0) {
                log.info("loadUserByUsername - getting user " + username + " using Hibernate");
                User user = userDAO.searchByLogin(username);
                GrantedAuthority[] auths = userRolesService.getAuthorities(user);
                if (log.isDebugEnabled()) {
                    StringBuilder sb = new StringBuilder();
                    for (GrantedAuthority auth : auths) {
                        sb.append(auth);
                        sb.append(" ");
                    }
                    log.debug("loadUserByUsername - user roles: " + sb);
                }
                return new Principal(user, auths);
            } else {
                log.info("loadUserByUsername - getting user " + username + " using JDBC");
                return jdbcSearchByLogin(username);
            }
        }
        catch (SecException e) {
            log.warn("loadUserByUsername - exception", e);
            throw new DataRetrievalFailureException("Error getting roles for user: " + username, e);
        }
        catch (DataIntegrityException e) {
            log.warn("loadUserByUsername - exception", e);
            throw new DataIntegrityViolationException("Inconsistent user name: " + username, e);
        }
        catch (DataNotFoundException e) {
            log.warn("loadUserByUsername - exception", e);
            throw new UsernameNotFoundException("User not found: " + username, e);
        }
        catch (DataAccException e) {
            log.warn("loadUserByUsername - exception", e);
            throw new DataRetrievalFailureException("Error getting user: " + username, e);
        }
        catch (SQLException e) {
            log.warn("loadUserByUsername - exception", e);
            throw new DataRetrievalFailureException("Error getting user: " + username, e);
        }
    }

    /**
     * Reset user password.
     *
     * @param user the user
     * @return the new password
     */
    public String resetPassword(User user) {
        String changedPassword = generateRandomPassword();
        changePassword(user, changedPassword);
        return changedPassword;
    }

    /**
     * Generate a new random password
     *
     * @return a new random password
     */
    private String generateRandomPassword() {
        StringBuilder ret = new StringBuilder();

        // Get lists of random words. We could cache these, but this method is rarely called and caching would
        // depend on user locale, so we prefer to waste CPU better than memory.
        String[] rnd0 = FacesUtils.formatMessage("AuthenticationManager.randomWords0").split(",");
        String[] rnd1 = FacesUtils.formatMessage("AuthenticationManager.randomWords1").split(",");
        String[] rnd2 = FacesUtils.formatMessage("AuthenticationManager.randomWords2").split(",");
        String[] rnd3 = FacesUtils.formatMessage("AuthenticationManager.randomWords3").split(",");
        String[] rnd4 = FacesUtils.formatMessage("AuthenticationManager.randomWords4").split(",");

        // Get a true random number generator
        SecureRandom rnd;
        try {
            rnd = SecureRandom.getInstance("SHA1PRNG");
        }
        catch (NoSuchAlgorithmException ex) {
            rnd = new SecureRandom();
        }

        // Generate random numbers
        int i0 = rnd.nextInt(rnd0.length);
        int i1 = rnd.nextInt(rnd1.length);
        int i2 = rnd.nextInt(rnd2.length);
        int i3 = rnd.nextInt(rnd3.length);
        int i4 = rnd.nextInt(rnd4.length);

        // Compose password
        ret.append(rnd0[i0]);
        ret.append(rnd1[i1]);
        ret.append(rnd2[i2]);
        ret.append(rnd3[i3]);
        ret.append(rnd4[i4]);

        return ret.toString();
    }

    /**
     * <p>
     * Get user details given her login. This method accesses tables using JDBC.
     * Thus, is should only be used if we need to log into the single-user console
     * to migrate tables (in that case, we cannot rely on Hibernate to get the
     * users until we have really finished migrating tables and data).
     * </p>
     *
     * @param login user login
     * @return a MiniUser object (minimal representation of a user)
     * @throws com.autentia.intra.dao.DataNotFoundException
     *          if user does not exist
     * @throws com.autentia.intra.dao.DataIntegrityException
     *          if user is duplicated in DB
     * @throws com.autentia.intra.dao.DataAccException
     *          if something fails accessing DB
     */
    private Principal jdbcSearchByLogin(String login) throws DataAccException, DataNotFoundException, DataIntegrityException {
        Session ses = HibernateUtil.getSession();
        Connection con = ses.connection();
        Principal ret;

        try {
            PreparedStatement stmt = null;
            ResultSet rs = null;

            try {
                stmt = con.prepareStatement(
                        "select u.id,u.login,u.password,u.active,u.name,r.id roleId " +
                                "from User u,Role r " +
                                "where u.roleId=r.id " +
                                "and u.login=?");
                stmt.setString(1, login);
                rs = stmt.executeQuery();

                if (rs.next()) {
                    int roleId = rs.getInt("roleId");

                    ret = new Principal(
                            rs.getInt("id"),
                            0, // In console mode we won't need departmentId so we leave it blank. We can't get
                            // it from the database because in versions prior to 0.4 the field didn't exist
                            rs.getString("login"),
                            rs.getString("password"),
                            rs.getBoolean("active"),
                            rs.getString("name"),
                            roleId,
                            (roleId == administratorRoleId)
                                    ? new GrantedAuthority[]{Permission.Action_Console}
                                    : new GrantedAuthority[]{}
                    );

                    if (rs.next()) {
                        throw new DataIntegrityException(User.class, "Duplicated user login: " + login);
                    }
                } else {
                    throw new DataNotFoundException(User.class, login);
                }
            }
            catch (SQLException e) {
                throw new DataAccException("Error loading user: " + login, e);
            }
            finally {
                if (rs != null) try {
                    rs.close();
                } catch (SQLException e) {
                }
                ;
                if (stmt != null) try {
                    stmt.close();
                } catch (SQLException e) {
                }
                ;
            }
        }
        finally {
            ses.close();
        }

        return ret;
    }
}
