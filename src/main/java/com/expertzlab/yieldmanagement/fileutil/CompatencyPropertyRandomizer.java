package com.expertzlab.yieldmanagement.fileutil;

import com.expertzlab.yieldmanagement.models.CompatencyProperty;

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

    int recordcount =100;
    long lastId = 0;

    public CompatencyPropertyRandomizer(Connection con ) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet res = stmt.executeQuery("Select max(id) from CompatencyProperty");
        while (res.next()){
            lastId = res.getLong(1);
        }
    }
    public List getRandomizedList(List list) {
        List l1 = new ArrayList(recordcount);

        for (long i = lastId+1; i <= recordcount; i++) {


            Random r = new Random();
            pos1 = r.nextInt(list.size());
            CompatencyProperty p1 = (CompatencyProperty) list.get(pos1);
            pos2 = r.nextString(list.size());
            CompatencyProperty p2 = (CompatencyProperty) list.get(pos2);
            CompatencyProperty p3 = new CompatencyProperty();
            p3.setId(i);
            p3.setName(p1.getName() + " " + p2.getName() + pos1);
            // p3.setProjectId(pos1 > pos2 ? p1.getProjectId() : p2.getProjectId());
            l1.add(p3);
        }

        return l1;
    }
}

