package com.expertzlab.yieldmanagement.models;

import java.util.Date;

/**
 * Created by expertzlab on 8/11/17.
 */
public class Availability {
    String region;
    Date date;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
