package com.getjavajob.training.bezmenovp.socialnetwork.service;

import com.getjavajob.training.bezmenovp.socialnetwork.common.MessageGroup;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.datajpa.repository.GroupMessageRepositoryImp;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.dto.util.ValidateException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@AllArgsConstructor
public class GroupMessageService {
    private MessageValidator validator;
    private GroupMessageRepositoryImp groupMessageRepositoryImp;

    @Transactional
    public MessageGroup create(MessageGroup message) throws ValidateException {
        return groupMessageRepositoryImp.create(message);
    }

    public MessageGroup getById(int idMessage) {
        return groupMessageRepositoryImp.getById(idMessage);
    }

    @Transactional
    public MessageGroup update(MessageGroup messageRequest) throws SQLException, ValidateException {
        validator.validateMessage(messageRequest);
        groupMessageRepositoryImp.update(messageRequest);
        return groupMessageRepositoryImp.getById(messageRequest.getId());
    }

    public List<MessageGroup> getByAppointment(int recipientId, String appointment) {
        return groupMessageRepositoryImp.getByAppointment(recipientId, appointment);
    }

    @Transactional
    public boolean deleteById(int id) {
        groupMessageRepositoryImp.deleteById(id);
        return true;
    }

}