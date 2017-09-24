package com.expertzlab.yieldmanagement.models;

/**
 * Created by expertzlab on 8/11/17.
 */
public class OwnerProperty {
    String name;
    String region;
    int propertyId;
    int ownerId;

    public int getOwnerId() { return ownerId; }

    public void setOwnerId(int ownerId) { this.ownerId = ownerId; }

    public String getUrl() {
        return name;
    }

    public void setUrl(String url) {
        this.name = url;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public void setPropertyId() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    public String toString(){
        return "name:"+name+",region:" + region+",propId:"+propertyId+",owner-id:"+ownerId;
    }
}
