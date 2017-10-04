package com.expertzlab.yieldmanagement.fileutils;

import com.expertzlab.yieldmanagement.fileutils.competingproperty.CompatencyPropertyDataReader;
import com.expertzlab.yieldmanagement.fileutils.owner.OwnerDataReader;
import com.expertzlab.yieldmanagement.fileutils.ownerproperty.OwnerPropertyDataReader;
import com.expertzlab.yieldmanagement.models.*;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by gireeshbabu on 26/09/17.
 */
public class AvailabilityService {

    Connection con;
    int recordcount = 10;
    long lastId = 1;
    OwnerDataReader oDr;
    OwnerPropertyDataReader opDr;
    CompatencyPropertyDataReader cpDr;
    DateDataReader dtDr;
    AvailabilityRandomizer availRand;
    PriceDataWriter priceDwr;
    AvailabilityDataWriter availDwr;

    public AvailabilityService(Connection con) throws SQLException {
        this.con = con;
        oDr = new OwnerDataReader(con);
        opDr = new OwnerPropertyDataReader(con);
        cpDr = new CompatencyPropertyDataReader(con);
        dtDr = new DateDataReader(con);
        availRand = new AvailabilityRandomizer();
        priceDwr = new PriceDataWriter(con);
        availDwr = new AvailabilityDataWriter(con);
    }

    public void generateAvailability() throws SQLException {

        //Getting owners
        Owner owner = null;
        oDr.getAllOwnerList();
        while (oDr.hasNext()) {
            owner = oDr.get();

            //Get owner Property
            opDr.getAllOwnerPropertyList(owner.getId());
            OwnerProperty ownerProperty = null;
            while (opDr.hasNext()) {
                ownerProperty = opDr.get();

                //Get Dates
                dtDr.getAllDateList();
                Availability avail = new Availability();
                while (dtDr.hasNext()) {

                    //Setting Owner P Availability
                    YMDate dt = dtDr.get();

                    avail.setOid(owner.getId());
                    avail.setOpid(ownerProperty.getPropertyId());
                    avail.setDid(dt.getId());
                    avail.setCpid(0);
                    String bookStatus = availRand.isBooked();
                    avail.setStatus(bookStatus);
                    availDwr.execute(avail);


                    //Get competing Property
                    cpDr.getAllCompatencyPropertyList(owner.getId());
                    CompetantProperty ownercp = null;
                    while (cpDr.hasNext()) {
                        ownercp = cpDr.get();

                        //Setting Owner P price
                        avail.setOpid(0);
                        avail.setOid(owner.getId());
                        avail.setCpid(ownercp.getCpid());
                        avail.setDid(dt.getId());
                        String bookedflag = availRand.isBooked();
                        avail.setStatus(bookedflag);
                        availDwr.execute(avail);

                    }

                }

            }

        }

    }
}
