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

package com.autentia.intra.converter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

/**
 * JSF converter to change minutes into dates
 *
 * @author ivan
 */
public class MinuteToHourConverter implements Converter {
    private static Log log = LogFactory.getLog(MinuteToHourConverter.class);

    public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
        log.debug("getAsObject - value=" + value);

        if (value == null) {
            return null;
        } else {
            try {
                double val = Double.parseDouble(value.replaceAll(",", "."));
                return new Integer((int) (val * 60));
            } catch (NumberFormatException e) {
                throw new ConverterException("Error converting minutes: " + value, e);
            }
        }
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {
        if (value instanceof Integer) {
            int val = ((Integer) value).intValue();
            return Double.toString(val / 60.0);
        } else {
            return (String) value;
        }
    }
}
