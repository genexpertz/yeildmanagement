package com.expertzlab.yieldmanagement.fileutils.ownerproperty;

import com.expertzlab.yieldmanagement.fileutils.propertymanager.PropertyManagerRandomizer;
import com.expertzlab.yieldmanagement.models.Owner;
import com.expertzlab.yieldmanagement.models.OwnerProperty;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

/**
 * Created by expertzlab on 9/6/17.
 */


public class OwnerPropertyDataWriter{
    Connection con;
    List<Object> list;
    String pmid;
    String region;


    public OwnerPropertyDataWriter(Connection con, List<Object> list) {
        this.con = con;
        this.list = list;

    }


    public void run() {


        try {
                OwnerPropertyRandomizer ar = new OwnerPropertyRandomizer(con);
                list = ar.getRandomizedList(list);
                for (Object op : list) {
                    System.out.println("OwnerProperty-" + op);
                    System.out.println("In new thread");
                    PreparedStatement statement = con.prepareStatement("insert into owner_property(opid,name,region,ownid) values(?,?,?,?)");
                    statement.setLong(1, ((OwnerProperty) op).getPropertyId());
                    statement.setString(2, ((OwnerProperty) op).getName());
                    statement.setString(3, ((OwnerProperty) op).getRegion());
                    statement.setLong(4, ((OwnerProperty) op).getOwnerId());
                    statement.execute();
                    System.out.println("Executed successfully");
                }
                list.clear();
                list = null;
            }  catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
