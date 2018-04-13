package com.flowergarden.dao;

import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.bouquet.MarriedBouquet;
import com.flowergarden.flowers.Flower;
import org.codehaus.jettison.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MarriedBouquetJsonDaoTest extends InitDummyDb {

    @Autowired
    MarriedBouquetJsonDao marriedBouquetJsonDao;

    @Test
    public void saveAndReadJsonTest() throws IOException, JAXBException, SQLException, JSONException,
                                             XMLStreamException {
        marriedBouquetJsonDao.saveBouquetToJson(1);
        Bouquet bouquet = marriedBouquetJsonDao.readBouquet();
        List<Flower> flowers = new ArrayList<>(bouquet.getFlowers());
        Assert.assertTrue(bouquet instanceof MarriedBouquet);
        Assert.assertEquals(12, bouquet.getAssemblePrice(), 0.01);
        Assert.assertEquals(3, flowers.size());
    }
}
