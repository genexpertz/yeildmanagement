package com.expertzlab.yieldmanagement.fileutils.owner;

import com.expertzlab.yieldmanagement.fileutils.CountConfig;
import com.expertzlab.yieldmanagement.fileutils.propertymanager.PropertyManagerDataReader;
import com.expertzlab.yieldmanagement.genutils.RandomNumGenerator;
import com.expertzlab.yieldmanagement.models.Owner;
import com.expertzlab.yieldmanagement.models.PropertyManager;

import java.sql.*;
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

    int maxOwnerCount = CountConfig.OWNER_COUNT;
    int recordcount = 0;
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
    public void writeRandomizedList(List list) throws SQLException {
        Random r = new Random();
        pmDataReader.getAllPropertyManagerList();
        recordcount = (int)lastId +1;
       while(pmDataReader.hasNext()) {
           PropertyManager pm = pmDataReader.get();
           int ownerCount = r.nextInt(maxOwnerCount);
           if(ownerCount < 1) ownerCount = 1;
           else if(ownerCount > maxOwnerCount) ownerCount = maxOwnerCount;

           for (long i = 1; i <= ownerCount; i++) {

               pos1 = RandomNumGenerator.getRandomPosition(list.size(),r);
               Owner p1 = (Owner) list.get(pos1);
               pos2 = RandomNumGenerator.getRandomPosition(list.size(),r);
               Owner p2 = (Owner) list.get(pos2);
               Owner own = new Owner();
               own.setId(recordcount);
               own.setName(p1.getName() + " " + p2.getName() + r.nextInt(((int) (recordcount + lastId))));
               own.setAddress(pos1 > pos2 ? p1.getAddress() : p2.getAddress());
               int rndNumer = r.nextInt(99999);
               own.setAddress(own.getAddress() + " HN#" + rndNumer);
               own.setContact(pos1 > pos2 ? p1.getContact() : p2.getContact());
               own.setManagerId(pm.getManagerId());
               String contact = own.getContact();
               if (own.getContact() != null) {
                   own.setContact(contact.substring(0, contact.length() - ("" + rndNumer).length() - 1) + rndNumer);
               }
               saveOwner(own);
               recordcount++;
           }

       }
       pmDataReader.close();
    }

    private void saveOwner(Owner own) throws SQLException {
        System.out.println("Owner -"+own);
        PreparedStatement statement = con.prepareStatement("insert into owner(ownid,name,contact,address,pmid) values(?,?,?,?,?) ");
        statement.setLong(1, own.getId());
        statement.setString(2, own.getName());
        statement.setString(3, own.getContact());
        statement.setString(4, own.getAddress());
        statement.setString(5, own.getManagerId());
        statement.execute();
        statement.close();
        System.out.println("Executed successfully");
    }
}
