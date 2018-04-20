package com.flowergarden.dao.impl;

import com.flowergarden.dao.ConnectionProvider;
import com.flowergarden.dao.InitDB;
import com.flowergarden.util.SqliteQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class InitDBImplSqlite implements InitDB {

    @Autowired
    private ConnectionProvider connectionProvider;

    @Autowired
    private SqliteQuery sqliteQuery;

    @Override
    public void createTables() throws SQLException {
        try (Connection connection = connectionProvider.getConnection()) {
            Statement st = connection.createStatement();
            st.executeUpdate(sqliteQuery.get("create.table.bouquet"));
            st.executeUpdate(sqliteQuery.get("create.table.flower"));
        }
    }

    @Override
    public void deleteTables() throws SQLException {
        try (Connection connection = connectionProvider.getConnection()) {
            Statement st = connection.createStatement();
            st.executeUpdate(sqliteQuery.get("drop.table.bouquet"));
            st.executeUpdate(sqliteQuery.get("drop.table.flower"));
        }
    }

    @Override
    public void populateTables() throws SQLException {
        try (Connection connection = connectionProvider.getConnection()) {
            Statement st = connection.createStatement();
            st.executeUpdate(sqliteQuery.get("test.data.population"));
        }
    }
}
