package com.expertzlab.yieldmanagement.fileutils.owner;

import com.expertzlab.yieldmanagement.models.Owner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by expertzlab on 8/16/17.
 */

public class OwnerDataWriter{
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
            ar.writeRandomizedList(list);

        } catch (SQLException e) {
            throw new RuntimeException("Owner Write failed",e);
        }
        System.out.println("Owner write finished");
    }
}
