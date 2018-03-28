package com.flowergarden.dao;

import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.dao.impl.ConnectionProviderImplSqlite;
import com.flowergarden.dao.impl.MarriedBouquetDaoImpl;
import com.flowergarden.dao.impl.MarriedBouquetJsonDaoImpl;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.sql.SQLException;

public class Runner {

    public static void main(String[] args) throws SQLException, IOException, JAXBException {
        MarriedBouquetJsonDao marriedBouquetJsonDao = new MarriedBouquetJsonDaoImpl();

        MarriedBouquetDaoImpl marriedBouquetDao = new MarriedBouquetDaoImpl(new ConnectionProviderImplSqlite().getConnection());
        Bouquet bouquet = marriedBouquetDao.findBouquetById(1);

//        marriedBouquetDao.findBouquets().forEach(b -> System.out.println(b));
        marriedBouquetJsonDao.saveBouquetToJson(bouquet);
    }
}