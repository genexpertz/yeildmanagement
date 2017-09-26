package com.expertzlab.yieldmanagement.models;

import java.util.Date;

/**
 * Created by expertzlab on 8/11/17.
 */
public class Price {

    int cpid;
    int opid;
    int Did;
    Float price;

    public int getCpid() {
        return cpid;
    }

    public void setCpid(int cpid) {
        this.cpid = cpid;
    }

    public int getOpid() {
        return opid;
    }

    public void setOpid(int opid) {
        this.opid = opid;
    }

    public int getDid() {
        return Did;
    }

    public void setDid(int did) {
        Did = did;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

}
