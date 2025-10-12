package com.suaempresa.locadora.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    
    private static Properties properties;
    
    private static final String DB_PROPERTIES_FILE = "/home/enzo/LPO2SistemaLocadoraRefatorado/SistemaLocadoraREFATORADO/src/com/suaempresa/locadora/model/dao/DataBase.properties";
    
    private ConnectionFactory() {}
    
    public static Connection getConnection() throws SQLException, IOException {
        readProperties();
        
        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");
        
        return DriverManager.getConnection(url, user, password);
    }
    
    public static void readProperties() throws IOException {
        if (properties == null) {
            properties = new Properties();
            try (FileInputStream file = new FileInputStream(DB_PROPERTIES_FILE)) {
                properties.load(file);
            } catch (IOException e) {
                throw e;
            }
        }
    }
}