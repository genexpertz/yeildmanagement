package com.expertzlab.yieldmanagement.models;

import java.util.Date;

/**
 * Created by expertzlab on 8/11/17.
 */
public class Availability {
    int cpid;
    int opid;
    int oid;

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    int Did;
    String status;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}