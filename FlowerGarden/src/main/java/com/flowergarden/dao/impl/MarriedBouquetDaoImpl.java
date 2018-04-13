package com.flowergarden.dao.impl;

import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.bouquet.MarriedBouquet;
import com.flowergarden.dao.BouquetDao;
import com.flowergarden.dao.ConnectionProvider;
import com.flowergarden.dao.GeneralFlowerDao;
import com.flowergarden.flowers.*;
import com.flowergarden.util.SqliteQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MarriedBouquetDaoImpl implements BouquetDao {

    private final static String BOUQUET_NAME = "married";

    private final static String ID = "id";

    private final static String ASSEMBLE_PRICE = "assemble_price";

    @Autowired
    private GeneralFlowerDao generalFlowerDao;

    @Autowired
    private ConnectionProvider connectionProvider;

    @Autowired
    private SqliteQuery sqliteQuery;

    @Override
    public void saveBouquet(Bouquet bouquet) throws SQLException {
        try (Connection connection = connectionProvider.getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement st = connection.prepareStatement(sqliteQuery.get("insert.bouquet"));
            st.setString(1, BOUQUET_NAME);
            st.setFloat(2, bouquet.getPrice());
            st.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
        }
    }

    @Override
    public MarriedBouquet findBouquetById(int id) throws SQLException {
        try (Connection connection = connectionProvider.getConnection()) {
            PreparedStatement st = connection.prepareStatement(sqliteQuery.get("select.bouquet.by.id"));
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return readResultSet(rs);
            }
        }
        return null;
    }

    @Override
    public List<Bouquet> findAllBouquets() throws SQLException {
        List<Bouquet> bouquets = new ArrayList<>();
        try (Connection connection = connectionProvider.getConnection()) {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sqliteQuery.get("select.bouquets"));
            while (rs.next()) {
                bouquets.add(readResultSet(rs));
            }
        }
        return bouquets;
    }

    private MarriedBouquet readResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt(ID);
        float assemblePrice = rs.getFloat(ASSEMBLE_PRICE);
        MarriedBouquet bouquet = new MarriedBouquet();
        bouquet.setAssembledPrice(assemblePrice);
        bouquet.setId(id);
        generalFlowerDao.findFlowersInBouquet(id).forEach(f -> bouquet.addFlower((GeneralFlower) f));
        return bouquet;
    }
}
