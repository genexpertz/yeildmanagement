package com.expertzlab.yieldmanagement.fileutil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by expertzlab on 8/11/17.
 */
public class LoadSampleData {
    String fileName;
    FileReader fileReader;
    Class clazz;
    File file = null;
    char[] chArray = new char[100];

    public LoadSampleData(String fileName, Class clazz) throws FileNotFoundException {

        file = new File(fileName);
        this.fileName = fileName;
        fileReader = new FileReader(file);
        this.clazz = clazz;

    }

    public List loadData() throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        List arrayList = new ArrayList();
        String header = readHeader();
        String[] array = null;
        if(header != null) {
            array = header.split("|");
        }
        Participant pt = null;
        String record = null;
        while(( record = readData()) != null) {

            //split record
            if (clazz.getName().equals("Participant")) {

                Class<?> loadedClass = Class.forName(clazz.getName());
                pt = (Participant) loadedClass.newInstance();
            }

            //pt.setName();
            //pt.setAge();
        }
        return arrayList;
    }

    private String readHeader() throws IOException {
        int rs = fileReader.read(chArray);
        if(rs > 1){
            return new String(chArray);
        } else {
            return null;
        }
    }

    private String readData() throws IOException {
        return "";
    }
}
