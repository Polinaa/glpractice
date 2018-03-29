package com.flowergarden.dao.daonew;

import java.rmi.UnexpectedException;
import java.sql.SQLException;

public interface InitDB {
    void createTable() throws SQLException;
    void deleteTable() throws SQLException;
    void populateTable() throws UnexpectedException, SQLException;
}
