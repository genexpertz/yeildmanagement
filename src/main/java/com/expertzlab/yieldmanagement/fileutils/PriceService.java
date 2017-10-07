package com.expertzlab.yieldmanagement.fileutils;

import com.expertzlab.yieldmanagement.fileutils.competingproperty.CompetencyPropertyDataReader;
import com.expertzlab.yieldmanagement.fileutils.owner.OwnerDataReader;
import com.expertzlab.yieldmanagement.fileutils.ownerproperty.OwnerPropertyDataReader;
import com.expertzlab.yieldmanagement.models.*;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by expertzlab on 9/6/17.
 */
public class PriceService {

    Connection con;
    int recordcount = 10;
    long lastId = 1;
    OwnerDataReader oDr;
    OwnerPropertyDataReader opDr;
    CompetencyPropertyDataReader cpDr;
    DateDataReader dtDr;
    PriceRandomizer priceRand;
    PriceDataWriter priceDwr;

    public PriceService(Connection con) throws SQLException {
        this.con = con;
        oDr = new OwnerDataReader(con);
        opDr = new OwnerPropertyDataReader(con);
        cpDr = new CompetencyPropertyDataReader(con);
        dtDr = new DateDataReader(con);
        priceRand = new PriceRandomizer();
        priceDwr = new PriceDataWriter(con);
    }

    public void generatePrices() throws SQLException {

        //Getting owners
        Owner owner = null;
        int owcount = oDr.getAllOwnerCount();
        for(int oc=1; oc <= owcount; oc++ ) {
            owner = oDr.get(oc);

            //Get owner Property
            OwnerProperty ownerProperty = null;
            int ownPropCount = opDr.getAllOwnerPropertyCount(owner.getId());
            for(int opc=1; opc <= ownPropCount; opc++ ) {
                ownerProperty = opDr.get(oc,opc);

                if(ownerProperty == null){
                    continue;
                }

                //Get Dates
                dtDr.getAllDateList();
                Price price = new Price();
                while (dtDr.hasNext()) {

                    //Setting Owner P price
                    YMDate dt = dtDr.get();
                    price.setOid(owner.getId());
                    price.setOpid(ownerProperty.getPropertyId());
                    price.setDid(dt.getId());
                    price.setCpid(0);
                    int ownerPrice = priceRand.getOwnerPrice();
                    price.setPrice(Float.valueOf(ownerPrice));
                    priceDwr.execute(price);

                    //Get competing Property
                    int comPropCount = cpDr.getAllCompatencyPropertyCount(opc);
                    CompetantProperty ownercp = null;
                    for(int cpc = 1; cpc <=comPropCount; cpc++ ){
                        ownercp = cpDr.get(opc);

                        //Setting Owner P price
                        price.setOid(owner.getId());
                        price.setOpid(ownerProperty.getPropertyId());
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
