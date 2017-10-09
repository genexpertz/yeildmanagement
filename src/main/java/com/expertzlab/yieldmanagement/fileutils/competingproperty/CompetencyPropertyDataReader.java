package com.expertzlab.yieldmanagement.fileutils.competingproperty;

/**
 * Created by expertzlab on 9/25/17.
 */

import com.expertzlab.yieldmanagement.models.CompetantProperty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CompetencyPropertyDataReader {


    Connection con;

    public CompetencyPropertyDataReader(Connection con)
    {

        this.con = con;
    }
    public int getAllCompatencyPropertyCount(int ownerPropId) throws SQLException {
        PreparedStatement statement = con.prepareStatement("select max(cpid) from compatency_property_list where opid=?");
        statement.setInt(1,ownerPropId);
        ResultSet res = statement.executeQuery();
        int compPropCount = 0;
        if(res.next()){
            compPropCount = res.getInt(1);
        }
        statement.close();
        res.close();
        return compPropCount;
    }

    public int getAllCompatencyPropertyStartCount(int ownerPropId) throws SQLException {
        PreparedStatement statement = con.prepareStatement("select min(cpid) from compatency_property_list where opid=?");
        statement.setInt(1,ownerPropId);
        ResultSet res = statement.executeQuery();
        int compPropCount = 0;
        if(res.next()){
            compPropCount = res.getInt(1);
        }
        statement.close();
        res.close();
        return compPropCount;
    }



    public CompetantProperty get(int propId) throws SQLException {
        String[] hArray = CompetantPropertyRecordArray();
        String[] rArray = new String[10];
        PreparedStatement statement = con.prepareStatement("select * from compatency_property_list where cpid=?");
        statement.setInt(1,propId);
        ResultSet res = statement.executeQuery();
        CompetantProperty competantProperty = null;
        if(res.next()){
            CompetantPropertyRecordArray(rArray,res);
            CompatencyPropertyDataSetter com = new CompatencyPropertyDataSetter(CompetantProperty.class,hArray,rArray);
            competantProperty = com.run();
        }
        statement.close();
        res.close();
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