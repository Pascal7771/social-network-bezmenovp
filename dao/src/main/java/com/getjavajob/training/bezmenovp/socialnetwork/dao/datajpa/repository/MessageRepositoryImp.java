package com.getjavajob.training.bezmenovp.socialnetwork.dao.datajpa.repository;

import com.getjavajob.training.bezmenovp.socialnetwork.common.Message;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.datajpa.MessageRepository;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.interfaces.MessageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessageRepositoryImp implements MessageDao<Message> {
    private MessageRepository messageRepository;

    @Autowired
    public MessageRepositoryImp(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public List<Message> getByAppointment(int recipientId, String appointment) {
        return messageRepository.getByAppointment(recipientId, appointment);
    }

    public List<Message> getByRecipientIdAAndSenderIdAndAppointment(int recipientId, int senderId, String appointment) {
        return messageRepository.getByRecipientIdAAndSenderIdAndAppointment(recipientId, senderId, appointment);
    }

    @Override
    public Message create(Message entity) {
        return messageRepository.save(entity);
    }

    @Override
    public Message getById(int id) {
        return messageRepository.findById(id).orElse(null);
    }

    @Override
    public Message update(Message entity) {
        return messageRepository.save(entity);
    }

    @Override
    public boolean deleteById(int id) {
        messageRepository.deleteById(id);
        return true;
    }

}