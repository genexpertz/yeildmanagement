package com.expertzlab.yieldmanagement.fileutils.propertymanager;

import com.expertzlab.yieldmanagement.models.PropertyManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by expertzlab on 8/16/17.
 */

public class PropertyManagerDataWriter{
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
            ar.writeRandomizedList(list);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
