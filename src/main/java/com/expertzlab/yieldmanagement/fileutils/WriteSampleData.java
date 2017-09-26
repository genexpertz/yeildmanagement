package com.expertzlab.yieldmanagement.fileutils;


import com.expertzlab.yieldmanagement.fileutils.owner.OwnerDataWriter;
import com.expertzlab.yieldmanagement.fileutils.propertymanager.PropertyManagerDataWriter;
import com.expertzlab.yieldmanagement.fileutils.ownerproperty.OwnerPropertyDataWriter;
import com.expertzlab.yieldmanagement.genutils.DBConnectionManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * Created by preethup on 11/8/17.
 */
public class WriteSampleData {

    private static final String Availability = "com.expertzlab.yieldmanagement.models.Availability";
    private static final String CompatencyProperty = "com.expertzlab.yieldmanagement.models.CompetantProperty";
    private static final String Owner = "com.expertzlab.yieldmanagement.models.Owner";
    private static final String OwnerProperty = "com.expertzlab.yieldmanagement.models.OwnerProperty";
    private static final String Price = "com.expertzlab.yieldmanagement.models.Price";
    private static final String PropertyManager = "com.expertzlab.yieldmanagement.models.PropertyManager";


    Map<Class,List> map;
    Connection con;

   public WriteSampleData(Map<Class,List> map) throws SQLException {

        this.con = DBConnectionManager.getConnection();
        this.map = map;
    }

    void writeData() {
        for (HashMap.Entry<Class, List> entry : map.entrySet()) {
            if (Availability.equals(entry.getKey())) {
                AvailabilityDataWriter pdw = new AvailabilityDataWriter(con, entry.getValue());
                pdw.start();
            } else if (CompatencyProperty.equals(entry.getKey())) {
                CompatencyPropertyDataWriter adw = new CompatencyPropertyDataWriter(con, entry.getValue());
                adw.start();
            } else if (Owner.equals(entry.getKey())) {
                OwnerDataWriter pgmdw = new OwnerDataWriter(con, entry.getValue());
                pgmdw.start();
            }else if (OwnerProperty.equals(entry.getKey())) {
                OwnerPropertyDataWriter prodw = new OwnerPropertyDataWriter(con, entry.getValue());
                prodw.start();
            }
            else if (PropertyManager.equals(entry.getKey())){
                PropertyManagerDataWriter pmdw = new PropertyManagerDataWriter(con,entry.getValue());
                pmdw.start();
            }
            }
        }
    }

