package com.expertzlab.yieldmanagement.fileutils;

import com.expertzlab.yieldmanagement.fileutils.ownerproperty.OwnerPropertyDataReader;
import com.expertzlab.yieldmanagement.models.CompetantProperty;
import com.expertzlab.yieldmanagement.models.OwnerProperty;

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
public class CompatencyPropertyRandomizer {
    int pos1;
    int pos2;
    Connection con;

    int maxCompatencyPropertyCount = 20;
    int recordcount = 1;
    long lastId = 0;
    OwnerPropertyDataReader opDataReader;

    public CompatencyPropertyRandomizer(Connection con) throws SQLException {
        this.con = con;
        opDataReader = new OwnerPropertyDataReader(con);

        Statement stmt = con.createStatement();
        ResultSet res = stmt.executeQuery("Select max(cpid) from compatency_property_list");
        while (res.next()) {
            lastId = res.getLong(1);
        }
    }

    public List getRandomizedList(List list) throws SQLException {
        List l1 = new ArrayList(recordcount);
        Random r = new Random();
        opDataReader.getAllOwnerPropertyList();
        OwnerProperty op = null;
        while (opDataReader.hasNext()) {
            op = opDataReader.get();

            int cpCount = r.nextInt(maxCompatencyPropertyCount);

            for (int i = 0; i <= cpCount; i++) {

                pos1 = r.nextInt(list.size());
                CompetantProperty p1 = (CompetantProperty) list.get(pos1);
                pos2 = r.nextInt(list.size());
                CompetantProperty p2 = (CompetantProperty) list.get(pos2);
                CompetantProperty p3 = new CompetantProperty();
                p3.setCpid((int) (recordcount + lastId));
                p3.setName(p1.getName() + " " + p2.getName() + r.nextInt(((int) (recordcount + lastId))));
                p3.setRegion(op.getRegion());
                p3.setOpid(op.getPropertyId());

                l1.add(p3);
                recordcount++;
            }

            if(recordcount>maxCompatencyPropertyCount) break;
        }
        return l1;

    }
}

