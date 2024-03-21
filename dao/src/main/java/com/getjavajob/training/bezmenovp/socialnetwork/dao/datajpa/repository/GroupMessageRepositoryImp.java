package com.getjavajob.training.bezmenovp.socialnetwork.dao.datajpa.repository;

import com.getjavajob.training.bezmenovp.socialnetwork.common.MessageGroup;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.datajpa.GroupMessageRepository;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.interfaces.MessageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GroupMessageRepositoryImp implements MessageDao<MessageGroup> {
    private GroupMessageRepository groupMessageRepository;

    @Autowired
    public GroupMessageRepositoryImp(GroupMessageRepository groupMessageRepository) {
        this.groupMessageRepository = groupMessageRepository;
    }

    @Override
    public List<MessageGroup> getByAppointment(int recipientId, String appointment) {
        return groupMessageRepository.getByAppointment(recipientId, appointment);
    }

    @Override
    public MessageGroup create(MessageGroup entity) {
        return groupMessageRepository.save(entity);
    }

    @Override
    public MessageGroup getById(int id) {
        return groupMessageRepository.findById(id).orElse(null);
    }

    @Override
    public MessageGroup update(MessageGroup entity) {
        return groupMessageRepository.save(entity);
    }

    @Override
    public boolean deleteById(int id) {
        groupMessageRepository.deleteById(id);
        return true;
    }

}