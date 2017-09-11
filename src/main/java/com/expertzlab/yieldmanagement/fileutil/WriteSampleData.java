package com.expertzlab.yieldmanagement.fileutil;


import com.expertzlab.yieldmanagement.models.CompatencyProperty;
import com.expertzlab.yieldmanagement.models.PropertyManager;

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

    private static final String Availability = "com.expertzlab.surveyvi.model.Participant";
    private static final String AGENT = "com.expertzlab.surveyvi.model.Agent";
    private static final String Owner = "com.expertzlab.surveyvi.model.Program";
    private static final String OwnerProperty = "com.expertzlab.surveyvi.model.Project";
    private static final String Price = "com.expertzlab.surveyvi.model.Company";

    Map<Class,List> map;
    Connection con;

   public WriteSampleData(Map<Class,List> map) throws SQLException {

        this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/surveyvisual", "root", "pp");
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
            else if (Price.equals(entry.getKey())) {
                PriceDataWriter cdw = new PriceDataWriter(con, entry.getValue());
                cdw.start();
            }
            else if (PropertyManager.equals(entry.getKey())){
                PropertyManagerDataWriter pmdw = new PropertyManagerDataWriter(con,entry.getValue());
                pmdw.start();
            }
            }
        }
    }

