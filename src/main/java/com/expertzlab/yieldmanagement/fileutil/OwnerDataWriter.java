package com.expertzlab.yieldmanagement.fileutil;

import com.expertzlab.yieldmanagement.models.Owner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by expertzlab on 8/16/17.
 */

public class OwnerDataWriter extends Thread{
    Connection con;
    List<Object> list;


    public OwnerDataWriter(Connection con, List<Object> list)
    {
        this.con = con;
        this.list = list;

    }
    public void run()
    {

        try {
            CompatencyPropertyRandomizer ar = new CompatencyPropertyRandomizer(con);
            list = ar.getRandomizedList(list);
            for(Object agt :list) {
                System.out.println("Owner -"+agt);
                System.out.println("In new thread");
                PreparedStatement statement = con.prepareStatement("insert into agent values(?,?) ");
                statement.setLong(1,((Owner)agt).getId());
                statement.setString(2,((Owner)agt).getName());
                //statement.setLong(3,((Agent)agt).getProjectId());
                statement.execute();
                System.out.println("Executed successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
