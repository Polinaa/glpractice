package com.flowergarden.util;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Component
public class SqliteQuery {
    Properties properties;

    public SqliteQuery() {
        properties = new Properties();
        try {
            properties.load(new FileReader(".." + File.separator + "FlowerGarden" + File.separator + "sqlitequeries.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String get(String key) {
        return properties.getProperty(key);
    }
}
