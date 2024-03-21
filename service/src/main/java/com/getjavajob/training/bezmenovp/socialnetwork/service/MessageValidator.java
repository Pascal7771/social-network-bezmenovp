package com.getjavajob.training.bezmenovp.socialnetwork.service;

import com.getjavajob.training.bezmenovp.socialnetwork.common.Message;
import com.getjavajob.training.bezmenovp.socialnetwork.common.MessageGroup;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.dto.util.ValidateException;
import org.springframework.stereotype.Service;

@Service
public class MessageValidator extends Throwable {

    public Message validateMessage(Message message) throws ValidateException {
        validateTextMessage(message.getTextMessage());
        return message;
    }

    protected String validateTextMessage(String textMessage) throws ValidateException {
        if (textMessage == null || textMessage.length() > 500) {
            throw new ValidateException("Text longer than 500 characters");
        } else {
            return textMessage;
        }
    }

    public MessageGroup validateMessage(MessageGroup message) throws ValidateException {
        validateTextMessage(message.getTextMessage());
        return message;
    }

}