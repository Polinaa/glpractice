package com.flowergarden.dao.impl;

import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.bouquet.MarriedBouquet;
import com.flowergarden.dao.BouquetDao;
import com.flowergarden.flowers.*;

import java.rmi.UnexpectedException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MarriedBouquetDaoImpl implements BouquetDao {

    private final static String DELETE_BY_ID = "DELETE FROM bouquet WHERE id = ?";

    private final static String SELECT_ALL_QUERY = "select * from bouquet";

    private final static String SAVE_BOUQUET_QUERY = "INSERT INTO bouquet" + "(name, assemble_price)" + "VALUES" + "(?, ?)";

    private final static String BOUQUET_NAME = "married bouquet";

    private static RoseDaoImpl roseDao;
    private static ChamomileDaoImpl chamomileDao;
    private static TulipDaoImpl tulipDao;

    private Connection connection;

    public MarriedBouquetDaoImpl(Connection connection) {
        this.connection = connection;
        roseDao = new RoseDaoImpl(connection);
        chamomileDao = new ChamomileDaoImpl(connection);
        tulipDao = new TulipDaoImpl(connection);
    }

    @Override
    public void saveBouquet(Bouquet bouquet) throws SQLException, UnexpectedException {
        connection.setAutoCommit(false);
        PreparedStatement st = connection.prepareStatement(SAVE_BOUQUET_QUERY);
        st.setString(1, BOUQUET_NAME);
        st.setFloat(2, bouquet.getPrice());
        st.executeUpdate();
        List<Flower> flowers = new ArrayList(bouquet.getFlowers());
        for (Flower flower:
             flowers) {
            if (flower instanceof Rose) {
                roseDao.saveFlower(flower);
            } else if (flower instanceof Chamomile) {
                chamomileDao.saveFlower(flower);
            } else if (flower instanceof Tulip) {
                tulipDao.saveFlower(flower);
            } else {
                throw new UnexpectedException("Couldn't find flower type " + flower.getClass());
            }
        }
        connection.commit();
        connection.setAutoCommit(true);
    }

    @Override
    public void deleteBouquetById(int id) throws SQLException {
        PreparedStatement st = connection.prepareStatement(DELETE_BY_ID);
        st.setInt(1, id);
        st.executeUpdate();
    }

    @Override
    public List<Bouquet> findBouquets() throws SQLException {
        List<Bouquet> bouquets = new ArrayList<>();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(SELECT_ALL_QUERY);
        while (rs.next()) {
            int id = rs.getInt("id");
            float assemblePrice = rs.getFloat("assemble_price");
            MarriedBouquet bouquet = new MarriedBouquet();
            bouquet.setAssembledPrice(assemblePrice);
            bouquet.setId(id);
            roseDao.findFlowers().forEach(f -> bouquet.addFlower((GeneralFlower) f));
            chamomileDao.findFlowers().forEach(f -> bouquet.addFlower((GeneralFlower) f));
            tulipDao.findFlowers().forEach(f -> bouquet.addFlower((GeneralFlower) f));
            bouquets.add(bouquet);
        }
        return bouquets;
    }
}
