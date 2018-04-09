package com.flowergarden.dao;

import com.flowergarden.dao.impl.GeneralFlowerDaoImpl;
import com.flowergarden.dao.impl.InitDBImplSqlite;
import com.flowergarden.dao.impl.ConnectionProviderImplSqlite;
import com.flowergarden.dao.impl.MarriedBouquetDaoImpl;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.sql.SQLException;

public class Runner {

    public static void main(String[] args) throws SQLException, IOException, JAXBException {
//        MarriedBouquetJsonDao marriedBouquetJsonDao = new MarriedBouquetJsonDaoImpl();
//
//        MarriedBouquetDaoImpl marriedBouquetDao = new MarriedBouquetDaoImpl(new ConnectionProviderImplSqlite().getConnection());
////        Bouquet bouquet = marriedBouquetDao.findBouquetById(1);
//        RoseDaoImpl roseDao = new RoseDaoImpl(new ConnectionProviderImplSqlite().getConnection());
//        roseDao.findFlowers().forEach(f -> System.out.println(f));
//        marriedBouquetDao.finddAllBouquets().forEach(b -> System.out.println(b));
//        marriedBouquetJsonDao.saveBouquetToJson(bouquet);


        ConnectionProviderImplSqlite connectionProviderImplSqlite = new ConnectionProviderImplSqlite();
        MarriedBouquetDaoImpl bouquetDao = new MarriedBouquetDaoImpl(connectionProviderImplSqlite.getConnection());
        InitDB initDB = new InitDBImplSqlite(connectionProviderImplSqlite.getConnection(), bouquetDao);
        initDB.deleteTables();
        initDB.createTables();
        initDB.populateTables();
        bouquetDao.findAllBouquets().forEach(b -> System.out.println(b));

        GeneralFlowerDaoImpl generalFlowerDao = new GeneralFlowerDaoImpl(connectionProviderImplSqlite.getConnection());
        generalFlowerDao.findAllFlowers().forEach(f-> System.out.println(f));
        generalFlowerDao.findFlowersInBouquet(1).forEach(f-> System.out.println(f));
    }
}
