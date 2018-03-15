package com.flowergarden.dao;

import com.flowergarden.flowers.Chamomile;
import com.flowergarden.flowers.Flower;
import com.flowergarden.flowers.Rose;
import com.flowergarden.flowers.Tulip;
import com.flowergarden.properties.FreshnessInteger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class GeneralFlowerDao implements FlowerDao {

    private final static String DELETE_BY_ID = "DELETE FROM flower WHERE id = ?";

    private final static String SELECT_ALL_QUERY = "select * from flower";

    private Connection connection;

    //    //TODO:length
    protected static String SAVE_FLOWER_QUERY;
//    = "INSERT INTO flower" + "(name, lenght, freshness, price, petals, spike)" + "VALUES"
//                                       + "(?, ?, ?, ?, ?, ?)";


    public GeneralFlowerDao(Connection connection) {
        this.connection = connection;
    }

    public void saveFlower(Flower flower) {
        try {
            PreparedStatement st = connection.prepareStatement(SAVE_FLOWER_QUERY);
            populateInsertPrepareStatement(st, flower);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFlowerById(int id) {
        try {
            PreparedStatement st = connection.prepareStatement(DELETE_BY_ID);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Flower> findFlowers() {
        List<Flower> flowers = new ArrayList<>();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(SELECT_ALL_QUERY);
            while (rs.next()) {
                int id = rs.getInt("id");
                System.out.println(id);
                String name = rs.getString("name");
                //TODO: change length
                int length = rs.getInt("lenght");
                int freshness = rs.getInt("freshness");
                float price = rs.getFloat("price");
                int petals = rs.getInt("petals");
                boolean spike = rs.getBoolean("spike");
//                int bouquetId = rs.getInt("bouquet_id");
                Flower flower = createFlowerInstance(name, length, freshness, price, petals, spike);
                flowers.add(flower);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flowers;
    }

    private Flower createFlowerInstance(String type, int length, int freshness, float price, int petals,
                                        boolean spike) {
        FreshnessInteger freshnessInteger = new FreshnessInteger(freshness);
        switch (type) {
            case "rose":
                return new Rose(spike, length, price, freshnessInteger);
            case "chamomile":
                return new Chamomile(petals, length, price, freshnessInteger);
            case "tulip":
                return new Tulip(length, price, freshnessInteger);
            default:
                throw new IllegalArgumentException("Couldn't find a flower type '" + type + "'.");
        }
    }

    protected abstract void populateInsertPrepareStatement(PreparedStatement preparedStatement, Flower flower)
        throws SQLException;
}
