package com.expertzlab.yieldmanagement.fileutil;

import com.expertzlab.yieldmanagement.models.*;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

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
        CompatencyProperty com = null;
        Price pri = null;
        PropertyManager pmgr =null;
        String record = null;
        int count = 0;
        while(( record = readData()) != null) {

            String[] rArray = record.split("\\|");
            DataSetter ds = null;
            switch (clazz.getName()){
                case "com.expertzlab.surveyvi.model.Participant":{
                    ds = new ParticipantDataSetter(clazz, harray, rArray);
                    pt = (Participant) ds.run();
                    pt.setId(count);
                    arrayList.add(pt);
                    break;
                }
                case "com.expertzlab.surveyvi.model.Agent":{
                    ds = new AgentDataSetter(clazz, harray, rArray);
                    agt = (Agent) ds.run();
                    agt.setId(count);
                    arrayList.add(agt);
                    break;
                }
                case "com.expertzlab.surveyvi.model.Program":{
                    ds = new ProgramDataSetter(clazz, harray, rArray);
                    pgm = (Program) ds.run();
                    pgm.setId(count);
                    arrayList.add(pgm);
                    break;
                }
                case "com.expertzlab.surveyvi.model.Project":{
                    ds = new ProjectDataSetter(clazz, harray, rArray);
                    pro = (Project) ds.run();
                    pro.setId(count);
                    arrayList.add(pro);
                    break;
                }
                case "com.expertzlab.surveyvi.model.Company":{
                    ds = new CompanyDataSetter(clazz, harray, rArray);
                    com = (Company) ds.run();
                    com.setId(count);
                    arrayList.add(com);
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