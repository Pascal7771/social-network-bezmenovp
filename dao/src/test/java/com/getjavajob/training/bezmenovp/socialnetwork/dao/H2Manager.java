package com.getjavajob.training.bezmenovp.socialnetwork.dao;

import org.h2.tools.RunScript;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

public class H2Manager {

    private static final String propertiesPath = Objects.requireNonNull(H2Manager.class.getClassLoader().
            getResource("PropertiesH2.properties")).getPath();
    private static final String SQLFileCreate = Objects.requireNonNull(H2Manager.class.getClassLoader().
            getResource("createTables.sql")).getPath();
    private static final String SQLFileDrop = Objects.requireNonNull(H2Manager.class.getClassLoader().
            getResource("dropTables.sql")).getPath();

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