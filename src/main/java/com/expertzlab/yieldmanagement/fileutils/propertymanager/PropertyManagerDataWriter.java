package com.expertzlab.yieldmanagement.fileutils.propertymanager;

import com.expertzlab.yieldmanagement.models.PropertyManager;

import java.sql.Connection;
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
            PropertyManagerRandomizer ar = new PropertyManagerRandomizer(con);
            list = ar.getRandomizedList(list);
            for(Object pmgr :list) {
                System.out.println("PropertyManager -"+pmgr);
                System.out.println("In new thread");
                PreparedStatement statement = con.prepareStatement("insert into property_manager(id,name,contact,region) values(?,?,?,?)");
                statement.setString(1,((PropertyManager)pmgr).getManagerId());
                statement.setString(2, ((PropertyManager)pmgr).getName());
                statement.setString(3,((PropertyManager)pmgr).getContact());
                statement.setString(4,((PropertyManager)pmgr).getRegion());

                //statement.setLong(3,((Agent)agt).getProjectId());
                statement.execute();
                System.out.println("Executed successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
