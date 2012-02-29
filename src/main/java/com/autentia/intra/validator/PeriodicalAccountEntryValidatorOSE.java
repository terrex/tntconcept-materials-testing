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


package com.autentia.intra.validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.math.BigDecimal;

/**
 * @author jclopez
 */
public class PeriodicalAccountEntryValidatorOSE implements Validator {
    private static final Log log = LogFactory.getLog(EuroValidator.class);

    /** */
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        log.info("validate - value = " + value);
        if (value != null) {
            // Check if value is a BigDecimal
            if (!(value instanceof BigDecimal)) {
                log.info("validate - value is not a BigDecimal (" + value.getClass().getName() + ")");
                throw new ValidatorException(new FacesMessage("Las cantidades monetarias deben ser de tipo BigDecimal"));
            }

            // Check if it has no more than 2 decimal digits
            BigDecimal bd = (BigDecimal) value;
            if (bd.scale() > 2) {
                log.info("validate - value has more than 2 decimals (" + value + ")");
                throw new ValidatorException(new FacesMessage("Las cantidades monetarias no pueden tener mas de dos decimales"));
            }

        }
    }
}

