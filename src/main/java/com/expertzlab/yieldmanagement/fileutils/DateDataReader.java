package com.expertzlab.yieldmanagement.fileutils;

import com.expertzlab.yieldmanagement.fileutils.PriceDataSetter;
import com.expertzlab.yieldmanagement.models.Price;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by expertzlab on 9/25/17.
 */
public class DateDataReader {


    Connection con;
    protected ResultSet res;

    public boolean hasNext() {

        try {
            return res.next();
        } catch (SQLException e) {
            return false;
        }
    }

    public DateDataReader(Connection con)
    {

        this.con = con;
    }
    public void getAllDateList() throws SQLException {
        PreparedStatement statement = con.prepareStatement("select * from dates");
        res = statement.executeQuery();
    }

    public void close() throws SQLException{
        res.close();
        System.out.println("Executed successfully");
    }

    public Price get() throws SQLException {
        String[] hArray = prepareParticipantHeaderArray();
        String[] rArray = new String[10];

        OwnerRecordArray(rArray,res);
        PriceDataSetter eds = new PriceDataSetter(Price.class,hArray,rArray);
        Price pri = eds.run();
        return pri;
    }


    private void OwnerRecordArray(String[] rArray, ResultSet res) throws SQLException {
        rArray[0] = String.valueOf(res.getInt(1));
        rArray[1] = String.valueOf(res.getDate(2));
        rArray[2] = String.valueOf(res.getInt(3));
        rArray[3] = String.valueOf(res.getInt(4));

    }

    private String[] prepareParticipantHeaderArray(){
        String[] hArray = new String[4];
        hArray[0]= "id";
        hArray[1]="date";
        hArray[2]="month";
        hArray[3]="year";
        return  hArray;
    }
}

