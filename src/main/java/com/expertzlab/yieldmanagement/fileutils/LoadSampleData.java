package com.expertzlab.yieldmanagement.fileutils;

import com.expertzlab.yieldmanagement.fileutils.owner.OwnerDataSetter;
import com.expertzlab.yieldmanagement.fileutils.propertymanager.PropertyManagerDataSetter;
import com.expertzlab.yieldmanagement.models.*;
import com.expertzlab.yieldmanagement.fileutils.ownerproperty.OwnerPropertyDataSetter;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;

/**
 * Created by expertzlab on 8/11/17.
 */
public class LoadSampleData {

    String fileName;
    FileReader fileReader;
    BufferedReader bfr;
    Class clazz;
    File file = null;
    char[] chArray = new char[100];

    public LoadSampleData(String fileName, Class clazz) throws FileNotFoundException {

        file = new File(fileName);
        this.fileName = fileName;
        fileReader = new FileReader(file);
        bfr = new BufferedReader(fileReader);
        this.clazz = clazz;

    }

    public List loadData() throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        List arrayList = new ArrayList();
        String header = readHeader();
        String[] harray = null;
        if(header != null) {
            harray = header.split("\\|");
        }
        Owner own = null;
        Availability ava = null;
        CompetantProperty com = null;
        OwnerProperty op = null;
        Price pri = null;
        PropertyManager pmgr =null;
        String record = null;
        int count = 0;
        while(( record = readData()) != null) {

            String[] rArray = record.split("\\|");
            DataSetter ds = null;
            switch (clazz.getName()){
                case "com.expertzlab.yieldmanagement.models.Availability":{
                    ds = new AvailabilityDataSetter(clazz, harray, rArray);
                    ava = (Availability) ds.run();
                    arrayList.add(ava);
                    break;
                }
                case "com.expertzlab.yieldmanagement.models.CompetantProperty":{
                    ds = new CompatencyPropertyDataSetter(clazz, harray, rArray);
                    com = (CompetantProperty) ds.run();
                   // com.setCpid(count);
                    arrayList.add(com);
                    break;
                }
                case "com.expertzlab.yieldmanagement.models.Owner":{

                    ds = new OwnerDataSetter(clazz, harray, rArray);
                    own = (Owner) ds.run();
                    //own.setId(count);
                    arrayList.add(own);
                    break;
                }
                case "com.expertzlab.yieldmanagement.models.OwnerProperty":{
                    ds = new OwnerPropertyDataSetter(clazz, harray, rArray) {
                    };
                    op = (OwnerProperty) ds.run();
                    op.setPropertyId();
                    arrayList.add(op);
                    break;
                }
                case "com.expertzlab.yieldmanagement.models.price": {
                    ds = new PriceDataSetter(clazz, harray, rArray);
                    pri = (Price) ds.run();
                    //pri.setPropertyId();
                    arrayList.add(pri);
                    break;
                }
                case "com.expertzlab.yieldmanagement.models.PropertyManager":{
                    ds = new PropertyManagerDataSetter(clazz, harray, rArray);
                    pmgr = (PropertyManager) ds.run();
                    //pmgr.setManagerId(i);
                    arrayList.add(pmgr);
                    break;
                }
            }
            count++;

        }
        return arrayList;
    }

    private String readHeader() throws IOException {
        String line = bfr.readLine();
        return line;
    }

    private String readData() throws IOException {
        String line = bfr.readLine();
        return line;
    }
}