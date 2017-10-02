package com.expertzlab.yieldmanagement.mapreduce;

/**
 * Created by expertzlab on 10/2/17.
 */

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;

import java.io.IOException;


/**
 * Created by eldho541 on 21/9/17.
 */
public class driver {
    public static void main(String args[])
    {
        driver ridriver =new driver();
        Path inp =new Path(args[0]);
        Path oup =new Path(args[1]);

    }

    public void run(Path inp,Path oup) throws IOException {
        Configuration conf =new Configuration() ;
        Job job1 = new Job();
        job1.setJarByClass(driver.class);
        job1.setMapperClass(mapper.class);
        job1.setReducerClass(reducer.class);

    }
}
