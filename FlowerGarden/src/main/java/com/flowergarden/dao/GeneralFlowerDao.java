package com.flowergarden.dao;

import com.flowergarden.flowers.Flower;
import com.flowergarden.properties.FreshnessInteger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class GeneralFlowerDao implements FlowerDao {

    private Connection connection;

    private String DELETE_BY_ID_QUERY = "DELETE FROM flower WHERE id = ? AND name='" + getType() + "'";

    protected String SELECT_ALL_QUERY = "SELECT * FROM flower WHERE name='" + getType() + "'";

    protected String SELECT_BY_ID_QUERY = "SELECT * FROM flower WHERE name='" + getType() + "' AND id=?";

    protected String SELECT_BY_BOUQUET_ID_QUERY = "SELECT * FROM flower WHERE name='" + getType() + "' AND bouquet_id=?";

    protected String SAVE_FLOWER_QUERY;

    public GeneralFlowerDao(Connection connection) {
        this.connection = connection;
    }

    public void saveFlower(Flower flower) throws SQLException {
        PreparedStatement st = connection.prepareStatement(SAVE_FLOWER_QUERY);
        populateInsertPrepareStatement(st, flower);
        st.executeUpdate();
    }

    public void deleteFlowerById(int id) throws SQLException {
        PreparedStatement st = connection.prepareStatement(DELETE_BY_ID_QUERY);
        st.setInt(1, id);
        st.executeUpdate();
    }

    public Flower findFlowerById(int id) throws SQLException {
        PreparedStatement st = connection.prepareStatement(SELECT_BY_ID_QUERY);
        st.setInt(1, id);
        ResultSet resultSet = st.executeQuery();
        return readResultSet(resultSet);
    }

    public List<Flower> findFlowersInBouquet(int id) throws SQLException {
        List<Flower> flowers = new ArrayList<>();
        PreparedStatement st = connection.prepareStatement(SELECT_BY_BOUQUET_ID_QUERY);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            flowers.add(readResultSet(rs));
        }
        return flowers;
    }

    public List<Flower> findFlowers() throws SQLException {
        List<Flower> flowers = new ArrayList<>();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(SELECT_ALL_QUERY);
        while (rs.next()) {
            System.out.println(rs.getInt("bouquet_id"));
            System.out.println(rs.getString("name"));
            flowers.add(readResultSet(rs));
        }
        return flowers;
    }

    private Flower readResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int length = rs.getInt("lenght");
        int freshness = rs.getInt("freshness");
        float price = rs.getFloat("price");
        int petals = rs.getInt("petals");
        boolean spike = rs.getBoolean("spike");
        FreshnessInteger freshnessInteger = new FreshnessInteger(freshness);
        return createFlowerInstance(id, length, freshnessInteger, price, petals, spike);
    }

    protected abstract Flower createFlowerInstance(int id, int length, FreshnessInteger freshness, float price, int petals,
                                                   boolean spike);

    protected abstract void populateInsertPrepareStatement(PreparedStatement preparedStatement, Flower flower)
            throws SQLException;

    protected abstract String getType();
}
