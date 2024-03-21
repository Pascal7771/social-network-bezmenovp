package com.getjavajob.training.bezmenovp.socialnetwork.dao.dto.util;

import com.getjavajob.training.bezmenovp.socialnetwork.common.Group;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class GroupRowMapper implements RowMapper<Group> {

    @Override
    public Group mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Group group = new Group();
        group.setId(resultSet.getInt("id"));
        group.setName(resultSet.getString("name"));
        group.setDescription(resultSet.getString("description"));
        group.setImg(resultSet.getBytes("IMG"));
        group.setIdCreator(resultSet.getInt("idcreator"));
        group.setCreationDate(LocalDate.parse(resultSet.getString("creationdate")));
        return group;
    }

}