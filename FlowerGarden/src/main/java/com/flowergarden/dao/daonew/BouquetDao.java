package com.flowergarden.dao.daonew;

import com.flowergarden.bouquet.Bouquet;

import java.rmi.UnexpectedException;
import java.sql.SQLException;
import java.util.List;

public interface BouquetDao {

    Bouquet findBouquetById(int id) throws SQLException;
    List<Bouquet> findAllBouquets() throws SQLException;
    void saveBouquet(Bouquet bouquet) throws SQLException, UnexpectedException;
    void deleteBouquetById(int id) throws SQLException;
}
