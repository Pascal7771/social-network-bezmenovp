package com.getjavajob.training.bezmenovp.socialnetwork.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PhoneDAO implements AutoCloseable {

    private static final String CREATE = "INSERT INTO ACCOUNT_PHONES (account_phone, account_work_phone, account_id)" +
            "VALUES (?, ?, ?)";
    private static final String SELECT_PHONE_BY_ID = "SELECT account_phone FROM ACCOUNT_PHONES WHERE account_id = ?";
    private static final String SELECT_WORK_PHONE_BY_ID = "SELECT account_work_phone FROM ACCOUNT_PHONES " +
            "WHERE account_id = ?";
    private static final String UPDATE_BY_ID = "UPDATE ACCOUNT_PHONES SET account_phone = ?, account_work_phone = ?" +
            "WHERE account_id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM ACCOUNT_PHONES WHERE account_id = ?";
    private final ConnectionPool connectionPool;

    public PhoneDAO(String propertiesPath) {
        connectionPool = ConnectionPool.getInstance(propertiesPath, 16);
    }

    @Override
    public void close() throws Exception {
        connectionPool.closeAllConnections();
    }

    public String createPhones(String accountPhone, String accountWorkPhone, int accountID) throws SQLException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE)) {
            preparedStatement.setString(1, accountPhone);
            preparedStatement.setString(2, accountWorkPhone);
            preparedStatement.setInt(3, accountID);
            preparedStatement.executeUpdate();
            String accountPhones = accountPhone + accountWorkPhone;
            connection.commit();
            return accountPhones;
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    public String getPhoneByID(int accountID) throws SQLException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PHONE_BY_ID)) {
            preparedStatement.setInt(1, accountID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                connection.commit();
                while (resultSet.next()) {
                    return resultSet.getString("account_phone");
                }
            }
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return null;
    }

    public String getWorkPhoneByID(int accountID) throws SQLException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_WORK_PHONE_BY_ID)) {
            preparedStatement.setInt(1, accountID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                connection.commit();
                while (resultSet.next()) {
                    return resultSet.getString("account_work_phone");
                }
            }
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return null;
    }

    public String updatePhonesByID(String accountPhone, String accountWorkPhone, int accountID) throws SQLException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_ID)) {
            preparedStatement.setString(1, accountPhone);
            preparedStatement.setString(2, accountWorkPhone);
            preparedStatement.setInt(3, accountID);
            preparedStatement.executeUpdate();
            connection.commit();
            String accountPhones = getPhoneByID(accountID) + getWorkPhoneByID(accountID);
            connection.commit();
            return accountPhones;
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    public int deletePhonesByID(int accountID) throws SQLException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setInt(1, accountID);
            int count = preparedStatement.executeUpdate();
            connection.commit();
            return count;
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

}