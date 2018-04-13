package com.flowergarden.util;

import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Component
public class SqliteQuery {
    Properties properties;

    public SqliteQuery() {
        properties = new Properties();
        try {
            properties.load(new FileReader("sqlitequeries.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String get(String key) {
        return properties.getProperty(key);
    }
}
