package com.getjavajob.training.bezmenovp.socialnetwork.service;

import com.getjavajob.training.bezmenovp.socialnetwork.common.Account;
import com.getjavajob.training.bezmenovp.socialnetwork.common.Group;
import com.getjavajob.training.bezmenovp.socialnetwork.common.Roles;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.datajpa.repository.AccountMemberRepositoryImp;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.datajpa.repository.AccountRepositoryImp;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.dto.util.ValidateException;
import com.getjavajob.training.bezmenovp.socialnetwork.service.resources.ServiceConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = ServiceConfig.class)
@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.lazy-initialization=true")
@AutoConfigureMockMvc
public class AccountServiceTest {
    @InjectMocks
    private AccountService accountService;
    @Mock
    private AccountRepositoryImp accountRepositoryImp;
    @Mock
    private AccountMemberRepositoryImp accountMemberRepositoryImp;
    @Mock
    private AccountValidator validator;

    private Account createAccount() {
        return Account.builder()
                .surname("Vasin")
                .name("Igor")
                .patronymic("ivanov")
                .email("samara@mail.ru")
                .password("147")
                .birthDay(LocalDate.parse("2005-10-21"))
                .phone("9853671523")
                .workPhone("9853671523")
                .address("Spb")
                .workAddress("Spb")
                .skype("40")
                .icq("5697")
                .additionally("")
                .role(Roles.USER)
                .build();
    }

    @Test
    public void createAccountTest() throws ValidateException {
        Account account = createAccount();
        when(accountRepositoryImp.create(account)).thenReturn(account);
        assertEquals(account, accountService.create(account));
    }

    @Test
    public void updateAccountTest() throws ValidateException {
        Account account = createAccount();
        when(accountRepositoryImp.create(account)).thenReturn(account);
        when(accountRepositoryImp.getById(account.getId())).thenReturn(account);
        assertEquals(account, accountService.update(account));
    }

    @Test
    public void getAllTest() {
        Account account = createAccount();
        List<Account> accountList = new ArrayList<>();
        accountList.add(account);
        when(accountRepositoryImp.getAll()).thenReturn(accountList);
        assertEquals(accountList, accountService.getAll());
    }

    @Test
    public void getByIdTest() {
        Account account = createAccount();
        when(accountRepositoryImp.getById(account.getId())).thenReturn(account);
        assertEquals(account, accountService.getById(account.getId()));
    }

    @Test
    public void getByEmailTest() {
        Account account = createAccount();
        when(accountRepositoryImp.getByEmail(account.getEmail())).thenReturn(account);
        assertEquals(account, accountService.getByEmail(account.getEmail()));
    }

    @Test
    public void deleteByIdTest() {
        Account account = createAccount();
        when(accountRepositoryImp.deleteById(account.getId())).thenReturn(true);
        assertTrue(accountService.deleteById(account.getId()));
    }

    @Test
    public void getFriendsTest() {
        Account account = createAccount();
        List<Account> accountList = account.getAccountFriends();
        when(accountMemberRepositoryImp.getMembers(account.getId())).thenReturn(account);
        assertEquals(accountList, accountService.getFriends(account.getId()));
    }

    @Test
    public void getAccountGroupsTest() {
        Account account = createAccount();
        Set<Group> groupList = account.getAccountGroup();
        when(accountRepositoryImp.getAccountGroupsList(account.getId())).thenReturn(account);
        assertEquals(groupList, accountService.getAccountGroupsList(account.getId()));
    }

    @Test
    public void getInvitesTest() {
        Account account = createAccount();
        List<Account> accountList = account.getAccountInvites();
        when(accountMemberRepositoryImp.getInvites(account.getId())).thenReturn(account);
        assertEquals(accountList, accountService.getInvites(account.getId()));
    }

    @Test
    public void getOutgoingInvitesTest() {
        Account account = createAccount();
        List<Account> accountList = account.getAccountOutgoingInvites();
        when(accountMemberRepositoryImp.getOutgoingInvites(account.getId())).thenReturn(account);
        assertEquals(accountList, accountService.getOutgoingInvites(account.getId()));
    }

}