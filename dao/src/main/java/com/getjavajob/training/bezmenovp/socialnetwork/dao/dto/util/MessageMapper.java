package com.getjavajob.training.bezmenovp.socialnetwork.dao.dto.util;

import com.getjavajob.training.bezmenovp.socialnetwork.common.Message;
import com.getjavajob.training.bezmenovp.socialnetwork.common.MessageGroup;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.datajpa.AccountRepository;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.dto.MessageDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;
import static java.time.format.DateTimeFormatter.ofPattern;
import static java.util.Objects.requireNonNull;

@AllArgsConstructor
@Component
public class MessageMapper {
    AccountMapper accountMapper;
    private AccountRepository accountRepository;

    public Message messageToData(MessageDto messageDto) {
        return Message.builder()
                .id(messageDto.getId())
                .recipientId(messageDto.getRecipientId())
                .senderId(messageDto.getSenderId())
                .textMessage(messageDto.getTextMessage())
                .timeSend(LocalDateTime.parse(now().format(ofPattern("yyyy-MM-dd HH:mm:ss"))
                        , ofPattern("yyyy-MM-dd HH:mm:ss")))
                .img(messageDto.getImg())
                .appointment(messageDto.getAppointment())
                .build();
    }

    public MessageDto messageToService(Message messageData) {
        return MessageDto.builder()
                .id(messageData.getId())
                .recipientId(messageData.getRecipientId())
                .senderId(messageData.getSenderId())
                .accountSender(accountMapper.accountToService(requireNonNull(accountRepository.findById(messageData.getSenderId()).orElse(null))))
                .textMessage(messageData.getTextMessage())
                .timeSend(messageData.getTimeSend().format(ofPattern("yyyy-MM-dd HH:mm:ss")))
                .img(messageData.getImg().length == 0 ? null : messageData.getImg())
                .appointment(messageData.getAppointment())
                .build();
    }

    public MessageGroup messageGroupToData(MessageDto messageDto) {
        return MessageGroup.builder()
                .id(messageDto.getId())
                .recipientId(messageDto.getRecipientId())
                .senderId(messageDto.getSenderId())
                .textMessage(messageDto.getTextMessage())
                .timeSend(LocalDateTime.parse(now().format(ofPattern("yyyy-MM-dd HH:mm:ss"))
                        , ofPattern("yyyy-MM-dd HH:mm:ss")))
                .img(messageDto.getImg())
                .appointment(messageDto.getAppointment())
                .build();
    }

    public MessageDto messageGroupToService(MessageGroup messageData) {
        return MessageDto.builder()
                .id(messageData.getId())
                .recipientId(messageData.getRecipientId())
                .senderId(messageData.getSenderId())
                .accountSender(accountMapper.accountToService(requireNonNull(accountRepository.findById(messageData.getSenderId()).orElse(null))))
                .textMessage(messageData.getTextMessage())
                .timeSend(messageData.getTimeSend().format(ofPattern("yyyy-MM-dd HH:mm:ss")))
                .img(messageData.getImg().length == 0 ? null : messageData.getImg())
                .appointment(messageData.getAppointment())
                .build();
    }

}