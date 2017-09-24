package com.expertzlab.yieldmanagement.models;

/**
 * Created by expertzlab on 8/11/17.
 */
public class Owner {
    int id;
    String contact;
    String name;
    String address;
    String managerId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId(String id) {
        this.id = Integer.parseInt(id);
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }
    public void setPmid(String pmid) {
        this.managerId = managerId;
    }
}

