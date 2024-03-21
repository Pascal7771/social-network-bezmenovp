package com.getjavajob.training.bezmenovp.socialnetwork.dao.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDto {
    private int id;
    private int recipientId;
    private int senderId;
    private AccountDto accountSender;
    private String textMessage;
    private String timeSend;
    private byte[] img;
    private String appointment;
}