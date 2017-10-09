package com.expertzlab.yieldmanagement.fileutils.ownerproperty;

/**
 * Created by expertzlab on 9/24/17.
 */

import com.expertzlab.yieldmanagement.fileutils.owner.OwnerDataSetter;
import com.expertzlab.yieldmanagement.models.Owner;
import com.expertzlab.yieldmanagement.models.OwnerProperty;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class OwnerPropertyDataReader {


    Connection con;


    public OwnerPropertyDataReader(Connection con)
    {

        this.con = con;
    }
    public int getAllOwnerPropertyCount(int ownerId) throws SQLException {
        PreparedStatement statement = con.prepareStatement("select max(opid) from owner_property where ownid=?");
        statement.setInt(1,ownerId);
        ResultSet res = statement.executeQuery();
        int propertyCount = 0;
        while(res.next()){
            propertyCount = res.getInt(1);
        }
        statement.close();
        res.close();
        return propertyCount;
    }

    public int getAllOwnerPropertyStartCount(int ownerId) throws SQLException {
        PreparedStatement statement = con.prepareStatement("select min(opid) from owner_property where ownid=?");
        statement.setInt(1,ownerId);
        ResultSet res = statement.executeQuery();
        int propertyCount = 0;
        while(res.next()){
            propertyCount = res.getInt(1);
        }
        statement.close();
        res.close();
        return propertyCount;
    }

    public int getAllOwnerPropertyCount() throws SQLException {
        PreparedStatement statement = con.prepareStatement("select max(opid) from owner_property");
        ResultSet res = statement.executeQuery();
        int propertyCount = 0;
        while(res.next()){
            propertyCount = res.getInt(1);
        }
        statement.close();
        res.close();
        return propertyCount;
    }

    public OwnerProperty get(int ownerId, int propertyId) throws SQLException {
        String[] hArray = prepareOwnerPropertyHeaderArray();
        String[] rArray = new String[4];
        PreparedStatement ps = con.prepareStatement("select * from owner_property where ownid=? and opid=?");
        ps.setInt(1,ownerId);
        ps.setInt(2,propertyId);
        ResultSet res = ps.executeQuery();
        OwnerProperty ownerProperty = null;
        if(res.next()) {
            OwnerPropertyRecordArray(rArray, res);
            OwnerPropertyDataSetter eds = new OwnerPropertyDataSetter(OwnerProperty.class, hArray, rArray);
            ownerProperty = eds.run();
        }
        ps.close();
        res.close();
        return ownerProperty;
    }

    public OwnerProperty get(int propertyId) throws SQLException {
        String[] hArray = prepareOwnerPropertyHeaderArray();
        String[] rArray = new String[4];
        PreparedStatement ps = con.prepareStatement("select * from owner_property where opid=?");
        ps.setInt(1,propertyId);
        ResultSet res = ps.executeQuery();
        OwnerProperty ownerProperty = null;
        if(res.next()) {
            OwnerPropertyRecordArray(rArray, res);
            OwnerPropertyDataSetter eds = new OwnerPropertyDataSetter(OwnerProperty.class, hArray, rArray);
            ownerProperty = eds.run();
        }
        ps.close();
        res.close();
        return ownerProperty;
    }


    private void OwnerPropertyRecordArray(String[] rArray, ResultSet res) throws SQLException {
        rArray[0] = res.getString(1);
        rArray[1] = res.getString(2);
        rArray[2] = res.getString(3);
        rArray[3] = res.getString(4);
    }

    private String[] prepareOwnerPropertyHeaderArray(){
        String[] hArray = new String[4];
        hArray[0]= "opid";
        hArray[1]="ownid";
        hArray[2]="region";
        hArray[3]="name";
        return  hArray;
    }
}
