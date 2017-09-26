package com.expertzlab.yieldmanagement.fileutils;

import com.expertzlab.yieldmanagement.fileutils.owner.OwnerDataReader;
import com.expertzlab.yieldmanagement.fileutils.ownerproperty.OwnerPropertyDataReader;
import com.expertzlab.yieldmanagement.models.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by expertzlab on 9/6/17.
 */
public class PriceService {

    Connection con;
    int recordcount =10;
    long lastId = 1;
    OwnerDataReader oDr;
    OwnerPropertyDataReader opDr;
    CompatencyPropertyDataReader  cpDr;
    DateDataReader dtDr;

    public PriceService(Connection con) throws SQLException {
        this.con = con;
        oDr = new OwnerDataReader(con);
        opDr = new OwnerPropertyDataReader(con);
        cpDr = new   CompatencyPropertyDataReader(con);
        dtDr = new DateDataReader(con);
    }

    public void generatePrices() throws SQLException {

        //Getting owners
        Owner owner = null;
        oDr.getAllOwnerList();
        while(oDr.hasNext()){
           owner = oDr.get();

           //Get owner Property
            opDr.getAllOwnerPropertyList(owner.getId());
            OwnerProperty ownerProperty = null;
            while(opDr.hasNext()){
                ownerProperty = opDr.get();

                //Get competing Property
                cpDr.getAllCompatencyPropertyList(owner.getId());
                CompetantProperty ownercp = null;
                while(cpDr.hasNext()){
                    ownercp = cpDr.get();

                    //Get Dates
                dtDr.getAllDateList();
                while(dtDr.hasNext()){
                    YMDate dt = dtDr.get();

                    Price price = new Price();
                    //price.setCpid();




                }


                }

            }

        }

    }

}
