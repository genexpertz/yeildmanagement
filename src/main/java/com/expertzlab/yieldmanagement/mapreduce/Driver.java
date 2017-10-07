package com.expertzlab.yieldmanagement.mapreduce;

/**
 * Created by expertzlab on 10/2/17.
 */

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.Job;

import java.io.IOException;


/**
 * Created by eldho541 on 21/9/17.
 */
public class Driver {

    static Path inp = null;
    static Path oup = null;

    public static void main(String args[]) throws InterruptedException, IOException, ClassNotFoundException {

        Driver ymdriver =new Driver();
        inp =new Path(args[0]);
        oup =new Path(args[1]);
        ymdriver.run(inp, oup);

    }

    public void run(Path inp,Path oup) throws IOException, ClassNotFoundException, InterruptedException {

        Configuration conf = new Configuration();
        Job job1 = new Job(conf,"step1");
        job1.setJarByClass(Driver.class);
        job1.setMapperClass(YmMapper.class);
        job1.setReducerClass(YmReducer.class);
        job1.setMapOutputKeyClass(Text.class);
        job1.setOutputValueClass(Text.class);
        FileInputFormat.setInputPaths(job1,inp);
        FileOutputFormat.setOutputPath(job1,oup);

        if(job1.waitForCompletion(true)){
            System.out.println("Job part1 completed Successfully");
        }

    }
}
