package com.flowergarden.dao.daonew.impl;

import com.flowergarden.dao.daonew.GeneralFlowerDao;
import com.flowergarden.flowers.*;
import com.flowergarden.properties.FreshnessInteger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//TODO: close connections
public class GeneralFlowerDaoImpl implements GeneralFlowerDao {
    private Connection connection;

    private static final String DELETE_BY_ID_QUERY = "DELETE FROM flower WHERE id = ?";

    private static final String SELECT_ALL_QUERY = "SELECT * FROM flower";

    private static final String SELECT_BY_ID_QUERY = SELECT_ALL_QUERY + " WHERE id=?";

    private static final String SELECT_BY_BOUQUET_ID_QUERY = SELECT_ALL_QUERY + " WHERE bouquet_id=?";

    //TODO: combine
    private static final String INSERT_QUERY = "INSERT INTO flower " + "(name, lenght, freshness, price, spike, petal, bouquet_id)" + "VALUES"
        + "(?, ?, ?, ?, ?, ?, ?)";


//    private static final String INSERT_QUERY_TULIP = "INSERT INTO flower" + "(name, lenght, freshness, price)" + "VALUES" + "(?, ?, ?, ?)";
//
//    private static final String INSERT_QUERY_CHAMOMILE = "INSERT INTO flower" + "(name, lenght, freshness, price, petal)" + "VALUES" + "(?, ?, ?, ?, ?)";


//    private static final String SAVE_FLOWER_QUERY;

    public GeneralFlowerDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public Flower findFlowerById(int id) throws SQLException {
        PreparedStatement st = connection.prepareStatement(SELECT_BY_ID_QUERY);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            return readResultSet(rs);
        }
        return null;
    }

    public List<Flower> findAllFlowers() throws SQLException {
        List<Flower> flowers = new ArrayList<>();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(SELECT_ALL_QUERY);
        while (rs.next()) {
            flowers.add(readResultSet(rs));
            System.out.println(rs.getInt("bouquet_id"));
        }
        return flowers;
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

    public void saveFlower(Flower flower, int bouquetId) throws SQLException {
        PreparedStatement st = connection.prepareStatement(INSERT_QUERY);
        populateInsertPrepareStatement(st, flower, bouquetId);
        st.executeUpdate();
    }

    public void deleteFlowerById(int id) throws SQLException {
        PreparedStatement st = connection.prepareStatement(DELETE_BY_ID_QUERY);
        st.setInt(1, id);
        st.executeUpdate();
    }

    private Flower readResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt(FlowerConstants.ID_COLUMN);
        String name = rs.getString(FlowerConstants.NAME_COLUMN);
        int length = rs.getInt(FlowerConstants.LENGTH_COLUMN);
        int freshness = rs.getInt(FlowerConstants.FRESHNESS_COLUMN);
        float price = rs.getFloat(FlowerConstants.PRICE_COLUMN);
        int petals = rs.getInt(FlowerConstants.PETAL_COLUMN);
        boolean spike = rs.getBoolean(FlowerConstants.SPIKE_COLUMN);
        FreshnessInteger freshnessInteger = new FreshnessInteger(freshness);
        return createFlowerInstance(id, name, length, freshnessInteger, price, petals, spike);
    }

    private Flower createFlowerInstance(int id, String name, int length, FreshnessInteger freshness, float price, int petals,
                                                   boolean spike) {
        switch (name) {
            case FlowerConstants.ROSE_TYPE:
                return new Rose(id, spike, length, price, freshness);
            case FlowerConstants.CHAMOMILE_TYPE:
                return new Chamomile(id, petals, length, price, freshness);
            case FlowerConstants.TULIP_TYPE:
                return new Tulip(id, length, price, freshness);
            default:
                throw new IllegalArgumentException("Couldn't create a Flower instance. Type '" + name + "' is missing.");
        }
    }

    protected void populateInsertPrepareStatement(PreparedStatement preparedStatement, Flower flower, int bouquetId)
        throws SQLException {
        preparedStatement.setString(1, flower.getClass().getSimpleName().toLowerCase());
        preparedStatement.setInt(2, flower.getLenght());
        preparedStatement.setInt(3, ((FreshnessInteger) flower.getFreshness()).getFreshness());
        preparedStatement.setFloat(4, flower.getPrice());
        if (flower instanceof Rose)
            preparedStatement.setBoolean(5, ((Rose) flower).getSpike());
        if (flower instanceof Chamomile)
            preparedStatement.setBoolean(6, ((Chamomile) flower).getPetal());
        preparedStatement.setInt(7, bouquetId);
    }

//    private String getInsertQuery(Flower flower) {
//        if (flower instanceof Rose) {
//            return INSERT_QUERY_ROSE;
//        }
//        if (flower instanceof Chamomile) {
//            return INSERT_QUERY_CHAMOMILE;
//        }
//        if (flower instanceof Tulip) {
//            return INSERT_QUERY_TULIP;
//        }
//        throw new IllegalArgumentException("Couldn't find an insert query for Class '" + flower.getClass().getCanonicalName());
//    }

    private class FlowerConstants {
        private static final String ID_COLUMN = "id";
        private static final String NAME_COLUMN = "name";
        private static final String LENGTH_COLUMN = "lenght";
        private static final String FRESHNESS_COLUMN = "freshness";
        private static final String PRICE_COLUMN = "price";
        private static final String PETAL_COLUMN = "petal";
        private static final String SPIKE_COLUMN = "spike";

        private static final String ROSE_TYPE = "rose";
        private static final String CHAMOMILE_TYPE = "chamomile";
        private static final String TULIP_TYPE = "tulip";
    }
}
