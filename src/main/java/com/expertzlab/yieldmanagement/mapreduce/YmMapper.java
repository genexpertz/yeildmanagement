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
            if(isOwnerProperty(cpid)) {
                opp = (int) Float.parseFloat(stringTokenizer.nextToken());
                context.write(date_id,new Text("oid="+oid+",opid="+opid+",opp="+opp));
                System.out.println("show:"+date_id+",oid="+oid+",opid="+opid+",opp="+opp);
            } else {
                cpp = (int) Float.parseFloat(stringTokenizer.nextToken());
                context.write(date_id,new Text("oid="+oid+",opid="+opid+",cpid="+cpid+",cpp="+cpp));
                System.out.println("show:"+date_id+",oid="+oid+",opid="+opid+",cpid="+cpid+",cpp="+cpp);
            }
        }

        else if (filename.contains("/availability"))
        {
            int oid = Integer.parseInt(stringTokenizer.nextToken());
            int opid=Integer.parseInt(stringTokenizer.nextToken());
            int cpid=Integer.parseInt(stringTokenizer.nextToken());
            date_id = new Text(stringTokenizer.nextToken());
            String opbs, cpbs;
            if(opid > 0) {
                opbs = stringTokenizer.nextToken();
                context.write(date_id,new Text("oid="+oid+",opid="+opid+",opbs="+opbs));
                System.out.println("show:"+date_id+",oid="+oid+",opid="+opid+",opbs="+opbs);
            } else {
                cpbs = stringTokenizer.nextToken();
                context.write(date_id,new Text("oid="+oid+",cpid="+cpid+",cpbs="+cpbs));
                System.out.println("show:"+date_id+",oid="+oid+",cpid="+cpid+",cpbs="+cpbs);
            }
        }
        else if(filename.contains("/date"))
        {
            date_id = new Text(stringTokenizer.nextToken());
            String date=stringTokenizer.nextToken();
            context.write(date_id,new Text("date="+date));
            System.out.println("show"+date_id+"Date="+date);

        }
    }

    private boolean isOwnerProperty(int cpid) {
        return cpid == 0;
    }
}