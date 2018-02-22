package com.expertzlab.yieldmanagement.fileutils.competingproperty;

import com.expertzlab.yieldmanagement.fileutils.CountConfig;
import com.expertzlab.yieldmanagement.fileutils.ownerproperty.OwnerPropertyDataReader;
import com.expertzlab.yieldmanagement.genutils.RandomNumGenerator;
import com.expertzlab.yieldmanagement.models.CompetantProperty;
import com.expertzlab.yieldmanagement.models.OwnerProperty;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by expertzlab on 9/6/17.
 */
public class CompatencyPropertyRandomizer {
    int pos1;
    int pos2;
    Connection con;

    int maxCompatencyPropertyCount = CountConfig.COMPETING_PROPERTY_COUNT;
    int recordcount = 1;
    long lastId = 0;
    OwnerPropertyDataReader opDataReader;

    public CompatencyPropertyRandomizer(Connection con) throws SQLException {
        this.con = con;
        opDataReader = new OwnerPropertyDataReader(con);

        Statement stmt = con.createStatement();
        ResultSet res = stmt.executeQuery("Select max(cpid) from compatency_property_list");
        while (res.next()) {
            lastId = res.getLong(1);
        }
    }

    public void generateCompetancyProperties(List list) throws SQLException {

        recordcount = (int)lastId +1;
        Random r = new Random();
        OwnerProperty ownerProperty = null;
        int ownPropCount = opDataReader.getAllOwnerPropertyCount();
        for(int opc=1; opc <= ownPropCount; opc++ ) {
            //ownerProperty = opDataReader.get(opc);

            int cpCount = r.nextInt(maxCompatencyPropertyCount);
            if(cpCount <1) cpCount = 1;
            else
            if(cpCount > maxCompatencyPropertyCount) cpCount = maxCompatencyPropertyCount;

            for (int i = 1; i <= cpCount; i++) {

                pos1 = RandomNumGenerator.getRandomPosition(list.size(), r);
                CompetantProperty p1 = (CompetantProperty) list.get(pos1);
                pos2 = RandomNumGenerator.getRandomPosition(list.size(), r);
                CompetantProperty p2 = (CompetantProperty) list.get(pos2);
                CompetantProperty p3 = new CompetantProperty();
                p3.setCpid(recordcount);
                p3.setName(p1.getName() + " " + p2.getName() + r.nextInt(((int) (recordcount + lastId))));
                p3.setRegion("Same as OP");
                p3.setOpid(opc);

                saveCompetancyProperty(p3);
                recordcount++;
            }
        }

    }

    private void saveCompetancyProperty(CompetantProperty p3) throws SQLException {
        System.out.println("CompetantProperty-"+p3);
        System.out.println("In new thread");
        PreparedStatement statement = con.prepareStatement("insert into compatency_property_list(cpid,opid,region,name) values(?,?,?,?) " );
        statement.setLong(1,p3.getCpid());
        statement.setLong(2,p3.getOpid());
        statement.setString(3,p3.getRegion());
        statement.setString(4,p3.getName());

        //statement.setLong(3,((Agent)agt).getProjectId());
        statement.execute();
        statement.close();
        System.out.println("Executed successfully");
    }
}

