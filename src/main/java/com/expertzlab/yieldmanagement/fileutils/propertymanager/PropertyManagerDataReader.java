package com.expertzlab.yieldmanagement.fileutils.propertymanager;

import com.expertzlab.yieldmanagement.models.PropertyManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PropertyManagerDataReader {


    Connection con;
    protected ResultSet res;
    PreparedStatement statement;
    public boolean hasNext() {

        try {
            return res.next();
        } catch (SQLException e) {
            return false;
        }
    }

    public PropertyManagerDataReader(Connection con)
    {

        this.con = con;
    }
    public void getAllPropertyManagerList() throws SQLException {
        statement = con.prepareStatement("select * from property_manager");
        res = statement.executeQuery();
    }

    public void close() throws SQLException{
        res.close();
        statement.close();
        System.out.println("Executed successfully");
    }

    public PropertyManager get() throws SQLException {
        String[] hArray = prepareParticipantHeaderArray();
        String[] rArray = new String[10];

        propertyManagerRecordArray(rArray,res);
        PropertyManagerDataSetter eds = new PropertyManagerDataSetter(PropertyManager.class,hArray,rArray);
        PropertyManager p = eds.run();
        return p;
    }


    private void propertyManagerRecordArray(String[] rArray, ResultSet res) throws SQLException {
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
        hArray[3]="region";
        return  hArray;
    }
}


