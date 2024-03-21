package com.getjavajob.training.bezmenovp.socialnetwork.dao.datajpa.repository;

import com.getjavajob.training.bezmenovp.socialnetwork.common.Account;
import com.getjavajob.training.bezmenovp.socialnetwork.common.Group;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.datajpa.AccountRepository;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.datajpa.GroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class GroupRepositoryManager {
    private GroupRepository groupRepository;
    private AccountRepository accountRepository;

    public boolean acceptInvite(int inviterId, int groupId) {
        Account account = accountRepository.findById(inviterId).orElse(null);
        Group group = groupRepository.findById(groupId).orElse(null);
        account.getGroupInvite().remove(group);
        account.getAccountGroup().add(group);
        accountRepository.save(account);
        return true;
    }

    public boolean delete(int memberId, int groupId) {
        Account account = accountRepository.findById(memberId).orElse(null);
        Group group = groupRepository.findById(groupId).orElse(null);
        account.getAccountGroup().remove(group);
        accountRepository.save(account);
        return true;
    }

}