package com.flowergarden.dao.daonew.impl;

import com.flowergarden.dao.ConnectionProvider;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProviderImplSqlite implements ConnectionProvider {

    private static final File DB_LOCATION = new File("flowertest.db");

    @Override
    public Connection getConnection() {
        String url;
        try {
            url = "jdbc:sqlite:" + DB_LOCATION.getCanonicalFile().toURI();
        } catch (IOException e) {
            throw new RuntimeException("Error finding the database file", e);
        }
        try {
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }
}
