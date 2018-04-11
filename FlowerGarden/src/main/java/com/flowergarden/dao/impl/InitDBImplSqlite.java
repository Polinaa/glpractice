package com.flowergarden.dao.impl;

import com.flowergarden.bouquet.MarriedBouquet;
import com.flowergarden.dao.BouquetDao;
import com.flowergarden.dao.InitDB;
import com.flowergarden.flowers.Chamomile;
import com.flowergarden.flowers.Rose;
import com.flowergarden.flowers.Tulip;
import com.flowergarden.properties.FreshnessInteger;

import java.rmi.UnexpectedException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class InitDBImplSqlite implements InitDB {

    private static final String CREATE_TABLES_QUERY = "CREATE TABLE IF NOT EXISTS bouquet ( " +
            "id INTEGER PRIMARY KEY, " +
            "assemble_price REAL NULL, " +
            "name TEXT NULL); ";
    private static final String CREATE_TABLES_QUERY2 = "CREATE TABLE IF NOT EXISTS flower ( " +
            "id INTEGER PRIMARY KEY, " +
            "name TEXT NULL, " +
            "lenght INTEGER NULL, " +
            "freshness INTEGER NULL, " +
            "price REAL NULL, " +
            "petal INTEGER NULL, " +
            "spike INTEGER NULL, " +
            "bouquet_id INTEGER NULL);";

    private static final String DELETE_TABLES_QUERY = "DROP TABLE IF EXISTS flower ;";
    private static final String DELETE_TABLES_QUERY2 = "DROP TABLE IF EXISTS bouquet ;";

    private Connection connection;

    private BouquetDao bouquetDao;

    public InitDBImplSqlite(Connection connection, BouquetDao bouquetDao) {
        this.connection = connection;
        this.bouquetDao = bouquetDao;
    }

    @Override
    public void createTables() throws SQLException {
        Statement st = connection.createStatement();
        st.executeUpdate(CREATE_TABLES_QUERY);
        st.executeUpdate(CREATE_TABLES_QUERY2);

    }

    @Override
    public void deleteTables() throws SQLException {
        Statement st = connection.createStatement();
        st.executeUpdate(DELETE_TABLES_QUERY);
        st.executeUpdate(DELETE_TABLES_QUERY2);
    }

    @Override
    public void populateTables() throws UnexpectedException, SQLException {
        MarriedBouquet bouquet = new MarriedBouquet();
        bouquet.setAssembledPrice(12);
        bouquet.setId(1);
        Rose rose = new Rose(true, 1, 1, new FreshnessInteger(1));
        Chamomile chamomile = new Chamomile(1, 12, 11, new FreshnessInteger(1));
        Tulip tulip = new Tulip(10, 10, new FreshnessInteger(1));
        bouquet.addFlower(tulip);
        bouquet.addFlower(chamomile);
        bouquet.addFlower(rose);
        bouquetDao.saveBouquet(bouquet);
    }
}
