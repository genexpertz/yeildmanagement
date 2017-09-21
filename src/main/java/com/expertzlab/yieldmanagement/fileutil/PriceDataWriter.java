package com.expertzlab.yieldmanagement.fileutil;

import com.expertzlab.yieldmanagement.models.Price;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by expertzlab on 8/16/17.
 */

public class PriceDataWriter extends Thread{
    Connection con;
    List<Object> list;


    public PriceDataWriter(Connection con, List<Object> list)
    {
        this.con = con;
        this.list = list;

    }
    public void run()
    {

        try {
            PriceRandomizer ar = new PriceRandomizer(con);
            list = ar.getRandomizedList(list);
            for(Object pri :list) {
                System.out.println("Price -"+pri);
                System.out.println("In new thread");
                PreparedStatement statement = con.prepareStatement("insert into price values(?,?) ");
                statement.setLong(1,((Price)pri).getPropertyId());
                statement.setDate(2, (Date) ((Price)pri).getDate());
                statement.setFloat(3,((Price)pri).getPrice());

                //statement.setLong(3,((Agent)agt).getProjectId());
                statement.execute();
                System.out.println("Executed successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
