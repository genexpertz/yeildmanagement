package com.expertzlab.yieldmanagement.fileutils.ownerproperty;

/**
 * Created by expertzlab on 9/24/17.
 */

import com.expertzlab.yieldmanagement.models.OwnerProperty;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;




public class OwnerPropertyDataReader {


    Connection con;
    protected ResultSet res;

    public boolean hasNext() {

        try {
            return res.next();
        } catch (SQLException e) {
            return false;
        }
    }

    public OwnerPropertyDataReader(Connection con)
    {

        this.con = con;
    }
    public void getAllOwnerPropertyList(int ownerId) throws SQLException {
        PreparedStatement statement = con.prepareStatement("select * from owner_property where ownerid=?");
        statement.setInt(1,ownerId);
        res = statement.executeQuery();
    }

    public void close() throws SQLException{
        res.close();
        System.out.println("Executed successfully");
    }

    public OwnerProperty get() throws SQLException {
        String[] hArray = prepareParticipantHeaderArray();
        String[] rArray = new String[10];

        OwnerPropertyRecordArray(rArray,res);
        OwnerPropertyDataSetter eds = new OwnerPropertyDataSetter(OwnerProperty.class,hArray,rArray);
        OwnerProperty ownerProperty = eds.run();
        return ownerProperty;
    }



    private void OwnerPropertyRecordArray(String[] rArray, ResultSet res) throws SQLException {
        rArray[0] = res.getString(1);
        rArray[1] = res.getString(2);
        rArray[2] = res.getString(3);
        rArray[3] = res.getString(4);
    }

    private String[] prepareParticipantHeaderArray(){
        String[] hArray = new String[4];
        hArray[0]= "opid";
        hArray[1]="ownid";
        hArray[2]="region";
        hArray[3]="name";
        return  hArray;
    }
}
