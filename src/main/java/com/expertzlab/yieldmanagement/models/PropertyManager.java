package com.expertzlab.yieldmanagement.models;

/**
 * Created by expertzlab on 8/11/17.
 */
public class PropertyManager {
    String region;
    String contact;
    String name;
    String managerId;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setId(String id){
        this.managerId = id;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }
}
