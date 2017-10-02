package com.expertzlab.yieldmanagement.fileutils.ownerproperty;

import com.expertzlab.yieldmanagement.fileutils.CountConfig;
import com.expertzlab.yieldmanagement.fileutils.owner.OwnerDataReader;
import com.expertzlab.yieldmanagement.fileutils.propertymanager.PropertyManagerDataReader;
import com.expertzlab.yieldmanagement.models.Owner;
import com.expertzlab.yieldmanagement.models.OwnerProperty;
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
        oDataReader.getAllOwnerList();
        if (lastId > 0) recordcount += lastId;
        while (oDataReader.hasNext()) {
            Owner o = oDataReader.get();
            int ownerCount = r.nextInt(maxOwnerPropertyCount);
            for (int i = 0; i <= ownerCount; i++) {

                pos1 = r.nextInt(list.size());
                OwnerProperty p1 = (OwnerProperty) list.get(pos1);
                pos2 = r.nextInt(list.size());
                OwnerProperty p2 = (OwnerProperty) list.get(pos2);
                OwnerProperty p3 = new OwnerProperty();
                p3.setPropertyId((int) (recordcount + lastId));
                p3.setName(p1.getName() + " " + p2.getName() + r.nextInt(((int) (recordcount + lastId))));
                p3.setRegion(pos1 > pos2 ? p1.getRegion() : p2.getRegion());
                int rndNumer = r.nextInt(99999);
                p3.setRegion(p3.getRegion() + "" + rndNumer);
                p3.setOwnerId(o.getId());
                l1.add(p3);
                recordcount++;
            }
            if (recordcount > maxOwnerPropertyCount) break;
        }
        return l1;
    }


}


