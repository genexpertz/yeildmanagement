package com.expertzlab.yieldmanagement.mapreduce;

/**
 * Created by expertzlab on 10/2/17.
 */
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;


/**
 * Created by eldho541 on 22/9/17.
 */
public class reducer extends Reducer<Text,Text,Text,Text> {


        public void reduce(Text key, Iterable<FloatWritable> valueList,
                           Context con) throws IOException, InterruptedException {
            try {
                Float total = (float) 0;
                int count = 0;
                for (FloatWritable var : valueList) {
                    total += var.get();
                    System.out.println("reducer " + var.get());
                    count++;
                }
                Float avg = (Float) total / count;
                String out = "Total: " + total + " :: " + "Average: " + avg;
                con.write(key, new Text(out));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
