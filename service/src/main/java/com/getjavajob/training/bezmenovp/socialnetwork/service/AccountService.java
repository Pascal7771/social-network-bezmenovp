package com.getjavajob.training.bezmenovp.socialnetwork.service;

import com.getjavajob.training.bezmenovp.socialnetwork.common.Account;
import com.getjavajob.training.bezmenovp.socialnetwork.common.Group;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.datajpa.repository.AccountMemberRepositoryImp;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.datajpa.repository.AccountRepositoryImp;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.datajpa.repository.AccountRepositoryManager;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.dto.util.ValidateException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class AccountService {
    private AccountValidator accountValidator;
    private AccountRepositoryImp accountRepositoryImp;
    private AccountMemberRepositoryImp accountMemberRepositoryImp;
    private AccountRepositoryManager accountRepositoryManager;

    @Transactional
    public Account create(Account accountRequest) throws ValidateException {
        accountValidator.validateAccount(accountRequest);
        return accountRepositoryImp.create(accountRequest);
    }

    @Transactional
    public Account update(Account accountRequest) throws ValidateException {
        accountValidator.validateAccount(accountRequest);
        accountRepositoryImp.create(accountRequest);
        return accountRepositoryImp.getById(accountRequest.getId());
    }

    @Transactional
    public List<Account> getAll() {
        return accountRepositoryImp.getAll();
    }

    public Account getByEmail(String email) {
        return accountRepositoryImp.getByEmail(email);
    }

    @Transactional
    public Account getById(int id) {
        return accountRepositoryImp.getById(id);
    }

    public Page<Account> getBySubStr(String subStr, Pageable pageNumber) {
        return accountRepositoryImp.findByTagName(subStr, pageNumber);
    }

    public List<Account> getBySubStr(String subStr) {
        return accountRepositoryImp.getBySubSrt(subStr);
    }

    @Transactional
    public boolean deleteById(int id) {
        try {
            accountRepositoryImp.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public List<Account> getFriends(int id) {
        Account account = accountMemberRepositoryImp.getMembers(id);
        return account.getAccountFriends();
    }

    public Set<Group> getAccountGroupsList(int id) {
        return accountRepositoryImp.getAccountGroupsList(id).getAccountGroup();
    }

    @Transactional
    public void addFriend(int accountId, int friendId) {
        if (!getFriends(accountId).contains(accountId)) {
            accountRepositoryManager.acceptInvite(accountId, friendId);
        }
    }

    @Transactional
    public void deleteFriend(int accountId, int friendId) {
        accountRepositoryManager.deleteFriend(accountId, friendId);
    }

    @Transactional
    public void invite(int idInviter, int friendId) {
        accountMemberRepositoryImp.invite(idInviter, friendId);
    }

    @Transactional
    public void rejectInvite(int inviterId, int accountId) {
        accountMemberRepositoryImp.rejectInvite(inviterId, accountId);
    }

    @Transactional
    public Account convertFromXml(int id, InputStream fileInputStream) throws JAXBException {
        return accountRepositoryManager.convertFromXml(id, fileInputStream);
    }

    public List<Account> getInvites(int id) {
        return accountMemberRepositoryImp.getInvites(id).getAccountInvites();
    }

    public List<Account> getOutgoingInvites(int accountId) {
        return accountMemberRepositoryImp.getOutgoingInvites(accountId).getAccountOutgoingInvites();
    }

    @Transactional
    public void makeAdmin(int id) {
        accountRepositoryImp.makeAdmin(id);
    }

}