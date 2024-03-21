package com.getjavajob.training.bezmenovp.socialnetwork.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Component
@Entity
@Table(name = "message")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int recipientId;
    private int senderId;
    private String textMessage;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timeSend;
    private byte[] img;
    private String appointment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return recipientId == message.recipientId && senderId == message.senderId && Objects.equals(textMessage, message.textMessage) && Objects.equals(appointment, message.appointment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipientId, senderId, textMessage, appointment);
    }

}