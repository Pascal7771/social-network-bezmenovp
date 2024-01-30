package com.getjavajob.training.bezmenovp.socialnetwork.service;


import com.getjavajob.training.bezmenovp.socialnetwork.dao.AccountDAO;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.PhoneDAO;
import com.getjavajob.training.bezmenovp.socialnetwork.domain.Account;

import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class AccountService {

    private final AccountDAO accountDAO;
    private final PhoneDAO phoneDAO;

    public AccountService() {
        this.accountDAO = new AccountDAO();
        this.phoneDAO = new PhoneDAO();
    }

    public AccountService(AccountDAO accountDAO, PhoneDAO phoneDAO) {
        this.accountDAO = accountDAO;
        this.phoneDAO = phoneDAO;
    }

    public Account createAccount(String accountPassword, String accountSurname, String accountName,
                                 String accountPatronymic, LocalDate accountBirthday, String accountPhone,
                                 String accountWorkPhone, String accountHomeAddress, String accountBusinessAddress,
                                 String accountEmail, String accountICQ, String accountSkype,
                                 String accountAdditionalInformation, InputStream accountImage) {
        try {
            Account account = accountDAO.createAccount(accountPassword, accountSurname, accountName,
                    accountPatronymic, accountBirthday, accountHomeAddress, accountBusinessAddress, accountEmail,
                    accountICQ, accountSkype, accountAdditionalInformation, accountImage);
            phoneDAO.createPhones(accountPhone, accountWorkPhone, account.getAccountID());
            account.setAccountPhone(phoneDAO.getPhoneByID(account.getAccountID()));
            account.setAccountWorkPhone(phoneDAO.getWorkPhoneByID(account.getAccountID()));
            return account;
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            return null;
        }
    }

    public List<Account> getAllAccounts() {
        try {
            List<Account> accountList = accountDAO.getAllAccounts();
            for (Account account : accountList) {
                account.setAccountPhone(phoneDAO.getPhoneByID(account.getAccountID()));
                account.setAccountWorkPhone(phoneDAO.getWorkPhoneByID(account.getAccountID()));
            }
            return accountList;
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public Account getAccountByID(int accountID) {
        try {
            Account account = accountDAO.getAccountByID(accountID);
            account.setAccountPhone(phoneDAO.getPhoneByID(account.getAccountID()));
            account.setAccountWorkPhone(phoneDAO.getWorkPhoneByID(account.getAccountID()));
            return account;
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            return null;
        }
    }

    public Account getAccountByEmail(String accountEmail) {
        try {
            Account account = accountDAO.getAccountByEmail(accountEmail);
            account.setAccountPhone(phoneDAO.getPhoneByID(account.getAccountID()));
            account.setAccountWorkPhone(phoneDAO.getWorkPhoneByID(account.getAccountID()));
            return account;
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            return null;
        }
    }

    public Account updateAccountByID(String accountPassword, String accountSurname, String accountName,
                                     String accountPatronymic, LocalDate accountBirthday, String accountPhone,
                                     String accountWorkPhone, String accountHomeAddress, String accountBusinessAddress,
                                     String accountEmail, String accountICQ, String accountSkype,
                                     String accountAdditionalInformation, InputStream accountImage, int accountID) {
        try {
            Account account = accountDAO.updateAccountByID(accountPassword, accountSurname, accountName,
                    accountPatronymic, accountBirthday, accountHomeAddress, accountBusinessAddress, accountEmail,
                    accountICQ, accountSkype, accountAdditionalInformation, accountImage, accountID);
            phoneDAO.updatePhonesByID(accountPhone, accountWorkPhone, accountID);
            account.setAccountPhone(phoneDAO.getPhoneByID(account.getAccountID()));
            account.setAccountWorkPhone(phoneDAO.getWorkPhoneByID(account.getAccountID()));
            return account;
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            return null;
        }
    }

    public int deleteAccountByID(int accountID) {
        try {
            return accountDAO.deleteAccountByID(accountID);
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            return -1;
        }
    }

    public int deleteAccountByEmail(String accountEmail) {
        try {
            phoneDAO.deletePhonesByID(accountDAO.getAccountByEmail(accountEmail).getAccountID());
            return accountDAO.deleteAccountByEmail(accountEmail);
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            return -1;
        }
    }

    public boolean addFriendAccountByID(int accountID, int friendID) {
        if (!getFriendsAccountIDByID(accountID).contains(friendID)) {
            try {
                accountDAO.addFriendAccountByID(accountID, friendID);
                return true;
            } catch (SQLException e) {
                System.err.println("SQL Exception: " + e.getMessage());
            }
        }
        return false;
    }

    public List<Integer> getFriendsAccountIDByID(int accountID) {
        try {
            return accountDAO.getFriendsAccountIDByID(accountID);
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public boolean deleteFriendshipByID(int accountID, int friendID) {
        try {
            accountDAO.deleteFriendshipByID(accountID, friendID);
            return true;
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            return false;
        }
    }

}