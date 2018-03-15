package com.flowergarden.dao;

import java.sql.Connection;

public interface ConnectionProvider {

    Connection getConnection();
}
