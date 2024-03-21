package com.getjavajob.training.bezmenovp.socialnetwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DatabaseConnectionChecker {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean isDatabaseConnected() {
        try {
            jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

}