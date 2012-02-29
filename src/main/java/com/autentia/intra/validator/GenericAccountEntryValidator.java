package com.autentia.intra.validator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class GenericAccountEntryValidator implements Validator {

    public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
            throws ValidatorException {
        ValidatorFactory.getFactory().getAccountEntryValidator().validate(arg0, arg1, arg2);

    }

}
