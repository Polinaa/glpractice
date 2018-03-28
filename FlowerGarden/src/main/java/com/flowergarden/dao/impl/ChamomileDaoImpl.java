package com.flowergarden.dao.impl;

import com.flowergarden.dao.GeneralFlowerDao;
import com.flowergarden.flowers.Chamomile;
import com.flowergarden.flowers.Flower;
import com.flowergarden.properties.FreshnessInteger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChamomileDaoImpl extends GeneralFlowerDao {

    {
        this.SAVE_FLOWER_QUERY =
            "INSERT INTO flower" + "(name, lenght, freshness, price, petal)" + "VALUES" + "(?, ?, ?, ?, ?)";
    }

    public ChamomileDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected Flower createFlowerInstance(int id, int length, FreshnessInteger freshness, float price, int petals, boolean spike) {
        return new Chamomile(id, petals, length, price, freshness);
    }

    @Override
    protected void populateInsertPrepareStatement(PreparedStatement preparedStatement, Flower flower)
        throws SQLException {
        preparedStatement.setString(1, getType());
        preparedStatement.setInt(2, flower.getLenght());
        preparedStatement.setInt(3, ((FreshnessInteger) flower.getFreshness()).getFreshness());
        preparedStatement.setFloat(4, flower.getPrice());
        preparedStatement.setBoolean(5, ((Chamomile) flower).getPetal());
    }

    @Override
    protected String getType() {
        return "chamomile";
    }
}
