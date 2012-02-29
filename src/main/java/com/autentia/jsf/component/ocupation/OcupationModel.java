/**
 *
 */
package com.autentia.jsf.component.ocupation;

import java.util.*;

/**
 * This interface represents must be implemented to a class
 * to represent vacations of
 *
 * @author german
 */
public abstract class OcupationModel {

    private static final Calendar cals = Calendar.getInstance();
    private static final Calendar cale = Calendar.getInstance();

    private final Map<Number, OcupationEntry> idOcupationMap = new HashMap<Number, OcupationEntry>();
    private final Collection<OcupationEntry> ocupationEntries = new ArrayList<OcupationEntry>();

    private static final boolean isBetweenRange(Date aDay, OcupationEntry entry) {

        cals.setTime(entry.getStart());
        cale.setTime(entry.getEnd());

        cals.set(Calendar.HOUR_OF_DAY, cals.getMinimum(Calendar.HOUR));
        cals.set(Calendar.MINUTE, cals.getMinimum(Calendar.MINUTE));
        cals.set(Calendar.SECOND, cals.getMinimum(Calendar.SECOND));
        cals.set(Calendar.MILLISECOND, cals.getMinimum(Calendar.MILLISECOND));

        cale.set(Calendar.HOUR_OF_DAY, cale.getMaximum(Calendar.HOUR));
        cale.set(Calendar.MINUTE, cale.getMaximum(Calendar.MINUTE));
        cale.set(Calendar.SECOND, cale.getMaximum(Calendar.SECOND));
        cale.set(Calendar.MILLISECOND, cale.getMaximum(Calendar.MILLISECOND));

        return (aDay.after(cals.getTime()) && aDay.before(cale.getTime())) || (aDay.compareTo(cals.getTime()) == 0) || (aDay.compareTo(cale.getTime()) == 0);
    }

    public final void addOcupationEntries(Collection<OcupationEntry> entries) {
        addOcupationEntries(entries, false);
    }

    public final void addOcupationEntries(Collection<OcupationEntry> entries, boolean init) {
        if (init) {
            idOcupationMap.clear();
            ocupationEntries.clear();
        }

        for (OcupationEntry entry : entries) {
            idOcupationMap.put(entry.getId(), entry);
        }

        ocupationEntries.addAll(entries);
    }

    public final void addOcupationEntry(OcupationEntry entry) {

        idOcupationMap.put(entry.getId(), entry);

        ocupationEntries.add(entry);
    }

    public final Collection<OcupationEntry> getOcupationEntries(Date day) {

        Collection<OcupationEntry> toReturn = new ArrayList<OcupationEntry>();

        for (OcupationEntry entry : ocupationEntries) {
            if (isBetweenRange(day, entry)) {
                toReturn.add(entry);
            }
        }

        return (toReturn.size() == 0) ? null : toReturn;
    }

    public final OcupationEntry getOcupationEntry(Number id) {
        return idOcupationMap.get(id);
    }


}
