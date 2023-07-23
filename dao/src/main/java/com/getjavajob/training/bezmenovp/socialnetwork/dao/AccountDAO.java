package com.getjavajob.training.bezmenovp.socialnetwork.dao;

import com.getjavajob.training.bezmenovp.socialnetwork.domain.Account;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class AccountDAO implements AutoCloseable {

    private static final String CREATE = "INSERT INTO ACCOUNTS (account_password, account_surname, account_name, " +
            "account_patronymic, account_birthday, account_home_address, account_business_address, account_email, " +
            "account_icq, account_skype, account_additional_information) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM ACCOUNTS";
    private static final String SELECT_BY_ID = "SELECT * FROM ACCOUNTS WHERE account_id = ?";
    private static final String UPDATE_BY_ID = "UPDATE ACCOUNTS SET account_password = ?, account_surname = ?," +
            "account_name = ?, account_patronymic = ?, account_birthday = ?, account_home_address = ?," +
            "account_business_address = ?, account_email = ?, account_icq = ?, account_skype = ?," +
            "account_additional_information= ? WHERE account_id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM ACCOUNTS WHERE account_id = ?";
    private static final String ADD_FRIEND_BY_ID = "INSERT INTO FRIENDS (account_id_1, account_id_2)" +
            "VALUES (?, ?), (?, ?)";
    private static final String SELECT_FRIENDS_BY_ID = "SELECT account_id_2 FROM FRIENDS WHERE account_id_1 = ?";
    private static final String DELETE_FRIEND_BY_ID = "DELETE FROM FRIENDS WHERE account_id_1 = ? AND account_id_2 = ?";
    private Connection connection;

    public AccountDAO(String propertiesPath) {
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
        return account;
    }

    public Account createAccount(String accountPassword, String accountSurname, String accountName,
                                 String accountPatronymic, LocalDate accountBirthday, String accountHomeAddress,
                                 String accountBusinessAddress, String accountEmail, String accountICQ,
                                 String accountSkype, String accountAdditionalInformation) throws SQLException {
        int accountID = 0;
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
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    accountID = resultSet.getInt(1);
                }
            }
        }
        return getAccountById(accountID);
    }

    public List<Account> getAllAccounts() throws SQLException {
        List<Account> allAccounts = new ArrayList<>();
        try (ResultSet resultSet = connection.createStatement().executeQuery(SELECT_ALL)) {
            while (resultSet.next()) {
                allAccounts.add(extractAccountFromDatabase(resultSet));
            }
        }
        return allAccounts;
    }

    public Account getAccountById(int accountID) throws SQLException {
        Account account = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setInt(1, accountID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    account = extractAccountFromDatabase(resultSet);
                }
            }
        }
        return account;
    }

    public Account updateAccountByID(String accountPassword, String accountSurname, String accountName,
                                     String accountPatronymic, LocalDate accountBirthday, String accountHomeAddress,
                                     String accountBusinessAddress, String accountEmail, String accountICQ,
                                     String accountSkype, String accountAdditionalInformation,
                                     int accountID) throws SQLException {
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
            preparedStatement.setInt(12, accountID);
            preparedStatement.executeUpdate();
            return getAccountById(accountID);
        }
    }

    public int deleteAccountByID(int accountID) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setInt(1, accountID);
            return preparedStatement.executeUpdate();
        }
    }

    public boolean addFriendAccountByID(int accountID, int friendID) throws SQLException {
        List<Integer> friendList = getFriendsAccountByID(accountID);
        if (!friendList.contains(friendID)) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_FRIEND_BY_ID)) {
                preparedStatement.setInt(1, accountID);
                preparedStatement.setInt(2, friendID);
                preparedStatement.setInt(3, friendID);
                preparedStatement.setInt(4, accountID);
                preparedStatement.executeUpdate();
                return true;
            }
        }
        return false;
    }

    public List<Integer> getFriendsAccountByID(int accountID) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FRIENDS_BY_ID)) {
            preparedStatement.setInt(1, accountID);
            List<Integer> friendList = new ArrayList<>();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    friendList.add(resultSet.getInt("account_id_2"));
                }
                return friendList;
            }
        }
    }

    public boolean deleteFriendshipByID(int accountID, int friendID) throws SQLException {
        try (PreparedStatement preparedStatement1 = connection.prepareStatement(DELETE_FRIEND_BY_ID);
             PreparedStatement preparedStatement2 = connection.prepareStatement(DELETE_FRIEND_BY_ID)) {
            preparedStatement1.setInt(1, accountID);
            preparedStatement1.setInt(2, friendID);
            preparedStatement1.executeUpdate();
            preparedStatement2.setInt(1, friendID);
            preparedStatement2.setInt(2, accountID);
            preparedStatement2.executeUpdate();
            return true;
        }
    }

}