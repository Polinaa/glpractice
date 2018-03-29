package com.flowergarden.dao.daonew;

import java.sql.Connection;

public interface ConnectionProvider {

    Connection getConnection();
}
