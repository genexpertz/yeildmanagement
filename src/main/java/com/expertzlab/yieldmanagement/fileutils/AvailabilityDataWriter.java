package com.expertzlab.yieldmanagement.fileutils;

import com.expertzlab.yieldmanagement.models.Availability;
import sun.security.x509.AVA;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by expertzlab on 9/6/17.
 */

public class AvailabilityDataWriter{
    Connection con;
    List<Object> list;


    public AvailabilityDataWriter(Connection con) {
        this.con = con;

    }
    public void execute(Availability avail) {
        String dateInsertSQL = "insert into availability_list values(?,?,?,?,?)";

        try {
            PreparedStatement stmt = con.prepareStatement(dateInsertSQL);
            stmt.setInt(1,avail.getOid());
            stmt.setInt(2,avail.getOpid());
            stmt.setInt(3,avail.getCpid());
            stmt.setInt(4,avail.getDid());
            stmt.setString(5,avail.getStatus());
            stmt.execute();
            stmt.close();
            System.out.println("Owner id:"+avail.getOid()+", owPropid:"+avail.getOpid()+", copPropid:"+avail.getCpid());
            if(avail.getOpid() < 1) {
                System.out.println("Writing book status "+avail.getStatus()+" for Comp pro - "+avail.getStatus() );
            } else {
                System.out.println("Writing book status "+avail.getStatus()+" for Own pro - "+avail.getStatus());
            }

        } catch (SQLException e) {
            System.out.println("Error in writing Availability");
        }
    }
}
