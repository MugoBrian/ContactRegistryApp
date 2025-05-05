package com.contactregistry.ContactRegistryApp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Explicitly load the MySQL JDBC driver
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load MySQL JDBC driver", e);
        }
    }

    private static final String URL = "jdbc:mysql://localhost:3306/contact_registry";
    private static final String USER = "root";
    private static final String PASSWORD = "Mugomuchiri@2001";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);

    }

}
