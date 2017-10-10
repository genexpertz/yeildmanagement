package com.expertzlab.yieldmanagement.fileutils.propertymanager;

import com.expertzlab.yieldmanagement.fileutils.CountConfig;
import com.expertzlab.yieldmanagement.genutils.RandomNumGenerator;
import com.expertzlab.yieldmanagement.models.PropertyManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by expertzlab on 9/6/17.
 */
public class PropertyManagerRandomizer {
    int pos1;
    int pos2;
    String pmIdGen;
    Connection con;
    int recordcount = 0;
    int maxpropManagerCount = CountConfig.PROPERTY_MANAGER_COUNT;
    int lastId = 0;

    public PropertyManagerRandomizer(Connection con ) throws SQLException {
        this.con = con;
        Statement stmt = con.createStatement();
        ResultSet res = stmt.executeQuery("Select max(id) from property_manager");
        while (res.next()){
            lastId = res.getInt(1);
        }
    }
    public void writeRandomizedList(List list) throws SQLException {
        recordcount = lastId+1;
        for (long i = 1; i <= maxpropManagerCount; i++) {

            Random r = new Random();
            pos1 = RandomNumGenerator.getRandomPosition(list.size(),r);
            PropertyManager p1 = (PropertyManager) list.get(pos1);
            pos2 = RandomNumGenerator.getRandomPosition(list.size(),r);
            PropertyManager p2 = (PropertyManager) list.get(pos2);
            if(p1 == null || p2 == null){
                throw new RuntimeException("Not enough value in the list");
            }
            PropertyManager pmgr = new PropertyManager();
            pmgr.setManagerId(recordcount);
            pmgr.setName(p1.getName() + " " + p2.getName() + r.nextInt(((int)(recordcount+lastId))));
            pmgr.setRegion(pos1 > pos2 ? p1.getRegion() : p2.getRegion());
            int rndNumer = r.nextInt(99999);
            pmgr.setRegion(pmgr.getRegion()+rndNumer );
            pmgr.setContact(pos1 > pos2 ? p1.getContact() : p2.getContact());

            String contact = pmgr.getContact();
            if(pmgr.getContact() != null) {
                pmgr.setContact(contact.substring(0, contact.length() - ("" + rndNumer).length()-1) + rndNumer);
            }

            savePropertyManager(pmgr);

            recordcount++;
        }
    }

    private void savePropertyManager(PropertyManager pmgr) throws SQLException {
        System.out.println("PropertyManager -"+pmgr);
        PreparedStatement statement = con.prepareStatement("insert into property_manager(id,name,contact,region) values(?,?,?,?)");
        statement.setString(1,pmgr.getManagerId());
        statement.setString(2, pmgr.getName());
        statement.setString(3,pmgr.getContact());
        statement.setString(4,pmgr.getRegion());

        //statement.setLong(3,((Agent)agt).getProjectId());
        statement.execute();
        statement.close();
        System.out.println("Executed successfully");
    }
}