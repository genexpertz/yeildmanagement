package com.expertzlab.yieldmanagement.fileutils.propertymanager;

import com.expertzlab.yieldmanagement.genutils.DBConnectionManager;
import com.expertzlab.yieldmanagement.models.PropertyManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;

/**
 * Created by expertzlab on 9/24/17.
 */
public class PropertyManagerDataReaderTest {

    PropertyManagerDataReader dataReader;

    Connection con;

    @Before
    public void setUp() throws SQLException {
      con =  DBConnectionManager.getConnection();
      dataReader = new PropertyManagerDataReader(con);
    }

    @Test
    public void testPropertyManagerDataReader() throws SQLException {
        assertNotNull(dataReader);
        dataReader.getAllPropertyManagerList();
        assertNotNull(dataReader.res);
        Assert.assertTrue(dataReader.hasNext());
        PropertyManager pm = dataReader.get();
        assertNotNull(pm);
        System.out.println("Region-"+pm.getRegion());
        System.out.println("Name-"+pm.getName());
        System.out.println("Contact-"+pm.getContact());
        System.out.println("Id-"+pm.getManagerId());
    }
}
