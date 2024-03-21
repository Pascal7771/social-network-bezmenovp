package com.getjavajob.training.bezmenovp.socialnetwork.dao.dto.util;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class Helper {

    public LocalDate setBirthDay(String dayOfBirth) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dayOfBirth, dateTimeFormatter);
    }

    public byte[] printAvatar(byte[] avatarData) {
        if (avatarData != null && avatarData.length != 0) {
            return avatarData;
        } else {
            InputStream inputStream = null;
            try {
                inputStream = new ClassPathResource("img/default-avatar.jpg").getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                return IOUtils.toByteArray(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}