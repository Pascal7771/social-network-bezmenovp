package com.getjavajob.training.bezmenovp.socialnetwork.dao;

import com.getjavajob.training.bezmenovp.socialnetwork.domain.Account;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AccountDAOTest {

    private static final String propertiesPath = "C:/Users/Pasha/Desktop/dev/projects/getjavajob-training/" +
            "social-network/dao/src/test/resources/PropertiesH2.properties";

    @Before
    public void setUp() {
        H2Manager.CreateTables();
    }

    @After
    public void tearDown() {
        H2Manager.DropTables();
    }

    @Test
    public void createAccount() {
        try (AccountDAO accountDAO = new AccountDAO(propertiesPath);) {
            Account actualAccount = accountDAO.createAccount("PPP", "Pavlov",
                    "Pavel12", "Pavlovich", LocalDate.of(1992, 05, 15),
                    "Frunze 5", "Lenina 2", "myMail", "myICQ",
                    "mySkype", "myInformation");
            Account expectedAccount = new Account(1, "PPP", "Pavlov",
                    "Pavel12", "Pavlovich", LocalDate.of(1992, 05, 15),
                    "Frunze 5", "Lenina 2", "myMail", "myICQ",
                    "mySkype", "myInformation");
            assertEquals(expectedAccount, actualAccount);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getAllAccounts() {
        try (AccountDAO accountDAO = new AccountDAO(propertiesPath);) {
            accountDAO.createAccount("PPP", "Pavlov",
                    "Pavel12", "Pavlovich", LocalDate.of(1992, 05, 15),
                    "Frunze 5", "Lenina 2", "myMail", "myICQ",
                    "mySkype", "myInformation");
            accountDAO.createAccount("PPP", "Pavlov",
                    "Pavel123", "Pavlovich", LocalDate.of(1992, 05, 15),
                    "Frunze 5", "Lenina 2", "myMail", "myICQ",
                    "mySkype", "myInformation");
            List<Account> actualAccounts = accountDAO.getAllAccounts();
            Account expectedAccount1 = new Account(1, "PPP", "Pavlov",
                    "Pavel12", "Pavlovich", LocalDate.of(1992, 05, 15),
                    "Frunze 5", "Lenina 2", "myMail", "myICQ",
                    "mySkype", "myInformation");
            Account expectedAccount2 = new Account(2, "PPP", "Pavlov",
                    "Pavel123", "Pavlovich", LocalDate.of(1992, 05, 15),
                    "Frunze 5", "Lenina 2", "myMail", "myICQ",
                    "mySkype", "myInformation");
            List<Account> expectedAccounts = new ArrayList<>();
            expectedAccounts.add(expectedAccount1);
            expectedAccounts.add(expectedAccount2);
            assertEquals(expectedAccounts, actualAccounts);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getAccountById() {
        try (AccountDAO accountDAO = new AccountDAO(propertiesPath);) {
            accountDAO.createAccount("PPP", "Pavlov",
                    "Pavel12", "Pavlovich", LocalDate.of(1992, 05, 15),
                    "Frunze 5", "Lenina 2", "myMail", "myICQ",
                    "mySkype", "myInformation");
            Account actualAccount = accountDAO.getAccountById(1);
            Account expectedAccount = new Account(1, "PPP", "Pavlov",
                    "Pavel12", "Pavlovich", LocalDate.of(1992, 05, 15),
                    "Frunze 5", "Lenina 2", "myMail", "myICQ",
                    "mySkype", "myInformation");
            assertEquals(expectedAccount, actualAccount);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void updateAccountByID() {
        try (AccountDAO accountDAO = new AccountDAO(propertiesPath);) {
            accountDAO.createAccount("PPP", "Pavlov",
                    "Pavel12", "Pavlovich", LocalDate.of(1992, 05, 15),
                    "Frunze 5", "Lenina 2", "myMail", "myICQ",
                    "mySkype", "myInformation");
            Account actualAccount = accountDAO.updateAccountByID("PPP", "Pavlov",
                    "Pavel123", "Pavlovich", LocalDate.of(1992, 05, 15),
                    "Frunze 5", "Lenina 2", "myMail", "myICQ",
                    "mySkype", "myInformation", 1);
            Account expectedAccount = new Account(1, "PPP", "Pavlov",
                    "Pavel123", "Pavlovich", LocalDate.of(1992, 05, 15),
                    "Frunze 5", "Lenina 2", "myMail", "myICQ",
                    "mySkype", "myInformation");
            assertEquals(expectedAccount, actualAccount);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void deleteAccountByID() {
        try (AccountDAO accountDAO = new AccountDAO(propertiesPath);) {
            accountDAO.createAccount("PPP", "Pavlov",
                    "Pavel12", "Pavlovich", LocalDate.of(1992, 05, 15),
                    "Frunze 5", "Lenina 2", "myMail", "myICQ",
                    "mySkype", "myInformation");
            assertEquals(1, accountDAO.deleteAccountByID(1));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void addFriendAccountByID() {
        try (AccountDAO accountDAO = new AccountDAO(propertiesPath);) {
            Account firstAccount = accountDAO.createAccount("PPP", "Pavlov",
                    "Pavel12", "Pavlovich", LocalDate.of(1992, 05, 15),
                    "Frunze 5", "Lenina 2", "myMail", "myICQ",
                    "mySkype", "myInformation");
            Account secondAccount = accountDAO.createAccount("PPP", "Pavlov",
                    "Pavel123", "Pavlovich", LocalDate.of(1992, 05, 15),
                    "Frunze 5", "Lenina 2", "myMail", "myICQ",
                    "mySkype", "myInformation");
            assertEquals(true, accountDAO.addFriendAccountByID(1, 2));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getFriendsAccountByID() {
        try (AccountDAO accountDAO = new AccountDAO(propertiesPath);) {
            Account firstAccount = accountDAO.createAccount("PPP", "Pavlov",
                    "Pavel12", "Pavlovich", LocalDate.of(1992, 05, 15),
                    "Frunze 5", "Lenina 2", "myMail", "myICQ",
                    "mySkype", "myInformation");
            Account secondAccount = accountDAO.createAccount("PPP", "Pavlov",
                    "Pavel123", "Pavlovich", LocalDate.of(1992, 05, 15),
                    "Frunze 5", "Lenina 2", "myMail", "myICQ",
                    "mySkype", "myInformation");
            accountDAO.addFriendAccountByID(1, 2);
            ArrayList<Integer> friendList = new ArrayList<>();
            friendList.add(2);
            assertEquals(friendList, accountDAO.getFriendsAccountByID(1));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void deleteFriendshipByID() {
        try (AccountDAO accountDAO = new AccountDAO(propertiesPath);) {
            Account firstAccount = accountDAO.createAccount("PPP", "Pavlov",
                    "Pavel12", "Pavlovich", LocalDate.of(1992, 05, 15),
                    "Frunze 5", "Lenina 2", "myMail", "myICQ",
                    "mySkype", "myInformation");
            Account secondAccount = accountDAO.createAccount("PPP", "Pavlov",
                    "Pavel123", "Pavlovich", LocalDate.of(1992, 05, 15),
                    "Frunze 5", "Lenina 2", "myMail", "myICQ",
                    "mySkype", "myInformation");
            accountDAO.addFriendAccountByID(1, 2);
            assertEquals(true, accountDAO.deleteFriendshipByID(1, 2));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}