package com.expertzlab.yieldmanagement.fileutils;

import java.util.Random;

/**
 * Created by gireeshbabu on 26/09/17.
 */
public class PriceRandomizer {

    int maxOwnerPrice = 15000;
    int threshodForCompProp = 2000;

    Random random = new Random();

    public PriceRandomizer(){

    }

    public int getOwnerPrice(){
        return random.nextInt(maxOwnerPrice);
    }

    public int getCompPropPrice(int owerPrice){
        int posORneg = (Math.random() < .5) ? -1 : 1;
        return owerPrice + posORneg * random.nextInt(threshodForCompProp);
    }

}
