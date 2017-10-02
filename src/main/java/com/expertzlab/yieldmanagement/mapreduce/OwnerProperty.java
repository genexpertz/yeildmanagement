package com.expertzlab.yieldmanagement.mapreduce;

import java.util.HashMap;

/**
 * Created by admin on 02/10/17.
 */
public class OwnerProperty {
    String price;
    String bookedStatus;
    HashMap<String,CompetingProperty> compPropMap;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBookedStatus() {
        return bookedStatus;
    }

    public void setBookedStatus(String bookedStatus) {
        this.bookedStatus = bookedStatus;
    }

    public HashMap<String, CompetingProperty> getCompPropMap() {
        return compPropMap;
    }

    public void setCompPropMap(HashMap<String, CompetingProperty> compPropMap) {
        this.compPropMap = compPropMap;
    }
}
