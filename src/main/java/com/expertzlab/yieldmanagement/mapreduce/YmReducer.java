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
import java.util.Map;
import java.util.Set;


/**
 * Created by eldho541 on 22/9/17.
 * The output for price of this reducer will be used in calendar.
 * In the format date,oid,opid,opp,opbs,cpap,cpabs
 * cpap - competing property average price
 * cpabs - competing property average booking percentage
 */
public class YmReducer extends org.apache.hadoop.mapreduce.Reducer<Text,Text,Text,Text> {


    public void reduce(Text key, Iterable<Text> valueList,
                       Context con) throws IOException, InterruptedException {

        HashMap<String,HashMap<String,OwnerProperty>> hashMap = new HashMap();

        String date = null;
        for (Text var : valueList) {
            String[] keyValueArray = var.toString().split(",");
            String ownerId = null;
            String opId = null;
            String opp = null;
            String opbs = null;
            String cpId = null;
            String cpp = null;
            for(String str: keyValueArray){
                String[] keyValue = str.split("=");
                if("date".equals(keyValue[0])){
                    date = keyValue[1];
                } else if("oid".equals(keyValue[0])){
                    ownerId = keyValue[1];
                    hashMap.put(ownerId, new HashMap<String, OwnerProperty>());
                } else if("opid".equals(keyValue[0])){
                    opId = keyValue[1];
                    if(hashMap.get(ownerId) != null){
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
        postProcess(hashMap, con,date);
    }

     private void postProcess(HashMap<String, HashMap<String, OwnerProperty>> hashMap,Context context, String date) throws IOException, InterruptedException {

        String[] result = new String[2];
        String compPropAvgPrice = null;
        String compAvgBookStat = null;

        int compPropPriceSum = 0;
        int compPropPriceCount = 0;
        int compPropBookStatCount = 0;

        Set<Map.Entry<String,HashMap<String,OwnerProperty>>> ownerEntries = hashMap.entrySet();
        for(Map.Entry<String,HashMap<String,OwnerProperty>> ownerE: ownerEntries ) {
            String ownerId = ownerE.getKey();

            Set<Map.Entry<String,OwnerProperty>> ownerPropEntries = ownerE.getValue().entrySet();
            for(Map.Entry<String,OwnerProperty> ownPropE: ownerPropEntries) {
                String opId = ownPropE.getKey();
                String opp = ownPropE.getValue().getPrice();
                String opbs = ownPropE.getValue().getBookedStatus();
                Set<Map.Entry<String, CompetingProperty>> compEntries = hashMap.get(ownerId).get(opId).getCompPropMap().entrySet();
                for (Map.Entry compE : compEntries) {
                    int compProPrice = Integer.parseInt(((CompetingProperty) compE.getValue()).getPrice());
                    String compPropBookStatus = ((CompetingProperty) compE.getValue()).getBookedStatus();
                    if ("yes".equals(compPropBookStatus)) {
                        compPropBookStatCount++;
                    }
                    if (compProPrice > 0) {
                        compPropPriceCount++;
                        compPropPriceSum += compProPrice;
                    }
                }
                compPropAvgPrice = getAveragePrice(compPropPriceSum, compPropPriceCount);
                compAvgBookStat = getBookingStatPercentage(compPropPriceCount, compPropBookStatCount);
                //oid,opid,opp,opbs,cpap,cpabs
                String valueString = ","+ownerId+","+opId+","+opp+","+opbs+","+compPropAvgPrice+","+compAvgBookStat;
                context.write(new Text(date),new Text(valueString));
            }
        }

    }

    private String getAveragePrice(int priceSum, int priceCount) {
        String compPropAvgPrice;
        int averagePrice = priceSum/priceCount;
        compPropAvgPrice = String.valueOf(averagePrice);
        return compPropAvgPrice;
    }

    private String getBookingStatPercentage(int priceCount, int bookStatCount) {
        String compAvgBookStat;
        int bookStatPercentage = (bookStatCount/priceCount)*100;
        compAvgBookStat = String.valueOf(bookStatPercentage);
        return compAvgBookStat;
    }
}
