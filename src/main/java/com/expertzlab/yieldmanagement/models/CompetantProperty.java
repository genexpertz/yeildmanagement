package com.expertzlab.yieldmanagement.models;

/**
 * Created by expertzlab on 8/11/17.
 */
public class CompetantProperty {
    int cpid;
    int opid;
    String region;
    String name;

    public int getCpid() {
        return cpid;
    }

    public void setCpid(int cpid) {
        this.cpid = cpid;
    }
    public void setCpid(String cpid) {
        this.cpid = Integer.parseInt( cpid);
    }

    public void setOpid(String opid) {
        this.opid = Integer.parseInt( opid);
    }
    public int getOpid() {
        return opid;
    }

    public void setOpid(int opid) {
        this.opid = opid;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

