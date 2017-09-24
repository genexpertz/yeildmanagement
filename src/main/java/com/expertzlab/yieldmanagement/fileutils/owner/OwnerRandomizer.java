package com.expertzlab.yieldmanagement.fileutils.owner;

import com.expertzlab.yieldmanagement.fileutils.propertymanager.PropertyManagerDataReader;
import com.expertzlab.yieldmanagement.models.Owner;
import com.expertzlab.yieldmanagement.models.PropertyManager;

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
    Connection con;

    int maxOwnerCount =10;
    int recordcount = 1;
    long lastId = 0;
    PropertyManagerDataReader pmDataReader;

    public OwnerRandomizer(Connection con ) throws SQLException {
        this.con = con;
        pmDataReader = new PropertyManagerDataReader(con);
        Statement stmt = con.createStatement();
        ResultSet res = stmt.executeQuery("Select max(ownid) from owner");
        while (res.next()){
            lastId = res.getLong(1);
        }
    }
    public List getRandomizedList(List list) throws SQLException {
        List l1 = new ArrayList();
        Random r = new Random();
        pmDataReader.getAllPropertyManagerList();
       while(pmDataReader.hasNext()) {
           PropertyManager pm = pmDataReader.get();
           int ownerCount = r.nextInt(maxOwnerCount);
           for (int i = 0; i < ownerCount; i++) {

               pos1 = r.nextInt(list.size());
               Owner p1 = (Owner) list.get(pos1);
               pos2 = r.nextInt(list.size());
               Owner p2 = (Owner) list.get(pos2);
               Owner p3 = new Owner();
               p3.setId((int) (recordcount + lastId));
               p3.setName(p1.getName() + " " + p2.getName() + r.nextInt(((int) (recordcount + lastId))));
               p3.setAddress(pos1 > pos2 ? p1.getAddress() : p2.getAddress());
               int rndNumer = r.nextInt(99999);
               p3.setAddress(p3.getAddress() + " HN#" + rndNumer);
               p3.setContact(pos1 > pos2 ? p1.getContact() : p2.getContact());
               p3.setManagerId(pm.getManagerId());
               String contact = p3.getContact();
               if (p3.getContact() != null) {
                   p3.setContact(contact.substring(0, contact.length() - ("" + rndNumer).length() - 1) + rndNumer);
               }
               l1.add(p3);
               recordcount++;
           }
       }

        return l1;
    }
}
