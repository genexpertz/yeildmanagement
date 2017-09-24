package com.expertzlab.yieldmanagement.fileutils.owner;

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
            OwnerRandomizer ar = new OwnerRandomizer(con);
            list = ar.getRandomizedList(list);
            for(Object own :list) {
                System.out.println("Owner -"+own);
                System.out.println("In new thread");
                PreparedStatement statement = con.prepareStatement("insert into owner(ownid,name,contact,address,pmid) values(?,?,?,?,?) ");
                statement.setLong(1,((Owner)own).getId());
                statement.setString(2,((Owner)own).getName());
                statement.setString(3,((Owner)own).getContact());
                statement.setString(4,((Owner)own).getAddress());
                statement.setString(5,((Owner)own).getManagerId());
                statement.execute();
                System.out.println("Executed successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
