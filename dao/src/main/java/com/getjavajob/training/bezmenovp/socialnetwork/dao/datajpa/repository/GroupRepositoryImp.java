package com.getjavajob.training.bezmenovp.socialnetwork.dao.datajpa.repository;

import com.getjavajob.training.bezmenovp.socialnetwork.common.Group;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.datajpa.GroupRepository;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.interfaces.GroupDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GroupRepositoryImp implements GroupDao {
    private GroupRepository groupRepository;

    @Autowired
    public GroupRepositoryImp(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public List<Group> getAll() {
        return (List<Group>) groupRepository.findAll();
    }

    public List<Group> getBySubSrt(String subString) {
        return groupRepository.getBySubSrt(subString);
    }

    public Page<Group> findByTagName(String subString, Pageable page) {
        return groupRepository.findByTagName(subString, page);
    }

    @Override
    public Group create(Group entity) {
        return groupRepository.save(entity);
    }

    @Override
    public Group getById(int id) {
        return groupRepository.findById(id).orElse(null);
    }

    @Override
    public Group update(Group entity) {
        return groupRepository.save(entity);
    }

    @Override
    public boolean deleteById(int id) {
        groupRepository.deleteById(id);
        groupRepository.deleteFromAccountGroup(id);
        groupRepository.deleteFromGroupInvite(id);
        return true;
    }

}