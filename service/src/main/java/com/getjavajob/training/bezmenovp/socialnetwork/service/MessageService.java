package com.getjavajob.training.bezmenovp.socialnetwork.service;

import com.getjavajob.training.bezmenovp.socialnetwork.common.Message;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.datajpa.repository.MessageRepositoryImp;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.dto.util.ValidateException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@AllArgsConstructor
public class MessageService {
    private MessageValidator validator;
    private MessageRepositoryImp messageRepositoryImp;

    @Transactional
    public Message create(Message message) throws ValidateException {
        return messageRepositoryImp.create(message);
    }

    @Transactional
    public Message getById(int messageId) {
        return messageRepositoryImp.getById(messageId);
    }

    @Transactional
    public Message update(Message messageRequest) throws SQLException, ValidateException {
        validator.validateMessage(messageRequest);
        messageRepositoryImp.update(messageRequest);
        return messageRepositoryImp.getById(messageRequest.getId());
    }

    @Transactional
    public List<Message> getByAppointment(int recipientI, String appointment) {
        return messageRepositoryImp.getByAppointment(recipientI, appointment);
    }

    @Transactional
    public List<Message> getPrivateDialog(int recipientId, int senderId) {
        return messageRepositoryImp.getByRecipientIdAAndSenderIdAndAppointment(recipientId, senderId, "personal");
    }

    @Transactional
    public boolean deleteById(int id) {
        messageRepositoryImp.deleteById(id);
        return true;
    }

}