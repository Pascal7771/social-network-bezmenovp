package com.getjavajob.training.bezmenovp.socialnetwork.service;

import com.getjavajob.training.bezmenovp.socialnetwork.common.Account;
import com.getjavajob.training.bezmenovp.socialnetwork.common.Group;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.datajpa.repository.GroupMemberRepositoryImp;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.datajpa.repository.GroupRepositoryImp;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.datajpa.repository.GroupRepositoryManager;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.dto.util.ValidateException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class GroupService {
    private GroupValidator validator;
    private GroupRepositoryImp groupRepositoryImp;
    private GroupMemberRepositoryImp groupMemberRepositoryImp;
    private GroupRepositoryManager groupRepositoryManager;

    @Transactional
    public Group create(Group groupRequest) throws ValidateException {
        validator.validateGroup(groupRequest);
        return groupRepositoryImp.create(groupRequest);
    }

    @Transactional
    public Group update(Group groupRequest) throws ValidateException {
        validator.validateGroup(groupRequest);
        groupRepositoryImp.update(groupRequest);
        return groupRepositoryImp.getById(groupRequest.getId());
    }

    public List<Group> getAll() {
        return groupRepositoryImp.getAll();
    }

    public Group getById(int id) {
        return groupRepositoryImp.getById(id);
    }

    public Page<Group> getBySubStr(String subStr, Pageable pageNumber) {
        return groupRepositoryImp.findByTagName(subStr, pageNumber);
    }

    public List<Group> getBySubStr(String subStr) {
        return groupRepositoryImp.getBySubSrt(subStr);
    }

    @Transactional
    public boolean deleteById(int id) {
        groupRepositoryImp.deleteById(id);
        return true;
    }

    public List<Account> getMembers(int groupId) {
        return groupMemberRepositoryImp.getMembers(groupId).getMembers();
    }

    @Transactional
    public void deleteMember(int memberId, int groupId) {
        groupRepositoryManager.delete(memberId, groupId);
    }

    @Transactional
    public void invite(int inviterId, int groupId) {
        groupMemberRepositoryImp.invite(inviterId, groupId);
    }

    public List<Account> getInvites(int groupId) {
        return groupMemberRepositoryImp.getInvitesAccount(groupId);
    }

    @Transactional
    public void acceptInvite(int inviterId, int groupId) {
        groupRepositoryManager.acceptInvite(inviterId, groupId);
    }

    @Transactional
    public void rejectInvite(int inviterId, int groupId) {
        groupMemberRepositoryImp.rejectInvite(inviterId, groupId);
    }

    public List<Group> getOutgoingInvites(int accountId) {
        return groupMemberRepositoryImp.getOutgoingInvites(accountId).getGroupInvite();
    }

}