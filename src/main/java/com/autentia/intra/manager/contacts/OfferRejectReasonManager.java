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

import com.autentia.intra.businessobject.OfferRejectReason;
import com.autentia.intra.dao.SortCriteria;
import com.autentia.intra.dao.hibernate.OfferRejectReasonDAO;
import com.autentia.intra.dao.search.OfferRejectReasonSearch;
import com.autentia.intra.util.SpringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;


public class OfferRejectReasonManager {

/* OfferRejectReason - generated by stajanov (do not edit/delete) */


    /**
     * Logger
     */
    private static final Log log = LogFactory.getLog(OfferRejectReasonManager.class);

    /**
     * OfferRejectReason DAO *
     */
    private OfferRejectReasonDAO offerRejectReasonDAO;

    /**
     * Get default OfferRejectReasonManager as defined in Spring's configuration file.
     *
     * @return the default singleton OfferRejectReasonManager
     */
    public static OfferRejectReasonManager getDefault() {
        return (OfferRejectReasonManager) SpringUtils.getSpringBean("managerOfferRejectReason");
    }

    /**
     * Empty constructor needed by CGLIB (Spring AOP)
     */
    protected OfferRejectReasonManager() {
    }

    /**
     * Default constructor
     *
     * @deprecated do not construct managers alone: use Spring's declared beans
     */
    public OfferRejectReasonManager(OfferRejectReasonDAO offerRejectReasonDAO) {
        this.offerRejectReasonDAO = offerRejectReasonDAO;
    }

    /**
     * List offerRejectReasons.
     *
     * @param search search filter to apply
     * @param sort   sorting criteria
     * @return the list of all offerRejectReasons sorted by requested criterion
     */
    public List<OfferRejectReason> getAllEntities(OfferRejectReasonSearch search, SortCriteria sort) {
        return offerRejectReasonDAO.search(search, sort);
    }

    /**
     * Get offerRejectReason by primary key.
     *
     * @return offerRejectReason selected by id.
     */
    public OfferRejectReason getEntityById(int id) {
        return offerRejectReasonDAO.getById(id);
    }

    /**
     * Insert offerRejectReason.
     */
    public void insertEntity(OfferRejectReason offerRejectReason) {
        offerRejectReasonDAO.insert(offerRejectReason);
    }

    /**
     * Update offerRejectReason.
     */
    public void updateEntity(OfferRejectReason offerRejectReason) {
        offerRejectReasonDAO.update(offerRejectReason);
    }

    /**
     * Delete offerRejectReason.
     */
    public void deleteEntity(OfferRejectReason offerRejectReason) {
        offerRejectReasonDAO.delete(offerRejectReason);
    }

/* OfferRejectReason - generated by stajanov (do not edit/delete) */


}