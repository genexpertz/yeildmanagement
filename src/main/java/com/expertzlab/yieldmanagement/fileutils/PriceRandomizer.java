package com.expertzlab.yieldmanagement.fileutils;



import com.expertzlab.yieldmanagement.fileutils.owner.OwnerDataReader;
import com.expertzlab.yieldmanagement.fileutils.ownerproperty.OwnerPropertyDataReader;
import com.expertzlab.yieldmanagement.models.OwnerProperty;
import com.expertzlab.yieldmanagement.models.Price;

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
public class PriceRandomizer {


    Connection con;



        while (OwnerPropertyDataReader.hasNext()){
            OwnerProperty op = OwnerPropertyDataReader.get();
            int ownerid = op.getOwnerId();
        }

        for (long i = lastId+1; i <= recordcount; i++) {

            Random r = new Random();
            pos1 = r.nextInt(list.size());
            Price p1 = (Price) list.get(pos1);
            pos2 = r.nextInt(list.size());
            Price p2 = (Price) list.get(pos2);
            Price p3 = new Price();
            p3.setCpid(.getPropertyId());
            p3.
        }

        return l1;
    }
}