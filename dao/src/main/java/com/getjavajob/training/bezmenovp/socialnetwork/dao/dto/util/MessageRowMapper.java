package com.getjavajob.training.bezmenovp.socialnetwork.dao.dto.util;

import com.getjavajob.training.bezmenovp.socialnetwork.common.Message;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

import static java.time.LocalDateTime.parse;

public class MessageRowMapper implements RowMapper<Message> {

    @Override
    public Message mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Message message = new Message();
        message.setId(resultSet.getInt("id"));
        message.setRecipientId(resultSet.getInt("recipientId"));
        message.setSenderId(resultSet.getInt("senderId"));
        message.setTextMessage(resultSet.getString("textMessage"));
        message.setTimeSend(parse(resultSet.getString("timeSend"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        message.setAppointment(resultSet.getString("appointment"));
        message.setImg(resultSet.getBytes("IMG"));
        return message;
    }

}