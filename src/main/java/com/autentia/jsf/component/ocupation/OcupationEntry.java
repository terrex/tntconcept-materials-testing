package com.autentia.jsf.component.ocupation;

import java.util.Date;


/**
 * Represents each entry of model. An entry may paint
 * many days of component
 *
 * @author german
 */
public interface OcupationEntry {

    /**
     * id of this entry
     *
     * @return unique id
     */
    public Number getId();

    /**
     * start date of range (included)
     *
     * @return start date of range
     */
    public Date getStart();

    /**
     * end day of range (included)
     *
     * @return end day of range
     */
    public Date getEnd();


    /**
     * Description of entry
     *
     * @return
     */
    public String getDescription();

    /**
     * entry is vacances
     *
     * @return
     */
    public boolean isVacances();


    /**
     * entry holiday
     *
     * @return
     */
    public boolean isHoliday();


    /**
     * Associated object
     *
     * @return asociated object
     */
    public Object getAssociatedObject();


    /**
     * duration of task
     *
     * @return duration of task in minutes
     */
    public int getDuration();
}
