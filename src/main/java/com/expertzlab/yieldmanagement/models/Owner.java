package com.expertzlab.yieldmanagement.models;

/**
 * Created by expertzlab on 8/11/17.
 */
public class Owner {
    int id;
    String contact;
    String name;
    String address;

    public int getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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


}

