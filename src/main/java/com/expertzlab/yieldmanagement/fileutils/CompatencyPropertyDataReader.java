package com.expertzlab.yieldmanagement.fileutils;

/**
 * Created by expertzlab on 9/25/17.
 */

import com.expertzlab.yieldmanagement.fileutils.propertymanager.PropertyManagerDataSetter;
import com.expertzlab.yieldmanagement.models.CompetantProperty;
import com.expertzlab.yieldmanagement.models.PropertyManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CompatencyPropertyDataReader {


    Connection con;
    protected ResultSet res;

    public boolean hasNext() {

        try {
            return res.next();
        } catch (SQLException e) {
            return false;
        }
    }

    public CompatencyPropertyDataReader(Connection con)
    {

        this.con = con;
    }
    public void getAllCompatencyPropertyList(int ownerPropId) throws SQLException {
        PreparedStatement statement = con.prepareStatement("select * from compatency_property_list where opid=?");
        statement.setInt(1,ownerPropId);
        res = statement.executeQuery();
    }

    public void close() throws SQLException{
        res.close();
        System.out.println("Executed successfully");
    }

    public CompetantProperty get() throws SQLException {
        String[] hArray = CompetantPropertyRecordArray();
        String[] rArray = new String[10];

        CompetantPropertyRecordArray(rArray,res);
        CompatencyPropertyDataSetter com = new CompatencyPropertyDataSetter(CompetantProperty.class,hArray,rArray);
        CompetantProperty competantProperty = com.run();
        return competantProperty;
    }


    private void  CompetantPropertyRecordArray(String[] rArray, ResultSet res) throws SQLException {
        rArray[0] = String.valueOf(res.getInt(1));
        rArray[1] = String.valueOf(res.getInt(2));
        rArray[2] = res.getString(3);
        rArray[3] = res.getString(4);
    }

    private String[] CompetantPropertyRecordArray(){
        String[] hArray = new String[4];
        hArray[0]= "cpid";
        hArray[1]="opid";
        hArray[2]="region";
        hArray[3]="name";
        return  hArray;
    }
}