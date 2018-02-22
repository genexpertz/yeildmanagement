package com.expertzlab.yieldmanagement.fileutils;

import com.expertzlab.yieldmanagement.fileutils.competingproperty.CompetencyPropertyDataReader;
import com.expertzlab.yieldmanagement.fileutils.owner.OwnerDataReader;
import com.expertzlab.yieldmanagement.fileutils.ownerproperty.OwnerPropertyDataReader;
import com.expertzlab.yieldmanagement.models.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by gireeshbabu on 26/09/17.
 */
public class AvailabilityService {

    Connection con;
    int recordcount = 10;
    long lastId = 1;
    OwnerDataReader oDr;
    OwnerPropertyDataReader opDr;
    CompetencyPropertyDataReader cpDr;
    DateDataReader dtDr;
    AvailabilityRandomizer availRand;
    PriceDataWriter priceDwr;
    AvailabilityDataWriter availDwr;

    public AvailabilityService(Connection con) throws SQLException {
        this.con = con;
        oDr = new OwnerDataReader(con);
        opDr = new OwnerPropertyDataReader(con);
        cpDr = new CompetencyPropertyDataReader(con);
        dtDr = new DateDataReader(con);
        availRand = new AvailabilityRandomizer();
        priceDwr = new PriceDataWriter(con);
        availDwr = new AvailabilityDataWriter(con);
    }

    public void generateAvailability() throws SQLException {

        List<YMDate> dateList = dtDr.getAllDateList();
        int dateListLen = dateList.size();
        //Getting owners
        Owner owner = null;
        oDr.getAllOwnerCount();
        int owcount = oDr.getAllOwnerCount();
        for(int oc=1; oc <= owcount; oc++ ) {
            //owner = oDr.get(oc);

            //Get owner Property
            OwnerProperty ownerProperty = null;
            int ownPropCountMax = opDr.getAllOwnerPropertyCount(oc);
            int ownPropCountMin = opDr.getAllOwnerPropertyStartCount(oc);
            for(int opc=ownPropCountMin; opc <= ownPropCountMax; opc++ ) {
                //ownerProperty = opDr.get(oc,opc);

                //Get Dates

                Availability avail = new Availability();
                for (int i=0; i< dateListLen; i++) {

                    //Setting Owner P Availability
                    YMDate dt = dateList.get(i);

                    avail.setOid(oc);
                    avail.setOpid(opc);
                    avail.setDid(dt.getId());
                    avail.setCpid(0);
                    String bookStatus = availRand.isBooked();
                    avail.setStatus(bookStatus);
                    availDwr.execute(avail);


                    //Get competing Property
                    int comPropCount = cpDr.getAllCompatencyPropertyCount(opc);
                    int compPropCountStart = cpDr.getAllCompatencyPropertyStartCount(opc);
                    CompetantProperty ownercp = null;
                    for(int cpc = compPropCountStart; cpc <=comPropCount; cpc++ ){
                        //ownercp = cpDr.get(cpc);

                        //Setting Owner P Availability
                        avail.setOpid(opc);
                        avail.setOid(oc);
                        avail.setCpid(cpc);
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
