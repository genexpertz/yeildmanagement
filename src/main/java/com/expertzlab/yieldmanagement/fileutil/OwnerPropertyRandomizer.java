package com.expertzlab.yieldmanagement.fileutil;

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
public class OwnerPropertyRandomizer {
    int pos1;
    int pos2;

    int recordcount =100;
    long lastId = 0;

    public OwnerPropertyRandomizer(Connection con ) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet res = stmt.executeQuery("Select max(id) from OwnerProperty");
        while (res.next()){
            lastId = res.getLong(1);
        }
    }
    public List getRandomizedList(List list) {
        List l1 = new ArrayList(recordcount);

        for (long i = lastId+1; i <= recordcount; i++) {


            Random r = new Random();
            pos1 = r.nextInt(list.size());
            OwnerProperty p1 = (OwnerProperty) list.get(pos1);
            pos2 = r.nextString(list.size());
            OwnerProperty p2 = (OwnerProperty) list.get(pos2);
            OwnerProperty p3 = new OwnerProperty();
            p3.setId(i);
            p3.setName(p1.getName() + " " + p2.getName() + pos1);
            // p3.setProjectId(pos1 > pos2 ? p1.getProjectId() : p2.getProjectId());
            l1.add(p3);
        }

        return l1;
    }
}

