package com.expertzlab.yieldmanagement.fileutils;

import com.expertzlab.yieldmanagement.models.Availability;

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
public class AvailabilityRandomizer {
    int pos1;
    int pos2;

    int recordcount =100;
    long lastId = 0;

    public AvailabilityRandomizer(Connection con ) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet res = stmt.executeQuery("Select max(id) from Availability");
        while (res.next()){
            lastId = res.getLong(1);
        }
    }
    public List getRandomizedList(List list) {
        List l1 = new ArrayList(recordcount);

        for (long i = lastId+1; i <= recordcount; i++) {

            Random r = new Random();
            pos1 = r.nextInt(list.size());
            Availability p1 = (Availability) list.get(pos1);
            pos2 = r.nextInt(list.size());
            Availability p2 = (Availability) list.get(pos2);
            Availability p3 = new Availability();
            p3.setDate();
            p3.setRegion(p1.getRegion() + " " + p2.getRegion() + pos1);
            // p3.setProjectId(pos1 > pos2 ? p1.getProjectId() : p2.getProjectId());
            l1.add(p3);
        }

        return l1;
    }
}
