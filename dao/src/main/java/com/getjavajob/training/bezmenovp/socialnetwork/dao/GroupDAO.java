package com.getjavajob.training.bezmenovp.socialnetwork.dao;

import com.getjavajob.training.bezmenovp.socialnetwork.domain.Group;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class GroupDAO implements AutoCloseable {

    private static final String CREATE = "INSERT INTO GROUPS (group_name, group_description) VALUES(?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM GROUPS";
    private static final String SELECT_BY_ID = "SELECT * FROM GROUPS WHERE group_id = ?";
    private static final String UPDATE_BY_ID = "UPDATE GROUPS SET group_name = ?, group_description = ?" +
            " WHERE group_id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM GROUPS WHERE group_id = ?";
    private Connection connection;

    public GroupDAO(String propertiesPath) {
        try (FileInputStream fis = new FileInputStream(propertiesPath)) {
            Properties properties = new Properties();
            properties.load(fis);
            connection = DriverManager.getConnection(properties.getProperty("db.url"),
                    properties.getProperty("db.username"), properties.getProperty("db.password"));
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    private Group extractGroupFromDatabase(ResultSet resultSet) throws SQLException {
        Group group = new Group();
        group.setGroupID(resultSet.getInt("group_id"));
        group.setGroupName(resultSet.getString("group_name"));
        group.setGroupDescription(resultSet.getString("group_description"));
        return group;
    }

    public Group createGroup(String groupName, String groupDescription) throws SQLException {
        int groupID = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE, RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, groupName);
            preparedStatement.setString(2, groupDescription);
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    groupID = resultSet.getInt(1);
                }
            }
        }
        return getGroupByID(groupID);
    }

    public List<Group> getAllGroups() throws SQLException {
        List<Group> allGroups = new ArrayList<>();
        try (ResultSet resultSet = connection.createStatement().executeQuery(SELECT_ALL)) {
            while (resultSet.next()) {
                allGroups.add(extractGroupFromDatabase(resultSet));
            }
            return allGroups;
        }
    }

    public Group getGroupByID(int groupID) throws SQLException {
        Group group = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setInt(1, groupID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    group = extractGroupFromDatabase(resultSet);
                }
            }
        }
        return group;
    }

    public Group updateGroupByID(String groupName, String groupDescription, int groupID) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_ID)) {
            preparedStatement.setString(1, groupName);
            preparedStatement.setString(2, groupDescription);
            preparedStatement.setInt(3, groupID);
            preparedStatement.executeUpdate();
            return getGroupByID(groupID);
        }
    }

    public int deleteGroupById(int groupID) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setInt(1, groupID);
            return preparedStatement.executeUpdate();
        }
    }

}