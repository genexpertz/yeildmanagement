package com.expertzlab.yieldmanagement.fileutils.competingproperty;

import java.sql.Connection;
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

             ar.generateCompetancyProperties(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
