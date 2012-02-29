package com.autentia.intra.dao.hibernate;

import com.autentia.intra.businessobject.Ensayo;
import com.autentia.intra.dao.DataAccException;
import com.autentia.intra.dao.IDataAccessObject;
import com.autentia.intra.dao.SearchCriteria;
import com.autentia.intra.dao.SortCriteria;
import com.autentia.intra.util.SpringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

public class EnsayoDAO extends HibernateManagerBase implements IDataAccessObject<Ensayo> {


    /**
     * Logger
     */
    private static final Log log = LogFactory.getLog(EnsayoDAO.class);

    /**
     * Get default EnsayoDAO as defined in Spring's configuration file.
     *
     * @return the default singleton EnsayoDAO
     */
    public static EnsayoDAO getDefault() {
        return (EnsayoDAO) SpringUtils.getSpringBean("daoEnsayo");
    }

    /**
     * Constructor
     *
     * @deprecated do not construct DAOs alone: use Spring's declared beans
     */
    public EnsayoDAO() {
        super(false);
    }

    /**
     * Retrieve a Ensayo object from database given its id
     *
     * @param id primary key of Ensayo object
     * @return the Ensayo object identified by the id
     * @throws DataAccException on error
     */
    public Ensayo getById(int id) throws DataAccException {
        return super.getByPk(Ensayo.class, id);
    }

    /**
     * Get all Ensayo objects from database sorted by the given criteria
     *
     * @param crit the sorting criteria
     * @return a list with all existing Ensayo objects
     * @throws DataAccException on error
     */
    public List<Ensayo> search(SortCriteria crit) throws DataAccException {
        return super.list(Ensayo.class, crit);
    }

    /**
     * Get specified Ensayo objects from database sorted by the given criteria
     *
     * @param search search criteria
     * @param sort   the sorting criteria
     * @return a list with Ensayo objects matching the search criteria
     * @throws DataAccException on error
     */
    public List<Ensayo> search(SearchCriteria search, SortCriteria sort) throws DataAccException {
        return super.search(Ensayo.class, search, sort);
    }

    /**
     * Insert a new Ensayo object in database
     *
     * @param dao the Ensayo object to insert
     * @throws DataAccException on error
     */
    public void insert(Ensayo dao) throws DataAccException {
        super.insert(dao);
    }

    /**
     * Update an existing Ensayo object in database
     *
     * @param dao the Ensayo object to update
     * @throws DataAccException on error
     */
    public void update(Ensayo dao) throws DataAccException {
        super.update(dao, dao.getId());
    }

    /**
     * Delete an existing Ensayo object in database
     *
     * @param dao the Ensayo object to update
     * @throws DataAccException on error
     */
    public void delete(Ensayo dao) throws DataAccException {
        super.delete(dao, dao.getId());
    }

}
