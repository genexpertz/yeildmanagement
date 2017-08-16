package com.expertzlab.yieldmanagement.models;

import java.util.Date;

/**
 * Created by expertzlab on 8/11/17.
 */
public class Price {
    Date date;
    int propertyID;
    Float price;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(int propertyID) {
        this.propertyID = propertyID;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
