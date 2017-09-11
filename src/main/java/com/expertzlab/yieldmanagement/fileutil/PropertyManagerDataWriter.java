package com.expertzlab.yieldmanagement.fileutil;

import com.expertzlab.yieldmanagement.models.PropertyManager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by expertzlab on 8/16/17.
 */

public class PropertyManagerDataWriter extends Thread{
    Connection con;
    List<Object> list;


    public PropertyManagerDataWriter(Connection con, List<Object> list)
    {
        this.con = con;
        this.list = list;

    }
    public void run()
    {

        try {
            PriceRandomizer ar = new PriceRandomizer(con);
            list = ar.getRandomizedList(list);
            for(Object pmgr :list) {
                System.out.println("PropertyManager -"+pmgr);
                System.out.println("In new thread");
                PreparedStatement statement = con.prepareStatement("insert into PropertyManager values(?,?) ");
                statement.setLong(1,((PropertyManager)pmgr).getManagerID());
                statement.setString(2, ((PropertyManager)pmgr).getName());
                statement.setString(3,((PropertyManager)pmgr).getContact());
                statement.setString(3,((PropertyManager)pmgr).getRegion());

                //statement.setLong(3,((Agent)agt).getProjectId());
                statement.execute();
                System.out.println("Executed successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
