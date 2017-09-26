package com.expertzlab.yieldmanagement.fileutils;

import com.expertzlab.yieldmanagement.models.Availability;

import java.sql.Connection;
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
            AvailabilityRandomizer ar = new AvailabilityRandomizer(con);
            list = ar.getRandomizedList(list);
            for(Object ava :list) {
                System.out.println("Availability -"+ava);
                System.out.println("In new thread");
                PreparedStatement statement = con.prepareStatement("insert into availability_list(cpid,opid,Did,status) values(?,?,?,?) ");
                statement.setLong(1, ( ((Availability)ava).getCpid()));
                statement.setLong(2,((Availability)ava).getOpid());
                statement.setLong(3,((Availability)ava).getDid());
                statement.setString(4,((Availability)ava).getStatus());
                //statement.setLong(3,((Agent)agt).getProjectId());
                statement.execute();

                System.out.println("Executed successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
