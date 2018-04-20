package com.flowergarden.dao.impl;

import com.flowergarden.dao.ConnectionProvider;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class ConnectionProviderImplSqlite implements ConnectionProvider {

    private static final File DB_LOCATION = new File(".." + File.separator + "FlowerGarden" + File.separator + "flowertest.db");

    @Override
    public Connection getConnection() throws SQLException{
        String url = null;
        try {
            url = "jdbc:sqlite:" + DB_LOCATION.getCanonicalFile().toURI();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(url);
    }
}
