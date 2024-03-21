package com.getjavajob.training.bezmenovp.socialnetwork.dao.datajpa.repository;

import com.getjavajob.training.bezmenovp.socialnetwork.common.Account;
import com.getjavajob.training.bezmenovp.socialnetwork.common.Group;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.datajpa.GroupMemberRepository;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.interfaces.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GroupMemberRepositoryImp implements MemberDao {
    private GroupMemberRepository groupMemberRepository;
    private GroupRepositoryManager groupRepositoryManager;

    @Autowired
    public GroupMemberRepositoryImp(GroupRepositoryManager groupRepositoryManager, GroupMemberRepository groupMemberRepository) {
        this.groupRepositoryManager = groupRepositoryManager;
        this.groupMemberRepository = groupMemberRepository;
    }

    @Override
    public boolean acceptInvite(int accountId, int appointmentId) {
        return groupRepositoryManager.acceptInvite(accountId, appointmentId);
    }

    @Override
    public Group getMembers(int accountId) {
        return groupMemberRepository.getMembers(accountId);
    }

    @Override
    public boolean delete(int accountId, int appointmentId) {
        return groupRepositoryManager.delete(accountId, appointmentId);
    }

    @Override
    public void invite(int inviterId, int appointmentId) {
        groupMemberRepository.invite(inviterId, appointmentId);
    }

    @Override
    public List<Account> getInvitesAccount(int id) {
        return groupMemberRepository.findById(id).get().getInviter();
    }

    @Override
    public void rejectInvite(int inviterId, int appointmentId) {
        groupMemberRepository.rejectInvite(inviterId, appointmentId);
    }

    public Account getOutgoingInvites(int accountId) {
        return groupMemberRepository.getOutgoingInvites(accountId);
    }

}