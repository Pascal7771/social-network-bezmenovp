package com.getjavajob.training.bezmenovp.socialnetwork.dao.dto;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Objects;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlRootElement(name = "AccountDto")
@XmlAccessorType(XmlAccessType.FIELD)
public class AccountDto {
    @XmlTransient
    private int id;
    private String surname;
    private String name;
    private String patronymic;
    private String birthDay;
    private String phone;
    private String workPhone;
    private String address;
    private String workAddress;
    private String email;
    private String icq;
    private String skype;
    private String additionally;
    private String password;
    @XmlTransient
    private byte[] img;
    @XmlTransient
    private String creationDate;
    @XmlTransient
    private String role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountDto that = (AccountDto) o;
        return id == that.id && Objects.equals(surname, that.surname) && Objects.equals(email, that.email)
                && Objects.equals(password, that.password) && Objects.equals(creationDate, that.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, surname, email, password, creationDate);
    }

}