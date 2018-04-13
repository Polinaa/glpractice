package com.flowergarden.dao;

import com.flowergarden.flowers.Chamomile;
import com.flowergarden.flowers.Flower;
import com.flowergarden.flowers.Tulip;
import com.flowergarden.properties.FreshnessInteger;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;

public class GeneraFlowerDaoTest extends InitDummyDb {

    @Autowired
    private GeneralFlowerDao generalFlowerDao;

    @Test
    public void findFlowerById() throws SQLException {
        Flower flower = generalFlowerDao.findFlowerById(2);
        Assert.assertTrue(flower instanceof Chamomile);
        Assert.assertEquals(2, flower.getFreshness().getFreshness());
        Assert.assertEquals(2, flower.getLenght());
        Assert.assertEquals(2, flower.getPrice(), 0.01);
        Assert.assertEquals(2, ((Chamomile) flower).getPetals());
    }

    @Test
    public void findAllFlowers() throws SQLException {
        Assert.assertEquals(3, generalFlowerDao.findAllFlowers().size());
    }

    @Test
    public void findFlowersInBouquet() throws SQLException {
        Assert.assertEquals(3, generalFlowerDao.findFlowersInBouquet(1).size());
    }

    @Test
    public void saveFlower() throws SQLException {
        Tulip tulip = new Tulip(2, 4.4f, new FreshnessInteger(6));
        generalFlowerDao.saveFlower(tulip, 0);
        Flower savedTulip = generalFlowerDao.findFlowerById(4);
        Assert.assertEquals(6, savedTulip.getFreshness().getFreshness());
        Assert.assertEquals(2, savedTulip.getLenght());
        Assert.assertEquals(4.4f, savedTulip.getPrice(), 0.01);
    }

    @Test
    public void deleteFlowerById() throws SQLException {
        generalFlowerDao.deleteFlowerById(1);
        Assert.assertEquals(2, generalFlowerDao.findAllFlowers().size());
    }
}
