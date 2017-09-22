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

    int recordcount =10;
    long lastId = 0;

    public OwnerPropertyRandomizer(Connection con ) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet res = stmt.executeQuery("Select max(id) from owner_property");
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
            pos2 = r.nextInt(list.size());
            OwnerProperty p2 = (OwnerProperty) list.get(pos2);
            OwnerProperty p3 = new OwnerProperty();
            p3.setPropertyId((int)i);
            p3.setName(p1.getName() + " " + p2.getName() + r.nextInt(((int)(recordcount+lastId))));
            p3.setRegion(pos1 > pos2 ? p1.getRegion() : p2.getRegion());
            int rndNumer = r.nextInt(99999);
            p3.setRegion(p3.getRegion()+ " HN#"+rndNumer );
            p3.setPropertyId(r.nextInt(20));
            //p3.setContact(pos1 > pos2 ? p1.getContact() : p2.getContact());

           // String contact = p3.getContact();
            //if(p3.getContact() != null) {
              //  p3.setContact(contact.substring(0, contact.length() - ("" + rndNumer).length()-1) + rndNumer);
            //}
            l1.add(p3);
        }

        return l1;
    }
}


