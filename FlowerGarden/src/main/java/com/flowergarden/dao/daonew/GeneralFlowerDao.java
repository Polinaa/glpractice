package com.flowergarden.dao.daonew;

import com.flowergarden.flowers.Flower;

import java.sql.SQLException;
import java.util.List;

public interface GeneralFlowerDao {

    Flower findFlowerById(int id) throws SQLException;
    List<Flower> findAllFlowers() throws SQLException;
    List<Flower> findFlowersInBouquet(int id) throws SQLException;
    void saveFlower(Flower flower) throws SQLException;
    void deleteFlowerById(int id) throws SQLException;
}