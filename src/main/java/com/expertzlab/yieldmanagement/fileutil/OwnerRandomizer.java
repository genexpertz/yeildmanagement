package com.expertzlab.yieldmanagement.fileutil;

import com.expertzlab.yieldmanagement.models.Owner;

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
public class OwnerRandomizer {
    int pos1;
    int pos2;

    int recordcount =100;
    long lastId = 0;

    public OwnerRandomizer(Connection con ) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet res = stmt.executeQuery("Select max(id) from agent");
        while (res.next()){
            lastId = res.getLong(1);
        }
    }
    public List getRandomizedList(List list) {
        List l1 = new ArrayList(recordcount);

        for (long i = lastId+1; i <= recordcount; i++) {

            Random r = new Random();
            pos1 = r.nextInt(list.size());
            Owner p1 = (Owner) list.get(pos1);
            pos2 = r.nextInt(list.size());
            Owner p2 = (Owner) list.get(pos2);
            Owner p3 = new Owner();
            p3.setId(i);
            p3.setName(p1.getName() + " " + p2.getName() + pos1);
            // p3.setProjectId(pos1 > pos2 ? p1.getProjectId() : p2.getProjectId());
            l1.add(p3);
        }

        return l1;
    }
}
