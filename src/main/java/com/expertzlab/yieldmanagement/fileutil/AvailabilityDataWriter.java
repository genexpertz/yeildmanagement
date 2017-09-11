package com.expertzlab.yieldmanagement.fileutil;

import com.expertzlab.yieldmanagement.models.Availability;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by expertzlab on 9/6/17.
 */

public class AvailabilityDataWriter extends Thread{
    Connection con;
    List<Object> list;


    public AvailabilityDataWriter(Connection con, List<Object> list)
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
                System.out.println("Availability -"+ava);
                System.out.println("In new thread");
                PreparedStatement statement = con.prepareStatement("insert into Availability values(?,?) ");
                statement.setDate(1, (Date) ((Availability)ava).getDate());
                statement.setString(2,((Availability)ava).getRegion());
                //statement.setLong(3,((Agent)agt).getProjectId());
                statement.execute();

                System.out.println("Executed successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
