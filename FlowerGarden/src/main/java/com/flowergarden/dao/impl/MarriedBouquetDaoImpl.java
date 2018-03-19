package com.flowergarden.dao.impl;

import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.bouquet.MarriedBouquet;
import com.flowergarden.dao.BouquetDao;
import com.flowergarden.flowers.GeneralFlower;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MarriedBouquetDaoImpl implements BouquetDao {

    private final static String DELETE_BY_ID = "DELETE FROM bouquet WHERE id = ?";

    private final static String SELECT_ALL_QUERY = "select * from bouquet";

    private static RoseDaoImpl roseDao;
    private static ChamomileDaoImpl chamomileDao;
    private static TulipDaoImpl tulipDao;

    private Connection connection;

    protected static String SAVE_FLOWER_QUERY;

    public MarriedBouquetDaoImpl(Connection connection) {
        this.connection = connection;
        roseDao = new RoseDaoImpl(connection);
        chamomileDao = new ChamomileDaoImpl(connection);
        tulipDao = new TulipDaoImpl(connection);
    }

    @Override
    public void saveBouquet(Bouquet bouquet) {
//        try {
//            PreparedStatement st = connection.prepareStatement(SAVE_FLOWER_QUERY);
//            preparedStatement.setString(1, type);
//            preparedStatement.setInt(2, flower.getLenght());
//            preparedStatement.setInt(3, ((FreshnessInteger) flower.getFreshness()).getFreshness());
//            preparedStatement.setFloat(4, flower.getPrice());
//            st.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void deleteBouquetById(int id) {
        try {
            PreparedStatement st = connection.prepareStatement(DELETE_BY_ID);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Bouquet> findBouquets() {
        List<Bouquet> bouquets = new ArrayList<>();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(SELECT_ALL_QUERY);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                float assemblePrice = rs.getFloat("assemble_price");
                MarriedBouquet bouquet = new MarriedBouquet();
                bouquet.setAssembledPrice(assemblePrice);
                roseDao.findFlowers().forEach(f -> bouquet.addFlower((GeneralFlower) f));
                chamomileDao.findFlowers().forEach(f -> bouquet.addFlower((GeneralFlower) f));
                tulipDao.findFlowers().forEach(f -> bouquet.addFlower((GeneralFlower) f));
                bouquets.add(bouquet);
            }
//            int count = rs.getMetaData().getColumnCount();
//            for (int i = 1; i<= count; i++) {
//                System.out.println(rs.getMetaData().getColumnTypeName(i) + " " + rs.getMetaData().getColumnName(i));
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bouquets;
    }
}
