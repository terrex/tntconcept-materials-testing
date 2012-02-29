package com.autentia.intra.bean.activity;

import com.autentia.intra.businessobject.Activity;
import com.autentia.intra.util.FacesUtils;
import org.apache.myfaces.custom.schedule.model.ScheduleEntry;

import java.util.Calendar;
import java.util.Date;

public class ActivityScheduleEntry implements ScheduleEntry {

    private final Activity activity;

    private final Calendar cal = Calendar.getInstance();

    public ActivityScheduleEntry(Activity activity) {
        this.activity = activity;
    }

    public String getDescription() {
        return activity.getDescription();
    }

    public Date getEndTime() {

        cal.setTime(getStartTime());
        cal.add(Calendar.MINUTE, activity.getDuration());

        return cal.getTime();
    }

    public String getId() {
        return String.valueOf(activity.getId());
    }

    public Date getStartTime() {
        return activity.getStartDate();
    }

    public String getSubtitle() {
        return getDuration() + "h." + (activity.isBillable() ? FacesUtils.formatMessage("activity.billable") : "");
    }

    public String getTitle() {
        return activity.getDescription();
    }

    public boolean isAllDay() {
        return false;
    }

    // other methods not implemented by interface ScheduleEntry

    public String getDuration() {

        return String.valueOf(Float.valueOf(activity.getDuration()) / 60);
    }

    public String getDetailTitle() {
        if (activity.getRole() != null) {
            return activity.getRole().getProject().getClient().getName() + " - " + activity.getRole().getProject().getName() + " (" + activity.getRole().getName() + ")";
        } else if (activity.getEnsayo() != null) {
            return activity.getEnsayo().getProject().getClient().getName() + " - " + activity.getEnsayo().getProject().getName() + " (" + activity.getEnsayo().getEnsayo().getName() + ")";
        } else {
            return null;
        }
    }

    public String getDetailSubtitle() {
        return getSubtitle();
    }

    public String getDetailDescription() {
        return getDescription().replaceAll("\n", " ");
    }

    public boolean isBillable() {
        return activity.isBillable();
    }


}
