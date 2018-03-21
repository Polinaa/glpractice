package com.flowergarden.dao;

import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.flowers.Flower;

import java.sql.SQLException;
import java.util.List;

public interface FlowerDao {
    void saveFlower(Flower flower) throws SQLException;
    void deleteFlowerById(int id) throws SQLException;
    List<Flower> findFlowers() throws SQLException;
    Flower findFlowerById(int id) throws SQLException;
    Flower findFlowersInBouquet(int id) throws SQLException;
}
