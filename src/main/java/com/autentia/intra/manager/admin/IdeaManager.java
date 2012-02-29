/* 
 *  TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L.  
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


package com.autentia.intra.manager.admin;


import com.autentia.intra.businessobject.Idea;
import com.autentia.intra.dao.SortCriteria;
import com.autentia.intra.dao.hibernate.IdeaDAO;
import com.autentia.intra.dao.search.IdeaSearch;
import com.autentia.intra.util.SpringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;


public class IdeaManager {

/* Idea - generated by stajanov (do not edit/delete) */


    /**
     * Logger
     */
    private static final Log log = LogFactory.getLog(IdeaManager.class);

    /**
     * Idea DAO *
     */
    private IdeaDAO ideaDAO;

    /**
     * Get default IdeaManager as defined in Spring's configuration file.
     *
     * @return the default singleton IdeaManager
     */
    public static IdeaManager getDefault() {
        return (IdeaManager) SpringUtils.getSpringBean("managerIdea");
    }

    /**
     * Empty constructor needed by CGLIB (Spring AOP)
     */
    protected IdeaManager() {
    }

    /**
     * Default constructor
     *
     * @deprecated do not construct managers alone: use Spring's declared beans
     */
    public IdeaManager(IdeaDAO ideaDAO) {
        this.ideaDAO = ideaDAO;
    }

    /**
     * List ideas.
     *
     * @param search search filter to apply
     * @param sort   sorting criteria
     * @return the list of all ideas sorted by requested criterion
     */
    public List<Idea> getAllEntities(IdeaSearch search, SortCriteria sort) {
        return ideaDAO.search(search, sort);
    }

    /**
     * Get idea by primary key.
     *
     * @return idea selected by id.
     */
    public Idea getEntityById(int id) {
        return ideaDAO.getById(id);
    }

    /**
     * Insert idea.
     */
    public void insertEntity(Idea idea) {
        ideaDAO.insert(idea);
    }

    /**
     * Update idea.
     */
    public void updateEntity(Idea idea) {
        ideaDAO.update(idea);
    }

    /**
     * Delete idea.
     */
    public void deleteEntity(Idea idea) {
        ideaDAO.delete(idea);
    }

/* Idea - generated by stajanov (do not edit/delete) */
}
