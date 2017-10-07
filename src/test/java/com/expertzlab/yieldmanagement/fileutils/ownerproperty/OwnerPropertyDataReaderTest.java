package com.expertzlab.yieldmanagement.fileutils.ownerproperty;

import com.expertzlab.yieldmanagement.fileutils.owner.OwnerDataReader;
import com.expertzlab.yieldmanagement.genutils.DBConnectionManager;
import com.expertzlab.yieldmanagement.models.Owner;
import com.expertzlab.yieldmanagement.models.OwnerProperty;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by gireeshbabu on 07/10/17.
 */
public class OwnerPropertyDataReaderTest {

    OwnerPropertyDataReader dataReader;

    @Before
    public void setup() throws SQLException {
        dataReader = new OwnerPropertyDataReader(DBConnectionManager.getConnection());

    }

    @Test
    public void testOwnerCount() throws SQLException {
        Assert.assertTrue( dataReader.getAllOwnerPropertyCount(1) > 0 );
    }

    @Test
    public void testGet() throws SQLException {
        OwnerProperty ownerProperty = dataReader.get(1,1);
        Assert.assertNotNull(ownerProperty);
    }

}
