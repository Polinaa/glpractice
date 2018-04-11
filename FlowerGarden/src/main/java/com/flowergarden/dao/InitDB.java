package com.flowergarden.dao;

import java.rmi.UnexpectedException;
import java.sql.SQLException;

public interface InitDB {
    void createTables() throws SQLException;
    void deleteTables() throws SQLException;
    void populateTables() throws UnexpectedException, SQLException;
}
