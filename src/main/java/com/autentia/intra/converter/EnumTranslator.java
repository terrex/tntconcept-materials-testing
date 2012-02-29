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

import com.autentia.intra.util.FacesUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

/**
 * Translator (I18N) for enumerations. Converts an enumerated value to its
 * internationalized string. This class is a translator, not a true converter.
 * It can only be used to output enums as I18N values, but not to convert input
 * values to enums.
 *
 * @author ivan
 */
public class EnumTranslator implements Converter {
    /**
     * Logger
     */
    private static Log log = LogFactory.getLog(EnumTranslator.class);

    /** */
    public Object getAsObject(FacesContext context, UIComponent component, String value)
            throws ConverterException {
        throw new ConverterException("EnumTranslator cannot be used as an I/O converter: " +
                "only conversion from Enum to String is supported, and not the other way");
    }

    /** */
    public String getAsString(FacesContext context, UIComponent component, Object value)
            throws ConverterException {
        if (value == null) {
            return null;
        } else {
            String className = value.getClass().getSimpleName();
            return FacesUtils.formatMessage(className + "." + value);
        }
    }
}
