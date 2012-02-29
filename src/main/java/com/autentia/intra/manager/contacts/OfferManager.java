/* 
 * TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L.  
 *	Copyright (C) 2007 Autentia Real Bussiness Solution S.L.					   
 *	
 * 	This program is free software; you can redistribute it and/or
 * 	modify it under the terms of the GNU General Public License
 * 	as published by the Free Software Foundation; either version 2
 * 	of the License, or (at your option) any later version.
 *
 * 	This program is distributed in the hope that it will be useful,
 * 	but WITHOUT ANY WARRANTY; without even the implied warranty of
 * 	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * 	GNU General Public License for more details.
 *
 * 	You should have received a copy of the GNU General Public License
 * 	along with this program; if not, write to the Free Software
 * 	Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 * 	Autentia Real Bussiness Solution S.L.
 * 	Tlf: +34 91 675 33 06, +34 655 99 11 72
 * 	Fax: +34 91 656 65 04
 * 	info@autentia.com																
 */


package com.autentia.intra.manager.contacts;

import com.autentia.intra.businessobject.Offer;
import com.autentia.intra.dao.SortCriteria;
import com.autentia.intra.dao.hibernate.OfferDAO;
import com.autentia.intra.dao.search.OfferSearch;
import com.autentia.intra.util.SpringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;


public class OfferManager {

/* Offer - generated by stajanov (do not edit/delete) */


    /**
     * Logger
     */
    private static final Log log = LogFactory.getLog(OfferManager.class);

    /**
     * Offer DAO *
     */
    private OfferDAO offerDAO;

    /**
     * Get default OfferManager as defined in Spring's configuration file.
     *
     * @return the default singleton OfferManager
     */
    public static OfferManager getDefault() {
        return (OfferManager) SpringUtils.getSpringBean("managerOffer");
    }

    /**
     * Empty constructor needed by CGLIB (Spring AOP)
     */
    protected OfferManager() {
    }

    /**
     * Default constructor
     *
     * @deprecated do not construct managers alone: use Spring's declared beans
     */
    public OfferManager(OfferDAO offerDAO) {
        this.offerDAO = offerDAO;
    }

    /**
     * List offers.
     *
     * @param search search filter to apply
     * @param sort   sorting criteria
     * @return the list of all offers sorted by requested criterion
     */
    public List<Offer> getAllEntities(OfferSearch search, SortCriteria sort) {
        return offerDAO.search(search, sort);
    }

    /**
     * Get offer by primary key.
     *
     * @return offer selected by id.
     */
    public Offer getEntityById(int id) {
        return offerDAO.getById(id);
    }

    /**
     * Insert offer.
     */
    public void insertEntity(Offer offer) {
        offerDAO.insert(offer);
    }

    /**
     * Update offer.
     */
    public void updateEntity(Offer offer) {
        offerDAO.update(offer);
    }

    /**
     * Delete offer.
     */
    public void deleteEntity(Offer offer) {
        offerDAO.delete(offer);
    }

/* Offer - generated by stajanov (do not edit/delete) */


}