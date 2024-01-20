package com.getjavajob.training.bezmenovp.socialnetwork.dao;

import com.getjavajob.training.bezmenovp.socialnetwork.domain.Group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class GroupDAO implements AutoCloseable {

    private static final String CREATE = "INSERT INTO GROUPS (group_name, group_description) VALUES(?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM GROUPS";
    private static final String SELECT_BY_ID = "SELECT * FROM GROUPS WHERE group_id = ?";
    private static final String UPDATE_BY_ID = "UPDATE GROUPS SET group_name = ?, group_description = ?" +
            " WHERE group_id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM GROUPS WHERE group_id = ?";
    private final ConnectionPool connectionPool;

    public GroupDAO() {
        connectionPool = new ConnectionPool(16);
    }

    public GroupDAO(String propertiesPath) {
        connectionPool = ConnectionPool.getInstance(propertiesPath, 16);
    }

    @Override
    public void close() throws Exception {
        connectionPool.closeAllConnections();
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
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE, RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, groupName);
            preparedStatement.setString(2, groupDescription);
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    groupID = resultSet.getInt(1);
                }
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return getGroupByID(groupID);
    }

    public List<Group> getAllGroups() throws SQLException {
        List<Group> allGroups = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        try (ResultSet resultSet = connection.createStatement().executeQuery(SELECT_ALL)) {
            while (resultSet.next()) {
                allGroups.add(extractGroupFromDatabase(resultSet));
            }
            connection.commit();
            return allGroups;
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    public Group getGroupByID(int groupID) throws SQLException {
        Group group = null;
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setInt(1, groupID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    group = extractGroupFromDatabase(resultSet);
                }
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return group;
    }

    public Group updateGroupByID(String groupName, String groupDescription, int groupID) throws SQLException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_ID)) {
            preparedStatement.setString(1, groupName);
            preparedStatement.setString(2, groupDescription);
            preparedStatement.setInt(3, groupID);
            preparedStatement.executeUpdate();
            connection.commit();
            return getGroupByID(groupID);
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    public int deleteGroupById(int groupID) throws SQLException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setInt(1, groupID);
            int count = preparedStatement.executeUpdate();
            connection.commit();
            return count;
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

}