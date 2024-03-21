package com.getjavajob.training.bezmenovp.socialnetwork.dao.dto.util;

import com.getjavajob.training.bezmenovp.socialnetwork.common.Account;
import com.getjavajob.training.bezmenovp.socialnetwork.common.Roles;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.dto.AccountDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.lang.String.valueOf;
import static java.time.LocalDate.parse;

@AllArgsConstructor
@Component
public class AccountMapper {
    private Helper helper;

    public Account accountToData(AccountDto accountDto) {
        return Account.builder()
                .id(accountDto.getId())
                .surname(accountDto.getSurname())
                .name(accountDto.getName())
                .patronymic(accountDto.getPatronymic())
                .password(accountDto.getPassword())
                .birthDay(helper.setBirthDay(accountDto.getBirthDay()))
                .phone(accountDto.getPhone())
                .workPhone(accountDto.getWorkPhone())
                .address(accountDto.getAddress())
                .workAddress(accountDto.getWorkAddress())
                .email(accountDto.getEmail())
                .icq(accountDto.getIcq())
                .skype(accountDto.getSkype())
                .additionally(accountDto.getAdditionally())
                .img(accountDto.getImg())
                .creationDate(accountDto.getCreationDate() == null ? parse(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                        : parse(accountDto.getCreationDate()))
                .role(accountDto.getRole().equals("USER") || accountDto.getRole() == null || accountDto.getRole().equals("") ? Roles.USER : Roles.ADMIN)
                .build();
    }

    public Account accountToData(Account account, AccountDto accountDto) {
        account.setId(accountDto.getId());
        account.setSurname(accountDto.getSurname());
        account.setName(accountDto.getName());
        account.setPatronymic(accountDto.getPatronymic());
        account.setPassword(accountDto.getPassword());
        account.setBirthDay(helper.setBirthDay(accountDto.getBirthDay()));
        account.setPhone(accountDto.getPhone());
        account.setWorkPhone(accountDto.getWorkPhone());
        account.setAddress(accountDto.getAddress());
        account.setWorkAddress(accountDto.getWorkAddress());
        account.setEmail(accountDto.getEmail());
        account.setIcq(accountDto.getIcq());
        account.setSkype(accountDto.getSkype());
        account.setAdditionally(accountDto.getAdditionally());
        account.setImg(accountDto.getImg());
        account.setCreationDate(accountDto.getCreationDate() == null ? parse(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                : parse(accountDto.getCreationDate()));
        account.setRole(accountDto.getRole().equals("USER") ? Roles.USER : Roles.ADMIN);
        return account;
    }

    public AccountDto accountToService(Account accountData) {
        return AccountDto.builder()
                .id(accountData.getId())
                .surname(accountData.getSurname())
                .name(accountData.getName())
                .patronymic(accountData.getPatronymic())
                .password(accountData.getPassword())
                .birthDay(accountData.getBirthDay().toString())
                .phone(accountData.getPhone())
                .workPhone(accountData.getWorkPhone())
                .address(accountData.getAddress())
                .workAddress(accountData.getWorkAddress())
                .email(accountData.getEmail())
                .icq(accountData.getIcq())
                .skype(accountData.getSkype())
                .additionally(accountData.getAdditionally())
                .img(helper.printAvatar(accountData.getImg()))
                .creationDate(accountData.getCreationDate().toString())
                .role(valueOf(accountData.getRole()))
                .build();
    }

}