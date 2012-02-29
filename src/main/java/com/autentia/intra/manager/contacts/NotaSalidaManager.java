package com.autentia.intra.manager.contacts;

import com.autentia.intra.businessobject.NotaSalida;
import com.autentia.intra.dao.SortCriteria;
import com.autentia.intra.dao.hibernate.NotaSalidaDAO;
import com.autentia.intra.dao.search.NotaSalidaSearch;
import com.autentia.intra.util.SpringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;


public class NotaSalidaManager {
    private static final Log log = LogFactory.getLog(NotaSalidaManager.class);
    private NotaSalidaDAO notaSalidaDAO;

    /**
     * Get default NotaSalidaManager as defined in Spring's configuration file.
     *
     * @return the default singleton NotaSalidaManager
     */
    public static NotaSalidaManager getDefault() {
        return (NotaSalidaManager) SpringUtils.getSpringBean("managerNotaSalida");
    }

    /**
     * Empty constructor needed by CGLIB (Spring AOP)
     */
    protected NotaSalidaManager() {
    }

    /**
     * Default constructor
     *
     * @deprecated do not construct managers alone: use Spring's declared beans
     */
    public NotaSalidaManager(NotaSalidaDAO notaSalidaDAO) {
        this.notaSalidaDAO = notaSalidaDAO;
    }

    /**
     * List notaSalidas.
     *
     * @param search search filter to apply
     * @param sort   sorting criteria
     * @return the list of all notaSalidas sorted by requested criterion
     */
    public List<NotaSalida> getAllEntities(NotaSalidaSearch search, SortCriteria sort) {
        return notaSalidaDAO.search(search, sort);
    }

    /**
     * Get NotaSalida by primary key.
     *
     * @return notaSalida selected by id.
     */
    public NotaSalida getEntityById(int id) {
        return notaSalidaDAO.getById(id);
    }

    /**
     * Insert NotaSalida.
     */
    public void insertEntity(NotaSalida notaSalida) {
        notaSalidaDAO.insert(notaSalida);
    }

    /**
     * Update notaSalida.
     */
    public void updateEntity(NotaSalida notaSalida) {
        notaSalidaDAO.update(notaSalida);
    }

    /**
     * Delete albaran.
     */
    public void deleteEntity(NotaSalida notaSalida) {
        notaSalidaDAO.delete(notaSalida);
    }
}
