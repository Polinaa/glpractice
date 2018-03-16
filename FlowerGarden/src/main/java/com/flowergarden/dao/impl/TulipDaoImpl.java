package com.flowergarden.dao.impl;

import com.flowergarden.dao.GeneralFlowerDao;
import com.flowergarden.flowers.Flower;
import com.flowergarden.properties.FreshnessInteger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TulipDaoImpl extends GeneralFlowerDao {
    private static final String type = "tulip";

    {
        //TODO:length
        this.SAVE_FLOWER_QUERY = "INSERT INTO flower" + "(name, lenght, freshness, price)" + "VALUES" + "(?, ?, ?, ?)";
    }

    public TulipDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected void populateInsertPrepareStatement(PreparedStatement preparedStatement, Flower flower)
        throws SQLException {
        preparedStatement.setString(1, type);
        preparedStatement.setInt(2, flower.getLenght());
        preparedStatement.setInt(3, ((FreshnessInteger) flower.getFreshness()).getFreshness());
        preparedStatement.setFloat(4, flower.getPrice());
    }
}