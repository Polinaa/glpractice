package com.flowergarden.dao;

import com.flowergarden.bouquet.Bouquet;

import java.rmi.UnexpectedException;
import java.sql.SQLException;
import java.util.List;

public interface BouquetDao {

    void saveBouquet(Bouquet bouquet) throws SQLException, UnexpectedException;
    Bouquet findBouquetById(int id) throws SQLException;
    void deleteBouquetById(int id) throws SQLException;
    List<Bouquet> findBouquets() throws SQLException;
}
