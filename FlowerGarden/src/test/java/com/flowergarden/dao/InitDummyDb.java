package com.flowergarden.dao;

import com.flowergarden.dao.impl.InitDBImplSqlite;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;

@ContextConfiguration(locations={"classpath*:app-context-myflowergarden.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class InitDummyDb {

    @Autowired
    private InitDBImplSqlite initDB;

    @Before
    public void initDummyDb() throws SQLException {
        initDB.deleteTables();
        initDB.createTables();
        initDB.populateTables();
    }
}
