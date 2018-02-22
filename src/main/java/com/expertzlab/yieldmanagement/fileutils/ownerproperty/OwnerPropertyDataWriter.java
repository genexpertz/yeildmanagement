package com.expertzlab.yieldmanagement.fileutils.ownerproperty;

import java.sql.*;
import java.sql.Connection;
import java.util.List;

/**
 * Created by expertzlab on 9/6/17.
 */


public class OwnerPropertyDataWriter{
    Connection con;
    List<Object> list;
    String pmid;
    String region;


    public OwnerPropertyDataWriter(Connection con, List<Object> list) {
        this.con = con;
        this.list = list;

    }


    public void run() {


        try {
                OwnerPropertyRandomizer ar = new OwnerPropertyRandomizer(con);
                ar.generateOwnerProperty(list);

            }  catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
