package com.expertzlab.yieldmanagement.fileutil;

import com.expertzlab.yieldmanagement.models.OwnerProperty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by expertzlab on 9/6/17.
 */

public class OwnerPropertyDataWriter extends Thread{
    Connection con;
    List<Object> list;


    public OwnerPropertyDataWriter(Connection con, List<Object> list)
    {
        this.con = con;
        this.list = list;

    }
    public void run()
    {

        try {
            OwnerPropertyRandomizer ar = new OwnerPropertyRandomizer(con);
            list = ar.getRandomizedList(list);
            for(Object op :list) {
                System.out.println("OwnerProperty-"+op);
                System.out.println("In new thread");
                PreparedStatement statement = con.prepareStatement("insert into OwnerProperty  values(?,?) ");
                statement.setLong(1,((OwnerProperty)op).getPropertyId());
                statement.setString(2,((OwnerProperty)op).getRegion());
                statement.setString(3,((OwnerProperty)op).getName());
                //statement.setFloat(4,((OwnerProperty)op).getPrice());
                //statement.setLong(3,((Agent)agt).getProjectId());
                statement.execute();
                System.out.println("Executed successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
