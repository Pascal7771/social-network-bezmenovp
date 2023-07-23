package com.getjavajob.training.bezmenovp.socialnetwork.dao;

import org.h2.tools.RunScript;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class H2Manager {

    private static final String propertiesPath = "C:/Users/Pasha/Desktop/dev/projects/getjavajob-training/" +
            "social-network/dao/src/test/resources/PropertiesH2.properties";
    private static final String SQLFileCreate = "C:/Users/Pasha/Desktop/dev/projects/getjavajob-training/" +
            "social-network/dao/src/test/resources/createTables.sql";
    private static final String SQLFileDrop = "C:/Users/Pasha/Desktop/dev/projects/getjavajob-training/" +
            "social-network/dao/src/test/resources/dropTables.sql";

    public static void CreateTables() {
        try (FileInputStream fis1 = new FileInputStream(propertiesPath);
             FileInputStream fis2 = new FileInputStream(SQLFileCreate)) {
            Properties properties = new Properties();
            properties.load(fis1);
            Connection connection = DriverManager.getConnection(properties.getProperty("db.url"),
                    properties.getProperty("db.username"), properties.getProperty("db.password"));
            InputStreamReader inputScript = new InputStreamReader(fis2);
            RunScript.execute(connection, inputScript);
            connection.close();
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void DropTables() {
        Properties properties = new Properties();
        try (FileInputStream fis1 = new FileInputStream(propertiesPath);
             FileInputStream fis2 = new FileInputStream(SQLFileDrop)) {
            properties.load(fis1);
            Connection connection = DriverManager.getConnection(properties.getProperty("db.url"),
                    properties.getProperty("db.username"), properties.getProperty("db.password"));
            InputStreamReader inputScript = new InputStreamReader(fis2);
            RunScript.execute(connection, inputScript);
            connection.close();
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

}