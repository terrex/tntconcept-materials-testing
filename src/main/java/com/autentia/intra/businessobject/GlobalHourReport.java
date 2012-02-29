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

package com.autentia.intra.businessobject;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * Transfer object to store Activitys
 *
 * @author stajanov code generator
 */
public class GlobalHourReport implements Serializable {
    /**
     * Serial version field
     */
    private static final long serialVersionUID = -1L;


    private Iterator<User> iterator = null;


    private Project project;

    private Map<User, Float> mapaUsuarios = null;


    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    /*
     public Map<User, BigDecimal> getMapaUsuarios() {
         return mapaUsuarios;
     }

     public void setMapaUsuarios(Map<User, BigDecimal> mapaUsuarios) {
         this.mapaUsuarios = mapaUsuarios;
     }
     */

    public void setUserHours(User us, Float hours) {
        if (mapaUsuarios == null) {
            mapaUsuarios = new HashMap<User, Float>();
        }

        // Sumo las horas a las anteriores.
        float endHours = hours + getUserHours(us);
        mapaUsuarios.put(us, endHours);

    }

    public float getUserHours(User us) {
        if (mapaUsuarios == null) {
            mapaUsuarios = new HashMap<User, Float>();
        }

        if (!mapaUsuarios.containsKey(us)) {
            return 0.0f;
        } else {
            return mapaUsuarios.get(us);

        }
    }


    public String getNextHours() {
        float next = getUserHours(iterator.next());

        if (next > 0)
            return "" + next;
        else
            return "";

    }


    public float getTotal() {
        float total = 0.0f;

        if (mapaUsuarios == null)
            return total;

        Iterator<User> it = mapaUsuarios.keySet().iterator();

        while (it.hasNext()) {
            total = total + getUserHours(it.next());
        }

        return total;
    }


    public boolean equals(Object that) {
        if (that == null)
            return false;

        try {
            GlobalHourReport objGlo = (GlobalHourReport) that;
            if (objGlo.project.getId() == this.project.getId())
                return true;
            else
                return false;

        } catch (Exception e) {
            return false;
        }


    }


    public int hashCode() {
        try {
            return this.project.getId().intValue();
        } catch (Exception e) {
            return super.hashCode();
        }
    }

    public Iterator<User> getIterator() {
        return iterator;
    }

    public void setIterator(Iterator<User> iterator) {
        this.iterator = iterator;
    }


    private Integer updatedById;

    public Integer getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Integer updatedById) {
        this.updatedById = updatedById;
    }
}
