package com.expertzlab.yieldmanagement.genutils;

import java.util.Random;

/**
 * Created by gireeshbabu on 09/10/17.
 */
public class RandomNumGenerator {

    public static int getRandomPosition(int size, Random r) {
        int pos = r.nextInt(size);
        if(pos > size){
            pos = size;
        } else if(pos < 1){
            pos = 1;
        }
        return pos;
    }
}
