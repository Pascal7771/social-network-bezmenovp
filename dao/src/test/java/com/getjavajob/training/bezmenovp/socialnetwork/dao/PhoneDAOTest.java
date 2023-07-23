package com.getjavajob.training.bezmenovp.socialnetwork.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class PhoneDAOTest {

    private static final String propertiesPath = "C:/Users/Pasha/Desktop/dev/projects/getjavajob-training/" +
            "social-network/dao/src/test/resources/PropertiesH2.properties";

    @Before
    public void setUp() {
        H2Manager.CreateTables();
        try (AccountDAO accountDAO = new AccountDAO(propertiesPath);) {
            accountDAO.createAccount("PPP", "Pavlov",
                    "Pavel12", "Pavlovich", LocalDate.of(1992, 05, 15),
                    "Frunze 5", "Lenina 2", "myMail", "myICQ",
                    "mySkype", "myInformation");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @After
    public void tearDown() {
        H2Manager.DropTables();
    }

    @Test
    public void createPhones() {
        try (PhoneDAO phoneDAO = new PhoneDAO(propertiesPath);) {
            String actualPhones = phoneDAO.createPhones("+79059097771", "+79059097772", 1);
            String expectedPhones = "+79059097771+79059097772";
            assertEquals(expectedPhones, actualPhones);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getPhoneByID() {
        try (PhoneDAO phoneDAO = new PhoneDAO(propertiesPath);) {
            phoneDAO.createPhones("+79059097771", "+79059097772", 1);
            String actualPhone = phoneDAO.getPhoneByID(1);
            String expectedPhone = "+79059097771";
            assertEquals(expectedPhone, actualPhone);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getWorkPhoneByID() {
        try (PhoneDAO phoneDAO = new PhoneDAO(propertiesPath);) {
            phoneDAO.createPhones("+79059097771", "+79059097772", 1);
            String actualPhone = phoneDAO.getWorkPhoneByID(1);
            String expectedPhone = "+79059097772";
            assertEquals(expectedPhone, actualPhone);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void updatePhonesByID() {
        try (PhoneDAO phoneDAO = new PhoneDAO(propertiesPath);) {
            phoneDAO.createPhones("+79059097771", "+79059097772", 1);
            String actualPhones = phoneDAO.updatePhonesByID("+79059097777", "+7905909778", 1);
            String expectedPhones = "+79059097777+7905909778";
            assertEquals(expectedPhones, actualPhones);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void deletePhonesByID() {
        try (PhoneDAO phoneDAO = new PhoneDAO(propertiesPath);) {
            phoneDAO.createPhones("+79059097771", "+79059097772", 1);
            assertEquals(1, phoneDAO.deletePhonesByID(1));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}