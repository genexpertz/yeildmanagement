package com.expertzlab.yieldmanagement.fileutils;

import com.expertzlab.yieldmanagement.fileutils.owner.OwnerDataReader;
import com.expertzlab.yieldmanagement.fileutils.ownerproperty.OwnerPropertyDataReader;
import com.expertzlab.yieldmanagement.models.*;

import java.sql.Connection;
import java.sql.ResultSet;
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
    int recordcount = 10;
    long lastId = 1;
    OwnerDataReader oDr;
    OwnerPropertyDataReader opDr;
    CompatencyPropertyDataReader cpDr;
    DateDataReader dtDr;
    PriceRandomizer priceRand;
    PriceDataWriter priceDwr;

    public PriceService(Connection con) throws SQLException {
        this.con = con;
        oDr = new OwnerDataReader(con);
        opDr = new OwnerPropertyDataReader(con);
        cpDr = new CompatencyPropertyDataReader(con);
        dtDr = new DateDataReader(con);
        priceRand = new PriceRandomizer();
        priceDwr = new PriceDataWriter(con);
    }

    public void generatePrices() throws SQLException {

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
                while (dtDr.hasNext()) {

                    //Setting Owner P price
                    YMDate dt = dtDr.get();
                    Price price = new Price();
                    price.setOid(owner.getId());
                    price.setOpid(ownerProperty.getPropertyId());
                    price.setDid(dt.getId());
                    int ownerPrice = priceRand.getOwnerPrice();
                    price.setPrice(Float.valueOf(ownerPrice));
                    priceDwr.execute(price);

                    //Get competing Property
                    cpDr.getAllCompatencyPropertyList(owner.getId());
                    CompetantProperty ownercp = null;
                    while (cpDr.hasNext()) {
                        ownercp = cpDr.get();

                        //Setting Owner P price
                        price = new Price();
                        price.setOid(owner.getId());
                        //price.setOpid(ownerProperty.getPropertyId());
                        price.setCpid(ownercp.getCpid());
                        price.setDid(dt.getId());
                        int comPropPrice = priceRand.getCompPropPrice(ownerPrice);
                        price.setPrice(Float.valueOf(comPropPrice));
                        priceDwr.execute(price);

                    }

                }

            }

        }

    }

}
