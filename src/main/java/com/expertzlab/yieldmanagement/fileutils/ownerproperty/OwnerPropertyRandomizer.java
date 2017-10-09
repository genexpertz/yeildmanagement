package com.expertzlab.yieldmanagement.fileutils.ownerproperty;

import com.expertzlab.yieldmanagement.fileutils.CountConfig;
import com.expertzlab.yieldmanagement.fileutils.owner.OwnerDataReader;
import com.expertzlab.yieldmanagement.genutils.RandomNumGenerator;
import com.expertzlab.yieldmanagement.models.Owner;
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
    int opId;

    int maxOwnerPropertyCount = CountConfig.OWNER_PROPERTY_COUNT;
    int recordcount = 1;
    long lastId = 0;
    Connection con;

    OwnerDataReader oDataReader;


    public OwnerPropertyRandomizer(Connection con) throws SQLException {
        this.con = con;
        oDataReader = new OwnerDataReader(con);
        Statement stmt = con.createStatement();
        ResultSet res = stmt.executeQuery("Select max(opid) from owner_property");
        while (res.next()) {
            lastId = res.getLong(1);
        }
    }

    public List getRandomizedList(List list) throws SQLException {
        List l1 = new ArrayList(recordcount);
        Random r = new Random();
        recordcount += lastId;
        Owner owner = null;
        int owcount = oDataReader.getAllOwnerCount();
        for(int oc=1; oc <= owcount; oc++ ) {
            owner = oDataReader.get(oc);
            int ownerPropertyCount = r.nextInt(maxOwnerPropertyCount);
            if(ownerPropertyCount < 1) ownerPropertyCount =1;
            else
                if(ownerPropertyCount > maxOwnerPropertyCount) ownerPropertyCount = maxOwnerPropertyCount;
            for (int i = 1; i <= ownerPropertyCount; i++) {

                pos1 = RandomNumGenerator.getRandomPosition(list.size(), r);
                OwnerProperty p1 = (OwnerProperty) list.get(pos1);
                pos2 = RandomNumGenerator.getRandomPosition(list.size(), r);
                OwnerProperty p2 = (OwnerProperty) list.get(pos2);
                OwnerProperty p3 = new OwnerProperty();
                p3.setPropertyId(recordcount);
                p3.setName(p1.getName() + " " + p2.getName() + r.nextInt(maxOwnerPropertyCount));
                p3.setRegion(pos1 > pos2 ? p1.getRegion() : p2.getRegion());
                int rndNumer = r.nextInt(99999);
                p3.setRegion(p3.getRegion() + "" + rndNumer);
                p3.setOwnerId(owner.getId());
                l1.add(p3);
                recordcount++;
            }
        }
        return l1;
    }


}


