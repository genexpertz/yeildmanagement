package com.expertzlab.yieldmanagement.fileutils;

import com.expertzlab.yieldmanagement.models.Price;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by expertzlab on 8/16/17.
 */

public class PriceDataWriter{
    Connection con;

    public PriceDataWriter(Connection con)
    {
        this.con = con;
    }
    public void execute(Price price)
    {
        String dateInsertSQL = "insert into price_list values(?,?,?,?,?)";

        try {
            PreparedStatement stmt = con.prepareStatement(dateInsertSQL);
            stmt.setInt(1,price.getOid());
            stmt.setInt(2,price.getOpid());
            stmt.setInt(3,price.getCpid());
            stmt.setInt(4,price.getDid());
            stmt.setFloat(5,price.getPrice());
            stmt.execute();

            if(price.getOpid() < 1) {
                System.out.println("Writing Price "+price.getPrice()+" for Comp pro " + price.getCpid());
            } else {
                System.out.println("Writing Price "+price.getPrice()+" for Own pro "+ price.getOpid());
            }

        } catch (SQLException e) {
            System.out.println("Error in writing Price");
        }
    }
}
