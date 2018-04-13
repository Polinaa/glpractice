package com.flowergarden.dao.impl;

import com.flowergarden.dao.ConnectionProvider;
import com.flowergarden.dao.GeneralFlowerDao;
import com.flowergarden.flowers.*;
import com.flowergarden.properties.FreshnessInteger;
import com.flowergarden.util.SqliteQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GeneralFlowerDaoImpl implements GeneralFlowerDao {

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

    @Autowired
    private ConnectionProvider connectionProvider;

    @Autowired
    private SqliteQuery sqliteQuery;

    public Flower findFlowerById(int id) throws SQLException {
        try (Connection connection = connectionProvider.getConnection()){
            PreparedStatement st = connection.prepareStatement(sqliteQuery.get("select.flower.by.id"));
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return readResultSet(rs);
            }
        }
        return null;
    }

    public List<Flower> findAllFlowers() throws SQLException {
        List<Flower> flowers = new ArrayList<>();
        try (Connection connection = connectionProvider.getConnection()) {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sqliteQuery.get("select.flowers"));
            while (rs.next()) {
                flowers.add(readResultSet(rs));
            }
        }
        return flowers;
    }

    public List<Flower> findFlowersInBouquet(int id) throws SQLException {
        List<Flower> flowers = new ArrayList<>();
        try (Connection connection = connectionProvider.getConnection()) {
            PreparedStatement st = connection.prepareStatement(sqliteQuery.get("select.flower.by.bouquet.id"));
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                flowers.add(readResultSet(rs));
            }
        }
        return flowers;
    }

    public void saveFlower(Flower flower, int bouquetId) throws SQLException {
        try (Connection connection = connectionProvider.getConnection()) {
            PreparedStatement st = connection.prepareStatement(sqliteQuery.get("insert.flower"));
            populateInsertPrepareStatement(st, flower, bouquetId);
            st.executeUpdate();
        }
    }

    public void deleteFlowerById(int id) throws SQLException {
        try (Connection connection = connectionProvider.getConnection()) {
            PreparedStatement st = connection.prepareStatement(sqliteQuery.get("delete.flower.by.id"));
            st.setInt(1, id);
            st.executeUpdate();
        }
    }

    private Flower readResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt(ID_COLUMN);
        String name = rs.getString(NAME_COLUMN);
        int length = rs.getInt(LENGTH_COLUMN);
        int freshness = rs.getInt(FRESHNESS_COLUMN);
        float price = rs.getFloat(PRICE_COLUMN);
        int petals = rs.getInt(PETAL_COLUMN);
        boolean spike = rs.getBoolean(SPIKE_COLUMN);
        FreshnessInteger freshnessInteger = new FreshnessInteger(freshness);
        return createFlowerInstance(id, name, length, freshnessInteger, price, petals, spike);
    }

    private Flower createFlowerInstance(int id, String name, int length, FreshnessInteger freshness, float price, int petals,
                                                   boolean spike) {
        switch (name) {
            case ROSE_TYPE:
                return new Rose(id, spike, length, price, freshness);
            case CHAMOMILE_TYPE:
                return new Chamomile(id, petals, length, price, freshness);
            case TULIP_TYPE:
                return new Tulip(id, length, price, freshness);
            default:
                throw new IllegalArgumentException("Couldn't create a Flower instance. Type '" + name + "' is missing.");
        }
    }

    private void populateInsertPrepareStatement(PreparedStatement preparedStatement, Flower flower, int bouquetId)
        throws SQLException {
        preparedStatement.setString(1, flower.getClass().getSimpleName().toLowerCase());
        preparedStatement.setInt(2, flower.getLenght());
        preparedStatement.setInt(3, ((FreshnessInteger) flower.getFreshness()).getFreshness());
        preparedStatement.setFloat(4, flower.getPrice());
        if (flower instanceof Rose)
            preparedStatement.setBoolean(5, ((Rose) flower).getSpike());
        else
            preparedStatement.setBoolean(5, false);

        if (flower instanceof Chamomile)
            preparedStatement.setInt(6, ((Chamomile) flower).getPetals());
        else
            preparedStatement.setInt(6, -1);
        preparedStatement.setInt(7, bouquetId);
    }
}
