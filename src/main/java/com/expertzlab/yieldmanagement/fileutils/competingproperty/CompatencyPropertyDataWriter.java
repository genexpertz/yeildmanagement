package com.expertzlab.yieldmanagement.fileutils.competingproperty;

import com.expertzlab.yieldmanagement.models.CompetantProperty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by expertzlab on 9/6/17.
 */

public class CompatencyPropertyDataWriter{
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
            for(Object com :list) {
                System.out.println("CompetantProperty-"+com);
                System.out.println("In new thread");
                PreparedStatement statement = con.prepareStatement("insert into compatency_property_list(cpid,opid,region,name) values(?,?,?,?) " );
                statement.setLong(1,((CompetantProperty)com).getCpid());
                statement.setLong(2,((CompetantProperty)com).getOpid());
                statement.setString(3,((CompetantProperty)com).getRegion());
                statement.setString(4,((CompetantProperty)com).getName());

                //statement.setLong(3,((Agent)agt).getProjectId());
                statement.execute();
                System.out.println("Executed successfully");
            }
            list.clear();
            list = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
