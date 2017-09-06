package com.expertzlab.yieldmanagement.fileutil;

import com.expertzlab.yieldmanagement.models.Owner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by expertzlab on 9/6/17.
 */

public class CompatencyPropertyDataWriter extends Thread{
    Connection con;
    List<Object> list;


    public CompatencyPropertyDataWriter(Connection con, List<Object> list)
    {
        this.con = con;
        this.list = list;

    }
    public void run()
    {

        try {
            CompatencyPropertyRandomizer ar = new CompatencyPropertyRandomizer(con);
            list = ar.getRandomizedList(list);
            for(Object ava :list) {
                System.out.println("CompatencyProperty-"+com);
                System.out.println("In new thread");
                PreparedStatement statement = con.prepareStatement("insert into agent values(?,?) ");
                statement.setLong(1,((CompatencyProperty)com).getId());
                statement.setString(2,((CompatencyProperty)com).getName());
                //statement.setLong(3,((Agent)agt).getProjectId());
                statement.execute();
                System.out.println("Executed successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
