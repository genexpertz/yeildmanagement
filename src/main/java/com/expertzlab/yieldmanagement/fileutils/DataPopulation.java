package com.expertzlab.yieldmanagement.fileutils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
/*import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;*/
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by gireeshbabu on 10/08/17.
 */
public class DataPopulation {
    static Map map = new LinkedHashMap();
    static File file;
    public static void main(String[] args) throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, SQLException {
        file = new File("classtype-datafile-mapping.properties");
        Scanner s = new Scanner(file);
        while (s.hasNext())
        {
            String line = s.nextLine();
            String[] strArray = line.split("=");
            Class clazz = Class.forName(strArray[0]);
            LoadSampleData ld = new LoadSampleData(strArray[1],clazz);
            List list = ld.loadData();
            System.out.println("Class is -"+clazz);
            System.out.println("List is -"+list);
            map.put(clazz.getName(),list);
        }
        WriteSampleData wsd = new WriteSampleData(map);
        wsd.writeData();

    }
}
