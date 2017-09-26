package com.expertzlab.yieldmanagement.fileutils;

import com.expertzlab.yieldmanagement.models.Availability;

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
public class AvailabilityRandomizer {


    public AvailabilityRandomizer( ) throws SQLException {

    }

    public String isBooked(){
        int posORneg = (Math.random() < .5) ? -1 : 1;
        if(posORneg < 0){
            return "yes";
        }
         else {
            return "no";
        }

    }

}
