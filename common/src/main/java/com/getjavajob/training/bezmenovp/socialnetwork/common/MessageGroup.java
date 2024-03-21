package com.getjavajob.training.bezmenovp.socialnetwork.common;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Component
@Entity
@Table(name = "group_message")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int recipientId;
    private int senderId;
    private String textMessage;
    private LocalDateTime timeSend;
    private byte[] img;
    private String appointment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageGroup that = (MessageGroup) o;
        return recipientId == that.recipientId && senderId == that.senderId && Objects.equals(textMessage, that.textMessage) && Objects.equals(appointment, that.appointment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipientId, senderId, textMessage, appointment);
    }

}