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

package com.autentia.intra.dao;

import java.util.List;

/**
 * DAO minimum interface: all DAOs must implement it.
 *
 * @author ivan
 * @param <T> type of transfer object the DAO manages
 */
public interface IDataAccessObject<T extends ITransferObject> {
    /**
     * Get DAO by primary key
     *
     * @param id primary key to look for
     * @return the requested DAO
     * @throws DataAccException
     */
    public T getById(int id) throws DataAccException;

    /**
     * List all DAOs
     *
     * @param crit sorting criteria (can be null for no sorting criteria)
     * @return the list of all DAOs
     * @throws DataAccException
     */
    public List<T> search(SortCriteria crit) throws DataAccException;

    /**
     * Search DAOs
     *
     * @param search searching criteria which depends on the manager being used (cannot be null)
     * @param sort   sorting criteria (can be null for no sorting criteria)
     * @return the list of all DAOs matching the criteria sorted by sorting criteria
     * @throws DataAccException
     */
    public List<T> search(SearchCriteria search, SortCriteria sort) throws DataAccException;

    /**
     * Insert new dao
     *
     * @param dao the transfer object to create
     * @throws DataAccException
     */
    public void insert(T to) throws DataAccException;

    /**
     * Update existing transfer object. Transfer object is recognyzed by its primary key field, which
     * should be filled-out before calling this method.
     *
     * @param to the transfer object to update
     * @throws DataAccException
     */
    public void update(T to) throws DataAccException;

    /**
     * Delete existing transfer object. Transfer object is recognyzed by its primary key field, which
     * should be filled-out before calling this method.
     *
     * @param dao the DAO to delete
     * @throws DataAccException
     */
    public void delete(T to) throws DataAccException;
}
