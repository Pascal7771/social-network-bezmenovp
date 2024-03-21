package com.getjavajob.training.bezmenovp.socialnetwork.dao.dto.util;

import com.getjavajob.training.bezmenovp.socialnetwork.common.Account;
import com.getjavajob.training.bezmenovp.socialnetwork.common.Roles;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static java.time.LocalDate.parse;

public class AccountRowMapper implements RowMapper<Account> {

    @Override
    public Account mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Account account = new Account();
        account.setId(resultSet.getInt("id"));
        account.setSurname(resultSet.getString("surname"));
        account.setName(resultSet.getString("name"));
        account.setPatronymic(resultSet.getString("patronymic"));
        account.setAddress(resultSet.getString("address"));
        account.setWorkAddress(resultSet.getString("workAddress"));
        account.setEmail(resultSet.getString("email"));
        account.setIcq(resultSet.getString("icq"));
        account.setSkype(resultSet.getString("skype"));
        account.setAdditionally(resultSet.getString("additionally"));
        account.setPassword(resultSet.getString("password"));
        account.setBirthDay(parse(resultSet.getString(("birthDay"))));
        account.setImg(resultSet.getBytes("IMG"));
        account.setCreationDate(parse(resultSet.getString("creationdate")));
        account.setRole(Roles.valueOf(resultSet.getString("role")));
        return account;
    }

}