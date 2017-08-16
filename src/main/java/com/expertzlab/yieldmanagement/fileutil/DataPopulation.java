package com.expertzlab.yieldmanagement.fileutil;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by expertzlab on 8/16/17.
 */public class DataPopulation {
    static Map map = new HashMap();
    static File file;
    public static void main(String[] args) throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        file = new File("classtype-datafile-mapping.properties");
        Scanner s = new Scanner(file);
        while (s.hasNext())
        {
            String line = s.nextLine();
            String[] strArray = line.split("=");
            Class clazz = Class.forName(strArray[0]);
            LoadSampleData ld = new LoadSampleData(strArray[1],clazz);
            List list = ld.loadData();
            map.put(clazz,list);
        }


    }
}


