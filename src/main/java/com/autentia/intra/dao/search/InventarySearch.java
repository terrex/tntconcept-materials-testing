package com.autentia.intra.dao.search;

import com.autentia.intra.businessobject.InventaryType;
import com.autentia.intra.businessobject.User;
import com.autentia.intra.dao.SearchCriteria;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

/**
 * Class to search for Inventary objects
 *
 * @author stajanov code generator
 */
public class InventarySearch extends SearchCriteria {
/* generated by stajanov (do not edit/delete) */


    @Override
    public String getHQL() {
        StringBuilder ret = new StringBuilder();
        int iArgNum = 0;


        if (isStartBuyDateSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            if (startBuyDate == null) {
                ret.append("buyDate=:arg" + (iArgNum++));
            } else {
                ret.append("buyDate>=:arg" + (iArgNum++));
            }
        }
        if (isEndBuyDateSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            if (endBuyDate == null) {
                ret.append("buyDate=:arg" + (iArgNum++));
            } else {
                ret.append("buyDate<=:arg" + (iArgNum++));
            }
        }


        if (isRentingSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            if (getRenting() == null) {
                ret.append("renting is NULL");
            } else {
                ret.append("renting = :arg" + (iArgNum++));
            }
        }


        if (isStartCostSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            if (startCost == null) {
                ret.append("cost=:arg" + (iArgNum++));
            } else {
                ret.append("cost>=:arg" + (iArgNum++));
            }
        }
        if (isEndCostSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            if (endCost == null) {
                ret.append("cost=:arg" + (iArgNum++));
            } else {
                ret.append("cost<=:arg" + (iArgNum++));
            }
        }


        if (isAmortizableSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            if (getAmortizable() == null) {
                ret.append("amortizable is NULL");
            } else {
                ret.append("amortizable = :arg" + (iArgNum++));
            }
        }


        if (isSerialNumberSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            if (getSerialNumber() == null) {
                ret.append("serialNumber is NULL");
            } else {
                ret.append("serialNumber = :arg" + (iArgNum++));
            }
        }


        if (isProviderSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            if (getProvider() == null) {
                ret.append("provider is NULL");
            } else {
                ret.append("provider = :arg" + (iArgNum++));
            }
        }


        if (isTrademarkSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            if (getTrademark() == null) {
                ret.append("trademark is NULL");
            } else {
                ret.append("trademark = :arg" + (iArgNum++));
            }
        }


        if (isModelSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            if (getModel() == null) {
                ret.append("model is NULL");
            } else {
                ret.append("model = :arg" + (iArgNum++));
            }
        }


        if (isSpeedSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            if (getSpeed() == null) {
                ret.append("speed is NULL");
            } else {
                ret.append("speed = :arg" + (iArgNum++));
            }
        }


        if (isStorageSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            if (getStorage() == null) {
                ret.append("storage is NULL");
            } else {
                ret.append("storage = :arg" + (iArgNum++));
            }
        }


        if (isRamSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            if (getRam() == null) {
                ret.append("ram is NULL");
            } else {
                ret.append("ram = :arg" + (iArgNum++));
            }
        }


        if (isLocationSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            if (getLocation() == null) {
                ret.append("location is NULL");
            } else {
                ret.append("location = :arg" + (iArgNum++));
            }
        }


        if (isDescriptionSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            if (getDescription() == null) {
                ret.append("description is NULL");
            } else {
                ret.append("description = :arg" + (iArgNum++));
            }
        }


        if (isTypeSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            if (getType() == null) {
                ret.append("type is NULL");
            } else {
                ret.append("type = :arg" + (iArgNum++));
            }
        }


        if (isOwnerIdSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            if (getOwnerId() == null) {
                ret.append("ownerId is NULL");
            } else {
                ret.append("ownerId = :arg" + (iArgNum++));
            }
        }


        if (isDepartmentIdSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            if (getDepartmentId() == null) {
                ret.append("departmentId is NULL");
            } else {
                ret.append("departmentId = :arg" + (iArgNum++));
            }
        }


        if (isStartInsertDateSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            if (startInsertDate == null) {
                ret.append("insertDate=:arg" + (iArgNum++));
            } else {
                ret.append("insertDate>=:arg" + (iArgNum++));
            }
        }
        if (isEndInsertDateSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            if (endInsertDate == null) {
                ret.append("insertDate=:arg" + (iArgNum++));
            } else {
                ret.append("insertDate<=:arg" + (iArgNum++));
            }
        }


        if (isStartUpdateDateSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            if (startUpdateDate == null) {
                ret.append("updateDate=:arg" + (iArgNum++));
            } else {
                ret.append("updateDate>=:arg" + (iArgNum++));
            }
        }
        if (isEndUpdateDateSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            if (endUpdateDate == null) {
                ret.append("updateDate=:arg" + (iArgNum++));
            } else {
                ret.append("updateDate<=:arg" + (iArgNum++));
            }
        }


        if (isAssignedToSet()) {
            ret.append((ret.length() == 0) ? "WHERE " : " AND ");
            if (getAssignedTo() == null) {
                ret.append("assignedTo is NULL");
            } else {
                ret.append("assignedTo = :arg" + (iArgNum++));
            }
        }

        customGetHQL(ret, iArgNum);
        return ret.toString();
    }

    @Override
    public Object[] getArguments() {
        ArrayList<Object> ret = new ArrayList<Object>();


        if (isStartBuyDateSet()) {
            ret.add(startBuyDate);
        }
        if (isEndBuyDateSet()) {
            ret.add(endBuyDate);
        }


        if (isRentingSet() && getRenting() != null) {
            ret.add(renting);
        }


        if (isStartCostSet()) {
            ret.add(startCost);
        }
        if (isEndCostSet()) {
            ret.add(endCost);
        }


        if (isAmortizableSet() && getAmortizable() != null) {
            ret.add(amortizable);
        }


        if (isSerialNumberSet() && getSerialNumber() != null) {
            ret.add(serialNumber);
        }


        if (isProviderSet() && getProvider() != null) {
            ret.add(provider);
        }


        if (isTrademarkSet() && getTrademark() != null) {
            ret.add(trademark);
        }


        if (isModelSet() && getModel() != null) {
            ret.add(model);
        }


        if (isSpeedSet() && getSpeed() != null) {
            ret.add(speed);
        }


        if (isStorageSet() && getStorage() != null) {
            ret.add(storage);
        }


        if (isRamSet() && getRam() != null) {
            ret.add(ram);
        }


        if (isLocationSet() && getLocation() != null) {
            ret.add(location);
        }


        if (isDescriptionSet() && getDescription() != null) {
            ret.add(description);
        }


        if (isTypeSet() && getType() != null) {
            ret.add(type);
        }


        if (isOwnerIdSet() && getOwnerId() != null) {
            ret.add(ownerId);
        }


        if (isDepartmentIdSet() && getDepartmentId() != null) {
            ret.add(departmentId);
        }


        if (isStartInsertDateSet()) {
            ret.add(startInsertDate);
        }
        if (isEndInsertDateSet()) {
            ret.add(endInsertDate);
        }


        if (isStartUpdateDateSet()) {
            ret.add(startUpdateDate);
        }
        if (isEndUpdateDateSet()) {
            ret.add(endUpdateDate);
        }


        if (isAssignedToSet() && getAssignedTo() != null) {
            ret.add(assignedTo);
        }

        customGetArguments(ret);
        return ret.toArray();
    }

    @Override
    public void reset() {


        unsetStartBuyDate();
        unsetEndBuyDate();


        unsetRenting();


        unsetStartCost();
        unsetEndCost();


        unsetAmortizable();


        unsetSerialNumber();


        unsetProvider();


        unsetTrademark();


        unsetModel();


        unsetSpeed();


        unsetStorage();


        unsetRam();


        unsetLocation();


        unsetDescription();


        unsetType();


        unsetOwnerId();


        unsetDepartmentId();


        unsetStartInsertDate();
        unsetEndInsertDate();


        unsetStartUpdateDate();
        unsetEndUpdateDate();


        unsetAssignedTo();

        customReset();
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("InventarySearch{");


        if (isStartBuyDateSet()) {
            ret.append("(startBuyDate");
            ret.append("=" + startBuyDate);
            ret.append(")");
        }
        if (isEndBuyDateSet()) {
            ret.append("(endBuyDate");
            ret.append("=" + endBuyDate);
            ret.append(")");
        }


        if (isRentingSet()) {
            ret.append("(renting");
            ret.append("=" + renting);
            ret.append(")");
        }


        if (isStartCostSet()) {
            ret.append("(startCost");
            ret.append("=" + startCost);
            ret.append(")");
        }
        if (isEndCostSet()) {
            ret.append("(endCost");
            ret.append("=" + endCost);
            ret.append(")");
        }


        if (isAmortizableSet()) {
            ret.append("(amortizable");
            ret.append("=" + amortizable);
            ret.append(")");
        }


        if (isSerialNumberSet()) {
            ret.append("(serialNumber");
            ret.append("=" + serialNumber);
            ret.append(")");
        }


        if (isProviderSet()) {
            ret.append("(provider");
            ret.append("=" + provider);
            ret.append(")");
        }


        if (isTrademarkSet()) {
            ret.append("(trademark");
            ret.append("=" + trademark);
            ret.append(")");
        }


        if (isModelSet()) {
            ret.append("(model");
            ret.append("=" + model);
            ret.append(")");
        }


        if (isSpeedSet()) {
            ret.append("(speed");
            ret.append("=" + speed);
            ret.append(")");
        }


        if (isStorageSet()) {
            ret.append("(storage");
            ret.append("=" + storage);
            ret.append(")");
        }


        if (isRamSet()) {
            ret.append("(ram");
            ret.append("=" + ram);
            ret.append(")");
        }


        if (isLocationSet()) {
            ret.append("(location");
            ret.append("=" + location);
            ret.append(")");
        }


        if (isDescriptionSet()) {
            ret.append("(description");
            ret.append("=" + description);
            ret.append(")");
        }


        if (isTypeSet()) {
            ret.append("(type");
            ret.append("=" + type);
            ret.append(")");
        }


        if (isOwnerIdSet()) {
            ret.append("(ownerId");
            ret.append("=" + ownerId);
            ret.append(")");
        }


        if (isDepartmentIdSet()) {
            ret.append("(departmentId");
            ret.append("=" + departmentId);
            ret.append(")");
        }


        if (isStartInsertDateSet()) {
            ret.append("(startInsertDate");
            ret.append("=" + startInsertDate);
            ret.append(")");
        }
        if (isEndInsertDateSet()) {
            ret.append("(endInsertDate");
            ret.append("=" + endInsertDate);
            ret.append(")");
        }


        if (isStartUpdateDateSet()) {
            ret.append("(startUpdateDate");
            ret.append("=" + startUpdateDate);
            ret.append(")");
        }
        if (isEndUpdateDateSet()) {
            ret.append("(endUpdateDate");
            ret.append("=" + endUpdateDate);
            ret.append(")");
        }


        if (isAssignedToSet()) {
            ret.append("(assignedTo");
            ret.append("=" + assignedTo);
            ret.append(")");
        }


        customToString(ret);
        ret.append("}");
        return ret.toString();
    }

    // Getters and setters


    public boolean isStartBuyDateSet() {
        return startBuyDateSet;
    }

    public Date getStartBuyDate() {
        return startBuyDate;
    }

    public void setStartBuyDate(Date startBuyDate) {
        this.startBuyDate = startBuyDate;
        this.startBuyDateSet = true;
    }

    public void unsetStartBuyDate() {
        this.startBuyDateSet = false;
    }

    public boolean isEndBuyDateSet() {
        return endBuyDateSet;
    }

    public Date getEndBuyDate() {
        return endBuyDate;
    }

    public void setEndBuyDate(Date endBuyDate) {
        this.endBuyDate = endBuyDate;
        this.endBuyDateSet = true;
    }

    public void unsetEndBuyDate() {
        this.endBuyDateSet = false;
    }


    public boolean isRentingSet() {
        return rentingSet;
    }

    public java.lang.Boolean getRenting() {
        return renting;
    }

    public void setRenting(java.lang.Boolean renting) {
        this.renting = renting;
        this.rentingSet = true;
    }

    public void unsetRenting() {
        this.rentingSet = false;
    }


    public boolean isStartCostSet() {
        return startCostSet;
    }

    public BigDecimal getStartCost() {
        return startCost;
    }

    public void setStartCost(BigDecimal startCost) {
        this.startCost = startCost;
        this.startCostSet = true;
    }

    public void unsetStartCost() {
        this.startCostSet = false;
    }

    public boolean isEndCostSet() {
        return endCostSet;
    }

    public BigDecimal getEndCost() {
        return endCost;
    }

    public void setEndCost(BigDecimal endCost) {
        this.endCost = endCost;
        this.endCostSet = true;
    }

    public void unsetEndCost() {
        this.endCostSet = false;
    }


    public boolean isAmortizableSet() {
        return amortizableSet;
    }

    public java.lang.Boolean getAmortizable() {
        return amortizable;
    }

    public void setAmortizable(java.lang.Boolean amortizable) {
        this.amortizable = amortizable;
        this.amortizableSet = true;
    }

    public void unsetAmortizable() {
        this.amortizableSet = false;
    }


    public boolean isSerialNumberSet() {
        return serialNumberSet;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        this.serialNumberSet = true;
    }

    public void unsetSerialNumber() {
        this.serialNumberSet = false;
    }


    public boolean isProviderSet() {
        return providerSet;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
        this.providerSet = true;
    }

    public void unsetProvider() {
        this.providerSet = false;
    }


    public boolean isTrademarkSet() {
        return trademarkSet;
    }

    public String getTrademark() {
        return trademark;
    }

    public void setTrademark(String trademark) {
        this.trademark = trademark;
        this.trademarkSet = true;
    }

    public void unsetTrademark() {
        this.trademarkSet = false;
    }


    public boolean isModelSet() {
        return modelSet;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
        this.modelSet = true;
    }

    public void unsetModel() {
        this.modelSet = false;
    }


    public boolean isSpeedSet() {
        return speedSet;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
        this.speedSet = true;
    }

    public void unsetSpeed() {
        this.speedSet = false;
    }


    public boolean isStorageSet() {
        return storageSet;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
        this.storageSet = true;
    }

    public void unsetStorage() {
        this.storageSet = false;
    }


    public boolean isRamSet() {
        return ramSet;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
        this.ramSet = true;
    }

    public void unsetRam() {
        this.ramSet = false;
    }


    public boolean isLocationSet() {
        return locationSet;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
        this.locationSet = true;
    }

    public void unsetLocation() {
        this.locationSet = false;
    }


    public boolean isDescriptionSet() {
        return descriptionSet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        this.descriptionSet = true;
    }

    public void unsetDescription() {
        this.descriptionSet = false;
    }


    public boolean isTypeSet() {
        return typeSet;
    }

    public InventaryType getType() {
        return type;
    }

    public void setType(InventaryType type) {
        this.type = type;
        this.typeSet = true;
    }

    public void unsetType() {
        this.typeSet = false;
    }


    public boolean isOwnerIdSet() {
        return ownerIdSet;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
        this.ownerIdSet = true;
    }

    public void unsetOwnerId() {
        this.ownerIdSet = false;
    }


    public boolean isDepartmentIdSet() {
        return departmentIdSet;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
        this.departmentIdSet = true;
    }

    public void unsetDepartmentId() {
        this.departmentIdSet = false;
    }


    public boolean isStartInsertDateSet() {
        return startInsertDateSet;
    }

    public Date getStartInsertDate() {
        return startInsertDate;
    }

    public void setStartInsertDate(Date startInsertDate) {
        this.startInsertDate = startInsertDate;
        this.startInsertDateSet = true;
    }

    public void unsetStartInsertDate() {
        this.startInsertDateSet = false;
    }

    public boolean isEndInsertDateSet() {
        return endInsertDateSet;
    }

    public Date getEndInsertDate() {
        return endInsertDate;
    }

    public void setEndInsertDate(Date endInsertDate) {
        this.endInsertDate = endInsertDate;
        this.endInsertDateSet = true;
    }

    public void unsetEndInsertDate() {
        this.endInsertDateSet = false;
    }


    public boolean isStartUpdateDateSet() {
        return startUpdateDateSet;
    }

    public Date getStartUpdateDate() {
        return startUpdateDate;
    }

    public void setStartUpdateDate(Date startUpdateDate) {
        this.startUpdateDate = startUpdateDate;
        this.startUpdateDateSet = true;
    }

    public void unsetStartUpdateDate() {
        this.startUpdateDateSet = false;
    }

    public boolean isEndUpdateDateSet() {
        return endUpdateDateSet;
    }

    public Date getEndUpdateDate() {
        return endUpdateDate;
    }

    public void setEndUpdateDate(Date endUpdateDate) {
        this.endUpdateDate = endUpdateDate;
        this.endUpdateDateSet = true;
    }

    public void unsetEndUpdateDate() {
        this.endUpdateDateSet = false;
    }


    public boolean isAssignedToSet() {
        return assignedToSet;
    }

    public User getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
        this.assignedToSet = true;
    }

    public void unsetAssignedTo() {
        this.assignedToSet = false;
    }

    // Fields


    private boolean startBuyDateSet;
    private Date startBuyDate;
    private boolean endBuyDateSet;
    private Date endBuyDate;


    private boolean rentingSet;
    private java.lang.Boolean renting;


    private boolean startCostSet;
    private BigDecimal startCost;
    private boolean endCostSet;
    private BigDecimal endCost;


    private boolean amortizableSet;
    private java.lang.Boolean amortizable;


    private boolean serialNumberSet;
    private String serialNumber;


    private boolean providerSet;
    private String provider;


    private boolean trademarkSet;
    private String trademark;


    private boolean modelSet;
    private String model;


    private boolean speedSet;
    private String speed;


    private boolean storageSet;
    private String storage;


    private boolean ramSet;
    private String ram;


    private boolean locationSet;
    private String location;


    private boolean descriptionSet;
    private String description;


    private boolean typeSet;
    private InventaryType type;


    private boolean ownerIdSet;
    private Integer ownerId;


    private boolean departmentIdSet;
    private Integer departmentId;


    private boolean startInsertDateSet;
    private Date startInsertDate;
    private boolean endInsertDateSet;
    private Date endInsertDate;


    private boolean startUpdateDateSet;
    private Date startUpdateDate;
    private boolean endUpdateDateSet;
    private Date endUpdateDate;


    private boolean assignedToSet;
    private User assignedTo;


    // Returns if there are a search condition active
    public boolean isSearchActive() {
        return customIsSearchActive() || startBuyDateSet || endBuyDateSet || rentingSet || startCostSet || endCostSet || amortizableSet || serialNumberSet || providerSet || trademarkSet || modelSet || speedSet || storageSet || ramSet || locationSet || descriptionSet || typeSet || ownerIdSet || departmentIdSet || startInsertDateSet || endInsertDateSet || startUpdateDateSet || endUpdateDateSet || assignedToSet;
    }

/* generated by stajanov (do not edit/delete) */

    private void customGetHQL(StringBuilder ret, int iArgNum) {
    }

    private boolean customIsSearchActive() {
        return false;
    }

    private void customToString(StringBuilder ret) {
    }

    private void customReset() {
    }

    private void customGetArguments(ArrayList ret) {
    }
}
