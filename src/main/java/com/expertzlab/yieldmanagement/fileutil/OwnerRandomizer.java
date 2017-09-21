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

    int recordcount =20;
    long lastId = 0;

    public OwnerRandomizer(Connection con ) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet res = stmt.executeQuery("Select max(id) from owner");
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
            p3.setId((int)i);
            p3.setName(p1.getName() + " " + p2.getName() + r.nextInt(((int)(recordcount+lastId))));
            p3.setAddress(pos1 > pos2 ? p1.getAddress() : p2.getAddress());
            int rndNumer = r.nextInt(99999);
            p3.setAddress(p3.getAddress()+ " HN#"+rndNumer );
            p3.setContact(pos1 > pos2 ? p1.getContact() : p2.getContact());

            String contact = p3.getContact();
            if(p3.getContact() != null) {
                p3.setContact(contact.substring(0, contact.length() - ("" + rndNumer).length()-1) + rndNumer);
            }
            l1.add(p3);
        }

        return l1;
    }
}
