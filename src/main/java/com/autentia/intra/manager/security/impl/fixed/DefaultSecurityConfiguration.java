package com.autentia.intra.manager.security.impl.fixed;

import com.autentia.intra.dao.ITransferObject;
import com.autentia.intra.manager.security.Permission;
import com.autentia.intra.manager.security.exception.SecConfigException;
import com.autentia.intra.util.ConfigurationUtil;
import com.autentia.intra.xml.DOMUtils;
import org.acegisecurity.GrantedAuthority;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.lang.reflect.Field;
import java.util.*;

/**
 * This class holds all security configuration for the "default fixed security"
 * of TNTConcept.
 *
 * @author Ivan Zaera Avellon
 */
public final class DefaultSecurityConfiguration implements ISecurityConfiguration {

    private Map<Integer, GrantedAuthority[]> rolePermissions;
    private Map<AclMatrixKey, AclMatrixValue> writeMatrix;
    private Map<AclMatrixKey, AclMatrixValue> deleteMatrix;
    private Map<AclMatrixKey, AclMatrixValue> readMatrix;


    private int roleAdminId;
    private int roleSupervisorId;
    private int roleStaffId;
    private int roleUserId;
    private int roleClientId;

    private static final Log logger = LogFactory.getLog(DefaultSecurityConfiguration.class);


    /**
     * Constructor
     *
     * @param cfg
     */
    public DefaultSecurityConfiguration(ConfigurationUtil cfg) {
        try {

            String path2File = cfg.getConfigDir() + cfg.getSecurityMatrix();
            File confFile = new File(path2File);
            Map<GrantedAuthority, boolean[]> permissionsMap = null;

            if (!confFile.exists()) {
                throw new SecConfigException("Fichero de configuración de seguridad no encontrado:" + path2File);
            }

            logger.info("Loading AGECI configuration from " + confFile);

            this.roleAdminId = cfg.getRoleAdminId();
            this.roleSupervisorId = cfg.getRoleSupervisorId();
            this.roleStaffId = cfg.getRoleStaffId();
            this.roleUserId = cfg.getRoleUserId();
            this.roleClientId = cfg.getRoleClientId();

            this.readMatrix = new HashMap<AclMatrixKey, AclMatrixValue>();
            this.writeMatrix = new HashMap<AclMatrixKey, AclMatrixValue>();
            this.deleteMatrix = new HashMap<AclMatrixKey, AclMatrixValue>();
            this.rolePermissions = new HashMap<Integer, GrantedAuthority[]>();
            permissionsMap = new HashMap<GrantedAuthority, boolean[]>();

            DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder domBuilder = domFactory.newDocumentBuilder();
            org.w3c.dom.Document document = domBuilder.parse(confFile);

            this.loadEntities(document, permissionsMap);
            this.loadActions(document, permissionsMap);

            this.readMatrix = Collections.unmodifiableMap(this.readMatrix);
            this.writeMatrix = Collections.unmodifiableMap(this.writeMatrix);
            this.deleteMatrix = Collections.unmodifiableMap(this.deleteMatrix);

            this.rolePermissions.put(roleAdminId, loadPermsArray(permissionsMap, 0));
            this.rolePermissions.put(roleSupervisorId, loadPermsArray(permissionsMap, 1));
            this.rolePermissions.put(roleStaffId, loadPermsArray(permissionsMap, 2));
            this.rolePermissions.put(roleUserId, loadPermsArray(permissionsMap, 3));
            this.rolePermissions.put(roleClientId, loadPermsArray(permissionsMap, 4));


            logger.info("AGECI configuration OK readed" + confFile);
        } catch (Exception ex) {
            logger.fatal(ex);    // SAXException, ParserConfigurationException, IOException, SecConfigException
        }
    }

    /**
     * Inicializa las matrizes de privilegios de operaciones de Lectura, Escritura y Borrado de cada una de las clases.
     *
     * @param permissionsMap
     */
    private void loadEntities(org.w3c.dom.Document document, Map<GrantedAuthority, boolean[]> permissionsMap) throws SecConfigException {
        NodeList entities = null;
        Node entity = null;
        String classname = null;
        NodeList operations = null;
        Node operation = null;
        String operationName = null;
        AclMatrixValue admin = null;
        AclMatrixValue super1 = null;
        AclMatrixValue staff = null;
        AclMatrixValue user = null;
        AclMatrixValue cli = null;
        Map<AclMatrixKey, AclMatrixValue> targetMatrix = null;

        logger.debug("Reading 'entities'");

        try {
            // Cargamos las entidades
            entities = document.getElementsByTagName("entity");
            for (int i = 0, iCount = entities.getLength(); i < iCount; i++) {
                entity = entities.item(i);
                if ("entity".equals(entity.getNodeName())) {
                    classname = DOMUtils.getAttribute(entity, "name");
                    logger.debug("Entity readed: " + classname);

                    classname = "com.autentia.intra.businessobject." + classname;
                    operations = entity.getChildNodes();
                    for (int j = 0, jCount = operations.getLength(); j < jCount; j++) {
                        operation = operations.item(j);

                        // <operation name="delete" admin="deny" super="deny" staff="deny" user="deny" cli="deny"/>
                        if ("operation".equals(operation.getNodeName())) {
                            operationName = DOMUtils.getAttribute(operation, "name");


                            logger.debug("Operation readed: " + operationName);

                            targetMatrix = null;
                            if ("read".equals(operationName)) {
                                targetMatrix = readMatrix;
                            } else if ("write".equals(operationName)) {
                                targetMatrix = writeMatrix;
                            } else if ("delete".equals(operationName)) {
                                targetMatrix = deleteMatrix;
                            } else if ("list".equals(operationName) || "create".equals(operationName) || "menu".equals(operationName)) {
                                // Nada
                            } else {
                                logger.fatal("Invalid Operation: " + operationName);
                                throw new SecConfigException("Invalid Operation: " + operationName);
                            }

                            if (targetMatrix != null) {
                                // Es una operacion del tipo: {delete, write, read}
                                admin = getVisibility(operation, "admin");
                                super1 = getVisibility(operation, "super");
                                staff = getVisibility(operation, "staff");
                                user = getVisibility(operation, "user");
                                cli = getVisibility(operation, "cli");

                                Class _class = Class.forName(classname);
                                Class<? extends ITransferObject> t = _class;
                                putInMatrix(targetMatrix, t, admin, super1, staff, user, cli);
                            } else {
                                // Es una operacion del tipo: {list, create, menu}
                                Class parameter = Class.forName(classname);
                                boolean[] permision = this.getPermisionArray(operation);
                                GrantedAuthority methodResult = null;

                                if ("list".equals(operationName)) {
                                    methodResult = Permission.Entity_List(parameter);
                                } else if ("create".equals(operationName)) {
                                    methodResult = Permission.Entity_Create(parameter);
                                } else if ("menu".equals(operationName)) {
                                    methodResult = Permission.Entity_Menu(parameter);
                                }

                                if (methodResult == null) {
                                    throw new NullPointerException("Permission return null");
                                }
                                permissionsMap.put(methodResult, permision);
                            }
                        }
                    }
                }
            }
        } catch (SecConfigException ex) {
            throw ex;
        } catch (Exception ex) {
            logger.fatal(ex);
            throw new SecConfigException("Invalid entity detected: " + classname, ex);
        }
    }


    public Map<AclMatrixKey, AclMatrixValue> getWriteMatrix() {
        return writeMatrix;
    }

    public Map<AclMatrixKey, AclMatrixValue> getReadMatrix() {
        return readMatrix;
    }

    public Map<AclMatrixKey, AclMatrixValue> getDeleteMatrix() {
        return deleteMatrix;
    }

    public Map<Integer, GrantedAuthority[]> getRolesMatrix() {
        return rolePermissions;
    }

    /**
     * @param document
     * @param permissionsMap
     */
    private void loadActions(org.w3c.dom.Document document, Map<GrantedAuthority, boolean[]> permissionsMap) throws SecConfigException {
        Node action = null;
        String name = null;
        boolean[] permision = null;
        Field _field = null;
        GrantedAuthority ga = null;

        try {
            NodeList actions = document.getElementsByTagName("action");
            for (int i = 0, iCount = actions.getLength(); i < iCount; i++) {
                action = actions.item(i);
                name = DOMUtils.getAttribute(action, "name");
                _field = Permission.class.getField(name);

                permision = getPermisionArray(action);
                ga = (GrantedAuthority) _field.get(null);
                permissionsMap.put(ga, permision);
            } // end for
        } catch (Exception ex) {
            logger.fatal(ex);

            throw new SecConfigException(null, ex);
        }
    }


    /**
     * @param attrs
     * @return
     */
    private boolean[] getPermisionArray(Node node) {
        boolean[] privileges = new boolean[5];
        String[] profiles = {"admin", "super", "staff", "user", "cli"};
        String pvalue;

        for (int i = 0; i < profiles.length; i++) {
            pvalue = DOMUtils.getAttribute(node, profiles[i]);
            privileges[i] = "true".equals(pvalue);
        }
        return privileges;
    }

    /**
     * Add levels to a matrix for a specified matrix entry
     *
     * @param matrix          the matrix to add to
     * @param type            type of transfer object
     * @param adminLevel      level to assign to administrator users
     * @param supervisorLevel level to assign to supervisor users
     * @param staffLevel      level to assign to staff users
     * @param userLevel       level to assign to normal users
     * @param clientLevel     level to assign to client users
     */
    private void putInMatrix(Map<AclMatrixKey, AclMatrixValue> matrix, Class<? extends ITransferObject> type,
                             AclMatrixValue adminLevel, AclMatrixValue supervisorLevel, AclMatrixValue staffLevel,
                             AclMatrixValue userLevel, AclMatrixValue clientLevel) {

        matrix.put(new AclMatrixKey(type, roleAdminId), adminLevel);
        matrix.put(new AclMatrixKey(type, roleSupervisorId), supervisorLevel);
        matrix.put(new AclMatrixKey(type, roleStaffId), staffLevel);
        matrix.put(new AclMatrixKey(type, roleUserId), userLevel);
        matrix.put(new AclMatrixKey(type, roleClientId), clientLevel);
    }

    private GrantedAuthority[] loadPermsArray(Map<GrantedAuthority, boolean[]> permissionsMap, int index) {
        List<GrantedAuthority> grant = new ArrayList<GrantedAuthority>();
        Set<GrantedAuthority> perms = permissionsMap.keySet();
        for (GrantedAuthority perm : perms) {
            if (permissionsMap.get(perm)[index]) {
                grant.add(perm);
            }
        }
        return grant.toArray(new GrantedAuthority[]{});
    }


    /**
     * @param node Nodo 'operation'
     * @param name Identificador de la propiedad
     * @return Realiza la conversión entre String a uno de los valores de la enumeración AclMatrixValue
     */
    private AclMatrixValue getVisibility(Node operation, String name) {
        String value = null;
        AclMatrixValue aclValue = null;

        value = DOMUtils.getAttribute(operation, name);
        if ("all".equals(value)) {
            aclValue = AclMatrixValue.ALL;
        } else if ("area".equals(value)) {
            aclValue = AclMatrixValue.AREA;
        } else if ("own".equals(value)) {
            aclValue = AclMatrixValue.OWN;
        } else if ("deny".equals(value)) {
            aclValue = AclMatrixValue.DENY;
        } else {
            logger.warn("Invalid visibility data. name=" + name + ", value=" + value + ". Setting 'deny' value");
            aclValue = AclMatrixValue.DENY;
        }

        return aclValue;
    }
}
