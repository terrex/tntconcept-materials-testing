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

import org.apache.commons.lang.StringUtils;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Conversor de n�meros de tel�fono
 *
 * @author AUTENTIA
 */

public class DateConverter implements Converter {
    /**
     * Devuelve la representaci�n Date de JAVA de un valor
     */
    public Object getAsObject(FacesContext context, UIComponent component, String value)
            throws ConverterException {
        DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = null;

        if (!StringUtils.isEmpty(value)) {
            try {
                fecha = fmt.parse(value);
            }
            catch (Exception pe) {
                String mensaje = "El valor no se corresponde con una fecha válida";
                throw new ConverterException(new FacesMessage(mensaje));
            }
        }
        return fecha;
    }

    /**
     * Devuelve la representacion cadena de una fecha
     */
    public String getAsString(FacesContext context, UIComponent component, Object value)
            throws ConverterException {
        DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        String valor = null;
        if (value == null) {
            valor = "";
        } else {
            valor = fmt.format(value);
        }
        return valor;
    }

}
