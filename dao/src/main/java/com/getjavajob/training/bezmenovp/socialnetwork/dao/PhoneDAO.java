package com.getjavajob.training.bezmenovp.socialnetwork.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class PhoneDAO implements AutoCloseable {

    private static final String CREATE = "INSERT INTO ACCOUNT_PHONES (account_phone, account_work_phone, account_id)" +
            "VALUES (?, ?, ?)";
    private static final String SELECT_PHONE_BY_ID = "SELECT account_phone FROM ACCOUNT_PHONES WHERE account_id = ?";
    private static final String SELECT_WORK_PHONE_BY_ID = "SELECT account_work_phone FROM ACCOUNT_PHONES " +
            "WHERE account_id = ?";
    private static final String UPDATE_BY_ID = "UPDATE ACCOUNT_PHONES SET account_phone = ?, account_work_phone = ?" +
            "WHERE account_id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM ACCOUNT_PHONES WHERE account_id = ?";
    private Connection connection;

    public PhoneDAO(String propertiesPath) {
        try (FileInputStream fis = new FileInputStream(propertiesPath)) {
            Properties properties = new Properties();
            properties.load(fis);
            connection = DriverManager.getConnection(properties.getProperty("db.url"),
                    properties.getProperty("db.username"), properties.getProperty("db.password"));
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public String createPhones(String accountPhone, String accountWorkPhone, int accountID) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE)) {
            preparedStatement.setString(1, accountPhone);
            preparedStatement.setString(2, accountWorkPhone);
            preparedStatement.setInt(3, accountID);
            preparedStatement.executeUpdate();
            return getPhoneByID(accountID).concat(getWorkPhoneByID(accountID));
        }
    }

    public String getPhoneByID(int accountID) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PHONE_BY_ID)) {
            preparedStatement.setInt(1, accountID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    return resultSet.getString("account_phone");
                }
            }
        }
        return null;
    }

    public String getWorkPhoneByID(int accountID) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_WORK_PHONE_BY_ID)) {
            preparedStatement.setInt(1, accountID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    return resultSet.getString("account_work_phone");
                }
            }
        }
        return null;
    }

    public String updatePhonesByID(String accountPhone, String accountWorkPhone, int accountID) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_ID)) {
            preparedStatement.setString(1, accountPhone);
            preparedStatement.setString(2, accountWorkPhone);
            preparedStatement.setInt(3, accountID);
            preparedStatement.executeUpdate();
            return getPhoneByID(accountID).concat(getWorkPhoneByID(accountID));
        }
    }

    public int deletePhonesByID(int accountID) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setInt(1, accountID);
            return preparedStatement.executeUpdate();
        }
    }

}