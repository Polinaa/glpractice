package com.flowergarden.dao.impl;

import com.flowergarden.dao.ConnectionProvider;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProviderImplSqlite implements ConnectionProvider {

    private static final File DB_LOCATION = new File("flowergarden.db");

    private static String url;

    private static Connection connection;

    public ConnectionProviderImplSqlite() throws SQLException {
        if (connection == null) {
            try {
                url = "jdbc:sqlite:" + DB_LOCATION.getCanonicalFile().toURI();
            } catch (IOException e) {
                e.printStackTrace();
            }
            connection = DriverManager.getConnection(url);
        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }
}
