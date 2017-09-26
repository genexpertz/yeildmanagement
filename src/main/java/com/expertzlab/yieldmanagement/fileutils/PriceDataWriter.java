package com.expertzlab.yieldmanagement.fileutils;

import com.expertzlab.yieldmanagement.models.Price;

import java.sql.Connection;
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
            PriceService ar = new PriceService(con);
            list = ar.getRandomizedList(list);
            for(Object pri :list) {
                System.out.println("Price -"+pri);
                System.out.println("In new thread");
                PreparedStatement statement = con.prepareStatement("insert into price_list(ownid,cpid,opid,Did,price) values(?,?,?,?,?) ");
                statement.setLong(1,((Price)pri).getOid());
                statement.setLong(2,((Price)pri).getCpid());
                statement.setLong(3,((Price)pri).getOpid());
                statement.setInt(4,((Price)pri).getDid());
                statement.setFloat(5,((Price)pri).getPrice());

                //statement.setLong(3,((Agent)agt).getProjectId());
                statement.execute();
                System.out.println("Executed successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
