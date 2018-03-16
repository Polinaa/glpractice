package com.flowergarden.dao;

import com.flowergarden.bouquet.Bouquet;

import java.util.List;

public interface BouquetDao {

    void saveBouquet(Bouquet bouquet);
    void deleteBouquetById(int id);
    List<Bouquet> findBouquets();
}
