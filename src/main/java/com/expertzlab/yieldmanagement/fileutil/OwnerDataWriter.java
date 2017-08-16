package com.expertzlab.yieldmanagement.fileutil;

import com.expertzlab.yieldmanagement.models.Owner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by expertzlab on 8/16/17.
 */

public class OwnerDataWriter extends DataWriter {

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
            for(Object pt : list) {

                PreparedStatement statement = con.prepareStatement("insert into Owner values(?????) ");

                statement.setString(1,((Owner)pt).getContact());

                statement.setInt(2, Integer.parseInt(((Owner)pt).getName()));
                statement.setInt(3,((Owner)pt).getId());
                statement.setString(4,((Owner)pt).getAddress());
                statement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
