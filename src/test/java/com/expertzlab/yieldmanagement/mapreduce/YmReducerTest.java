package com.expertzlab.yieldmanagement.mapreduce;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by admin on 02/10/17.
 */
public class YmReducerTest {

    YmReducer ymReducer;
    Reducer.Context context;
    Text value;
    ReduceDriver<Text,Text,Text,Text> reduceDriver;

    @Before
    public void setUp(){
        ymReducer = new YmReducer();
        context = mock(Reducer.Context.class);
        reduceDriver = ReduceDriver.newReduceDriver(ymReducer);

    }

    @Test
    public void testYmReducer_OID_OPID_OPP() throws IOException, InterruptedException {
        setUp();
        List<Text> values = new ArrayList<Text>();
        values.add(new Text("oid=1,opid=1,opp=10693"));
        reduceDriver.withInput(new Text("1"), values);
        //reduceDriver.withOutput(new Text("6"), new IntWritable(2));
        reduceDriver.runTest();
    }

    @Test
    public void testYmReducer_OID_CPID_CPP() throws IOException, InterruptedException {
        setUp();
        List<Text> values = new ArrayList<Text>();
        values.add(new Text("oid=1,cpid=1,cpp=10693"));
        reduceDriver.withInput(new Text("1"), values);
        //reduceDriver.withOutput(new Text("6"), new IntWritable(2));
        reduceDriver.runTest();
    }
}
