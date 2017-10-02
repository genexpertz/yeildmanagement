package com.expertzlab.yieldmanagement.mapreduce;

/**
 * Created by expertzlab on 10/2/17.
 */

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Created by eldho541 on 22/9/17.
 */
public class YmMapper extends org.apache.hadoop.mapreduce.Mapper<LongWritable,Text,Text,Text> {

    String filename;
    protected Text date_id;
    public void setup()
    {
        setup();
    }

    @Override
    protected void setup(Context context) {

        filename=((FileSplit)context.getInputSplit()).getPath().toString();
    }

    public void map(LongWritable key,Text values,Context context) throws IOException, InterruptedException {
        String value = values.toString();
        StringTokenizer stringTokenizer = new StringTokenizer(value,",");
        if(filename.contains("/price")) {
            int oid = Integer.parseInt(stringTokenizer.nextToken());
            int opid=Integer.parseInt(stringTokenizer.nextToken());
            int cpid=Integer.parseInt(stringTokenizer.nextToken());
            date_id = new Text(stringTokenizer.nextToken());
            int opp, cpp;
            if(opid > 0) {
                opp = (int) Float.parseFloat(stringTokenizer.nextToken());
                context.write(date_id,new Text("oid="+oid+",opid="+opid+",opp="+opp));
                System.out.println("show:"+date_id+",oid="+oid+",opid="+opid+",opp="+opp);
            } else {
                cpp = (int) Float.parseFloat(stringTokenizer.nextToken());
                context.write(date_id,new Text("oid="+oid+",cpid="+cpid+",cpp="+cpp));
                System.out.println("show:"+date_id+",oid="+oid+",cpid="+cpid+",cpp="+cpp);
            }
        }

        else if (filename.contains("/avilability"))
        {
            date_id = new Text(stringTokenizer.nextToken());
            int oid=Integer.parseInt(stringTokenizer.nextToken());
            int cid=Integer.parseInt(stringTokenizer.nextToken());
            /*int a= Integer.parseInt(stringTokenizer.nextToken());
            {
                if(a == 1) {
                    Boolean status = true;
                }
                else {
                    Boolean status = false;
                }*/

            String status=stringTokenizer.nextToken();
            //String avilability=stringTokenizer.nextToken();
            context.write(date_id,new Text("oid="+oid+"cpid"+cid+"cpbs"+status));
            System.out.println("show"+date_id+"oid="+oid+",cpid"+cid+"cpbs"+status);
        }
        else if(filename.contains("/date"))
        {
            date_id = new Text(stringTokenizer.nextToken());
            String date=stringTokenizer.nextToken();
            context.write(date_id,new Text("date="+date));
            System.out.println("show"+date_id+"Date="+date);

        }
    }
}