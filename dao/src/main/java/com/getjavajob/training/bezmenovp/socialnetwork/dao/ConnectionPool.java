package com.getjavajob.training.bezmenovp.socialnetwork.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {

    private static ConnectionPool instance;
    private String driver;
    private String url;
    private String username;
    private String password;
    private int poolSize;
    private LinkedBlockingQueue<Connection> connections;

    private ConnectionPool(String propertiesPath, int poolSize) {
        try (FileInputStream fis = new FileInputStream(propertiesPath)) {
            Properties properties = new Properties();
            properties.load(fis);
            this.url = properties.getProperty("db.url");
            this.username = properties.getProperty("db.username");
            this.password = properties.getProperty("db.password");
            this.driver = properties.getProperty("db.driver");
            this.poolSize = poolSize;
            this.connections = new LinkedBlockingQueue<>(poolSize);
            initializePool();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public ConnectionPool(int poolSize) {
        try {
            Properties properties = new Properties();
            properties.load(this.getClass().getClassLoader()
                    .getResourceAsStream("PropertiesPG.properties"));
            this.url = properties.getProperty("db.url");
            this.username = properties.getProperty("db.username");
            this.password = properties.getProperty("db.password");
            this.driver = properties.getProperty("db.driver");
            this.poolSize = poolSize;
            this.connections = new LinkedBlockingQueue<>(poolSize);
            initializePool();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized ConnectionPool getInstance(String propertiesPath, int poolSize) {
        if (instance == null) {
            instance = new ConnectionPool(propertiesPath, poolSize);
        }
        return instance;
    }

    private void initializePool() {
        try {
            Class.forName(driver);
            for (int i = 0; i < poolSize; i++) {
                Connection connection = DriverManager.getConnection(url, username, password);
                connection.setAutoCommit(false);
                connections.offer(connection);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        try {
            return connections.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        }
    }

    public void releaseConnection(Connection connection) {
        connections.offer(connection);
    }

    public synchronized void closeAllConnections() throws SQLException {
        for (Connection connection : connections) {
            connection.close();
        }
        connections.clear();
        instance = null;
    }

}
