package com.expertzlab.yieldmanagement.fileutils.owner;

import com.expertzlab.yieldmanagement.genutils.DBConnectionManager;
import com.expertzlab.yieldmanagement.models.Owner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by gireeshbabu on 07/10/17.
 */
public class OwnerDataReaderTest {

    OwnerDataReader dataReader;

    @Before
    public void setup() throws SQLException {
        dataReader = new OwnerDataReader(DBConnectionManager.getConnection());

    }

    @Test
    public void testOwnerCount() throws SQLException {
       Assert.assertTrue( dataReader.getAllOwnerCount() > 0 );
    }

    @Test
    public void testGet() throws SQLException {
       Owner owner = dataReader.get(1);
       Assert.assertNotNull(owner);
    }
}
