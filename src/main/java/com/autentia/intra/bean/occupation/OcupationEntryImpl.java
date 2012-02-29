/**
 *
 */
package com.autentia.intra.bean.occupation;

import com.autentia.jsf.component.ocupation.OcupationEntry;

import java.util.Date;

/**
 * @author german
 */
public class OcupationEntryImpl implements OcupationEntry {

    private Integer id;

    private Object associatedObject;
    private String description;
    private Date start;
    private Date end;
    private int duration;

    private boolean isVacances = false;

    private boolean isHoliday = false;


    public OcupationEntryImpl() {

    }

    public OcupationEntryImpl(Integer id, Date start,
                              Date end, String description, boolean isVacances, int duration) {
        super();
        this.id = id;
        this.description = description;
        this.start = start;
        this.end = end;
        this.isVacances = isVacances;
        this.duration = duration;
    }


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public Object getAssociatedObject() {
        return associatedObject;
    }


    public void setAssociatedObject(Object associatedObject) {
        this.associatedObject = associatedObject;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public Date getStart() {
        return start;
    }


    public void setStart(Date start) {
        this.start = start;
    }


    public Date getEnd() {
        return end;
    }


    public void setEnd(Date end) {
        this.end = end;
    }


    public boolean isVacances() {
        return isVacances;
    }


    public void setVacances(boolean isVacances) {
        this.isVacances = isVacances;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isHoliday() {
        return isHoliday;
    }

    public void setHoliday(boolean isHoliday) {
        this.isHoliday = isHoliday;
    }


}
