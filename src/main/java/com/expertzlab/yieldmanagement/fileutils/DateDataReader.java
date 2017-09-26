package com.expertzlab.yieldmanagement.fileutils;

import com.expertzlab.yieldmanagement.fileutils.PriceDataSetter;
import com.expertzlab.yieldmanagement.fileutils.owner.OwnerDataSetter;
import com.expertzlab.yieldmanagement.models.Owner;
import com.expertzlab.yieldmanagement.models.Price;
import com.expertzlab.yieldmanagement.models.YMDate;

import java.sql.*;

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

    public YMDate get() throws SQLException {

        String[] hArray = prepareDateHeaderArray();
        String[] rArray = new String[4];

        dateRecordArray(rArray,res);
        DateDataSetter dds = new DateDataSetter(YMDate.class,hArray,rArray);
        YMDate date = dds.run();
        return date;
    }


    private void dateRecordArray(String[] rArray, ResultSet res) throws SQLException {
        rArray[0] = res.getString("id");
        rArray[1] = res.getString("date");
        rArray[2] = res.getString("year");
        rArray[3] = res.getString("month");

    }

    private String[] prepareDateHeaderArray(){
        String[] hArray = new String[4];
        hArray[0]= "id";
        hArray[1]="date";
        hArray[2]="year";
        hArray[3]="month";
        return  hArray;
    }
}

