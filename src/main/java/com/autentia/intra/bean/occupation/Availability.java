package com.autentia.intra.bean.occupation;

import com.autentia.intra.businessobject.User;
import com.autentia.jsf.component.ocupation.OcupationModel;

public class Availability {
    OcupationModel model;
    User user;


    public OcupationModel getModel() {
        return model;
    }

    public void setModel(OcupationModel model) {
        this.model = model;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
