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

package com.autentia.intra.util;

import com.autentia.intra.dao.ITransferObject;
import org.apache.commons.beanutils.BeanUtilsBean;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;

public class BeanUtils {
    /**
     * Devuelve la pila de una excepcion
     *
     * @param ex la excepcion
     * @return la pila de la excepcion
     */
    public static String getErrorDesc(Throwable ex) {
        StringBuffer sb = new StringBuffer();
        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));
        sb.append(sw.toString());
        return sb.toString();
    }

    /**
     * Copies one ITransferObject into another (excluding the id property)
     *
     * @param copyAccountEntry
     * @param periodicalAccountEntry
     */
    public static void copyTransferObject(ITransferObject source, ITransferObject dest) {
        try {
            BeanUtilsBean.getInstance().copyProperties(dest, source);
        }
        catch (IllegalAccessException ex) {
            throw new RuntimeException("Error cloning ITransferObject", ex);
        }
        catch (InvocationTargetException ex) {
            throw new RuntimeException("Error cloning ITransferObject", ex);
        }
    }
}
