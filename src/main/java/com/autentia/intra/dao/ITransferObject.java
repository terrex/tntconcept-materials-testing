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

import java.util.Date;

/**
 * Interface implemented by all DAO transfer objects.
 *
 * @author ivan
 */
public interface ITransferObject {

    /**
     * Get transfer object's id
     *
     * @return transfer object's id
     */
    public Integer getId();

    /**
     * Get transfer object's owner
     */
    public Integer getOwnerId();

    /**
     * Get transfer object's department
     */
    public Integer getDepartmentId();

    /**
     * set transfer object's owner
     */
    public void setOwnerId(Integer ownerId);

    /**
     * set transfer object's department
     */
    public void setDepartmentId(Integer departmentId);


    /**
     * Get transfer object's Creation Date
     *
     * @return transfer object's Creation Date
     */
    public Date getInsertDate();

    /**
     * Get transfer object's Modification Date
     */
    public Date getUpdateDate();

    /**
     * set transfer object's Creation
     */
    public void setInsertDate(Date insertDate);

    /**
     * set transfer object's Modification
     */
    public void setUpdateDate(Date updateDate);

    /**
     * Get transfer object's owner
     */
    public Integer getUpdatedById();

    /**
     * Get transfer object's owner
     */
    public void setUpdatedById(Integer updatedById);

}
