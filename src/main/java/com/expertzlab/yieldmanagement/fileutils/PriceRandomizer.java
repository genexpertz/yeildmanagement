package com.expertzlab.yieldmanagement.fileutils;

import java.util.Random;

/**
 * Created by gireeshbabu on 26/09/17.
 */
public class PriceRandomizer {

    int maxOwnerPrice = 15000;
    int threshodForCompProp = 2000;

    Random random = new Random(15000);

    public PriceRandomizer(){

    }

    public int getOwnerPrice(){
        return random.nextInt(maxOwnerPrice);
    }

    public int getCompPropPrice(int owerPrice){
        int posORneg = (Math.random() < .5) ? -1 : 1;
        int newprice = owerPrice + posORneg * random.nextInt(threshodForCompProp);
        if(newprice < 0){
            newprice = newprice * -1 + 2000;
        }
        return  newprice;
    }

}
