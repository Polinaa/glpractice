package com.flowergarden.dao;

import com.flowergarden.flowers.Flower;

import java.util.List;

public interface FlowerDao {
    void saveFlower(Flower flower);
    void deleteFlowerById(int id);
    List<Flower> findFlowers();
}
