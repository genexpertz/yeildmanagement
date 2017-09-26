package com.expertzlab.yieldmanagement.fileutils;

import com.expertzlab.yieldmanagement.fileutils.ownerproperty.OwnerPropertyDataReader;
import com.expertzlab.yieldmanagement.models.Owner;
import com.expertzlab.yieldmanagement.models.OwnerProperty;
import com.expertzlab.yieldmanagement.models.Price;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by expertzlab on 9/6/17.
 */
public class PriceRandomizer {
    int pos1;
    int pos2;
    Connection con;

    int recordcount =10;
    long lastId = 1;
    OwnerPropertyDataReader opD;
    CompatencyPropertyDataReader  CompatencyPropertyDataReader;
    DateDataReader DateDataReader;

    public PriceRandomizer(Connection con ) throws SQLException {
        this.con = con;
        OwnerPropertyDataReader opReader = new OwnerPropertyDataReader(con);
        CompatencyPropertyDataReader coReader = new   CompatencyPropertyDataReader(con);
        DateDataReader dReader = new DateDataReader;
        Statement stmt = con.createStatement();
       // ResultSet res = stmt.executeQuery("Select max(cpid) from compatency_property_list";

      //  while (res.next()){
        //    lastId = res.getLong(1);
      //  }
    }
    public List getRandomizedList(List list) throws SQLException {
        List l1 = new ArrayList(recordcount);
        Random r = new Random();
        DateDataReader.getAllDateList();
        CompatencyPropertyDataReader.getAllCompatencyPropertyList();
        OwnerPropertyDataReader.getAllOwnerPropertyList();
        while (OwnerPropertyDataReader.hasNext()){
            OwnerProperty op = OwnerPropertyDataReader.get();
            int ownerid = op.getOwnerId();
        }

        for (long i = lastId+1; i <= recordcount; i++) {

            Random r = new Random();
            pos1 = r.nextInt(list.size());
            Price p1 = (Price) list.get(pos1);
            pos2 = r.nextInt(list.size());
            Price p2 = (Price) list.get(pos2);
            Price p3 = new Price();
            p3.setCpid(op.getPropertyId());
            p3.setName(p1.getName() + " " + p2.getName() + pos1);
            // p3.setProjectId(pos1 > pos2 ? p1.getProjectId() : p2.getProjectId());
            l1.add(p3);
        }

        return l1;
    }
}
