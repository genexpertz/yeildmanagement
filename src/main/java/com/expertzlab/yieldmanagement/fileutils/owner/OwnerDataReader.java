package com.expertzlab.yieldmanagement.fileutils.owner;

import com.expertzlab.yieldmanagement.fileutils.owner.OwnerDataSetter;
import com.expertzlab.yieldmanagement.models.Owner;

import java.sql.*;


public class OwnerDataReader {


    Connection con;


    public OwnerDataReader(Connection con)
    {

        this.con = con;
    }
    public int getAllOwnerCount() throws SQLException {
        Statement statement = con.createStatement();
        ResultSet res = statement.executeQuery("select max(ownid) from owner");
        int count = 0;
        if(res.next()) {
            count = res.getInt(1);
        }
        statement.close();
        res.close();
        return count;

    }


    public Owner get(int ownId) throws SQLException {
        String[] hArray = prepareParticipantHeaderArray();
        String[] rArray = new String[4];
        PreparedStatement ps = con.prepareStatement("select * from owner where ownid=?");
        ps.setInt(1,ownId);
        ResultSet res = ps.executeQuery();
        Owner owner = null;
        if(res.next()) {
            OwnerRecordArray(rArray, res);
            OwnerDataSetter eds = new OwnerDataSetter(Owner.class, hArray, rArray);
            owner = eds.run();
        }
        ps.close();
        res.close();
        return owner;
    }


    private void OwnerRecordArray(String[] rArray, ResultSet res) throws SQLException {
        rArray[0] = res.getString(1);
        rArray[1] = res.getString(2);
        rArray[2] = res.getString(3);
        rArray[3] = res.getString(4);

    }

    private String[] prepareParticipantHeaderArray(){
        String[] hArray = new String[4];
        hArray[0]= "id";
        hArray[1]="name";
        hArray[2]="contact";
        hArray[2]="address";
        hArray[3]="pmid";
        return  hArray;
    }
}


