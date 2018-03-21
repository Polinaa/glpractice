package com.flowergarden.dao.impl;

import com.flowergarden.dao.GeneralFlowerDao;
import com.flowergarden.flowers.Flower;
import com.flowergarden.flowers.Rose;
import com.flowergarden.properties.FreshnessInteger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoseDaoImpl extends GeneralFlowerDao {

    {
        this.SAVE_FLOWER_QUERY = "INSERT INTO flower" + "(name, lenght, freshness, price, spike)" + "VALUES"
                               + "(?, ?, ?, ?, ?)";
        this.type = "rose";
    }

    public RoseDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected Flower createFlowerInstance(int id, int length, FreshnessInteger freshness, float price, int petals, boolean spike) {
        return new Rose(id, spike, length, price, freshness);
    }

    @Override
    protected void populateInsertPrepareStatement(PreparedStatement preparedStatement, Flower flower)
        throws SQLException {
        preparedStatement.setString(1, type);
        preparedStatement.setInt(2, flower.getLenght());
        preparedStatement.setInt(3, ((FreshnessInteger) flower.getFreshness()).getFreshness());
        preparedStatement.setFloat(4, flower.getPrice());
        preparedStatement.setBoolean(5, ((Rose) flower).getSpike());
    }
}
