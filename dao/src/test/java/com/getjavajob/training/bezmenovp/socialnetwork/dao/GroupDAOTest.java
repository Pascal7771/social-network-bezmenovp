/*package com.getjavajob.training.bezmenovp.socialnetwork.dao;

import com.getjavajob.training.bezmenovp.socialnetwork.domain.Group;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GroupDAOTest {

    private static final String propertiesPath = "C:/Users/Pasha/Desktop/dev/projects/getjavajob-training/" +
            "social-network/dao/src/test/resources/properties.properties";

    @Before
    public void setUp() {
        H2Manager.CreateTables();
    }

    @After
    public void tearDown() {
        H2Manager.DropTables();
    }

    @Test
    public void createGroup() {
        try (GroupDAO groupDAO = new GroupDAO(propertiesPath);) {
            Group actualGroup = groupDAO.createGroup("Bug", "Super Cat");
            Group expectedGroup = new Group(1, "Bug", "Super Cat");
            assertEquals(expectedGroup, actualGroup);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getAllGroups() {
        try (GroupDAO groupDAO = new GroupDAO(propertiesPath);) {
            groupDAO.createGroup("Bug", "Super Cat");
            groupDAO.createGroup("Brag", "Little dog");
            List<Group> actualGroups = groupDAO.getAllGroups();
            Group expectedGroup1 = new Group(1, "Bug", "Super Cat");
            Group expectedGroup2 = new Group(2, "Brag", "Little dog");
            List<Group> expectedGroups = new ArrayList<>();
            expectedGroups.add(expectedGroup1);
            expectedGroups.add(expectedGroup2);
            assertEquals(expectedGroups, actualGroups);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getGroupByID() {
        try (GroupDAO groupDAO = new GroupDAO(propertiesPath);) {
            groupDAO.createGroup("Bug", "Super Cat");
            Group actualGroup = groupDAO.getGroupByID(1);
            Group expectedGroup = new Group(1, "Bug", "Super Cat");
            assertEquals(expectedGroup, actualGroup);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void updateGroupByID() {
        try (GroupDAO groupDAO = new GroupDAO(propertiesPath);) {
            groupDAO.createGroup("Bug", "Super Cat");
            Group actualGroup = groupDAO.updateGroupByID("Brag", "Little Dog", 1);
            Group expectedGroup = new Group(1, "Brag", "Little Dog");
            assertEquals(expectedGroup, actualGroup);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void deleteGroupById() {
        try (GroupDAO groupDAO = new GroupDAO(propertiesPath);) {
            groupDAO.createGroup("Bug", "Super Cat");
            assertEquals(1, groupDAO.deleteGroupById(1));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}*/