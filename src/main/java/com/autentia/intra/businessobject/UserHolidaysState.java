package com.autentia.intra.businessobject;

import java.io.Serializable;

public class UserHolidaysState implements Serializable {

    private User user;
    private int totalAccepted = 0;
    private int totalYear = 0;

    public int getTotal() {
        return (getTotalYear() - totalAccepted);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getTotalAccepted() {
        return totalAccepted;
    }

    public void setTotalAccepted(int totalAccepted) {
        this.totalAccepted = totalAccepted;
    }

    public int getTotalYear() {
        return totalYear;
    }

    public void setTotalYear(int totalYear) {
        this.totalYear = totalYear;
    }


}
