package com.expertzlab.yieldmanagement.mapreduce;

/**
 * Created by expertzlab on 10/2/17.
 */

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileSplit;

import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Created by eldho541 on 22/9/17.
 */
public class mapper extends Mapper<LongWritable,Text,Text,Text>{

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
            date_id = new Text(stringTokenizer.nextToken());
            int oid=Integer.parseInt(stringTokenizer.nextToken());
            int cid=Integer.parseInt(stringTokenizer.nextToken());
            int opp=Integer.parseInt(stringTokenizer.nextToken());
            int cpp=Integer.parseInt(stringTokenizer.nextToken());
            context.write(date_id,new Text("oid="+oid+",cid="+cid+",opp="+opp+",cpp="+cpp));
            System.out.println("show"+date_id+"oid="+oid+",cid="+cid+",opp="+opp+"cpp"+cpp);
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
            context.write(date_id,new Text("oid="+oid+"cid"+cid+"status"+status));
            System.out.println("show"+date_id+"oid="+oid+",cid"+cid+"status"+status);
        }
        else if(filename.contains("/date"))
        {
            date_id = new Text(stringTokenizer.nextToken());
            String date=stringTokenizer.nextToken();
            context.write(date_id,new Text("Date="+date));
            System.out.println("show"+date_id+"Date="+date);

        }
    }
}