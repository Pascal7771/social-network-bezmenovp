/*package com.getjavajob.training.bezmenovp.socialnetwork.service;

import com.getjavajob.training.bezmenovp.socialnetwork.dao.AccountDAO;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.PhoneDAO;
import com.getjavajob.training.bezmenovp.socialnetwork.domain.Account;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AccountServiceTest {

    @Mock
    private AccountDAO accountDAO;
    @Mock
    private PhoneDAO phoneDAO;
    private AccountService accountService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        accountService = new AccountService(accountDAO, phoneDAO);
    }

    @Test
    public void createAccount() throws SQLException {
        Account expectedAccount = new Account("PPP", "Pavlov",
                "Pavel12", "Pavlovich", LocalDate.of(1992, 05, 15),
                "123123", "123123", "Frunze 5",
                "Lenina 2", "myMail", "myICQ", "mySkype",
                "myInformation");
        when(accountDAO.createAccount("PPP", "Pavlov",
                "Pavel12", "Pavlovich", LocalDate.of(1992, 05, 15),
                "Frunze 5", "Lenina 2", "myMail", "myICQ",
                "mySkype", "myInformation")).thenReturn(expectedAccount);
        Account actualAccount = accountService.createAccount("PPP", "Pavlov",
                "Pavel12", "Pavlovich", LocalDate.of(1992, 05, 15),
                "123123", "123123", "Frunze 5",
                "Lenina 2", "myMail", "myICQ", "mySkype",
                "myInformation");
        verify(accountDAO).createAccount("PPP", "Pavlov",
                "Pavel12", "Pavlovich", LocalDate.of(1992, 05, 15),
                "Frunze 5", "Lenina 2", "myMail", "myICQ",
                "mySkype", "myInformation");
        assertEquals(expectedAccount, actualAccount);
    }

    @Test
    public void getAllAccounts() throws SQLException {
        Account account = new Account("PPP", "Pavlov",
                "Pavel12", "Pavlovich", LocalDate.of(1992, 05, 15),
                "123123", "123123", "Frunze 5",
                "Lenina 2", "myMail", "myICQ", "mySkype",
                "myInformation");
        List<Account> expectedAccounts = new ArrayList<>();
        expectedAccounts.add(account);
        when(accountDAO.getAllAccounts()).thenReturn(expectedAccounts);
        List<Account> actualAccounts = accountService.getAllAccounts();
        verify(accountDAO).getAllAccounts();
        assertEquals(expectedAccounts, actualAccounts);
    }

    @Test
    public void getAccountByID() throws SQLException {
        Account expectedAccount = new Account("PPP", "Pavlov",
                "Pavel12", "Pavlovich", LocalDate.of(1992, 05, 15),
                "123123", "123123", "Frunze 5",
                "Lenina 2", "myMail", "myICQ", "mySkype",
                "myInformation");
        when(accountDAO.getAccountByID(1)).thenReturn(expectedAccount);
        Account actualAccount = accountService.getAccountByID(1);
        verify(accountDAO).getAccountByID(1);
        assertEquals(expectedAccount, actualAccount);
    }

    @Test
    public void getAccountByEmail() throws SQLException {
        Account account = new Account("PPP", "Pavlov",
                "Pavel12", "Pavlovich", LocalDate.of(1992, 05, 15),
                "123123", "123123", "Frunze 5",
                "Lenina 2", "myMail", "myICQ", "mySkype",
                "myInformation");
        when(accountDAO.getAccountByEmail("myMail")).thenReturn(account);
        Account actualAccount = accountService.getAccountByEmail("myMail");
        verify(accountDAO).getAccountByEmail("myMail");
        assertEquals(account, actualAccount);
    }

    @Test
    public void updateAccountByID() throws SQLException {
        Account expectedAccount = new Account("PPP", "Pavlov",
                "Pavel12", "Pavlovich", LocalDate.of(1992, 05, 15),
                "123123", "123123", "Frunze 5",
                "Lenina 2", "myMail", "myICQ", "mySkype",
                "myInformation");
        when(accountDAO.updateAccountByID("PPP", "Pavlov",
                "Pavel12", "Pavlovich", LocalDate.of(1992, 05, 15),
                "Frunze 5", "Lenina 2", "myMail",
                "myICQ", "mySkype", "myInformation",
                1)).thenReturn(expectedAccount);
        Account actualAccount = accountService.updateAccountByID("PPP", "Pavlov",
                "Pavel12", "Pavlovich", LocalDate.of(1992, 05, 15),
                "123123", "123123", "Frunze 5", "Lenina 2",
                "myMail", "myICQ", "mySkype", "myInformation",
                1);
        verify(accountDAO).updateAccountByID("PPP", "Pavlov",
                "Pavel12", "Pavlovich", LocalDate.of(1992, 05, 15),
                "Frunze 5", "Lenina 2", "myMail",
                "myICQ", "mySkype", "myInformation",
                1);
        assertEquals(expectedAccount, actualAccount);
    }

    @Test
    public void deleteAccountByID() throws SQLException {
        Account account = new Account("PPP", "Pavlov",
                "Pavel12", "Pavlovich", LocalDate.of(1992, 05, 15),
                "123123", "123123", "Frunze 5",
                "Lenina 2", "myMail", "myICQ", "mySkype",
                "myInformation");
        when(accountDAO.deleteAccountByID(account.getAccountID())).thenReturn(1);
        int deleteCounter = accountService.deleteAccountByID(account.getAccountID());
        verify(accountDAO).deleteAccountByID(account.getAccountID());
        assertEquals(1, deleteCounter);
    }

    @Test
    public void deleteAccountByEmail() throws SQLException {
        Account account = new Account("PPP", "Pavlov",
                "Pavel12", "Pavlovich", LocalDate.of(1992, 05, 15),
                "123123", "123123", "Frunze 5",
                "Lenina 2", "myMail", "myICQ", "mySkype",
                "myInformation");
        when(accountDAO.getAccountByEmail(account.getAccountEmail())).thenReturn(account);
        when(accountDAO.deleteAccountByEmail(account.getAccountEmail())).thenReturn(1);
        int deleteCounter = accountService.deleteAccountByEmail(account.getAccountEmail());
        verify(phoneDAO, times(1)).deletePhonesByID(account.getAccountID());
        verify(accountDAO, times(1)).deleteAccountByEmail(account.getAccountEmail());
        assertEquals(1, deleteCounter);
    }

    @Test
    public void addFriendAccountByID() throws SQLException {
        Account account1 = new Account("PPP", "Pavlov",
                "Pavel12", "Pavlovich", LocalDate.of(1992, 05, 15),
                "123123", "123123", "Frunze 5",
                "Lenina 2", "myMail", "myICQ", "mySkype",
                "myInformation");
        Account account2 = new Account("PPP", "Pavlov",
                "Pavel12", "Pavlovich", LocalDate.of(1992, 05, 15),
                "123123", "123123", "Frunze 5",
                "Lenina 2", "myMail", "myICQ", "mySkype",
                "myInformation");
        when(accountDAO.addFriendAccountByID(account1.getAccountID(), account2.getAccountID())).thenReturn(true);
        boolean addMarker = accountService.addFriendAccountByID(account1.getAccountID(), account2.getAccountID());
        verify(accountDAO).addFriendAccountByID(account1.getAccountID(), account2.getAccountID());
        assertTrue(addMarker);
    }

    @Test
    public void getFriendsAccountIDByID() throws SQLException {
        Account account1 = new Account("PPP", "Pavlov",
                "Pavel12", "Pavlovich", LocalDate.of(1992, 05, 15),
                "123123", "123123", "Frunze 5",
                "Lenina 2", "myMail", "myICQ", "mySkype",
                "myInformation");
        Account account2 = new Account("PPP", "Pavlov",
                "Pavel12", "Pavlovich", LocalDate.of(1992, 05, 15),
                "123123", "123123", "Frunze 5",
                "Lenina 2", "myMail", "myICQ", "mySkype",
                "myInformation");
        List<Integer> expectedFriendList = new ArrayList<>();
        expectedFriendList.add(account2.getAccountID());
        when(accountDAO.getFriendsAccountIDByID(account1.getAccountID())).thenReturn(expectedFriendList);
        List<Integer> actualFriendList = accountService.getFriendsAccountIDByID(account1.getAccountID());
        verify(accountDAO).getFriendsAccountIDByID(account1.getAccountID());
        assertEquals(expectedFriendList, actualFriendList);
    }

    @Test
    public void deleteFriendshipByID() throws SQLException {
        Account account1 = new Account("PPP", "Pavlov",
                "Pavel12", "Pavlovich", LocalDate.of(1992, 05, 15),
                "123123", "123123", "Frunze 5",
                "Lenina 2", "myMail", "myICQ", "mySkype",
                "myInformation");
        Account account2 = new Account("PPP", "Pavlov",
                "Pavel12", "Pavlovich", LocalDate.of(1992, 05, 15),
                "123123", "123123", "Frunze 5",
                "Lenina 2", "myMail", "myICQ", "mySkype",
                "myInformation");
        when(accountDAO.deleteFriendshipByID(account1.getAccountID(), account2.getAccountID())).thenReturn(true);
        boolean deleteMarker = accountService.deleteFriendshipByID(account1.getAccountID(), account2.getAccountID());
        verify(accountDAO).deleteFriendshipByID(account1.getAccountID(), account2.getAccountID());
        assertTrue(deleteMarker);
    }

}*/