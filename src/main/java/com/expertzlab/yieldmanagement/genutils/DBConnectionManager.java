package com.expertzlab.yieldmanagement.genutils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by expertzlab on 9/24/17.
 */

public class DBConnectionManager {

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/yieldmanagement", "root", "root123");
    }
}
