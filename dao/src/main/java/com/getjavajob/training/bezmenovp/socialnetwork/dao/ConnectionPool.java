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
    private final String driver;
    private final String url;
    private final String username;
    private final String password;
    private final int poolSize;
    private final LinkedBlockingQueue<Connection> connections;

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
        } catch (ClassNotFoundException | SQLException e) {
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
