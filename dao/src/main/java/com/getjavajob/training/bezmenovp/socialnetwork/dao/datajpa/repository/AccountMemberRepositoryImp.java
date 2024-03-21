package com.getjavajob.training.bezmenovp.socialnetwork.dao.datajpa.repository;

import com.getjavajob.training.bezmenovp.socialnetwork.common.Account;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.datajpa.AccountMemberRepository;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.interfaces.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class AccountMemberRepositoryImp implements MemberDao {
    private AccountMemberRepository accountMemberRepository;
    private AccountRepositoryManager accountRepositoryManager;

    @Autowired
    public AccountMemberRepositoryImp(AccountMemberRepository accountMemberRepository, AccountRepositoryManager accountRepositoryManager) {
        this.accountMemberRepository = accountMemberRepository;
        this.accountRepositoryManager = accountRepositoryManager;
    }

    @Override
    public boolean acceptInvite(int accountId, int appointmentId) {
        return accountRepositoryManager.acceptInvite(accountId, appointmentId);
    }

    @Override
    public Account getMembers(int accountId) {
        return accountMemberRepository.getMembers(accountId);
    }

    @Override
    public boolean delete(int accountId, int appointmentId) {
        return accountRepositoryManager.deleteFriend(accountId, appointmentId);
    }

    @Override
    public void invite(int inviterId, int appointmentId) {
        accountMemberRepository.invite(inviterId, appointmentId);
    }

    @Override
    public List<Account> getInvitesAccount(int id) {
        return Collections.emptyList();
    }

    public Account getInvites(int id) {
        return accountMemberRepository.getInvites(id);
    }


    @Override
    public void rejectInvite(int inviterId, int appointmentId) {
        accountMemberRepository.rejectInvite(inviterId, appointmentId);
    }

    public Account getOutgoingInvites(int accountId) {
        return accountMemberRepository.getOutgoingInvites(accountId);
    }

}