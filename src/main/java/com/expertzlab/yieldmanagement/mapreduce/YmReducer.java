package com.expertzlab.yieldmanagement.mapreduce;

/**
 * Created by expertzlab on 10/2/17.
 */
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.HashMap;


/**
 * Created by eldho541 on 22/9/17.
 */
public class YmReducer extends org.apache.hadoop.mapreduce.Reducer<Text,Text,Text,Text> {


    public void reduce(Text key, Iterable<Text> valueList,
                       Context con) throws IOException, InterruptedException {

        HashMap<String,HashMap<String,OwnerProperty>> hashMap = new HashMap();

        for (Text var : valueList) {
            String[] keyValueArray = var.toString().split(",");
            for(String str: keyValueArray){
                String[] keyValue = str.split("=");
                String ownerId = null;
                String opId = null;
                String opp = null;
                String opbs = null;
                String cpId = null;
                String cpp = null;
                if("oid".equals(keyValue[0])){
                    ownerId = keyValue[1];
                    hashMap.put(ownerId, new HashMap<String, OwnerProperty>());
                } else if("opid".equals(keyValue[0])){
                    opId = keyValue[1];
                    if(hashMap.get(ownerId) == null){
                        OwnerProperty op =  new OwnerProperty();
                        hashMap.get(ownerId).put(opId,op);
                    }
                } else if("opp".equals(keyValue[0])){
                    opp = keyValue[1];
                    hashMap.get(ownerId).get(opId).setPrice(opp);
                } else if("opbs".equals(keyValue[0])){
                    opbs = keyValue[1];
                    hashMap.get(ownerId).get(opId).setBookedStatus(opbs);
                } else if("cpid".equals(keyValue[0])){
                    cpId = keyValue[1];
                    HashMap<String, CompetingProperty> compPropMap = new HashMap<>();
                    compPropMap.put(cpId,new CompetingProperty());
                    hashMap.get(ownerId).get(opId).setCompPropMap(compPropMap);
                } else if("cpp".equals(keyValue[0])){
                    cpp = keyValue[1];
                    hashMap.get(ownerId).get(opId).getCompPropMap().get(cpId).setPrice(cpp);
                } else if ("cpbs".equals(keyValue[0])){
                    String cpbs = keyValue[1];
                    hashMap.get(ownerId).get(opId).getCompPropMap().get(cpId).setBookedStatus(cpbs);
                }
            }
        }
    }
}
