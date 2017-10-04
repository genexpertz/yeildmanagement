package com.expertzlab.yieldmanagement.mapreduce;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.junit.Before;
import org.junit.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by admin on 02/10/17.
 */

public class YmMapperTest {

    YmMapper ymMapper;
    Mapper.Context context;
    Text value;
    LongWritable key;

    @Before
    public void setUp(){
        ymMapper = new YmMapper();
        context = mock(Mapper.Context.class);
        key = new LongWritable(1000);
        FileSplit split = mock(FileSplit.class);
        Path p = new Path("hdfs://localhost:9000/users/admin/price_list/part-m-0000.txt");
        when(split.getPath()).thenReturn(p);
        when(context.getInputSplit()).thenReturn(split);
    }

    @Test
    public void testYmMapper() throws IOException, InterruptedException {
        setUp();
        //oid,opid,cpid,did,price
        value = new Text("1,6,1,198,14368.0");
       ymMapper.setup(context);
        ymMapper.map(key,value,context);
    }
}
