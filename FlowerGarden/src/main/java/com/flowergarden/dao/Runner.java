package com.flowergarden.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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

        ApplicationContext ctx = new ClassPathXmlApplicationContext("app-context-myflowergarden.xml");
        InitDB initDB =  (InitDB) ctx.getBean("initDBImplSqlite");
        BouquetDao bouquetDao =  (BouquetDao) ctx.getBean("marriedBouquetDaoImpl");

        GeneralFlowerDao generalFlowerDao =  (GeneralFlowerDao) ctx.getBean("generalFlowerDaoImpl");

        initDB.deleteTables();
        initDB.createTables();
        initDB.populateTables();

        bouquetDao.findAllBouquets().forEach(b -> System.out.println(b));

        generalFlowerDao.findAllFlowers().forEach(f-> System.out.println(f));
        generalFlowerDao.findFlowersInBouquet(1).forEach(f-> System.out.println(f));
    }
}
