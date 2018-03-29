package com.flowergarden.dao.daonew.impl;

import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.bouquet.MarriedBouquet;
import com.flowergarden.dao.daonew.BouquetDao;
import com.flowergarden.dao.daonew.GeneralFlowerDao;
import com.flowergarden.flowers.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MarriedBouquetDaoImpl implements BouquetDao {

    private final static String DELETE_BY_ID = "DELETE FROM bouquet WHERE id = ?";

    private final static String SELECT_ALL_QUERY = "SELECT * FROM bouquet";

    private final static String SELECT_BY_ID_QUERY = SELECT_ALL_QUERY + " WHERE id =?";

    private final static String SAVE_BOUQUET_QUERY = "INSERT INTO bouquet" + "(name, assemble_price)" + " VALUES" + "(?, ?)";

    private final static String BOUQUET_NAME = "married";

    private GeneralFlowerDao generalFlowerDao;

    private Connection connection;

    public MarriedBouquetDaoImpl(Connection connection) {
        this.connection = connection;
        this.generalFlowerDao = new GeneralFlowerDaoImpl(connection);
   }

    @Override
    public void saveBouquet(Bouquet bouquet) throws SQLException {
        connection.setAutoCommit(false);
        PreparedStatement st = connection.prepareStatement(SAVE_BOUQUET_QUERY);
        st.setString(1, BOUQUET_NAME);
        st.setFloat(2, bouquet.getPrice());
        st.executeUpdate();
        List<Flower> flowers = new ArrayList(bouquet.getFlowers());
        flowers.stream().forEach(flower -> {
            try {
                generalFlowerDao.saveFlower(flower);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        connection.commit();
        connection.setAutoCommit(true);
    }

    @Override
    public MarriedBouquet findBouquetById(int id) throws SQLException {
        PreparedStatement st = connection.prepareStatement(SELECT_BY_ID_QUERY);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            return readResultSet(rs);
        }
        return null;
    }

    @Override
    public void deleteBouquetById(int id) throws SQLException {
        PreparedStatement st = connection.prepareStatement(DELETE_BY_ID);
        st.setInt(1, id);
        st.executeUpdate();
    }

    @Override
    public List<Bouquet> findAllBouquets() throws SQLException {
        List<Bouquet> bouquets = new ArrayList<>();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(SELECT_ALL_QUERY);
        while (rs.next()) {
            bouquets.add(readResultSet(rs));
        }
        return bouquets;    }

    private MarriedBouquet readResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        float assemblePrice = rs.getFloat("assemble_price");
        MarriedBouquet bouquet = new MarriedBouquet();
        bouquet.setAssembledPrice(assemblePrice);
        bouquet.setId(id);
        generalFlowerDao.findFlowersInBouquet(id).forEach(f -> bouquet.addFlower((GeneralFlower) f));
        return bouquet;
    }
}
