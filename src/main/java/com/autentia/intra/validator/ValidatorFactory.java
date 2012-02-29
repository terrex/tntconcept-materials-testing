package com.autentia.intra.validator;

import com.autentia.intra.util.ConfigurationUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.faces.validator.Validator;
import java.util.HashMap;


public class ValidatorFactory {

    private static final Log log = LogFactory.getLog(ValidatorFactory.class);

    private static ValidatorFactory instance = null;

    private HashMap<String, Class> tableValidators = new HashMap<String, Class>();

    private ValidatorFactory() {
    }

    private static void init() {
        if (instance == null) {
            synchronized (ValidatorFactory.class) {
                if (instance == null) {
                    instance = new ValidatorFactory();
                }
            }
        }
    }

    public static ValidatorFactory getFactory() {
        init();
        return instance;
    }

    private Validator getValidator(String className) {

        Class classFinal = null;
        Validator validator = null;

        try {
            if (tableValidators.containsKey(className)) {
                classFinal = (Class) tableValidators.get(className);
            } else {
                ClassLoader cl = getClass().getClassLoader();
                classFinal = cl.loadClass(className);
                tableValidators.put(className, classFinal);
            }
            validator = (Validator) classFinal.newInstance();
        } catch (Exception ex) {
            log.error("Error creating Validator: " + ex.getMessage());
        }
        return validator;
    }

    public Validator getIdentityCardValidator() {

        // conseguir nombre de clase por propiedar en properties
        String className = ConfigurationUtil.getDefault().getIdentityCardValidator();
        return getValidator(className);
    }

    public Validator getMoneyValidator() {
        // conseguir nombre de clase por propiedar en properties
        String className = ConfigurationUtil.getDefault().getMoneyValidator();
        return getValidator(className);
    }

    public Validator getAccountEntryValidator() {
        String className = ConfigurationUtil.getDefault().getAccountEntryValidator();
        return getValidator(className);
    }


    public Validator getPeriodicalAccountEntryValidator() {
        String className = ConfigurationUtil.getDefault().getPeriodicalAccountEntryValidator();
        return getValidator(className);
    }

}
