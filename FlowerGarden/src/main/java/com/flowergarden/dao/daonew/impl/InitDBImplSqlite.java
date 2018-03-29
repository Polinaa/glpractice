package com.flowergarden.dao.daonew.impl;

import com.flowergarden.bouquet.MarriedBouquet;
import com.flowergarden.dao.BouquetDao;
import com.flowergarden.dao.daonew.InitDB;
import com.flowergarden.flowers.Chamomile;
import com.flowergarden.flowers.Rose;
import com.flowergarden.flowers.Tulip;
import com.flowergarden.properties.FreshnessInteger;

import java.io.File;
import java.rmi.UnexpectedException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class InitDBImplSqlite implements InitDB {

    private static final String CREATE_TABLES_QUERY = "";

    private static final String DELETE_TABLES_QUERY = "";

    private Connection connection;

    private File dbLocation;

    private BouquetDao bouquetDao;

    public InitDBImplSqlite(Connection connection, File dbLocation, BouquetDao bouquetDao) {
        this.connection = connection;
        this.dbLocation = dbLocation;
        this.bouquetDao = bouquetDao;
    }

    @Override
    public void createTable() throws SQLException {
        PreparedStatement st = connection.prepareStatement(CREATE_TABLES_QUERY);
        st.executeUpdate();
    }

    @Override
    public void deleteTable() throws SQLException {
        PreparedStatement st = connection.prepareStatement(DELETE_TABLES_QUERY);
        st.executeUpdate();
    }

    @Override
    public void populateTable() throws UnexpectedException, SQLException {
        MarriedBouquet bouquet = new MarriedBouquet();
        bouquet.setAssembledPrice(10);
        Rose rose = new Rose(true, 1, 1, new FreshnessInteger(1));
        Chamomile chamomile = new Chamomile(1, 12, 11, new FreshnessInteger(1));
        Tulip tulip = new Tulip(10, 10, new FreshnessInteger(1));
        bouquet.addFlower(tulip);
        bouquet.addFlower(chamomile);
        bouquet.addFlower(rose);
        bouquetDao.saveBouquet(bouquet);
    }
}
