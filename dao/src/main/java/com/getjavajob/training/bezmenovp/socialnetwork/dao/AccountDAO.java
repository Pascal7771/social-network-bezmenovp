package com.getjavajob.training.bezmenovp.socialnetwork.dao;

import com.getjavajob.training.bezmenovp.socialnetwork.domain.Account;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class AccountDAO implements AutoCloseable {

    private static final String CREATE = "INSERT INTO ACCOUNTS (account_password, account_surname, account_name, " +
            "account_patronymic, account_birthday, account_home_address, account_business_address, account_email, " +
            "account_icq, account_skype, account_additional_information, account_image) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM ACCOUNTS";
    private static final String SELECT_BY_ID = "SELECT * FROM ACCOUNTS WHERE account_id = ?";
    private static final String SELECT_BY_EMAIL = "SELECT * FROM ACCOUNTS WHERE account_email = ?";
    private static final String UPDATE_BY_ID = "UPDATE ACCOUNTS SET account_password = ?, account_surname = ?," +
            "account_name = ?, account_patronymic = ?, account_birthday = ?, account_home_address = ?," +
            "account_business_address = ?, account_email = ?, account_icq = ?, account_skype = ?," +
            "account_additional_information= ?, account_image = ? WHERE account_id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM ACCOUNTS WHERE account_id = ?";
    private static final String DELETE_BY_EMAIL = "DELETE FROM ACCOUNTS WHERE account_email = ?";
    private static final String ADD_FRIEND_BY_ID = "INSERT INTO FRIENDS (account_id_1, account_id_2)" +
            "VALUES (?, ?), (?, ?)";
    private static final String SELECT_FRIENDS_BY_ID = "SELECT account_id_2 FROM FRIENDS WHERE account_id_1 = ?";
    private static final String DELETE_FRIEND_BY_ID = "DELETE FROM FRIENDS WHERE account_id_1 = ? AND account_id_2 = ?";
    private final ConnectionPool connectionPool;

    public AccountDAO() {
        connectionPool = new ConnectionPool(16);
    }

    public AccountDAO(String propertiesPath) {
        connectionPool = ConnectionPool.getInstance(propertiesPath, 16);
    }

    @Override
    public void close() throws Exception {
        connectionPool.closeAllConnections();
    }

    private Account extractAccountFromDatabase(ResultSet resultSet) throws SQLException {
        Account account = new Account();
        account.setAccountID(resultSet.getInt("account_id"));
        account.setAccountPassword(resultSet.getString("account_password"));
        account.setAccountSurname(resultSet.getString("account_surname"));
        account.setAccountName(resultSet.getString("account_name"));
        account.setAccountPatronymic(resultSet.getString(("account_patronymic")));
        account.setAccountBirthDay(LocalDate.parse(resultSet.getString("account_birthday")));
        account.setAccountHomeAddress(resultSet.getString("account_home_address"));
        account.setAccountBusinessAddress(resultSet.getString("account_business_address"));
        account.setAccountEmail(resultSet.getString("account_email"));
        account.setAccountICQ(resultSet.getString("account_icq"));
        account.setAccountSkype(resultSet.getString("account_skype"));
        account.setAccountAdditionalInformation(resultSet.getString("account_additional_information"));
        account.setAccountImage(resultSet.getBytes("account_image"));
        return account;
    }

    public Account createAccount(String accountPassword, String accountSurname, String accountName,
                                 String accountPatronymic, LocalDate accountBirthday, String accountHomeAddress,
                                 String accountBusinessAddress, String accountEmail, String accountICQ,
                                 String accountSkype, String accountAdditionalInformation, InputStream accountImage) throws SQLException {
        int accountID = 0;
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE, RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, accountPassword);
            preparedStatement.setString(2, accountSurname);
            preparedStatement.setString(3, accountName);
            preparedStatement.setString(4, accountPatronymic);
            preparedStatement.setDate(5, Date.valueOf(accountBirthday));
            preparedStatement.setString(6, accountHomeAddress);
            preparedStatement.setString(7, accountBusinessAddress);
            preparedStatement.setString(8, accountEmail);
            preparedStatement.setString(9, accountICQ);
            preparedStatement.setString(10, accountSkype);
            preparedStatement.setString(11, accountAdditionalInformation);
            preparedStatement.setBinaryStream(12, accountImage);
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    accountID = resultSet.getInt(1);
                }
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return getAccountByID(accountID);
    }

    public List<Account> getAllAccounts() throws SQLException {
        List<Account> allAccounts = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        try (ResultSet resultSet = connection.createStatement().executeQuery(SELECT_ALL)) {
            while (resultSet.next()) {
                allAccounts.add(extractAccountFromDatabase(resultSet));
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return allAccounts;
    }

    public Account getAccountByID(int accountID) throws SQLException {
        Account account = new Account();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setInt(1, accountID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    account = extractAccountFromDatabase(resultSet);
                }
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return account;
    }

    public Account getAccountByEmail(String accountEmail) throws SQLException {
        Account account = new Account();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_EMAIL)) {
            preparedStatement.setString(1, accountEmail);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    account = extractAccountFromDatabase(resultSet);
                }
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return account;
    }

    public Account updateAccountByID(String accountPassword, String accountSurname, String accountName,
                                     String accountPatronymic, LocalDate accountBirthday, String accountHomeAddress,
                                     String accountBusinessAddress, String accountEmail, String accountICQ,
                                     String accountSkype, String accountAdditionalInformation, InputStream accountImage,
                                     int accountID) throws SQLException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_ID)) {
            preparedStatement.setString(1, accountPassword);
            preparedStatement.setString(2, accountSurname);
            preparedStatement.setString(3, accountName);
            preparedStatement.setString(4, accountPatronymic);
            preparedStatement.setDate(5, Date.valueOf(accountBirthday));
            preparedStatement.setString(6, accountHomeAddress);
            preparedStatement.setString(7, accountBusinessAddress);
            preparedStatement.setString(8, accountEmail);
            preparedStatement.setString(9, accountICQ);
            preparedStatement.setString(10, accountSkype);
            preparedStatement.setString(11, accountAdditionalInformation);
            preparedStatement.setBinaryStream(12, accountImage);
            preparedStatement.setInt(13, accountID);
            preparedStatement.executeUpdate();
            connection.commit();
            return getAccountByID(accountID);
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    public int deleteAccountByID(int accountID) throws SQLException {
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

    public int deleteAccountByEmail(String accountEmail) throws SQLException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_EMAIL)) {
            preparedStatement.setString(1, accountEmail);
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

    public boolean addFriendAccountByID(int accountID, int friendID) throws SQLException {
        List<Integer> friendList = getFriendsAccountIDByID(accountID);
        if (!friendList.contains(friendID)) {
            Connection connection = connectionPool.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_FRIEND_BY_ID)) {
                preparedStatement.setInt(1, accountID);
                preparedStatement.setInt(2, friendID);
                preparedStatement.setInt(3, friendID);
                preparedStatement.setInt(4, accountID);
                preparedStatement.executeUpdate();
                connection.commit();
                return true;
            } catch (SQLException e) {
                connection.rollback();
            } finally {
                connectionPool.releaseConnection(connection);
            }
        }
        return false;
    }

    public List<Integer> getFriendsAccountIDByID(int accountID) throws SQLException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FRIENDS_BY_ID)) {
            preparedStatement.setInt(1, accountID);
            List<Integer> friendList = new ArrayList<>();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    friendList.add(resultSet.getInt("account_id_2"));
                }
                connection.commit();
                return friendList;
            }
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    public boolean deleteFriendshipByID(int accountID, int friendID) throws SQLException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement1 = connection.prepareStatement(DELETE_FRIEND_BY_ID);
             PreparedStatement preparedStatement2 = connection.prepareStatement(DELETE_FRIEND_BY_ID)) {
            preparedStatement1.setInt(1, accountID);
            preparedStatement1.setInt(2, friendID);
            preparedStatement1.executeUpdate();
            preparedStatement2.setInt(1, friendID);
            preparedStatement2.setInt(2, accountID);
            preparedStatement2.executeUpdate();
            connection.commit();
            return true;
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

}