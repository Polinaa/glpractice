package com.flowergarden.dao;


import com.flowergarden.flowers.Flower;
import com.flowergarden.flowers.Rose;
import com.flowergarden.properties.FreshnessInteger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoseDao extends GeneralFlowerDao {

    {
        //TODO:length
        this.SAVE_FLOWER_QUERY = "INSERT INTO flower" + "(name, lenght, freshness, price, spike)" + "VALUES"
                               + "(?, ?, ?, ?, ?)";
    }

    public RoseDao(Connection connection) {
        super(connection);
    }

    @Override
    protected void populateInsertPrepareStatement(PreparedStatement preparedStatement, Flower flower)
        throws SQLException {
        preparedStatement.setString(1, "rose");
        preparedStatement.setInt(2, flower.getLenght());
        preparedStatement.setInt(3, ((FreshnessInteger) flower.getFreshness()).getFreshness());
        preparedStatement.setFloat(4, flower.getPrice());
        preparedStatement.setBoolean(5, ((Rose) flower).getSpike());
    }
}
