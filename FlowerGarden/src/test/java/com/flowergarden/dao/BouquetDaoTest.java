package com.flowergarden.dao;

import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.bouquet.MarriedBouquet;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.rmi.UnexpectedException;
import java.sql.SQLException;

public class BouquetDaoTest extends InitDummyDb {

    @Autowired
    private BouquetDao marriedBouquetDao;

    @Test
    public void findBouquetById() throws SQLException {
        Bouquet bouquet = marriedBouquetDao.findBouquetById(1);
        Assert.assertTrue(bouquet instanceof MarriedBouquet);
        Assert.assertEquals(12, bouquet.getAssemblePrice(), 0.01);
    }

    @Test
    public void findAllBouquets() throws SQLException {
        Assert.assertEquals(1, marriedBouquetDao.findAllBouquets().size());
    }

    @Test
    public void saveBouquet() throws SQLException, UnexpectedException {
        MarriedBouquet marriedBouquet = new MarriedBouquet();
        marriedBouquet.setAssembledPrice(2);
        marriedBouquetDao.saveBouquet(marriedBouquet);
        Assert.assertEquals(2, marriedBouquetDao.findAllBouquets().size());
        Bouquet savedBouquet = marriedBouquetDao.findBouquetById(2);
        Assert.assertTrue(savedBouquet instanceof MarriedBouquet);
        Assert.assertEquals(2, savedBouquet.getAssemblePrice(), 0.01);
    }
}
