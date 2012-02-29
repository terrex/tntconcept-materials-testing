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

import com.autentia.intra.businessobject.Interaction;
import com.autentia.intra.dao.SortCriteria;
import com.autentia.intra.dao.hibernate.InteractionDAO;
import com.autentia.intra.dao.search.InteractionSearch;
import com.autentia.intra.util.SpringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;


public class InteractionManager {

/* Interaction - generated by stajanov (do not edit/delete) */


    /**
     * Logger
     */
    private static final Log log = LogFactory.getLog(InteractionManager.class);

    /**
     * Interaction DAO *
     */
    private InteractionDAO interactionDAO;

    /**
     * Get default InteractionManager as defined in Spring's configuration file.
     *
     * @return the default singleton InteractionManager
     */
    public static InteractionManager getDefault() {
        return (InteractionManager) SpringUtils.getSpringBean("managerInteraction");
    }

    /**
     * Empty constructor needed by CGLIB (Spring AOP)
     */
    protected InteractionManager() {
    }

    /**
     * Default constructor
     *
     * @deprecated do not construct managers alone: use Spring's declared beans
     */
    public InteractionManager(InteractionDAO interactionDAO) {
        this.interactionDAO = interactionDAO;
    }

    /**
     * List interactions.
     *
     * @param search search filter to apply
     * @param sort   sorting criteria
     * @return the list of all interactions sorted by requested criterion
     */
    public List<Interaction> getAllEntities(InteractionSearch search, SortCriteria sort) {
        return interactionDAO.search(search, sort);
    }

    /**
     * Get interaction by primary key.
     *
     * @return interaction selected by id.
     */
    public Interaction getEntityById(int id) {
        return interactionDAO.getById(id);
    }

    /**
     * Insert interaction.
     */
    public void insertEntity(Interaction interaction) {
        interactionDAO.insert(interaction);
    }

    /**
     * Update interaction.
     */
    public void updateEntity(Interaction interaction) {
        interactionDAO.update(interaction);
    }

    /**
     * Delete interaction.
     */
    public void deleteEntity(Interaction interaction) {
        interactionDAO.delete(interaction);
    }
/* Interaction - generated by stajanov (do not edit/delete) */


}
