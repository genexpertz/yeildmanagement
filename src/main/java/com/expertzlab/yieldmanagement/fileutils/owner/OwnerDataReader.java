package com.expertzlab.yieldmanagement.fileutils.owner;

import com.expertzlab.yieldmanagement.fileutils.DateDataReader;
import com.expertzlab.yieldmanagement.fileutils.owner.OwnerDataSetter;
import com.expertzlab.yieldmanagement.models.Owner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class OwnerDataReader {


    public static com.expertzlab.yieldmanagement.fileutils.DateDataReader DateDataReader;
    Connection con;
    protected ResultSet res;

    public boolean hasNext() {

        try {
            return res.next();
        } catch (SQLException e) {
            return false;
        }
    }

    public OwnerDataReader(Connection con)
    {

        this.con = con;
    }
    public void getAllOwnerList() throws SQLException {
        PreparedStatement statement = con.prepareStatement("select * from owner");
        res = statement.executeQuery();
    }

    public void close() throws SQLException{
        res.close();
        System.out.println("Executed successfully");
    }

    public Owner get() throws SQLException {
        String[] hArray = prepareParticipantHeaderArray();
        String[] rArray = new String[10];

        OwnerRecordArray(rArray,res);
        OwnerDataSetter eds = new OwnerDataSetter(Owner.class,hArray,rArray);
        Owner owner = eds.run();
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


